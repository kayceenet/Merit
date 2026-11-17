package com.example.data.curriculum

object BiologyCurriculum {

  val track =
    SubjectTrack(
      id = "biology",
      title = "Biology Mastery",
      shortName = "Biology",
      tagline = "Cells, Physiology, Bioenergetics, Genetics & Ecological Systems",
      examCoverage = "JAMB 40 Qs • WAEC Paper 1, 2 & Specimen Practical",
      modules =
        listOf(
          CourseModule(
            id = "bio_mod_1",
            subjectId = "biology",
            indexLabel = "Module 01",
            title = "Cell Organization, Membrane Transport & Enzymes",
            summary = "Build from living vs non-living traits and organelle ultrastructure to osmosis, plasmolysis, and enzyme catalysis.",
            lessons =
              listOf(
                Lesson(
                  id = "bio_01_cell_organization",
                  moduleId = "bio_mod_1",
                  subjectId = "biology",
                  title = "Levels of Organization, Organelles & Plant vs Animal Cells",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. A.1–2 • WAEC Cell Biology",
                  learningOutcomes =
                    listOf(
                      "Trace organization from Cell -> Tissue -> Organ -> System -> Organism with plant/animal examples.",
                      "Identify the function of Mitochondria, Ribosomes, Chloroplasts, Golgi apparatus, and Lysosomes.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1 (From Scratch): Inside the Living Cell",
                        explanation =
                          "Every living organism starts from a microscopic cell! Prokaryotes (Bacteria) lack a true membrane-bound nucleus; Eukaryotes (Plants, Animals, Fungi) enclose DNA inside a double-membraned NUCLEUS. Ribosomes synthesize proteins; Mitochondria generate ATP energy; Golgi apparatus packages secretions; Lysosomes digest worn-out parts.",
                        keyFormula = "\\text{Cell} \\rightarrow \\text{Tissue} \\rightarrow \\text{Organ} \\rightarrow \\text{System} \\rightarrow \\text{Organism}",
                        examTip =
                          "Amoeba, Euglena, Paramecium, and Chlamydomonas live as complete single-celled organisms; Volvox lives as a colony!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Plant Cell vs Animal Cell Checklist",
                        explanation =
                          "Plant cells carry three hallmark structures absent in typical animal cells: (1) Rigid CELLULOSE Cell Wall (prevents bursting in water!), (2) Chloroplasts for photosynthesis, and (3) A large permanent central vacuole. Animal cells instead possess CENTRIOLES for spindle fiber formation and store glycogen (not starch).",
                        keyFormula = "\\text{Plant: Cellulose Wall + Chloroplast + Large Vacuole + Starch}   |   \\text{Animal: Centrioles + Glycogen}",
                        examTip =
                          "Fungi have chitin cell walls (no chlorophyll!), and Bacteria have peptidoglycan cell walls!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Classifying Tissues vs Organs in Exam Specimens",
                        examSource = "WAEC SSCE 2023 • Paper 1 Q3",
                        problemStatement =
                          "Sort these specimens by level of organization: (i) Hydra, (ii) Blood, (iii) Onion bulb, (iv) Euglena.",
                        steps =
                          listOf(
                            "Step 1 — Euglena: Single eukaryotic cell (Cellular level).",
                            "Step 2 — Hydra: Two germ layers (diploblastic) with specialized tissues (Tissue level).",
                            "Step 3 — Blood: Liquid connective tissue carrying RBCs, WBCs, and platelets (Tissue level).",
                            "Step 4 — Onion bulb: Modified underground shoot with fleshy leaves & stem (Organ level).",
                          ),
                        finalAnswer = "Euglena (Cell), Hydra & Blood (Tissue), Onion bulb (Organ)",
                        examinerTrap = "Onion bulb and Irish potato are ORGANS, while xylem, phloem, and blood are TISSUES!",
                      ),
                      WorkedExample(
                        title = "Magnification Calculation in Microscopy",
                        examSource = "JAMB UTME 2022 • Biology Q5",
                        problemStatement =
                          "A student draws a Paramecium measuring 0.2 mm actual length as a 10 cm diagram. Calculate drawing magnification.",
                        steps =
                          listOf(
                            "Step 1 — Convert both to millimetres: 10 cm = 100 mm.",
                            "Step 2 — Magnification = Image size / Actual size = 100 / 0.2 = ×500.",
                          ),
                        finalAnswer = "×500",
                        examinerTrap = "Always write '×' BEFORE the number (×500) in WAEC Practical drawings — never '500cm'!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Erythrocyte Maturation & Mitochondrial Richness",
                        industryDomain = "Hematology & Sports Physiology",
                        narrative =
                          "Mammalian Red Blood Cells shed their nucleus to maximize hemoglobin space, while heart muscle and sperm cells pack thousands of mitochondria to fuel nonstop contraction and motility.",
                        examConnection = "Links organelle abundance directly to physiological cell specialization.",
                      ),
                      RealLifeScenario(
                        title = "Euglena's Dual Plant/Animal Adaptability",
                        industryDomain = "Freshwater Protistology",
                        narrative =
                          "Euglena swims using an animal-like flagellum and eyespot toward sunlit water surfaces to photosynthesize with plant-like chloroplasts!",
                        examConnection = "Classic JAMB question: Euglena as a plant-animal bridge.",
                      ),
                    ),
                  labType = PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
                  labBrief =
                    "Inspect how chloroplast-driven primary producers anchor the entire ecological energy pyramid in the Lab workspace below.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_01_1",
                        topic = "Cell Biology",
                        examType = "JAMB",
                        year = 2024,
                        question = "Which organelle is primarily responsible for assembling proteins from amino acids?",
                        options = listOf("Lysosome", "Ribosome", "Golgi body", "Centriole"),
                        correctIndex = 1,
                        hint = "Found free in cytoplasm or attached to Rough Endoplasmic Reticulum.",
                        stepByStepSolution = "Ribosomes translate mRNA codons into polypeptide chains (proteins).",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_01_2",
                        topic = "Cell Organization",
                        examType = "WAEC",
                        year = 2023,
                        question = "Which structure exists in BOTH typical plant cells and prokaryotic bacterial cells, though made of different polymers?",
                        options = listOf("Nucleus", "Mitochondrion", "Cell wall", "Chloroplast"),
                        correctIndex = 2,
                        hint = "Plants use cellulose; bacteria use peptidoglycan.",
                        stepByStepSolution = "Both plant and bacterial cells have rigid outer cell walls (cellulose vs peptidoglycan), whereas bacteria lack nuclei and mitochondria.",
                        waecMarkingScheme = "A1: Cell wall.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_01_3",
                        topic = "Levels of Organization",
                        examType = "JAMB",
                        year = 2022,
                        question = "Which of the following is at the ORGAN level of organization?",
                        options = listOf("Spirogyra", "Blood", "Kidney", "Paramecium"),
                        correctIndex = 2,
                        hint = "An organ combines multiple tissues working together.",
                        stepByStepSolution = "The kidney combines epithelial, connective, and nervous tissues; Blood is a tissue; Spirogyra & Paramecium are cellular/filamentous.",
                      ),
                    ),
                ),
                Lesson(
                  id = "bio_02_osmosis_diffusion",
                  moduleId = "bio_mod_1",
                  subjectId = "biology",
                  title = "Diffusion, Osmosis, Plasmolysis & Turgidity",
                  durationMinutes = 18,
                  syllabusTag = "JAMB Sec. B.1 • WAEC Practical Osmosis",
                  learningOutcomes =
                    listOf(
                      "Differentiate passive diffusion, active transport (uses ATP against gradient), and osmosis.",
                      "Predict hemolysis vs crenation in animal RBCs and turgidity vs plasmolysis in plant strips across hypotonic/hypertonic baths.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: How Water Moves Across Membranes",
                        explanation =
                          "OSMOSIS is the net movement of WATER molecules from a region of HIGHER water potential (dilute / hypotonic solution) to a region of LOWER water potential (concentrated / hypertonic solution) through a selectively permeable membrane. Notice water ALWAYS chases the MORE CONCENTRATED solute!",
                        keyFormula = "\\text{Hypotonic (Dilute)} \\xrightarrow{\\text{Water}} \\text{Hypertonic (Concentrated Solute)}",
                        examTip =
                          "ACTIVE TRANSPORT moves mineral ions AGAINST the concentration gradient into root hairs — so it requires cellular ATP respiration and oxygen!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Animal Cells vs Walled Plant Cells",
                        explanation =
                          "In PURE WATER (hypotonic): Red Blood Cells swell and burst (HEMOLYSIS); Plant cells swell tightly against their rigid cellulose wall and become TURGID (supporting soft herbs!). In STRONG SALT/SUGAR (hypertonic): RBCs shrink wrinkled (CRENATION); Plant cytoplasm pulls away from the cell wall (PLASMOLYSIS), causing wilting!",
                        keyFormula = "\\text{Hypotonic: RBC } \\rightarrow \\text{Hemolysis}   |   \\text{Plant } \\rightarrow \\text{Turgid}",
                        examTip =
                          "Boiled potato or yam strips lose semi-permeable membrane integrity — so osmosis stops completely!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Yam Cylinder Length Experiment Analysis",
                        examSource = "WAEC SSCE 2023 • Paper 3 Specimen",
                        problemStatement =
                          "Two fresh yam strips of 5.0 cm are placed in Beaker A (distilled water) and Beaker B (20% sucrose) for 1 hour. Compare their length and texture.",
                        steps =
                          listOf(
                            "Step 1 — Beaker A is hypotonic to yam sap -> water enters by osmosis -> strip lengthens (> 5.0 cm) and feels firm/turgid.",
                            "Step 2 — Beaker B is hypertonic -> water leaves yam cells -> strip shrinks (< 5.0 cm) and feels flaccid/soft due to plasmolysis.",
                          ),
                        finalAnswer = "A: Longer & Turgid (> 5.0 cm); B: Shorter & Flaccid (< 5.0 cm)",
                        examinerTrap = "Say 'becomes turgid/firm' for Beaker A, NEVER 'plant cell bursts'!",
                      ),
                      WorkedExample(
                        title = "Active Uptake of Nitrate Ions in Waterlogged Soil",
                        examSource = "JAMB UTME 2021 • Biology Q14",
                        problemStatement =
                          "Why does waterlogging of soil prevent crops from absorbing nitrate ions even though nitrate ions remain dissolved around roots?",
                        steps =
                          listOf(
                            "Step 1 — Root hairs absorb mineral salts mainly via Active Transport against concentration gradients.",
                            "Step 2 — Waterlogged soil drives out oxygen, halting aerobic respiration and ATP energy needed for carrier proteins.",
                          ),
                        finalAnswer = "Lack of O₂ stops aerobic respiration & ATP for active transport",
                        examinerTrap = "Don't say minerals move by osmosis — ONLY water moves by osmosis!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "IV Hospital Saline Drip Isotonicity",
                        industryDomain = "Clinical Nursing & Emergency Medicine",
                        narrative =
                          "Hospital intravenous drips infuse exact 0.9% NaCl (isotonic to blood plasma) — infusing pure distilled water into a vein would fatally rupture red blood cells by hemolysis!",
                        examConnection = "Shows why isotonicity preserves delicate animal cell membranes.",
                      ),
                      RealLifeScenario(
                        title = "Salting Fish & Meat for Food Preservation",
                        industryDomain = "Food Microbiology",
                        narrative =
                          "Rubbing dry salt onto fish creates a hypertonic brine that dehydrates spoilage bacteria via osmotic plasmolysis so microbes cannot multiply.",
                        examConnection = "Frequent WAEC essay application of osmosis.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Use the Molarity bench to see how changing solute concentration creates osmotic gradients between solutions.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_02_1",
                        topic = "Osmosis",
                        examType = "JAMB",
                        year = 2024,
                        question = "What happens when a living mammalian red blood cell is placed in distilled water?",
                        options = listOf("Crenation", "Plasmolysis", "Hemolysis", "Turgidity"),
                        correctIndex = 2,
                        hint = "Animal cells lack a rigid cellulose wall to stop swelling.",
                        stepByStepSolution = "Water rushes in osmotically until the plasma membrane ruptures -> Hemolysis.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_02_2",
                        topic = "Transport Across Membranes",
                        examType = "WAEC",
                        year = 2023,
                        question = "Which process requires metabolic energy (ATP) to move mineral ions from low soil concentration into root hair cells?",
                        options = listOf("Diffusion", "Osmosis", "Active transport", "Transpiration pull"),
                        correctIndex = 2,
                        hint = "Movement AGAINST a concentration gradient demands ATP.",
                        stepByStepSolution = "Active transport uses ATP from root respiration to pump mineral ions up their gradient.",
                        waecMarkingScheme = "A1: Active transport.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_02_3",
                        topic = "Plasmolysis",
                        examType = "JAMB",
                        year = 2022,
                        question = "A wilted herbaceous plant recovers firmness after watering because its parenchyma cells regain:",
                        options = listOf("Plasmolysis", "Turgor pressure", "Flaccidity", "Crenation"),
                        correctIndex = 1,
                        hint = "Internal water pressure pushing outward against the cell wall gives structural support.",
                        stepByStepSolution = "Turgor pressure keeps non-woody stems and leaves upright.",
                      ),
                    ),
                ),
                Lesson(
                  id = "bio_03_enzymes_biochemistry",
                  moduleId = "bio_mod_1",
                  subjectId = "biology",
                  title = "Food Tests, Biological Molecules & Enzyme Kinetics",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. B.2 • WAEC Practical Food Tests",
                  learningOutcomes =
                    listOf(
                      "Select exact WAEC reagents and color endpoints for Starch, Reducing Sugars, Proteins, and Fats.",
                      "Explain Lock-and-Key specificity, optimum temperature/pH, and irreversible heat denaturation.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: WAEC Practical Food Test Matrix",
                        explanation =
                          "Memorize every color shift cleanly: (1) STARCH + Iodine -> Blue-Black; (2) REDUCING SUGAR (Glucose/Maltose) + Benedict's (heat!) -> Blue -> Green -> Yellow -> Brick-Red ppt; (3) PROTEIN + Biuret (NaOH + 1% CuSO₄) -> Violet/Purple (or Millon's -> brick-red on heating); (4) FAT/OIL + Sudan III -> Red stain (or Translucent spot on paper)!",
                        keyFormula = "\\text{Starch: Iodine (Blue-Black)}   |   \\text{Sugar: Benedict's (Brick-Red)}   |   \\text{Protein: Biuret (Violet)}",
                        examTip =
                          "Sucrose is a NON-REDUCING sugar! To test Sucrose, boil first with dilute HCl to hydrolyze it into Glucose + Fructose, neutralize with NaOH, THEN heat with Benedict's!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Enzymes — Biological Catalysts",
                        explanation =
                          "Enzymes are protein catalysts shaped with a specific 3D ACTIVE SITE ('Lock & Key'). Activity peaks at OPTIMUM temperature (~37–40 °C in humans). Above ~50 °C, hydrogen bonds snap and the active site melts permanently -> DENATURATION (never say 'killed' — enzymes aren't alive!). Stomach Pepsin prefers acidic pH 2; Salivary Amylase (Ptyalin) prefers pH 7; Trypsin prefers alkaline pH 8.",
                        keyFormula = "\\text{Pepsin: pH 2 (Acidic)}   |   \\text{Amylase: pH 7}   |   \\text{Trypsin/Lipase: pH 8 (Alkaline)}",
                        examTip =
                          "Low temperature (0 °C) merely INACTIVATES enzymes reversibly (warm back up and they work!); high heat DENATURES them irreversibly!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Unknown Food Sample Analysis Table",
                        examSource = "WAEC SSCE 2022 • Paper 3 Practical",
                        problemStatement =
                          "Sample X gives (a) Violet color with CuSO₄ + NaOH, (b) remains brown with Iodine, and (c) stays blue after heating with Benedict's. Identify X's nutrient content.",
                        steps =
                          listOf(
                            "Step 1 — Biuret violet confirms PROTEIN is present.",
                            "Step 2 — Brown Iodine means STARCH is absent.",
                            "Step 3 — Blue Benedict's means REDUCING SUGAR is absent.",
                          ),
                        finalAnswer = "Contains Protein only; Starch and Reducing Sugar absent",
                        examinerTrap = "Don't state 'contains sugar' — Benedict's tests REDUCING sugars specifically (sucrose could still be present!).",
                      ),
                      WorkedExample(
                        title = "Comparing Acidic vs Neutral Salivary Amylase Action",
                        examSource = "JAMB UTME 2023 • Biology Q18",
                        problemStatement =
                          "Test tube 1 contains starch + fresh saliva; Tube 2 has starch + saliva + dilute HCl; Tube 3 has starch + boiled saliva. After 20 min at 37 °C, which stays blue-black with Iodine?",
                        steps =
                          listOf(
                            "Step 1 — Tube 1 (pH ~7, 37 °C): Amylase digests starch to maltose -> Iodine stays brown.",
                            "Step 2 — Tube 2 (acidic HCl) & Tube 3 (boiled 100 °C): Amylase fails/denatures -> Starch remains -> Blue-Black!",
                          ),
                        finalAnswer = "Tubes 2 and 3 turn Blue-Black",
                        examinerTrap = "Salivary amylase stops digesting starch as soon as swallowed food hits acidic gastric HCl in the stomach!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Biological Laundry Detergents & Fever Danger",
                        industryDomain = "Biotechnology & Clinical Medicine",
                        narrative =
                          "Bio-detergents pack thermostable proteases and lipases to break blood and oil stains at 40 °C — while in humans, a fever above 41 °C threatens brain enzymes with catastrophic denaturation!",
                        examConnection = "Directly connects temperature enzyme denaturation curves.",
                      ),
                      RealLifeScenario(
                        title = "Lactose Intolerance Enzyme Supplementation",
                        industryDomain = "Human Nutrition",
                        narrative =
                          "People lacking intestinal lactase take oral lactase tablets so milk lactose cleaves cleanly into absorbable glucose and galactose without gut bloating.",
                        examConnection = "Illustrates strict enzyme-substrate Lock & Key specificity.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Use the pH Scale lab below to inspect Stomach pH (~2.0 for Pepsin), Saliva pH (~7.0), and Duodenal Alkaline pH (~8.5 for Trypsin)!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_03_1",
                        topic = "Food Tests",
                        examType = "JAMB",
                        year = 2025,
                        question = "Which reagent produces a purple/violet color when added to egg albumen solution?",
                        options = listOf("Iodine solution", "Biuret reagent", "Benedict's solution", "Sudan III stain"),
                        correctIndex = 1,
                        hint = "Egg albumen is rich in protein.",
                        stepByStepSolution = "Biuret test (NaOH + dilute CuSO₄) turns violet/purple in the presence of peptide bonds (proteins).",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_03_2",
                        topic = "Enzymes",
                        examType = "WAEC",
                        year = 2023,
                        question = "Why do digestive enzymes stop functioning rapidly above 60 °C?",
                        options =
                          listOf(
                            "Substrate runs out faster",
                            "Enzyme molecules are killed",
                            "Active site shape is denatured permanently",
                            "pH automatically drops to 1",
                          ),
                        correctIndex = 2,
                        hint = "Heat disrupts tertiary protein folding.",
                        stepByStepSolution = "Excess heat breaks weak hydrogen/ionic bonds holding the active site's 3D shape, causing irreversible denaturation.",
                        waecMarkingScheme = "A1: Denaturation / loss of 3D active site shape.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_03_3",
                        topic = "Digestion",
                        examType = "JAMB",
                        year = 2022,
                        question = "Which enzyme digests lipids into fatty acids and glycerol in an alkaline small intestine?",
                        options = listOf("Pepsin", "Ptyalin", "Lipase", "Rennin"),
                        correctIndex = 2,
                        hint = "Match substrate root 'lip-' + '-ase'.",
                        stepByStepSolution = "Pancreatic lipase hydrolyzes emulsified fats into fatty acids and glycerol at alkaline duodenal pH.",
                      ),
                    ),
                ),
              ),
          ),
          CourseModule(
            id = "bio_mod_2",
            subjectId = "biology",
            indexLabel = "Module 02",
            title = "Nutrition, Respiration, Internal Transport & Excretion",
            summary = "Master photosynthesis equations, aerobic vs anaerobic ATP respiration, blood circulation, transpiration, and kidney nephrons.",
            lessons =
              listOf(
                Lesson(
                  id = "bio_04_photosynthesis_nutrition",
                  moduleId = "bio_mod_2",
                  subjectId = "biology",
                  title = "Photosynthesis Stages, Limiting Factors & Mammalian Digestion",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. B.3 • WAEC Plant & Animal Nutrition",
                  learningOutcomes =
                    listOf(
                      "Separate the Light-Dependent stage (photolysis of H₂O -> O₂ + ATP + NADPH) from the Calvin Dark stage (CO₂ -> Glucose).",
                      "Trace villus absorption of amino acids/monosaccharides into capillaries vs fatty acids into lacteals.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Light vs Dark Photosynthesis Reactions",
                        explanation =
                          "Green leaves trap solar energy via chlorophyll inside chloroplasts. (1) LIGHT STAGE (Grana/Thylakoids): light splits water molecules (Photolysis) releasing O₂ gas, ATP, and NADPH. (2) DARK / CALVIN STAGE (Stroma): uses ATP + NADPH (not direct light!) to reduce CO₂ into glucose/starch.",
                        keyFormula = "6CO_2 + 6H_2O \\xrightarrow{\\text{Light, Chlorophyll}} C_6H_{12}O_6 + 6O_2\\uparrow",
                        examTip =
                          "All O₂ gas released by plants comes strictly from SPLITTING WATER (H₂O), NOT from CO₂! Soda lime (or KOH/NaOH) absorbs CO₂ in leaf starch experiments.",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Mammalian Dentition, Bile & Small Intestine Absorption",
                        explanation =
                          "Herbivores lack canines and possess a toothless gap (DIASTEMA) + ridged molars for grinding cellulose. Bile (from liver, stored in gall bladder) contains NO enzymes — it EMULSIFIES fats into tiny droplets and neutralizes acidic chyme! Inside the ileum, VILLI capillaries absorb glucose + amino acids to the hepatic portal vein, while LACTEALS absorb fats into lymph!",
                        keyFormula = "\\text{Dental Formula Man: } I\\frac{2}{2}, C\\frac{1}{1}, P\\frac{2}{2}, M\\frac{3}{3} = 32   |   \\text{Bile: Emulsification (No Enzyme)}",
                        examTip =
                          "Never claim bile 'digests' fats chemically — bile physically EMULSIFIES fats to boost lipase surface area!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Destarching & Testing a Variegated Leaf for Starch",
                        examSource = "WAEC SSCE 2023 • Paper 2 Q3",
                        problemStatement =
                          "Why is a potted leaf boiled in water, then boiled in ethanol inside a water bath before adding Iodine?",
                        steps =
                          listOf(
                            "Step 1 — Boiling water kills cells, bursts starch grains, and stops enzyme activity.",
                            "Step 2 — Hot ethanol (heated indirectly via water bath because ethanol is flammable!) dissolves out green chlorophyll so blue-black starch contrast is clearly visible.",
                            "Step 3 — Warm water rinse softens brittle ethanol-treated leaf before Iodine drops.",
                          ),
                        finalAnswer = "Water kills/softens; Ethanol decolorizes chlorophyll safely in water bath",
                        examinerTrap = "Always specify 'water bath / indirect heating' for ethanol — direct Bunsen flame ignites alcohol!",
                      ),
                      WorkedExample(
                        title = "Hepatic Portal Vein Nutrient Delivery",
                        examSource = "JAMB UTME 2022 • Biology Q16",
                        problemStatement =
                          "After a meal of rice and beans, which blood vessel carries the highest concentration of absorbed glucose and amino acids?",
                        steps =
                          listOf(
                            "Step 1 — Starch digests to glucose; protein digests to amino acids inside the ileum.",
                            "Step 2 — Ileum villi capillaries drain directly into the Hepatic Portal Vein toward the liver.",
                          ),
                        finalAnswer = "Hepatic portal vein",
                        examinerTrap = "Fatty acids bypass the hepatic portal vein initially via lacteals -> thoracic duct!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Greenhouse CO₂ Enrichment & Crop Yield",
                        industryDomain = "Controlled Horticulture",
                        narrative =
                          "On bright sunny afternoons CO₂ (~0.04% in air) becomes the limiting factor of photosynthesis; commercial tomato greenhouses inject extra CO₂ to accelerate sugar synthesis.",
                        examConnection = "Illustrates Blackman's law of limiting factors.",
                      ),
                      RealLifeScenario(
                        title = "Gallbladder Stones & Fat Intolerance",
                        industryDomain = "Clinical Gastroenterology",
                        narrative =
                          "Blocking the bile duct stops bile salts reaching the duodenum, slowing lipase fat emulsification and preventing absorption of fat-soluble Vitamins A, D, E, K.",
                        examConnection = "Reinforces bile emulsification and lacteal transport.",
                      ),
                    ),
                  labType = PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
                  labBrief =
                    "Adjust Solar Input & Transfer Efficiency in the Energy Pyramid Lab below to see how plant primary productivity limits all higher food web levels!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_04_1",
                        topic = "Photosynthesis",
                        examType = "JAMB",
                        year = 2024,
                        question = "Oxygen evolved during photosynthesis originates directly from:",
                        options = listOf("Carbon(IV) oxide", "Water molecules", "Glucose breakdown", "Chlorophyll pigment"),
                        correctIndex = 1,
                        hint = "Photolysis occurs in the light-dependent stage.",
                        stepByStepSolution = "Photolysis splits water: 2H₂O -> 4H⁺ + 4e⁻ + O₂↑.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_04_2",
                        topic = "Mammalian Digestion",
                        examType = "WAEC",
                        year = 2023,
                        question = "Which digestive secretion contains NO enzymes yet is essential for efficient fat digestion?",
                        options = listOf("Saliva", "Gastric juice", "Bile", "Succus entericus"),
                        correctIndex = 2,
                        hint = "Produced by the liver to break large fat globules into tiny droplets.",
                        stepByStepSolution = "Bile emulsifies lipids physically (increasing surface area for pancreatic lipase) without containing enzymes.",
                        waecMarkingScheme = "A1: Bile.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_04_3",
                        topic = "Dentition",
                        examType = "JAMB",
                        year = 2022,
                        question = "The toothless gap found between incisors and premolars in grazing herbivores is called the:",
                        options = listOf("Carnassial", "Diastema", "Enamel", "Pulp cavity"),
                        correctIndex = 1,
                        hint = "Allows tongue manipulation of grass cud.",
                        stepByStepSolution = "Herbivores lack canines, creating the diastema gap to separate cropping incisors from grinding cheek teeth.",
                      ),
                    ),
                ),
                Lesson(
                  id = "bio_05_respiration_circulation",
                  moduleId = "bio_mod_2",
                  subjectId = "biology",
                  title = "Cellular Respiration (ATP) & Mammalian Double Circulation",
                  durationMinutes = 22,
                  syllabusTag = "JAMB Sec. B.4–5 • WAEC Transport & Respiration",
                  learningOutcomes =
                    listOf(
                      "Compare Aerobic respiration (36–38 ATP) vs Anaerobic glycolysis in muscle (lactic acid) and yeast (ethanol + CO₂).",
                      "Trace oxygenated/deoxygenated blood through heart chambers, valves, and pulmonary vs systemic circuits.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: Cellular Respiration — Harvesting ATP Energy",
                        explanation =
                          "Respiration is NOT mere breathing — it is enzymatic oxidation of glucose inside cells to release ATP! AEROBIC respiration uses O₂ in mitochondria (C₆H₁₂O₆ + 6O₂ -> 6CO₂ + 6H₂O + 38 ATP). ANAEROBIC respiration in oxygen-starved yeast yields ETHANOL + CO₂ + 2 ATP (bread rising/brewing!); in sprinting human muscles it yields LACTIC ACID (causing muscle cramp / Oxygen Debt!).",
                        keyFormula = "\\text{Aerobic: } 38\\text{ ATP}   |   \\text{Yeast: } C_6H_{12}O_6 \\rightarrow 2C_2H_5OH + 2CO_2 + 2\\text{ ATP}",
                        examTip =
                          "Notice Respiratory Quotient RQ = Volume of CO₂ produced / Volume of O₂ consumed = 1.0 for pure Carbohydrates!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Double Circulation & Plant Xylem/Phloem",
                        explanation =
                          "Right Atrium -> Tricuspid valve -> Right Ventricle -> Pulmonary ARTERY (only artery carrying DEOXYGENATED blood!) -> Lungs -> Pulmonary VEIN (only vein carrying OXYGENATED blood!) -> Left Atrium -> Bicuspid valve -> Thick-walled Left Ventricle -> Aorta to body! In plants, XYLEM carries water/minerals UPWARD via Transpiration Pull (dead lignified vessels); PHLOEM translocates sucrose BIDIRECTIONALLY (living sieve tubes).",
                        keyFormula = "\\text{Pulmonary Artery: Deoxygenated}   |   \\text{Xylem: Water (Up)}   |   \\text{Phloem: Sugar (Bidirectional)}",
                        examTip =
                          "Left Ventricle has a far thicker muscular wall than Right Ventricle because it pumps blood round the ENTIRE body, not just to adjacent lungs!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Post-Sprint Oxygen Debt Repayment",
                        examSource = "WAEC SSCE 2022 • Paper 2 Q5",
                        problemStatement =
                          "Why does a 100 m sprinter keep panting heavily for minutes AFTER crossing the finish line?",
                        steps =
                          listOf(
                            "Step 1 — During explosive sprinting, muscles respired anaerobically forming toxic LACTIC ACID ('oxygen debt').",
                            "Step 2 — Deep fast breathing afterward supplies extra O₂ to transport lactic acid to the liver and oxidize/reconvert it into glucose and CO₂.",
                          ),
                        finalAnswer = "To supply O₂ to clear accumulated lactic acid in the liver",
                        examinerTrap = "Yeast produces ethanol; mammalian muscle produces LACTIC ACID — never mix them up!",
                      ),
                      WorkedExample(
                        title = "Potometer Transpiration Rate Factors",
                        examSource = "JAMB UTME 2023 • Biology Q21",
                        problemStatement =
                          "Under which environmental combination will the air bubble in a leafy shoot potometer travel FASTEST?",
                        steps =
                          listOf(
                            "Step 1 — Warm temperature & bright light open stomata and increase evaporation.",
                            "Step 2 — Dry (low humidity) + windy air sweeps away saturated water vapor shell around leaves.",
                          ),
                        finalAnswer = "High temperature, bright light, windy air, and LOW humidity",
                        examinerTrap = "HIGH humidity SLOWS transpiration down because air is already damp!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Highland Altitude Athlete Adaptation",
                        industryDomain = "Sports Medicine",
                        narrative =
                          "Long-distance runners training in Jos or Kenyan highlands stimulate kidneys to boost erythropoietin, packing extra red blood cells to maximize mitochondrial ATP output.",
                        examConnection = "Links hemoglobin oxygen transport to aerobic respiration.",
                      ),
                      RealLifeScenario(
                        title = "Palm Wine Yeast Fermentation & Bread Dough Rising",
                        industryDomain = "Food Fermentation",
                        narrative =
                          "Wild yeasts ferment palm sap anaerobically into ethanol and fizzing CO₂ bubbles — the exact same CO₂ gas makes baker's dough puff up in warm ovens!",
                        examConnection = "Standard WAEC anaerobic fermentation application.",
                      ),
                    ),
                  labType = PracticalLabType.GAS_LAW_PISTON,
                  labBrief =
                    "Observe how temperature and gas partial pressure control oxygen diffusion gradients in tissues and stomata!",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_05_1",
                        topic = "Circulation",
                        examType = "JAMB",
                        year = 2024,
                        question = "Which blood vessel carries oxygenated blood directly from the lungs to the left atrium?",
                        options = listOf("Pulmonary artery", "Pulmonary vein", "Vena cava", "Aorta"),
                        correctIndex = 1,
                        hint = "Veins return blood TO the heart; Pulmonary means lungs.",
                        stepByStepSolution = "Pulmonary veins return freshly oxygenated blood from lung alveoli into the left atrium.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_05_2",
                        topic = "Respiration",
                        examType = "WAEC",
                        year = 2023,
                        question = "Anaerobic respiration in fatigued human skeletal muscle produces:",
                        options = listOf("Ethanol and CO₂", "Lactic acid", "Urea", "Oxalic acid"),
                        correctIndex = 1,
                        hint = "Causes muscular soreness and creates an oxygen debt.",
                        stepByStepSolution = "Pyruvate accepts H⁺ from NADH forming lactic acid when O₂ delivery lags demand.",
                        waecMarkingScheme = "A1: Lactic acid.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_05_3",
                        topic = "Plant Transport",
                        examType = "JAMB",
                        year = 2022,
                        question = "Which environmental change DECREASES the rate of transpiration from plant leaves?",
                        options = listOf("Higher wind speed", "Higher light intensity", "Higher atmospheric humidity", "Higher temperature"),
                        correctIndex = 2,
                        hint = "Water evaporates slowly into damp air.",
                        stepByStepSolution = "High humidity narrows the water vapor diffusion gradient between spongy mesophyll air spaces and outside air.",
                      ),
                    ),
                ),
                Lesson(
                  id = "bio_06_excretion_homeostasis",
                  moduleId = "bio_mod_2",
                  subjectId = "biology",
                  title = "Excretion, Kidney Nephron Function & Homeostasis",
                  durationMinutes = 20,
                  syllabusTag = "JAMB Sec. B.6 • WAEC Excretion & Coordination",
                  learningOutcomes =
                    listOf(
                      "Trace Ultrafiltration at Bowman's capsule, Selective Reabsorption in PCT, and ADH osmoregulation in collecting ducts.",
                      "Explain liver deamination, Insulin vs Glucagon blood glucose control, and mammalian thermoregulation.",
                    ),
                  concepts =
                    listOf(
                      ConceptBlock(
                        heading = "Step 1: How the Kidney Nephron Filters Blood",
                        explanation =
                          "Excretion removes toxic METABOLIC wastes (CO₂ via lungs; Urea/excess salts via kidneys — note feces egestion is NOT excretion!). Inside 1 million NEPHRONS: (1) High afferent pressure squeezes small molecules (water, glucose, urea, salts) at Bowman's capsule (ULTRAFILTRATION — proteins/RBCs stay out!); (2) Proximal Convoluted Tubule reabsorbs 100% of GLUCOSE cleanly (SELECTIVE REABSORPTION); (3) Collecting duct adjusts water via ADH!",
                        keyFormula = "\\text{Dehydrated } \\Rightarrow \\text{Pituitary ADH}\\uparrow \\Rightarrow \\text{Permeable Ducts} \\Rightarrow \\text{Concentrated Urine}",
                        examTip =
                          "Finding GLUCOSE in urine signals Diabetes Mellitus; finding PROTEIN signals damaged glomeruli!",
                      ),
                      ConceptBlock(
                        heading = "Step 2: Homeostasis — Insulin, Glucagon & Skin Thermoregulation",
                        explanation =
                          "The Liver deaminates excess amino acids into toxic ammonia, quickly converted to safer UREA via the ornithine cycle. High blood sugar triggers Pancreas INSULIN (turns glucose -> stored liver glycogen). Low blood sugar triggers GLUCAGON (glycogen -> glucose). In heat, skin arterioles VASODILATE and sweat glands secrete sweat whose evaporation cools blood!",
                        keyFormula = "\\text{High Glucose} \\xrightarrow{\\text{Insulin}} \\text{Glycogen}   |   \\text{Low Glucose} \\xrightarrow{\\text{Glucagon}} \\text{Glucose}",
                        examTip =
                          "Arterioles VASODILATE (widen) near skin in hot weather, NOT 'capillaries expanding' — smooth muscle sits in arteriole walls!",
                      ),
                    ),
                  workedExamples =
                    listOf(
                      WorkedExample(
                        title = "Kidney Filtrate Composition Tracing",
                        examSource = "JAMB UTME 2024 • Biology Q24",
                        problemStatement =
                          "A healthy athlete's glomerular filtrate contains 0.1% glucose, 0.03% urea, and 0% albumin protein. What concentrations appear in final bladder urine?",
                        steps =
                          listOf(
                            "Step 1 — Protein (albumin) is too huge for Bowman's membrane -> 0% in filtrate AND 0% in urine.",
                            "Step 2 — 100% of filtered glucose is actively reabsorbed in PCT -> 0% in healthy urine.",
                            "Step 3 — Water is reabsorbed while urea stays behind -> Urea % rises sharply (~2.0%).",
                          ),
                        finalAnswer = "0% Glucose, 0% Protein, High (~2%) Urea",
                        examinerTrap = "Notice urea mass doesn't multiply magically — its CONCENTRATION rises because water gets reabsorbed!",
                      ),
                      WorkedExample(
                        title = "Negative Feedback During Dehydration",
                        examSource = "WAEC SSCE 2022 • Paper 1 Q27",
                        problemStatement =
                          "A hiker sweats heavily in dry harmattan air without drinking water. Trace the hormonal response.",
                        steps =
                          listOf(
                            "Step 1 — Hypothalamus osmoreceptors detect raised blood osmotic concentration.",
                            "Step 2 — Posterior pituitary releases MORE Antidiuretic Hormone (ADH).",
                            "Step 3 — Collecting ducts absorb more water back into blood -> small volume of dark concentrated urine.",
                          ),
                        finalAnswer = "High ADH -> More water reabsorbed -> Small concentrated urine",
                        examinerTrap = "Drinking lots of water INHIBITS ADH, producing large dilute urine!",
                      ),
                    ),
                  scenarios =
                    listOf(
                      RealLifeScenario(
                        title = "Kidney Dialysis Machines in Renal Failure",
                        industryDomain = "Biomedical Engineering",
                        narrative =
                          "Hemodialysis mimics the nephron by flowing patient blood past cellophane tubing bathed in glucose-balanced dialysate so urea diffuses out without stripping vital blood glucose or proteins.",
                        examConnection = "Applies selective diffusion and glomerular filtration.",
                      ),
                      RealLifeScenario(
                        title = "Nerve Reflex Arcs & Fight-or-Flight Adrenaline",
                        industryDomain = "Neuro-Endocrinology",
                        narrative =
                          "Touching a hot stove fires Receptor -> Sensory -> Relay (Spinal Cord) -> Motor -> Effector before pain even reaches the conscious brain, while Adrenaline spikes heartbeat and glycogen breakdown in emergencies.",
                        examConnection = "Connects reflex pathway order with hormonal glucose regulation.",
                      ),
                    ),
                  labType = PracticalLabType.TITRATION_PH,
                  labBrief =
                    "Use the Buffer & Concentration Bench to inspect how blood pH and solute molarity regulate homeostatic kidney reabsorption.",
                  exercises =
                    listOf(
                      PracticeQuestion(
                        id = "ex_bio_06_1",
                        topic = "Kidney & Excretion",
                        examType = "JAMB",
                        year = 2025,
                        question = "In which part of the mammalian nephron does ultrafiltration occur?",
                        options = listOf("Loop of Henle", "Bowman's capsule / Glomerulus", "Distal convoluted tubule", "Collecting duct"),
                        correctIndex = 1,
                        hint = "High pressure squeezes plasma out of the capillary knot.",
                        stepByStepSolution = "Ultrafiltration takes place between the glomerulus capillaries and Bowman's capsule.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_06_2",
                        topic = "Homeostasis",
                        examType = "WAEC",
                        year = 2023,
                        question = "Which hormone promotes conversion of excess blood glucose into glycogen inside the liver and muscles?",
                        options = listOf("Glucagon", "Thyroxine", "Insulin", "ADH"),
                        correctIndex = 2,
                        hint = "Produced by Beta cells of Islets of Langerhans; deficiency causes diabetes.",
                        stepByStepSolution = "Insulin lowers high blood glucose by triggering cellular uptake and glycogen synthesis.",
                        waecMarkingScheme = "A1: Insulin.",
                      ),
                      PracticeQuestion(
                        id = "ex_bio_06_3",
                        topic = "Nervous System",
                        examType = "JAMB",
                        year = 2022,
                        question = "Which part of the human brain coordinates body posture, balance, and smooth muscular movement?",
                        options = listOf("Medulla oblongata", "Cerebellum", "Hypothalamus", "Olfactory lobe"),
                        correctIndex = 1,
                        hint = "Medulla handles breathing/heartbeat; Cerebellum handles balance.",
                        stepByStepSolution = "The cerebellum coordinates muscular tone, posture, and equilibrium.",
                      ),
                    ),
                ),
              ),
          ),
        ) + BiologyCurriculumPart2.modules,
      pastQuestions = BiologyCurriculumPart2.pastQuestions,
    )
}
