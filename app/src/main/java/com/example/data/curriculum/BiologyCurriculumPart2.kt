package com.example.data.curriculum

object BiologyCurriculumPart2 {

  val module3And4: List<CourseModule> =
    listOf(
      CourseModule(
        id = "bio_mod_3",
        subjectId = "biology",
        indexLabel = "Module 03",
        title = "Nervous Coordination, Sense Organs, Hormones & Reproduction",
        summary = "Master Neurons, Reflex Arcs, Eye Accommodation & Defects, Ear Balance, Endocrine Glands, Plant Tropisms & Floral/Human Reproduction.",
        lessons =
          listOf(
            Lesson(
              id = "bio_07_nervous_eye_ear",
              moduleId = "bio_mod_3",
              subjectId = "biology",
              title = "Nervous System, Reflex Arc, Eye & Ear Mechanics",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. B7 • WAEC Nervous Coordination & Sense Organs",
              learningOutcomes =
                listOf(
                  "Trace a Reflex Arc (Receptor → Sensory → Relay → Motor → Effector) and Synaptic Neurotransmitter relay.",
                  "Identify Forebrain (Cerebrum/Hypothalamus), Midbrain & Hindbrain (Cerebellum = Balance; Medulla = Breathing).",
                  "Correct Short-Sightedness (Myopia → Concave lens) vs Long-Sightedness (Hypermetropia → Convex lens) and trace Ear hearing.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Brain Zones & The Instant Reflex Shortcut",
                    explanation =
                      "Touch a hot pot and your hand jerks away BEFORE you even feel 'Ouch!'—because the signal takes a Spinal Cord Reflex shortcut (Receptor → Sensory Neuron → Relay Neuron → Motor Neuron → Muscle Effector) instead of waiting for the Brain! Inside the Brain: Cerebrum handles thinking/memory; Cerebellum keeps balance & smooth walking; Medulla Oblongata keeps heartbeat & breathing running even while you sleep!",
                    keyFormula = "\\text{Stimulus} \\to \\text{Receptor} \\to \\text{Sensory} \\to \\text{Relay (Spinal)} \\to \\text{Motor} \\to \\text{Effector}",
                    examTip =
                      "At a Synapse gap, electrical impulses switch to CHEMICAL neurotransmitters (e.g., Acetylcholine), guaranteeing signals travel ONE WAY ONLY!",
                  ),
                  ConceptBlock(
                    heading = "2. Focusing the Eye & Fixing Blurry Vision + Hearing",
                    explanation =
                      "Look at a nearby book: Circular Ciliary muscles CONTRACT → Suspensory ligaments go SLACK → Elastic Lens bulges FATTER to bend rays sharply! Look far away: Ciliary relaxes → Ligaments pull tight → Lens flattens thin! Eye defects: Long eyeball or over-strong lens focuses light IN FRONT of Retina = MYOPIA (fix with diverging CONCAVE lens!). Too-short eyeball focuses BEHIND Retina = HYPERMETROPIA (fix with converging CONVEX lens!). Inside the Ear: Pinna → Eardrum → 3 Ossicles (Hammer-Anvil-Stirrup amplify 20×!) → Oval Window → Cochlea (Hearing!), while Semicircular Canals sense Balance!",
                    keyFormula = "\\text{Myopia (Near OK, Far Blurry)} \\to \\text{CONCAVE}   |   \\text{Hypermetropia (Far OK, Near Blurry)} \\to \\text{CONVEX}",
                    examTip =
                      "Rods see dim black-and-white (Vitamin A rhodopsin!); Cones concentrated in the Fovea see sharp daylight colour!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Diagnosing & Correcting Myopia vs Presbyopia",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A student reads her textbook clearly without glasses but cannot read notes written on the whiteboard 6 m away. Name the defect, optical cause, and lens.",
                    steps =
                      listOf(
                        "Step 1 — Near clear, far blurry = Short-sightedness (Myopia).",
                        "Step 2 — Eyeball is too elongated horizontally (or lens too curved), converging parallel distant rays IN FRONT OF the retina.",
                        "Step 3 — Diverging CONCAVE (biconcave) spectacles spread light slightly so it lands sharply ON the retina.",
                      ),
                    finalAnswer = "Myopia • Concave (diverging) lens",
                    examinerTrap = "Don't confuse Astigmatism (uneven cornea corrected with CYLINDRICAL lens) with Myopia!",
                  ),
                  WorkedExample(
                    title = "Tracing Sound Amplification Through the Middle Ear",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Name the correct sequence of vibrations from Tympanum (eardrum) to Auditory Nerve.",
                    steps =
                      listOf(
                        "Step 1 — Eardrum → Malleus (Hammer) → Incus (Anvil) → Stapes (Stirrup).",
                        "Step 2 — Stapes taps the Oval Window → Endolymph/Perilymph fluid in COCHLEA ripples → Organ of Corti hair cells fire Auditory Nerve.",
                      ),
                    finalAnswer = "Tympanum → Malleus → Incus → Stapes → Oval Window → Cochlea",
                    examinerTrap = "Eustachian tube equalizes air pressure across the eardrum—it does NOT carry sound to the brain!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Eardrum Popping on Airplanes & Spinal Reflex Testing",
                    industryDomain = "Neurology & Aviation Medicine",
                    narrative =
                      "Yawning during airplane descent opens the Eustachian tube linking the throat to the middle ear, equalizing cabin pressure safely across the eardrum.",
                    examConnection = "Tapping the patellar knee tendon tests an intact monosynaptic spinal reflex arc.",
                  ),
                ),
              labType = PracticalLabType.NERVOUS_ENDOCRINE_LAB,
              labBrief =
                "Switch between Normal, Myopic, and Hypermetropic eyeballs and slip Concave vs Convex spectacle lenses in front to see rays snap onto the Retina!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_07_1",
                    topic = "Brain Functions",
                    examType = "JAMB",
                    year = 2024,
                    question = "Damage to the Cerebellum most directly impairs a person's:",
                    options = listOf("Speech memory", "Posture, muscular balance & coordination", "Heartbeat rate", "Colour vision"),
                    correctIndex = 1,
                    hint = "Hint: Alcohol suppresses cerebellum → staggering gait!",
                    stepByStepSolution = "The cerebellum coordinates fine motor balance and posture.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_2",
                    topic = "Eye Accommodation",
                    examType = "WAEC",
                    year = 2023,
                    question = "When focusing on a close book, what happens to the ciliary muscles and eye lens?",
                    options = listOf("Ciliary relaxes; lens flattens", "Ciliary contracts; suspensory ligaments slacken; lens gets thicker/more convex", "Pupil dilates widely", "Optic nerve stretches"),
                    correctIndex = 1,
                    hint = "Close objects need maximum bending power (thick convex lens).",
                    stepByStepSolution = "Contracting the circular ciliary ring slackens ligament tension so the elastic lens bulges.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_3",
                    topic = "Eye Defects",
                    examType = "JAMB",
                    year = 2025,
                    question = "Image formation IN FRONT of the retina in a short-sighted eye is corrected using:",
                    options = listOf("Convex lens", "Concave lens", "Cylindrical lens", "Bifocal mirror"),
                    correctIndex = 1,
                    hint = "Need to diverge incoming rays slightly before hitting the cornea.",
                    stepByStepSolution = "Concave diverging lenses push the focal point backward onto the retina.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_4",
                    topic = "Ear Equilibrium",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which structures inside the inner ear detect rotational head balance rather than sound?",
                    options = listOf("Ossicles", "Cochlea", "Semicircular canals", "Eustachian tube"),
                    correctIndex = 2,
                    hint = "Three mutually perpendicular fluid loops.",
                    stepByStepSolution = "Semicircular canals + utricle/saccule maintain dynamic & static equilibrium.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_5",
                    topic = "Synaptic Transmission",
                    examType = "JAMB",
                    year = 2021,
                    question = "How does a nerve impulse cross the microscopic synaptic cleft between two neurons?",
                    options = listOf("Direct spark jump", "Release of chemical neurotransmitters like acetylcholine", "Blood plasma flow", "Cilia beating"),
                    correctIndex = 1,
                    hint = "Vesicles fuse at presynaptic knob.",
                    stepByStepSolution = "Chemical neurotransmitters diffuse across the cleft to bind postsynaptic receptors.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_6",
                    topic = "Sensory Receptors",
                    examType = "NECO",
                    year = 2024,
                    question = "Which vitamin is essential to synthesize Rhodopsin pigment in retinal rod cells for dim-light vision?",
                    options = listOf("Vitamin D", "Vitamin A (Retinol)", "Vitamin C", "Vitamin B12"),
                    correctIndex = 1,
                    hint = "Carrots/palm oil beta-carotene prevents night blindness.",
                    stepByStepSolution = "Vitamin A forms retinal, the light-absorbing chromophore of rhodopsin.",
                  ),
                ),
            ),

            Lesson(
              id = "bio_08_hormones_skeleton_tropisms",
              moduleId = "bio_mod_3",
              subjectId = "biology",
              title = "Endocrine Hormones, Plant Tropisms & Supporting Skeletons",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. B7–B8 • WAEC Hormonal Coordination, Tropisms & Skeleton",
              learningOutcomes =
                listOf(
                  "Contrast rapid wired Nervous control against slower blood-borne Hormonal control.",
                  "Identify Master Pituitary, Thyroxine (Iodine!), Adrenaline ('Fight/Flight'), and Pancreatic Insulin/Glucagon.",
                  "Explain Auxin Phototropism & Geotropism in plants, plus Hinge vs Ball-and-Socket Synovial joints.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Endocrine Hormones vs Nerve Signals + Blood Sugar Duel",
                    explanation =
                      "Nerves act like an instant fibre-optic phone call; Hormones act like letters mailed via the bloodstream—slower, longer-lasting, affecting distant organs! Classic pancreas seesaw: Eat a heavy carbohydrate lunch and Blood Glucose spikes → Islets of Langerhans release INSULIN telling the liver to pack sugar away as stored GLYCOGEN! Skip meals? Pancreas releases GLUCAGON breaking glycogen back into Glucose!",
                    keyFormula = "\\text{High Sugar} \\xrightarrow{\\text{Insulin}} \\text{Glycogen Stored}   |   \\text{Low Sugar} \\xrightarrow{\\text{Glucagon}} \\text{Glucose Released}",
                    examTip =
                      "Auxin plant hormone trick: Light destroys/pushes Auxin onto the SHADED side of a stem! In SHOOTS, extra Auxin speeds cell elongation so the stem bends TOWARD light! Wait—in ROOTS, heavy Auxin INHIBITS growth so bottom root cells lag and roots bend DOWNWARD (positive geotropism)!",
                  ),
                  ConceptBlock(
                    heading = "2. Plant Growth Hormones, Skeletons & Antagonistic Muscles",
                    explanation =
                      "Plants also use Gibberellins (bolt stems & break dormancy), Cytokinins (cell division), Ethene (ripen fruits!), and Abscisic Acid (dormancy/leaf drop!). In animals, Axial Skeleton (Skull, Backbone, Ribs) protects soft organs; Appendicular Skeleton (Limbs + Pelvic/Pectoral girdles) moves you around! Muscles only PULL (never push!), so they work in pairs like Biceps (flexor) & Triceps (extensor) across Cartilage-padded Synovial joints!",
                    keyFormula = "\\text{Ball \\& Socket (Shoulder/Hip: 360}^\\circ\\text{)}   |   \\text{Hinge (Elbow/Knee: 1 plane)}   |   \\text{Atlas (Yes nod) / Axis (No turn)}",
                    examTip =
                      "Tendons tie Muscle → Bone (tough non-elastic!); Ligaments tie Bone → Bone (slightly elastic to hold joints stable!)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Clinostat Auxin Geotropism Experiment",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "Two germinating bean seedlings lie horizontally—one stationary, one pinned on a slowly rotating electric clinostat. Compare growth after 48 hours.",
                    steps =
                      listOf(
                        "Step 1 — Stationary: gravity pools Auxin on lower side → shoot curves UP (neg. geotropism) while root curves DOWN (pos. geotropism).",
                        "Step 2 — Rotating clinostat cancels one-sided gravity pull (equal Auxin all around) → both shoot and root grow STRAIGHT horizontally!",
                      ),
                    finalAnswer = "Stationary curves; Rotating Clinostat grows straight horizontally",
                    examinerTrap = "Don't forget opposite effects of high Auxin on Shoots (promotes!) vs Roots (inhibits!)!",
                  ),
                  WorkedExample(
                    title = "Vertebral Anatomy: Atlas vs Axis vs Thoracic",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Match Cervical Atlas, Cervical Axis, and Thoracic vertebrae to their standout structural features.",
                    steps =
                      listOf(
                        "Step 1 — Atlas (C1): Huge neural canal, NO centrum, broad wing transverse processes (nods 'yes').",
                        "Step 2 — Axis (C2): Upright peg-like Odontoid Process locking into Atlas (turns head 'no').",
                        "Step 3 — Thoracic: Very LONG backward-slanting neural spine + rib articulation facets.",
                      ),
                    finalAnswer = "Atlas = No Centrum  |  Axis = Odontoid Peg  |  Thoracic = Long Neural Spine",
                    examinerTrap = "Sacral vertebrae fuse into a wedge (Sacrum) supporting the pelvic hip girdle!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Synthetic Auxin Herbicides & Adrenaline EpiPens",
                    industryDomain = "Agronomy & Emergency Care",
                    narrative =
                      "Farmers spray synthetic auxin (2,4-D) to make broad-leaved weeds outgrow their food supply and die without harming narrow-leaved maize, while EpiPens inject adrenaline to reverse lethal allergic shock in seconds.",
                    examConnection = "Shows both physiological fight-or-flight and commercial hormone uses.",
                  ),
                ),
              labType = PracticalLabType.NERVOUS_ENDOCRINE_LAB,
              labBrief =
                "Adjust Blood Glucose & Adrenaline sliders to trigger Insulin/Glucagon liver feedback or tilt a virtual Seedling to watch Auxin bend shoots vs roots!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_08_1",
                    topic = "Blood Sugar Homeostasis",
                    examType = "JAMB",
                    year = 2024,
                    question = "After a high-carbohydrate meal, which hormone stimulates liver and muscle cells to absorb glucose and store glycogen?",
                    options = listOf("Glucagon", "Adrenaline", "Insulin", "Thyroxine"),
                    correctIndex = 2,
                    hint = "Secreted by beta cells of the Pancreas.",
                    stepByStepSolution = "Insulin lowers high blood glucose back to normal (~90 mg/100 mL).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_2",
                    topic = "Plant Tropisms",
                    examType = "WAEC",
                    year = 2023,
                    question = "A potted coleoptile illuminated only from the right bends toward the light because Auxin accumulates on the:",
                    options = listOf("Illuminated side, stopping growth", "Shaded left side, stimulating faster cell elongation there", "Shoot tip symmetrically", "Root cap only"),
                    correctIndex = 1,
                    hint = "Longer cells on the left push the stem bending right toward light.",
                    stepByStepSolution = "Lateral Auxin migration to the shaded side promotes differential elongation.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_3",
                    topic = "Tendons vs Ligaments",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which tough fibrous tissue attaches skeletal Muscle directly onto Bone?",
                    options = listOf("Ligament", "Cartilage", "Tendon", "Synovial membrane"),
                    correctIndex = 2,
                    hint = "Achilles tendon connects calf muscle to heel bone.",
                    stepByStepSolution = "Tendons connect muscle to bone; Ligaments link bone to bone.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_4",
                    topic = "Thyroid Deficiency",
                    examType = "WAEC",
                    year = 2022,
                    question = "Iodine deficiency in childhood impairs Thyroxine production, leading to stunted physical & mental growth known as:",
                    options = listOf("Gigantism", "Cretinism", "Kwashiorkor", "Diabetes insipidus"),
                    correctIndex = 1,
                    hint = "In adults, lack of iodine swells the thyroid gland (Goitre).",
                    stepByStepSolution = "Congenital hypothyroidism causes Cretinism.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_5",
                    topic = "Synovial Joints",
                    examType = "JAMB",
                    year = 2021,
                    question = "The shoulder and hip joints permit movement in all planes because they are:",
                    options = listOf("Hinge joints", "Pivot joints", "Ball-and-socket joints", "Gliding joints"),
                    correctIndex = 2,
                    hint = "Round humeral/femoral head rotates inside cup socket.",
                    stepByStepSolution = "Ball-and-socket joints allow multi-axial rotation.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_6",
                    topic = "Nastic vs Tropic",
                    examType = "NECO",
                    year = 2024,
                    question = "The rapid folding of Mimosa pudica ('touch-me-not') leaflets when brushed is an example of:",
                    options = listOf("Phototropism", "Chemotaxis", "Thigmonasty (non-directional turgor response)", "Geotropism"),
                    correctIndex = 2,
                    hint = "Nastic responses don't depend on which direction the touch came from!",
                    stepByStepSolution = "Turgor-driven folding independent of stimulus direction is a Nastic movement.",
                  ),
                ),
            ),

            Lesson(
              id = "bio_09_reproduction_plants_humans",
              moduleId = "bio_mod_3",
              subjectId = "biology",
              title = "Plant & Human Reproduction, Placenta & Menstrual Cycle",
              durationMinutes = 23,
              syllabusTag = "JAMB Sec. B9 • WAEC Reproduction & Development",
              learningOutcomes =
                listOf(
                  "Compare Wind vs Insect flowers and explain Plant Double Fertilization (Zygote 2n + Endosperm 3n!).",
                  "Classify Fruit Dispersal (Wind parachute/wing, Explosive pods, Water buoyant husk, Animal hooks/flesh).",
                  "Track FSH, Estrogen, LH (Day-14 Ovulation surge!) and Progesterone across the 28-day cycle & Placental exchange.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Flower Structure, Double Fertilization & Seeds",
                    explanation =
                      "Stamen (Male) = Anther making Pollen + Filament. Pistil/Carpel (Female) = sticky Stigma + Style + Ovary holding Ovules! After pollination, the pollen tube shoots 2 sperm nuclei down into the ovule for Plant DOUBLE FERTILIZATION: Sperm #1 fuses with Egg → 2n Embryo; Sperm #2 fuses with 2 Polar Nuclei → 3n Endosperm food bank! Afterwards: Ovule becomes SEED, Ovary swells into FRUIT!",
                    keyFormula = "\\text{Epigeal (Bean: cotyledons lifted above soil)}   |   \\text{Hypogeal (Maize: cotyledon stays underground)}",
                    examTip =
                      "Top 3 things EVERY seed strictly needs to germinate: (1) Water, (2) Oxygen, (3) Warmth! Notice LIGHT is NOT required by most germinating seeds underground!",
                  ),
                  ConceptBlock(
                    heading = "2. Human 28-Day Menstrual Hormones & The Placenta",
                    explanation =
                      "Days 1–5: Lining sheds (menstruation). Pituitary FSH ripens an ovarian follicle, which pumps out ESTROGEN to rebuild the thick uterus wall! On Day 14, a dramatic spike in LH triggers OVULATION! Empty follicle turns into yellow Corpus Luteum secreting PROGESTERONE to lock the lush lining steady—if pregnancy occurs, Progesterone stays high; if not, Corpus Luteum shrinks, Progesterone drops, and shedding restarts! Inside pregnancy, the Placenta lets O₂, Glucose & Antibodies pass mom→baby and Urea/CO₂ pass baby→mom WITHOUT mixing blood!",
                    keyFormula = "\\text{Day 14: LH Surge} \\implies \\text{Ovulation}   |   \\text{Progesterone} = \\text{Maintains Uterine Lining}",
                    examTip =
                      "Remember Male Gametes (Meiosis): 1 spermatocyte makes 4 sperm; Female: 1 oocyte makes 1 big egg + 3 tiny polar bodies!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Identifying Wind vs Insect Pollinated Flowers",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "A flower has dull small greenish petals, zero nectar, huge feathery stigmas hanging outside, and smooth powdery pollen. Classify its pollination.",
                    steps =
                      listOf(
                        "Step 1 — No bright colours or scent means insects ignore it.",
                        "Step 2 — Exposed feathery stigmas act like nets catching light dry wind-blown pollen (like Maize/Grasses).",
                      ),
                    finalAnswer = "Wind-pollinated (Anemophilous)",
                    examinerTrap = "Insect pollen is heavy, sticky/spiny; wind pollen is smooth, light & abundant!",
                  ),
                  WorkedExample(
                    title = "Distinguishing Epigeal vs Hypogeal Germination",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Why do Cowpea cotyledons emerge above ground while Maize scutellum stays buried?",
                    steps =
                      listOf(
                        "Step 1 — In Cowpea (dicot), the HYPOCOTYL elongates rapidly, arching and pulling cotyledons above soil to turn green (Epigeal).",
                        "Step 2 — In Maize (monocot), the EPICOTYL elongates pushing only the plumule up while the food seed remains underground (Hypogeal).",
                      ),
                    finalAnswer = "Hypocotyl elongation (Epigeal) vs Epicotyl elongation (Hypogeal)",
                    examinerTrap = "Don't swap Hypo-cotyl (below cotyledon) and Epi-cotyl (above cotyledon)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Cross-Pollination Hybrid Vigor & Placental Rh Compatibility",
                    industryDomain = "Agronomy & Obstetrics",
                    narrative =
                      "Farmers cover maize tassels or remove anthers (emasculation) to enforce cross-pollination for high-yield hybrid seeds, while obstetrics monitors maternal antibodies crossing the placenta.",
                    examConnection = "Protandry (anthers ripen first) and Protogyny (stigma ripens first) prevent self-pollination naturally.",
                  ),
                ),
              labType = PracticalLabType.REPRODUCTION_FLOWER_LAB,
              labBrief =
                "Slide Day 1–28 across the Menstrual Cycle to watch FSH, LH Day-14 peak, Estrogen & Progesterone waves alongside uterine thickness!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_09_1",
                    topic = "Menstrual Ovulation",
                    examType = "JAMB",
                    year = 2024,
                    question = "A sharp spike in which pituitary hormone directly triggers ovulation around Day 14?",
                    options = listOf("Progesterone", "Luteinizing Hormone (LH)", "Oxytocin", "Prolactin"),
                    correctIndex = 1,
                    hint = "Stands for Luteinizing Hormone—turns the burst follicle into Corpus Luteum.",
                    stepByStepSolution = "High estrogen triggers positive feedback causing the mid-cycle LH surge.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_2",
                    topic = "Fruit vs Seed",
                    examType = "WAEC",
                    year = 2023,
                    question = "Following successful fertilization in a flowering plant, the Ovule and Ovary develop respectively into:",
                    options = listOf("Fruit and Seed", "Seed and Fruit", "Endosperm and Pericarp", "Testa and Plumule"),
                    correctIndex = 1,
                    hint = "Ovule inside Ovary → Seed inside Fruit!",
                    stepByStepSolution = "Fertilized ovule forms seed; ripened ovary wall forms fruit pericarp.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_3",
                    topic = "Seed Germination Test",
                    examType = "JAMB",
                    year = 2025,
                    question = "In the 4-tube Pyrogallol germination experiment, seeds in the tube containing Alkaline Pyrogallol fail to sprout because pyrogallol absorbs:",
                    options = listOf("Carbon dioxide", "Moisture", "Oxygen", "Nitrogen"),
                    correctIndex = 2,
                    hint = "Seeds need aerobic respiration to mobilize stored starch!",
                    stepByStepSolution = "Alkaline pyrogallol removes O₂, proving oxygen is essential for germination.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_4",
                    topic = "Plant Double Fertilization",
                    examType = "WAEC",
                    year = 2022,
                    question = "Fusion of the second male nucleus with two diploid/haploid polar nuclei creates which nutritive tissue?",
                    options = listOf("Zygote (2n)", "Triploid Endosperm (3n)", "Testa", "Micropyle"),
                    correctIndex = 1,
                    hint = "n + n + n = 3n food tissue for the embryo.",
                    stepByStepSolution = "Triple fusion forms the 3n primary endosperm nucleus.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_5",
                    topic = "Birth Control & Pregnancy",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which hormone stops menstruation and maintains the thick uterine wall throughout pregnancy?",
                    options = listOf("FSH", "Progesterone", "Adrenaline", "Glucagon"),
                    correctIndex = 1,
                    hint = "Pro-gestation hormone!",
                    stepByStepSolution = "Progesterone from Corpus Luteum then Placenta maintains the endometrium.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_6",
                    topic = "Fruit Dispersal",
                    examType = "NECO",
                    year = 2024,
                    question = "Tridax (coat buttons) and Dandelion fruits possess silky pappus hairs specifically adapted for:",
                    options = listOf("Animal hook dispersal", "Explosive dehiscence", "Wind dispersal", "Water flotation"),
                    correctIndex = 2,
                    hint = "Tiny parachutes catch light breezes.",
                    stepByStepSolution = "Pappus parachutes keep light fruits airborne over long distances.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "bio_mod_4",
        subjectId = "biology",
        indexLabel = "Module 04",
        title = "Genetics, DNA Replication, Variation & Evolution",
        summary = "Master Mendelian Punnett squares, Incomplete Dominance, ABO Blood & Sex-Linked Haemophilia/Sickle-Cell crosses, DNA→Protein & Darwin Evolution.",
        lessons =
          listOf(
            Lesson(
              id = "bio_10_mendelian_genetics",
              moduleId = "bio_mod_4",
              subjectId = "biology",
              title = "Mendelian Genetics, Sickle-Cell, Blood Groups & Sex-Linked Traits",
              durationMinutes = 23,
              syllabusTag = "JAMB Sec. C1–C2 • WAEC Heredity & Genetics",
              learningOutcomes =
                listOf(
                  "Solve Monohybrid (3:1), Dihybrid (9:3:3:1), Test Cross (1:1), and Incomplete Dominance (1:2:1) crosses.",
                  "Predict Blood Group O/A/B/AB children and Sickle-Cell (AA / AS / SS) chances accurately.",
                  "Solve X-linked Haemophilia & Colour-Blindness pedigree crosses.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. Dominant, Recessive, Co-Dominant & Test Crosses",
                    explanation =
                      "Genes come in pairs called Alleles—one from Mom, one from Dad! A Dominant allele (T) masks a Recessive one (t) whenever paired together (Tt still looks Tall!). Crossing two hybrids Tt × Tt always produces 3 Tall : 1 Short phenotype (1 TT : 2 Tt : 1 tt)! How do you check whether a tall pea plant is pure TT or hybrid Tt? Do a TEST CROSS against a pure recessive short tt—if ANY short offspring appear, the tall parent MUST be hybrid Tt!",
                    keyFormula = "\\text{Monohybrid } F_2 = 3:1   |   \\text{Dihybrid } F_2 = 9:3:3:1   |   \\text{AS} \\times \\text{AS} \\implies 25\\%\\text{ AA}:50\\%\\text{ AS}:25\\%\\text{ SS}",
                    examTip =
                      "ABO Blood Group trick: I^A and I^B are CO-DOMINANT (both show in AB!), while I^O is recessive! An A parent (I^A I^O) + B parent (I^B I^O) can have babies of ALL 4 types: A, B, AB, and O!",
                  ),
                  ConceptBlock(
                    heading = "2. Sex-Linked Diseases & Sickle-Cell Trait",
                    explanation =
                      "Boys are XY while Girls are XX! Because the tiny Y chromosome lacks matching backup genes for traits on X, a boy who inherits just ONE broken X^h allele from his carrier mom (X^H X^h) immediately gets Haemophilia or Red-Green Colour Blindness—whereas a girl needs TWO broken copies (X^h X^h)! Sickle-cell (Hb^A Hb^S) heterozygote carriers resist severe malaria (Heterozygote Advantage)!",
                    keyFormula = "\\text{Carrier Mom } (X^H X^h) \\times \\text{Normal Dad } (X^H Y) \\implies 50\\%\\text{ of Sons Affected } (25\\%\\text{ all kids})",
                    examTip =
                      "Father NEVER passes an X-linked trait to his son (he gives Y to sons!)—fathers pass X-linked genes ONLY to daughters!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Sickle-Cell Genetic Counselling (AS × AS)",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "Both parents are healthy Sickle-Cell carriers (Hb^A Hb^S). Using a cross, state the probability of (a) an SS child and (b) a normal AA child.",
                    steps =
                      listOf(
                        "Step 1 — Gametes from each parent: Hb^A and Hb^S.",
                        "Step 2 — Punnett grid yields: AA, AS, AS, SS.",
                        "Step 3 — P(SS) = 1/4 = 25%; P(AA) = 1/4 = 25% (50% healthy carriers AS).",
                      ),
                    finalAnswer = "25% SS  |  25% AA  |  50% AS",
                    examinerTrap = "Previous children's results do NOT alter odds for the next baby—every pregnancy independently stays 25% SS!",
                  ),
                  WorkedExample(
                    title = "Blood Group Cross I^A I^O × I^B I^O",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "Can a Blood Group A father and Blood Group B mother legitimately have a Blood Group O baby? Prove it.",
                    steps =
                      listOf(
                        "Step 1 — Father heterozygous: I^A i; Mother heterozygous: I^B i.",
                        "Step 2 — Offspring combos: I^A I^B (AB), I^A i (A), I^B i (B), and ii (Group O = 25%!).",
                      ),
                    finalAnswer = "Yes! 25% chance if both parents carry recessive i (I^O)",
                    examinerTrap = "Forgetting heterozygous genotypes I^A i and I^B i tricks many students into saying 'Impossible!'",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Premarital AA/AS Genotype Screening & Forensic Paternity",
                    industryDomain = "Preventive Medicine",
                    narrative =
                      "Genetic screening before marriage prevents painful SS sickle-cell crises (since AS × AA produces 0% SS children!).",
                    examConnection = "Explain why Sickle-Cell gene persists in tropical Africa despite lethality: AS heterozygotes survive Plasmodium falciparum malaria better!",
                  ),
                ),
              labType = PracticalLabType.MENDELIAN_GENETICS,
              labBrief =
                "Pick Parent 1 & Parent 2 genotypes (AA/AS/SS, ABO Blood Groups, or X-linked Carrier) to auto-compute the live Punnett Square & percentages!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_10_1",
                    topic = "Test Cross",
                    examType = "JAMB",
                    year = 2024,
                    question = "Crossing a tall pea plant with a dwarf (tt) plant gives half tall and half dwarf offspring. The tall parent's genotype is:",
                    options = listOf("TT", "Tt", "tt", "TTTT"),
                    correctIndex = 1,
                    hint = "1:1 phenotypic ratio from a test cross proves heterozygosity.",
                    stepByStepSolution = "Tt × tt → 50% Tt (Tall) and 50% tt (Dwarf).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_2",
                    topic = "Sex-Linked Colour Blindness",
                    examType = "WAEC",
                    year = 2023,
                    question = "A colour-blind father marries a homozygous normal mother (X^C X^C). What fraction of their SONS will be colour-blind?",
                    options = listOf("0%", "25%", "50%", "100%"),
                    correctIndex = 0,
                    hint = "Sons get their single X ONLY from Mom!",
                    stepByStepSolution = "All sons get X^C from Mom and Y from Dad → 0% sons affected (all daughters are carriers X^C X^c).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_3",
                    topic = "Incomplete Dominance",
                    examType = "JAMB",
                    year = 2025,
                    question = "Crossing red (RR) and white (WW) four-o'clock flowers produces all pink (RW) plants. Crossing two pink plants yields what ratio?",
                    options = listOf("3 Red : 1 White", "1 Red : 2 Pink : 1 White", "All Pink", "2 Red : 1 White"),
                    correctIndex = 1,
                    hint = "In incomplete dominance, phenotype ratio equals genotype ratio 1:2:1.",
                    stepByStepSolution = "RW × RW → 1 RR (Red) : 2 RW (Pink) : 1 WW (White).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_4",
                    topic = "Dihybrid Independent Assortment",
                    examType = "WAEC",
                    year = 2022,
                    question = "How many distinct phenotypes appear in the F₂ generation of Mendel's classical dihybrid cross RrYy × RrYy?",
                    options = listOf("2", "3", "4", "9"),
                    correctIndex = 2,
                    hint = "9:3:3:1 ratio counts 4 observable combinations.",
                    stepByStepSolution = "Four phenotypes appear in 9:3:3:1 ratio.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_5",
                    topic = "Mitosis vs Meiosis",
                    examType = "JAMB",
                    year = 2021,
                    question = "Crossing-over exchange of genetic segments between homologous chromatids occurs during:",
                    options = listOf("Mitotic anaphase", "Meiotic Prophase I", "Interphase G1", "Telophase II"),
                    correctIndex = 1,
                    hint = "Chiasmata form during synapsis in Prophase I.",
                    stepByStepSolution = "Prophase I crossing-over generates genetic recombination.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_6",
                    topic = "Sex Determination",
                    examType = "NECO",
                    year = 2024,
                    question = "In humans, the biological sex of a baby is determined at fertilization by:",
                    options = listOf("Mother's ovum (X vs Y)", "Father's sperm carrying either X or Y", "Age of uterus", "Hormone level"),
                    correctIndex = 1,
                    hint = "Eggs carry only X; half of sperm carry X, half carry Y.",
                    stepByStepSolution = "Sperm X + Egg X = XX girl; Sperm Y + Egg X = XY boy.",
                  ),
                ),
            ),

            Lesson(
              id = "bio_11_dna_variation_evolution",
              moduleId = "bio_mod_4",
              subjectId = "biology",
              title = "DNA Code, Variation Types & Theories of Evolution",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. C3–C4 • WAEC Nucleic Acids, Variation & Evolution",
              learningOutcomes =
                listOf(
                  "Apply DNA base-pairing (A=T, G≡C) vs RNA (A=U) and Central Dogma (DNA → mRNA → Protein).",
                  "Contrast Morphological Continuous Variation (Height, Weight) vs Discontinuous Variation (Blood Group, Fingerprint).",
                  "Compare Lamarck ('Acquired Use/Disuse'—disproved!) vs Darwin ('Natural Selection') + Fossils & Vestigial Evidences.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "1. DNA Base-Pairing Twins & Making Proteins",
                    explanation =
                      "Think of DNA as a twisted double ladder whose rungs ONLY snap together in exact buddy pairs: Adenine (A) always locks with Thymine (T), and Guanine (G) always locks with Cytosine (C)! (When DNA copies a temporary mRNA messenger inside the nucleus, RNA uses Uracil U instead of T!). Three letters on mRNA (a Codon) call one specific amino acid onto the Ribosome to build proteins!",
                    keyFormula = "\\text{DNA: A} \\leftrightarrow \\text{T},\\; \\text{G} \\leftrightarrow \\text{C}   |   \\text{DNA} \\xrightarrow{\\text{Transcription}} \\text{mRNA} \\xrightarrow{\\text{Translation}} \\text{Protein}",
                    examTip =
                      "Chargaff shortcut: if DNA has 20% Adenine, then Thymine is ALSO 20% (40% together), leaving 60% split equally → 30% Guanine and 30% Cytosine!",
                  ),
                  ConceptBlock(
                    heading = "2. Variation & Darwin vs Lamarck Evolution",
                    explanation =
                      "Continuous variation (Height, Skin tone, Weight) spreads smoothly on a bell curve controlled by MANY genes + environment! Discontinuous variation (ABO Blood group, Tongue rolling, Fingerprint Whorl/Loop/Arch) puts everyone into neat separate buckets controlled by few genes! Darwin explained evolution cleanly: organisms overproduce → face scarce food/predators → beneficial mutant variations SURVIVE and pass helpful genes on (Natural Selection)! Lamarck wrongly claimed stretching a giraffe's neck stretched its babies' necks too!",
                    keyFormula = "\\text{Evidence of Evolution: Fossils + Homologous Pentadactyl Limbs + Vestigial Organs (Appendix/Coccyx) + Embryology}",
                    examTip =
                      "Analogous structures (Bird wing vs Butterfly wing—different structure, same job) show Convergent evolution; Homologous structures (Human arm vs Whale flipper vs Bat wing—same bones!) prove Common Ancestry!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Transcribing a DNA Strand to mRNA",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "A DNA template strand reads 3'–TAC GGA CTT–5'. Write the complementary mRNA strand.",
                    steps =
                      listOf(
                        "Step 1 — Match A→U (RNA has no Thymine!), T→A, G→C, C→G.",
                        "Step 2 — TAC → AUG; GGA → CCU; CTT → GAA.",
                      ),
                    finalAnswer = "5'–AUG CCU GAA–3'",
                    examinerTrap = "Writing T instead of U inside an mRNA sequence!",
                  ),
                  WorkedExample(
                    title = "Pepper Moth Industrial Melanism",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Explain why dark peppered moths (Biston betularia) out-numbered pale moths in polluted industrial towns.",
                    steps =
                      listOf(
                        "Step 1 — Coal soot blackened tree bark, killing pale lichens.",
                        "Step 2 — Birds easily spotted and ate pale moths, whereas dark mutants stayed camouflaged (Natural Selection!), surviving to reproduce.",
                      ),
                    finalAnswer = "Directional Natural Selection favoring soot-camouflaged dark alleles",
                    examinerTrap = "Tree soot did NOT mutate moths dark—dark variation ALREADY existed and was selected!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Antibiotic-Resistant Bacteria & Forensic Fingerprints",
                    industryDomain = "Epidemiology & Forensics",
                    narrative =
                      "Stopping antibiotics half-way kills weak bacteria while letting tougher resistant mutants multiply (real-time Darwinian selection!), while unique fingerprint ridges identify suspects.",
                    examConnection = "Explains rapid evolution of pesticide-resistant mosquitoes too!",
                  ),
                ),
              labType = PracticalLabType.EVOLUTION_VARIATION_LAB,
              labBrief =
                "Switch between Bell-Curve Continuous Variation (Height) and Discrete Bar Discontinuous Traits (Blood Group), or run Industrial Moth Natural Selection!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_11_1",
                    topic = "Chargaff DNA Math",
                    examType = "JAMB",
                    year = 2024,
                    question = "A double-stranded DNA sample contains 18% Guanine. What percentage of Adenine does it contain?",
                    options = listOf("18%", "32%", "36%", "64%"),
                    correctIndex = 1,
                    hint = "G = C = 18% ⇒ G+C = 36% ⇒ A+T = 64% split equally.",
                    stepByStepSolution = "(100 − 36) / 2 = 32% Adenine.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_2",
                    topic = "Discontinuous Variation",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which human trait is an example of DISCONTINUOUS (discrete) variation?",
                    options = listOf("Body weight", "Height", "ABO Blood group", "Hand span"),
                    correctIndex = 2,
                    hint = "You are cleanly Type A, B, AB, or O with zero intermediates.",
                    stepByStepSolution = "Blood groups fall into sharp distinct categories unaffected by diet.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_3",
                    topic = "Homologous Organs",
                    examType = "JAMB",
                    year = 2025,
                    question = "The human arm, whale flipper, and bat wing share the same pentadactyl bone blueprint. This demonstrates:",
                    options = listOf("Analogous convergent structures", "Homologous structures proving divergent common ancestry", "Lamarckian disuse", "Artificial cloning"),
                    correctIndex = 1,
                    hint = "Same underlying bone plan adapted for grasping, swimming, and flying.",
                    stepByStepSolution = "Homologous limbs reveal common vertebrate evolutionary ancestry.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_4",
                    topic = "RNA vs DNA",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which nitrogenous base replaces Thymine in RNA molecules?",
                    options = listOf("Cytosine", "Adenine", "Uracil", "Guanine"),
                    correctIndex = 2,
                    hint = "A pairs with U in RNA.",
                    stepByStepSolution = "RNA uses Uracil (U) instead of Thymine (T).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_5",
                    topic = "Lamarck Error",
                    examType = "JAMB",
                    year = 2021,
                    question = "Lamarck's evolutionary theory of 'Inheritance of Acquired Characteristics' was disproved primarily because:",
                    options = listOf("Genes mutated too fast", "Body muscle/somatic changes aren't written into sperm/egg DNA", "Fossils don't exist", "Animals don't adapt"),
                    correctIndex = 1,
                    hint = "Cutting tails off mice never produces tailless baby mice (Weismann).",
                    stepByStepSolution = "Somatic modifications do not alter germ-cell DNA inherited by offspring.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_6",
                    topic = "Vestigial Evidence",
                    examType = "NECO",
                    year = 2024,
                    question = "The human appendix and tailbone (coccyx) serve as evolutionary clues known as:",
                    options = listOf("Analogous organs", "Vestigial structures", "Mutant tumors", "Adaptive radiation"),
                    correctIndex = 1,
                    hint = "Reduced remnants of structures functional in distant ancestors.",
                    stepByStepSolution = "Vestigial organs retain evolutionary history even after losing major function.",
                  ),
                ),
            ),
          ),
      ),
    )
}
