package com.example.data.curriculum

object GovernmentCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "government",
      title = "Government Specialization",
      shortName = "Government",
      subtitle = "10 Zero-to-A1 lessons on Statecraft, Constitutions, Nigerian Federalism, Colonial History & Foreign Policy",
      modules = module1To3 + GovernmentCurriculumPart2.module4And5,
      pastQuestions = GovernmentCurriculumPart2.pastQuestions,
    )
  }

  internal fun govConcept(
    heading: String,
    explanation: String,
    keyFormula: String?,
    examTip: String,
    mechanism: String,
    intel: String,
    recap: String,
  ) = ConceptBlock(
    heading = heading.replace(Regex("""^\d+\.\s+"""), "").trim(),
    explanation = explanation,
    keyFormula = keyFormula,
    examTip = examTip,
    mechanismWhyHow = mechanism,
    examIntelligence = intel,
    recap = recap,
  )

  internal fun q(
    id: String,
    topic: String,
    board: String,
    year: Int,
    prompt: String,
    options: List<String>,
    correct: Int,
    hint: String,
    solution: String,
    scheme: String? = null,
  ) = PracticeQuestion(
    id = id,
    topic = topic,
    examType = board,
    year = year,
    question = prompt,
    options = options,
    correctIndex = correct,
    hint = hint,
    stepByStepSolution = solution,
    waecMarkingScheme = scheme,
  )

  val module1To3: List<CourseModule> =
    listOf(
      CourseModule(
        id = "gov_mod_1",
        subjectId = "government",
        indexLabel = "Module 01",
        title = "Core Concepts, Statecraft, Constitutions & Rule of Law",
        summary = "Power, Authority, Legitimacy, Sovereignty, Democracy vs Authoritarianism, Constitutions & Fundamental Rights.",
        lessons =
          listOf(
            Lesson(
              id = "gov_01_power_authority_state",
              moduleId = "gov_mod_1",
              subjectId = "government",
              title = "Power, Authority, Legitimacy, Sovereignty & State Forms",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 1 • WAEC Basic Political Concepts",
              learningOutcomes =
                listOf(
                  "Distinguish raw Power from rightful Authority (Max Weber's three types) and Legitimacy.",
                  "Identify the four essential elements of a Sovereign State vs Nation & Society.",
                  "Compare Democracy, Aristocracy, Monarchy, Fascism, and Socialism cleanly.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Power vs Authority vs Legitimacy",
                    "Suppose an armed bandit holds up a highway bus vs a uniformed traffic officer directing cars at an intersection. Both compel action, yet only the officer holds AUTHORITY (the recognized, lawful right to command) backed by LEGITIMACY (public acceptance that the ruler ought to be obeyed). Power without legitimacy survives solely on coercion and breaks down rapidly.",
                    "\\text{Authority} = \\text{Power} + \\text{Rightful Mandate} + \\text{Consent (Legitimacy)}",
                    "Max Weber classified three Authority sources: Traditional (hereditary Emir/Oba), Charismatic (personal magnetic appeal), and Legal-Rational (constitutional office & ballot).",
                    "Legitimacy flows from credible elections, constitutional legality, respect for rights, and effective public welfare.",
                    "JAMB frequently tests Weber's Legal-Rational Authority in modern constitutional republics.",
                    "Power compels obedience; Authority is rightful power; Legitimacy is citizen acceptance.",
                  ),
                  govConcept(
                    "2. Sovereignty & Elements of the State",
                    "A Sovereign State is NOT merely a cultural group (Nation) or social club (Society): it possesses four non-negotiable pillars—Defined Territory, Permanent Population, Organized Government, and Sovereignty (supreme internal authority over every citizen plus external independence from foreign dictate).",
                    "\\text{State} = \\text{Population} + \\text{Territory} + \\text{Government} + \\text{Sovereignty}",
                    "Jean Bodin (1576) pioneered modern Sovereignty theory; John Austin located legal sovereignty in a determinate supreme lawmaking body.",
                    "Internal limitations on sovereignty come from constitutional Fundamental Rights and Judicial Review; external limits come from international treaties (UN, ECOWAS).",
                    "WAEC contrasts 'De Facto Sovereignty' (actual physical control via military force) against 'De Jure Sovereignty' (lawful constitutional title).",
                    "State needs Population, Territory, Government, Sovereignty; De Jure = legal right.",
                  ),
                  govConcept(
                    "3. Political Culture & Political Socialization",
                    "Why do citizens in one country queue patiently at ballot boxes while others shun voting? Political Socialization is the lifelong transmission of civic values via Family (first agent!), Schools, Peer Groups, Mass Media, and Political Parties. Almond & Verba grouped Political Culture into Parochial (unaware), Subject (passive obeyers), and Participant (active voters/critics).",
                    "\\text{Socialization Agents}: \\text{Family} \\rightarrow \\text{School} \\rightarrow \\text{Peers/Media} \\rightarrow \\text{Parties}",
                    "Family lays early attitudes toward authority; Schools instill national anthems, history, and civic rights.",
                    "High Participant culture strengthens democratic accountability and curbs electoral apathy.",
                    "NECO often asks for the primary/earliest agent of political socialization (Family).",
                    "Socialization shapes Political Culture; Family is first; Participant culture drives democracy.",
                  ),
                  govConcept(
                    "4. Democracy vs Totalitarianism & Fascism",
                    "Direct Democracy lets citizens vote directly on laws (ancient Athens, Swiss Referendums); Representative Democracy delegates power through free, periodic, secret-ballot elections under majority rule + minority protection. Contrast Totalitarian Fascism/Nazism: one-party state worship, suppressed dissent, state-controlled media, and aggressive nationalism.",
                    "\\text{Democracy} = \\text{Competitive Elections} + \\text{Opposition Rights} + \\text{Rule of Law}",
                    "Recall that 'Popular Sovereignty' means supreme power belongs ultimately to the electorate.",
                    "Secret ballot + Independent Electoral Commission + Free Press protect peaceful power alternation.",
                    "JAMB contrasts Liberal Democracy vs One-Party/Military Authoritarian regimes.",
                    "Representative democracy requires competitive choice, free press, and protected opposition.",
                  ),
                  govConcept(
                    "5. Capitalism, Socialism & Communalism",
                    "Political Ideologies organize property and state control: Capitalism protects private capital and market competition; Socialism prioritizes public ownership of key industries and equitable welfare; Communism envisions a stateless, classless cooperative society; Communalism stresses traditional collective land ownership and kinship reciprocity.",
                    "\\text{Capitalism (Private Market)} \\longleftrightarrow \\text{Mixed} \\longleftrightarrow \\text{Socialism (Public Ownership)}",
                    "Karl Marx authored 'The Communist Manifesto' (with Engels) and 'Das Kapital', arguing class struggle between bourgeoisie and proletariat drives change.",
                    "Most modern African constitutional states run Mixed Economies blending private entrepreneurship with public social infrastructure.",
                    "WAEC distinguishes Communalism (kinship cooperative land tenure) from modern industrial Socialism.",
                    "Capitalism = private enterprise; Socialism = state/public stewardship; Marx = class struggle.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Classifying Authority, Legitimacy & Statehood",
                    "WAEC WASSCE-Style Worked Problem",
                    "Compare two local governors: Governor X wins an election widely certified free and fair, whereas General Y seizes radio stations in an overnight coup and rules by decree while holding effective control of troops. Classify their sovereignty and authority types.",
                    listOf(
                      "Step 1 — Governor X holds De Jure Sovereignty and Legal-Rational Authority grounded in constitutional ballot consent (high Legitimacy).",
                      "Step 2 — General Y holds De Facto power (actual physical troop obedience) yet lacks De Jure constitutional legitimacy.",
                      "Step 3 — Mention that military coups suspend the constitution and rule via Decrees rather than legislative Acts.",
                    ),
                    "Governor X: Legal-Rational Authority & De Jure Legitimacy; General Y: De Facto Control",
                    "Calling a military junta's effective control of barracks 'De Jure authority'—De Jure strictly means lawful constitutional title!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Local Ward Townhall vs Constitutional Court",
                    "Nigerian Civic Institutions",
                    "Citizens accept a Supreme Court election verdict not because judges command army tanks, but because constitutional legitimacy binds all tiers.",
                    "Illustrates Legal-Rational Authority in action.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Explore how Constitutional Type, Federal vs Unitary allocation, and Legislative veto checks govern power balance.",
              exercises =
                listOf(
                  q("ex_gov_01_1", "Weber Authority", "JAMB", 2021, "According to Max Weber, authority exercised by an elected President strictly by virtue of constitutional office is:", listOf("Traditional authority", "Charismatic authority", "Legal-rational authority", "De facto tyranny"), 2, "Grounded in written laws and procedures.", "Legal-rational authority derives from codified rules and offices."),
                  q("ex_gov_01_2", "Elements of State", "WAEC", 2022, "Which attribute distinguishes a sovereign State cleanly from a cultural Nation?", listOf("Common language", "Shared folklore", "Supreme internal & external Sovereignty within a defined territory", "Similar cuisine"), 2, "States hold legal sovereignty; Nations are cultural communities.", "Sovereignty and organized government define a State."),
                  q("ex_gov_01_3", "Socialization", "JAMB", 2023, "Which institution serves as the earliest foundational agent of political socialization?", listOf("The Armed Forces", "The Family", "Trade Unions", "ECOWAS Parliament"), 1, "Shapes childhood attitudes toward rules first.", "Family socializes norms before school or parties."),
                  q("ex_gov_01_4", "Sovereignty Types", "JAMB", 2020, "Power exercised strictly by actual physical control rather than constitutional legality is called:", listOf("De jure sovereignty", "De facto sovereignty", "Titular monarchy", "Parliamentary privilege"), 1, "'In fact' vs 'in law'.", "De facto means actual control regardless of legal title."),
                  q("ex_gov_01_5", "Political Culture", "WAEC", 2021, "Almond and Verba described citizens who know about government and actively vote, protest, and lobby as holding a:", listOf("Parochial culture", "Subject culture", "Participant political culture", "Feudal allegiance"), 2, "Active civic engagement.", "Participant political culture engages both inputs and outputs."),
                  q("ex_gov_01_6", "Ideologies", "NECO", 2022, "Fascism as an ideology is fundamentally characterized by:", listOf("Decentralized regional autonomy", "Extreme nationalism, one-party dictatorship & suppression of opposition", "Worker cooperatives without state power", "Multi-party judicial supremacy"), 1, "Dictatorial ultranationalism.", "Fascism subordinates individual liberty completely to an authoritarian leader/state."),
                ),
              prerequisites = listOf("SS1 Civic Education basics"),
              dependencySummary = "Understanding Sovereignty and Legitimacy leads directly into Written Constitutions & Rule of Law.",
              vocabulary =
                listOf(
                  VocabularyEntry("Sovereignty", "Supreme, indivisible legal authority within a state's borders free of external dictation.", "Final say inside & outside borders.", "Coined systematically by Jean Bodin."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Nation and State are identical terms", "Newspapers swap them casually.", "A Nation is a cultural/linguistic group (e.g., Kurds or Yoruba); a State is a sovereign political entity (e.g., Nigeria).", "States hold Sovereignty; Nations hold shared identity."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Explain two practical limitations that prevent any modern democratic government from claiming absolute unlimited sovereignty.",
                  "Standard",
                  4,
                  listOf("Think about domestic courts/constitutional rights.", "Think about international treaties & global trade dependencies."),
                  "Separate internal vs external limits.",
                  "1. Internal: Written Constitution, Fundamental Human Rights, and Judicial Review void ultra vires laws. 2. External: UN/ECOWAS treaties, international law, and global economic interdependence.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Civic Authority & Legitimacy Audit",
                  "Constitutional Governance Dossier",
                  "Evaluate why transparent elections and responsive municipal services build stronger compliance than force alone.",
                  listOf("Map Weber's 3 authority types across Nigerian civic life", "Audit 4 legitimacy pillars", "Identify external sovereignty treaties"),
                  listOf("Distinguishes De Jure vs De Facto cleanly", "Applies Socialization agents accurately", "Separates Nation vs State"),
                  "Legal-rational elections plus visible public service legitimacy lower policing costs and stabilize constitutional alternation.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "State = Pop + Terr + Govt + Sovereignty; Weber Authority = Trad/Charismatic/Legal-Rational.",
                  listOf("De Jure: By Law", "De Facto: Actual Control", "Bodin: Sovereignty Theorist"),
                  listOf("Swapping Nation (cultural) with State (legal)", "Confusing Power with Legitimate Authority"),
                  "Family First Socializer; Legal-Rational = Constitution; De Jure = Legal Title.",
                ),
            ),
            Lesson(
              id = "gov_02_constitutions_rule_law",
              moduleId = "gov_mod_1",
              subjectId = "government",
              title = "Constitutions, Constitutionalism, Rule of Law & Rights",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 2 • WAEC Constitutions & Human Rights",
              learningOutcomes =
                listOf(
                  "Classify Written vs Unwritten, Rigid vs Flexible, and Federal vs Unitary constitutions.",
                  "Apply A.V. Dicey's three pillars of the Rule of Law and identify exceptions (diplomatic/executive immunity).",
                  "Contrast Constitutionalism, Habeas Corpus, and Fundamental Human Rights safeguards.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Written vs Unwritten & Rigid vs Flexible",
                    "Think of a Written Constitution (Nigeria 1999, USA 1787) as a single codified rulebook locked in a safe, while an Unwritten Constitution (United Kingdom) combines centuries of Acts of Parliament (Magna Carta 1215, Bill of Rights 1689), court precedents, and conventions without one single book. Rigid constitutions demand special supermajorities (e.g., 2/3 National Assembly + 2/3 States in Nigeria) to amend; Flexible ones change by ordinary legislative vote.",
                    "\\text{Federal States} \\implies \\text{Written + Rigid Constitution Required}",
                    "Why must a Federation ALWAYS be Rigid & Written? Regions/States need written certainty that the federal center cannot unilaterally abolish state powers overnight!",
                    "Supermajority amendment locks prevent transient parliaments from erasing minority safeguards.",
                    "JAMB repeatedly highlights UK/New Zealand for Unwritten/Flexible vs Nigeria/USA for Written/Rigid.",
                    "Written = single codified document; Rigid = special amendment hurdle (vital for federalism).",
                  ),
                  govConcept(
                    "2. Constitutionalism vs Bare Constitution",
                    "A dictatorship can print a fancy paper constitution and ignore every line of it! Constitutionalism means rulers genuinely OBEY constitutional limits—courts overturn ultra vires executive orders, budgets need legislative sanction, and fundamental rights bind the police.",
                    "\\text{Constitutionalism} = \\text{Limited Government + Enforceable Checks}",
                    "Ultra Vires acts ('beyond legal powers') are declared null and void by competent courts.",
                    "Limited power channels political rivalry into courts and ballots instead of arbitrary detentions.",
                    "WAEC defines Constitutionalism as adherence to both the letter and limiting spirit of the constitution.",
                    "A constitution is the text; Constitutionalism is ruling strictly within those legal limits.",
                  ),
                  govConcept(
                    "3. A.V. Dicey's Three Principles of the Rule of Law",
                    "British jurist A.V. Dicey anchored the Rule of Law on three legs: (1) Supremacy of Regular Law (no citizen punished except for a clear breach proved in open ordinary court—zero arbitrary arrest!), (2) Equality Before the Law (ministers and artisans face the same regular courts), and (3) Rights Guaranteed by Judicial Decisions rather than bare paper promises.",
                    "\\text{Dicey}: \\text{No Arbitrary Rule} + \\text{Equality Before Law} + \\text{Independent Courts}",
                    "Real-world limits/exceptions to equality exist: Diplomatic Immunity for foreign ambassadors, Executive Immunity (Sec. 308 of Nigeria's 1999 Constitution shielding sitting President/Governors from civil/criminal arrest while in office), Parliamentary Privilege inside chambers, and State of Emergency rules.",
                    "Sec. 308 prevents frivolous courtroom paralysis of sitting governors, though they face prosecution once their tenure ends.",
                    "JAMB tests Diplomatic Immunity & Section 308 as recognized exceptions to Rule of Law equality.",
                    "Rule of Law = Supremacy + Equality + Rights; note Immunity & Emergency exceptions.",
                  ),
                  govConcept(
                    "4. Fundamental Human Rights & Legal Writs",
                    "Chapter IV of Nigeria's 1999 Constitution protects Life, Personal Liberty, Fair Hearing, Freedom of Speech/Religion/Movement, and Property. If police detain someone unlawfully past constitutional hours, lawyers seek a Writ of HABEAS CORPUS ('produce the body' in court immediately). Other writs: MANDAMUS (compels public duty) and CERTIORARI (moves lower tribunal case up for review).",
                    "\\text{Unlawful Detention Remedy} \\implies \\text{Writ of Habeas Corpus}",
                    "Even Fundamental Rights face lawful restrictions: libel laws limit speech, quarantine limits movement, and court convictions restrict liberty.",
                    "Independent judges + Free Press + Legal Aid turn parchment rights into living protection.",
                    "NECO matches Habeas Corpus (unlawful detention) vs Mandamus (compel official duty).",
                    "Habeas Corpus frees unlawful detainees; Mandamus compels official public duty.",
                  ),
                  govConcept(
                    "5. Citizenship Acquisition & Loss",
                    "Nigerian citizenship arises via Birth (born to at least one Nigerian parent/grandparent), Registration (foreign spouses married to Nigerians or diaspora ties), or Naturalization (long residency 15+ years, good character, local community integration, oath). Only Naturalized citizens can lose citizenship (denaturalization for treason/disloyalty) and cannot be President.",
                    "\\text{Citizenship}: \\text{Birth} \\mid \\text{Registration} \\mid \\text{Naturalization}",
                    "A citizen by BIRTH in Nigeria cannot simply be stripped of citizenship involuntarily.",
                    "Civic duties balance citizenship rights: paying taxes, obeying laws, jury/witness service, and defending sovereignty.",
                    "JAMB verifies residency/oath rules distinguishing Birth vs Naturalization.",
                    "Citizenship by Birth is irrevocable; Naturalized citizens satisfy residency/oath rules.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Auditing Rule of Law Exceptions & Constitutional Writs",
                    "JAMB UTME-Style Worked Problem",
                    "A trade-union leader is held without charge for 14 days by security officers, while simultaneously a foreign envoy escapes a speeding ticket due to diplomatic plates. Identify the judicial writ for the detainee and explain how diplomatic status interacts with Dicey's Rule of Law.",
                    listOf(
                      "Step 1 — Detainee remedy: apply at High Court for a Writ of Habeas Corpus compelling officers to produce him and justify lawful cause.",
                      "Step 2 — Envoy immunity: Diplomatic Immunity is an accepted international exception to A.V. Dicey's Equality Before the Law principle.",
                      "Step 3 — Note that host states can still declare an abusive diplomat 'Persona Non Grata' and expel them.",
                    ),
                    "Writ of Habeas Corpus; Diplomatic Immunity is a standard exception to Dicey's Equality pillar",
                    "Confusing Habeas Corpus (produce detainee) with Mandamus (command public official to perform a statutory duty).",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Supreme Court Overturning an Unlawful Local Decree",
                    "Judicial Constitutionalism",
                    "When a legislature passes an act encroaching on another tier's exclusive list, Supreme Court Judicial Review declares it unconstitutional and void.",
                    "Real-world enforcement of Constitutional Supremacy.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Switch constitutional systems (Written/Unwritten, Presidential/Parliamentary) and inspect judicial review & immunity rules.",
              exercises =
                listOf(
                  q("ex_gov_02_1", "Dicey Rule of Law", "JAMB", 2021, "Who popularized the three classical principles of the Rule of Law in 1885?", listOf("Montesquieu", "A.V. Dicey", "Thomas Hobbes", "Jean-Jacques Rousseau"), 1, "British constitutional jurist.", "Albert Venn Dicey formulated Rule of Law principles in 'Introduction to the Study of the Law of the Constitution'."),
                  q("ex_gov_02_2", "Written & Rigid Link", "WAEC", 2022, "Why does a Federal state almost universally adopt a Written and Rigid Constitution?", listOf("To simplify rapid overnight abolition of states", "To safeguard division of powers clearly between center and component units", "To abolish courts", "To eliminate elections"), 1, "Guarantees component state autonomy.", "Clear written division cannot be altered unilaterally by the central legislature alone."),
                  q("ex_gov_02_3", "Prerogative Writs", "JAMB", 2023, "A High Court order compelling security agents to bring an uncharged detainee before a judge immediately is:", listOf("Mandamus", "Certiorari", "Habeas Corpus", "Quo Warranto"), 2, "Literally 'you shall have the body'.", "Habeas Corpus guards personal liberty against arbitrary detention."),
                  q("ex_gov_02_4", "Immunity Exception", "JAMB", 2020, "Under Nigeria's 1999 Constitution, Section 308 grants sitting Governors/President temporary immunity from court prosecution. This illustrates:", listOf("Complete abolition of courts", "A constitutional exception/limitation to Equality Before the Law", "Charismatic legitimacy", "Unwritten convention"), 1, "Recognized practical carve-out during office term.", "Section 308 temporarily pauses civil/criminal suits until tenure concludes."),
                  q("ex_gov_02_5", "UK Constitution", "WAEC", 2021, "Britain's constitution is classically described as:", listOf("Written and Rigid", "Unwritten and Flexible", "Unwritten and Confederal", "Military Decree"), 1, "Statutes, conventions, and case law changeable by simple majority.", "UK Parliament can amend constitutional statutes by normal legislative votes."),
                  q("ex_gov_02_6", "Citizenship", "NECO", 2022, "Which method of acquiring Nigerian citizenship can NEVER be revoked involuntarily?", listOf("Naturalization", "Honorary conferment", "Citizenship by Birth", "Conditional registration"), 2, "Inherent constitutional birthright.", "Section 30 permits deprivation ONLY for registered or naturalized citizens."),
                ),
              prerequisites = listOf("Power, Authority & Sovereignty"),
              dependencySummary = "Written Constitutions assign powers cleanly across the Three Organs of Government in Lesson 3.",
              vocabulary =
                listOf(
                  VocabularyEntry("Constitutionalism", "Practice whereby government operates strictly within constitutional limits and courts strike down excesses.", "Actually obeying the constitution, not just printing it.", "Core requirement of democratic liberty."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Unwritten Constitution means ZERO written laws exist", "'Unwritten' sounds wordless.", "Britain's 'unwritten' constitution includes famous written Acts like Magna Carta 1215 & Human Rights Act 1998—'unwritten' simply means NOT compiled in ONE single master code!", "Uncodified ≠ devoid of written statutes."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "List three recognized practical limitations to A.V. Dicey's Rule of Law principle in Nigeria.",
                  "Standard",
                  3,
                  listOf("Think of embassies, governors (Sec 308), and national emergencies.", "Consider administrative tribunals or delayed justice."),
                  "Provide three concrete constitutional/practical limits.",
                  "1. Executive Immunity (Sec. 308) for sitting President/Governors. 2. Diplomatic Immunity for foreign envoys. 3. States of Emergency / Preventive detention powers during war/insurgency.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Fundamental Rights Defence & Writs Clinic",
                  "Constitutional Review Brief",
                  "Evaluate 3 citizen petitions involving unlawful arrest, refusal of a local council to issue a statutory permit, and emergency curfews.",
                  listOf("Match Habeas Corpus & Mandamus to petitions", "Verify lawful right restrictions during emergency", "Cite Dicey Rule of Law safeguards"),
                  listOf("Correct writ matches every fact pattern", "Distinguishes lawful restriction from abuse", "Explains Constitutionalism checks"),
                  "Issue Habeas Corpus for uncharged detention, Mandamus to compel statutory council permit processing, and check proportionality of emergency curfews.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Federal → Written+Rigid; UK → Unwritten+Flexible; Dicey = Supremacy + Equality + Judicial Rights.",
                  listOf("Habeas Corpus: Free Detainee", "Mandamus: Compel Official Duty", "Sec 308: Executive Immunity"),
                  listOf("Equating Unwritten with zero statutes", "Forgetting Sec 308/Diplomatic exceptions"),
                  "Constitutionalism = Limited Govt; Writs Protect Citizens Against Ultra Vires Acts.",
                ),
            ),
          ),
      ),
      CourseModule(
        id = "gov_mod_2",
        subjectId = "government",
        indexLabel = "Module 02",
        title = "Organs of Government, Systems & Nigerian Federalism",
        summary = "Legislature, Executive, Judiciary, Separation of Powers, Presidential vs Parliamentary, Federalism & Local Govt.",
        lessons =
          listOf(
            Lesson(
              id = "gov_03_organs_checks_balances",
              moduleId = "gov_mod_2",
              subjectId = "government",
              title = "Organs of Government, Checks & Balances & Executive Systems",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 3–4 • WAEC Separation of Powers & Cabinet/Presidential",
              learningOutcomes =
                listOf(
                  "Map lawmaking, execution, and adjudication roles across Legislature, Executive, and Judiciary.",
                  "Compare Separation of Powers (Presidential USA/Nigeria) vs Fusion of Powers (UK Parliamentary).",
                  "Evaluate Delegated Legislation merits, abuse risks, and legislative/judicial controls.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Montesquieu's Separation of Powers & Checks & Balances",
                    "Baron de Montesquieu warned in 'The Spirit of the Laws' (1748) that uniting lawmaking, law enforcement, and judging inside the same person guarantees tyranny. Therefore, power splits across three organs: Legislature (makes bills/approves budgets), Executive (implements laws/commands forces), and Judiciary (interprets constitution & tries disputes)—kept honest by mutual CHECKS & BALANCES (Executive veto, Senate confirmations, Judicial Review, Legislative impeachment).",
                    "\\text{Checks}: \\text{Veto} \\leftrightarrow \\text{2/3 Override} \\mid \\text{Judicial Review} \\mid \\text{Impeachment}",
                    "Complete watertight separation would paralyze government; Checks & Balances deliberately interlock powers so 'ambition counteracts ambition'.",
                    "Two-thirds legislative override prevents an obstinate president from blocking popular laws permanently.",
                    "JAMB credits Baron de Montesquieu directly for Separation of Powers.",
                    "Legislature makes laws; Executive enforces; Judiciary interprets; Checks prevent monopoly.",
                  ),
                  govConcept(
                    "2. Unicameral vs Bicameral Legislature & Lawmaking Stages",
                    "A Unicameral house (1 chamber, e.g., Nigerian State Houses of Assembly, Ghana) cuts cost and passes bills fast. A Bicameral National Assembly (Nigeria: 109-member Senate + 360-member House of Representatives) gives minority/smaller states equal Senate voice (3 per state + 1 FCT = 109!) and adds a cooling second check on hasty bills through First Reading, Second Reading, Committee Stage, Report, Third Reading, Concurrence, and Presidential Assent.",
                    "\\text{Nigeria NASS}: 109\\text{ Senators } (3\\times 36 + 1) + 360\\text{ Reps} = 469",
                    "Remember: Committee Stage (line-by-line scrutiny & public hearings) is where bills are reshaped most thoroughly!",
                    "Bicameralism prevents majoritarian steamrolling in populous federations.",
                    "WAEC asks for exact Senate composition (3 per state + 1 FCT = 109) and 2/3 override of Presidential veto.",
                    "Committee Stage refines bill details; Bicameral protects states & slows hasty laws.",
                  ),
                  govConcept(
                    "3. Presidential vs Parliamentary (Cabinet) Systems",
                    "Compare Nigeria's 1979/1999 Presidential System with Britain's (and Nigeria's 1960–66 First Republic) Parliamentary System. Under Presidentialism, Executive & Legislature sit separately (Head of State = Head of Government = President; ministers CANNOT sit in parliament; fixed tenure). Under Parliamentarism, FUSION OF POWERS rules: Prime Minister and Cabinet MUST be MPs drawn from the majority party, under COLLECTIVE RESPONSIBILITY and a Vote of No Confidence!",
                    "\\text{Presidential}: \\text{Single Exec + Separation + Fixed Term} \\;\\mid\\; \\text{Parliamentary}: \\text{Dual Exec + Fusion}",
                    "Under Parliamentary Collective Responsibility, every minister must either publicly defend Cabinet decisions or RESIGN—none can criticize policy from inside!",
                    "Vote of No Confidence can topple a Parliamentary government mid-term without waiting 4 years, unlike Presidential Impeachment strictly for gross misconduct.",
                    "JAMB loves 'Dual Executive' (Ceremonial President/Monarch as Head of State vs Prime Minister as Head of Government) as the hallmark of Parliamentarism.",
                    "Presidential = Separation + Single Head; Parliamentary = Fusion + Dual Head + Collective Resp.",
                  ),
                  govConcept(
                    "4. Judiciary Independence & Neutrality",
                    "Judges must rule fairly without fearing angry politicians. Five safeguards protect Judicial Independence: non-partisan appointment via Judicial Service Commissions (NJC), security of tenure (removal only by strict supermajority/misconduct address), salaries charged directly on the Consolidated Revenue Fund (insulated from annual cuts!), immunity from lawsuits over rulings, and judicial separation.",
                    "\\text{Consolidated Revenue Fund} \\implies \\text{Judges' Pay Immune from Annual Cuts}",
                    "If Parliament could slash judicial pay each December, judges would tremble before ministers.",
                    "Impartial courts assure foreign investors and opposition parties alike that disputes get fair law-based hearings.",
                    "WAEC lists Consolidated Fund pay & NJC security of tenure as top judicial independence pillars.",
                    "Security of tenure + NJC + Consolidated Revenue Fund protect impartial justice.",
                  ),
                  govConcept(
                    "5. Delegated Legislation: Pros & Safeguards",
                    "Modern National Assemblies cannot code every aviation safety rule or university fee schedule, so Parent Acts delegate rule-making to Ministers (Statutory Instruments), Municipal Councils (Byelaws), and Agencies (Order-in-Council). While Delegated Legislation saves parliamentary time and taps specialist engineers, unchecked bureaucrats could abuse it—so Courts strike down 'Ultra Vires' regulations and Parliamentary Scrutiny Committees review them.",
                    "\\text{Parent Act} \\rightarrow \\text{Statutory Instrument / Byelaw} \\rightarrow \\text{Judicial Ultra Vires Control}",
                    "Local Governments issue BYELAWS; Ministers issue STATUTORY INSTRUMENTS/REGULATIONS.",
                    "Expert agencies adapt technical rules quickly as vaccines, telecoms, or prices evolve.",
                    "JAMB links Byelaws specifically to Local Government Councils.",
                    "Delegated rules speed technical governance; Parliamentary & Judicial Ultra Vires checks prevent abuse.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Presidential vs First Republic Parliamentary Structure",
                    "WAEC WASSCE-Style Worked Problem",
                    "Compare Nigeria's First Republic (1963) Parliamentary Executive with the 1999 Presidential Executive across: (a) Head of State vs Head of Government, (b) Cabinet membership in parliament, and (c) removal mechanism.",
                    listOf(
                      "Step 1 — Heads: 1963 split Ceremonial President (Azikiwe) from Head of Government PM (Balewa); 1999 combines BOTH offices in an elected Executive President.",
                      "Step 2 — Parliament overlap: 1963 required ministers to remain MPs (Fusion); 1999 requires nominated ministers to resign/stay outside National Assembly (Separation).",
                      "Step 3 — Removal: Parliamentary PM loses office upon a simple Parliamentary Vote of No Confidence; Presidential removal demands difficult 2/3 Impeachment trials for gross misconduct.",
                    ),
                    "Dual Exec + Fusion + No-Confidence (1963) vs Single Exec + Separation + Impeachment (1999)",
                    "Assuming a Presidential Minister can sit and vote in Nigeria's National Assembly—Constitution requires executive separation!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "National Assembly 2/3 Veto Override & Budget Appropriation",
                    "Federal Checks & Balances",
                    "When the President declines assent to a bill, two-thirds concurrence in both Senate and House turns it into law automatically without presidential signature.",
                    "Exemplifies active Legislative Checks over Executive Veto.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Compare Presidential Separation vs Parliamentary Fusion and test supermajority checks, revenue allocation, and court review.",
              exercises =
                listOf(
                  q("ex_gov_03_1", "Separation Theorist", "JAMB", 2021, "The classical doctrine of Separation of Powers was systematically articulated by:", listOf("A.V. Dicey", "Baron de Montesquieu", "Thomas Hobbes", "Karl Marx"), 1, "Author of 'The Spirit of the Laws' (1748).", "Montesquieu warned uniting executive, legislative, and judicial power breeds tyranny."),
                  q("ex_gov_03_2", "Cabinet Principle", "WAEC", 2022, "Under Parliamentary Collective Responsibility, a cabinet minister who publicly rejects an agreed Cabinet policy must:", listOf("Call a presidential veto", "Resign from the Cabinet", "Dissolve the Supreme Court", "Ignore Parliament"), 1, "Speak with one united voice or step down.", "Disagreeing ministers resign rather than undercut Cabinet unity."),
                  q("ex_gov_03_3", "Dual Executive", "JAMB", 2023, "A key hallmark of the Parliamentary System of Government is:", listOf("A single Head of State & Government", "Separation between Head of State and Head of Government (Dual Executive)", "Ministers banned from parliament", "Fixed four-year executive immunity from legislature"), 1, "Monarch/Ceremonial President + Prime Minister.", "Parliamentarism separates ceremonial unity (Head of State) from executive leadership (PM)."),
                  q("ex_gov_03_4", "Lawmaking Stage", "JAMB", 2020, "At which parliamentary stage is a bill scrutinized clause-by-clause with expert amendments?", listOf("First Reading", "Committee Stage", "Presidential Assent", "Dissolution"), 1, "Detailed line-by-line clause work.", "Standing committees debate, amend, and refine clauses before Third Reading."),
                  q("ex_gov_03_5", "Judicial Safeguard", "WAEC", 2021, "Charging Supreme Court judges' salaries directly onto the Consolidated Revenue Fund primarily protects:", listOf("Party discipline", "Judicial Independence from legislative salary bullying", "Executive decree power", "Tax evasion"), 1, "Insulates pay from yearly parliamentary politics.", "Financial security prevents retaliatory pay cuts."),
                  q("ex_gov_03_6", "Delegated Rules", "NECO", 2022, "Rules enacted by elected Local Government Councils inside their municipalities are called:", listOf("Decrees", "Orders-in-Council", "Bye-laws", "Treaties"), 2, "Municipal delegated legislation.", "Local authorities exercise delegated power via Bye-laws."),
                ),
              prerequisites = listOf("Constitutions & Rule of Law"),
              dependencySummary = "Organ separation extends vertically into Unitary vs Federal tiers and Local Governments in Lesson 4.",
              vocabulary =
                listOf(
                  VocabularyEntry("Collective Responsibility", "Parliamentary rule requiring all Cabinet ministers to stand publicly behind Cabinet decisions or resign.", "Cabinet speaks as one team.", "Contrasts with Individual Ministerial Responsibility."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Judicial Review lets judges draft new budgets", "Confusing review power with lawmaking.", "Judges only invalidate unconstitutional laws/acts (negative check); they do NOT write statutes or budgets.", "Courts strike down ultra vires acts; legislatures write laws."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Explain two advantages and two criticisms of Delegated Legislation in a busy federal democracy.",
                  "Standard",
                  4,
                  listOf("Time pressure & technical expertise are pros; bureaucratic abuse & weak publicity are cons.", "State Parliamentary & Judicial remedies."),
                  "Balance operational efficiency with accountability risk.",
                  "Pros: Saves parliamentary floor time; handles fast technical/emergency regulations. Cons: Undemocratic bureaucratic rule-making; excessive regulations lacking publicity—checked via Ultra Vires court review and Parliamentary Scrutiny Committees.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Executive-Legislative Balance & Accountability Audit",
                  "Institutional Design Matrix",
                  "Compare how Nigerian 1999 Presidential Checks differ from First Republic Parliamentary Crisis resolution.",
                  listOf("Trace bill path through First/Second/Committee/Third Readings + 2/3 Override", "Contrast No-Confidence vs Impeachment", "Design Judicial Review safeguard"),
                  listOf("Accurate NASS numbers (109 Senate, 360 Reps)", "Identifies Fusion vs Separation cleanly", "States NJC & Consolidated Revenue Fund rules"),
                  "Bicameral NASS (109 + 360) plus 2/3 veto override prevents gridlock while NJC/Consolidated Fund insulates courts from partisan retaliation.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Montesquieu: 3 Organs + Checks; Presidential = Separation + Single Head; Parliamentary = Fusion + Dual Head.",
                  listOf("NASS: 109 Senate + 360 Reps", "Bye-laws: Local Govts", "Ultra Vires: Beyond Power"),
                  listOf("Thinking Presidential Ministers sit in NASS", "Confusing Committee Stage vs First Reading"),
                  "Veto Overridden by 2/3 Both Houses; Collective Resp = Defend or Resign.",
                ),
            ),
            Lesson(
              id = "gov_04_federalism_local_govt",
              moduleId = "gov_mod_2",
              subjectId = "government",
              title = "Federal, Unitary & Confederal Systems + Local Government",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 5 • WAEC Federalism & 1976 LG Reform",
              learningOutcomes =
                listOf(
                  "Contrast Unitary (Ghana/UK), Federal (Nigeria/USA), and Confederal (AU/Senegambia) power distribution.",
                  "Classify Nigeria's Exclusive, Concurrent, and Residual Legislative Lists cleanly.",
                  "Evaluate the 1976 Local Government Reform, 3rd Tier status, and Revenue Allocation tension.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Unitary vs Federal vs Confederal Power Distribution",
                    "Imagine a single headquarters commanding local branches (Unitary: UK, Ghana, France—Parliament can create or scrap regions anytime) vs a contractual partnership sharing supreme powers constitutionally between a central government and constituent states (Federal: Nigeria, USA, India—neither tier can abolish the other!) vs a loose league of sovereign nations delegating narrow duties upward (Confederal: early US Articles of Confederation, ECOWAS/AU—weak center, right of secession).",
                    "\\text{Unitary}: \\text{Center Supreme} \\mid \\text{Federal}: \\text{Shared Sovereignty} \\mid \\text{Confederal}: \\text{Units Supreme}",
                    "Why did Nigeria adopt Federalism (Lyttelton 1954)? Vast geography, multi-ethnic diversity (Hausa/Fulani, Yoruba, Igbo, minorities), fear of domination, and administrative efficiency.",
                    "Federalism balances national defense & currency cohesion with local cultural self-rule.",
                    "JAMB contrasts Confederal weakness (weak center, member veto/secession) vs True Federation.",
                    "Unitary = 1 supreme center; Federal = co-ordinate tiers; Confederal = loose treaty league.",
                  ),
                  govConcept(
                    "2. Nigeria's Exclusive, Concurrent & Residual Lists",
                    "Nigeria's 1999 Constitution allocates duties cleanly: (1) Exclusive List (Federal ONLY: Defence, Currency/CBN, Foreign Affairs, Customs, Aviation, Police/Mining), (2) Concurrent List (BOTH Federal & States: Higher Education, Healthcare, Roads, Electricity—if Federal and State laws clash, Federal law PREVAILS!), and (3) Residual List (STATES ONLY: anything omitted from the first two, e.g., Chieftaincy, Primary Markets/Customary Courts).",
                    "\\text{Concurrent Conflict Rule}: \\text{Federal Act Prevails Over State Law}",
                    "Currency & Defence ALWAYS stay on the Exclusive Federal List; Chieftaincy & local matters live in the Residual List.",
                    "Clear list boundaries prevent jurisdictional collisions, leaving the Supreme Court to arbitrate boundary disputes.",
                    "Frequent WAEC question: which list belongs to States alone? Residual List!",
                    "Exclusive = Federal; Concurrent = Shared (Federal wins clash); Residual = States alone.",
                  ),
                  govConcept(
                    "3. Fiscal Federalism & Revenue Allocation Commissions",
                    "In a federation, money must match duties! Nigeria has convened Revenue Commissions—Phillipson (1946), Hicks-Phillipson (1951), Chick (1953), Raisman (1958), Okigbo (1980), and permanent RMAFC—debating principles like Derivation, Need, Population, Equality of States, and Internal Revenue Effort into the Federation Account (Federal ~52.68%, States ~26.72%, LGs ~20.60%, plus 13% oil Derivation).",
                    "\\text{Federation Account}: \\text{Federal} + \\text{36 States} + \\text{774 Local Govts (+13\\% Derivation)}",
                    "Oil/solid-mineral Derivation assigns 13% directly back to producing states before general Federation Account sharing.",
                    "Excessive dependence on central oil allocations weakens state tax accountability; expanding local IGR strengthens fiscal autonomy.",
                    "JAMB asks why Fiscal Federalism disputes arise (tension between Derivation vs Population/Need principles).",
                    "Derivation rewards resource-originating states; RMAFC recommends permanent sharing ratios.",
                  ),
                  govConcept(
                    "4. The Landmark 1976 Local Government Reform",
                    "Before 1976, local councils varied wildly across regions without guaranteed funds. The 1976 Local Government Reform (under General Obasanjo's administration) standardized uniform elected single-tier Local Governments nationwide (now 774 LGAs!) as the official THIRD TIER of government with statutory Federation Account funding and Fourth Schedule duties (primary markets, feeder roads, birth/death registration, sanitary inspection, primary health/schools).",
                    "\\text{1976 Reform} \\implies \\text{Uniform 3rd Tier + Statutory Allocation + Elected Chairs}",
                    "Why did 1976 happen? Bring governance closer to grassroots, train future national leaders, and relieve State bottlenecks.",
                    "Joint State-Local Government Account control historically weakened council autonomy until Supreme Court rulings affirmed direct financial independence.",
                    "Standard NECO/WAEC staple: 1976 Reform made Local Government a uniform 3rd tier.",
                    "1976 standardized 774 uniform LGAs as Nigeria's 3rd tier with Fourth Schedule duties.",
                  ),
                  govConcept(
                    "5. Strengths & Friction Points in Nigerian Federalism",
                    "Federalism safeguards ethnic minorities, spawns state development hubs, and bars central dictatorship. Yet friction persists: State-creation demands, indigene-settler disputes, over-centralized policing/revenue dependence, and minority fears—mitigated through the Federal Character Commission (FCC) ensuring equitable appointments across states.",
                    "\\text{Federal Character} \\implies \\text{Equitable State Representation in Federal Posts}",
                    "National Youth Service Corps (NYSC, 1973), Federal Unity Schools, and Federal Character aim to foster national integration.",
                    "Balancing Merit alongside Regional Equity keeps multi-ethnic federations cohesive.",
                    "JAMB connects NYSC & Federal Character Commission directly to National Unity & Integration.",
                    "Federal Character, NYSC, and Unity Colleges mitigate minority marginalization fears.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Applying Legislative Lists & Clash Resolution",
                    "JAMB UTME-Style Worked Problem",
                    "A State House of Assembly passes a law minting a regional trade currency AND another law upgrading a state university curriculum where an existing Federal National Assembly Act sets minimum accreditation rules. Judge the validity of both state laws.",
                    listOf(
                      "Step 1 — Regional currency: Currency & Legal Tender sit strictly on the EXCLUSIVE FEDERAL LIST → State currency law is VOID immediately.",
                      "Step 2 — University education: Higher Education sits on the CONCURRENT LIST → Valid unless it conflicts with Federal Act.",
                      "Step 3 — Conflict rule: Under Concurrent Clash (Doctrine of Covering the Field), Federal NASS minimum accreditation law PREVAILS to the extent of inconsistency.",
                    ),
                    "Currency law void (Exclusive); University clash resolved in favour of Federal Act",
                    "Thinking Residual List belongs to the Federal Government—Residual matters rest exclusively with States!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Supreme Court Affirming Direct Local Government Fiscal Autonomy",
                    "Third-Tier Governance",
                    "Direct Federation Account disbursements empower all 774 LGAs to pave market access roads and maintain primary health centres without state diversion.",
                    "Strengthens 1976 Third-Tier grassroots accountability.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Test Federal List allocation (Exclusive/Concurrent/Residual) and inspect clash resolution & fiscal sharing ratios.",
              exercises =
                listOf(
                  q("ex_gov_04_1", "Federal Rationale", "JAMB", 2021, "Which primary factor prompted Nigeria to adopt a Federal structure in 1954?", listOf("Uniform single ethnicity", "Vast territory, cultural diversity & minority fear of domination", "Desire for absolute monarchy", "Tiny island size"), 1, "Multicultural & geographical breadth.", "Federalism reconciles unity with regional diversity."),
                  q("ex_gov_04_2", "Legislative Lists", "WAEC", 2022, "Under Nigeria's Constitution, Chieftaincy disputes and primary rural markets fall under the:", listOf("Exclusive Federal List", "Residual List (States)", "Diplomatic List", "Unitary Decree"), 1, "Unmentioned matters stay with States.", "Residual matters belong to State jurisdiction."),
                  q("ex_gov_04_3", "Concurrent Clash", "JAMB", 2023, "When a valid State law conflicts directly with a Federal Act on an item inside the Concurrent List:", listOf("State law automatically cancels the Federal Act", "Federal law prevails to the extent of inconsistency", "Both laws freeze for 10 years", "Local Council Chairman decides"), 1, "Doctrine of Federal supremacy on Concurrent matters.", "Constitution makes Federal Act prevail over conflicting State provision."),
                  q("ex_gov_04_4", "1976 LG Reform", "WAEC", 2021, "The 1976 Local Government Reform was historic because it:", listOf("Abolished all councils", "Established uniform multi-purpose Local Governments as Nigeria's 3rd tier with statutory funds", "Handed councils to colonial District Officers", "Moved defence to local chairmen"), 1, "Standardized grassroots third tier.", "1976 unified structures and created statutory Federation funding."),
                  q("ex_gov_04_5", "Confederalism", "JAMB", 2020, "Which feature distinctly defines a Confederation compared to a true Federation?", listOf("Supreme central legislature binding citizens directly", "Weak center wholly dependent on sovereign member states that retain secession rights", "Single written unitary charter", "Abolition of state borders"), 1, "Sovereignty remains in component states.", "Confederations run by treaty consent with weak central institutions."),
                  q("ex_gov_04_6", "Unity Mechanisms", "NECO", 2022, "Which institution was created specifically to ensure equitable ethnic/state spread in federal appointments?", listOf("Independent Corrupt Practices Commission", "Federal Character Commission", "Code of Conduct Bureau", "Revenue Mobilization Commission"), 1, "Prevents regional monopoly of federal posts.", "Federal Character Commission monitors equitable representation."),
                ),
              prerequisites = listOf("Separation of Powers & Organs"),
              dependencySummary = "Multi-tier elections bring voters, parties, and pressure groups into focus in Module 3.",
              vocabulary =
                listOf(
                  VocabularyEntry("Fiscal Federalism", "Constitutional sharing of revenue sources and expenditure duties across Federal, State, and Local tiers.", "Who collects taxes and how Federation money splits.", "Driven in Nigeria by RMAFC and Derivation rules."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Federalism means weak central government", "Confusing Federation with Confederation.", "A Federation gives BOTH center and states direct sovereign powers over citizens in their respective lists—unlike a weak treaty Confederation!", "Confederation = weak center; Federation = co-ordinate tiers."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "State two constitutional reasons why Local Governments struggle to deliver grassroots infrastructure despite Fourth Schedule powers.",
                  "Standard",
                  3,
                  listOf("Think of State-Local Joint Accounts and weak internal property/market tax capacity.", "Consider staffing & administrative interference."),
                  "Highlight fiscal & political interference bottlenecks.",
                  "1. Historical State control/deductions via State Joint Local Government Accounts & caretaker appointments replacing elected chairs. 2. Weak local tax base / internal revenue leakage.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Grassroots Third-Tier Fiscal Independence Brief",
                  "Intergovernmental Audit",
                  "Propose reforms enabling a rural LGA to boost Internally Generated Revenue without harassing petty market women.",
                  listOf("Separate Fourth Schedule local taxes cleanly", "Digitalize shop/tenement rates transparently", "Align State-Local project tracking"),
                  listOf("Uses Exclusive/Concurrent/Residual distinctions", "Incorporates 1976 Reform mandate", "Eliminates double taxation"),
                  "Digital market-stall receipts and direct LGA treasury transfers eliminate middleman cash leaks and fund rural boreholes visibly.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Unitary (1 center) vs Federal (3 lists) vs Confederal (weak center); 1976 Reform = 3rd Tier.",
                  listOf("Exclusive: Defense/CBN", "Concurrent clash: Federal wins", "Residual: States"),
                  listOf("Giving Residual List to Federal govt", "Confusing Confederation with Federation"),
                  "1954 Lyttelton = Federalism; 1976 = Uniform 3rd Tier; Derivation = 13%.",
                ),
            ),
          ),
      ),
      CourseModule(
        id = "gov_mod_3",
        subjectId = "government",
        indexLabel = "Module 03",
        title = "Elections, Parties, Pressure Groups & Colonial Foundations",
        summary = "Electoral systems (FPTP vs PR), INEC & public opinion, Parties/Pressure groups, Pre-colonial admin & Indirect Rule.",
        lessons =
          listOf(
            Lesson(
              id = "gov_05_electoral_parties_opinion",
              moduleId = "gov_mod_3",
              subjectId = "government",
              title = "Electoral Systems, INEC, Parties, Pressure Groups & Public Opinion",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 6–8 • WAEC Suffrage, Parties & Pressure Groups",
              learningOutcomes =
                listOf(
                  "Compare Simple Plurality (FPTP), Second Ballot, Absolute Majority, and Proportional Representation.",
                  "Distinguish Political Parties (seek office directly) from Pressure Groups (influence policy without fielding rulers).",
                  "Analyze Franchise types, Gerrymandering, INEC independence, and Public Opinion measurement.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Electoral Systems: FPTP vs Absolute Majority vs PR",
                    "In a Simple Plurality / First-Past-The-Post (FPTP) race (Nigeria National Assembly, UK Commons), whoever gets the MOST votes wins even with 34% vs 33% vs 33%! By contrast, Nigeria's Presidential election requires Absolute Majority PLUS constitutional SPREAD (25% in at least 2/3 of 36 States + FCT) or faces a Second-Ballot Runoff. Proportional Representation (PR) awards legislative seats matching each party's exact vote percentage—protecting minority voices while often producing coalition governments.",
                    "\\text{Presidential Win} = \\text{Highest Votes} + \\ge 25\\% \\text{ in } \\frac{2}{3}\\text{ States}",
                    "Gerrymandering manipulates constituency boundaries unfairly to favour the incumbent party before election day!",
                    "FPTP creates stable two-party majorities cleanly, whereas PR maximizes fairness for minor/regional parties.",
                    "JAMB frequently defines Gerrymandering and contrasts FPTP ('winner-takes-all') against PR.",
                    "FPTP = plurality wins; Second Ballot = >50% runoff; PR = seats match vote share.",
                  ),
                  govConcept(
                    "2. Suffrage, Free & Fair Elections & INEC Role",
                    "Universal Adult Suffrage grants voting rights to every sane, qualified adult citizen (age 18 in Nigeria) regardless of wealth or gender—unlike Restricted Franchise based on property or education. Free & Fair elections require an independent Electoral Commission (INEC), updated Voters' Register, Secret Ballot (preventing intimidation), impartial Election Tribunals, and equal state-media access.",
                    "\\text{Credible Election} = \\text{Independent EMB} + \\text{Secret Ballot} + \\text{Impartial Tribunals}",
                    "Secret Ballot removes employer or landlord coercion from polling booths.",
                    "Transparent BVAS/collation chains minimize post-election litigation and boost regime legitimacy.",
                    "WAEC notes 1979 formally extended Universal Adult Suffrage across ALL Nigerian regions (including northern women).",
                    "Universal suffrage = 18+ citizens; Credibility hinges on Secret Ballot & Independent INEC.",
                  ),
                  govConcept(
                    "3. Political Parties & Party Systems",
                    "A Political Party aggregates diverse interests under a common manifesto to nominate candidates, contest elections, win power, and educate voters (or serve as constructive Opposition!). Zero-party (military), One-Party (de jure or de facto dominance), Two-Party (USA/1993 Nigeria SDP-NRC), and Multi-Party (Nigeria 4th Republic) systems balance choice vs coalition stability.",
                    "\\text{Party Goal} = \\text{Capture Govt Power} \\;\\neq\\; \\text{Pressure Group (Influence Policy)}",
                    "Even in opposition, parties hold ministers accountable through parliamentary questions and shadow alternatives.",
                    "Two-party systems clarify voter choice cleanly; multi-party coalitions mirror diverse multi-ethnic societies.",
                    "Distinguish 'Manifesto' (written party action pledge) from 'Zero-Party' non-partisan polls.",
                    "Parties seek political office directly; Opposition keeps sitting governments accountable.",
                  ),
                  govConcept(
                    "4. Pressure Groups: Types & Lobbying Tactics",
                    "Unlike political parties, Pressure Groups (Nigerian Labour Congress NLC, ASUU, NBA, NMA, Market Associations) do NOT nominate candidates to form government—they influence policies protecting member interests! Anomic groups erupt spontaneously (street protests); Institutional groups sit inside bureaucracy/military; Associational groups run formal unions. Tactics include Lobbying legislators, Strikes/Boycotts, Court Litigation, and Media Campaigns.",
                    "\\text{Tactics}: \\text{Lobbying} \\mid \\text{Collective Bargaining} \\mid \\text{Strikes} \\mid \\text{Litigation}",
                    "Do NOT assume every pressure group is selfish: Promotional / Cause groups (human-rights NGOs, anti-corruption coalitions) campaign for broad public causes!",
                    "Effective pressure groups bridge citizens and busy ministries between 4-year election cycles.",
                    "Classic JAMB distinction: Parties contest to RULE; Pressure Groups lobby to INFLUENCE rulers.",
                    "Pressure groups influence policy via lobbying, strikes, and courts without taking office.",
                  ),
                  govConcept(
                    "5. Public Opinion & Civil Mass Media",
                    "Public Opinion represents the prevailing shared sentiment of citizens on public controversies—measured via Scientific Opinion Polls (random sampling!), Referendums/Plebiscites, Letters/Social Media, and Elections. Propaganda tries to manipulate emotion one-sidedly, while Free Mass Media informs reasoned civic debate.",
                    "\\text{Measurement}: \\text{Random Poll} \\mid \\text{Referendum} \\mid \\text{Electoral Mandate}",
                    "A Referendum (Plebiscite) asks citizens to vote directly YES/NO on a specific constitutional or border question (e.g., 1961 Cameroon/Northern Nigeria plebiscites).",
                    "Credible polling warns leaders early before unpopular taxes trigger mass resistance.",
                    "NECO separates Public Opinion (fluid citizen views) from Election Mandate (formal 4-year ballot verdict).",
                    "Public opinion guides responsive leaders via polls, free media, and referendums.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Analyzing FPTP vs Proportional Seat Outcomes",
                    "JAMB UTME-Style Worked Problem",
                    "In a 100-seat parliament under First-Past-The-Post, Party A scores 42% nationwide winning 62 close constituencies, Party B gets 35% winning 35 seats, and Party C gets 23% winning only 3 seats. Contrast this outcome against Pure Proportional Representation.",
                    listOf(
                      "Step 1 — Under FPTP: Party A gains an absolute legislative majority (62/100 seats) despite only 42% vote share—ensuring stable single-party cabinet!",
                      "Step 2 — Distortion risk: Party C received 23% of national votes yet holds just 3% of seats ('wasted votes').",
                      "Step 3 — Under Pure PR: Seats align strictly with votes (42, 35, 23)—fairer minority representation, though Party A would require a coalition with Party B or C to reach 51.",
                    ),
                    "FPTP favors stable majority (62 seats); PR favors fairness (42-35-23 split + coalition)",
                    "Saying Proportional Representation produces a single-party landslide when votes are split 42/35/23.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "ASUU / NLC Tripartite Wage Negotiations",
                    "Associational Pressure Groups",
                    "Trade unions negotiate minimum-wage adjustments with Federal/State committees using collective bargaining, lobbying, and industrial strike notices rather than fielding presidential candidates.",
                    "Textbook Associational Pressure Group vs Political Party distinction.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Inspect coalition vs single-party stability mechanics across plural vs proportional parliamentary designs.",
              exercises =
                listOf(
                  q("ex_gov_05_1", "Gerrymandering", "JAMB", 2021, "Redrawing electoral constituency maps unfairly to hand an incumbent party extra seats is called:", listOf("Franchise expansion", "Gerrymandering", "Referendum", "Plebiscite"), 1, "Partisan district boundary distortion.", "Gerrymandering distorts fair district representation."),
                  q("ex_gov_05_2", "Party vs Pressure", "WAEC", 2022, "The fundamental difference between a Political Party and a Pressure Group lies in:", listOf("Use of media", "Parties seek to win elections and control government; pressure groups seek solely to influence policies", "Membership numbers", "Legal registration"), 1, "Capturing power vs lobbying rulers.", "Parties field candidates for office; pressure groups lobby."),
                  q("ex_gov_05_3", "Proportional Rep", "JAMB", 2023, "The major strength of Proportional Representation (PR) over Simple Majority (FPTP) is that PR:", listOf("Eliminates political parties", "Reflects minority party vote shares accurately in seat allocation", "Guarantees one-party rule", "Abolishes secret ballots"), 1, "Prevents 'wasted votes' for smaller parties.", "Seats match vote percentages across the electorate."),
                  q("ex_gov_05_4", "Referendum", "JAMB", 2020, "Asking registered citizens directly via ballot to vote YES or NO on a proposed constitutional merger is a:", listOf("Primary election", "Bye-election", "Referendum / Plebiscite", "Shadow cabinet"), 2, "Direct popular verdict on an issue.", "Referendum settles specific national issues by direct citizen vote."),
                  q("ex_gov_05_5", "Pressure Tactics", "WAEC", 2021, "Which classification fits university unions like ASUU or the Nigerian Bar Association (NBA)?", listOf("Anomic street groups", "Associational / Professional Pressure Groups", "Political Parties", "Military juntas"), 1, "Formal voluntary occupational bodies.", "Registered professional unions represent Associational Pressure Groups."),
                  q("ex_gov_05_6", "Nigeria Suffrage", "NECO", 2022, "Secret balloting is essential in democratic elections mainly to:", listOf("Slow down counting", "Protect voters from intimidation, bribery threats, and victimization", "Hide winners permanently", "Exempt parties from manifestos"), 1, "Ensures free conscience at the booth.", "Privacy guarantees free uncoerced choice."),
                ),
              prerequisites = listOf("Federalism & Local Government"),
              dependencySummary = "Electoral participation builds straight from Nigeria's Pre-Colonial & Indirect Rule roots in Lesson 6.",
              vocabulary =
                listOf(
                  VocabularyEntry("Gerrymandering", "Manipulating electoral district borders to concentrate rivals or dilute opposition votes.", "Rigging constituency map shapes.", "Prevented by independent boundary delimitation commissions."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Pressure groups only use violent riots", "Media highlights strikes.", "Most daily pressure-group influence happens quietly through expert lobbying, policy memos, court suits, and tripartite negotiation tables.", "Strikes are last resorts after bargaining stalls."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Explain why a Two-Party System often looks more stable yet less inclusive for diverse ethnic minorities than a Multi-Party Proportional System.",
                  "Standard",
                  3,
                  listOf("Compare clear legislative majorities vs coalition bargaining.", "Compare breadth of ideological/regional voice."),
                  "Weigh governability against minority inclusion.",
                  "Two-party systems produce clear majorities without fragile coalitions, whereas Multi-party PR gives diverse minorities direct seats and voice even if cabinets must govern through coalitions.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Free & Fair Electoral Integrity Checklist",
                  "Observer Mission Brief",
                  "Audit a hypothetical State Governorship poll for INEC independence, secret ballot compliance, and fair boundary delimitation.",
                  listOf("Verify independent Voters' Register & BVAS audit", "Screen districts for Gerrymandering", "Assess tribunal independence"),
                  listOf("Tests Universal Suffrage & Secret Ballot", "Separates Plurality vs Absolute Spread rule", "Protects free media & observer access"),
                  "Strict chain-of-custody collation, independent boundary formulas, and protected polling booths secure credible results.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "FPTP = Most Votes; PR = % Seats; Party = Rule; Pressure Group = Lobby; Gerrymander = Rig Borders.",
                  listOf("FPTP: Winner Takes Seat", "INEC: Election Manager", "NLC/NBA: Associational Groups"),
                  listOf("Saying Pressure Groups nominate Presidential candidates", "Ignoring 25% spread rule in Nigerian Presidential polls"),
                  "18+ Universal Suffrage (1979 Nationwide); Secret Ballot Stops Intimidation.",
                ),
            ),
            Lesson(
              id = "gov_06_precolonial_indirect_rule",
              moduleId = "gov_mod_3",
              subjectId = "government",
              title = "Pre-Colonial Systems (Hausa/Fulani, Yoruba, Igbo) & Indirect Rule",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 9–10 • WAEC Pre-Colonial Admin & Colonial Policies",
              learningOutcomes =
                listOf(
                  "Contrast Centralized Hausa-Fulani Emirates, Semi-Centralized Checks-and-Balances Yoruba Oyo, and Decentralized Acephalous Igbo systems.",
                  "Explain WHY Britain adopted Indirect Rule (Lugard) and why it succeeded in the North, partially in the West, and failed/sparked Aba Women's War 1929 in the East.",
                  "Compare British Indirect Rule vs French Assimilation & Association across West Africa.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Hausa-Fulani Centralized Emirate Administration",
                    "Following the 1804 Sokoto Jihad led by Uthman dan Fodio, Northern Emirates ran a HIGHLY CENTRALIZED THEOCRATIC hierarchy anchored on Sharia Islamic law. Emirs appointed top officials—Waziri (Prime Minister/Head of Administration), Galadima (Capital Administrator), Madawaki (Army Commander), Dogari (Police), Maaji (Treasurer), and Alkali (Sharia Judges)—and collected taxes (Jangali cattle tax & Kharaj land tax).",
                    "\\text{Sokoto Caliphate / Emir} \\rightarrow \\text{Waziri, Galadima, Maaji, Alkali} \\quad (\\text{Highly Centralized})",
                    "Because taxation, courts (Alkali), and hierarchical obedience already existed pre-colonially, Britain's Indirect Rule slotted right atop the Emir seamlessly!",
                    "Clear vertical authority simplified tax collection and administration across vast savanna distances.",
                    "JAMB routinely maps Waziri (Chief Admin), Madawaki (Army), Maaji (Treasurer), and Jangali (cattle tax).",
                    "Hausa-Fulani: Centralized theocratic Emirate + Sharia + organized Jangali/Kharaj taxes.",
                  ),
                  govConcept(
                    "2. Yoruba Pre-Colonial Constitutional Checks (Old Oyo)",
                    "Neither absolute dictatorship nor pure statelessness: the Alaafin ruled Old Oyo alongside a powerful seven-member kingmaker council—the OYO MESI led by the BASHORUN! If the Alaafin grew tyrannical, the Oyo Mesi could present an empty calabash (symbolic rejection requiring the Alaafin's suicide/deposition), while the OGBONI secret society checked the Bashorun himself—a masterclass in pre-colonial Checks & Balances!",
                    "\\text{Alaafin} \\longleftrightarrow \\text{Oyo Mesi (Bashorun)} \\longleftrightarrow \\text{Ogboni Council}",
                    "Note that Are-Ona-Kakanfo commanded Oyo's field army (and historically could not re-enter Oyo defeated!).",
                    "Triangular checks stopped either royal autocracy or kingmaker abuse.",
                    "WAEC compares Oyo's checks (empty calabash) with centralized Emirates where no comparable formal deposing council existed.",
                    "Yoruba Old Oyo: Constitutional Monarchy checking Alaafin via Oyo Mesi & Ogboni.",
                  ),
                  govConcept(
                    "3. Igbo Acephalous / Segmentary Republican System",
                    "Pre-colonial Igboland had NO central king! Power was DECENTRALIZED and egalitarian across Age Grades (enforcement/public works), Council of Elders (Ofo title holders / Amala), Umuada (influential daughters' forum), Ozo titled nobility, and the general Village Assembly (Oha-na-Eze) where adult voices debated consensus directly alongside Oracles (e.g., Arochukwu).",
                    "\\text{Igbo Village Democracy}: \\text{Amala Elders} + \\text{Age Grades} + \\text{Oha-na-Eze Assembly}",
                    "Because no paramount chief existed to issue taxes or orders on Britain's behalf, creating artificial 'Warrant Chiefs' provoked bitter resistance!",
                    "Consensus debate respected individual achievement (Ozo title) rather than hereditary royal blood.",
                    "JAMB keywords for Igbo pre-colonial governance: Acephalous, Egalitarian, Segmentary, Non-Centralized.",
                    "Igbo = Acephalous, Republican, Decentralized consensus (no central monarch).",
                  ),
                  govConcept(
                    "4. British Indirect Rule & the 1929 Aba Women's War",
                    "Lord Frederick Lugard introduced Indirect Rule to govern huge territories cheaply with minimal British staff, respecting existing traditional structures where convenient. It flourished in the North (strong Emir tax/court pyramid), achieved partial success in the West (taxes sparked riots in Iseyin/Abeokuta because Yoruba monarchs were constitutional, not absolute), and COLLAPSED in the East where imposed 'Warrant Chiefs' + direct head-tax fears triggered the historic 1929 Aba Women's War!",
                    "\\text{Indirect Rule}: \\text{North (Success)} \\mid \\text{West (Partial)} \\mid \\text{East (Failed} \\to 1929\\text{ Aba War)}",
                    "Also remember indirect rule excluded Western-educated African elites, fueling modern Nationalist agitation!",
                    "Attempting to transplant centralized Emirate taxation onto egalitarian Igbo assemblies violated local political culture.",
                    "WAEC awards top marks for linking Aba Women's War (1929) to Warrant Chiefs & taxation rumors.",
                    "Indirect Rule saved British funds & staff; succeeded in North, stumbled in West, failed in East.",
                  ),
                  govConcept(
                    "5. British Indirect Rule vs French Assimilation & Association",
                    "While Britain preserved local chiefs under Indirect Rule (leading eventually toward independence), France initially pursued ASSIMILATION—aiming to turn colonial subjects (especially Senegal's Four Communes: Dakar, Saint-Louis, Rufisque, Gorée) into 'Black Frenchmen' under French language, law, and Paris parliament representation—before pivoting to flexible ASSOCIATION as Assimilation proved too expensive and resisted.",
                    "\\text{British Indirect Rule (Chiefs Kept)} \\;\\neq\\; \\text{French Assimilation (Cultural Gallicization + Indigénat)}",
                    "Unassimilated subjects faced the harsh 'Indigénat' summary forced-labour and justice code—whereas Britain used Native Courts!",
                    "French rule suppressed traditional rulers' autonomy sharply compared to Lugard's Native Authorities.",
                    "Standard JAMB contrast: British Indirect Rule vs French Assimilation (Four Communes of Senegal).",
                    "France initially sought Assimilation (Black Frenchmen), later shifting to Association.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Why Indirect Rule Outcomes Varied Across Nigeria's Three Regions",
                    "WAEC WASSCE-Style Worked Problem",
                    "Account systematically for why Lord Lugard's Indirect Rule achieved broad compliance in Northern Nigeria, only partial success in Western Nigeria, and fierce collapse in Eastern Nigeria.",
                    listOf(
                      "Step 1 — North (High Success): Existing centralized Emirate structure, Sharia Alkali courts, and Jangali/Kharaj direct tax traditions matched Indirect Rule directly.",
                      "Step 2 — West (Partial Success): Obas (like Alaafin) held constitutional limits under Oyo Mesi/Ogboni and lacked prior direct-tax customs, sparking Iseyin/Abeokuta unrest.",
                      "Step 3 — East (Collapse): Decentralized acephalous Igbo society had NO kings or direct taxation; appointed Warrant Chiefs lacked legitimacy, culminating in the 1929 Aba Women's War.",
                    ),
                    "Matched North's centralization, strained West's constitutional limits, and clashed with East's egalitarianism",
                    "Claiming 'Southern Nigerians hated Britain more personally' instead of tying results to pre-colonial institutional structure!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Traditional Council Dispute Mediation & Civic Mobilization",
                    "Modern Institutional Heritage",
                    "Today's Council of Traditional Rulers preserves advisory prestige rooted in Northern Emirates and Western Palaces alongside Eastern Town Unions.",
                    "Reflects Nigeria's layered pre-colonial governance heritage.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Compare structural centralization & accountability models across unitary, federal, and tiered governance architectures.",
              exercises =
                listOf(
                  q("ex_gov_06_1", "Hausa-Fulani Officials", "JAMB", 2021, "In pre-colonial Hausa-Fulani Emirate administration, who served as Prime Minister / chief adviser to the Emir?", listOf("Madawaki", "Waziri", "Dogari", "Maaji"), 1, "Highest administrative officer beneath Emir.", "Waziri coordinated administration; Maaji handled treasury; Madawaki led army."),
                  q("ex_gov_06_2", "Old Oyo Checks", "WAEC", 2022, "Which council in the Old Oyo Empire acted as kingmakers empowered to reject a tyrannical Alaafin?", listOf("Igwe cabinet", "Oyo Mesi led by the Bashorun", "Age Grades", "Warrant Chiefs"), 1, "Seven noble chiefs symbolizing rejection via empty calabash.", "Oyo Mesi balanced royal power, itself checked by Ogboni."),
                  q("ex_gov_06_3", "Igbo Pre-Colonial", "JAMB", 2023, "Pre-colonial Igbo political organization is accurately described as:", listOf("Absolute theocratic monarchy", "Acephalous, decentralized, and egalitarian", "Military dictatorship", "Feudal empire"), 1, "Literally 'headless' (no central monarch).", "Decisions emerged through Elders, Age Grades, Ozo & Village Assemblies."),
                  q("ex_gov_06_4", "Indirect Rule Failure", "WAEC", 2021, "The 1929 Aba Women's War erupted mainly against:", listOf("French Indigénat in Dakar", "Imposed Warrant Chiefs and feared direct taxation in Eastern Nigeria", "1976 Local Govt Reform", "Sokoto Caliphate tax"), 1, "Colonial Warrant Chief imposition & tax rumors.", "Rejection of alien Warrant Chiefs and taxation fears ignited 1929 protests."),
                  q("ex_gov_06_5", "French Assimilation", "JAMB", 2020, "Residents of which Senegalese coastal cities originally enjoyed French Assimilation citizenship rights?", listOf("The Four Communes (Dakar, Saint-Louis, Rufisque, Gorée)", "Lagos and Calabar", "Accra and Kumasi", "Abuja and Kano"), 0, "Senegal's historic Four Communes.", "France applied full Assimilation chiefly inside Senegal's Four Communes."),
                  q("ex_gov_06_6", "Educated Elites", "NECO", 2022, "A major political criticism of British Indirect Rule was that it:", listOf("Gave full cabinet seats to Nigerian lawyers immediately", "Excluded educated Westernized African elites from colonial administration", "Taught French in Northern schools", "Abolished all traditional Emirs"), 1, "Alienated university-trained nationalists like Macaulay & Azikiwe.", "Exclusion drove educated elites into organized Nationalism."),
                ),
              prerequisites = listOf("Authority & Decentralization concepts"),
              dependencySummary = "Exclusion of educated elites under Indirect Rule launched Constitutional Nationalism in Module 4.",
              vocabulary =
                listOf(
                  VocabularyEntry("Acephalous", "Literally 'without a head/monarch'—a stateless or decentralized polity governed through councils & assemblies.", "Village democracy without a king.", "Key JAMB descriptor for pre-colonial Igboland."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Alaafin of Oyo was an unchecked absolute dictator", "Mistaking royal reverence for absolutism.", "Oyo Mesi (Bashorun) could force a tyrannical Alaafin to commit suicide via the empty calabash, while Ogboni audited Oyo Mesi!", "Old Oyo ran intricate checks and balances."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Identify three practical reasons why Lord Lugard adopted Indirect Rule in Northern Nigeria in 1900.",
                  "Standard",
                  3,
                  listOf("Consider shortage of European officers & tight colonial budgets.", "Consider language/malaria barriers and ready-made Emirate tax structures."),
                  "State administrative, fiscal, and cultural constraints.",
                  "1. Acute shortage of trained British administrators. 2. Tight colonial treasury funds (Emirates already had self-funding taxes). 3. Poor transport/climate plus existing hierarchical obedience made working through Emirs practical.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Comparative Colonial & Indigenous Governance Matrix",
                  "Historical Policy Comparison",
                  "Chart Executive authority, Taxation, Courts, and Legitimacy across Hausa-Fulani, Old Oyo, Igbo, Indirect Rule, and French Assimilation.",
                  listOf("Compare centralization scores cleanly", "Trace 1929 Aba Women's War causes", "Identify Indigénat vs Native Courts"),
                  listOf("Correct officials matched per polity", "Separates French Assimilation vs Association", "Connects elite exclusion to Nationalism"),
                  "Pre-colonial institutional centralization predicted whether colonial Indirect Rule stabilized governance or triggered immediate resistance.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "North = Emir/Waziri (Centralized); Oyo = Alaafin + Oyo Mesi + Ogboni; East = Acephalous; France = Assimilation → Association.",
                  listOf("Jangali: Cattle Tax", "Oyo Mesi: Kingmakers", "1929: Aba Women's War"),
                  listOf("Calling Old Oyo absolute monarchy", "Confusing British Indirect Rule with French Assimilation"),
                  "Lugard Excluded Educated Elites → Fueled Nationalism.",
                ),
            ),
          ),
      ),
    )
}
