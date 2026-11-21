package com.example.data.curriculum

/**
 * Low-stakes "Check Your Understanding" intermediate drills attached directly inside dense
 * formula/concept blocks for Physics and Mathematics before the gated Mastery Quiz.
 */
object ConceptMicroDrillsPart1 {

  fun drillsFor(lessonId: String, sectionIndex: Int): List<MicroDrill> =
    byLesson[lessonId]?.get(sectionIndex).orEmpty()

  fun allDrillsForLesson(lessonId: String): Map<Int, List<MicroDrill>> = byLesson[lessonId].orEmpty()

  private val byLesson: Map<String, Map<Int, List<MicroDrill>>> =
    mapOf(
      "phys_01_units" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Which pair shares the identical ML²T⁻² dimensional formula?",
            options = listOf("Power and Force", "Work Done and Torque", "Impulse and Pressure", "Momentum and Energy"),
            correctIndex = 1,
            hint = "Check Force × Distance units for both Work (J) and Torque (N·m).",
            explanation = "Both Work = F·d and Torque = F·r evaluate to [M L T⁻²][L] = [M L² T⁻²].",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "A vernier caliper exhibits a −0.03 cm zero error and reads 2.45 cm on a cylinder. What is the true diameter?",
            options = listOf("2.42 cm", "2.45 cm", "2.48 cm", "2.75 cm"),
            correctIndex = 2,
            hint = "Subtract the signed error: True = Observed − (−0.03).",
            explanation = "2.45 − (−0.03) = 2.45 + 0.03 = 2.48 cm.",
          ),
        ),
      ),
      "phys_02_vectors" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A boat heads at 12 N acting at 60° above East. Find its horizontal Eastward component (cos 60° = 0.5).",
            options = listOf("6.0 N", "10.4 N", "12.0 N", "24.0 N"),
            correctIndex = 0,
            hint = "Horizontal adjacent leg uses cosine: F_x = F cos θ.",
            explanation = "F_x = 12 × cos 60° = 12 × 0.5 = 6.0 N East.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "Perpendicular pulls of 6.0 N North and 8.0 N East combine to what resultant magnitude?",
            options = listOf("2.0 N", "10.0 N", "14.0 N", "48.0 N"),
            correctIndex = 1,
            hint = "Apply Pythagoras: √(6² + 8²).",
            explanation = "√(36 + 64) = √100 = 10.0 N (6-8-10 scaled 3-4-5 triangle).",
          ),
        ),
      ),
      "phys_03_kinematics" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A taxi accelerates uniformly from 10 m/s to 26 m/s over 4.0 s. Calculate distance travelled.",
            options = listOf("36 m", "64 m", "72 m", "104 m"),
            correctIndex = 2,
            hint = "Use s = ½(u + v)t.",
            explanation = "Average speed = (10 + 26)/2 = 18 m/s; distance = 18 × 4.0 = 72 m.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "A stone launched at 40 m/s at 30° reaches maximum height (g = 10 m/s²). What is its horizontal speed at the peak?",
            options = listOf("0 m/s", "20 m/s", "34.6 m/s", "40 m/s"),
            correctIndex = 2,
            hint = "Only vertical velocity v_y drops to zero; v_x stays u cos θ.",
            explanation = "v_x = 40 cos 30° = 40 × 0.866 = 34.6 m/s throughout flight.",
          ),
        ),
      ),
      "phys_04_newton_equilibrium" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A 1,200 kg car stalls from 15 m/s to rest in 3.0 s. Calculate the average braking force magnitude.",
            options = listOf("4,000 N", "6,000 N", "18,000 N", "54,000 N"),
            correctIndex = 1,
            hint = "Impulse magnitude FΔt = mΔv.",
            explanation = "F = (1200 × 15) / 3.0 = 6,000 N.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "A uniform 100 cm metre rule pivots at 40 cm. An unknown mass m hangs at 10 cm balancing 30 N at 60 cm. Find m (ignore beam weight only if stated negligible; assume negligible beam weight here).",
            options = listOf("10 N", "15 N", "20 N", "30 N"),
            correctIndex = 2,
            hint = "Match lever arms from pivot at 40 cm: 30 cm vs 20 cm.",
            explanation = "Clockwise = Anticlockwise: W × (40 − 10) = 30 × (60 − 40) → 30W = 600 → W = 20 N.",
          ),
        ),
      ),
      "phys_05_work_machines" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Find the kinetic energy of a 4.0 kg trolley moving at 5.0 m/s.",
            options = listOf("10 J", "20 J", "50 J", "100 J"),
            correctIndex = 2,
            hint = "Recall KE = ½mv².",
            explanation = "KE = 0.5 × 4.0 × 5.0² = 2.0 × 25 = 50 J.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "A pulley block with 5 supporting rope strands lifts a 400 N crate using 100 N effort. Calculate efficiency.",
            options = listOf("25%", "50%", "80%", "125%"),
            correctIndex = 2,
            hint = "MA = 400/100 = 4, VR = 5; η = (MA/VR)×100%.",
            explanation = "η = (4 / 5) × 100% = 80%.",
          ),
        ),
      ),
      "phys_06_shm_circular" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "If simple pendulum length increases by a factor of 4 while g stays constant, how does period T change?",
            options = listOf("Halves", "Stays unchanged", "Doubles", "Quadruples"),
            correctIndex = 2,
            hint = "Period T ∝ √l.",
            explanation = "√4 = 2, so doubling pendulum length-square-root doubles period T.",
          ),
        ),
      ),
      "phys_07_fluids_elasticity" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A spring extends by 0.04 m under an 8 N weight. How much elastic potential energy is stored?",
            options = listOf("0.16 J", "0.32 J", "200 J", "0.08 J"),
            correctIndex = 0,
            hint = "Elastic U = ½Fe.",
            explanation = "U = 0.5 × 8 × 0.04 = 0.16 J.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "What hydrostatic gauge pressure exists 5.0 m below freshwater of density 1000 kg/m³ (g = 10 m/s²)?",
            options = listOf("500 Pa", "5,000 Pa", "50,000 Pa", "500,000 Pa"),
            correctIndex = 2,
            hint = "Compute P = ρgh.",
            explanation = "P = 1000 × 10 × 5.0 = 50,000 Pa (50 kPa).",
          ),
        ),
      ),
      "phys_08_heat_expansion" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "An iron rod of α = 1.2 × 10⁻⁵ K⁻¹ has length 2.0 m and heats by 50 K. Calculate expansion ΔL.",
            options = listOf("1.2 × 10⁻³ m", "2.4 × 10⁻³ m", "6.0 × 10⁻⁴ m", "1.2 × 10⁻⁴ m"),
            correctIndex = 0,
            hint = "Apply ΔL = α L₀ Δθ.",
            explanation = "ΔL = 1.2 × 10⁻⁵ × 2.0 × 50 = 1.2 × 10⁻³ m (1.2 mm).",
          ),
        ),
      ),
      "phys_09_calorimetry_heat_transfer" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "How much heat energy warms 2.0 kg of water (c = 4200 J/kg·K) from 25°C to 45°C?",
            options = listOf("84 kJ", "168 kJ", "210 kJ", "378 kJ"),
            correctIndex = 1,
            hint = "Compute Q = mcΔθ with Δθ = 20 K.",
            explanation = "Q = 2.0 × 4200 × 20 = 168,000 J = 168 kJ.",
          ),
        ),
      ),
      "phys_10_waves_sound" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A sound wave travels at 340 m/s with frequency 680 Hz. Calculate its wavelength.",
            options = listOf("0.50 m", "2.0 m", "340 m", "1020 m"),
            correctIndex = 0,
            hint = "Apply λ = v / f.",
            explanation = "λ = 340 / 680 = 0.50 m.",
          ),
        ),
      ),
      "phys_11_optics_lenses" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "An object stands 30 cm from a converging lens of focal length f = +10 cm. Locate real image distance v.",
            options = listOf("7.5 cm", "15.0 cm", "20.0 cm", "40.0 cm"),
            correctIndex = 1,
            hint = "Use 1/v = 1/f − 1/u.",
            explanation = "1/v = 1/10 − 1/30 = 2/30 = 1/15, so v = +15.0 cm.",
          ),
        ),
      ),
      "phys_12_electrostatics_capacitors" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Two capacitors of 6 μF and 3 μF are wired in SERIES. Compute their equivalent capacitance.",
            options = listOf("2 μF", "4.5 μF", "9 μF", "18 μF"),
            correctIndex = 0,
            hint = "Series capacitors use product over sum: (C₁C₂)/(C₁+C₂).",
            explanation = "C_eq = (6 × 3) / (6 + 3) = 18 / 9 = 2 μF.",
          ),
        ),
      ),
      "phys_13_circuits_emf" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A cell of EMF 3.0 V and internal resistance 0.5 Ω drives a 5.5 Ω resistor. Find circuit current I.",
            options = listOf("0.50 A", "0.55 A", "1.00 A", "6.00 A"),
            correctIndex = 0,
            hint = "Total resistance is R + r.",
            explanation = "I = E / (R + r) = 3.0 / (5.5 + 0.5) = 3.0 / 6.0 = 0.50 A.",
          ),
        ),
      ),
      "phys_14_em_ac" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "An ideal transformer steps 240 V down across 1200 primary turns to 200 secondary turns. Find secondary voltage.",
            options = listOf("20 V", "40 V", "120 V", "1440 V"),
            correctIndex = 1,
            hint = "Apply V_s / V_p = N_s / N_p.",
            explanation = "V_s = 240 × (200 / 1200) = 240 / 6 = 40 V.",
          ),
        ),
      ),
      "phys_15_quantum_electronics" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Photons of energy 4.2 eV strike a metal with work function 2.5 eV. Calculate maximum photoelectron kinetic energy.",
            options = listOf("1.7 eV", "2.5 eV", "6.7 eV", "10.5 eV"),
            correctIndex = 0,
            hint = "K_max = hf − Φ.",
            explanation = "K_max = 4.2 − 2.5 = 1.7 eV.",
          ),
        ),
      ),
      "phys_16_nuclear" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A 64 g radioactive isotope decays with a half-life of 6 hours. How much mass remains after 18 hours?",
            options = listOf("4 g", "8 g", "16 g", "32 g"),
            correctIndex = 1,
            hint = "Find n = 18 / 6 = 3 half-lives; N = N₀ × (1/2)³.",
            explanation = "After 3 half-lives: 64 × (1/8) = 8 g remain undecayed.",
          ),
        ),
      ),

      // --- MATHEMATICS (15) ---
      "math_01_bases_modular" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Evaluate 213₄ cleanly in base 10.",
            options = listOf("29", "35", "39", "43"),
            correctIndex = 2,
            hint = "Expand 2(4²) + 1(4¹) + 3(4⁰).",
            explanation = "2(16) + 4 + 3 = 32 + 7 = 39.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "Simplify 29 mod 6.",
            options = listOf("1", "3", "4", "5"),
            correctIndex = 3,
            hint = "Divide 29 by 6 and keep the positive remainder.",
            explanation = "29 = 4 × 6 + 5, so 29 ≡ 5 (mod 6).",
          ),
        ),
      ),
      "math_02_indices_logs_surds" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Evaluate (27/8)^(−2/3).",
            options = listOf("4/9", "9/4", "−9/4", "8/27"),
            correctIndex = 0,
            hint = "Flip fraction for negative exponent → (8/27)^(2/3), then cube root first.",
            explanation = "Cube root of 8/27 is 2/3; squaring gives (2/3)² = 4/9.",
          ),
        ),
        1 to listOf(
          MicroDrill(
            prompt = "Rationalize the denominator of 6 / √3.",
            options = listOf("2√3", "3√2", "6√3", "√3"),
            correctIndex = 0,
            hint = "Multiply top and bottom by √3.",
            explanation = "(6√3) / (√3 × √3) = 6√3 / 3 = 2√3.",
          ),
        ),
      ),
      "math_03_sets_venn_functions" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Out of 50 students, 30 study Biology, 28 study Chemistry, and 5 study neither. How many study BOTH?",
            options = listOf("8", "13", "18", "22"),
            correctIndex = 1,
            hint = "n(B ∪ C) = 50 − 5 = 45; subtract from 30 + 28.",
            explanation = "n(B ∩ C) = (30 + 28) − 45 = 58 − 45 = 13.",
          ),
        ),
      ),
      "math_04_linear_variation_ineq" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "If y varies inversely as x² and y = 4 when x = 3, find y when x = 6.",
            options = listOf("1", "2", "8", "16"),
            correctIndex = 0,
            hint = "First compute k = yx² = 4 × 9 = 36.",
            explanation = "y = 36 / 6² = 36 / 36 = 1.",
          ),
        ),
      ),
      "math_05_quadratics_polynomials" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "For 2x² − 6x + 5 = 0 with roots α, β, compute α + β and αβ.",
            options = listOf("α+β = 3, αβ = 5/2", "α+β = −6, αβ = 5", "α+β = −3, αβ = 5/2", "α+β = 6, αβ = 5"),
            correctIndex = 0,
            hint = "Normalize by a = 2: α + β = −b/a and αβ = c/a.",
            explanation = "α + β = −(−6)/2 = 3; αβ = 5/2.",
          ),
        ),
      ),
      "math_06_sequences_binary" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Find the 10th term of the AP: 5, 9, 13, ...",
            options = listOf("37", "41", "45", "49"),
            correctIndex = 1,
            hint = "Apply T_n = a + (n − 1)d with a = 5, d = 4.",
            explanation = "T₁₀ = 5 + 9(4) = 5 + 36 = 41.",
          ),
        ),
      ),
      "math_07_matrices_determinants" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Find x if the 2×2 matrix [[x, 4], [3, 6]] is singular (determinant = 0).",
            options = listOf("1", "2", "3", "4"),
            correctIndex = 1,
            hint = "Set ad − bc = 6x − 12 = 0.",
            explanation = "6x = 12 gives x = 2.",
          ),
        ),
      ),
      "math_08_polygons_parallels" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Each interior angle of a regular polygon is 140°. How many sides does it have?",
            options = listOf("6", "8", "9", "10"),
            correctIndex = 2,
            hint = "Find exterior angle = 180° − 140° = 40°, then compute 360° / ext.",
            explanation = "n = 360° / 40° = 9 sides (nonagon).",
          ),
        ),
      ),
      "math_09_circle_theorems_loci" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "In cyclic quadrilateral PQRS, ∠P = 74°. Find opposite angle ∠R.",
            options = listOf("74°", "106°", "148°", "16°"),
            correctIndex = 1,
            hint = "Opposite angles of a cyclic quadrilateral add to 180°.",
            explanation = "∠R = 180° − 74° = 106°.",
          ),
        ),
      ),
      "math_10_mensuration_3d" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A cone has base radius 3 cm and perpendicular height 4 cm. Calculate its slant height l and curved surface area in π.",
            options = listOf("l = 5 cm, CSA = 15π cm²", "l = 5 cm, CSA = 12π cm²", "l = 7 cm, CSA = 21π cm²", "l = 5 cm, CSA = 24π cm²"),
            correctIndex = 0,
            hint = "Use l = √(r² + h²) and CSA = πrl.",
            explanation = "l = √(3² + 4²) = 5 cm; CSA = π(3)(5) = 15π cm².",
          ),
        ),
      ),
      "math_11_trig_bearings" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "A bearing of B from A is 065°. What is the back-bearing of A from B?",
            options = listOf("115°", "205°", "245°", "295°"),
            correctIndex = 2,
            hint = "Since 065° < 180°, add 180°.",
            explanation = "Back-bearing = 065° + 180° = 245°.",
          ),
        ),
      ),
      "math_12_coordinate_geometry" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Line L has gradient 2/3. What is the gradient of any line perpendicular to L?",
            options = listOf("2/3", "−2/3", "3/2", "−3/2"),
            correctIndex = 3,
            hint = "Perpendicular condition m₁ m₂ = −1.",
            explanation = "Negative reciprocal of 2/3 is −3/2.",
          ),
        ),
      ),
      "math_13_calculus" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "Differentiate y = 2x³ − 5x + 7 with respect to x.",
            options = listOf("6x² − 5", "2x² − 5", "6x³ − 5", "6x² + 7"),
            correctIndex = 0,
            hint = "Apply d/dx(xⁿ) = n xⁿ⁻¹ and derivative of constant 7 is 0.",
            explanation = "dy/dx = 3(2x²) − 5 + 0 = 6x² − 5.",
          ),
        ),
        2 to listOf(
          MicroDrill(
            prompt = "Evaluate definite integral ∫[1 to 3] 2x dx.",
            options = listOf("4", "6", "8", "10"),
            correctIndex = 2,
            hint = "Antiderivative of 2x is x²; compute 3² − 1².",
            explanation = "[x²]₁³ = 9 − 1 = 8.",
          ),
        ),
      ),
      "math_14_perm_comb_prob" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "From 6 candidates, in how many ways can a committee of 2 be selected (order unimportant)?",
            options = listOf("12", "15", "30", "36"),
            correctIndex = 1,
            hint = "Unordered committee uses ⁶C₂ = 6×5 / 2.",
            explanation = "⁶C₂ = 30 / 2 = 15 ways.",
          ),
        ),
      ),
      "math_15_statistics" to mapOf(
        0 to listOf(
          MicroDrill(
            prompt = "If every score in a class test (mean 52, SD 6) receives a +8 bonus mark, what are the new Mean and SD?",
            options = listOf("Mean 60, SD 14", "Mean 60, SD 6", "Mean 52, SD 6", "Mean 60, SD 48"),
            correctIndex = 1,
            hint = "Adding a constant shifts Mean but leaves spread (SD) unchanged.",
            explanation = "Mean shifts to 52 + 8 = 60, while SD stays 6.",
          ),
        ),
      ),
    )
}
