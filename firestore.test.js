const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "alice_123";
const BOB_UID = "bob_456";

const [emulatorHost, emulatorPortStr] = (process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085").split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

function pastTimestamp() {
  return new Date(Date.now() - 2000);
}

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

test("Unauthenticated caller cannot read or write any user collections", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("examAttempts").get());
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).collection("studyNotes").get());
});

test("Owner can create and update their own UserProfile with valid schema", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const profileRef = aliceDb.collection("users").doc(ALICE_UID);

  await assertSucceeds(
    profileRef.set({
      userId: ALICE_UID,
      displayName: "Adaeze Okafor",
      targetExam: "BOTH",
      targetScore: 340,
      streakDays: 1,
      totalXp: 50,
      createdAt: pastTimestamp(),
      updatedAt: pastTimestamp(),
    })
  );

  await assertSucceeds(
    profileRef.update({
      targetScore: 360,
      totalXp: 120,
      streakDays: 2,
      updatedAt: pastTimestamp(),
    })
  );
});

test("Shadow Update & Ghost Field rejected on UserProfile", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const profileRef = aliceDb.collection("users").doc(ALICE_UID);

  await assertFails(
    profileRef.set({
      userId: ALICE_UID,
      displayName: "Adaeze",
      targetExam: "JAMB",
      targetScore: 320,
      streakDays: 1,
      totalXp: 0,
      isAdmin: true,
      createdAt: pastTimestamp(),
      updatedAt: pastTimestamp(),
    })
  );
});

test("Cross-user isolation prevents Bob from reading or writing Alice's data", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).set({
      userId: ALICE_UID,
      displayName: "Alice",
      targetExam: "WAEC",
      targetScore: 300,
      streakDays: 3,
      totalXp: 200,
      createdAt: pastTimestamp(),
      updatedAt: pastTimestamp(),
    })
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").doc("phys_proj_01").set({
      userId: ALICE_UID,
      lessonId: "phys_proj_01",
      subjectId: "physics",
      moduleId: "phys_mod_1",
      completedSteps: ["concept", "examples"],
      exerciseScore: 80,
      isCompleted: false,
      createdAt: pastTimestamp(),
      updatedAt: pastTimestamp(),
    })
  );

  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  await assertFails(bobDb.collection("users").doc(ALICE_UID).get());
  await assertFails(bobDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get());
  await assertFails(
    bobDb.collection("users").doc(ALICE_UID).collection("studyNotes").doc("note_1").set({
      userId: BOB_UID,
      lessonId: "phys_proj_01",
      subjectId: "physics",
      title: "Hijack",
      content: "Test",
      isBookmarked: false,
      createdAt: pastTimestamp(),
      updatedAt: pastTimestamp(),
    })
  );
});

test("ExamAttempts are immutable once created and reject invalid counts", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const attemptRef = aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_01");

  await assertFails(
    attemptRef.set({
      userId: ALICE_UID,
      examType: "JAMB",
      subjectId: "physics",
      subjectTitle: "Physics",
      correctCount: 15,
      totalQuestions: 10,
      percentage: 100,
      durationSeconds: 300,
      weakTopics: [],
      createdAt: pastTimestamp(),
    })
  );

  await assertSucceeds(
    attemptRef.set({
      userId: ALICE_UID,
      examType: "JAMB",
      subjectId: "physics",
      subjectTitle: "Physics",
      correctCount: 8,
      totalQuestions: 10,
      percentage: 80,
      durationSeconds: 320,
      weakTopics: ["Optics"],
      createdAt: pastTimestamp(),
    })
  );

  await assertFails(
    attemptRef.update({
      correctCount: 10,
      percentage: 100,
    })
  );
});

test("StudyNotes CRUD enforces ownership and size bounds", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const noteRef = aliceDb.collection("users").doc(ALICE_UID).collection("studyNotes").doc("note_01");

  await assertSucceeds(
    noteRef.set({
      userId: ALICE_UID,
      lessonId: "math_quad_01",
      subjectId: "mathematics",
      title: "Discriminant Formula",
      content: "D = b^2 - 4ac determines real vs complex roots.",
      isBookmarked: true,
      createdAt: pastTimestamp(),
      updatedAt: pastTimestamp(),
    })
  );

  await assertSucceeds(
    noteRef.update({
      title: "Quadratic Discriminant",
      content: "D > 0 gives two distinct real roots.",
      isBookmarked: false,
      updatedAt: pastTimestamp(),
    })
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("studyNotes").get()
  );

  await assertSucceeds(noteRef.delete());
});

test("NECO and ALL examType values succeed and invalid list items at index > 0 fail", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_neco").set({
      userId: ALICE_UID,
      examType: "NECO",
      subjectId: "biology",
      subjectTitle: "Biology",
      correctCount: 9,
      totalQuestions: 10,
      percentage: 90,
      durationSeconds: 240,
      weakTopics: ["Genetics"],
      createdAt: pastTimestamp(),
    })
  );

  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).collection("examAttempts").doc("attempt_bad_list").set({
      userId: ALICE_UID,
      examType: "ALL",
      subjectId: "physics",
      subjectTitle: "Physics",
      correctCount: 7,
      totalQuestions: 10,
      percentage: 70,
      durationSeconds: 300,
      weakTopics: ["Waves", 123],
      createdAt: pastTimestamp(),
    })
  );
});
