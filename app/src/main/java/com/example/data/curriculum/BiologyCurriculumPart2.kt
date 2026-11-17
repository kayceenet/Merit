package com.example.data.curriculum

object BiologyCurriculumPart2 {

  val modules: List<CourseModule> =
    listOf(
      CourseModule(
        id = "bio_mod_3",
        subjectId = "biology",
        indexLabel = "Module 03",
        title = "Cell Division, Mendelian Genetics & Evolution",
        summary = "Master Mitosis vs Meiosis, Punnett crosses, sickle-cell/ABO/sex-linked inheritance, and Darwinian natural selection.",
        lessons =
          listOf(
            Lesson(
              id = "bio_07_mitosis_meiosis_reproduction",
              moduleId = "bio_mod_3",
              subjectId = "biology",
              title = "Mitosis vs Meiosis, Pollination & Double Fertilization",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. C.1–2 • WAEC Reproduction",
              learningOutcomes =
                listOf(
                  "Compare Mitosis (2 diploid identical cells) vs Meiosis (4 haploid varied gametes via crossing over).",
                  "Trace wind vs insect pollination features and double fertilization producing 2n zygote + 3n endosperm.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Mitosis (Growth/Repair) vs Meiosis (Gametes)",
                    explanation =
                      "MITOSIS produces TWO genetically IDENTICAL diploid (2n) daughter cells for growth, healing, and asexual binary fission. MEIOSIS runs two divisions (Meiosis I halves chromosomes 2n -> n; Meiosis II splits chromatids) yielding FOUR genetically UNIQUE haploid (n) gametes! Crossing-over at Pachytene/Prophase I creates vital genetic variation.",
                    keyFormula = "\\text{Mitosis: } 2n \\rightarrow 2n + 2n\\ (\\text{Identical})   |   \\text{Meiosis: } 2n \\rightarrow 4 \\times n\\ (\\text{Varied})",
                    examTip =
                      "Chromosomes line up at the cell equator during METAPHASE (remember 'M' for Middle!) and separate to opposite poles in ANAPHASE ('A' for Apart!).",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Flowering Plant Double Fertilization & Fruits",
                    explanation =
                      "Pollen tube delivers TWO male nuclei into the ovule embryo sac: Male Nucleus 1 + Egg Cell -> DIPLOID Zygote (2n embryo); Male Nucleus 2 + 2 Polar Nuclei -> TRIPLOID Endosperm (3n food store)! After fertilization, OVULE develops into the SEED and OVARY develops into the FRUIT!",
                    keyFormula = "\\text{Ovule} \\rightarrow \\text{Seed}   |   \\text{Ovary} \\rightarrow \\text{Fruit}   |   \\text{Endosperm} = 3n",
                    examTip =
                      "Epigeal germination (cowpea/bean) pushes cotyledons ABOVE soil via hypocotyl elongation; Hypogeal germination (maize) leaves cotyledon BELOW soil!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Counting Chromosome Numbers Across Gametes & Endosperm",
                    examSource = "JAMB UTME 2024 • Biology Q29",
                    problemStatement =
                      "A maize plant has a diploid leaf cell chromosome count of 2n = 20. State the number of chromosomes in (a) a pollen grain nucleus, (b) root cortex cell, and (c) endosperm cell.",
                    steps =
                      listOf(
                        "Step 1 — Root cortex is somatic (mitotic): 2n = 20.",
                        "Step 2 — Pollen grain gamete is haploid (meiotic): n = 10.",
                        "Step 3 — Endosperm results from 1 male nucleus (10) + 2 polar nuclei (10 + 10): 3n = 30.",
                      ),
                    finalAnswer = "Pollen = 10; Root = 20; Endosperm = 30",
                    examinerTrap = "Endosperm is TRIPLOID (3n), NOT diploid!",
                  ),
                  WorkedExample(
                    title = "Identifying Wind-Pollinated Flower Specimens",
                    examSource = "WAEC SSCE 2023 • Paper 3 Specimen",
                    problemStatement =
                      "State three structural adaptations visible in a wind-pollinated grass/maize flower.",
                    steps =
                      listOf(
                        "Step 1 — Dull/small petals with NO nectar or scent (doesn't waste energy attracting insects).",
                        "Step 2 — Long hanging filaments with versatile anthers shaking huge quantities of light smooth pollen.",
                        "Step 3 — Large feathery stigmas protruding outside bracts to comb airborne pollen.",
                      ),
                    finalAnswer = "Dull bracts, versatile dangling anthers, large feathery stigmas",
                    examinerTrap = "Insect pollen is sticky/spiky and sparse; wind pollen is smooth, light, and abundant!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Stem-Cell Skin Grafting & Cassava Vegetative Propagation",
                    industryDomain = "Regenerative Medicine & Agronomy",
                    narrative =
                      "Skin grafts multiply via mitosis to heal burns rapidly, just as IITA cassava stem cuttings clone high-yielding disease-resistant varieties mitotically without genetic reshuffling.",
                    examConnection = "Distinguishes mitotic clonal consistency from meiotic variability.",
                  ),
                  RealLifeScenario(
                    title = "Human Chorionic Placental Exchange",
                    industryDomain = "Obstetrics & Embryology",
                    narrative =
                      "The placenta links fetal and maternal capillaries closely without mixing blood directly — letting O₂ and glucose diffuse in while shielding baby from maternal blood pressure spikes.",
                    examConnection = "Standard WAEC pregnancy physiology question.",
                  ),
                ),
              labType = PracticalLabType.MENDELIAN_GENETICS,
              labBrief =
                "Inspect how meiotic haploid segregation feeds parental alleles cleanly into the Mendelian Punnett Cross Lab below!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_07_1",
                    topic = "Cell Division",
                    examType = "JAMB",
                    year = 2025,
                    question = "At which stage of mitosis do sister chromatids separate toward opposite poles of the cell?",
                    options = listOf("Prophase", "Metaphase", "Anaphase", "Telophase"),
                    correctIndex = 2,
                    hint = "Remember 'A' for Apart.",
                    stepByStepSolution = "Spindle fibers shorten during Anaphase, pulling sister chromatids Apart to opposite poles.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_2",
                    topic = "Plant Reproduction",
                    examType = "WAEC",
                    year = 2023,
                    question = "Following successful fertilization in a flowering plant, the ovary wall matures into the:",
                    options = listOf("Seed coat (testa)", "Fruit pericarp", "Endosperm", "Embryo"),
                    correctIndex = 1,
                    hint = "Ovule becomes seed; Ovary becomes fruit.",
                    stepByStepSolution = "The ovary develops into the fruit (and ovary wall into pericarp), while the ovule forms the seed.",
                    waecMarkingScheme = "A1: Fruit / pericarp.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_07_3",
                    topic = "Germination",
                    examType = "JAMB",
                    year = 2022,
                    question = "Which combination of external conditions is strictly necessary for ALL dormant seeds to germinate?",
                    options =
                      listOf(
                        "Soil, sunlight, and fertilizer",
                        "Water, oxygen, and suitable warmth",
                        "Carbon dioxide, chlorophyll, and water",
                        "Nitrogen, sunlight, and moisture",
                      ),
                    correctIndex = 1,
                    hint = "Dormant seeds photosynthesize ONLY AFTER green leaves emerge!",
                    stepByStepSolution = "Germination requires moisture (activate enzymes), oxygen (aerobic ATP respiration), and warmth; sunlight is unneeded initially for most seeds.",
                  ),
                ),
            ),
            Lesson(
              id = "bio_08_mendelian_genetics",
              moduleId = "bio_mod_3",
              subjectId = "biology",
              title = "Mendelian Crosses, Codominance & Sickle-Cell Inheritance",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. D.1–2 • WAEC Genetics",
              learningOutcomes =
                listOf(
                  "Solve monohybrid, dihybrid (9:3:3:1), ABO multiple-allele, and HbA/HbS sickle-cell Punnett problems.",
                  "Predict X-linked recessive inheritance probabilities (hemophilia & color blindness) in sons vs daughters.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Genotype vs Phenotype & Test Crosses",
                    explanation =
                      "Genotype is an organism's allele pair (e.g., TT or Tt); Phenotype is the observable trait (Tall). In COMPLETE DOMINANCE (Tt × Tt), offspring yield 3 Tall : 1 Short phenotype (1 TT : 2 Tt : 1 tt genotype). A TEST CROSS mates an unknown dominant individual against a homozygous recessive (tt) to reveal whether it was pure TT or hybrid Tt!",
                    keyFormula = "Tt \\times Tt \\Rightarrow 3:1\\text{ Pheno} \\mid 1:2:1\\text{ Geno}   |   Hb^{AS} \\times Hb^{AS} \\Rightarrow 25\\% AA : 50\\% AS : 25\\% SS",
                    examTip =
                      "In CODOMINANCE (Hb^A/Hb^S or Blood Groups I^A/I^B), BOTH alleles show in the heterozygote, making Phenotypic AND Genotypic ratios BOTH 1:2:1!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: ABO Blood Groups & Sex-Linked Traits",
                    explanation =
                      "ABO blood groups feature THREE alleles (Iᴬ, Iᴮ, i): Iᴬ and Iᴮ are CODOMINANT over recessive i! So Group A can be IᴬIᴬ or Iᴬi; Group B can be IᴮIᴮ or Iᴮi; Group AB is IᴬIᴮ; Group O is ii. Sex-linked alleles sit on the X chromosome (color blindness, hemophilia): a boy (XY) needs ONLY ONE defective Xʰ from his mother to express the condition!",
                    keyFormula = "\\text{ABO: } I^A = I^B > i   |   \\text{X-Linked Sons: } X^HY \\text{ (Normal) or } X^hY \\text{ (Affected)}",
                    examTip =
                      "Read JAMB probability wording closely: '25% of ALL children' vs '50% of SONS'!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Pre-Marital Sickle-Cell Genotype Counseling",
                    examSource = "WAEC SSCE 2023 • Paper 2 Q4",
                    problemStatement =
                      "Both parents carry sickle-cell trait (Hb^A Hb^S). Construct a genetic cross and state the chance of their child having sickle-cell anemia.",
                    steps =
                      listOf(
                        "Step 1 — Parents: Hb^A Hb^S × Hb^A Hb^S -> gametes Hb^A, Hb^S from each.",
                        "Step 2 — F₁ combinations: Hb^A Hb^A (1), Hb^A Hb^S (2), Hb^S Hb^S (1).",
                        "Step 3 — Sickle-cell anemia (Hb^S Hb^S) = 1 / 4 = 25% (0.25).",
                      ),
                    finalAnswer = "1/4 or 25% HbSS; 50% HbAS carrier; 25% HbAA",
                    examinerTrap = "Every single pregnancy resets to the exact same 25% chance — having one SS child does NOT protect the next!",
                  ),
                  WorkedExample(
                    title = "Color-Blindness Carrier Mother Cross",
                    examSource = "JAMB UTME 2024 • Biology Q33",
                    problemStatement =
                      "A carrier woman (X^C X^c) marries a normal-vision man (X^C Y). What fraction of their SONS will be color blind?",
                    steps =
                      listOf(
                        "Step 1 — Mother's ova: X^C, X^c. Father's Y-bearing sperm gives sons.",
                        "Step 2 — Sons are X^C Y (Normal) and X^c Y (Color blind) in 1:1 ratio.",
                        "Step 3 — Fraction among SONS = 1/2 = 50% (1/4 of all children).",
                      ),
                    finalAnswer = "50% of sons (25% of all children)",
                    examinerTrap = "Check denominator! 'Of their sons' means denominator is 2, not 4!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Pre-Marital Genotype Clinics & Heterozygote Advantage",
                    industryDomain = "Hematology & Public Health",
                    narrative =
                      "Hb^AS heterozygotes survive severe Plasmodium falciparum malaria far better than Hb^AA, maintaining the Hb^S allele in tropical populations while pre-marital Punnett counseling protects future families.",
                    examConnection = "Bridges Mendelian genetics, clinical counseling, and natural selection!",
                  ),
                  RealLifeScenario(
                    title = "Blood Transfusion Compatibility Screening",
                    industryDomain = "Clinical Hematology",
                    narrative =
                      "Group O (ii) lacks A/B surface antigens (universal red cell donor), whereas Group AB (IᴬIᴮ) lacks anti-A/anti-B plasma antibodies (universal recipient).",
                    examConnection = "Directly tests codominance and agglutination compatibility.",
                  ),
                ),
              labType = PracticalLabType.MENDELIAN_GENETICS,
              labBrief =
                "Experiment in the Mendelian & Sickle-Cell Cross Lab below: compare AS × AS, AA × SS, and AS × SS live Punnett ratios!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_08_1",
                    topic = "ABO Genetics",
                    examType = "JAMB",
                    year = 2024,
                    question = "A heterozygous Blood Group A father (Iᴬi) and heterozygous Group B mother (Iᴮi) can produce children of how many blood groups?",
                    options = listOf("1", "2", "3", "4"),
                    correctIndex = 3,
                    hint = "Draw the 2×2 Punnett grid for (Iᴬ, i) × (Iᴮ, i).",
                    stepByStepSolution = "Genotypes produced: IᴬIᴮ (AB), Iᴬi (A), Iᴮi (B), ii (O) -> all 4 blood group phenotypes!",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_2",
                    topic = "Mendelian Ratios",
                    examType = "WAEC",
                    year = 2023,
                    question = "In a classic Mendelian dihybrid cross between two heterozygotes (TtRr × TtRr), the expected F₂ phenotypic ratio is:",
                    options = listOf("3:1", "1:2:1", "9:3:3:1", "1:1:1:1"),
                    correctIndex = 2,
                    hint = "Combine two independent 3:1 ratios: (3:1) × (3:1).",
                    stepByStepSolution = "Independent assortment of two heterozygous traits yields the classic 9:3:3:1 F₂ phenotypic ratio.",
                    waecMarkingScheme = "A1: 9:3:3:1.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_08_3",
                    topic = "Sex-Linked Inheritance",
                    examType = "JAMB",
                    year = 2022,
                    question = "Why are X-linked recessive disorders like hemophilia far more common in males than females?",
                    options =
                      listOf(
                        "Males have two X chromosomes",
                        "Males inherit Y from mother",
                        "A single recessive X allele expresses in XY males",
                        "Females cannot carry X chromosomes",
                      ),
                    correctIndex = 2,
                    hint = "Y carries no matching locus to mask a defective X allele.",
                    stepByStepSolution = "XY males have only one X chromosome, so one recessive allele Xʰ causes full expression (whereas XX females need two).",
                  ),
                ),
            ),
            Lesson(
              id = "bio_09_variation_evolution",
              moduleId = "bio_mod_3",
              subjectId = "biology",
              title = "Variation, Natural Selection & Evolutionary Evidence",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. D.3 • WAEC Evolution",
              learningOutcomes =
                listOf(
                  "Distinguish continuous variation (height, polygenic) vs discontinuous variation (blood group, tongue rolling).",
                  "Compare Darwin's Natural Selection vs Lamarck's disproven inheritance of acquired traits.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Continuous vs Discontinuous Variation",
                    explanation =
                      "CONTINUOUS VARIATION changes smoothly with intermediates (height, body weight, skin shade) — governed by MANY genes + environment, forming a bell-shaped normal curve! DISCONTINUOUS VARIATION splits organisms into sharp, non-overlapping classes (ABO blood group, fingerprint whorl/arch, tongue rolling) — controlled purely by 1–2 genes unaffected by diet or sunlight!",
                    keyFormula = "\\text{Continuous: Height/Weight (Bell Curve)}   |   \\text{Discontinuous: ABO/Tongue Roll (Discrete Bars)}",
                    examTip =
                      "Morphological variation affects external body form; Physiological variation affects internal biochemical traits (ABO blood group, PTC tasting)!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Darwin's Natural Selection vs Lamarck",
                    explanation =
                      "LAMARCK argued giraffes stretched their necks and passed stretched necks to offspring — FALSE (acquired gym muscles aren't coded in sperm/egg DNA!). DARWIN explained variation ALREADY exists -> struggle for scarce resources -> 'Survival of the Fittest' -> survivors breed and pass favorable genes!",
                    keyFormula = "\\text{Mutation/Meiosis} \\rightarrow \\text{Selection Pressure} \\rightarrow \\text{Differential Reproduction}",
                    examTip =
                      "Antibiotic-resistant bacteria and Peppered Moth industrial melanism are classic modern demonstrations of Darwinian selection!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Malaria Antibiotics Resistance Selection Mechanism",
                    examSource = "JAMB UTME 2023 • Biology Q35",
                    problemStatement =
                      "Using Darwin's theory, explain why incomplete malaria dosing breeds chloroquine-resistant Plasmodium strains.",
                    steps =
                      listOf(
                        "Step 1 — Spontaneous random mutations create rare parasites able to pump out the drug.",
                        "Step 2 — Incomplete drug doses kill sensitive parasites while leaving resistant mutants alive.",
                        "Step 3 — Resistant survivors reproduce rapidly without competition, spreading resistance genes.",
                      ),
                    finalAnswer = "Pre-existing resistant mutants survive selection and multiply",
                    examinerTrap = "The drug DOES NOT 'cause' mutations intentionally — it SELECTS pre-existing resistant variants!",
                  ),
                  WorkedExample(
                    title = "Evidence of Evolution: Homologous vs Analogous Organs",
                    examSource = "WAEC SSCE 2021 • Paper 1 Q38",
                    problemStatement =
                      "Classify (a) pentadactyl forelimbs of whale/bat/human and (b) wings of butterfly vs bird.",
                    steps =
                      listOf(
                        "Step 1 — Whale flipper, bat wing, and human arm share IDENTICAL pentadactyl bone layout from a common ancestor -> HOMOLOGOUS structures (divergent evolution).",
                        "Step 2 — Bird wing (bones/feathers) and butterfly wing (chitin membrane) look functionally similar for flight with totally different origins -> ANALOGOUS structures (convergent evolution).",
                      ),
                    finalAnswer = "(a) Homologous (common ancestry); (b) Analogous (convergent evolution)",
                    examinerTrap = "Homologous = same origin/different function; Analogous = different origin/same function!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Camouflage in Peppered Moths & Grasshoppers",
                    industryDomain = "Evolutionary Ecology",
                    narrative =
                      "During soot-darkened industrial winters, dark melanic moths hid unseen from hungry birds on blackened bark while pale moths were eaten — flipping allele frequencies within decades.",
                    examConnection = "Classic textbook Natural Selection observation in WAEC.",
                  ),
                  RealLifeScenario(
                    title = "Biometric Fingerprint Criminology",
                    industryDomain = "Forensic Genetics",
                    narrative =
                      "Fingerprint patterns (loop, whorl, arch) remain fixed for life and exhibit sharp discontinuous variation useful for unique citizen identity.",
                    examConnection = "Tests discontinuous morphological human variation.",
                  ),
                ),
              labType = PracticalLabType.MENDELIAN_GENETICS,
              labBrief =
                "Inspect how discrete allele crosses drive discontinuous trait ratios vs polygenic continuous inheritance.",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_09_1",
                    topic = "Variation",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which human characteristic is an example of DISCONTINUOUS variation?",
                    options = listOf("Body mass", "Height", "ABO blood group", "Leaf length"),
                    correctIndex = 2,
                    hint = "Has distinct categories with zero intermediates.",
                    stepByStepSolution = "ABO blood groups fall cleanly into discrete classes (A, B, AB, O) unlike continuous height or weight.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_2",
                    topic = "Evolution",
                    examType = "WAEC",
                    year = 2023,
                    question = "The forelimb of a whale and the arm of a human share the same five-digit pentadactyl bone plan. This is evidence of:",
                    options = listOf("Convergent evolution", "Homologous structures from common ancestry", "Use and disuse", "Artificial cloning"),
                    correctIndex = 1,
                    hint = "Shared structural anatomy adapted for swimming vs grasping.",
                    stepByStepSolution = "Homologous organs evolve from a shared ancestor into varied forms (divergent evolution).",
                    waecMarkingScheme = "A1: Homologous organs / divergent evolution from common ancestor.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_09_3",
                    topic = "Adaptation",
                    examType = "JAMB",
                    year = 2021,
                    question = "According to Charles Darwin, organisms best suited to their environment survive because of:",
                    options = listOf("Willful effort", "Natural selection", "Mutation suppression", "Acquired behavior inheritance"),
                    correctIndex = 1,
                    hint = "Environmental selection of advantageous heritable traits.",
                    stepByStepSolution = "Natural selection favors individuals carrying advantageous heritable variations, increasing their reproductive success.",
                  ),
                ),
            ),
          ),
      ),
      CourseModule(
        id = "bio_mod_4",
        subjectId = "biology",
        indexLabel = "Module 04",
        title = "Ecology, Energy Flow & Biogeochemical Cycles",
        summary = "Master trophic levels, the 10% energy pyramid rule, nitrogen & carbon cycles, symbiosis, and population sampling.",
        lessons =
          listOf(
            Lesson(
              id = "bio_10_trophic_pyramids_biomes",
              moduleId = "bio_mod_4",
              subjectId = "biology",
              title = "Ecosystems, Food Webs, Ecological Pyramids & Symbiosis",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. E.1–2 • WAEC Ecology",
              learningOutcomes =
                listOf(
                  "Calculate trophic energy transfers using the ~10% ecological efficiency rule.",
                  "Distinguish Mutualism, Commensalism, Parasitism, and Saprophytism with West African examples.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Food Chains & The 10% Energy Pyramid",
                    explanation =
                      "Every ecosystem runs on unidirectional energy! PRODUCERS (green plants/phytoplankton) trap ~1% of sunlight. Only ~10% of chemical energy passes up each step (Producer -> Primary Consumer -> Secondary Consumer -> Tertiary Consumer) — ~90% is lost as respiration heat, movement, and undigested waste! That's why Pyramid of ENERGY is ALWAYS upright and food chains rarely exceed 4–5 steps.",
                    keyFormula = "E_{n+1} \\approx 0.10 \\times E_n   \\quad (\\text{90\\% lost per trophic step!})",
                    examTip =
                      "Pyramid of NUMBERS can invert (1 huge Iroko tree feeding 10,000 caterpillars!), whereas Pyramid of ENERGY NEVER inverts!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Ecological Associations (Symbiosis Matrix)",
                    explanation =
                      "Check signs (+ benefits, 0 unaffected, − harmed): MUTUALISM (+/+) — Rhizobium bacteria in cowpea root nodules or Lichen (Alga + Fungus); COMMENSALISM (+/0) — Remora fish hitching onto shark or Epiphytic fern on oil palm trunk; PARASITISM (+/−) — Plasmodium in human, Tapeworm, Mistletoe on cocoa tree!",
                    keyFormula = "\\text{Mutualism } (+/+)   |   \\text{Commensalism } (+/0)   |   \\text{Parasitism } (+/-)",
                    examTip =
                      "Epiphytes (orchids/ferns on tall trees) ONLY perch for sunlight without piercing host xylem — they are COMMENSALS, whereas Dodder & Mistletoe pierce phloem/xylem as PARASITES!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                  title = "Trophic Pyramid Energy Calculation",
                  examSource = "JAMB UTME 2024 • Biology Q37",
                  problemStatement =
                      "Grass (Producers) fixes 20,000 kJ of energy. Assuming 10% energy transfer per trophic level, how much energy reaches Hawks in the chain: Grass -> Grasshopper -> Lizard -> Hawk?",
                  steps =
                    listOf(
                      "Step 1 — Grass (Level 1) = 20,000 kJ.",
                      "Step 2 — Grasshopper (Level 2) = 10% of 20,000 = 2,000 kJ.",
                      "Step 3 — Lizard (Level 3) = 10% of 2,000 = 200 kJ.",
                      "Step 4 — Hawk (Level 4) = 10% of 200 = 20 kJ.",
                    ),
                  finalAnswer = "20 kJ",
                  examinerTrap = "Count the ARROWS (3 transfers = 10⁻³), not total organism count!",
                ),
                WorkedExample(
                  title = "Sampling Grass Population Density with Quadrats",
                  examSource = "WAEC SSCE 2022 • Paper 2 Q8",
                  problemStatement =
                    "A 1.0 m × 1.0 m quadrat is thrown randomly 5 times in a 100 m² school field, yielding Tridax counts of 4, 6, 3, 7, 5. Estimate total Tridax population.",
                  steps =
                    listOf(
                      "Step 1 — Mean density per 1 m² quadrat = (4 + 6 + 3 + 7 + 5) / 5 = 25 / 5 = 5 plants/m².",
                      "Step 2 — Total field population = Density × Field Area = 5 × 100 = 500 plants.",
                    ),
                  finalAnswer = "500 Tridax plants",
                  examinerTrap = "Quadrats measure stationary plants only — use Capture-Mark-Recapture for mobile grasshoppers or fish!",
                ),
              ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Kainji Savanna Grasslands & Niger Delta Mangroves",
                    industryDomain = "Nigerian Conservation Biology",
                    narrative =
                      "Mangrove prop roots hold anoxic coastal mud with breathing pneumatophores while Guinea savanna trees shed leaves and grow thick fire-resistant bark against dry-season bushfires.",
                    examConnection = "Direct match for Nigerian biome adaptations in WAEC Paper 2.",
                  ),
                  RealLifeScenario(
                    title = "Grain vs Beef Caloric Footprint",
                    industryDomain = "Sustainable Agriculture",
                    narrative =
                      "Because 90% of grain energy dissipates as cow respiration heat, an acre of beans or maize feeds nearly 10× more humans directly than beef cattle raised on that same grain!",
                    examConnection = "Applies the 10% trophic energy pyramid rule directly.",
                  ),
                ),
              labType = PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
              labBrief =
                "Use the Trophic Energy Pyramid Lab below: vary Producer Energy and Transfer Efficiency (5%–20%) to inspect biomass & energy drops across 4 trophic levels!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_10_1",
                    topic = "Energy Flow",
                    examType = "JAMB",
                    year = 2025,
                    question = "If producers capture 50,000 J of solar energy and 10% transfers per trophic level, how much reaches Secondary Consumers?",
                    options = listOf("5,000 J", "500 J", "50 J", "5 J"),
                    correctIndex = 1,
                    hint = "Producer (50,000) -> Primary Consumer -> Secondary Consumer (2 steps).",
                    stepByStepSolution = "Primary Consumer gets 5,000 J; Secondary Consumer gets 10% of 5,000 J = 500 J.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_2",
                    topic = "Symbiosis",
                    examType = "WAEC",
                    year = 2023,
                    question = "The association between Nitrogen-fixing Rhizobium bacteria and leguminous cowpea roots is an example of:",
                    options = listOf("Parasitism", "Commensalism", "Mutualism", "Saprophytism"),
                    correctIndex = 2,
                    hint = "Bacteria get sugars/shelter; plant gets fixed nitrates.",
                    stepByStepSolution = "Both species gain essential nutrition -> Mutualism (+/+).",
                    waecMarkingScheme = "A1: Mutualism.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_10_3",
                    topic = "Ecological Instruments",
                    examType = "JAMB",
                    year = 2022,
                    question = "Which instrument measures the rate of wind speed in a terrestrial habitat study?",
                    options = listOf("Hygrometer", "Anemometer", "Secchi disc", "Photometer"),
                    correctIndex = 1,
                    hint = "Hygrometer = humidity; Secchi disc = water turbidity.",
                    stepByStepSolution = "An anemometer measures wind speed; hygrometer measures humidity; Secchi disc measures water transparency.",
                  ),
                ),
            ),
            Lesson(
              id = "bio_11_cycles_conservation",
              moduleId = "bio_mod_4",
              subjectId = "biology",
              title = "Nitrogen & Carbon Cycles, Pollution & Succession",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. E.3–4 • WAEC Cycles & Conservation",
              learningOutcomes =
                listOf(
                  "Trace atmospheric N₂ -> Nitrites/Nitrates -> Plant proteins -> Denitrification via named bacteria.",
                  "Identify Eutrophication causes, Carbon cycle balances, and Primary vs Secondary succession.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Step 1: Master the 4 Bacterial Teams of the Nitrogen Cycle",
                    explanation =
                      "Plants CANNOT breathe atmospheric N₂ gas directly — they absorb soluble NITRATES (NO₃⁻)! Remember the 4 bacterial roles: (1) FIXERS: Rhizobium / Azotobacter / Lightning turn N₂ -> Ammonia/Nitrates; (2) PUTREFYING bacteria rot dead proteins -> Ammonia; (3) NITRIFYING bacteria oxidize Ammonia -> Nitrites (NITROSOMONAS) -> Nitrates (NITROBACTER); (4) DENITRIFYING bacteria (Pseudomonas) in soggy airless soil turn nitrates BACK into useless N₂ gas!",
                    keyFormula = "NH_4^+ \\xrightarrow{\\text{Nitrosomonas}} NO_2^- \\xrightarrow{\\text{Nitrobacter}} NO_3^- \\xrightarrow{\\text{Pseudomonas}} N_2\\uparrow",
                    examTip =
                      "JAMB loves the order: Nitrosomonas makes Nitrite (NO₂⁻); Nitrobacter makes Nitrate (NO₃⁻)!",
                  ),
                  ConceptBlock(
                    heading = "Step 2: Eutrophication, Greenhouse Gases & Succession",
                    explanation =
                      "Washing NPK fertilizer or sewage into rivers triggers explosive ALGAL BLOOM! When algae die, aerobic bacteria multiply and strip ALL dissolved oxygen, suffocating fish -> EUTROPHICATION. Meanwhile PRIMARY SUCCESSION colonizes bare lifeless rock starting with pioneer LICHENS, whereas SECONDARY SUCCESSION regrows far faster on burned farmland because soil/seeds remain!",
                    keyFormula = "\\text{Bare Rock} \\xrightarrow{\\text{Lichens}} \\text{Mosses} \\rightarrow \\text{Herbs} \\rightarrow \\text{Climax Forest}",
                    examTip =
                      "Carbon(IV) oxide (CO₂) and Methane (CH₄) drive Global Warming; CFCs deplete the stratospheric Ozone layer (not acid rain — SO₂ causes acid rain!).",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                  title = "Preventing Soil Nitrogen Loss on Farms",
                  examSource = "WAEC SSCE 2023 • Paper 2 Q9",
                  problemStatement =
                    "Explain how draining waterlogged farmland preserves soil nitrate fertility.",
                  steps =
                    listOf(
                      "Step 1 — Waterlogged soil lacks oxygen (anaerobic conditions).",
                      "Step 2 — Anaerobic Denitrifying bacteria (Pseudomonas) convert fertile nitrates NO₃⁻ into escaping atmospheric N₂ gas.",
                      "Step 3 — Drainage restores soil air, halting denitrification and favoring aerobic nitrification.",
                    ),
                  finalAnswer = "Aeration suppresses anaerobic denitrifying bacteria",
                  examinerTrap = "Don't confuse Leaching (rain washing nitrates deeper) with Denitrification (bacteria turning NO₃⁻ to N₂ gas)!",
                ),
                WorkedExample(
                  title = "Tracing Lake Eutrophication Fish Kills",
                  examSource = "JAMB UTME 2024 • Biology Q39",
                  problemStatement =
                    "What causes massive fish death weeks after heavy fertilizer runoff enters a calm lagoon?",
                  steps =
                    listOf(
                      "Step 1 — Excess nitrates/phosphates trigger dense surface algal bloom blocking sunlight to deeper plants.",
                      "Step 2 — Dying algae & weeds feed aerobic decomposer bacteria that exhaust dissolved O₂ -> fish suffocate.",
                    ),
                  finalAnswer = "Decomposer bacteria deplete dissolved oxygen following algal decay",
                  examinerTrap = "Fertilizers aren't directly toxic to fish — OXYGEN DEPLETION by decomposers kills them!",
                ),
              ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Crop Rotation with Cowpea & Groundnuts",
                    industryDomain = "Sustainable Farming",
                    narrative =
                      "Alternating maize with cowpea restores depleted soil nitrates naturally via Rhizobium root nodules without expensive synthetic urea.",
                    examConnection = "Classic WAEC practical method of soil fertility conservation.",
                  ),
                  RealLifeScenario(
                    title = "Lagos Lagoon Water Hyacinth Blooms",
                    industryDomain = "Freshwater Ecology",
                    narrative =
                      "Nutrient runoff sparks dense mats of Eichhornia (water hyacinth) choking boat waterways and depleting oxygen beneath.",
                    examConnection = "Real-world West African eutrophication & aquatic pollution example.",
                  ),
                ),
              labType = PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
              labBrief =
                "Inspect how primary producer disruption collapses energy transfer across upper aquatic consumers in the Pyramid Lab!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_11_1",
                    topic = "Nitrogen Cycle",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which bacterium converts nitrites (NO₂⁻) directly into absorbable nitrates (NO₃⁻) in well-aerated soil?",
                    options = listOf("Nitrosomonas", "Nitrobacter", "Rhizobium", "Pseudomonas"),
                    correctIndex = 1,
                    hint = "Nitrosomonas goes NH₃ -> NO₂⁻; the second nitrifier finishes NO₂⁻ -> NO₃⁻.",
                    stepByStepSolution = "Nitrobacter oxidizes nitrites to nitrates for root uptake.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_2",
                    topic = "Pollution",
                    examType = "WAEC",
                    year = 2023,
                    question = "Sulfur(IV) oxide (SO₂) and nitrogen oxides released from burning fossil fuels dissolve in rainwater primarily to cause:",
                    options = listOf("Ozone depletion", "Acid rain", "Eutrophication", "Soil erosion"),
                    correctIndex = 1,
                    hint = "Forms dilute sulfurous and nitric acids in clouds.",
                    stepByStepSolution = "SO₂ and NOₓ react with atmospheric moisture forming Acid Rain that corrodes statues and acidifies lakes.",
                    waecMarkingScheme = "A1: Acid rain.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_11_3",
                    topic = "Succession",
                    examType = "JAMB",
                    year = 2022,
                    question = "The pioneer organisms typically colonizing bare igneous rock during Primary Succession are:",
                    options = listOf("Grasses", "Ferns", "Lichens", "Earthworms"),
                    correctIndex = 2,
                    hint = "Fungal-algal symbiotes that secrete mild acids weathering bare stone.",
                    stepByStepSolution = "Crustose lichens survive extreme rock exposure, weather mineral dust, and trap first organic humus.",
                  ),
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion(
        id = "pq_bio_2025_1",
        topic = "Genetics",
        examType = "JAMB",
        year = 2025,
        question = "If a woman who is a carrier for hemophilia (XᴴXʰ) marries a normal man (XᴴY), what percentage of their SONS will be hemophilic?",
        options = listOf("0%", "25%", "50%", "100%"),
        correctIndex = 2,
        hint = "Inspect ONLY the sons: XᴴY vs XʰY.",
        stepByStepSolution = "Sons get Y from father and Xᴴ or Xʰ from mother -> 1 of 2 sons (50%) inherits XʰY.",
      ),
      PracticeQuestion(
        id = "pq_bio_2025_2",
        topic = "Cell Bioenergetics",
        examType = "WAEC",
        year = 2025,
        question = "Which cellular organelle houses the Krebs cycle and electron transport chain for ATP synthesis?",
        options = listOf("Ribosome", "Golgi apparatus", "Mitochondrion", "Lysosome"),
        correctIndex = 2,
        hint = "Double-membraned organelle with folded cristae.",
        stepByStepSolution = "Aerobic Krebs cycle and oxidative phosphorylation take place inside mitochondria.",
        waecMarkingScheme = "A1: Mitochondrion.",
      ),
      PracticeQuestion(
        id = "pq_bio_2024_1",
        topic = "Plant Transport",
        examType = "JAMB",
        year = 2024,
        question = "Removing a ring of bark (phloem) around a dicotyledonous stem causes swelling above the cut mainly because:",
        options =
          listOf(
            "Water cannot ascend to leaves",
            "Manufactured sugars accumulate above the girdle",
            "Mineral salts leak outward",
            "Transpiration stops",
          ),
        correctIndex = 1,
        hint = "Deep central xylem stays intact; outer phloem is removed.",
        stepByStepSolution = "Ringing removes phloem sieve tubes, halting downward sugar translocation from leaves and causing localized swelling above the ring.",
      ),
      PracticeQuestion(
        id = "pq_bio_2024_2",
        topic = "Hormones & Coordination",
        examType = "WAEC",
        year = 2024,
        question = "Goitre (enlargement of the thyroid gland) is commonly caused by dietary deficiency of:",
        options = listOf("Calcium", "Iron", "Iodine", "Vitamin C"),
        correctIndex = 2,
        hint = "Required to synthesize thyroxine hormone.",
        stepByStepSolution = "Lack of dietary iodine prevents thyroxine synthesis, causing compensatory thyroid gland enlargement (goitre).",
        waecMarkingScheme = "A1: Iodine.",
      ),
      PracticeQuestion(
        id = "pq_bio_2023_1",
        topic = "Osmosis",
        examType = "JAMB",
        year = 2023,
        question = "The shrinking of cytoplasm away from the cell wall of a plant cell immersed in hypertonic salt solution is termed:",
        options = listOf("Hemolysis", "Plasmolysis", "Turgidity", "Flocculation"),
        correctIndex = 1,
        hint = "Happens to walled plant cells losing water rapidly.",
        stepByStepSolution = "Water leaves vacuole/cytoplasm osmotically so plasma membrane detaches from cellulose wall -> Plasmolysis.",
      ),
      PracticeQuestion(
        id = "pq_bio_2023_2",
        topic = "Blood Groups",
        examType = "WAEC",
        year = 2023,
        question = "Why is an individual with Blood Group O safely able to donate packed red blood cells to recipients of Groups A, B, and AB in emergencies?",
        options =
          listOf(
            "O red cells carry no A or B antigens",
            "O plasma lacks all antibodies",
            "O red cells contain both A and B antigens",
            "O blood clots instantaneously",
          ),
        correctIndex = 0,
        hint = "Recipient antibodies attack donor red cell surface antigens.",
        stepByStepSolution = "Group O RBCs lack A and B surface antigens, so recipient anti-A and anti-B antibodies cannot agglutinate them.",
        waecMarkingScheme = "A1: Absence of A and B antigens on Group O red blood cell membranes.",
      ),
      PracticeQuestion(
        id = "pq_bio_2022_1",
        topic = "Ecology",
        examType = "JAMB",
        year = 2022,
        question = "In a pond ecosystem, microscopic floating algae (phytoplankton) occupy which trophic position?",
        options = listOf("Decomposers", "Primary producers", "Primary consumers", "Secondary consumers"),
        correctIndex = 1,
        hint = "They perform photosynthesis trapping solar energy.",
        stepByStepSolution = "Phytoplankton contain chlorophyll and synthesize organic food as Primary Producers.",
      ),
      PracticeQuestion(
        id = "pq_bio_2022_2",
        topic = "Skeleton & Joints",
        examType = "WAEC",
        year = 2022,
        question = "Which type of movable joint allows rotation and movement in all planes at the shoulder and hip?",
        options = listOf("Hinge joint", "Pivot joint", "Ball-and-socket joint", "Gliding joint"),
        correctIndex = 2,
        hint = "Humerus head fits into glenoid cavity.",
        stepByStepSolution = "Shoulder (pectoral) and hip (pelvic) articulations are Ball-and-Socket joints enabling multidirectional motion.",
        waecMarkingScheme = "A1: Ball-and-socket joint.",
      ),
      PracticeQuestion(
        id = "pq_bio_2021_1",
        topic = "Support Tissues in Plants",
        examType = "JAMB",
        year = 2021,
        question = "Which plant tissue provides flexible mechanical support to young herbaceous stems without restricting growth?",
        options = listOf("Sclerenchyma", "Collenchyma", "Dead cork", "Tracheids"),
        correctIndex = 1,
        hint = "Living cells with unevenly thickened cellulose corners.",
        stepByStepSolution = "Collenchyma consists of living elongated cells with cellulose-thickened corners giving flexible support to growing stems and petioles.",
      ),
      PracticeQuestion(
        id = "pq_bio_2021_2",
        topic = "Respiratory Surfaces",
        examType = "WAEC",
        year = 2021,
        question = "Which adaptation is shared by earthworm skin, fish gills, and mammalian alveoli for efficient gas exchange?",
        options =
          listOf(
            "Thick dry cuticle",
            "Thin moist vascularized surface",
            "Presence of chitin rings",
            "Absence of blood capillaries",
          ),
        correctIndex = 1,
        hint = "Gases must dissolve in moisture and diffuse rapidly over short distances.",
        stepByStepSolution = "All efficient respiratory surfaces are thin (one cell thick), moist, richly supplied with capillaries, and large in surface area.",
        waecMarkingScheme = "A1: Thin, moist, vascularized / large surface area.",
      ),
      PracticeQuestion(
        id = "pq_bio_2020_1",
        topic = "Vitamins & Nutrition",
        examType = "JAMB",
        year = 2020,
        question = "Deficiency of Vitamin D in growing children impairs calcium bone absorption, resulting in:",
        options = listOf("Scurvy", "Beri-beri", "Rickets", "Pellagra"),
        correctIndex = 2,
        hint = "Vitamin C -> Scurvy; B1 -> Beri-beri; D -> soft bowed bones.",
        stepByStepSolution = "Vitamin D deficiency prevents proper calcium-phosphate bone mineralization, causing Rickets in children.",
      ),
      PracticeQuestion(
        id = "pq_bio_2020_2",
        topic = "Microorganisms & Immunity",
        examType = "WAEC",
        year = 2020,
        question = "Vector control by draining stagnant gutter pools reduces malaria primarily by breaking which life-cycle stage?",
        options =
          listOf(
            "Plasmodium sporozoite in human liver",
            "Anopheles mosquito egg, larva & pupa stages",
            "Tsetse fly puparium",
            "Housefly maggots",
          ),
        correctIndex = 1,
        hint = "Female Anopheles breeds aquatic eggs, larvae, and pupae in still water.",
        stepByStepSolution = "Female Anopheles mosquitoes lay eggs in stagnant water; draining pools destroys aquatic larvae and pupae.",
        waecMarkingScheme = "A1: Destroys aquatic breeding stages (eggs/larvae/pupae) of Anopheles.",
      ),
      PracticeQuestion(
        id = "pq_bio_2019_1",
        topic = "Soil Science",
        examType = "JAMB",
        year = 2019,
        question = "Which soil type holds optimal water and aeration simultaneously, making it best for crop cultivation?",
        options = listOf("Sandy soil", "Clay soil", "Loamy soil", "Gravel"),
        correctIndex = 2,
        hint = "Balanced blend of sand, silt, clay, and rich humus.",
        stepByStepSolution = "Loamy soil balances drainage (sand), water/nutrient retention (clay/humus), and good air spaces.",
      ),
      PracticeQuestion(
        id = "pq_bio_2019_2",
        topic = "Mammalian Eye",
        examType = "WAEC",
        year = 2019,
        question = "In the mammalian eye, the region of the retina packed exclusively with cones for sharpest color daylight vision is the:",
        options = listOf("Blind spot", "Yellow spot (Fovea centralis)", "Choroid", "Iris"),
        correctIndex = 1,
        hint = "Blind spot has zero photoreceptors; sharpest focus hits fovea.",
        stepByStepSolution = "The Fovea centralis (yellow spot) contains dense cones for acute daylight and color resolution.",
        waecMarkingScheme = "A1: Fovea centralis / Yellow spot.",
      ),
      PracticeQuestion(
        id = "pq_bio_2018_1",
        topic = "Genetics",
        examType = "JAMB",
        year = 2018,
        question = "In Mendel's experiments, crossing a pure-breeding tall pea (TT) with a dwarf pea (tt) yields an F₁ generation that is:",
        options = listOf("100% heterozygous tall (Tt)", "50% tall, 50% dwarf", "75% tall, 25% dwarf", "100% pure tall (TT)"),
        correctIndex = 0,
        hint = "TT gives only T; tt gives only t.",
        stepByStepSolution = "Every F₁ offspring receives T from one parent and t from the other -> 100% heterozygous tall (Tt).",
      ),
      PracticeQuestion(
        id = "pq_bio_2018_2",
        topic = "Conservation",
        examType = "WAEC",
        year = 2018,
        question = "Which practice effectively preserves endangered wildlife biodiversity in situ?",
        options =
          listOf(
            "Indiscriminate bush burning",
            "Establishing strict Game Reserves and National Parks",
            "Continuous monoculture farming",
            "Unregulated logging",
          ),
        correctIndex = 1,
        hint = "Protects natural habitats and breeding populations.",
        stepByStepSolution = "National Parks (e.g., Yankari, Cross River) protect native habitats and ban poaching so populations recover naturally.",
        waecMarkingScheme = "A1: Game reserves / National parks.",
      ),
    )
}
