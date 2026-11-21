package com.example.data.curriculum

/**
 * Exhaustive syllabus concept blocks appended per lesson so each topic naturally spans as many
 * sections as required by the full JAMB UTME, WAEC WASSCE, and NECO SSCE syllabuses instead of
 * forcing every lesson into a fixed section count.
 */
object TopicExhaustiveSections {

  fun extraFor(lessonId: String): List<ConceptBlock> = sections[lessonId].orEmpty()

  private val sections: Map<String, List<ConceptBlock>> =
    mapOf(
      "phys_04_newton_equilibrium" to
        listOf(
          ConceptBlock(
            heading = "Universal Gravitation, Orbital Speed & Escape Velocity",
            explanation =
              "Newton's Law of Universal Gravitation states that any two point masses attract with force proportional to the product of their masses and inversely proportional to the square of their separation. At Earth's surface of radius R, gravitational field strength g = GM/R², allowing satellites to maintain circular orbit when gravitational pull supplies the exact centripetal force mv²/r.",
            keyFormula = "F = \\frac{G m_1 m_2}{r^2}   |   v_{\\text{esc}} = \\sqrt{\\frac{2GM}{R}} = \\sqrt{2gR}",
            examTip =
              "Escape velocity depends strictly on planet mass M and radius R (or g and R), never on the mass of the launched rocket.",
            mechanismWhyHow =
              "Equating kinetic energy (1/2)mv² to gravitational potential well GMm/R shows why doubling orbital radius halves potential energy and reduces escape speed cleanly by factor √2.",
            examIntelligence =
              "JAMB frequently tests percentage change in g when altitude doubles (g drops by 1/4 when r doubles to 2R).",
            recap = "Force follows inverse-square 1/r²; escape velocity scales as √(2gR).",
          ),
          ConceptBlock(
            heading = "Centres of Gravity & Stability States",
            explanation =
              "An object's weight acts effectively through its Centre of Gravity (CG). Stability improves when the base area is wide and the CG sits low: in stable equilibrium, tilting raises the CG so gravity returns the body; in unstable equilibrium, tilting lowers the CG so it topples; in neutral equilibrium, height of CG stays unchanged.",
            keyFormula = "\\text{Static Condition}: \\sum F_x = 0,\\; \\sum F_y = 0,\\; \\sum \\tau = 0",
            examTip =
              "Racing cars use wide wheelbases and low-slung engines specifically to lower the centre of gravity.",
            mechanismWhyHow =
              "When the vertical plumb line from the CG stays inside the base polygon, gravity creates a restoring torque back toward the pivot.",
            examIntelligence =
              "WAEC Paper 2 commonly contrasts stable (cone on flat base), unstable (cone on tip), and neutral (sphere on level table) examples.",
            recap = "Lower CG plus wider base maximizes restoring torque stability.",
          ),
        ),
      "phys_06_shm_circular" to
        listOf(
          ConceptBlock(
            heading = "Damped Oscillation, Resonance & Spiral Springs",
            explanation =
              "Real oscillators lose mechanical energy continuously against air drag or viscous friction (damping), causing amplitude to decay while natural frequency remains nearly steady. Driving an oscillator at its exact natural frequency f₀ causes resonance—maximum energy transfer and sharply peaked amplitude.",
            keyFormula = "T_{\\text{spring}} = 2\\pi\\sqrt{\\frac{m}{k}} = 2\\pi\\sqrt{\\frac{e}{g}}",
            examTip =
              "Unlike a simple pendulum whose period T depends on length and g (independent of bob mass), a loaded vertical spring's period depends directly on mass m and spring stiffness k.",
            mechanismWhyHow =
              "At resonance, the periodic driving force stays perfectly in phase with the oscillator's velocity, performing positive net work every cycle.",
            examIntelligence =
              "NECO links tuning radio RLC circuits and suspension bridge wind hazards directly to resonance.",
            recap = "Spring period scales with √(m/k); resonance peaks when driving f matches natural f₀.",
          ),
        ),
      "phys_07_fluids_elasticity" to
        listOf(
          ConceptBlock(
            heading = "Young's Modulus, Hooke's Law & Elastic Strain Energy",
            explanation =
              "Within the proportional limit, extension e of a wire is directly proportional to stretching force F (F = ke). Normalizing force by cross-sectional area A gives Tensile Stress (σ = F/A), while normalizing extension by original length L₀ gives Tensile Strain (ε = e/L₀). Their constant ratio is Young's Modulus E, measuring material stiffness independent of wire geometry.",
            keyFormula = "E = \\frac{\\text{Stress}}{\\text{Strain}} = \\frac{F L_0}{A e}   |   U = \\frac{1}{2}Fe = \\frac{1}{2}ke^2",
            examTip =
              "Remember Strain has no unit (m/m cancels), so Young's Modulus carries the exact same unit as Stress and Pressure (N/m² or Pa).",
            mechanismWhyHow =
              "Area under the linear Force-Extension graph equals total work done stretching atomic bonds, storing ½Fe as recoverable elastic potential energy.",
            examIntelligence =
              "JAMB often traps students asking what happens when a wire length doubles—Hooke's constant k halves, yet material Young's Modulus E stays constant.",
            recap = "Stress = F/A, Strain = e/L₀, E = FL₀/(Ae), Energy = ½Fe.",
          ),
          ConceptBlock(
            heading = "Surface Tension, Adhesion & Capillarity",
            explanation =
              "Surface tension arises because liquid molecules on a free surface experience inward cohesive pull with no upward neighbours, creating an elastic surface film. Adhesion exceeding cohesion (water in clean glass) forms a concave meniscus and drives capillary rise; cohesion exceeding adhesion (mercury in glass) produces a convex meniscus and capillary depression.",
            keyFormula = "\\gamma = \\frac{F}{L} \\;(\\text{N/m})   |   h = \\frac{2\\gamma\\cos\\theta}{\\rho g r}",
            examTip =
              "Capillary rise height h is inversely proportional to tube radius r—narrower tubes lift water higher.",
            mechanismWhyHow =
              "Adding soap or warming water disrupts surface hydrogen bonding, lowering surface tension γ so soapy water penetrates fabric pores faster.",
            examIntelligence =
              "WAEC compares capillary glass tubes immersed in water vs mercury, testing meniscus curvature and level difference.",
            recap = "Water-glass wets with concave capillary rise; mercury-glass depresses with convex meniscus.",
          ),
        ),
      "phys_08_heat_expansion" to
        listOf(
          ConceptBlock(
            heading = "Saturated Vapour Pressure (SVP), Boiling & Relative Humidity",
            explanation =
              "In a closed space above a liquid, evaporation and condensation equalize at Saturated Vapour Pressure (SVP), which rises rapidly with temperature and depends solely on temperature—not container volume. Boiling begins the moment a liquid's SVP equals external atmospheric pressure, explaining why water boils below 100°C atop mountain peaks and above 100°C inside sealed pressure cookers.",
            keyFormula = "\\text{Relative Humidity} = \\frac{\\text{SVP at Dew Point}}{\\text{SVP at Air Temperature}} \\times 100\\%",
            examTip =
              "Increasing the volume of a closed vessel containing saturated vapour with excess liquid leaves SVP unchanged once equilibrium re-establishes.",
            mechanismWhyHow =
              "Dew point is the temperature at which existing atmospheric moisture becomes saturated and condenses onto cool surfaces as morning dew or cloud droplets.",
            examIntelligence =
              "JAMB tests calculating Relative Humidity percentages directly from wet-and-dry bulb hygrometer or dew-point SVP tables.",
            recap = "Boiling occurs when SVP = P_ext; RH uses SVP(dew point) / SVP(air temp).",
          ),
        ),
      "phys_11_optics_lenses" to
        listOf(
          ConceptBlock(
            heading = "Optical Instruments, Human Eye & Colour Dispersion",
            explanation =
              "A simple magnifying glass places an object inside the focal length f to produce an upright, virtual magnified image. A compound microscope pairs a short-focus objective lens with an eyepiece for high angular magnification, whereas an astronomical telescope in normal adjustment separates objective (f_o) and eyepiece (f_e) by f_o + f_e to achieve angular magnification M = f_o / f_e.",
            keyFormula = "M_{\\text{telescope}} = \\frac{f_o}{f_e} \\quad (L = f_o + f_e)",
            examTip =
              "In glass prisms, violet light bends most (shortest wavelength, highest refractive index) while red deviates least (ROYGBIV).",
            mechanismWhyHow =
              "Different wavelengths travel at slightly different speeds inside crown glass, splitting composite white light into its continuous visible spectrum.",
            examIntelligence =
              "WAEC compares astronomical telescope adjustment (infinite final image, L = f_o + f_e) with myopia/hypermetropia corrective spectacles.",
            recap = "Telescope magnification = f_o/f_e; prism dispersion bends violet most, red least.",
          ),
        ),
      "phys_13_circuits_emf" to
        listOf(
          ConceptBlock(
            heading = "Wheatstone Bridge & Potentiometer Null Methods",
            explanation =
              "A Wheatstone bridge balances four resistors so zero current flows through the central galvanometer when R₁/R₂ = R₃/R₄. Similarly, a potentiometer compares unknown cell EMFs against a uniform resistance wire at balance length L (E₁/E₂ = L₁/L₂), achieving high precision because it draws zero current at the null point.",
            keyFormula = "\\frac{R_1}{R_2} = \\frac{R_3}{R_4}   |   \\frac{E_1}{E_2} = \\frac{L_1}{L_2}   |   r = R\\left(\\frac{L_1 - L_2}{L_2}\\right)",
            examTip =
              "A potentiometer measures true EMF rather than terminal PD precisely because zero current flows through the test cell at balance.",
            mechanismWhyHow =
              "With zero current passing through the cell under test at balance, lost volts (Ir) disappear completely, making balanced wire potential drop identical to E.",
            examIntelligence =
              "WAEC Paper 3 Alternative-to-Practical tests potentiometer balance lengths L₁ (open switch) and L₂ (shunted by resistor R) to find internal resistance r.",
            recap = "Zero galvanometer deflection at balance eliminates internal lost volts Ir.",
          ),
        ),
      "phys_15_quantum_electronics" to
        listOf(
          ConceptBlock(
            heading = "Digital Logic Gates & Semiconductor Junctions",
            explanation =
              "Doping pure silicon (Group IV) with phosphorus creates electron-rich n-type semiconductors, while doping with boron produces hole-rich p-type material. Joining p and n layers forms a diode that conducts under forward bias and blocks under reverse bias (rectification). Digital circuits combine transistors into Logic Gates: AND (all HIGH → 1), OR (any HIGH → 1), NOT (inverter), NAND (NOT-AND), and NOR (NOT-OR).",
            keyFormula = "AND: Y = A \\cdot B   |   OR: Y = A + B   |   NAND: Y = \\overline{A \\cdot B}",
            examTip =
              "A NAND gate followed by another NAND tied as an inverter restores standard AND logic; NAND and NOR are universal logic gates.",
            mechanismWhyHow =
              "Full-wave bridge rectifiers use four diodes to flip both halves of an AC cycle into unidirectional pulsing DC before capacitor smoothing.",
            examIntelligence =
              "JAMB frequently supplies a 2-input binary truth table and asks candidates to identify whether the gate is AND, NAND, OR, NOR, or XOR.",
            recap = "Forward bias conducts; NAND outputs 0 only when both inputs are 1.",
          ),
        ),
      "math_01_bases_modular" to
        listOf(
          ConceptBlock(
            heading = "Percentage Error, Bounds & Commercial Arithmetic",
            explanation =
              "Every rounded measurement carries uncertainty equal to half the smallest scale unit (±0.5 unit). Percentage error divides absolute discrepancy by the true value × 100%. In commercial arithmetic, markups, trade discounts, simple interest I = PRT/100, and compound interest A = P(1 + r/100)ⁿ model retail pricing and banking accurately.",
            keyFormula = "\\%\\text{Error} = \\frac{|\\text{Measured} - \\text{True}|}{\\text{True}} \\times 100\\%   |   A = P\\left(1 + \\frac{r}{100}\\right)^n",
            examTip =
              "Always divide error by the TRUE actual value, never by the mistaken measured reading.",
            mechanismWhyHow =
              "Compounding recalculates interest onto accumulated principal each period, creating exponential growth compared to linear simple interest.",
            examIntelligence =
              "JAMB regularly combines sequential discounts (e.g., 10% then 20%, net discount 28% rather than 30%) with profit-over-cost-price percentages.",
            recap = "Percentage error denominator is always the true value; compound interest multiplies (1 + r)ⁿ.",
          ),
        ),
      "math_02_indices_logs_surds" to
        listOf(
          ConceptBlock(
            heading = "Logarithmic Equations, Change of Base & Binomial Expansion",
            explanation =
              "Converting logarithmic statements back to exponential form solves equations systematically once domain constraints (argument > 0, base > 0 and ≠ 1) are checked. When expanding (1 + x)ⁿ for small n or positive integer powers, Pascal's triangle and combinations nCr generate coefficients directly.",
            keyFormula = "\\log_b x = \\frac{\\log_a x}{\\log_a b}   |   (a+b)^n = \\sum_{r=0}^n \\binom{n}{r} a^{n-r} b^r",
            examTip =
              "Discard any algebraic quadratic root that forces the inside of a logarithm (e.g., x − 2) to become zero or negative.",
            mechanismWhyHow =
              "Change-of-base identity links unknown bases cleanly to base 10 or natural logarithms by taking logs of both sides of b^y = x.",
            examIntelligence =
              "WAEC Section A combines two logs via log M + log N = log(MN), produces a quadratic, and awards a specific mark for rejecting the extraneous root.",
            recap = "Combine single logs on each side, solve algebraically, and reject non-positive log arguments.",
          ),
        ),
      "math_04_linear_variation_ineq" to
        listOf(
          ConceptBlock(
            heading = "Linear Programming & Feasible Region Optimization",
            explanation =
              "When real constraints take the form ax + by ≤ c, each inequality partitions the coordinate plane into a boundary line (solid for ≤ or ≥, dashed for < or >) and a feasible half-plane. The intersection polygon of all active constraints encloses feasible integer or real production choices, and any linear objective P = px + qy attains its maximum or minimum at a corner vertex of that polygon.",
            keyFormula = "\\text{Maximize } P = px + qy \\text{ at vertices } (x_i, y_i) \\text{ of feasible polygon}",
            examTip =
              "Test the origin (0, 0) quickly when the boundary line does not pass through (0, 0) to decide which half-plane satisfies the inequality.",
            mechanismWhyHow =
              "Parallel profit contours sweep across the convex feasible polygon until touching its outermost corner vertex last.",
            examIntelligence =
              "WAEC graphs 3–4 linear constraints and asks candidates to shade the required region and read off maximum integer coordinates (x, y).",
            recap = "Boundary solid/dashed by ≤ vs <; optimal objective always occurs at a polygon vertex.",
          ),
        ),
      "math_10_mensuration_3d" to
        listOf(
          ConceptBlock(
            heading = "Longitude, Latitude & Great Circle Earth Distances",
            explanation =
              "Earth is modelled as a sphere of radius R ≈ 6400 km. Meridians of longitude are Great Circles passing through both poles, whereas parallels of latitude α° N/S are small circles of reduced radius r = R cos α. Shortest surface distances follow Great Circles using arc length s = (θ / 360°) × 2πR along the same meridian or along parallel latitude radius R cos α.",
            keyFormula = "d_{\\text{meridian}} = \\frac{\\Delta\\phi}{360^\\circ}(2\\pi R)   |   d_{\\text{parallel}} = \\frac{\\Delta\\lambda}{360^\\circ}(2\\pi R\\cos\\alpha)",
            examTip =
              "Add angles when two towns lie on OPPOSITE sides of the Equator or Prime Meridian (e.g., 15° N and 20° S gives Δφ = 35°); subtract when on the SAME side.",
            mechanismWhyHow =
              "Multiplying equator circumference 2πR by cos α accounts precisely for how parallels shrink toward zero circumference at the poles.",
            examIntelligence =
              "WAEC Section B routinely asks for distance along a parallel of latitude followed by flying speed = distance / time.",
            recap = "Meridian distance uses R; parallel-of-latitude distance uses small-circle radius R cos α.",
          ),
        ),
      "math_13_calculus" to
        listOf(
          ConceptBlock(
            heading = "Indefinite & Definite Integration, Curve Areas & Kinematics",
            explanation =
              "Integration reverses differentiation: ∫xⁿ dx = xⁿ⁺¹ / (n + 1) + C for n ≠ −1. Evaluating a definite integral ∫[a, b] f(x) dx applies F(b) − F(a) to measure signed net area between the curve and the x-axis. In kinematics, integrating acceleration yields velocity and integrating velocity yields displacement.",
            keyFormula = "\\int x^n\\,dx = \\frac{x^{n+1}}{n+1} + C \\;(n\\neq -1)   |   \\text{Area} = \\int_a^b y\\,dx",
            examTip =
              "Regions lying below the x-axis return negative definite integrals—split the integral at x-intercepts and take absolute values when finding total geometric area.",
            mechanismWhyHow =
              "Summing infinitesimal vertical strips of height y = f(x) and width dx converges exactly to the antiderivative difference F(b) − F(a).",
            examIntelligence =
              "JAMB integrates polynomials term-by-term or applies u-substitution to linear brackets (ax + b)ⁿ, dividing by coefficient a.",
            recap = "Increase exponent by 1, divide by new exponent, add + C for indefinite or F(b) − F(a) for definite.",
          ),
        ),
      "chem_03_periodic_bonding" to
        listOf(
          ConceptBlock(
            heading = "Intermolecular Forces, Hydrogen Bonding & Crystal Lattices",
            explanation =
              "Even neutral covalent molecules attract one another weakly via instantaneous-dipole Van der Waals forces, stronger permanent dipole-dipole interactions, or strong Hydrogen Bonding whenever H bonds directly to tiny electronegative N, O, or F atoms. Hydrogen bonding explains why H₂O is a dense liquid at 25°C while heavier H₂S is a gas, and why ice floats with an open hexagonal lattice.",
            keyFormula = "\\text{Strength Order: } \\text{Ionic/Covalent} \\gg \\text{H-Bond (N,O,F)} > \\text{Dipole-Dipole} > \\text{Van der Waals}",
            examTip =
              "Check for N−H, O−H, or F−H covalent links immediately whenever a question asks why boiling point spikes unusually (HF, H₂O, NH₃, Ethanol).",
            mechanismWhyHow =
              "Intense partial positive charge on H bonded to F/O/N pulls strongly on lone electron pairs of neighbouring molecules, storing extra cohesive energy.",
            examIntelligence =
              "JAMB contrasts giant covalent lattices (diamond high MP, graphite conducts via delocalized 4th electron) with simple molecular crystals (iodine sublimes easily).",
            recap = "Hydrogen bonding requires H bonded directly to F, O, or N.",
          ),
        ),
      "chem_06_acids_bases_titration" to
        listOf(
          ConceptBlock(
            heading = "Salt Hydrolysis, Buffer Action & Water Hardness",
            explanation =
              "Not all aqueous salts sit at pH 7: salts formed from a strong acid and weak base (e.g., NH₄Cl, FeCl₃) hydrolyze to turn blue litmus red (pH < 7), whereas salts from a weak acid and strong base (e.g., Na₂CO₃, CH₃COONa) yield alkaline solutions (pH > 7). A buffer mixture (weak acid + its conjugate salt) resists sharp pH swings when small acid or alkali drops are added.",
            keyFormula = "K_w = [H^+][OH^-] = 1.0\\times 10^{-14} \\text{ at } 25^\\circ\\text{C}   |   \\text{pH} + \\text{pOH} = 14",
            examTip =
              "Temporary hardness from Ca(HCO₃)₂ softens upon simple boiling; permanent hardness from CaSO₄ or MgSO₄ requires washing soda (Na₂CO₃) or ion-exchange resin.",
            mechanismWhyHow =
              "In a CH₃COOH/CH₃COONa buffer, excess added H⁺ reacts with abundant CH₃COO⁻ ions to form weakly ionized CH₃COOH, buffering the pH.",
            examIntelligence =
              "WAEC frequently tests salt pH classification (NH₄Cl acidic vs Na₂CO₃ alkaline vs NaCl neutral) alongside titration indicator selection.",
            recap = "Strong partner controls salt pH; buffer pairs neutralize small added H⁺ or OH⁻.",
          ),
        ),
      "chem_13_organic_hydrocarbons" to
        listOf(
          ConceptBlock(
            heading = "Aromatic Benzene, Petroleum Cracking & Octane Number",
            explanation =
              "Benzene (C₆H₆) contains a planar ring of six carbon atoms sharing a delocalized cloud of pi-electrons rather than alternating fixed double bonds. Consequently, benzene prefers electrophilic substitution (such as nitration with conc. HNO₃/H₂SO₄) rather than decolourizing bromine water rapidly. In petroleum refineries, fractional distillation separates crude oil by boiling range, while catalytic cracking breaks long heavy fractions into high-octane petrol and ethene.",
            keyFormula = "\\text{C}_{10}\\text{H}_{22} \\xrightarrow{\\text{Catalyst, Heat}} \\text{C}_8\\text{H}_{18} + \\text{C}_2\\text{H}_4",
            examTip =
              "Branched-chain alkanes and aromatics raise petrol octane number (smooth ignition), whereas straight-chain heptane causes engine knocking (octane rating 0).",
            mechanismWhyHow =
              "Resonance stabilization lowers benzene's internal energy compared to hypothetical cyclohexatriene, making ring preservation during substitution thermodynamically favoured.",
            examIntelligence =
              "JAMB contrasts thermal/catalytic cracking (produces petrol + alkenes) against catalytic reforming (converts straight alkanes to branched/aromatic high-octane fuels).",
            recap = "Benzene undergoes substitution; cracking makes petrol + alkenes; reforming boosts octane rating.",
          ),
        ),
      "chem_14_alkanols_esters_polymers" to
        listOf(
          ConceptBlock(
            heading = "Carbohydrates, Proteins & Giant Biomolecules",
            explanation =
              "Carbohydrates obey the general formula Cₓ(H₂O)_y: monosaccharides (glucose C₆H₁₂O₆) reduce Fehling's and Benedict's solutions to brick-red Cu₂O, while non-reducing disaccharides (sucrose) require dilute acid hydrolysis first. Amino acids act as amphoteric zwitterions and condense via peptide links (−CONH−) into polypeptides and proteins, giving a violet Biuret ring and yellow-orange Xanthoproteic response.",
            keyFormula = "\\text{Sucrose (C}_{12}\\text{H}_{22}\\text{O}_{11}\\text{)} + \\text{H}_2\\text{O} \\xrightarrow{\\text{H}^+} \\text{Glucose} + \\text{Fructose}",
            examTip =
              "Heat breaks hydrogen bonds holding a protein's 3D folding (denaturation) without snapping strong covalent peptide backbones.",
            mechanismWhyHow =
              "Every glycosidic bond in starch/cellulose and peptide bond in nylon/silk forms via condensation elimination of H₂O.",
            examIntelligence =
              "WAEC tests distinguishing reducing glucose from non-reducing sucrose using Fehling's reagent before and after dilute HCl boiling.",
            recap = "Glucose reduces Fehling's directly; sucrose requires acid hydrolysis first; proteins contain −CONH−.",
          ),
        ),
      "bio_04_nutrition_digestion" to
        listOf(
          ConceptBlock(
            heading = "Plant Mineral Nutrition & Mammalian Dentition",
            explanation =
              "Plants synthesize organic sugars via photosynthesis yet depend on mineral ions absorbed actively from soil water: Nitrogen for amino acids/proteins, Magnesium as the central atom in chlorophyll (deficiency causes yellow chlorosis), Phosphorus for ATP/nucleic acids, and Potassium for stomatal opening. In mammals, heterodont dentition specializes teeth into incisors (chisel cutting), canines (tearing), and premolars/molars (grinding).",
            keyFormula = "\\text{Human Dental Formula: } I\\frac{2}{2},\\; C\\frac{1}{1},\\; PM\\frac{2}{2},\\; M\\frac{3}{3} = 32 \\text{ teeth}",
            examTip =
              "Herbivores lack upper incisors (replaced by a horny pad) and display a wide toothless gap (diastema) to manipulate fibrous grass.",
            mechanismWhyHow =
              "Active transport pumps nitrate and magnesium ions into root hair cells against concentration gradients using mitochondrial ATP.",
            examIntelligence =
              "JAMB contrasts Magnesium deficiency (chlorosis of older leaves) against Nitrogen deficiency (stunted growth + yellowing) alongside carnivore carnassial teeth.",
            recap = "Mg builds chlorophyll; N builds proteins; carnivore carnassials shear flesh cleanly.",
          ),
        ),
      "bio_10_mendelian_genetics" to
        listOf(
          ConceptBlock(
            heading = "Dihybrid Inheritance, ABO Multiple Alleles & Sex Linkage",
            explanation =
              "Mendel's Second Law (Independent Assortment) predicts a 9 : 3 : 3 : 1 F₂ phenotypic ratio when two independent traits cross (e.g., TtRr × TtRr). Non-Mendelian extensions vital for exams include ABO blood group codominance (Iᴬ and Iᴮ codominant over recessive i) and X-linked recessive disorders such as haemophilia and red-green colour blindness, which males inherit directly on their single X chromosome from carrier mothers.",
            keyFormula = "\\text{Dihybrid } F_2 \\text{ Ratio} = 9:3:3:1   |   \\text{Blood Type AB} = I^A I^B \\text{ (Codominant)}",
            examTip =
              "An X-linked affected father never passes a condition to his sons (who receive his Y chromosome) but makes all his daughters carriers.",
            mechanismWhyHow =
              "Independent alignment of homologous pairs at Metaphase I shuffles maternal and paternal alleles independently across gametes unless genes sit close together (linkage).",
            examIntelligence =
              "WAEC frequently tests ABO paternity puzzles: an O (ii) child cannot come from an AB (IᴬIᴮ) parent.",
            recap = "9:3:3:1 marks independent dihybrid F₂; AB blood is codominant; sons inherit X-linked traits from mothers.",
          ),
        ),
      "bio_12_ecology_sampling" to
        listOf(
          ConceptBlock(
            heading = "Population Sampling & Ecological Succession",
            explanation =
              "Ecologists estimate plant density via random quadrat throws and mobile animal populations via Capture-Mark-Release-Recapture (Lincoln Index: N = M₁ × M₂ / R). Over decades, bare rock or cleared farmland undergoes primary or secondary succession from hardy pioneer mosses/lichens through grasses and shrubs to a stable self-perpetuating climax forest biome.",
            keyFormula = "\\text{Estimated Population } N = \\frac{\\text{First Capture } M_1 \\times \\text{Second Capture } M_2}{\\text{Marked Recaptured } R}",
            examTip =
              "Use a quadrat strictly for stationary organisms (plants/barnacles), never for fast-moving insects or mammals.",
            mechanismWhyHow =
              "Each seral stage enriches humus depth and microclimate shade, altering soil conditions until climax species outcompete earlier pioneers.",
            examIntelligence =
              "JAMB tests calculating percentage frequency or population density from quadrat grids alongside pioneer species identification.",
            recap = "Quadrat = plants; Capture-Recapture = mobile animals; Succession leads to Climax community.",
          ),
        ),
      "eng_03_sentences_modifiers" to
        listOf(
          ConceptBlock(
            heading = "Punctuation Mechanics: Semicolons, Colons & Restrictive Clauses",
            explanation =
              "Punctuation clarifies syntactic boundaries. A semicolon joins two closely related independent clauses without a coordinating conjunction (avoiding a comma splice); a colon introduces a formal list, explanation, or quotation after an independent clause; and enclosing commas mark non-restrictive relative clauses while no commas surround essential restrictive identification.",
            keyFormula = "\\text{Clause}_1 ;\\; \\text{Clause}_2 \\quad | \\quad \\text{Independent Clause} : \\text{List / Explanation}",
            examTip =
              "Test comma usage with 'however' and 'therefore': joining two complete sentences with ', however,' creates a comma splice—place a semicolon before 'however'.",
            mechanismWhyHow =
              "Removing a non-restrictive phrase bounded by paired commas must still leave the core subject-verb reference unambiguous.",
            examIntelligence =
              "WAEC Objective and Essay scripts deduct mechanical accuracy marks quickly for apostrophe omissions ('its' possessive vs 'it's' contraction) and comma splices.",
            recap = "Use semicolons between independent clauses; keep 'its' free of apostrophes for possession.",
          ),
        ),
      "eng_15_comprehension_essay" to
        listOf(
          ConceptBlock(
            heading = "Literary Devices, Writer's Tone & Essay Blueprints",
            explanation =
              "Comprehension passages routinely examine figures of speech (metaphor, personification, irony, hyperbole, oxymoron, euphemism) alongside the author's tone (satirical, nostalgic, cautionary, laudatory). In Paper 2 Continuous Writing, argumentative debates demand taking a clear side early and refuting counter-arguments, whereas speech/article formats require audience salutations and formal headings.",
            keyFormula = "\\text{Argumentative} = \\text{Vocatives} + \\text{Clear Stand} + \\text{Counter-Refutation}",
            examTip =
              "Always read grammatical-function questions in context—decide what role the underlined clause or word plays INSIDE that exact passage sentence.",
            mechanismWhyHow =
              "Tone emerges from word choice (diction) and modal verbs; distinguishing literal statements from ironic overstatement prevents misreading intent.",
            examIntelligence =
              "JAMB Comprehension asks at least one question per passage on mood/attitude and one on word replacement by contextual synonym.",
            recap = "Match tone to diction clues; match letter/speech format strictly to the audience named.",
          ),
        ),
      "eco_02_utility_demand" to
        listOf(
          ConceptBlock(
            heading = "Ordinal Utility: Indifference Curves & Budget Constraint",
            explanation =
              "While cardinal utility attempts to count 'utils', ordinal utility ranks consumer bundles along convex downward-sloping Indifference Curves—each representing equal total satisfaction. Higher curves signify greater welfare, curves never intersect, and rational equilibrium occurs where the downward Budget Line (M = PₓX + PᵧY) sits cleanly tangent to the highest reachable indifference curve.",
            keyFormula = "\\text{Consumer Equilibrium: } MRS_{xy} = \\frac{MU_x}{MU_y} = \\frac{P_x}{P_y}",
            examTip =
              "Diminishing Marginal Rate of Substitution (MRS) explains why indifference curves bow inward toward the origin (convex).",
            mechanismWhyHow =
              "At tangency, the subjective trade-off slope the consumer is willing to make equals the objective market price ratio.",
            examIntelligence =
              "WAEC tests listing the four properties of indifference curves (downward sloping, convex, non-intersecting, higher = preferred).",
            recap = "Indifference curves never cross; consumer equilibrium sits at budget-line tangency.",
          ),
        ),
      "eco_06_market_structures" to
        listOf(
          ConceptBlock(
            heading = "Factor Markets, Economic Rent & Market Failure",
            explanation =
              "Factors of production earn factor prices (Wages, Rent, Interest, Profit). Transfer Earnings is the minimum payment needed to keep a factor in its present employment, while any surplus above transfer earnings is Economic Rent (highest for completely inelastic land or unique specialist talent). When unregulated markets overproduce pollution (negative externalities) or underproduce streetlights (non-rival, non-excludable public goods), government regulation steps in.",
            keyFormula = "\\text{Total Earnings} = \\text{Transfer Earnings} + \\text{Economic Rent}",
            examTip =
              "When factor supply is perfectly inelastic (fixed land), 100% of factor earnings equals Economic Rent (Transfer Earnings = 0).",
            mechanismWhyHow =
              "Because public goods are non-excludable, private firms face free-rider losses and cannot price them profitably without public taxation.",
            examIntelligence =
              "JAMB distinguishes Quasi-Rent (short-run surplus on fixed capital machines) from pure David Ricardo Land Rent.",
            recap = "Surplus above transfer earnings = Economic Rent; non-excludability causes public-good market failure.",
          ),
        ),
      "gov_04_federalism_local_govt" to
        listOf(
          ConceptBlock(
            heading = "Nigerian Revenue Allocation Commissions & Derivation",
            explanation =
              "Fiscal federalism has been a central challenge in Nigeria since amalgamation. Key colonial and post-independence commissions shaped sharing principles: Phillipson (1946) stressed derivation; Hicks-Phillipson (1951) added need and national interest; Chick (1953) restored strong derivation; Raisman (1958) created the Distributable Pool Account; Dina (1969) emphasized balanced national development; and Okigbo (1980) structured Federal-State-Local shares under today's RMAFC.",
            keyFormula = "\\text{Nigeria Formula Tiers}: \\text{Federal} \\;\\cdot\\; \\text{States} \\;\\cdot\\; \\text{Local Govts} + 13\\% \\text{ Derivation}",
            examTip =
              "Link Phillipson (1946) → Chick (1953) with strong Derivation, Raisman (1958) with the Distributable Pool Account, and the 1999 Constitution with minimum 13% natural-resource derivation.",
            mechanismWhyHow =
              "Balancing Derivation (rewarding resource-producing states) against Equity/Population/Need maintains fiscal solidarity across diverse regions.",
            examIntelligence =
              "WAEC frequently tests matching a named Fiscal Commission (Phillipson, Chick, Raisman, Aboyade, Okigbo) to its defining recommendation.",
            recap = "Phillipson/Chick = Derivation; Raisman = Distributable Pool; 1999 = 13% Derivation floor.",
          ),
        ),
      "gov_07_nationalism_1963_const" to
        listOf(
          ConceptBlock(
            heading = "Pre-Independence Constitutional Milestones (1922–1957)",
            explanation =
              "Nigeria's road to independence unfolded through four landmark colonial constitutions: Clifford (1922) introduced the elective principle (3 Lagos + 1 Calabar seats); Richards (1946) integrated North and South inside one central Legislative Council with three Regional Houses; Macpherson (1951) widened grassroots consultation and created a central Council of Ministers; and Lyttelton (1954) established true Federalism with Exclusive, Concurrent, and Residual lists plus Regional Premiers.",
            keyFormula = "1922 \\text{ (Elective)} \\rightarrow 1946 \\text{ (Regionalism)} \\rightarrow 1951 \\text{ (Quasi-Federal)} \\rightarrow 1954 \\text{ (True Federalism)}",
            examTip =
              "Eastern and Western Regions achieved internal self-government in 1957, while the Northern Region followed in 1959 ahead of October 1, 1960 independence.",
            mechanismWhyHow =
              "Each constitutional breakdown (such as the 1953 Kano crisis and Eastern Regional Crisis) forced London conferences in 1953–1958 toward greater regional autonomy.",
            examIntelligence =
              "JAMB regularly tests which constitution introduced Regional Houses of Assembly (Richards 1946) vs Regional Premiers and True Federalism (Lyttelton 1954).",
            recap = "1922 Elective; 1946 Regions + North-South integration; 1951 Ministers; 1954 True Federalism.",
          ),
        ),
    )
}
