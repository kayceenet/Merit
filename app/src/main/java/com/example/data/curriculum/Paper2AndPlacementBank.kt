package com.example.data.curriculum

data class MarkingStep(
  val id: String,
  val code: String,
  val description: String,
  val marks: Int,
)

data class Paper2Prompt(
  val id: String,
  val subjectId: String,
  val examBoard: String,
  val paperLabel: String,
  val topic: String,
  val prompt: String,
  val totalMarks: Int,
  val rubricSteps: List<MarkingStep>,
  val modelSolution: String,
)

object Paper2AndPlacementBank {

  fun paper2PromptsFor(subjectId: String): List<Paper2Prompt> =
    allPaper2Prompts.filter { it.subjectId == subjectId }

  fun placementQuestionsFor(subjectId: String): List<PracticeQuestion> {
    val track = CurriculumCatalog.trackById(subjectId) ?: return emptyList()
    val lessons = track.allLessons
    val sampledFromFoundations =
      lessons.take((lessons.size * 3 / 5).coerceAtLeast(3)).mapNotNull { it.exercises.firstOrNull() }
    val pool = (sampledFromFoundations + track.pastQuestions).distinctBy { it.id }
    return pool.take(8)
  }

  val allPaper2Prompts: List<Paper2Prompt> =
    listOf(
      // --- PHYSICS ---
      Paper2Prompt(
        id = "p2_phy_1",
        subjectId = "physics",
        examBoard = "WAEC Paper 2",
        paperLabel = "Essay & Derivation (Part II)",
        topic = "Projectiles & Equilibrium",
        prompt =
          "A mortar shell is fired from ground level at u = 50 m/s at 30° above the horizontal (g = 10 m/s^2).\n(a) State the physical condition that holds at the maximum height.\n(b) Calculate the time to reach maximum height, total time of flight, and horizontal range R.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_phy_1_1", "B1", "State vertical velocity v_y = 0 at maximum height (a_y = -g continues acting downward).", 2),
            MarkingStep("p2_phy_1_2", "M1", "Substitute u_y = u\\sin\\theta = 50\\sin 30^\\circ = 25 m/s into t = \\frac{u\\sin\\theta}{g}.", 2),
            MarkingStep("p2_phy_1_3", "A1", "Obtain time to peak t = 2.5 s and total time of flight T = 2t = 5.0 s.", 3),
            MarkingStep("p2_phy_1_4", "M1/A1", "Apply R = u\\cos\\theta \\cdot T = (50\\cos 30^\\circ)(5.0) \\approx 216.5 m.", 3),
          ),
        modelSolution =
          "At peak height, vertical velocity v_y = 0 while gravity g = 10 m/s^2 downward persists.\n" +
            "u_y = 50\\sin 30^\\circ = 25 m/s, u_x = 50\\cos 30^\\circ = 43.30 m/s.\n" +
            "t = \\frac{25}{10} = 2.5 s \\implies T = 2(2.5) = 5.0 s.\n" +
            "R = u_x T = 43.30 \\times 5.0 = 216.5 m.",
      ),
      Paper2Prompt(
        id = "p2_phy_2",
        subjectId = "physics",
        examBoard = "WAEC Paper 3",
        paperLabel = "Alternative to Practical (Optics)",
        topic = "Converging Lens Verification",
        prompt =
          "In a converging-lens experiment, sharp screen images were obtained for object distances u = 25.0, 30.0, 40.0 cm corresponding to v = 37.5, 30.0, 24.0 cm.\n(a) State two precautions taken to obtain an accurate focal length f.\n(b) Use one conjugate pair to verify f and explain why a graph of \\frac{1}{v} against \\frac{1}{u} is preferred over a single trial.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_phy_2_1", "B1", "Two valid precautions: align optical centres coaxially on bench; avoid parallax when reading scale.", 2),
            MarkingStep("p2_phy_2_2", "M1", "Apply \\frac{1}{f} = \\frac{1}{u} + \\frac{1}{v} with u = 30.0 cm, v = 30.0 cm (magnification m = 1 at 2f).", 3),
            MarkingStep("p2_phy_2_3", "A1", "Compute \\frac{1}{f} = \\frac{1}{30} + \\frac{1}{30} = \\frac{1}{15} \\implies f = 15.0 cm.", 3),
            MarkingStep("p2_phy_2_4", "B1", "Explain graphing averages out random error across trials and yields intercept \\frac{1}{f} directly.", 2),
          ),
        modelSolution =
          "Precautions: (1) Place optical pin, lens centre, and screen centre along one horizontal axis. (2) Keep eye perpendicular to bench rule to avoid parallax.\n" +
            "At u = 30.0 cm, v = 30.0 cm: \\frac{1}{f} = \\frac{1}{30} + \\frac{1}{30} = \\frac{2}{30} \\implies f = 15.0 cm.\n" +
            "Plotting \\frac{1}{v} vs \\frac{1}{u} gives slope -1 and intercepts \\frac{1}{f}, minimizing random error across multiple trials.",
      ),
      Paper2Prompt(
        id = "p2_phy_3",
        subjectId = "physics",
        examBoard = "NECO Paper 2",
        paperLabel = "Structured Theory",
        topic = "Internal Resistance & Terminal PD",
        prompt =
          "A cell of EMF E = 3.0 V and internal resistance r = 1.0 \\Omega supplies current to an external resistor R = 5.0 \\Omega.\nCalculate: (a) current I in the circuit, (b) terminal potential difference V, and (c) lost volts across internal resistance.",
        totalMarks = 9,
        rubricSteps =
          listOf(
            MarkingStep("p2_phy_3_1", "M1", "Recall E = I(R + r) and write I = \\frac{3.0}{5.0 + 1.0}.", 3),
            MarkingStep("p2_phy_3_2", "A1", "Obtain I = 0.50 A.", 2),
            MarkingStep("p2_phy_3_3", "M1/A1", "Terminal p.d. V = IR = 0.50 \\times 5.0 = 2.50 V.", 2),
            MarkingStep("p2_phy_3_4", "A1", "Lost volts = Ir = E - V = 0.50 \\times 1.0 = 0.50 V.", 2),
          ),
        modelSolution =
          "I = \\frac{E}{R + r} = \\frac{3.0}{5.0 + 1.0} = 0.50 A.\n" +
            "Terminal p.d. V = IR = 0.50 \\times 5.0 = 2.50 V.\n" +
            "Lost volts = Ir = 0.50 \\times 1.0 = 0.50 V (check: V + Ir = 2.50 + 0.50 = 3.00 V = E).",
      ),

      // --- MATHEMATICS ---
      Paper2Prompt(
        id = "p2_math_1",
        subjectId = "mathematics",
        examBoard = "WAEC Paper 2",
        paperLabel = "Compulsory Theory (Section A)",
        topic = "Logarithms & Surds",
        prompt =
          "Without using mathematical tables or calculator:\n(a) Simplify \\frac{\\sqrt{48} - \\sqrt{12}}{\\sqrt{3}}.\n(b) Solve for x if \\log_{10}(x + 4) + \\log_{10}(x - 2) = 1 + \\log_{10} 2 (state why any negative root is rejected).",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_math_1_1", "M1/A1", "Factor perfect squares: \\sqrt{48} = 4\\sqrt{3}, \\sqrt{12} = 2\\sqrt{3}, giving \\frac{2\\sqrt{3}}{\\sqrt{3}} = 2.", 3),
            MarkingStep("p2_math_1_2", "M1", "Combine logs & replace 1 = \\log_{10} 10: \\log_{10}[(x+4)(x-2)] = \\log_{10} 20.", 2),
            MarkingStep("p2_math_1_3", "M1", "Expand quadratic x^2 + 2x - 8 = 20 \\implies x^2 + 2x - 28 = 0 or check roots carefully; wait if equation solved properly use formula x = -1 \\pm \\sqrt{29} or factor cleanly.", 3),
            MarkingStep("p2_math_1_4", "B1/A1", "Identify x > 2 domain so x - 2 > 0, keeping only positive valid root x = \\sqrt{29} - 1.", 2),
          ),
        modelSolution =
          "(a) \\frac{4\\sqrt{3} - 2\\sqrt{3}}{\\sqrt{3}} = \\frac{2\\sqrt{3}}{\\sqrt{3}} = 2.\n" +
            "(b) \\log_{10}[(x+4)(x-2)] = \\log_{10}(10 \\times 2) = \\log_{10} 20.\n" +
            "Hence x^2 + 2x - 8 = 20 \\implies x^2 + 2x - 28 = 0.\n" +
            "x = \\frac{-2 \\pm \\sqrt{4 + 112}}{2} = -1 \\pm \\sqrt{29}.\n" +
            "Since \\log_{10}(x - 2) requires x > 2, x = \\sqrt{29} - 1 (approx 4.385).",
      ),
      Paper2Prompt(
        id = "p2_math_2",
        subjectId = "mathematics",
        examBoard = "WAEC Paper 2",
        paperLabel = "Section B (Calculus & Mensuration)",
        topic = "Stationary Points & Curve Area",
        prompt =
          "Given the curve y = x^3 - 3x^2 - 9x + 5:\n(a) Find the coordinates of the stationary points.\n(b) Use the second derivative \\frac{d^2y}{dx^2} to classify each point as maximum or minimum.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_math_2_1", "M1", "Differentiate: \\frac{dy}{dx} = 3x^2 - 6x - 9 = 0.", 2),
            MarkingStep("p2_math_2_2", "A1", "Divide by 3: (x - 3)(x + 1) = 0 \\implies x = -1, 3.", 2),
            MarkingStep("p2_math_2_3", "A1", "Compute y-values: (-1, 10) and (3, -22).", 3),
            MarkingStep("p2_math_2_4", "M1/A1", "Evaluate \\frac{d^2y}{dx^2} = 6x - 6: at x = -1 (-12 < 0 \\implies local max); at x = 3 (+12 > 0 \\implies local min).", 3),
          ),
        modelSolution =
          "\\frac{dy}{dx} = 3x^2 - 6x - 9 = 3(x - 3)(x + 1) = 0 \\implies x = -1 \\text{ or } 3.\n" +
            "At x = -1, y = (-1)^3 - 3(-1)^2 - 9(-1) + 5 = 10 \\implies (-1, 10).\n" +
            "At x = 3, y = 27 - 27 - 27 + 5 = -22 \\implies (3, -22).\n" +
            "\\frac{d^2y}{dx^2} = 6x - 6: at x = -1, -12 < 0 (maximum); at x = 3, +12 > 0 (minimum).",
      ),
      Paper2Prompt(
        id = "p2_math_3",
        subjectId = "mathematics",
        examBoard = "NECO Paper 2",
        paperLabel = "Geometry & Circle Theorems",
        topic = "Cyclic Quadrilaterals & Tangents",
        prompt =
          "Points P, Q, R, S lie on a circle with centre O. Tangent TP touches the circle at P such that \\angle TPQ = 58^\\circ and \\angle QPR = 34^\\circ.\nFind, with stated geometric reasons: (a) \\angle PRQ, (b) \\angle QOR, and (c) \\angle PSR if P, Q, R, S is cyclic in order.",
        totalMarks = 9,
        rubricSteps =
          listOf(
            MarkingStep("p2_math_3_1", "M1/A1", "Apply Alternate Segment Theorem: \\angle PRQ = \\angle TPQ = 58^\\circ.", 3),
            MarkingStep("p2_math_3_2", "B1/A1", "Angle at centre = 2 × angle at circumference: \\angle QPR = 34^\\circ \\implies \\angle QOR = 2(34^\\circ) = 68^\\circ.", 3),
            MarkingStep("p2_math_3_3", "M1/A1", "Wait find \\angle PQR = 180^\\circ - (58^\\circ + 34^\\circ) = 88^\\circ, then opposite angles of cyclic quad: \\angle PSR = 180^\\circ - 88^\\circ = 92^\\circ.", 3),
          ),
        modelSolution =
          "(a) \\angle PRQ = \\angle TPQ = 58^\\circ (angle between tangent and chord equals angle in alternate segment).\n" +
            "(b) \\angle QOR = 2 \\times \\angle QPR = 2 \\times 34^\\circ = 68^\\circ (angle at centre is twice angle at circumference).\n" +
            "(c) In \\triangle PQR, \\angle PQR = 180^\\circ - (34^\\circ + 58^\\circ) = 88^\\circ. Thus \\angle PSR = 180^\\circ - 88^\\circ = 92^\\circ (opposite angles of a cyclic quadrilateral are supplementary).",
      ),

      // --- CHEMISTRY ---
      Paper2Prompt(
        id = "p2_chem_1",
        subjectId = "chemistry",
        examBoard = "WAEC Paper 3",
        paperLabel = "Volumetric Practical (Titration)",
        topic = "Stoichiometry & Molarity",
        prompt =
          "25.0 cm^3 of 0.10 mol/dm^3 Na_2CO_3(aq) required 20.0 cm^3 of dilute HCl(aq) for complete neutralization using methyl orange indicator.\nEquation: Na_2CO_3 + 2HCl \\to 2NaCl + H_2O + CO_2\nCalculate: (a) concentration of HCl in mol/dm^3, (b) mass concentration of HCl in g/dm^3 (H = 1, Cl = 35.5), and (c) colour change at endpoint.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_chem_1_1", "B1", "State mole ratio \\frac{n_A}{n_B} = \\frac{2}{1} from balanced equation.", 2),
            MarkingStep("p2_chem_1_2", "M1", "Substitute into \\frac{C_A V_A}{C_B V_B} = \\frac{n_A}{n_B}: \\frac{C_A \\times 20.0}{0.10 \\times 25.0} = \\frac{2}{1}.", 3),
            MarkingStep("p2_chem_1_3", "A1", "Compute C_A = 0.25 mol/dm^3 and M_{HCl} = 36.5 g/mol \\implies \\rho = 0.25 \\times 36.5 = 9.125 g/dm^3.", 3),
            MarkingStep("p2_chem_1_4", "B1", "Methyl orange shifts from yellow (alkali) to orange/pink at acid endpoint.", 2),
          ),
        modelSolution =
          "From \\frac{C_A V_A}{C_B V_B} = \\frac{2}{1}: C_A = \\frac{2 \\times 0.10 \\times 25.0}{20.0} = 0.25 mol/dm^3.\n" +
            "Mass conc. = C_A \\times M_{HCl} = 0.25 \\times 36.5 = 9.125 g/dm^3.\n" +
            "Indicator shift (acid in burette into base): yellow to orange-pink.",
      ),
      Paper2Prompt(
        id = "p2_chem_2",
        subjectId = "chemistry",
        examBoard = "WAEC Paper 3",
        paperLabel = "Qualitative Analysis Bench",
        topic = "Cation & Anion Identification",
        prompt =
          "Salt X dissolves in water to form a blue solution.\n(i) With NaOH(aq) in drops then excess, a pale-blue gelatinous precipitate insoluble in excess forms.\n(ii) With NH_3(aq) in drops then excess, a pale-blue precipitate dissolves in excess to form a deep-blue solution.\n(iii) Acidified BaCl_2(aq) forms a white precipitate insoluble in dilute HCl.\nDeduce X, write both key ionic equations, and explain the deep-blue complex.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_chem_2_1", "B1", "Identify cation Cu^{2+} and anion SO_4^{2-} (Salt X = CuSO_4).", 3),
            MarkingStep("p2_chem_2_2", "B1", "Cu^{2+}(aq) + 2OH^-(aq) \\to Cu(OH)_2(s) pale-blue ppt.", 2),
            MarkingStep("p2_chem_2_3", "B1", "In excess NH_3: Cu(OH)_2 + 4NH_3 \\to [Cu(NH_3)_4]^{2+} + 2OH^- (deep-blue tetraamminecopper(II)).", 3),
            MarkingStep("p2_chem_2_4", "B1", "Anion ionic equation: Ba^{2+}(aq) + SO_4^{2-}(aq) \\to BaSO_4(s) insoluble in dilute HCl.", 2),
          ),
        modelSolution =
          "Salt X is Copper(II) tetraoxosulphate(VI), CuSO_4.\n" +
            "Cu^{2+}(aq) + 2OH^-(aq) \\to Cu(OH)_2(s).\n" +
            "Excess NH_3 dissolves Cu(OH)_2 to give the soluble deep-blue complex [Cu(NH_3)_4]^{2+}(aq).\n" +
            "White BaSO_4(s) insoluble in HCl confirms SO_4^{2-} (ruling out CO_3^{2-} or SO_3^{2-}).",
      ),
      Paper2Prompt(
        id = "p2_chem_3",
        subjectId = "chemistry",
        examBoard = "NECO Paper 2",
        paperLabel = "Electrochemistry",
        topic = "Faraday's Laws of Electrolysis",
        prompt =
          "A steady current of 2.5 A is passed through aqueous CuSO_4 using inert platinum electrodes for 32 minutes 10 seconds (Cu = 63.5, 1 F = 96500 C/mol).\nCalculate the mass of copper deposited at the cathode and explain why the blue electrolyte turns acidic and colourless.",
        totalMarks = 9,
        rubricSteps =
          listOf(
            MarkingStep("p2_chem_3_1", "M1", "Convert time t = (32 \\times 60) + 10 = 1930 s and compute Q = It = 2.5 \\times 1930 = 4825 C.", 3),
            MarkingStep("p2_chem_3_2", "M1/A1", "Use Cu^{2+} + 2e^- \\to Cu: m = \\frac{63.5 \\times 4825}{2 \\times 96500} = 1.5875 g.", 4),
            MarkingStep("p2_chem_3_3", "B1", "Cathode removes Cu^{2+} while anode discharges OH^- generating H_2SO_4(aq) as solution fades.", 2),
          ),
        modelSolution =
          "t = 1930 s, Q = 2.5 \\times 1930 = 4825 C (0.05 F).\n" +
            "Since 2 F deposit 63.5 g Cu, m = \\frac{0.05}{2} \\times 63.5 = 1.588 g.\n" +
            "As Cu^{2+} deposits and OH^- discharges as O_2 at Pt anode, H^+ and SO_4^{2-} remain as dilute H_2SO_4.",
      ),

      // --- BIOLOGY ---
      Paper2Prompt(
        id = "p2_bio_1",
        subjectId = "biology",
        examBoard = "WAEC Paper 2",
        paperLabel = "Section A Theory (Genetics)",
        topic = "Mendelian Cross & Sickle-Cell Inheritance",
        prompt =
          "Both parents have genotype Hb^A Hb^S (sickle-cell trait carriers).\n(a) Draw/write a complete genetic cross showing gametes and F_1 genotypes.\n(b) State the exact phenotypic probability ratio and percentage chance of any one pregnancy producing an SS (sickle-cell anaemia) child.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_bio_1_1", "B1", "State parental genotypes Hb^A Hb^S \\times Hb^A Hb^S and separate gametes A, S for each parent.", 3),
            MarkingStep("p2_bio_1_2", "A1", "Correctly list F_1 genotypes: 1 AA : 2 AS : 1 SS.", 3),
            MarkingStep("p2_bio_1_3", "B1/A1", "Phenotypes: 75% unaffected (25% AA normal + 50% AS trait carrier), 25% (\\frac{1}{4}) SS child per pregnancy.", 4),
          ),
        modelSolution =
          "Parents: AS \\times AS \\implies Gametes: A, S and A, S.\n" +
            "F_1 offspring: AA, AS, AS, SS (Genotypic ratio 1 AA : 2 AS : 1 SS).\n" +
            "Every individual pregnancy has an independent 25% (1 in 4) chance of SS.",
      ),
      Paper2Prompt(
        id = "p2_bio_2",
        subjectId = "biology",
        examBoard = "WAEC Paper 3",
        paperLabel = "Alternative to Practical",
        topic = "Food Tests & Osmosis Experiment",
        prompt =
          "Describe a controlled laboratory procedure using potato cylinders of identical dimensions to investigate osmosis across 0% (distilled water) and 10% sucrose solution.\nInclude measured variables, expected length/turgidity outcome in both dishes, and biological explanation via water potential.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_bio_2_1", "B1", "Cut equal potato cylinders, blot dry, measure initial length/mass before submerging for 45 mins.", 3),
            MarkingStep("p2_bio_2_2", "B1", "Distilled water (hypotonic): cylinder swells, gains length/mass, feels firm (turgid).", 3),
            MarkingStep("p2_bio_2_3", "B1", "10% sucrose (hypertonic): cylinder shrinks, loses mass, feels flaccid (plasmolysis).", 2),
            MarkingStep("p2_bio_2_4", "B1", "Explain water moves down water potential gradient across selectively permeable cell membrane.", 2),
          ),
        modelSolution =
          "Measure initial length/mass of equal blotted potato strips; place in distilled water and 10% sucrose for equal duration.\n" +
            "In distilled water (higher water potential outside), endosmosis enters cells causing turgidity.\n" +
            "In 10% sucrose (lower water potential outside), exosmosis leaves vacuole causing flaccidity/plasmolysis.",
      ),
      Paper2Prompt(
        id = "p2_bio_3",
        subjectId = "biology",
        examBoard = "NECO Paper 2",
        paperLabel = "Organ Systems & Ecology",
        topic = "Nitrogen Cycle Bacteria Roles",
        prompt =
          "Distinguish clearly between the exact roles of the following four bacteria in the nitrogen cycle:\n(i) Rhizobium, (ii) Nitrosomonas, (iii) Nitrobacter, and (iv) Pseudomonas (in waterlogged soil).",
        totalMarks = 8,
        rubricSteps =
          listOf(
            MarkingStep("p2_bio_3_1", "B1", "Rhizobium (legume root nodules): fixes atmospheric N_2 into ammonium/organic nitrogen.", 2),
            MarkingStep("p2_bio_3_2", "B1", "Nitrosomonas (nitrification step 1): oxidizes NH_4^+ \\to NO_2^- (nitrite).", 2),
            MarkingStep("p2_bio_3_3", "B1", "Nitrobacter (nitrification step 2): oxidizes NO_2^- \\to NO_3^- (absorbable nitrate).", 2),
            MarkingStep("p2_bio_3_4", "B1", "Pseudomonas (denitrification): reduces NO_3^- back to atmospheric N_2 under anaerobic conditions.", 2),
          ),
        modelSolution =
          "(i) Rhizobium: symbiotic nitrogen fixation (N_2 \\to NH_4^+) in legume root nodules.\n" +
            "(ii) Nitrosomonas: oxidizes ammonia/ammonium to nitrite (NO_2^-).\n" +
            "(iii) Nitrobacter: oxidizes nitrite to plant-absorbable nitrate (NO_3^-).\n" +
            "(iv) Pseudomonas: denitrifying bacteria reducing soil nitrate back to N_2 gas in waterlogged anaerobic soils.",
      ),

      // --- USE OF ENGLISH ---
      Paper2Prompt(
        id = "p2_eng_1",
        subjectId = "english",
        examBoard = "WAEC Paper 2",
        paperLabel = "Section C: Summary Writing",
        topic = "Own-Word Paraphrase & Penalty Avoidance",
        prompt =
          "Read the short extract and summarize in TWO separate single sentences the measures recommended to reduce urban flooding:\n\"Unplanned erection of concrete shops across natural drainage channels stops storm water from flowing toward rivers, while indiscriminate dumping of sachet-water plastics blocks underground culverts completely. Unless municipal authorities demolish illegal structures along waterways and enforce strict waste-collection bylaws, rainy-season inundation will continue.\"",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_eng_1_1", "C5", "Sentence 1 states removing/clearing buildings obstructing drainage channels (in own words, not verbatim lifting).", 4),
            MarkingStep("p2_eng_1_2", "C5", "Sentence 2 states regulating plastic refuse disposal / enforcing waste management laws (complete sentence).", 4),
            MarkingStep("p2_eng_1_3", "P-Check", "No preamble/intro sentence, strictly numbered 2 complete sentences, zero verbatim phrase lifting.", 2),
          ),
        modelSolution =
          "1. City authorities should clear unauthorized buildings erected across drainage routes.\n" +
            "2. Municipalities must enforce waste-disposal regulations to keep underground drains clear of plastic refuse.",
      ),
      Paper2Prompt(
        id = "p2_eng_2",
        subjectId = "english",
        examBoard = "WAEC Paper 2",
        paperLabel = "Section A: Formal Letter Structure",
        topic = "Formal Letter to the Commissioner / Principal",
        prompt =
          "Outline the exact WAEC Paper 2 formal conventions for writing to your State Commissioner for Education requesting science laboratory equipment:\nSpecify: (a) addresses required, (b) salutation, (c) heading/title rule, (d) register & contractions rule, and (e) complimentary close + signature.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_eng_2_1", "F1", "Both writer's address (top right + full date) AND recipient's official title & address (left margin).", 2),
            MarkingStep("p2_eng_2_2", "F1", "Formal salutation: 'Dear Sir,' or 'Dear Madam,' (never first name).", 2),
            MarkingStep("p2_eng_2_3", "F1", "Concise underlined/capitalized title above Paragraph 1; formal tone with zero slang or contractions ('do not', not 'don't').", 3),
            MarkingStep("p2_eng_2_4", "F1", "Complimentary close strictly 'Yours faithfully,' + handwritten signature + printed full name in full.", 3),
          ),
        modelSolution =
          "1. Writer's address & date (top right); Recipient's official designation & address (left).\n" +
            "2. Salutation: 'Dear Sir,' / 'Dear Madam,'.\n" +
            "3. Capitalized/underlined Heading stating request.\n" +
            "4. Formal standard English throughout (zero contractions like can't/won't, zero colloquialisms).\n" +
            "5. Close: 'Yours faithfully,' + Signature + Full Name.",
      ),
      Paper2Prompt(
        id = "p2_eng_3",
        subjectId = "english",
        examBoard = "NECO Paper 2",
        paperLabel = "Section B: Grammatical Functions",
        topic = "Clause Type & Grammatical Function Identification",
        prompt =
          "Analyze the italicized clause in: \"The scholarship board honoured the candidate WHO DEVELOPED AN AFFORDABLE SOLAR WATER FILTER.\"\n(a) State its exact grammatical name.\n(b) State its exact grammatical function.\n(c) Rewrite using an active participial/non-finite modifier without ambiguity.",
        totalMarks = 6,
        rubricSteps =
          listOf(
            MarkingStep("p2_eng_3_1", "B1", "Grammatical name: Relative / Adjectival clause (introduced by 'who').", 2),
            MarkingStep("p2_eng_3_2", "B1", "Grammatical function: Qualifies the noun phrase 'the candidate'.", 2),
            MarkingStep("p2_eng_3_3", "B1", "Clean rewrite: 'Having developed an affordable solar water filter, the candidate was honoured by the scholarship board.'", 2),
          ),
        modelSolution =
          "(a) Grammatical name: Adjectival (Relative) clause.\n" +
            "(b) Function: Modifies / qualifies the noun phrase 'the candidate'.\n" +
            "(c) Having developed an affordable solar water filter, the candidate received an award from the scholarship board.",
      ),

      // --- ECONOMICS ---
      Paper2Prompt(
        id = "p2_eco_1",
        subjectId = "economics",
        examBoard = "WAEC Paper 2",
        paperLabel = "Data Response & Calculation",
        topic = "Elasticity of Demand & Tax Incidence",
        prompt =
          "A sachet-water manufacturer raises factory price from NGN 200 to NGN 250 per bag, causing weekly quantity demanded to fall from 10,000 bags to 8,500 bags.\n(a) Compute the numerical Price Elasticity of Demand (|PED|) and classify whether demand is elastic or inelastic.\n(b) Compute Total Revenue before and after the price rise and explain who bears most of a NGN 20 per-bag excise tax under this elasticity.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_eco_1_1", "M1", "Compute %ΔP = (50/200)×100 = +25% and %ΔQd = (−1,500/10,000)×100 = −15%.", 3),
            MarkingStep("p2_eco_1_2", "A1", "Obtain |PED| = 15% / 25% = 0.60 (Price Inelastic since 0.60 < 1).", 3),
            MarkingStep("p2_eco_1_3", "M1/A1", "Total Revenue rises from NGN 2,000,000 (200×10,000) to NGN 2,125,000 (250×8,500).", 2),
            MarkingStep("p2_eco_1_4", "B1", "Because |PED| < 1 (inelastic necessity), CONSUMERS bear the larger share of an indirect excise tax.", 2),
          ),
        modelSolution =
          "(a) %ΔP = +25%, %ΔQd = −15% → |PED| = 15 / 25 = 0.60 (Inelastic).\n" +
            "(b) TR₀ = 200 × 10,000 = NGN 2,000,000; TR₁ = 250 × 8,500 = NGN 2,125,000 (TR rises!). With inelastic demand, buyers absorb most of the NGN 20 tax via higher retail price.",
      ),
      Paper2Prompt(
        id = "p2_eco_2",
        subjectId = "economics",
        examBoard = "NECO Paper 2",
        paperLabel = "Macroeconomic Essay & Multiplier",
        topic = "National Income, Multiplier & CBN Open Market Operations",
        prompt =
          "In a closed economy without taxes, Marginal Propensity to Save (MPS) is 0.20.\n(a) Calculate the Keynesian Investment Multiplier k and find total income expansion generated by NGN 40 billion new public infrastructure.\n(b) If inflation climbs toward 25% thereafter, explain two Central Bank monetary instruments to stabilize prices.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_eco_2_1", "M1/A1", "State k = 1 / MPS = 1 / 0.20 = 5.", 3),
            MarkingStep("p2_eco_2_2", "A1", "Compute ΔY = 5 × NGN 40B = NGN 200 billion.", 3),
            MarkingStep("p2_eco_2_3", "B2", "Instrument 1: OMO — CBN SELLS Treasury Bills/bonds to banks and public to withdraw excess liquidity.", 2),
            MarkingStep("p2_eco_2_4", "B2", "Instrument 2: Hike Monetary Policy Rate (MPR) and Cash Reserve Ratio (CRR) to raise loan cost and shrink bank credit multiplier.", 2),
          ),
        modelSolution =
          "(a) k = 1 / 0.20 = 5 → ΔY = 5 × 40B = NGN 200 billion.\n" +
            "(b) CBN combats inflation by: (1) Selling government Treasury Bills via Open Market Operations (mopping cash reserves), and (2) Raising MPR & Cash Reserve Ratio (contracting bank lending capacity).",
      ),

      // --- GOVERNMENT ---
      Paper2Prompt(
        id = "p2_gov_1",
        subjectId = "government",
        examBoard = "WAEC Paper 2",
        paperLabel = "Comparative Institutions Essay",
        topic = "1963 Parliamentary vs 1999 Presidential System",
        prompt =
          "Contrast Nigeria's 1963 First Republican Parliamentary model with the 1999 Presidential Constitution under four clear heads:\n(a) Head of State vs Head of Government, (b) Executive-Legislature relationship (Fusion vs Separation), (c) Ministerial Tenure & Removal, and (d) Collective Responsibility vs Presidential Accountability.",
        totalMarks = 12,
        rubricSteps =
          listOf(
            MarkingStep("p2_gov_1_1", "B3", "1963 split Dual Executive (Ceremonial President vs PM Balewa); 1999 fuses BOTH roles in Executive President.", 3),
            MarkingStep("p2_gov_1_2", "B3", "1963 fused Cabinet Ministers inside Parliament (MPs); 1999 bars sitting Ministers from National Assembly membership.", 3),
            MarkingStep("p2_gov_1_3", "B3", "Parliamentary PM removed anytime by simple Vote of No Confidence; Executive President enjoys fixed 4-yr tenure barring 2/3 impeachment.", 3),
            MarkingStep("p2_gov_1_4", "B3", "Parliamentary Collective Responsibility requires united Cabinet defense or resignation; Presidential Ministers answer President individually.", 3),
          ),
        modelSolution =
          "1. Executive Structure: Dual (Azikiwe/Balewa) in 1963 vs Single Executive President in 1999.\n" +
            "2. Fusion vs Separation: 1963 ministers remained sitting MPs; 1999 mandates strict separation.\n" +
            "3. Removal: Simple No-Confidence vote topples Parliamentary PM; 2/3 Impeachment for gross misconduct required in Presidential system.\n" +
            "4. Responsibility: Collective Cabinet solidarity (1963) vs Presidential individual ministerial appointment/dismissal (1999).",
      ),
      Paper2Prompt(
        id = "p2_gov_2",
        subjectId = "government",
        examBoard = "NECO Paper 2",
        paperLabel = "Nigerian Constitutional History",
        topic = "Indirect Rule Performance & 1922–1954 Constitutional Steps",
        prompt =
          "(a) Explain why British Indirect Rule achieved compliance in Northern Nigeria yet collapsed in Eastern Nigeria (1929 Aba Women's War).\n(b) State the distinct historical contribution of the 1922 Clifford and 1954 Lyttelton Constitutions.",
        totalMarks = 10,
        rubricSteps =
          listOf(
            MarkingStep("p2_gov_2_1", "B3", "North: pre-existing centralized Emirates, Sharia Alkali courts & Jangali/Kharaj tax customs fit Indirect Rule directly.", 3),
            MarkingStep("p2_gov_2_2", "B3", "East: decentralized/acephalous Igbo assemblies had no monarchs; artificial Warrant Chiefs & direct tax fears sparked 1929 Aba Women's War.", 3),
            MarkingStep("p2_gov_2_3", "B2", "Clifford 1922: introduced first Elective Principle (3 Lagos + 1 Calabar seats), prompting Macaulay's NNDP (1923).", 2),
            MarkingStep("p2_gov_2_4", "B2", "Lyttelton 1954: instituted True Federalism with Exclusive/Concurrent/Residual legislative lists and Regional Premiers.", 2),
          ),
        modelSolution =
          "(a) Northern Emirates possessed centralized hierarchy, courts, and regular direct taxation ready for Lugard's officers; Eastern Igboland was acephalous and egalitarian, so Britain's imposed Warrant Chiefs lacked legitimacy and triggered the 1929 Aba Women's War.\n" +
            "(b) 1922 Clifford introduced Nigeria's first Elective Principle (4 elected seats); 1954 Lyttelton established True Federalism with distinct central vs regional legislative powers.",
      ),
    )
}
