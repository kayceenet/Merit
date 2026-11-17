package com.example.data.curriculum

object BiologyCurriculumPart3 {

  val module5: CourseModule =
    CourseModule(
      id = "bio_mod_5",
      subjectId = "biology",
      indexLabel = "Module 05",
      title = "Ecology, Biogeochemical Cycles, Soil, Health & Conservation",
      summary = "Master Food Chains & 10% Energy Pyramids, Quadrat Sampling, Nigerian Biomes, Carbon/Nitrogen Cycles, Soil Types, Disease Vectors & Conservation.",
      lessons =
        listOf(
          Lesson(
            id = "bio_12_ecology_sampling",
            moduleId = "bio_mod_5",
            subjectId = "biology",
            title = "Ecosystems, Food Webs, 10% Energy Pyramids & Quadrat Sampling",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. D1–D2 • WAEC Trophic Ecology & Field Instruments",
            learningOutcomes =
              listOf(
                "Trace energy flow from Sunlight → Producers → Herbivores → Carnivores via the 10% Rule.",
                "Contrast Pyramids of Energy (NEVER inverted!), Biomass, and Numbers (can invert on one big tree!).",
                "Estimate population density using Quadrats, Transects & Capture-Recapture (Lincoln Index) + abiotic tools.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. The 10% Energy Tax & Why Food Chains Stop at 4–5 Steps",
                  explanation =
                      "Plants trap barely 1–2% of sunshine into food! When a goat eats grass, it burns 90% of that energy moving, breathing, and staying warm—so ONLY ~10% gets stored as goat meat for a lion to eat next! Because 90% leaks as heat at every single meal, a Pyramid of Energy is ALWAYS upright and food chains rarely stretch past 4 or 5 links!",
                  keyFormula = "\\text{10\\% Rule: } E_{n+1} \\approx 0.10\\,E_n   |   \\text{Population Density} = \\frac{\\text{Total Count}}{\\text{No. Throws} \\times \\text{Quadrat Area}}",
                  examTip =
                      "Can a Pyramid of NUMBERS ever look upside-down? YES—1 giant Iroko tree (producer) feeding 10,000 caterpillars feeding 500 mites!",
                ),
                ConceptBlock(
                  heading = "2. Field Sampling Tools (Quadrat vs Capture-Recapture)",
                  explanation =
                      "Never try counting every grass blade in a football pitch! Toss a 1 m² wooden square (Quadrat) randomly 10 times for stationary plants, average the count, and multiply by field area! What about moving grasshoppers or tilapia fish? Catch sample M₁, dab non-toxic paint, release, catch sample M₂ next day, count marked repeats R: Total N = (M₁ × M₂) / R! Measure abiotic clues with Secchi Disc (water transparency!), Anemometer (wind speed), Hygrometer (humidity) & Rain Gauge!",
                  keyFormula = "\\text{Lincoln Index: } N = \\frac{M_1 \\times M_2}{R_{\\text{marked recaptured}}}",
                  examTip =
                      "Symbiosis review: Tapeworm in gut = Parasitism (+/−); Remora hitching free ride on shark = Commensalism (+/0); Lichen (Alga + Fungus) = Mutualism (+/+)!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Calculating 10% Trophic Energy Transfer",
                  examSource = "JAMB UTME 2024 • Bio Q34",
                  problemStatement =
                      "Grass traps 20,000 kJ of energy. How much reaches the Hawk in: Grass → Grasshopper → Lizard → Hawk?",
                  steps =
                      listOf(
                        "Step 1 — Grasshopper (10% of 20,000) = 2,000 kJ.",
                        "Step 2 — Lizard (10% of 2,000) = 200 kJ.",
                        "Step 3 — Hawk (10% of 200) = 20 kJ.",
                      ),
                    finalAnswer = "20 kJ",
                    examinerTrap = "Don't divide by 10 once—divide at EVERY trophic arrow (3 arrows = ÷ 1000)!",
                ),
                WorkedExample(
                  title = "Capture-Recapture Fish Population Estimate",
                  examSource = "WAEC SSCE 2023 • Paper 1 Q36",
                  problemStatement =
                      "50 tilapia fish are netted, fin-tagged, and released. Next week 60 tilapia are caught, 12 bearing tags. Estimate the pond population.",
                    steps =
                    listOf(
                      "Step 1 — N = (M₁ × M₂) / R.",
                      "Step 2 — N = (50 × 60) / 12 = 3000 / 12 = 250 fish.",
                    ),
                  finalAnswer = "250 tilapia",
                  examinerTrap = "Assumes tags don't fall off, washed-out fish don't die, and marked fish remix randomly!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Why Eating Plants Supports 10× More People Than Beef",
                  industryDomain = "Food Security & Agronomy",
                  narrative =
                      "One hectare of soya beans yields ~10× more human calories than feeding that soya to cattle first—direct consequence of the 10% Lindeman trophic loss.",
                  examConnection = "Explains why Top Predators (Eagles/Lions) always have tiny population sizes and need vast territories.",
                ),
              ),
            labType = PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
            labBrief =
              "Adjust Producer kcal and Lindeman efficiency (5–20%) across Grass → Herbivore → Carnivore → Hawk, or toss random virtual Quadrats!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_bio_12_1",
                  topic = "Trophic Energy Pyramid",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which ecological pyramid can NEVER be inverted in any stable natural ecosystem?",
                  options = listOf("Pyramid of Numbers", "Pyramid of Biomass", "Pyramid of Energy", "Pyramid of Parasites"),
                  correctIndex = 2,
                  hint = "Thermodynamics prevents creating extra energy moving up trophic levels.",
                  stepByStepSolution = "Energy decreases irreversibly at every step (~90% lost as heat).",
                ),
                PracticeQuestion(
                  id = "ex_bio_12_2",
                  topic = "Field Instruments",
                  examType = "WAEC",
                  year = 2023,
                  question = "A Secchi disc is lowered into a lake to measure:",
                  options = listOf("Dissolved oxygen", "Water turbidity / light transparency", "Water pH", "Current speed"),
                  correctIndex = 1,
                  hint = "Black-and-white disc dropped until it disappears from view.",
                  stepByStepSolution = "Secchi depth measures water clarity/turbidity.",
                ),
                PracticeQuestion(
                  id = "ex_bio_12_3",
                  topic = "Decomposers",
                  examType = "JAMB",
                  year = 2025,
                  question = "Saprophytic fungi and bacteria prevent dead matter from piling up endlessly because they:",
                  options = listOf("Fix sunlight", "Recycle organic dead matter back into mineral nutrients", "Prey on hawks", "Produce ozone"),
                  correctIndex = 1,
                  hint = "Nature's nutrient recyclers!",
                  stepByStepSolution = "Decomposers break down proteins/cellulose into NH₄⁺, NO₃⁻, PO₄³⁻, and CO₂.",
                ),
                PracticeQuestion(
                  id = "ex_bio_12_4",
                  topic = "Epiphytes",
                  examType = "WAEC",
                  year = 2022,
                  question = "An orchid growing high on an oil palm trunk purely for sunlight support without stealing sap demonstrates:",
                  options = listOf("Parasitism", "Commensalism", "Predation", "Saprophytism"),
                  correctIndex = 1,
                  hint = "Orchid benefits (+); tall host tree is unharmed (0).",
                  stepByStepSolution = "Epiphytic support without nutrient theft is Commensalism.",
                ),
                PracticeQuestion(
                  id = "ex_bio_12_5",
                  topic = "Quadrat Estimation",
                  examType = "JAMB",
                  year = 2021,
                  question = "Five throws of a 1 m² quadrat on a 100 m² lawn yield counts of 6, 8, 7, 9, 10 dandelions. Estimate total dandelions.",
                  options = listOf("80", "400", "800", "4000"),
                  correctIndex = 2,
                  hint = "Mean per m² = (6+8+7+9+10)/5 = 8.",
                  stepByStepSolution = "8 plants/m² × 100 m² = 800 plants.",
                ),
                PracticeQuestion(
                  id = "ex_bio_12_6",
                  topic = "Biological Pest Control",
                  examType = "WAEC",
                  year = 2024,
                  question = "Introducing Ladybird beetles to eat crop-destroying aphids is preferred over DDT spray because it:",
                  options = listOf("Kills all soil worms", "Avoids biomagnification & toxic food contamination", "Works instantly in 1 second", "Increases soil salinity"),
                  correctIndex = 1,
                  hint = "DDT concentrates up food chains in eagle eggshells!",
                  stepByStepSolution = "Biological predator control avoids chemical pollution and bioaccumulation.",
                ),
              ),
          ),

          Lesson(
            id = "bio_13_biomes_adaptations",
            moduleId = "bio_mod_5",
            subjectId = "biology",
            title = "Nigerian Biomes, Succession & Structural Adaptations",
            durationMinutes = 19,
            syllabusTag = "JAMB Sec. D3–D4 • WAEC Biomes, Succession & Adaptations",
            learningOutcomes =
              listOf(
                "Locate Mangrove Swamp, Tropical Rainforest, Guinea/Sudan/Sahel Savanna & Montane zones on a map.",
                "Contrast Primary Succession (bare rock → Lichen → Moss → Grass → Climax Forest) vs Secondary Succession.",
                "Explain plant/animal adaptations in Hydrophytes (Aerenchyma), Xerophytes (Spines/Sunken Stomata) & Halophytes (Pneumatophores!).",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Biomes from Coastline to Sahel + Ecological Succession",
                  explanation =
                      "Travel South → North across West Africa and rainfall steadily drops: Coastal salty Mangrove Swamps (Stilt roots + breathing Pneumatophores!) → Dense Canopy Tropical Rainforest (tall buttress trees + lianas + epiphytes) → Guinea Savanna (tall grass + broad fire-resistant trees) → Sudan Savanna → thorny Sahel Savanna! Meanwhile, ecosystems mature step-by-step via Primary Succession: Pioneer Lichens crack bare rock → Mosses trap thin soil → Ferns/Grasses → Shrubs → stable Climax Forest!",
                  keyFormula = "\\text{Bare Rock} \\xrightarrow{\\text{Lichens (Pioneer)}} \\text{Moss} \\to \\text{Grasses} \\to \\text{Shrubs} \\to \\text{Climax Forest}",
                  examTip =
                      "Secondary Succession (after bush fire or abandoned farmland) is MUCH faster because Soil + Seeds ALREADY exist!",
                ),
                ConceptBlock(
                  heading = "2. Plant & Animal Survival Toolkits (Hydrophyte vs Xerophyte vs Mangrove)",
                  explanation =
                      "Water Lily (Hydrophyte): spongy Aerenchyma air pockets keep leaves floating and Stomata sit ONLY on top! Cactus (Xerophyte): leaves shrunk into sharp SPINES to stop water loss, thick waxy cuticle, swollen green stem for photosynthesis & storage, deep/wide roots! Red Mangrove (Halophyte in airless mud): Stilt roots brace against tides while snorkeling Pneumatophore breathing roots poke UP into the air!",
                  keyFormula = "\\text{Xerophyte} = \\text{Spines + Sunken Stomata + Thick Cuticle}   |   \\text{Mangrove} = \\text{Prop/Stilt + Breathing Pneumatophores}",
                  examTip =
                      "Camel & Desert Rat survival trick: Camels oxidize stored hump FAT yielding metabolic H₂O, and Kangaroo rats produce super-concentrated urine!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Interpreting Aquatic vs Desert Leaf Cross-Sections",
                  examSource = "JAMB UTME 2024 • Bio Q36",
                  problemStatement =
                      "Microscopy shows a leaf with thick waxy cuticle, sunken stomata lined with hairs, and rolled blade. Identify habitat and adaptive benefit.",
                  steps =
                      listOf(
                        "Step 1 — Trapping humid still air inside rolled blades and sunken hair pits dramatically cuts water evaporation (transpiration).",
                        "Step 2 — Classic Xerophyte adaptation to arid Desert / Sahel conditions.",
                      ),
                    finalAnswer = "Xerophyte (Arid Desert/Sahel) conserving scarce water",
                    examinerTrap = "Hydrophytes have thin/NO waxy cuticle and stomata on the UPPER surface ONLY!",
                ),
                WorkedExample(
                  title = "Mangrove Swamp Mud Roots",
                  examSource = "WAEC SSCE 2023 • Paper 2 Q9",
                  problemStatement = "Why do White Mangrove trees (Avicennia) grow vertical peg-like roots sticking UP out of tidal mud?",
                  steps =
                    listOf(
                      "Step 1 — Waterlogged salty mangrove mud has almost zero dissolved O₂ for normal underground root respiration.",
                      "Step 2 — Pneumatophores possess open lenticels above high-tide water to draw atmospheric O₂ down to submerged roots.",
                    ),
                  finalAnswer = "Pneumatophores absorb atmospheric O₂ in anaerobic waterlogged mud",
                  examinerTrap = "Prop roots stabilize against waves; Pneumatophores specifically handle GAS EXCHANGE!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Sahel Desertification Shelterbelts & Rainforest Canopy Stratification",
                  industryDomain = "Forestry & Climate Resilience",
                  narrative =
                      "Planting Neem and Acacia tree shelterbelts across northern Nigeria blocks hot Harmattan desert winds, stabilizing topsoil against Sahel encroachment.",
                  examConnection = "Shows competition for light creating 3–4 vertical canopy layers in rainforests.",
                ),
              ),
            labType = PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
            labBrief =
              "Inspect Biome Rainfall/Temperature gradients from Mangrove to Sahel, step through Bare-Rock → Climax Succession, and inspect Xerophyte vs Hydrophyte traits!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_bio_13_1",
                  topic = "Pioneer Species",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which organism acts as the classic pioneer colonizer on bare volcanic rock during Primary Succession?",
                  options = listOf("Iroko tree", "Crustose Lichens", "Elephant grass", "Mushroom"),
                  correctIndex = 1,
                  hint = "Alga + Fungus symbiosis secreting weak acids to weather rock.",
                  stepByStepSolution = "Lichens weather bare rock and trap wind dust to form initial soil.",
                ),
                PracticeQuestion(
                  id = "ex_bio_13_2",
                  topic = "Mangrove Roots",
                  examType = "WAEC",
                  year = 2023,
                  question = "Upward-growing breathing roots (pneumatophores) are a diagnostic adaptation of plants in:",
                  options = listOf("Sudan savanna", "Mangrove swamp", "Montane grassland", "Desert oasis"),
                  correctIndex = 1,
                  hint = "Waterlogged anaerobic tidal mud lacks oxygen.",
                  stepByStepSolution = "Mangroves use pneumatophores for root respiration in anoxic mud.",
                ),
                PracticeQuestion(
                  id = "ex_bio_13_3",
                  topic = "Hydrophyte Adaptations",
                  examType = "JAMB",
                  year = 2025,
                  question = "Floating leaves of Water Lettuce (Pistia) and Water Lily have large air-filled parenchyma (aerenchyma) mainly to:",
                  options = listOf("Store starch", "Provide buoyancy and internal O₂ diffusion", "Deter fish", "Reflect UV light"),
                  correctIndex = 1,
                  hint = "Spongy air chambers float leaves effortlessly.",
                  stepByStepSolution = "Aerenchyma gives buoyancy and supplies O₂ to submerged stems.",
                ),
                PracticeQuestion(
                  id = "ex_bio_13_4",
                  topic = "Savanna Biome",
                  examType = "WAEC",
                  year = 2022,
                  question = "Which West African vegetation zone is marked by tall perennial grasses, scattered fire-resistant baobab/shea trees, and distinct wet/dry seasons?",
                  options = listOf("Tropical rainforest", "Guinea Savanna", "Mangrove swamp", "Tundra"),
                  correctIndex = 1,
                  hint = "Thick corky bark resists seasonal bush fires.",
                  stepByStepSolution = "Guinea savanna spans Nigeria's middle belt with grasses and fire-adapted trees.",
                ),
                PracticeQuestion(
                  id = "ex_bio_13_5",
                  topic = "Secondary Succession",
                  examType = "JAMB",
                  year = 2021,
                  question = "Secondary succession on an abandoned cocoa farm reaches climax faster than primary succession because:",
                  options = listOf("Rainfall is higher", "Soil, humus, and buried seeds are already present", "No herbivores visit", "Sun shines longer"),
                  correctIndex = 1,
                  hint = "No need to spend centuries weathering rock into soil.",
                  stepByStepSolution = "Pre-existing soil organic matter and seed banks accelerate recovery.",
                ),
                PracticeQuestion(
                  id = "ex_bio_13_6",
                  topic = "Camouflage Adaptation",
                  examType = "WAEC",
                  year = 2024,
                  question = "When a harmless hoverfly evolves yellow-and-black wasp stripes to scare off birds, this adaptation is:",
                  options = listOf("Counter-shading", "Batesian mimicry", "Hibernation", "Territoriality"),
                  correctIndex = 1,
                  hint = "Defenseless species copying a dangerous model.",
                  stepByStepSolution = "Harmless species imitating toxic/stinging models is Batesian mimicry.",
                ),
              ),
          ),

          Lesson(
            id = "bio_14_cycles_soil",
            moduleId = "bio_mod_5",
            subjectId = "biology",
            title = "Carbon/Nitrogen Cycles, Soil Science & Pollution",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. D5–D6 • WAEC Nutrient Cycles, Soil & Pollution",
            learningOutcomes =
              listOf(
                "Name all 4 Nitrogen Cycle bacteria groups (Nitrogen-fixing, Putrefying, Nitrifying, Denitrifying).",
                "Compare Sandy (high drainage, low humus), Clay (waterlogged, poor air) & Loamy soils + Capillary Rise/Humus tests.",
                "Explain Eutrophication (algal blooms killing fish via BOD depletion) & Greenhouse Warming.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. The 4 Hero & Villain Bacteria of the Nitrogen Cycle",
                  explanation =
                      "Air is 78% Nitrogen (N₂), yet plants can't breathe it—they need NITRATES (NO₃⁻) dissolved in soil water! 4 bacteria groups run the show: (1) FIXERS (Rhizobium in legume roots, Azotobacter, lightning!) pull N₂ out of air into ammonia/nitrates! (2) PUTREFYING decomposers rot dead bodies into Ammonia (NH₄⁺)! (3) NITRIFYING heroes (Nitrosomonas turns NH₄⁺ → Nitrite NO₂⁻; Nitrobacter turns NO₂⁻ → Nitrate NO₃⁻—both need O₂ air!). (4) DENITRIFYING villains (Pseudomonas in soggy airless soil) waste nitrates back to useless N₂ gas!",
                  keyFormula = "\\text{NH}_4^+ \\xrightarrow{\\text{Nitrosomonas}} \\text{NO}_2^- \\xrightarrow{\\text{Nitrobacter}} \\text{NO}_3^-\\; (\\text{Plant Uptake!})   |   \\text{NO}_3^- \\xrightarrow{\\text{Pseudomonas}} \\text{N}_2\\uparrow",
                  examTip =
                      "How does Eutrophication kill pond fish? Fertilizer runoff sparks huge green Algae bloom → algae die and sink → aerobic Decomposer Bacteria multiply massively using up ALL dissolved O₂ → fish suffocate!",
                ),
                ConceptBlock(
                  heading = "2. Sandy vs Clay vs Loamy Soil (Golden Balanced Mix!)",
                  explanation =
                      "Sandy soil has big coarse grains: drains water instantly (leaching nutrients!) and warms fast, holding almost no water. Clay soil packs tiny sticky plates: holds tons of water tightly (waterlogged, suffocates roots!). LOAM is the gardener's goldmine—balanced sand + silt + clay + rich dark HUMUS holding both moisture AND air spaces! Calculate % Soil Humus by weighing dry soil before vs after red-hot ignition (humus burns away!)!",
                  keyFormula = "\\%\\text{ Water} = \\frac{\\text{Dry loss at } 105^\\circ\\text{C}}{\\text{Fresh Mass}}\\times 100   |   \\%\\text{ Humus} = \\frac{\\text{Loss on Ignition}}{\\text{Oven-Dry Mass}}\\times 100",
                  examTip =
                      "Capillary Rise practical: Clay pushes water HIGHEST up a glass soil column overnight (slowly!), while Sand stops low & quickly!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Determining Soil Humus Percentage Lab",
                  examSource = "WAEC SSCE 2024 • Practical Paper 3",
                  problemStatement =
                      "An evaporating dish + soil weighs 50 g. After oven-drying at 105°C it weighs 45 g; after red-hot ignition to constant mass it weighs 40 g (empty dish = 20 g). Find % Humus of dry soil.",
                  steps =
                      listOf(
                        "Step 1 — Dry soil mass = 45 − 20 = 25 g (5 g water evaporated at 105°C).",
                        "Step 2 — Humus burnt off on ignition = 45 − 40 = 5 g.",
                        "Step 3 — % Humus = (5 / 25) × 100 = 20%.",
                      ),
                    finalAnswer = "20% Humus",
                    examinerTrap = "Don't forget to subtract the empty dish mass (20 g) before dividing!",
                ),
                WorkedExample(
                  title = "Why Ploughing Helps Soil Fertility",
                  examSource = "JAMB UTME 2023 • Bio Q37",
                  problemStatement = "How does draining and aerating waterlogged farmland improve plant nitrate supply?",
                  steps =
                    listOf(
                      "Step 1 — Oxygen boosts aerobic Nitrifying bacteria (Nitrosomonas & Nitrobacter).",
                      "Step 2 — Oxygen simultaneously suppresses anaerobic Denitrifying Pseudomonas that would waste soil NO₃⁻ into N₂.",
                    ),
                  finalAnswer = "Promotes aerobic nitrification & suppresses anaerobic denitrification",
                  examinerTrap = "Nitrifying bacteria are strictly AEROBIC; Denitrifying bacteria thrive only in anaerobic waterlogged mud!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Crop Rotation with Cowpea & Oil-Spill Bioremediation",
                  industryDomain = "Sustainable Agriculture",
                  narrative =
                      "Alternating Maize (heavy nitrogen feeder) with Cowpea or Groundnut naturally replenishes soil nitrates via Rhizobium nodules without buying expensive urea bags.",
                  examConnection = "Links Nitrogen Cycle directly to soil conservation methods (contour ploughing, cover cropping, mulching).",
                ),
              ),
            labType = PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB,
            labBrief =
              "Inspect bacterial steps along the Nitrogen Cycle (Nitrosomonas → Nitrobacter vs Pseudomonas) and test Sandy, Loamy & Clay water drainage + capillary rise!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_bio_14_1",
                  topic = "Nitrifying Bacteria",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which bacterium converts Nitrite (NO₂⁻) into plant-absorbable Nitrate (NO₃⁻)?",
                  options = listOf("Nitrosomonas", "Nitrobacter", "Pseudomonas", "Rhizobium"),
                  correctIndex = 1,
                  hint = "Nitrosomonas goes NH₃ → NO₂⁻; Nitrobacter finishes NO₂⁻ → NO₃⁻.",
                  stepByStepSolution = "Nitrobacter oxidizes nitrites into usable nitrates.",
                ),
                PracticeQuestion(
                  id = "ex_bio_14_2",
                  topic = "Eutrophication",
                  examType = "WAEC",
                  year = 2023,
                  question = "Sudden fish kills in lakes polluted with NPK fertilizer occur directly because:",
                  options = listOf("Algae poison gills instantly", "Bacteria decomposing dead algal blooms exhaust dissolved oxygen", "Water pH rises to 14", "Sunlight heats the bottom"),
                  correctIndex = 1,
                  hint = "Biological Oxygen Demand (BOD) spikes during microbial decay.",
                  stepByStepSolution = "Bacterial decomposition of algal blooms creates anoxia (oxygen starvation).",
                ),
                PracticeQuestion(
                  id = "ex_bio_14_3",
                  topic = "Soil Drainage",
                  examType = "JAMB",
                  year = 2025,
                  question = "Which soil texture exhibits the HIGHEST water-holding capacity and SLOWEST drainage?",
                  options = listOf("Coarse sand", "Sandy loam", "Clay soil", "Gravel"),
                  correctIndex = 2,
                  hint = "Microscopic pores hold water tightly via capillary forces.",
                  stepByStepSolution = "Clay holds maximum water with minimal aeration.",
                ),
                PracticeQuestion(
                  id = "ex_bio_14_4",
                  topic = "Soil Conservation",
                  examType = "WAEC",
                  year = 2022,
                  question = "Ploughing across the slope rather than straight uphill on farmland is specifically called:",
                  options = listOf("Bush burning", "Contour ploughing", "Monoculture", "Shifting cultivation"),
                  correctIndex = 1,
                  hint = "Horizontally follows elevation contour lines to slow rainwater runoff.",
                  stepByStepSolution = "Contour ploughing traps water furrows and checks gully erosion.",
                ),
                PracticeQuestion(
                  id = "ex_bio_14_5",
                  topic = "Greenhouse Effect",
                  examType = "JAMB",
                  year = 2021,
                  question = "Global warming intensifies primarily because rising CO₂ and CH₄ gases:",
                  options = listOf("Block incoming UV", "Trap outgoing long-wave infrared heat radiated from Earth", "Destroy oxygen", "Cool ocean currents"),
                  correctIndex = 1,
                  hint = "Like glass panes in a botanical greenhouse.",
                  stepByStepSolution = "Greenhouse gases absorb and re-emit terrestrial infrared radiation.",
                ),
                PracticeQuestion(
                  id = "ex_bio_14_6",
                  topic = "Carbon Monoxide Toxicity",
                  examType = "WAEC",
                  year = 2024,
                  question = "Why is inhaling exhaust Carbon Monoxide (CO) from a generator indoors lethal?",
                  options = listOf("Freezes alveoli", "Binds Haemoglobin ~200× tighter than O₂ forming carboxyhaemoglobin", "Dissolves plasma", "Bursts kidneys"),
                  correctIndex = 1,
                  hint = "Blocks red blood cells from carrying oxygen to the brain.",
                  stepByStepSolution = "Irreversible carboxyhaemoglobin causes rapid cellular asphyxiation.",
                ),
              ),
          ),

          Lesson(
            id = "bio_15_microbes_vectors_conservation",
            moduleId = "bio_mod_5",
            subjectId = "biology",
            title = "Microorganisms, Disease Vectors, Immunity & Conservation",
            durationMinutes = 20,
            syllabusTag = "JAMB Sec. D7–E • WAEC Health, Microbes, Vectors & Conservation",
            learningOutcomes =
              listOf(
                "Match major diseases to their exact Causative Pathogen & Vector (Malaria, Cholera, Bilharzia, River Blindness, Sleeping Sickness).",
                "Distinguish Active Natural/Artificial Immunity (Vaccination) from Passive Immunity (Maternal IgG / Antiserum).",
                "Explain Wildlife Conservation laws, National Parks & sustainable resource management.",
              ),
            concepts =
              listOf(
                ConceptBlock(
                  heading = "1. Germs vs Vectors (Don't Mix Them Up!)",
                  explanation =
                      "Huge JAMB trap: Mosquito does NOT cause malaria—it is only the DELIVERY TAXI (Vector)! The actual pathogen parasite is microscopic Plasmodium! Know your Top 5: (1) Malaria = Plasmodium carried by female Anopheles mosquito; (2) Sleeping Sickness (Trypanosomiasis) = Trypanosoma carried by Tsetse fly; (3) River Blindness (Onchocerciasis) = Onchocerca worm carried by Blackfly (Simulium); (4) Bilharzia (Schistosomiasis) = Blood fluke carried by Freshwater Snail; (5) Cholera/Typhoid = Bacteria spread by Houseflies & dirty water!",
                  keyFormula = "\\text{Vector (Taxi): Anopheles / Tsetse / Blackfly / Snail} \\neq \\text{Pathogen (Killer): Plasmodium / Trypanosoma / Vibrio}",
                  examTip =
                      "How does spraying kerosene oil on stagnant ponds kill mosquitoes? It clogs surface tension so breathing siphon tubes of floating Larvae & Pupae suffocate!",
                ),
                ConceptBlock(
                  heading = "2. Immunity (Make Your Own vs Borrow) & Conservation",
                  explanation =
                      "Active Immunity means YOUR OWN immune system produces antibodies + lifelong memory cells (either by surviving measles naturally, OR safely via a Vaccine of weakened/dead germs!). Passive Immunity means BORROWING ready-made antibodies (Mom's breastmilk / placenta, or urgent Antivenom snake shot)—works immediately, fades in weeks! Meanwhile, Conservation saves endangered species (Yankari & Kainji National Parks, strict hunting bans, reforestation) for biodiversity!",
                  keyFormula = "\\text{Vaccine} = \\text{Artificial ACTIVE (Long-lasting)}   |   \\text{Antivenom / Breastmilk} = \\text{PASSIVE (Instant, Short)}",
                  examTip =
                      "Aseptic techniques: Autoclaving (121°C pressurized steam), Pasteurization (72°C brief heating kills milk pathogens without boiling flavour), and Disinfectants vs Antiseptics (skin-safe!)!",
                ),
              ),
            workedExamples =
              listOf(
                WorkedExample(
                  title = "Controlling Malaria vs Bilharzia Life Cycles",
                  examSource = "JAMB UTME 2024 • Bio Q39",
                  problemStatement =
                      "Contrast the most effective vector-targeted control step for (a) Malaria and (b) Schistosomiasis (Bilharzia).",
                  steps =
                    listOf(
                      "Step 1 — Malaria vector: female Anopheles breeds in stagnant puddles → drain ditches, sleep under insecticide-treated nets (ITNs), introduce Gambusia fish to eat larvae.",
                      "Step 2 — Bilharzia intermediate host: Freshwater Snail (Bulinus) → treat ponds with molluscicides & ban urinating/defecating in streams.",
                    ),
                  finalAnswer = "Malaria: Drain puddles/ITNs (Anopheles)  |  Bilharzia: Eliminate water snails & sanitary sewage",
                  examinerTrap = "Vector for Sleeping Sickness is Tsetse fly (Glossina), NOT Mosquito!",
                ),
                WorkedExample(
                  title = "Active Vaccination vs Passive Snake Antivenom",
                  examSource = "WAEC SSCE 2023 • Paper 1 Q39",
                  problemStatement = "Why does a child receive Polio drops BEFORE exposure whereas a snakebite victim receives Antivenom AFTER bite?",
                  steps =
                    listOf(
                      "Step 1 — Polio vaccine triggers Active Artificial Immunity: slow first build (~14 days) but creates lifelong B/T memory cells!",
                      "Step 2 — Venom kills in hours, too fast to wait—ready-made horse antibodies (Passive Artificial Immunity) neutralize toxin instantly.",
                    ),
                  finalAnswer = "Vaccine = slow lifelong Active  |  Antivenom = instant short Passive",
                  examinerTrap = "Saying vaccines 'kill the virus directly'—vaccines ONLY train YOUR immune system to make antibodies!",
                ),
              ),
            scenarios =
              listOf(
                RealLifeScenario(
                  title = "Microbial Sewage Treatment & Yankari Reserve",
                  industryDomain = "Public Health & Wildlife Management",
                  narrative =
                      "Modern sewage plants cultivate aerobic bacteria to digest raw waste cleanly before river release, while Yankari Game Reserve protects West African elephants and lions from poaching.",
                  examConnection = "Antibiotics (Penicillin from Penicillium fungus) kill Bacteria ONLY—never Viruses!",
                ),
              ),
            labType = PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB,
            labBrief =
              "Match Pathogen, Vector & Lifecycle Stage for Nigeria's top endemic diseases and explore Pond Oxygen vs Bacteria microbial growth curves!",
            exercises =
              listOf(
                PracticeQuestion(
                  id = "ex_bio_15_1",
                  topic = "Disease Vectors",
                  examType = "JAMB",
                  year = 2024,
                  question = "Which pair matches BOTH the disease Onchocerciasis (River Blindness) and its transmitting vector?",
                  options = listOf("Trypanosoma — Tsetse fly", "Onchocerca volvulus — Blackfly (Simulium)", "Plasmodium — Culex mosquito", "Vibrio — Housefly"),
                  correctIndex = 1,
                  hint = "Blackflies breed in fast-flowing oxygenated rivers.",
                  stepByStepSolution = "Blackfly (Simulium damnosum) transmits microfilariae of Onchocerca volvulus.",
                ),
                PracticeQuestion(
                  id = "ex_bio_15_2",
                  topic = "Bacterial Growth Curve",
                  examType = "WAEC",
                  year = 2023,
                  question = "During which phase of a closed-flask bacterial growth curve do cells divide exponentially at maximum speed?",
                  options = listOf("Lag phase", "Log (Exponential) phase", "Stationary phase", "Death phase"),
                  correctIndex = 1,
                  hint = "After adapting during Lag phase, division doubles each generation.",
                  stepByStepSolution = "Log phase shows steep straight-line exponential multiplication.",
                ),
                PracticeQuestion(
                  id = "ex_bio_15_3",
                  topic = "Passive Immunity",
                  examType = "JAMB",
                  year = 2025,
                  question = "Newborn babies gain temporary natural immunity during their first months primarily from:",
                  options = listOf("BCG scar", "Maternal antibodies in colostrum & across the placenta", "Eating solid starch", "Sunlight Vitamin D"),
                  correctIndex = 1,
                  hint = "Passive Natural Immunity from Mom!",
                  stepByStepSolution = "Maternal IgG and IgA antibodies protect newborns passively until their own immune system matures.",
                ),
                PracticeQuestion(
                  id = "ex_bio_15_4",
                  topic = "Pasteurization",
                  examType = "WAEC",
                  year = 2022,
                  question = "Fresh cow's milk is heated briefly to ~72°C for 15 seconds then rapidly chilled in Pasteurization to:",
                  options = listOf("Curdle casein", "Destroy harmful vegetative pathogens while preserving taste & nutrients", "Kill bacterial endospores at 180°C", "Add sugar"),
                  correctIndex = 1,
                  hint = "Invented by Louis Pasteur.",
                  stepByStepSolution = "Pasteurization kills TB/Brucella pathogens without boiling flavor away.",
                ),
                PracticeQuestion(
                  id = "ex_bio_15_5",
                  topic = "Antibiotic Target",
                  examType = "JAMB",
                  year = 2021,
                  question = "Why is Penicillin effective against strep throat yet completely useless against HIV or Common Cold?",
                  options = listOf("Penicillin only dissolves bacterial cell walls; viruses lack cell walls", "Viruses eat penicillin", "Cold is too cold", "Bacteria swim faster"),
                  correctIndex = 0,
                  hint = "Bacteria have peptidoglycan walls; viruses don't!",
                  stepByStepSolution = "Penicillin inhibits bacterial cell-wall cross-linking—ineffective on non-cellular viruses.",
                ),
                PracticeQuestion(
                  id = "ex_bio_15_6",
                  topic = "Conservation",
                  examType = "WAEC",
                  year = 2024,
                  question = "Establishing National Game Reserves like Yankari and Cross River helps conservation primarily by:",
                  options = listOf("Encouraging logging", "Protecting natural habitats & endangered wildlife from poaching", "Draining wetlands", "Introducing invasive weeds"),
                  correctIndex = 1,
                  hint = "In-situ habitat preservation.",
                  stepByStepSolution = "Protected reserves preserve breeding habitats and genetic biodiversity.",
                ),
              ),
          ),
        ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      PracticeQuestion("pq_bio_01", "Classification", "JAMB", 2025, "Which organism possesses both plant chloroplasts and an animal-like flagellum?", listOf("Euglena", "Amoeba", "Paramecium", "Chlamydomonas"), 0, "Chlamydomonas has a cellulose wall; Euglena lacks a cell wall (pellicle) & has an eyespot.", "Euglena bridges plant and animal features inside Protista."),
      PracticeQuestion("pq_bio_02", "Osmosis", "WAEC", 2024, "A plant cell placed in a hypotonic solution becomes:", listOf("Plasmolyzed", "Flaccid", "Turgid", "Haemolyzed"), 2, "Cell wall prevents bursting as water enters vacuole.", "Turgor pressure inflates the cell stiffly against its wall."),
      PracticeQuestion("pq_bio_03", "Enzymes", "JAMB", 2024, "Salivary amylase (ptyalin) stops digesting starch in the stomach because:", listOf("Stomach lacks water", "Hydrochloric acid pH ~2 denatures salivary amylase", "Pepsin eats it instantly", "Temp hits 60°C"), 1, "Ptyalin's optimum pH is ~6.8–7.0.", "Strong gastric HCl denatures neutral-preferring amylase."),
      PracticeQuestion("pq_bio_04", "Photosynthesis", "WAEC", 2023, "Which mineral deficiency turns mature plant leaves pale yellow (chlorosis)?", listOf("Magnesium", "Calcium", "Sodium", "Chlorine"), 0, "Mg²⁺ sits at the very center of every Chlorophyll ring!", "Lack of Magnesium prevents chlorophyll synthesis."),
      PracticeQuestion("pq_bio_05", "Food Tests", "JAMB", 2023, "Heating glucose solution with Benedict's reagent yields a precipitate coloured:", listOf("Violet", "Brick-red", "Blue-black", "Pink"), 1, "Cu²⁺ reduces to Cu₂O.", "Reducing sugars give brick-red copper(I) oxide."),
      PracticeQuestion("pq_bio_06", "Heart Circulation", "WAEC", 2024, "Which valve prevents blood from flowing back from the Left Ventricle into the Left Atrium?", listOf("Tricuspid valve", "Bicuspid (Mitral) valve", "Pulmonary semilunar", "Aortic semilunar"), 1, "Left = Bicuspid; Right = Tricuspid.", "Bicuspid (mitral) valve guards the left AV opening."),
      PracticeQuestion("pq_bio_07", "Transpiration", "JAMB", 2025, "Which instrument directly measures the rate of water uptake/transpiration in a cut leafy shoot?", listOf("Clinostat", "Potometer", "Auxanometer", "Hygrometer"), 1, "Tracks air bubble movement along capillary tube.", "Potometer quantifies water uptake rate."),
      PracticeQuestion("pq_bio_08", "Kidney Nephron", "WAEC", 2022, "Presence of glucose in human urine (glycosuria) typically signals insufficient:", listOf("ADH", "Thyroxine", "Insulin", "Bile"), 2, "Blood glucose exceeds kidney PCT transport max.", "Insulin deficiency (Diabetes mellitus) leaves excess glucose spilling into urine."),
      PracticeQuestion("pq_bio_09", "Respiration", "JAMB", 2024, "Which blood vessel leaves the kidneys with the LOWEST concentration of urea?", listOf("Renal artery", "Renal vein", "Hepatic vein", "Vena cava"), 1, "Cleaned blood exiting the kidney.", "Renal vein."),
      PracticeQuestion("pq_bio_10", "Eye Defects", "WAEC", 2023, "Long-sightedness (Hypermetropia), where images focus behind the retina, is corrected with:", listOf("Concave lens", "Convex lens", "Opaque disc", "Plane mirror"), 1, "Need extra converging power to pull rays forward onto retina.", "Convex (converging) lens."),
      PracticeQuestion("pq_bio_11", "Brain", "JAMB", 2022, "Which part of the human brain regulates involuntary heartbeat, breathing rate, and swallowing?", listOf("Cerebrum", "Cerebellum", "Medulla oblongata", "Olfactory lobe"), 2, "Brainstem vital center.", "Medulla oblongata."),
      PracticeQuestion("pq_bio_12", "Plant Hormones", "WAEC", 2025, "Which gaseous plant hormone triggers fruit ripening and leaf abscission?", listOf("Auxin", "Gibberellin", "Ethene (Ethylene)", "Cytokinin"), 2, "One ripe banana ripens the whole bunch!", "Ethene gas."),
      PracticeQuestion("pq_bio_13", "Skeleton", "JAMB", 2021, "The odontoid process (tooth-like peg for rotating the skull) is a feature of which vertebra?", listOf("Atlas", "Axis", "Lumbar", "Sacrum"), 1, "Second cervical vertebra C2.", "Axis vertebra."),
      PracticeQuestion("pq_bio_14", "Reproduction", "WAEC", 2024, "Where does fertilization of a human ovum normally take place?", listOf("Uterus lining", "Vagina", "Oviduct (Fallopian tube)", "Ovary surface"), 2, "Upper third of the Fallopian tube.", "Fallopian tube (oviduct)."),
      PracticeQuestion("pq_bio_15", "Seeds & Fruits", "JAMB", 2023, "A true fruit differs from a seed because a true fruit develops from the fertilized:", listOf("Ovule", "Ovary", "Receptacle", "Stigma"), 1, "Cashew apple is a false fruit (swollen pedicel); mango develops from ovary.", "Ovary."),
      PracticeQuestion("pq_bio_16", "Genetics", "WAEC", 2021, "A father is Blood Group O and mother is Blood Group AB. What blood groups can their biological children have?", listOf("A and B only", "AB and O only", "O only", "All four A, B, AB, O"), 0, "ii × I^A I^B → I^A i and I^B i ONLY.", "50% Type A, 50% Type B."),
      PracticeQuestion("pq_bio_17", "Sex-Linked Genes", "JAMB", 2024, "Why do X-linked recessive disorders like Haemophilia strike men far more frequently than women?", listOf("Y carries toxic genes", "Men possess only one X chromosome, so a single recessive allele is expressed", "Women are immune to clotting factors", "Men bleed more"), 1, "XY males have no second backup X chromosome.", "Hemizygous X^h Y males express the recessive allele immediately."),
      PracticeQuestion("pq_bio_18", "DNA Replication", "WAEC", 2022, "On DNA, Cytosine always forms three hydrogen bonds exclusively with:", listOf("Adenine", "Thymine", "Guanine", "Uracil"), 2, "A=T, C≡G.", "Guanine."),
      PracticeQuestion("pq_bio_19", "Evolution", "JAMB", 2025, "The rapid evolution of 14 Galápagos finch beak shapes from one mainland ancestor illustrates:", listOf("Adaptive radiation", "Lamarckian use", "Convergent mimicry", "Vestigial loss"), 0, "Diversifying into empty ecological feeding niches.", "Adaptive radiation."),
      PracticeQuestion("pq_bio_20", "Ecology", "WAEC", 2023, "In the food chain Algae → Tadpole → Tilapia → Kingfisher, the Tilapia acts as a:", listOf("Primary producer", "Primary consumer", "Secondary consumer", "Tertiary consumer"), 2, "Algae (Prod) → Tadpole (1°) → Tilapia (2°) → Kingfisher (3°).", "Secondary consumer."),
      PracticeQuestion("pq_bio_21", "Nitrogen Cycle", "JAMB", 2022, "Which soil bacteria reduce fertility by converting useful nitrates into atmospheric N₂ gas?", listOf("Rhizobium", "Nitrosomonas", "Denitrifying bacteria (Pseudomonas)", "Azotobacter"), 2, "Thrive in waterlogged oxygen-poor soils.", "Denitrifying bacteria."),
      PracticeQuestion("pq_bio_22", "Soil Science", "WAEC", 2024, "Which soil type combines balanced drainage, good aeration, and rich organic humus ideal for crops?", listOf("Sandy soil", "Clay soil", "Loamy soil", "Laterite"), 2, "Sand + Silt + Clay + Humus.", "Loamy soil."),
      PracticeQuestion("pq_bio_23", "Vectors", "JAMB", 2021, "Trypanosoma gambiense, causing African Sleeping Sickness, is transmitted by the:", listOf("Anopheles mosquito", "Tsetse fly (Glossina)", "Blackfly", "Sandfly"), 1, "Feeds on blood along savannah riverbanks.", "Tsetse fly."),
      PracticeQuestion("pq_bio_24", "Immunity", "WAEC", 2025, "Injecting tetanus toxoid vaccine stimulates a patient to develop:", listOf("Natural passive immunity", "Artificial active immunity", "Artificial passive immunity", "Innate allergy"), 1, "Person's own B-cells produce long-term memory + antibodies.", "Artificial active immunity."),
    )
}
