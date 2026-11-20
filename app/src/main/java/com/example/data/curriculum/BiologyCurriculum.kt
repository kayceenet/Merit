package com.example.data.curriculum

object BiologyCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "biology",
      title = "Biology Specialization",
      shortName = "Biology",
      subtitle = "15 Zero-to-A1 lessons covering Cell Biology, Enzymes, Circulation, Excretion, Genetics, Evolution & Ecology",
      modules = module1And2 + BiologyCurriculumPart2.module3And4 + listOf(BiologyCurriculumPart3.module5),
      pastQuestions = BiologyCurriculumPart3.pastQuestions,
    )
  }

  val module1And2: List<CourseModule> =
    listOf(
      CourseModule(
        id = "bio_mod_1",
        subjectId = "biology",
        indexLabel = "Module 01",
        title = "Diversity of Life, Cell Ultrastructure & Bioenergetics",
        summary = "Start from zero: MRS NERG-D life rules, 5 Kingdoms, Microscopy, Organelles, Osmosis/Plasmolysis, Photosynthesis & Cellular Respiration.",
        lessons =
          listOf(
            Lesson(
              id = "bio_01_diversity_microscopy",
              moduleId = "bio_mod_1",
              subjectId = "biology",
              title = "Characteristics of Life, 5 Kingdoms & Microscopy",
              durationMinutes = 18,
              syllabusTag = "JAMB Sec. A1–A2 • WAEC Living Things & Classification",
              learningOutcomes =
                listOf(
                  "Identify all 8 life processes (MR NIGER D) and explain why Viruses sit on the living/non-living fence.",
                  "Classify organisms across Whittaker's 5 Kingdoms (Monera, Protista, Fungi, Plantae, Animalia).",
                  "Calculate Microscope Total Magnification and convert mm ↔ μm (× 1,000).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "MR NIGER D + Why Viruses Confuse Scientists",
                    explanation =
                      "How do we know a goat is alive while a stone isn't? Every living thing checks MR NIGER D: Movement, Respiration (making energy—NOT just breathing!), Nutrition, Irritability (sensing), Growth, Excretion (dumping metabolic waste—NOT pooping undigested food, which is Egestion!), Reproduction, and Death. Outside a host cell, a Virus acts like dead crystal dust; inside your lung cell, it hijacks machinery to copy itself!",
                    keyFormula = "\\text{Taxonomic Hierarchy: Kingdom} \\to \\text{Phylum} \\to \\text{Class} \\to \\text{Order} \\to \\text{Family} \\to \\text{Genus} \\to \\text{Species}",
                    examTip =
                      "Binomial Naming rule: Genus starts CAPITALIZED, species all lowercase, BOTH italicized or underlined separately (e.g., Homo sapiens)!",
                  ),
                  ConceptBlock(
                    heading = "The 5 Kingdoms & Microscope Math",
                    explanation =
                      "Only ONE kingdom lacks a true nucleus (Prokaryotes): Monera (Bacteria)! Single-celled Eukaryotes with a nucleus (Amoeba, Euglena, Plasmodium) go in Protista. Fungi (Mushrooms, Rhizopus, Yeast) look plant-like but have NO chlorophyll and have Chitin walls (not Cellulose!). On a light microscope, Eyepiece × Objective = Total Magnification!",
                    keyFormula = "\\text{Total Mag} = M_{\\text{eyepiece}} \\times M_{\\text{objective}}   |   M = \\frac{\\text{Drawing Size}}{\\text{Actual Size}}",
                    examTip =
                      "1 millimetre (mm) = 1,000 micrometres (μm). Never mix mm and μm when dividing Drawing Size ÷ Actual Size!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Calculating Microscope Drawing Magnification",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "An Amoeba actually measures 200 μm across. A student draws it 6 cm wide in her notebook. Find the magnification.",
                    steps =
                      listOf(
                        "Step 1 — Convert 6 cm to μm: 6 cm = 60 mm = 60,000 μm.",
                        "Step 2 — Magnification = Image / Actual = 60,000 / 200 = ×300.",
                      ),
                    finalAnswer = "×300",
                    examinerTrap = "Always write a cross '×' before magnification (×300), never units like cm!",
                  ),
                  WorkedExample(
                    title = "Euglena: Half-Plant, Half-Animal Protist",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Why do botanists AND zoologists both claim Euglena viridis?",
                    steps =
                      listOf(
                        "Step 1 — Plant features: possesses Chloroplasts for photosynthesis and pyrenoids for starch storage.",
                        "Step 2 — Animal features: lacks cell wall (flexible pellicle), moves via Flagellum, detects light with Eye-spot (stigma), uses contractile vacuole.",
                      ),
                    finalAnswer = "Protista bridging Autotroph & Heterotroph traits",
                    examinerTrap = "Remember Euglena belongs in Kingdom Protista, NOT Plantae!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Identifying Bacteria vs Fungi Infections in Hospitals",
                    industryDomain = "Clinical Microbiology",
                    narrative =
                      "Antibiotics target bacterial peptidoglycan cell walls (Kingdom Monera), which leaves human animal cells untouched—and explains why antibiotics fail completely against viral flu or fungal ringworm!",
                    examConnection = "Bacteria cell wall = Peptidoglycan; Fungi = Chitin; Plants = Cellulose.",
                  ),
                ),
              labType = PracticalLabType.CELL_OSMOSIS_LAB,
              labBrief =
                "Adjust Eyepiece × Objective lenses and measure specimen magnification cleanly from mm down to μm!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_01_1",
                    topic = "5 Kingdoms",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which kingdom consists exclusively of Prokaryotic organisms lacking a membrane-bound nucleus?",
                    options = listOf("Protista", "Monera", "Fungi", "Plantae"),
                    correctIndex = 1,
                    hint = "Bacteria and Blue-green algae.",
                    stepByStepSolution = "Kingdom Monera contains prokaryotic bacteria lacking nuclear membranes and mitochondria.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_01_2",
                    topic = "Fungi Cell Wall",
                    examType = "WAEC",
                    year = 2023,
                    question = "Unlike green plants, fungal cell walls are strengthened primarily by:",
                    options = listOf("Cellulose", "Lignin", "Chitin", "Peptidoglycan"),
                    correctIndex = 2,
                    hint = "Also found in insect exoskeletons!",
                    stepByStepSolution = "Fungi have chitinous cell walls and store glycogen, not starch.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_01_3",
                    topic = "Excretion vs Egestion",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which activity represents TRUE Excretion rather than Egestion?",
                    options = listOf("Passing out faeces", "Removing urea in urine and CO₂ from lungs", "Spitting saliva", "Shedding nails"),
                    correctIndex = 1,
                    hint = "Excretion removes metabolic waste made INSIDE cells.",
                    stepByStepSolution = "Urea and CO₂ are metabolic wastes filtered out by kidneys and lungs.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_01_4",
                    topic = "Magnification",
                    examType = "WAEC",
                    year = 2022,
                    question = "A ×10 eyepiece paired with a ×40 high-power objective gives what total magnification?",
                    options = listOf("×50", "×400", "×4", "×1000"),
                    correctIndex = 1,
                    hint = "Multiply the two lens powers.",
                    stepByStepSolution = "10 × 40 = ×400.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_01_5",
                    topic = "Taxonomy",
                    examType = "JAMB",
                    year = 2021,
                    question = "Two organisms belong to the SAME species only if they can:",
                    options = listOf("Live in the same habitat", "Eat identical food", "Interbreed naturally to produce fertile offspring", "Share similar fur colour"),
                    correctIndex = 2,
                    hint = "A horse and donkey make a sterile mule → different species.",
                    stepByStepSolution = "Biological species concept requires fertile offspring.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_01_6",
                    topic = "Viruses",
                    examType = "NECO",
                    year = 2024,
                    question = "Why are Viruses considered living organisms ONLY when inside a host cell?",
                    options = listOf("They photosynthesize there", "They possess organelles", "They replicate using the host's ribosomes & ATP", "They breathe oxygen"),
                    correctIndex = 2,
                    hint = "Outside hosts they exist as inert nucleic-acid + protein crystals.",
                    stepByStepSolution = "Viruses replicate genetic material inside living host cells.",
                  ),
                ),
            ),

            Lesson(
              id = "bio_02_cells_osmosis",
              moduleId = "bio_mod_1",
              subjectId = "biology",
              title = "Cell Organelles, Diffusion, Osmosis & Plasmolysis",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. A3 • WAEC Cell Structure & Water Relations",
              learningOutcomes =
                listOf(
                  "Compare Plant vs Animal cells (Cell Wall, Large Central Vacuole, Chloroplasts vs Centrioles).",
                  "Match Mitochondrion, Ribosome, Golgi Body, Lysosome, and Nucleus to exact functions.",
                  "Predict Flaccidity/Plasmolysis vs Turgidity & Haemolysis across Hypotonic, Isotonic & Hypertonic baths.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Inside the Microscopic City (Organelle Jobs)",
                    explanation =
                      "Every cell is a miniature factory city! Nucleus = City Hall storing DNA blueprints. Ribosomes = tiny 3D Printers building Proteins! Smooth/Rough ER = Conveyor Highways. Golgi Body = Post Office packaging proteins into vesicles. Mitochondrion = Power Plant burning glucose into ATP energy. Lysosome = Recycling Wrecking Crew!",
                    keyFormula = "\\text{Plant ONLY: Cell Wall + Chloroplasts + Large Vacuole}   |   \\text{Animal ONLY: Centrioles + Lysosomes}",
                    examTip =
                      "Muscle cells and sperm tails are PACKED with Mitochondria because they burn massive ATP for movement!",
                  ),
                  ConceptBlock(
                    heading = "Osmosis: Water Always Chases the Saltier / Sweeter Crowd!",
                    explanation =
                      "Diffusion moves ink or perfume from High → Low concentration. Osmosis is ONLY for WATER crossing a semi-permeable gate—from DILUTE (lots of water, weak salt = Hypotonic) into CONCENTRATED (less free water, high salt/sugar = Hypertonic)! Drop a Red Blood Cell into pure water: water rushes IN until it POPS (Haemolysis)! Put a potato strip in strong salt: water rushes OUT so cytoplasm shrinks away from the stiff wall (Plasmolysis)!",
                    keyFormula = "\\text{Hypotonic Bath} \\implies \\text{Plant Turgid / Animal Bursts}   |   \\text{Hypertonic Bath} \\implies \\text{Plant Plasmolyzed / Animal Crenated}",
                    examTip =
                      "Plant cells DON'T burst in distilled water because their tough cellulose Cell Wall pushes back with Turgor Pressure!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Potato Strip Weight Change in Sugar Solutions",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "Fresh potato cylinders of mass 5.0 g are placed in Beaker A (distilled water) and Beaker B (20% sucrose) for 1 hour. Predict mass and feel.",
                    steps =
                      listOf(
                        "Step 1 — Beaker A is hypotonic to cell sap: water enters by osmosis ⇒ mass INCREASES (> 5.0 g), strip feels stiff/turgid.",
                        "Step 2 — Beaker B is hypertonic to cell sap: water exits by osmosis ⇒ mass DECREASES (< 5.0 g), strip feels limp/flaccid (plasmolyzed).",
                      ),
                    finalAnswer = "A: Heavier & Turgid  |  B: Lighter & Flaccid",
                    examinerTrap = "Sugar molecules don't cross the selectively permeable membrane—WATER moves toward the higher sucrose side!",
                  ),
                  WorkedExample(
                    title = "Amoeba Contractile Vacuole in Sea Water",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "Why do freshwater Amoebae pump contractile vacuoles furiously whereas marine Amoebae rarely need one?",
                    steps =
                      listOf(
                        "Step 1 — Freshwater is hypotonic, so water floods into freshwater Amoeba constantly by osmosis.",
                        "Step 2 — Marine seawater is nearly isotonic to Amoeba cytoplasm—net water inflow is negligible!",
                      ),
                    finalAnswer = "Freshwater Amoeba faces continuous osmotic water influx (osmoregulation)",
                    examinerTrap = "Contractile vacuole handles OSMOREGULATION (water balance), NOT urea nitrogen excretion!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Why Hospitals Infuse 0.9% Normal Saline Instead of Pure Water",
                    industryDomain = "Emergency Medicine",
                    narrative =
                      "Injecting pure distilled water directly into an IV vein would hypotonically burst millions of red blood cells (haemolysis)! Doctors use isotonic 0.9% NaCl saline.",
                    examConnection = "Tests exact osmotic equilibrium between plasma and RBCs.",
                  ),
                ),
              labType = PracticalLabType.CELL_OSMOSIS_LAB,
              labBrief =
                "Place Plant and Red Blood Cells into Hypotonic, Isotonic, and Hypertonic baths to observe Turgidity, Plasmolysis, and Haemolysis live!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_02_1",
                    topic = "Cell Organelles",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which organelle is directly responsible for assembling amino acids into proteins?",
                    options = listOf("Lysosome", "Ribosome", "Golgi apparatus", "Nucleolus"),
                    correctIndex = 1,
                    hint = "Free in cytoplasm or studding Rough ER.",
                    stepByStepSolution = "Ribosomes translate mRNA into polypeptide chains.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_02_2",
                    topic = "Plasmolysis",
                    examType = "WAEC",
                    year = 2023,
                    question = "When an onion epidermal cell is soaked in concentrated brine, the cell membrane pulls away from the cell wall. This is called:",
                    options = listOf("Haemolysis", "Turgidity", "Plasmolysis", "Pinocytosis"),
                    correctIndex = 2,
                    hint = "Hypertonic water loss shrinks the protoplast.",
                    stepByStepSolution = "Outward osmosis shrinks the vacuole and detaches plasma membrane → Plasmolysis.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_02_3",
                    topic = "Active Transport",
                    examType = "JAMB",
                    year = 2025,
                    question = "Root hair cells absorb nitrate ions from very dilute soil water into concentrated root sap by:",
                    options = listOf("Osmosis", "Simple diffusion", "Active transport using ATP", "Pinocytosis"),
                    correctIndex = 2,
                    hint = "Pumping UP a concentration gradient costs cellular energy!",
                    stepByStepSolution = "Moving mineral ions from low → high concentration requires Active Transport (ATP).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_02_4",
                    topic = "Haemolysis",
                    examType = "WAEC",
                    year = 2022,
                    question = "A red blood cell placed in distilled water will:",
                    options = listOf("Shrink and crinkle", "Stay unchanged", "Swell and burst (haemolyze)", "Turn green"),
                    correctIndex = 2,
                    hint = "Animal cells have NO rigid cellulose cell wall to resist bursting.",
                    stepByStepSolution = "Endosmosis inflates the RBC past membrane strength → Haemolysis.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_02_5",
                    topic = "Powerhouse Organelle",
                    examType = "JAMB",
                    year = 2021,
                    question = "In which organelle does the Krebs cycle and oxidative phosphorylation (aerobic ATP synthesis) take place?",
                    options = listOf("Chloroplast", "Mitochondrion", "Peroxisome", "Vacuole"),
                    correctIndex = 1,
                    hint = "Folded inner cristae maximize ATP production.",
                    stepByStepSolution = "Mitochondria generate the bulk of cellular ATP aerobically.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_02_6",
                    topic = "Cell Theory",
                    examType = "NECO",
                    year = 2024,
                    question = "Which scientist coined the term 'cell' after observing honeycomb pores in thin cork slices (1665)?",
                    options = listOf("Robert Hooke", "Matthias Schleiden", "Theodor Schwann", "Rudolf Virchow"),
                    correctIndex = 0,
                    hint = "Hooke published Micrographia in 1665.",
                    stepByStepSolution = "Robert Hooke named plant cork chambers 'cells'.",
                  ),
                ),
            ),

            Lesson(
              id = "bio_03_photosynthesis_respiration_enzymes",
              moduleId = "bio_mod_1",
              subjectId = "biology",
              title = "Enzymes, Photosynthesis & Cellular Respiration",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. B1–B2 • WAEC Plant/Cell Bioenergetics & Enzymes",
              learningOutcomes =
                listOf(
                  "Explain Lock-and-Key Enzyme catalysis, Optimum Temp/pH, and Denaturation (> 45°C).",
                  "Contrast Light-Dependent Stage (photolysis splitting H₂O into O₂!) vs Dark/Calvin Stage (CO₂ → Glucose).",
                  "Compare Aerobic (38 ATP) vs Anaerobic Fermentation (2 ATP: Lactic Acid in muscles vs Ethanol in yeast).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Enzymes: Fussy Biological Helper Keys",
                    explanation =
                      "Enzymes are protein speed-boosters shaped like tiny locks matching only ONE substrate key! Speed rises with warmth up to an Optimum (~37–40°C in humans)—push past 50°C and the protein LOCK MELTS out of shape permanently (DENATURED!). Notice pH tastes too: Stomach Pepsin loves strong acid (pH 2), Salivary Amylase likes neutral (pH 7), Intestinal Trypsin likes alkaline (pH 8.5)!",
                    keyFormula = "E + S \\rightleftharpoons ES \\to E + P   |   \\text{Temp } > 50^\\circ\\text{C} \\implies \\text{Active Site Denatured}",
                    examTip =
                      "Careful: freezing cold (~0°C) only INACTIVATES enzymes (puts them to sleep—warm back up and they work!); boiling DENATURES them forever!",
                  ),
                  ConceptBlock(
                    heading = "Photosynthesis & Aerobic vs Anaerobic Energy",
                    explanation =
                      "Chloroplasts run solar power in 2 shifts: Day Shift (Light Reaction inside Grana) uses sunlight to split water (Photolysis) releasing O₂ bubbles + ATP/NADPH! Night-capable Shift (Calvin Cycle in Stroma) uses that ATP to stitch CO₂ into Glucose! Meanwhile, ALL living cells burn Glucose 24/7: With Oxygen → 38 ATP; Without Oxygen → only 2 ATP (Muscles make painful Lactic Acid → Oxygen Debt; Yeast makes Ethanol + CO₂ for bread rising!).",
                    keyFormula = "6\\text{CO}_2 + 6\\text{H}_2\\text{O} \\xrightarrow{\\text{Light/Chl}} \\text{C}_6\\text{H}_{12}\\text{O}_6 + 6\\text{O}_2   |   \\text{Yeast: } \\text{C}_6\\text{H}_{12}\\text{O}_6 \\to 2\\text{C}_2\\text{H}_5\\text{OH} + 2\\text{CO}_2 + 2\\text{ATP}",
                    examTip =
                      "During Starch Leaf Testing: (1) Boil leaf in water to kill cells, (2) Boil in ALCOHOL (water-bath!) to bleach green chlorophyll, (3) Rinse warm water, (4) Add Iodine → Blue-Black!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Source of Oxygen Bubbles in Photosynthesis",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement =
                      "Using heavy ¹⁸O isotope labels, scientists prove that ALL oxygen gas released during photosynthesis comes from:",
                    steps =
                      listOf(
                        "Step 1 — In the Light Reaction (Grana/Thylakoids), chlorophyll energy splits Water: 2H₂O → 4H⁺ + 4e⁻ + O₂↑.",
                        "Step 2 — CO₂ oxygen atoms end up inside glucose C₆H₁₂O₆ and H₂O instead.",
                      ),
                    finalAnswer = "Photolysis of Water (H₂O), NOT CO₂",
                    examinerTrap = "Assuming O₂ bubbles come from stripping O₂ out of CO₂!",
                  ),
                  WorkedExample(
                    title = "Anaerobic Sprinting & Oxygen Debt",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement = "Why does a 100 m sprinter pant heavily AFTER finishing the race?",
                    steps =
                      listOf(
                        "Step 1 — During explosive sprinting, muscles switch partially to anaerobic respiration producing Lactic Acid (2 ATP/glucose).",
                        "Step 2 — Panting repays the 'Oxygen Debt' so the liver can oxidize accumulated lactic acid back into harmless CO₂/H₂O or glycogen.",
                      ),
                    finalAnswer = "Repays Oxygen Debt to clear Lactic Acid in the liver",
                    examinerTrap = "Muscles NEVER produce Ethanol during anaerobic respiration—only Yeast & Plants do!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "High Fever Danger & Greenhouse CO₂ Enriching",
                    industryDomain = "Medicine & Agronomy",
                    narrative =
                      "A fever above 41°C becomes fatal because brain enzymes begin denaturing; meanwhile greenhouse growers pump extra CO₂ to eliminate the carbon limiting factor in tomatoes.",
                    examConnection = "Limiting factors of photosynthesis: Light intensity, CO₂ %, Temperature.",
                  ),
                ),
              labType = PracticalLabType.BIOENERGETICS_ENZYME_LAB,
              labBrief =
                "Adjust Enzyme Temperature & pH sliders to spot optimum peaks vs denaturation collapse, or vary Light/CO₂ to track Oxygen bubble rate!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_03_1",
                    topic = "Leaf Starch Test",
                    examType = "JAMB",
                    year = 2024,
                    question = "In testing a green leaf for starch, why is the leaf boiled in warm ethanol using a water bath?",
                    options = listOf("Soften leaf", "Dissolve out chlorophyll cleanly & prevent ethanol fire", "Burst starch grains", "Kill bacteria"),
                    correctIndex = 1,
                    hint = "Ethanol is flammable and masks iodine's blue-black colour with green chlorophyll.",
                    stepByStepSolution = "Hot alcohol decolorizes the leaf safely over a water bath so iodine blue-black shows clearly.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_03_2",
                    topic = "Enzyme Specificity",
                    examType = "WAEC",
                    year = 2023,
                    question = "Stomach Pepsin stops working once food enters the alkaline duodenum primarily because:",
                    options = listOf("Starch blocks it", "High pH (> 7) denatures Pepsin's active site", "Temperature drops", "Bile digests pepsin"),
                    correctIndex = 1,
                    hint = "Pepsin's optimum is acidic pH ~2.",
                    stepByStepSolution = "Alkaline pancreatic/bile juice alters ionization and denatures acidic pepsin.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_03_3",
                    topic = "Photolysis Origin",
                    examType = "JAMB",
                    year = 2025,
                    question = "The oxygen gas given off by aquatic Elodea plants under bright light comes directly from:",
                    options = listOf("Carbon dioxide", "Water molecules split by light", "Soil nitrates", "Glucose breakdown"),
                    correctIndex = 1,
                    hint = "Photolysis of H₂O in PS II.",
                    stepByStepSolution = "Light energy splits water during the light-dependent stage.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_03_4",
                    topic = "Dark Reaction Site",
                    examType = "WAEC",
                    year = 2022,
                    question = "Where inside the chloroplast does the Light-Independent (Calvin Cycle) sugar fixation occur?",
                    options = listOf("Thylakoid lumen", "Grana stacks", "Stroma fluid", "Cristae"),
                    correctIndex = 2,
                    hint = "Light stage = Grana; Dark stage = Stroma.",
                    stepByStepSolution = "Enzymes (RuBisCO) fixing CO₂ reside in the chloroplast stroma.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_03_5",
                    topic = "Anaerobic Yeast",
                    examType = "JAMB",
                    year = 2021,
                    question = "In dough baking, yeast causes bread to rise because anaerobic fermentation releases bubbles of:",
                    options = listOf("Oxygen", "Methane", "Carbon(IV) oxide (CO₂)", "Hydrogen"),
                    correctIndex = 2,
                    hint = "Glucose → Ethanol + CO₂.",
                    stepByStepSolution = "Trapped CO₂ gas pockets puff up dough before baking evaporates the alcohol.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_03_6",
                    topic = "Compensation Point",
                    examType = "NECO",
                    year = 2024,
                    question = "At the Light Compensation Point (dawn/dusk), a green leaf shows zero net gas exchange because:",
                    options = listOf("Stomata are frozen", "Rate of Photosynthesis equals Rate of Respiration", "Chlorophyll breaks down", "Enzyme stops"),
                    correctIndex = 1,
                    hint = "O₂ produced = O₂ consumed; CO₂ produced = CO₂ fixed.",
                    stepByStepSolution = "Photosynthesis and respiration cancel each other out exactly at the compensation point.",
                  ),
                ),
            ),
          ),
      ),

      CourseModule(
        id = "bio_mod_2",
        subjectId = "biology",
        indexLabel = "Module 02",
        title = "Nutrition, Transport, Respiration & Excretion",
        summary = "Master Food Tests, Mammalian Digestion & Teeth, Heart Double Circulation, Xylem/Phloem, Lung Mechanics & Kidney Nephron/Homeostasis.",
        lessons =
          listOf(
            Lesson(
              id = "bio_04_nutrition_digestion",
              moduleId = "bio_mod_2",
              subjectId = "biology",
              title = "Food Classes, Biochemical Tests & Mammalian Digestion",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. B3 • WAEC Nutrition, Dentition & Digestion",
              learningOutcomes =
                listOf(
                  "Perform & interpret Starch, Reducing Sugar (Benedict's), Non-reducing Sugar, Protein (Biuret/Millon) & Fat tests.",
                  "Identify Fat-Soluble (A, D, E, K) vs Water-Soluble (B, C) Vitamins and Mineral Deficiency diseases.",
                  "Trace Digestion enzymes across Mouth (Ptyalin), Stomach (Pepsin/Rennin), Duodenum (Bile + Pancreatic juice) & Ileum Villi.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "The 5 Classic WAEC Food Tests",
                    explanation =
                      "Spot nutrients by colour! Starch + Iodine = BLUE-BLACK! Reducing Sugar (Glucose/Maltose) + Benedict's + Heat = BRICK-RED ppt! (Sucrose stays blue until boiled with dil. HCl first—Non-reducing sugar!). Protein + Biuret (CuSO₄ + NaOH) = VIOLET / PURPLE! Fats + Sudan III = RED oil droplets (or translucent greasy spot on paper)!",
                    keyFormula = "\\text{Starch} \\xrightarrow{\\text{Amylase}} \\text{Maltose} \\xrightarrow{\\text{Maltase}} \\text{Glucose}   |   \\text{Protein} \\xrightarrow{\\text{Pepsin/Trypsin}} \\text{Peptides} \\xrightarrow{\\text{Erepsin}} \\text{Amino Acids}",
                    examTip =
                      "Does Bile contain ANY digestive enzymes? NO! Bile contains ONLY bile salts that EMULSIFY fat blobs into tiny droplets + NaHCO₃ to neutralize stomach acid!",
                  ),
                  ConceptBlock(
                    heading = "Teeth, Villi Absorption & Deficiencies",
                    explanation =
                      "Human adult Dental Formula (half jaw): I 2/2, C 1/1, PM 2/2, M 3/3 = 32 teeth (Herbivores leave a toothless Diastema gap instead of Canines!). Inside the Ileum, millions of finger-like Villi maximize absorption: Glucose & Amino acids enter Blood Capillaries → Hepatic Portal Vein to Liver, whereas Fatty Acids + Glycerol enter milky LACTEALS (lymph)!",
                    keyFormula = "\\text{Deficiencies: Vit A}\\to\\text{Night Blindness}   |   \\text{Vit C}\\to\\text{Scurvy}   |   \\text{Vit D}\\to\\text{Rickets}   |   \\text{Iodine}\\to\\text{Goitre}",
                    examTip =
                      "Kwashiorkor (swollen belly, reddish hair) = Protein deficiency; Marasmus = total Energy/Calorie + Protein starvation!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Testing Unknown Food Juice for Sucrose",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "Solution X stays blue when boiled with Benedict's solution. When boiled with dilute HCl, cooled, neutralized with NaHCO₃, and re-boiled with Benedict's, it turns brick-red. Identify X.",
                    steps =
                      listOf(
                        "Step 1 — Initial negative Benedict's rules out reducing sugars (glucose/fructose/maltose).",
                        "Step 2 — Acid hydrolysis splits sucrose into reducing monomers (glucose + fructose), turning Benedict's brick-red after neutralizing.",
                      ),
                    finalAnswer = "Non-reducing sugar (Sucrose)",
                    examinerTrap = "Forgetting to neutralize HCl with NaHCO₃ before adding Benedict's reagent stops the alkaline Cu²⁺ colour test!",
                  ),
                  WorkedExample(
                    title = "Carnivore vs Herbivore Dental Formula",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Which tooth adaptations specifically distinguish a carnivore (dog) from a herbivore (rabbit)?",
                    steps =
                      listOf(
                        "Step 1 — Carnivore: long sharp curved Canines for tearing + jagged Carnassial teeth (last upper premolar & first lower molar) for shearing meat/bone.",
                        "Step 2 — Herbivore: zero Canines (Diastema gap for tongue manipulation) + flat ridged grinding molars that grow continuously.",
                      ),
                    finalAnswer = "Carnassial slicing teeth & prominent canines vs Diastema gap",
                    examinerTrap = "Don't forget herbivores use symbiotic bacteria/protozoa in the Caecum/Rumen to make Cellulase!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Gallstones Blocking Bile Flow & Lactose Intolerance",
                    industryDomain = "Gastroenterology",
                    narrative =
                      "Blocking the bile duct stops fat emulsification causing pale greasy stools, while adults lacking Lactase enzyme experience bloating because gut bacteria ferment milk lactose.",
                    examConnection = "Tests roles of Bile vs Pancreatic Lipase & Brush-border disaccharidases.",
                  ),
                ),
              labType = PracticalLabType.NUTRITION_TRANSPORT_LAB,
              labBrief =
                "Drop Starch, Glucose, Sucrose, Egg Albumin & Oil into test tubes with Iodine, Benedict's & Biuret reagents to observe authentic colour reactions!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_04_1",
                    topic = "Food Tests",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which reagent turns violet/purple in the presence of protein?",
                    options = listOf("Iodine solution", "Fehling's reagent", "Biuret reagent (NaOH + CuSO₄)", "Sudan III"),
                    correctIndex = 2,
                    hint = "Millon's gives red; Biuret gives violet/purple.",
                    stepByStepSolution = "Cu²⁺ complexes with peptide bonds in alkaline Biuret reagent turning solution violet.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_04_2",
                    topic = "Bile Role",
                    examType = "WAEC",
                    year = 2023,
                    question = "Which statement correctly describes the digestive action of Bile?",
                    options = listOf("Contains lipase", "Digests starch to maltose", "Emulsifies large fat globules into droplets without enzymes", "Coagulates milk protein"),
                    correctIndex = 2,
                    hint = "Bile increases fat surface area for pancreatic lipase.",
                    stepByStepSolution = "Bile physically emulsifies lipids and alkalizes acidic chyme.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_04_3",
                    topic = "Ileum Absorption",
                    examType = "JAMB",
                    year = 2025,
                    question = "Fatty acids and glycerol absorbed in the ileum enter directly into the:",
                    options = listOf("Hepatic portal vein", "Lacteal of the villus", "Pulmonary vein", "Renal artery"),
                    correctIndex = 1,
                    hint = "Lymphatic vessel running down the center of each villus.",
                    stepByStepSolution = "Lipids re-form chylomicrons and enter the lacteal before joining lymph circulation.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_04_4",
                    topic = "Vitamin Deficiency",
                    examType = "WAEC",
                    year = 2022,
                    question = "Deficiency of Vitamin K directly causes:",
                    options = listOf("Rickets", "Beri-beri", "Impaired blood clotting (haemorrhage)", "Scurvy"),
                    correctIndex = 2,
                    hint = "Vitamin K enables hepatic synthesis of prothrombin.",
                    stepByStepSolution = "Lack of Vitamin K prevents fibrinogen clotting cascade.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_04_5",
                    topic = "Stomach Enzymes",
                    examType = "JAMB",
                    year = 2021,
                    question = "In young mammals, which gastric enzyme curdles soluble milk caseinogen into insoluble casein?",
                    options = listOf("Pepsin", "Rennin", "Trypsin", "Ptyalin"),
                    correctIndex = 1,
                    hint = "Slows milk flow so pepsin has time to digest it.",
                    stepByStepSolution = "Rennin (chymosin) coagulates milk protein in infants.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_04_6",
                    topic = "Symbiotic Root Nodules",
                    examType = "NECO",
                    year = 2024,
                    question = "The relationship between Rhizobium bacteria inside cowpea root nodules and the legume plant is:",
                    options = listOf("Parasitism", "Commensalism", "Mutualism (Symbiosis)", "Saprophytism"),
                    correctIndex = 2,
                    hint = "Bacteria fix N₂ for plant; plant shares sugars with bacteria.",
                    stepByStepSolution = "Both partners benefit mutually.",
                  ),
                ),
            ),

            Lesson(
              id = "bio_05_transport_circulation",
              moduleId = "bio_mod_2",
              subjectId = "biology",
              title = "Heart Double Circulation, Blood & Plant Vascular Transport",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. B4 • WAEC Transport in Plants & Animals",
              learningOutcomes =
                listOf(
                  "Trace mammalian Double Circulation accurately through Right Atrium (RA) → RV → Lungs → LA → Left Ventricle (LV) → Aorta.",
                  "Compare Arteries (thick, elastic, no valves except semilunar) vs Veins (valves, wide lumen) vs Capillaries.",
                  "Explain Transpiration Pull (Xylem upward) vs Translocation (Phloem sieve tubes 2-way).",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Double Circulation: Why the Left Ventricle Has Triple Muscle!",
                    explanation =
                      "Deoxygenated blood returns from your body via the Vena Cava into the RIGHT ATRIUM (RA) → pushes through Tricuspid Valve into RIGHT VENTRICLE (RV) → pumps out Pulmonary ARTERY to the Lungs to grab O₂! Fresh red blood returns via Pulmonary VEIN into LEFT ATRIUM (LA) → passes Bicuspid (Mitral) Valve into super-muscular LEFT VENTRICLE (LV), which squeezes hard enough to blast blood all the way around your whole body through the Aorta!",
                    keyFormula = "\\text{Body} \\xrightarrow{\\text{Vena Cava}} \\text{RA} \\to \\text{RV} \\xrightarrow{\\text{Pulm. Artery}} \\text{Lungs} \\xrightarrow{\\text{Pulm. Vein}} \\text{LA} \\to \\text{LV} \\xrightarrow{\\text{Aorta}} \\text{Body}",
                    examTip =
                      "GOLDEN EXCEPTION: Arteries carry blood AWAY from the heart (oxygenated EXCEPT Pulmonary Artery & Umbilical Artery!); Veins carry blood TOWARD the heart (deoxygenated EXCEPT Pulmonary Vein!)!",
                  ),
                  ConceptBlock(
                    heading = "Plant Vascular Superhighways: Dead Xylem vs Living Phloem",
                    explanation =
                      "Trees need no heart! Sunlight evaporates water off leaves through Stomata (Transpiration), sucking a continuous water chain UP hollow dead Lignified Xylem vessels (Cohesion-Tension + Root Pressure)! Meanwhile, living Phloem Sieve Tubes (helped by nucleated Companion Cells) pump sucrose sugars UP AND DOWN to fruits, growing buds, and underground roots (Translocation)!",
                    keyFormula = "\\text{High Wind + Low Humidity + Warmth} \\implies \\text{Transpiration Rate} \\uparrow   |   \\text{Phloem Ringing} \\implies \\text{Sugar Swells Above Ring}",
                    examTip =
                      "Blood Type AB = Universal Recipient (no A/B antibodies); Type O = Universal Donor (no A/B antigens on RBCs)!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Bark Ringing Experiment (Girdling)",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "A ring of outer bark containing Phloem is peeled off a mango branch while central Xylem stays intact. What happens after 3 weeks?",
                    steps =
                      listOf(
                        "Step 1 — Water continues moving UP central Xylem, so leaves stay green and crisp!",
                        "Step 2 — Sugars flowing down from leaves get blocked above the cut, forming a swollen callus bulge ABOVE the ring while lower roots/stem starve.",
                      ),
                    finalAnswer = "Swelling above the ring; leaves stay turgid initially",
                    examinerTrap = "Leaves do NOT wilt immediately when only phloem is cut—xylem carries water!",
                  ),
                  WorkedExample(
                    title = "Renal vs Hepatic Portal Blood Composition",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Which blood vessel contains the LEAST urea in the entire human body?",
                    steps =
                      listOf(
                        "Step 1 — The Kidney strips urea waste out of incoming blood.",
                        "Step 2 — Therefore clean blood leaving via the RENAL VEIN has minimum urea (while Hepatic Vein leaving the liver has the MOST urea!).",
                      ),
                    finalAnswer = "Renal Vein (lowest urea)  |  Hepatic Vein (highest urea)",
                    examinerTrap = "Mixing up Renal Artery (dirty urea-rich) with Renal Vein (cleaned)!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Potometer Hydration Measurement & Pacemaker SA Node",
                    industryDomain = "Botany & Cardiology",
                    narrative =
                      "The heart's natural pacemaker—the Sinoatrial (SA) node in the Right Atrium—fires 72 electrical beats per minute without needing conscious brain commands.",
                    examConnection = "Remember Red Blood Cells lack a nucleus (more room for Haemoglobin!), while White Blood Cells possess lobed/round nuclei.",
                  ),
                ),
              labType = PracticalLabType.NUTRITION_TRANSPORT_LAB,
              labBrief =
                "Trace oxygen saturation & blood pressure through RA → RV → Lungs → LA → LV and run a live Potometer to measure leaf Transpiration rate!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_05_1",
                    topic = "Heart Anatomy",
                    examType = "JAMB",
                    year = 2024,
                    question = "Which chamber of the mammalian heart has the thickest muscular wall and why?",
                    options = listOf("Right atrium — receives vena cava", "Right ventricle — pumps to lungs", "Left ventricle — pumps blood to entire body", "Left atrium — prevents backflow"),
                    correctIndex = 2,
                    hint = "High systemic pressure must overcome resistance from head to toe.",
                    stepByStepSolution = "Left ventricle develops highest pressure to drive systemic circulation via the aorta.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_05_2",
                    topic = "Blood Clotting",
                    examType = "WAEC",
                    year = 2023,
                    question = "During blood clotting, Thrombokinase + Ca²⁺ convert Prothrombin into active Thrombin, which converts:",
                    options = listOf("Haemoglobin to oxyhaemoglobin", "Soluble Fibrinogen into insoluble Fibrin mesh", "Platelets into phagocytes", "Bilirubin to urea"),
                    correctIndex = 1,
                    hint = "Fibrin threads trap RBCs to seal the wound.",
                    stepByStepSolution = "Thrombin catalyzes soluble fibrinogen → insoluble fibrin clot.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_05_3",
                    topic = "Blood Vessel Exception",
                    examType = "JAMB",
                    year = 2025,
                    question = "Which blood vessel carries OXYGENATED blood despite being named a vein?",
                    options = listOf("Inferior vena cava", "Pulmonary vein", "Hepatic portal vein", "Renal vein"),
                    correctIndex = 1,
                    hint = "Returns freshly oxygenated blood from lungs back to Left Atrium.",
                    stepByStepSolution = "Pulmonary vein returns O₂-rich blood to LA (Pulmonary artery carries deoxygenated blood!).",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_05_4",
                    topic = "Plant Transport",
                    examType = "WAEC",
                    year = 2022,
                    question = "Which abiotic condition DECREASES the rate of transpiration from a leafy shoot?",
                    options = listOf("High temperature", "Windy breeze", "High relative humidity", "Bright sunlight"),
                    correctIndex = 2,
                    hint = "Humid air is already packed with water vapour, flattening the diffusion gradient.",
                    stepByStepSolution = "High atmospheric humidity reduces water-vapour gradient outside stomata.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_05_5",
                    topic = "ABO Compatibility",
                    examType = "JAMB",
                    year = 2021,
                    question = "A person with Blood Group O can safely donate packed red cells in emergencies to:",
                    options = listOf("Group O only", "Group AB only", "Groups A, B, AB, and O", "Groups A and B only"),
                    correctIndex = 2,
                    hint = "Group O RBCs carry neither A nor B surface antigens.",
                    stepByStepSolution = "Absence of A/B antigens prevents recipient antibodies from agglutinating O cells.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_05_6",
                    topic = "Open vs Closed Circulation",
                    examType = "NECO",
                    year = 2024,
                    question = "Which organism possesses an OPEN circulatory system with haemolymph bathing tissues in a haemocoel?",
                    options = listOf("Earthworm", "Tilapia fish", "Grasshopper", "Toad"),
                    correctIndex = 2,
                    hint = "Arthropods (insects/crustaceans) have open circulation.",
                    stepByStepSolution = "Insects use a dorsal tubular heart pumping haemolymph into body sinuses (haemocoel).",
                  ),
                ),
            ),

            Lesson(
              id = "bio_06_respiration_excretion",
              moduleId = "bio_mod_2",
              subjectId = "biology",
              title = "Breathing Systems, Kidney Nephron & Homeostasis",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. B5–B6 • WAEC Respiratory & Excretory Systems",
              learningOutcomes =
                listOf(
                  "Compare Gill Counter-Current flow, Insect Tracheae, Amphibian Skin/Buccal pump & Human Alveoli.",
                  "Describe Inhalation vs Exhalation diaphragm & intercostal muscle pressure mechanics.",
                  "Trace Kidney Nephron Ultrafiltration → Selective Reabsorption → ADH Osmoregulation + Liver/Skin Homeostasis.",
                ),
              concepts =
                listOf(
                  ConceptBlock(
                    heading = "Gas Exchange Surfaces & Breathing Like a Bellows",
                    explanation =
                      "Whether a Fish's Gill Filaments, Frog's Moist Skin, Insect's Spiracles/Tracheae, or Human Alveoli—ALL great breathing organs share 4 rules: HUGE Surface Area, Ultra-Thin (1 cell thick!), Moist, and Rich Blood Supply (except insects, whose tracheal air pipes go straight to muscles!). During Inhalation, your External Intercostal muscles lift ribs UP & OUT while your Diaphragm flattens DOWN—chest volume EXPANDS, air pressure DROPS below outside air, and oxygen rushes in!",
                    keyFormula = "\\text{Fish Gill Counter-Current} > \\text{Concurrent (maintains } \\text{O}_2 \\text{ gradient along full lamella!)}",
                    examTip =
                      "Exhaled air is NOT 0% O₂! It still has ~16% O₂ (down from 21%!) and ~4% CO₂ (up 100× from 0.04%)!",
                  ),
                  ConceptBlock(
                    heading = "The Kidney Nephron Filter & Antidiuretic Hormone (ADH)",
                    explanation =
                      "Your Liver deaminates excess amino acids into toxic Ammonia → safe UREA (remember: humans don't store spare protein!). Each Kidney packs 1 million Nephron filters: High pressure inside the Glomerulus squeezes water, salts, glucose & urea into Bowman's Capsule (Ultrafiltration—big Red Blood Cells & Plasma Proteins STAY behind!). As fluid passes the Proximal Tubule, 100% of Glucose is pumped back! On a hot dry day, Pituitary gland releases ADH → Collecting Duct becomes permeable → extra water reabsorbed → small dark urine!",
                    keyFormula = "\\text{Bowman's Capsule (Ultrafiltration)} \\to \\text{PCT (100\\% Glucose Reabsorbed)} \\to \\text{Loop of Henle} \\to \\text{CD (+ADH } \\implies \\text{Water Saved)}",
                    examTip =
                      "Finding Glucose in urine points to Diabetes Mellitus (missing Insulin); finding Protein in urine points to damaged Glomerular capillaries!",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    title = "Fluid Composition Changes Along a Human Nephron",
                    examSource = "WAEC WASSCE-Style Worked Problem",
                    problemStatement =
                      "Compare the concentration of Plasma Protein, Glucose, and Urea in (a) Renal Artery, (b) Glomerular Filtrate, and (c) Urine.",
                    steps =
                      listOf(
                        "Step 1 — Renal Artery: High Plasma Protein, Normal Glucose, High Urea.",
                        "Step 2 — Glomerular Filtrate: ZERO Protein (too big!), Normal Glucose, Urea present.",
                        "Step 3 — Final Urine: ZERO Protein, ZERO Glucose (all reabsorbed!), Very HIGH Urea concentration.",
                      ),
                    finalAnswer = "Protein filtered out at Bowman's; Glucose 100% reabsorbed at PCT; Urea concentrated",
                    examinerTrap = "Saying urea is zero in filtrate—no, urea filters into Bowman's capsule and simply gets concentrated as water is reabsorbed!",
                  ),
                  WorkedExample(
                    title = "Sweating vs Shivering Homeostasis in Skin & Hypothalamus",
                    examSource = "JAMB UTME-Style Worked Problem",
                    problemStatement = "Describe two skin blood-vessel responses when human body temperature rises above 37°C.",
                    steps =
                      listOf(
                        "Step 1 — Hypothalamus detects warm blood and triggers VASODILATION (surface arterioles widen, flushing warm blood near skin to radiate heat).",
                        "Step 2 — Sweat glands secrete water whose evaporation removes latent heat from skin; erector pili muscles relax flattening hairs.",
                      ),
                    finalAnswer = "Vasodilation + Sweating + Relaxed erector pili",
                    examinerTrap = "Do NOT say blood vessels 'move closer to the skin'—they WIDEN (vasodilate) so MORE blood flows through superficial capillaries!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    title = "Kidney Dialysis Machines & Alcohol Dehydration",
                    industryDomain = "Nephrology",
                    narrative =
                      "Alcohol inhibits Pituitary ADH secretion—which explains why drinking fluids with alcohol actually dehydrates the body with frequent pale dilute urine!",
                    examConnection = "Plant excretory organs: Lenticels/Stomata (O₂/CO₂), Bark/Fallen leaves (tannins/alkaloids/gums).",
                  ),
                ),
              labType = PracticalLabType.RESPIRATION_KIDNEY_LAB,
              labBrief =
                "Toggle Inhalation vs Exhalation diaphragm mechanics and vary Hydration level to watch Pituitary ADH adjust Nephron water reabsorption & urine volume!",
              exercises =
                listOf(
                  PracticeQuestion(
                    id = "ex_bio_06_1",
                    topic = "Inhalation Mechanics",
                    examType = "JAMB",
                    year = 2024,
                    question = "During quiet human inspiration (breathing in), the diaphragm and thoracic pressure change as follows:",
                    options = listOf("Diaphragm arches up; pressure rises", "Diaphragm contracts & flattens; pressure drops below atmospheric", "Ribs move inward; pressure drops", "External intercostals relax"),
                    correctIndex = 1,
                    hint = "Larger chest volume → lower pressure draws air in.",
                    stepByStepSolution = "Flattening diaphragm + upward ribs expand chest volume, lowering intrapulmonary pressure.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_06_2",
                    topic = "Selective Reabsorption",
                    examType = "WAEC",
                    year = 2023,
                    question = "In a healthy human kidney, 100% of filtered Glucose is normally reabsorbed in the:",
                    options = listOf("Glomerulus", "Bowman's capsule", "Proximal Convoluted Tubule (PCT)", "Collecting duct"),
                    correctIndex = 2,
                    hint = "First coiled tube right after Bowman's capsule.",
                    stepByStepSolution = "Active co-transport in the PCT reabsorbs all glucose and amino acids.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_06_3",
                    topic = "Nitrogenous Waste Formation",
                    examType = "JAMB",
                    year = 2025,
                    question = "Where in the mammalian body is Urea actually synthesized from excess amino acids (Deamination)?",
                    options = listOf("Kidney cortex", "Spleen", "Liver", "Pancreas"),
                    correctIndex = 2,
                    hint = "Liver makes urea; Kidney only FILTERS & excretes it!",
                    stepByStepSolution = "Hepatocytes in the liver deaminate excess amino acids via the Ornithine cycle.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_06_4",
                    topic = "Insect Respiration",
                    examType = "WAEC",
                    year = 2022,
                    question = "Air enters an insect's tracheal respiratory tubes directly through external openings called:",
                    options = listOf("Stomata", "Lenticels", "Spiracles", "Opercula"),
                    correctIndex = 2,
                    hint = "Paired openings along thorax and abdomen segments.",
                    stepByStepSolution = "Spiracles regulate air entry into chitin-ringed tracheae and tracheoles.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_06_5",
                    topic = "Osmoregulation ADH",
                    examType = "JAMB",
                    year = 2021,
                    question = "Which hormone increases water reabsorption in kidney collecting ducts during dehydration?",
                    options = listOf("Insulin", "Adrenaline", "Antidiuretic Hormone (ADH / Vasopressin)", "Thyroxine"),
                    correctIndex = 2,
                    hint = "Secreted by posterior pituitary when blood osmotic pressure rises.",
                    stepByStepSolution = "ADH inserts aquaporins into collecting duct walls to conserve body water.",
                  ),
                  PracticeQuestion(
                    id = "ex_bio_06_6",
                    topic = "Excretory Products in Reptiles",
                    examType = "NECO",
                    year = 2024,
                    question = "Birds and desert reptiles excrete nitrogenous waste mainly as semi-solid Uric Acid primarily to:",
                    options = listOf("Save energy", "Conserve body water & lighten flight weight", "Increase urine volume", "Neutralize stomach acid"),
                    correctIndex = 1,
                    hint = "Ammonia needs lots of water (fish); Uric acid needs almost zero water!",
                    stepByStepSolution = "Insoluble uric acid crystal paste requires minimal water loss.",
                  ),
                ),
            ),
          ),
      ),
    )
}
