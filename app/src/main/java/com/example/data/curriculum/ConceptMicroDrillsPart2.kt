package com.example.data.curriculum

/**
 * Low-stakes "Check Your Understanding" intermediate drills attached directly inside core
 * concept cards for Chemistry, Biology, Use of English, Economics, and Government.
 */
object ConceptMicroDrillsPart2 {

  fun drillsFor(lessonId: String, sectionIndex: Int): List<MicroDrill> =
    byLesson[lessonId]?.get(sectionIndex).orEmpty()

  fun allDrillsForLesson(lessonId: String): Map<Int, List<MicroDrill>> = byLesson[lessonId].orEmpty()

  private val byLesson: Map<String, Map<Int, List<MicroDrill>>> =
    mapOf(
      // --- CHEMISTRY (15) ---
      "chem_01_separation_kinetic" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which technique separates a miscible ethanol-water liquid mixture by boiling point difference cleanly?",
            options = listOf("Separating funnel", "Fractional distillation", "Paper chromatography", "Sublimation"),
            correctIndex = 1,
            hint = "Miscible liquids with close boiling points require a fractionating column.",
            explanation = "Ethanol (78°C) and water (100°C) are miscible; fractional distillation separates them cleanly.",
          ),
        ),
      ),
      "chem_02_atomic_orbitals" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Write the outer ground-state valence configuration of Chromium (Z = 24).",
            options = listOf("[Ar] 3d⁴ 4s²", "[Ar] 3d⁵ 4s¹", "[Ar] 3d⁶ 4s⁰", "[Ar] 3d³ 4s² 4p¹"),
            correctIndex = 1,
            hint = "Half-filled 3d⁵ gains extra exchange stability.",
            explanation = "Chromium adopts [Ar] 3d⁵ 4s¹ (half-filled d subshell stability).",
          ),
        ),
      ),
      "chem_03_periodic_bonding" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which type of bond forms when NH₃ shares its lone nitrogen electron pair completely with H⁺ to form NH₄⁺?",
            options = listOf("Electrovalent bond", "Dative coordinate bond", "Metallic bond", "Van der Waals bond"),
            correctIndex = 1,
            hint = "One donor species supplies both shared bonding electrons.",
            explanation = "NH₃ donates its lone pair into H⁺'s empty 1s orbital, forming a dative coordinate covalent bond.",
          ),
        ),
      ),
      "chem_04_stoichiometry_moles" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "What volume of O₂ gas at STP reacts completely with 4.0 g of H₂ gas according to 2H₂ + O₂ → 2H₂O (H = 1, V_m = 22.4 dm³)?",
            options = listOf("11.2 dm³", "22.4 dm³", "44.8 dm³", "89.6 dm³"),
            correctIndex = 1,
            hint = "4.0 g H₂ = 2.0 mol H₂, requiring 1.0 mol O₂ by 2:1 ratio.",
            explanation = "n(H₂) = 4.0/2.0 = 2.0 mol → n(O₂) = 1.0 mol → Volume = 1.0 × 22.4 = 22.4 dm³.",
          ),
        ),
      ),
      "chem_05_gas_laws" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Compare effusion speed of H₂ (M = 2) relative to CH₄ (M = 16) using Graham's Law r₁/r₂ = √(M₂/M₁).",
            options = listOf("√2 times faster", "2√2 times faster", "4 times slower", "8 times faster"),
            correctIndex = 1,
            hint = "Compute √(16 / 2) = √8.",
            explanation = "r(H₂)/r(CH₄) = √(16/2) = √8 = 2√2 ≈ 2.83 times faster.",
          ),
        ),
      ),
      "chem_06_acids_bases_titration" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "25.0 cm³ of 0.20 M NaOH neutralizes 20.0 cm³ of H₂SO₄ completely. Find acid molarity C_A.",
            options = listOf("0.125 M", "0.250 M", "0.500 M", "0.100 M"),
            correctIndex = 0,
            hint = "Equation: H₂SO₄ + 2NaOH → Na₂SO₄ + 2H₂O gives n_A/n_B = 1/2.",
            explanation = "C_A = (1 × 0.20 × 25.0) / (2 × 20.0) = 5.0 / 40.0 = 0.125 mol/dm³.",
          ),
        ),
      ),
      "chem_07_thermochemistry" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A reaction has ΔH = +35 kJ/mol and ΔS = +150 J/(mol·K). At 300 K, is it spontaneous (ΔG = ΔH − TΔS)?",
            options = listOf("Yes, ΔG = −10 kJ/mol", "No, ΔG = +80 kJ/mol", "No, ΔG = +44,965 kJ/mol", "Yes, ΔG = −44,965 kJ/mol"),
            correctIndex = 0,
            hint = "Convert ΔS to 0.150 kJ/(mol·K) before subtracting TΔS.",
            explanation = "ΔG = 35 − (300 × 0.150) = 35 − 45 = −10 kJ/mol (< 0, hence spontaneous).",
          ),
        ),
      ),
      "chem_08_kinetics_equilibrium" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "In 2SO₂(g) + O₂(g) ⇌ 2SO₃(g) (ΔH < 0), raising pressure at fixed temperature shifts equilibrium which way?",
            options = listOf("Toward SO₂ + O₂ (left)", "Toward SO₃ (right, fewer gas moles)", "No shift occurs", "Lowers K_p value"),
            correctIndex = 1,
            hint = "Compare 3 moles of reactant gases against 2 moles of product gas.",
            explanation = "Higher pressure favours the side with fewer gaseous moles (3 → 2), producing more SO₃.",
          ),
        ),
      ),
      "chem_09_redox_cells" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Determine the oxidation state of Manganese in KMnO₄.",
            options = listOf("+4", "+5", "+6", "+7"),
            correctIndex = 3,
            hint = "Set K(+1) + Mn + 4×O(−2) = 0.",
            explanation = "+1 + Mn − 8 = 0 solves to Mn = +7.",
          ),
        ),
      ),
      "chem_10_electrolysis" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Calculate the mass of Silver (Ag = 108, z = 1) deposited by passing 0.25 F through AgNO₃(aq).",
            options = listOf("13.5 g", "27.0 g", "54.0 g", "108.0 g"),
            correctIndex = 1,
            hint = "Ag⁺ + e⁻ → Ag uses 1 F per 108 g.",
            explanation = "m = 0.25 × 108 = 27.0 g Ag.",
          ),
        ),
      ),
      "chem_11_nonmetals_water" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which industrial catalyst accelerates Contact Process oxidation of SO₂ to SO₃?",
            options = listOf("Finely divided Iron", "Vanadium(V) oxide (V₂O₅)", "Platinum-rhodium gauze", "Nickel powder"),
            correctIndex = 1,
            hint = "Iron is Haber (NH₃); Pt-Rh is Ostwald (HNO₃).",
            explanation = "Vanadium(V) oxide (V₂O₅) catalyzes 2SO₂ + O₂ ⇌ 2SO₃ in the Contact Process.",
          ),
        ),
      ),
      "chem_12_metals_extraction" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Why is molten cryolite (Na₃AlF₆) added during Hall-Héroult extraction of Aluminium?",
            options = listOf("To oxidize bauxite faster", "To dissolve Al₂O₃ and lower operating temperature from ~2050°C to ~950°C", "To purify iron slag as silicate", "To form an alloy coating"),
            correctIndex = 1,
            hint = "Pure alumina melts above 2000°C.",
            explanation = "Cryolite acts as a molten solvent lowering the bath melting point to ~950°C and raising conductivity.",
          ),
        ),
      ),
      "chem_13_organic_hydrocarbons" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which reagent positively identifies a terminal alkyne like Ethyne (H−C≡C−H) with a brick-red precipitate?",
            options = listOf("Aqueous Bromine water", "Ammoniacal Copper(I) chloride", "Limewater", "Dilute HCl"),
            correctIndex = 1,
            hint = "Both alkenes and alkynes decolourize bromine water, so pick the terminal-alkyne precipitate.",
            explanation = "Ammoniacal CuCl forms red copper(I) dicarbide precipitate specifically with terminal −C≡C−H alkynes.",
          ),
        ),
      ),
      "chem_14_alkanols_esters_polymers" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Heating Propanoic acid with Ethanol and a trace of conc. H₂SO₄ produces which sweet-smelling ester?",
            options = listOf("Propyl ethanoate", "Ethyl propanoate", "Methyl propanoate", "Propyl methanoate"),
            correctIndex = 1,
            hint = "Alcohol gives the '-yl' alkyl part; Acid gives the '-anoate' part.",
            explanation = "Ethanol → Ethyl; Propanoic acid → propanoate → Ethyl propanoate.",
          ),
        ),
      ),
      "chem_15_qualitative_nuclear" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A salt gives a white ppt in NaOH(aq) soluble in excess, and a white ppt in NH₃(aq) ALSO soluble in excess. Identify the cation.",
            options = listOf("Ca²⁺", "Al³⁺", "Pb²⁺", "Zn²⁺"),
            correctIndex = 3,
            hint = "Among ZAP white ppts (Zn²⁺, Al³⁺, Pb²⁺), only one dissolves in excess NH₃.",
            explanation = "Zn²⁺ uniquely dissolves in both excess NaOH and excess aqueous NH₃ to form [Zn(NH₃)₄]²⁺.",
          ),
        ),
      ),

      // --- BIOLOGY (15) ---
      "bio_01_diversity_microscopy" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A chloroplast image measures 4.0 mm across on a diagram drawn at ×800 magnification. Find actual diameter in μm.",
            options = listOf("5.0 μm", "32 μm", "50 μm", "3200 μm"),
            correctIndex = 0,
            hint = "Convert 4.0 mm = 4000 μm, then divide by 800.",
            explanation = "Actual = 4000 μm / 800 = 5.0 μm.",
          ),
        ),
      ),
      "bio_02_cells_osmosis" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "What happens to a red blood cell placed inside distilled water (hypotonic medium)?",
            options = listOf("Plasmolysis occurs", "Haemolysis (swells and bursts)", "Crenation occurs", "Turns flaccid safely"),
            correctIndex = 1,
            hint = "Animal cells lack a rigid cellulose cell wall.",
            explanation = "Endosmosis swells the RBC until its fragile membrane ruptures (haemolysis).",
          ),
        ),
      ),
      "bio_03_photosynthesis_respiration_enzymes" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "From which reactant molecule does the oxygen gas (O₂) released during photosynthesis originate?",
            options = listOf("Carbon dioxide (CO₂)", "Chlorophyll a", "Water (H₂O) during photolysis", "Glucose breakdown"),
            correctIndex = 2,
            hint = "Recall the Light-Stage Hill reaction inside thylakoid membranes.",
            explanation = "Light energy splits water (2H₂O → 4H⁺ + 4e⁻ + O₂) via photolysis.",
          ),
        ),
      ),
      "bio_04_nutrition_digestion" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Why does sucrose fail to turn Benedict's solution brick-red until boiled first with dilute HCl and neutralized?",
            options = listOf("Sucrose contains starch polymers", "Sucrose is a non-reducing disaccharide needing acid hydrolysis into glucose + fructose", "HCl oxidizes Cu²⁺ directly", "Benedict's works only on amino acids"),
            correctIndex = 1,
            hint = "Hydrolysis breaks the glycosidic bond exposing reducing sugar groups.",
            explanation = "Acid hydrolysis cleaves sucrose into reducing monosaccharides (glucose and fructose) that reduce Cu²⁺ to brick-red Cu₂O.",
          ),
        ),
      ),
      "bio_05_transport_circulation" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which mammalian vessel uniquely carries DEOXYGENATED blood away from the Right Ventricle toward the lungs?",
            options = listOf("Pulmonary vein", "Pulmonary artery", "Aorta", "Superior vena cava"),
            correctIndex = 1,
            hint = "Arteries carry blood away from the heart.",
            explanation = "The pulmonary artery pumps deoxygenated blood from the Right Ventricle to lung alveoli.",
          ),
        ),
      ),
      "bio_06_respiration_excretion" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "During dehydration on a hot Harmattan afternoon, pituitary ADH secretion changes collecting-duct permeability how?",
            options = listOf("Decreases water reabsorption → dilute urine", "Increases collecting-duct permeability → small concentrated urine", "Halts glomerular filtration", "Excretes excess glucose"),
            correctIndex = 1,
            hint = "ADH (Antidiuretic Hormone) conserves body water.",
            explanation = "High ADH opens aquaporin channels in the collecting duct, returning water to blood and yielding concentrated urine.",
          ),
        ),
      ),
      "bio_07_nervous_eye_ear" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "How is Myopia (short-sightedness, image focuses IN FRONT of retina) corrected optically?",
            options = listOf("Convex (converging) lens", "Concave (diverging) biconcave lens", "Cylindrical lens only", "Opaque diaphragm"),
            correctIndex = 1,
            hint = "Rays converge too early and must be diverged slightly first.",
            explanation = "A concave diverging lens spreads incoming rays slightly so they focus cleanly back onto the retina.",
          ),
        ),
      ),
      "bio_08_hormones_skeleton_tropisms" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "How does unequal auxin accumulation on the SHADED side of a plant shoot drive positive phototropism?",
            options = listOf("Kills shaded cells", "Stimulates faster cell elongation on the shaded side, bending the shoot toward light", "Stops water uptake on lighted flank", "Converts starch to cellulose"),
            correctIndex = 1,
            hint = "High auxin promotes elongation in stems (unlike roots).",
            explanation = "Higher auxin concentration elongates cells on the dark side faster, curving the shoot tip toward illumination.",
          ),
        ),
      ),
      "bio_09_reproduction_plants_humans" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which pituitary hormone surge around Day 14 triggers ovulation (release of the secondary oocyte)?",
            options = listOf("Progesterone", "Luteinizing Hormone (LH)", "Oxytocin", "Prolactin"),
            correctIndex = 1,
            hint = "Day 14 peak follows high estrogen positive feedback.",
            explanation = "The rapid LH surge ruptures the mature Graafian follicle at Day 14 to release the ovum.",
          ),
        ),
      ),
      "bio_10_mendelian_genetics" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Two sickle-cell trait carrier parents (AS × AS) plan a pregnancy. Find the chance of an unaffected normal AA child.",
            options = listOf("25%", "50%", "75%", "100%"),
            correctIndex = 0,
            hint = "List all 4 Punnett combinations: AA, AS, AS, SS.",
            explanation = "Genotypic ratio 1 AA : 2 AS : 1 SS yields 1/4 (25%) normal AA, 50% AS carrier, and 25% SS.",
          ),
        ),
      ),
      "bio_11_dna_variation_evolution" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Transcribe the DNA template strand 3'-TAC GGA-5' cleanly into complementary mRNA codons.",
            options = listOf("5'-ATG CCT-3'", "5'-AUG CCU-3'", "5'-UAC GGA-3'", "5'-UAG CCU-3'"),
            correctIndex = 1,
            hint = "In RNA, A pairs with U (never T) while T pairs with A, C with G.",
            explanation = "T→A, A→U, C→G, G→C gives mRNA 5'-AUG CCU-3'.",
          ),
        ),
      ),
      "bio_12_ecology_sampling" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "40 tilapia were tagged and released. Later, 50 fish were netted and 10 bore tags. Estimate total pond population (Lincoln Index).",
            options = listOf("100", "200", "400", "2000"),
            correctIndex = 1,
            hint = "Apply N = (M₁ × M₂) / R.",
            explanation = "N = (40 × 50) / 10 = 2000 / 10 = 200 tilapia.",
          ),
        ),
      ),
      "bio_13_biomes_adaptations" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Where are stomata concentrated on floating hydrophytes such as Water Lily (Nymphaea)?",
            options = listOf("Strictly lower epidermis", "Upper epidermis exposed to air", "Submerged root hairs", "Absent everywhere"),
            correctIndex = 1,
            hint = "Undersides sit submerged against liquid water.",
            explanation = "Stomata lie on the upper waxy leaf surface facing atmospheric CO₂ directly.",
          ),
        ),
      ),
      "bio_14_cycles_soil" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which soil bacterium oxidizes nitrites (NO₂⁻) directly into plant-absorbable nitrates (NO₃⁻)?",
            options = listOf("Rhizobium", "Nitrosomonas", "Nitrobacter", "Pseudomonas"),
            correctIndex = 2,
            hint = "Nitrosomonas handles NH₄⁺ → NO₂⁻ first; the second nitrifier finishes NO₂⁻ → NO₃⁻.",
            explanation = "Nitrobacter oxidizes nitrite (NO₂⁻) into nitrate (NO₃⁻).",
          ),
        ),
      ),
      "bio_15_microbes_vectors_conservation" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Identify the exact biological vector that transmits Sleeping Sickness (Trypanosoma brucei).",
            options = listOf("Female Anopheles mosquito", "Tsetse fly (Glossina)", "Aedes aegypti mosquito", "Freshwater snail (Bulinus)"),
            correctIndex = 1,
            hint = "Bulinus transmits Bilharzia; Anopheles transmits Malaria.",
            explanation = "The Tsetse fly transmits Trypanosoma protozoans causing human trypanosomiasis.",
          ),
        ),
      ),

      // --- USE OF ENGLISH (15) ---
      "eng_01_parts_affixes" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Choose the grammatically standard sentence.",
            options = listOf(
              "The bursar purchased new furnitures and equipments yesterday.",
              "The bursar purchased new furniture and equipment yesterday.",
              "The bursar purchased fewer furniture yesterday.",
              "The bursar bought an equipment yesterday.",
            ),
            correctIndex = 1,
            hint = "Both 'furniture' and 'equipment' are uncountable mass nouns.",
            explanation = "Non-count nouns 'furniture' and 'equipment' take neither plural '-s' nor 'an'.",
          ),
        ),
      ),
      "eng_02_clauses_functions" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Identify the grammatical name and function of 'THAT THE CANDIDATE FORGED HER CERTIFICATE' in: 'The panel proved that the candidate forged her certificate.'",
            options = listOf(
              "Adjectival clause qualifying 'panel'",
              "Noun clause, Object of the verb 'proved'",
              "Adverbial clause of reason modifying 'proved'",
              "Prepositional phrase functioning as complement",
            ),
            correctIndex = 1,
            hint = "Substitute 'it': 'The panel proved IT.'",
            explanation = "Replacing it with 'something/it' confirms it is a Noun clause functioning as Direct Object of 'proved'.",
          ),
        ),
      ),
      "eng_03_sentences_modifiers" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Select the relative pronoun that correctly fills: 'The prefect ______ the committee believes deserved the prize graduated top.'",
            options = listOf("who", "whom", "whose", "which"),
            correctIndex = 0,
            hint = "Delete the parenthetical insert 'the committee believes' mentally.",
            explanation = "Without 'the committee believes', the clause reads '______ deserved the prize', requiring subject pronoun 'who'.",
          ),
        ),
      ),
      "eng_04_tenses_conditionals" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Complete correctly: 'If the contractor had inspected the culvert earlier, the road ______.'",
            options = listOf("would not collapse", "would not have collapsed", "will not collapse", "had not collapsed"),
            correctIndex = 1,
            hint = "Type 3 impossible past conditional pairs 'had + V₃' with 'would have + V₃'.",
            explanation = "Type 3 conditional requires 'would not have collapsed' in the main clause.",
          ),
        ),
      ),
      "eng_05_subjunctive_passive" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Convert to Standard Passive Voice: 'The principal made the latecomers sweep the hall.'",
            options = listOf(
              "The latecomers were made sweep the hall.",
              "The latecomers were made to sweep the hall.",
              "The latecomers were making to sweep the hall.",
              "The latecomers made to sweep the hall.",
            ),
            correctIndex = 1,
            hint = "Causative active 'make + bare infinitive' restores 'to' in the passive.",
            explanation = "In passive voice, 'made' requires explicit 'to': 'were made to sweep'.",
          ),
        ),
      ),
      "eng_06_concord" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Fill in the correct verb: 'The Vice-Chancellor, accompanied by three Deans, ______ addressing the freshers.'",
            options = listOf("are", "were", "is", "have been"),
            correctIndex = 2,
            hint = "Ignore parenthetical 'accompanied by three Deans'; match only the singular head subject.",
            explanation = "Singular subject 'The Vice-Chancellor' takes singular verb 'is'.",
          ),
        ),
      ),
      "eng_07_tags_punctuation" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Choose the right question tag: 'Hardly anyone expected that upset victory, ______?'",
            options = listOf("didn't they", "did they", "had they", "was it"),
            correctIndex = 1,
            hint = "'Hardly' carries negative polarity, so the tag must be positive.",
            explanation = "'Hardly anyone' is negative, pairing with positive plural pronoun tag 'did they?'.",
          ),
        ),
      ),
      "eng_08_synonyms_antonyms" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Choose the word MOST NEARLY OPPOSITE in meaning to 'frugal' in: 'Unlike his frugal elder sister, Chinedu lived an ______ life.'",
            options = listOf("thrifty", "extravagant", "prudent", "modest"),
            correctIndex = 1,
            hint = "'Frugal' means sparing/economical; look for wasteful spending.",
            explanation = "'Extravagant' directly opposes 'frugal'.",
          ),
        ),
      ),
      "eng_09_idioms_phrasal_verbs" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Pick the natural placement of the pronoun object in: 'The chairman cancelled the strike yesterday; he called ______.'",
            options = listOf("off it", "it off", "it out", "out it"),
            correctIndex = 1,
            hint = "'Call off' means cancel, and pronoun objects go inside separable verbs.",
            explanation = "Pronoun 'it' sits between verb and particle: 'called it off'.",
          ),
        ),
      ),
      "eng_10_prepositions_confusables" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Select the standard formal English sentence:",
            options = listOf(
              "The delegates discussed about the tariff bill all afternoon.",
              "The panel comprises of five senior fellows.",
              "We congratulated her on her well-deserved fellowship.",
              "Please fill this cheque carefully using stationaries.",
            ),
            correctIndex = 2,
            hint = "Transitive 'discuss' and 'comprise' reject extra prepositions; 'congratulate' pairs with 'on'.",
            explanation = "'Congratulated her on' is standard; 'discuss about' and 'comprises of' are redundant.",
          ),
        ),
      ),
      "eng_11_registers_cloze" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "In judicial register, what term names the party initiating a civil lawsuit against a defendant?",
            options = listOf("Prosecutor", "Plaintiff", "Bailiff", "Arbitrator"),
            correctIndex = 1,
            hint = "Civil suits match Plaintiff vs Defendant (criminal uses Prosecutor vs Accused).",
            explanation = "A civil claimant suing another party is termed the Plaintiff.",
          ),
        ),
      ),
      "eng_12_vowels_diphthongs" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which word contains the SAME vowel sound as the underlined /iː/ in 'key'?",
            options = listOf("great", "quay", "kit", "weight"),
            correctIndex = 1,
            hint = "Check spoken RP pronunciation rather than visual spelling.",
            explanation = "'Quay' is pronounced /kiː/, sharing the identical long /iː/ phoneme with 'key'.",
          ),
        ),
      ),
      "eng_13_consonants_silent" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which past-tense '-ed' verb ends in the voiceless /t/ consonant sound?",
            options = listOf("begged", "washed", "loaded", "cleaned"),
            correctIndex = 1,
            hint = "Test whether the root's final consonant is voiceless (/ʃ/ in wash).",
            explanation = "Voiceless /ʃ/ makes past '-ed' assimilate to voiceless /t/ in 'washed' (/wɒʃt/).",
          ),
        ),
      ),
      "eng_14_stress_rhyme" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Select the correct stress pattern for the 4-syllable word 'photoGRAphic' vs 'phoTOgraphy': which syllable is stressed in 'democRAtic'?",
            options = listOf("DEmocratic", "deMOcratic", "demoCRAtic", "democraTIC"),
            correctIndex = 2,
            hint = "Suffix '-ic' pulls primary stress onto the syllable immediately preceding it.",
            explanation = "With '-ic' ending, primary stress lands on the penultimate syllable: demo-CRA-tic.",
          ),
        ),
      ),
      "eng_15_comprehension_essay" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "After opening an official formal letter with 'Dear Sir,', what complimentary close matches WAEC Paper 2 rules?",
            options = listOf("Yours sincerely, Chidi", "Yours faithfully, + signature + full name", "Your faithful friend, Chidi", "Best regards, C. Okafor"),
            correctIndex = 1,
            hint = "Unknown recipient ('Dear Sir/Madam') requires 'Yours faithfully'.",
            explanation = "Strict WAEC formal convention pairs 'Dear Sir/Madam,' with 'Yours faithfully,' + signature + full name.",
          ),
        ),
      ),

      // --- ECONOMICS (10) ---
      "eco_01_scarcity_ppc" to mapOf(
        2 to listOf(
          MicroDrill(
            prompt = "An economy shifts output from (50 buses, 200 lorries) to (70 buses, 160 lorries). Find opportunity cost of 1 extra bus.",
            options = listOf("0.5 lorries", "2.0 lorries", "40 lorries", "20 lorries"),
            correctIndex = 1,
            hint = "Opportunity cost per bus = ΔLorries sacrificed / ΔBuses gained.",
            explanation = "Sacrificed 40 lorries / Gained 20 buses = 2.0 lorries per bus.",
          ),
        ),
      ),
      "eco_02_utility_demand" to mapOf(
        2 to listOf(
          MicroDrill(
            prompt = "If price drops by 10% and quantity demanded rises by 25%, compute |PED| and predict Total Revenue direction.",
            options = listOf("0.4 (Inelastic) → Revenue falls", "2.5 (Elastic) → Revenue rises", "2.5 (Elastic) → Revenue falls", "1.0 (Unitary) → Revenue flat"),
            correctIndex = 1,
            hint = "|PED| = %ΔQ / %ΔP.",
            explanation = "|PED| = 25% / 10% = 2.5 (> 1, Elastic), so cutting price boosts Total Revenue.",
          ),
        ),
      ),
      "eco_03_supply_equilibrium" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "If Qd = 80 − 2P and Qs = 20 + 4P, calculate equilibrium price P*.",
            options = listOf("NGN 10", "NGN 15", "NGN 20", "NGN 30"),
            correctIndex = 0,
            hint = "Equate Qd = Qs: 80 − 2P = 20 + 4P.",
            explanation = "60 = 6P gives P* = 10 (and Q* = 60).",
          ),
        ),
      ),
      "eco_04_production_division" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Three workers harvest 90 tubers; hiring a fourth raises total output to 112 tubers. Find the 4th worker's Marginal Product.",
            options = listOf("22 tubers", "28 tubers", "37.3 tubers", "112 tubers"),
            correctIndex = 0,
            hint = "MP = ΔTP / ΔL.",
            explanation = "MP₄ = 112 − 90 = 22 tubers (AP = 112/4 = 28).",
          ),
        ),
      ),
      "eco_05_costs_revenue" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "At Q = 10 units, Fixed Cost is NGN 500 and Variable Cost is NGN 1,500. Compute Average Total Cost (ATC).",
            options = listOf("NGN 50", "NGN 150", "NGN 200", "NGN 2,000"),
            correctIndex = 2,
            hint = "TC = FC + VC, then ATC = TC / Q.",
            explanation = "TC = 500 + 1,500 = 2,000; ATC = 2,000 / 10 = NGN 200.",
          ),
        ),
      ),
      "eco_06_market_structures" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "At what output rule does every profit-maximizing firm (Perfect Competition or Monopoly) operate?",
            options = listOf("TR = TC", "MC = MR (with MC rising)", "Price = AFC", "AR = 0"),
            correctIndex = 1,
            hint = "Extra unit adds profit until Marginal Cost catches Marginal Revenue.",
            explanation = "Profit maximizes where Marginal Cost equals Marginal Revenue (MC = MR) and MC cuts MR from below.",
          ),
        ),
      ),
      "eco_07_national_income_multiplier" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "If Marginal Propensity to Consume (MPC) is 0.75, find the investment multiplier k.",
            options = listOf("1.33", "3", "4", "7.5"),
            correctIndex = 2,
            hint = "Use k = 1 / (1 − MPC) = 1 / MPS.",
            explanation = "MPS = 1 − 0.75 = 0.25, giving k = 1 / 0.25 = 4.",
          ),
        ),
      ),
      "eco_08_money_banking_inflation" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "If CBN sets Cash Reserve Ratio at 12.5% (0.125), what maximum deposit multiplier applies to NGN 1 million fresh deposits?",
            options = listOf("NGN 4 million", "NGN 8 million", "NGN 10 million", "NGN 12.5 million"),
            correctIndex = 1,
            hint = "Deposit multiplier = 1 / r.",
            explanation = "Multiplier = 1 / 0.125 = 8 → NGN 8 million total deposit capacity.",
          ),
        ),
      ),
      "eco_09_intl_trade_bop" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "If export price index is 120 and import price index is 150, calculate the Terms of Trade (ToT) index.",
            options = listOf("80", "100", "125", "180"),
            correctIndex = 0,
            hint = "ToT = (Px / Pm) × 100.",
            explanation = "(120 / 150) × 100 = 80 (unfavourable, < 100).",
          ),
        ),
      ),
      "eco_10_growth_development" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Which tax keeps taking a SMALLER fraction of income as earnings climb, hurting low-income buyers hardest (e.g., flat VAT on food)?",
            options = listOf("Progressive tax", "Proportional tax", "Regressive tax", "Capital gains tax"),
            correctIndex = 2,
            hint = "Flat naira tax takes larger share out of small salaries.",
            explanation = "A flat indirect tax on necessities acts as a regressive tax relative to household income.",
          ),
        ),
      ),

      // --- GOVERNMENT (10) ---
      "gov_01_power_authority_state" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Distinguish De Facto from De Jure sovereignty in constitutional studies.",
            options = listOf(
              "De Facto means legal right; De Jure means military takeover",
              "De Jure rests on lawful constitutional title; De Facto rests on actual physical control",
              "Both apply exclusively to cultural Nations",
              "De Jure refers to local councils only",
            ),
            correctIndex = 1,
            hint = "'Jure' shares its root with jurisprudence/justice (law).",
            explanation = "De Jure = rightful legal sovereignty; De Facto = effective factual control.",
          ),
        ),
      ),
      "gov_02_constitutions_rule_law" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "What defines a 'Rigid' constitution such as Nigeria's 1999 Constitution?",
            options = listOf("Written on metallic tablets", "Requires special supermajority and state-assembly ratifications to amend", "Never allows elections", "Unwritten custom only"),
            correctIndex = 1,
            hint = "Contrast with ordinary legislative simple-majority amendment in Britain.",
            explanation = "Rigid constitutions require special legislative majorities beyond ordinary lawmaking steps.",
          ),
        ),
      ),
      "gov_03_organs_checks_balances" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Which constitutional mechanism allows the Judiciary to strike down an unconstitutional statute passed by the National Assembly?",
            options = listOf("Vote of No Confidence", "Judicial Review", "Guillotine motion", "Dissolution"),
            correctIndex = 1,
            hint = "Courts check ultra vires laws against supreme constitutional text.",
            explanation = "Judicial Review empowers courts to declare unconstitutional statutes or executive acts null and void.",
          ),
        ),
      ),
      "gov_04_federalism_local_govt" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Under Nigeria's 1999 Constitution, who prevails when valid Federal and State laws conflict on the Concurrent Legislative List?",
            options = listOf("State Assembly law", "National Assembly Federal law", "Local Council bye-law", "Traditional Emirate Council"),
            correctIndex = 1,
            hint = "Doctrine of 'covering the field' resolves Concurrent conflicts.",
            explanation = "Federal legislation prevails to the extent of inconsistency on the Concurrent List.",
          ),
        ),
      ),
      "gov_05_electoral_parties_opinion" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "What defines 'Gerrymandering' during electoral constituency boundary redrawing?",
            options = listOf("Expanding youth voter registration", "Deliberately shaping electoral districts to unfairly favour one political party", "Counting ballots electronically", "Holding run-off second ballots"),
            correctIndex = 1,
            hint = "Manipulates geographic district maps before voting day.",
            explanation = "Gerrymandering distorts constituency lines to manufacture biased seat advantages.",
          ),
        ),
      ),
      "gov_06_precolonial_indirect_rule" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Why did British Indirect Rule collapse violently in Eastern Nigeria (1929 Aba Women's War)?",
            options = listOf("Excessive Emirate taxation", "Acephaly (non-centralized village democracy) rejected artificial Warrant Chiefs and tax fears", "French Assimilation intervened", "Absence of ports"),
            correctIndex = 1,
            hint = "Igbo societies lacked single autocratic monarchs before colonialism.",
            explanation = "Imposed Warrant Chiefs lacked traditional legitimacy in acephalous Eastern communities.",
          ),
        ),
      ),
      "gov_07_nationalism_1963_const" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Which landmark pre-independence Nigerian Constitution first introduced the Elective Principle (3 Lagos + 1 Calabar seats)?",
            options = listOf("1922 Clifford Constitution", "1946 Richards Constitution", "1951 Macpherson Constitution", "1954 Lyttelton Constitution"),
            correctIndex = 0,
            hint = "Prompted Herbert Macaulay to found NNDP in 1923.",
            explanation = "The 1922 Clifford Constitution introduced Nigeria's first elected legislative seats.",
          ),
        ),
      ),
      "gov_08_1979_1999_military_democ" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Contrast executive accountability in Nigeria's 1963 Parliamentary Republic versus the 1979 Presidential Republic:",
            options = listOf(
              "1963 separated President and PM; 1979 combined both into an Executive President removable only by impeachment",
              "1979 used a hereditary monarch",
              "1963 banned all cabinet ministers from Parliament",
              "Both prevented judicial review",
            ),
            correctIndex = 0,
            hint = "1963 had Ceremonial Azikiwe + PM Balewa; 1979 created Executive Shagari presidency.",
            explanation = "1963 split ceremonial Head of State from Prime Minister; 1979 fused them into a directly elected Executive President.",
          ),
        ),
      ),
      "gov_09_public_service_corporations" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "How can citizens obtain free non-court investigation against administrative bias or unfairness by federal ministries in Nigeria?",
            options = listOf("Central Bank Governor", "Public Complaints Commission (Ombudsman)", "INEC Returning Officer", "Senate Chief Whip"),
            correctIndex = 1,
            hint = "The administrative watchdog created specifically to hear public grievances.",
            explanation = "The Public Complaints Commission serves as Nigeria's Ombudsman investigating maladministration.",
          ),
        ),
      ),
      "gov_10_foreign_policy_ecowas_un" to mapOf(
        1 to listOf(
          MicroDrill(
            prompt = "Which United Nations principal organ holds binding authority under Chapter VII to authorize peacekeeping or sanctions?",
            options = listOf("Trusteeship Council", "Security Council (5 permanent + 10 rotating members)", "UNESCO Secretariat", "International Labour Organization"),
            correctIndex = 1,
            hint = "Holds the P5 veto nations (US, UK, France, Russia, China).",
            explanation = "The UN Security Council carries primary responsibility for international peace and security.",
          ),
        ),
      ),
    )
}
