package com.example.ui.labs

import com.example.data.curriculum.PracticalLabType
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

sealed class GamifiedRound {
  abstract val id: String
  abstract val title: String
  abstract val scenario: String
  abstract val objective: String
  abstract val hint: String
  abstract val explanation: String

  data class SliderChallenge(
    override val id: String,
    override val title: String,
    override val scenario: String,
    override val objective: String,
    val param1Label: String,
    val param1Unit: String,
    val param1Range: ClosedFloatingPointRange<Float>,
    val param1Initial: Float,
    val param2Label: String? = null,
    val param2Unit: String = "",
    val param2Range: ClosedFloatingPointRange<Float> = 0f..1f,
    val param2Initial: Float = 0f,
    val outputLabel: String,
    val outputUnit: String,
    val targetValue: Float,
    val tolerance: Float,
    val computeOutput: (Float, Float) -> Float,
    val gaugeRange: ClosedFloatingPointRange<Float>,
    override val hint: String,
    override val explanation: String,
  ) : GamifiedRound()

  data class ConsoleChallenge(
    override val id: String,
    override val title: String,
    override val scenario: String,
    override val objective: String,
    val controlALabel: String,
    val controlAOptions: List<String>,
    val controlBLabel: String,
    val controlBOptions: List<String>,
    val correctAIndex: Int,
    val correctBIndex: Int,
    val observePreview: (Int, Int) -> String,
    override val hint: String,
    override val explanation: String,
  ) : GamifiedRound()
}

object GamifiedLabChallenges {

  fun roundsFor(labType: PracticalLabType): List<GamifiedRound> =
    when (labType) {
      PracticalLabType.MEASUREMENT_VECTOR_LAB ->
        listOf(
          slider(
            id = "mv_1",
            title = "Vernier Zero-Error Calibration",
            scenario = "A Lagos foundry caliper shows a +0.04 cm positive zero error before measuring an alloy rod whose true diameter is 2.30 cm.",
            objective = "Dial the raw observed vernier scale reading (cm) that yields a calibrated true diameter of 2.30 cm.",
            p1Label = "Observed Scale Reading", p1Unit = "cm", p1Range = 2.00f..2.60f, p1Init = 2.22f,
            outLabel = "True Diameter", outUnit = "cm", target = 2.30f, tol = 0.02f, gauge = 2.00f..2.60f,
            compute = { obs, _ -> obs - 0.04f },
            hint = "True = Observed − (+Zero Error) → Observed = 2.30 + 0.04 cm.",
            explanation = "Observed 2.34 cm minus the +0.04 cm zero error gives the true diameter of 2.30 cm.",
          ),
          slider(
            id = "mv_2",
            title = "Resultant Force Angle Lock",
            scenario = "Two tugboats pull a barge at Abuja Dam with perpendicular forces Fₓ = 60 N and Fᵧ = 80 N? Tune the second force Fᵧ against Fₓ = 60 N to hit a 100.0 N resultant.",
            objective = "Set orthogonal force Fᵧ (N) when Fₓ = 60 N to achieve a 100.0 N resultant.",
            p1Label = "Orthogonal Force Fᵧ", p1Unit = "N", p1Range = 20f..140f, p1Init = 50f,
            outLabel = "Resultant R", outUnit = "N", target = 100.0f, tol = 2.5f, gauge = 60f..160f,
            compute = { fy, _ -> sqrt(60f * 60f + fy * fy) },
            hint = "Pythagoras 3-4-5 multiple: √(60² + Fᵧ²) = 100 N.",
            explanation = "With Fₓ = 60 N and Fᵧ = 80 N at 90°, R = √(3600 + 6400) = 100 N.",
          ),
          slider(
            id = "mv_3",
            title = "Upstream Ferry Drift Canceller",
            scenario = "A river flows East at 3.0 m/s while a boat cruises at speed v against a 36.9° upstream heading (sin 36.9° = 0.60).",
            objective = "Tune boat engine speed v (m/s) so the westward component v·sin(36.9°) cancels the 3.0 m/s current.",
            p1Label = "Engine Speed v", p1Unit = "m/s", p1Range = 2.0f..10.0f, p1Init = 3.5f,
            outLabel = "Net Drift (East +)", outUnit = "m/s", target = 0.0f, tol = 0.25f, gauge = -3.0f..3.0f,
            compute = { v, _ -> 3.0f - v * 0.6f },
            hint = "Zero drift occurs when v × 0.60 = 3.0 m/s.",
            explanation = "At v = 5.0 m/s, the 3.0 m/s upstream component cancels the 3.0 m/s river current cleanly.",
          ),
        )

      PracticalLabType.PROJECTILE_KINEMATICS ->
        listOf(
          slider(
            id = "proj_1",
            title = "Precision Range Calibration",
            scenario = "Launch speed is locked at u = 20 m/s (g = 10 m/s²). Aim the launcher angle θ to hit the 40.0 m horizontal target zone.",
            objective = "Tune launch angle θ (°) to land at Range R = 40.0 m.",
            p1Label = "Launch Angle θ", p1Unit = "°", p1Range = 10f..80f, p1Init = 20f,
            outLabel = "Horizontal Range", outUnit = "m", target = 40.0f, tol = 1.8f, gauge = 0f..45f,
            compute = { deg, _ -> (400f * sin(2f * deg * PI.toFloat() / 180f)) / 10f },
            hint = "Maximum range R = u² sin(2θ)/g hits 40 m when sin(2θ) = 1.",
            explanation = "At θ = 45°, sin(90°) = 1 yields maximum range R = 20²/10 = 40.0 m.",
          ),
          slider(
            id = "proj_2",
            title = "Peak Clearance Target",
            scenario = "Fire at θ = 30° (sin 30° = 0.5) to clear an obstacle whose exact apex H must reach 20.0 m (g = 10 m/s²).",
            objective = "Adjust initial speed u (m/s) at θ = 30° to reach Max Height H = 20.0 m.",
            p1Label = "Launch Speed u", p1Unit = "m/s", p1Range = 10f..60f, p1Init = 22f,
            outLabel = "Max Height H", outUnit = "m", target = 20.0f, tol = 1.2f, gauge = 0f..45f,
            compute = { u, _ -> (u * u * 0.25f) / 20f },
            hint = "H = (u sin 30°)² / (2g) = u² / 80 = 20 → u² = 1600.",
            explanation = "At u = 40 m/s and θ = 30°, vertical uᵧ = 20 m/s gives H = 20² / 20 = 20.0 m.",
          ),
          slider(
            id = "proj_3",
            title = "Total Hang-Time Sync",
            scenario = "A drone drop requires total flight time T = 6.0 s when launched at u = 60 m/s (g = 10 m/s²).",
            objective = "Dial angle θ (°) so Time of Flight T = 2u sin θ / g equals 6.0 s.",
            p1Label = "Launch Angle θ", p1Unit = "°", p1Range = 10f..85f, p1Init = 55f,
            outLabel = "Flight Time T", outUnit = "s", target = 6.0f, tol = 0.35f, gauge = 0f..12f,
            compute = { deg, _ -> (2f * 60f * sin(deg * PI.toFloat() / 180f)) / 10f },
            hint = "6.0 = 12 sin θ → sin θ = 0.50.",
            explanation = "At θ = 30°, sin 30° = 0.50 gives uᵧ = 30 m/s and T = 2(30)/10 = 6.0 s.",
          ),
        )

      PracticalLabType.TORQUE_MACHINES_LAB ->
        listOf(
          slider(
            id = "trq_1",
            title = "Meter-Rule Pivot Balance",
            scenario = "A 30 N counterweight hangs 40 cm left of the knife-edge pivot. Place a 20 N load on the right arm for zero net torque.",
            objective = "Slide distance d (cm) on the right side so clockwise and anticlockwise moments balance (Δτ = 0).",
            p1Label = "Right Arm Distance d", p1Unit = "cm", p1Range = 10f..80f, p1Init = 30f,
            outLabel = "Net Torque Bias", outUnit = "N·cm", target = 0f, tol = 60f, gauge = -600f..600f,
            compute = { d, _ -> 20f * d - 30f * 40f },
            hint = "30 × 40 = 20 × d → d = 1200 / 20 cm.",
            explanation = "At d = 60 cm, clockwise torque 20 × 60 = 1200 N·cm balances 30 × 40 = 1200 N·cm.",
          ),
          slider(
            id = "trq_2",
            title = "Pulley Efficiency Optimizer",
            scenario = "A block-and-tackle system has Velocity Ratio VR = 4 and lifts a 600 N crate. Set the applied effort E (N) so efficiency η = MA / VR reaches 75%.",
            objective = "Tune effort E (N) to deliver 75.0% efficiency at L = 600 N, VR = 4.",
            p1Label = "Applied Effort E", p1Unit = "N", p1Range = 120f..400f, p1Init = 300f,
            outLabel = "Efficiency η", outUnit = "%", target = 75.0f, tol = 3.0f, gauge = 30f..100f,
            compute = { e, _ -> ((600f / e) / 4f) * 100f },
            hint = "η = 600 / (4 × E) = 0.75 → E = 600 / 3 = 200 N.",
            explanation = "At E = 200 N, MA = 600/200 = 3.0, giving Efficiency = (3.0 / 4) × 100% = 75%.",
          ),
          slider(
            id = "trq_3",
            title = "Inclined Plane Mechanical Advantage",
            scenario = "Push a 500 N drum up a frictionless ramp of length L = 5.0 m. Adjust vertical platform height h (m) so required effort equals 200 N.",
            objective = "Adjust ramp height h (m) for VR = L/h = 2.5 (Effort = 200 N).",
            p1Label = "Vertical Height h", p1Unit = "m", p1Range = 0.5f..4.0f, p1Init = 3.0f,
            outLabel = "Required Effort", outUnit = "N", target = 200.0f, tol = 12f, gauge = 50f..400f,
            compute = { h, _ -> 500f * (h / 5.0f) },
            hint = "On a smooth incline, Effort = Load × (h / L) = 500 × h / 5 = 100h.",
            explanation = "Setting h = 2.0 m gives VR = 5.0/2.0 = 2.5 and required effort 500/2.5 = 200 N.",
          ),
        )

      PracticalLabType.SHM_FLUIDS_LAB ->
        listOf(
          slider(
            id = "shm_1",
            title = "Seconds Pendulum Tuning",
            scenario = "A physics laboratory clock needs a pendulum with an exact period T = 2.00 s where g = 9.87 m/s² (π² ≈ 9.87).",
            objective = "Adjust pendulum length L (m) to lock period T = 2π√(L/g) at 2.00 s.",
            p1Label = "Pendulum Length L", p1Unit = "m", p1Range = 0.25f..2.00f, p1Init = 0.50f,
            outLabel = "Period T", outUnit = "s", target = 2.00f, tol = 0.06f, gauge = 0.8f..3.0f,
            compute = { l, _ -> 2f * PI.toFloat() * sqrt(l / 9.87f) },
            hint = "When T = 2 s and g = π², L simplifies neatly to 1.00 m.",
            explanation = "T = 2π√(1.00 / π²) = 2.00 s regardless of bob mass.",
          ),
          slider(
            id = "shm_2",
            title = "Archimedes Upthrust Equalizer",
            scenario = "Lower a block into brine (density 1200 kg/m³, g = 10 m/s²). Control displaced volume V (litres = ×10⁻³ m³) to create 60.0 N upthrust.",
            objective = "Set submerged volume V (L) to yield Upthrust U = ρVg = 60.0 N.",
            p1Label = "Submerged Volume V", p1Unit = "L", p1Range = 1.0f..10.0f, p1Init = 2.5f,
            outLabel = "Upthrust U", outUnit = "N", target = 60.0f, tol = 2.5f, gauge = 10f..120f,
            compute = { vLitres, _ -> 1200f * (vLitres * 0.001f) * 10f },
            hint = "Each 1 L displaced gives 12 N upthrust (1200 × 10⁻³ × 10).",
            explanation = "Displacing 5.0 L of 1200 kg/m³ brine produces 1200 × 0.005 × 10 = 60.0 N upthrust.",
          ),
          slider(
            id = "shm_3",
            title = "Hooke's Law Spring Extension",
            scenario = "A steel coil has stiffness k = 250 N/m. Adjust suspended load F (N) to store exactly 0.80 J of elastic potential energy.",
            objective = "Tune tension force F (N) so Elastic Energy E = F² / (2k) equals 0.80 J.",
            p1Label = "Suspended Load F", p1Unit = "N", p1Range = 5f..40f, p1Init = 10f,
            outLabel = "Stored Energy E", outUnit = "J", target = 0.80f, tol = 0.05f, gauge = 0f..3.2f,
            compute = { f, _ -> (f * f) / (2f * 250f) },
            hint = "F² / 500 = 0.80 → F² = 400.",
            explanation = "At F = 20 N, extension e = 0.08 m and E = ½Fe = 0.5 × 20 × 0.08 = 0.80 J.",
          ),
        )

      PracticalLabType.THERMAL_EXPANSION_CALORIMETRY ->
        listOf(
          slider(
            id = "thm_1",
            title = "Calorimetry Power Heater Match",
            scenario = "Heat 2.0 kg of water (c = 4200 J/kg·K) from 20°C to 45°C using an electric heater for 100 s.",
            objective = "Adjust heater power P (kW) so ΔT hits 25.0°C in 100 s.",
            p1Label = "Heater Power P", p1Unit = "kW", p1Range = 0.5f..4.0f, p1Init = 1.0f,
            outLabel = "Temp Rise ΔT", outUnit = "°C", target = 25.0f, tol = 1.2f, gauge = 0f..48f,
            compute = { pKw, _ -> (pKw * 1000f * 100f) / (2.0f * 4200f) },
            hint = "Q = mcΔT = 2 × 4200 × 25 = 210,000 J → P = 210,000 / 100 s = 2.10 kW.",
            explanation = "2.10 kW over 100 s delivers 210 kJ, raising 2.0 kg water by 25.0°C.",
          ),
          slider(
            id = "thm_2",
            title = "Kelvin Charles' Law Chamber",
            scenario = "A cylinder holds 300 cm³ of gas at 300 K under constant pressure. Adjust absolute temperature T (K) to expand volume to 450 cm³.",
            objective = "Dial Kelvin temperature T (K) for target volume V₂ = 450 cm³.",
            p1Label = "Absolute Temp T₂", p1Unit = "K", p1Range = 200f..600f, p1Init = 320f,
            outLabel = "Gas Volume V₂", outUnit = "cm³", target = 450.0f, tol = 15f, gauge = 200f..600f,
            compute = { t, _ -> 300f * (t / 300f) },
            hint = "V₁/T₁ = V₂/T₂ → V₂ = T₂ numerically here since V₁ = T₁ = 300.",
            explanation = "Expanding 300 cm³ by 1.5× at constant pressure requires T₂ = 1.5 × 300 = 450 K.",
          ),
          slider(
            id = "thm_3",
            title = "Railway Steel Gap Expansion",
            scenario = "A 20.0 m steel rail (α = 1.2 × 10⁻⁵ K⁻¹) heats up across ΔT (°C). Set ΔT so linear expansion ΔL reaches exactly 12.0 mm.",
            objective = "Adjust temperature rise ΔT (°C) to produce 12.0 mm expansion.",
            p1Label = "Temp Rise ΔT", p1Unit = "°C", p1Range = 10f..80f, p1Init = 25f,
            outLabel = "Expansion ΔL", outUnit = "mm", target = 12.0f, tol = 0.6f, gauge = 0f..20f,
            compute = { dt, _ -> 20000f * 1.2e-5f * dt },
            hint = "ΔL = 20,000 mm × 1.2×10⁻⁵ × ΔT = 0.24 × ΔT mm.",
            explanation = "At ΔT = 50°C, expansion is 20,000 × 1.2×10⁻⁵ × 50 = 12.0 mm.",
          ),
        )

      PracticalLabType.WAVE_OPTICS_BENCH ->
        listOf(
          slider(
            id = "wo_1",
            title = "Standing Wave Speed Matcher",
            scenario = "A plucked guitar wire vibrates at f = 250 Hz. Tune wavelength λ (m) to generate wave speed v = 100.0 m/s.",
            objective = "Adjust wavelength λ (m) so v = fλ equals 100.0 m/s.",
            p1Label = "Wavelength λ", p1Unit = "m", p1Range = 0.10f..1.00f, p1Init = 0.75f,
            outLabel = "Wave Speed v", outUnit = "m/s", target = 100.0f, tol = 5.0f, gauge = 25f..250f,
            compute = { lam, _ -> 250f * lam },
            hint = "v = 250 × λ = 100 → λ = 0.40 m.",
            explanation = "At λ = 0.40 m and f = 250 Hz, wave speed v = 100.0 m/s.",
          ),
          slider(
            id = "wo_2",
            title = "Converging Lens Real Focus",
            scenario = "A converging lens has focal length f = 15.0 cm. Move object distance u (cm) to form an equally sized inverted image (M = 1.0, v = 30.0 cm).",
            objective = "Place object u (cm) so image distance v = uf/(u−f) equals 30.0 cm.",
            p1Label = "Object Distance u", p1Unit = "cm", p1Range = 18f..60f, p1Init = 45f,
            outLabel = "Image Distance v", outUnit = "cm", target = 30.0f, tol = 1.5f, gauge = 15f..90f,
            compute = { u, _ -> (u * 15f) / (u - 15f) },
            hint = "Equal real inverted magnification happens at u = 2f.",
            explanation = "Placing the object at u = 2f = 30.0 cm puts the sharp real image at v = 30.0 cm.",
          ),
          slider(
            id = "wo_3",
            title = "Closed Resonance Pipe Fundamental",
            scenario = "Sound travels at 340 m/s. Tune a closed air column length L (cm) to resonate cleanly at fundamental frequency f₀ = 425 Hz.",
            objective = "Adjust closed tube length L (cm) so f₀ = v / (4L) hits 425 Hz.",
            p1Label = "Air Column Length L", p1Unit = "cm", p1Range = 10f..40f, p1Init = 32f,
            outLabel = "Fundamental f₀", outUnit = "Hz", target = 425f, tol = 18f, gauge = 200f..900f,
            compute = { lCm, _ -> 340f / (4f * (lCm / 100f)) },
            hint = "L = v / (4f₀) = 340 / (4 × 425) = 0.20 m.",
            explanation = "At L = 20.0 cm (0.20 m), quarter-wavelength resonance f₀ = 340 / 0.80 = 425 Hz.",
          ),
        )

      PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB ->
        listOf(
          slider(
            id = "cap_1",
            title = "Charge Storage Lock",
            scenario = "A 50 μF filter capacitor feeds a rectifier across adjustable DC voltage V.",
            objective = "Tune supply V (V) to store Q = 600 μC.",
            p1Label = "Supply Voltage V", p1Unit = "V", p1Range = 2f..24f, p1Init = 6f,
            outLabel = "Stored Charge Q", outUnit = "μC", target = 600f, tol = 25f, gauge = 100f..1200f,
            compute = { v, _ -> 50f * v },
            hint = "Q = C·V = 50 × V = 600 → V = 12 V.",
            explanation = "At V = 12.0 V, a 50 μF capacitor stores Q = 600 μC.",
          ),
          slider(
            id = "cap_2",
            title = "Series Capacitor Equivalent",
            scenario = "Connect a 6.0 μF capacitor in series with variable capacitor C₂ (μF). Hit exact net capacitance C_eq = 2.0 μF.",
            objective = "Select C₂ (μF) so (6·C₂)/(6 + C₂) = 2.0 μF.",
            p1Label = "Series Capacitor C₂", p1Unit = "μF", p1Range = 1.0f..12.0f, p1Init = 6.0f,
            outLabel = "Net Capacitance", outUnit = "μF", target = 2.0f, tol = 0.15f, gauge = 0.5f..4.5f,
            compute = { c2, _ -> (6f * c2) / (6f + c2) },
            hint = "1/2 = 1/6 + 1/C₂ → 1/C₂ = 1/3 → C₂ = 3.0 μF.",
            explanation = "6 μF and 3 μF in series yield 18 / 9 = 2.0 μF.",
          ),
          slider(
            id = "cap_3",
            title = "Flash-Lamp Energy Burst",
            scenario = "A 200 μF flash bank charges to voltage V. Reach exact energy U = ½CV² = 10.0 mJ.",
            objective = "Set charging voltage V (V) to store 10.0 mJ.",
            p1Label = "Charging Voltage V", p1Unit = "V", p1Range = 2f..20f, p1Init = 5f,
            outLabel = "Stored Energy U", outUnit = "mJ", target = 10.0f, tol = 0.6f, gauge = 0f..40f,
            compute = { v, _ -> 0.5f * 0.2f * v * v },
            hint = "½(0.2 mF)V² = 0.1 V² = 10 → V² = 100.",
            explanation = "Charging 200 μF to 10.0 V stores ½ × 200×10⁻⁶ × 100 = 10.0 mJ.",
          ),
        )

      PracticalLabType.OHMS_CIRCUIT ->
        listOf(
          slider(
            id = "ohm_1",
            title = "Load Power Matching",
            scenario = "A solar battery (E = 12.0 V, internal resistance r = 2.0 Ω) drives load resistor R (Ω). Match R for maximum power delivery P = 18.0 W.",
            objective = "Tune external resistor R (Ω) to achieve peak power P = 18.0 W.",
            p1Label = "Load Resistance R", p1Unit = "Ω", p1Range = 0.5f..8.0f, p1Init = 5.0f,
            outLabel = "Load Power P", outUnit = "W", target = 18.0f, tol = 0.7f, gauge = 0f..20f,
            compute = { r, _ -> val i = 12f / (r + 2f); i * i * r },
            hint = "Maximum power transfer occurs when R equals internal resistance r.",
            explanation = "At R = r = 2.0 Ω, current I = 12/4 = 3.0 A and P = I²R = 18.0 W.",
          ),
          slider(
            id = "ohm_2",
            title = "Terminal Potential Check",
            scenario = "Same 12.0 V cell (r = 2.0 Ω): dial current draw I (A) via rheostat until terminal p.d. V = E − Ir reads 9.0 V.",
            objective = "Adjust circuit current I (A) to drop terminal voltage to 9.0 V.",
            p1Label = "Circuit Current I", p1Unit = "A", p1Range = 0.2f..4.0f, p1Init = 0.5f,
            outLabel = "Terminal Voltage V", outUnit = "V", target = 9.0f, tol = 0.3f, gauge = 4f..12f,
            compute = { i, _ -> 12f - i * 2f },
            hint = "Lost volts Ir = 12 − 9 = 3.0 V → I = 3.0 / 2.0 A.",
            explanation = "Drawing 1.5 A loses 3.0 V internally across r = 2.0 Ω, leaving V = 9.0 V.",
          ),
          slider(
            id = "ohm_3",
            title = "Parallel Shunt Equivalent",
            scenario = "Wire a 12 Ω lamp in parallel with variable resistor R₂ (Ω) to hit combined resistance R_eq = 4.0 Ω.",
            objective = "Tune R₂ (Ω) to yield net parallel resistance 4.0 Ω.",
            p1Label = "Parallel Resistor R₂", p1Unit = "Ω", p1Range = 2f..18f, p1Init = 12f,
            outLabel = "Net Resistance", outUnit = "Ω", target = 4.0f, tol = 0.25f, gauge = 1f..10f,
            compute = { r2, _ -> (12f * r2) / (12f + r2) },
            hint = "1/4 = 1/12 + 1/R₂ → 1/R₂ = 2/12 = 1/6.",
            explanation = "12 Ω in parallel with 6.0 Ω yields (12 × 6) / 18 = 4.0 Ω.",
          ),
        )

      PracticalLabType.EM_TRANSFORMER_AC_LAB ->
        listOf(
          slider(
            id = "em_1",
            title = "Substation Step-Down Ratio",
            scenario = "Input primary voltage is Vₚ = 240 V on Nₚ = 1200 turns. Adjust secondary turns Nₛ to step down output cleanly to 12.0 V.",
            objective = "Tune secondary turns Nₛ for 12.0 V AC output.",
            p1Label = "Secondary Turns Nₛ", p1Unit = "turns", p1Range = 20f..200f, p1Init = 120f,
            outLabel = "Secondary Voltage Vₛ", outUnit = "V", target = 12.0f, tol = 0.6f, gauge = 0f..40f,
            compute = { ns, _ -> 240f * (ns / 1200f) },
            hint = "Vₛ / 240 = Nₛ / 1200 → Nₛ = 1200 / 20.",
            explanation = "60 secondary turns (20:1 step-down ratio) transforms 240 V down to 12.0 V.",
          ),
          console(
            id = "em_2",
            title = "Digital Gate Alarm Interlock",
            scenario = "A vault alarm triggers HIGH (1) ONLY when Door Sensor A = 0 AND Override Key B = 1 using Inverter + AND logic.",
            objective = "Select the single logic gate whose output equals 1 only when BOTH inputs equal 1, then identify its active output for (A=1, B=1).",
            cALabel = "Gate Chip", cAOptions = listOf("AND", "OR", "NAND", "XOR"),
            cBLabel = "Output for (1, 1)", cBOptions = listOf("0 (LOW)", "1 (HIGH)"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b ->
              val g = listOf("AND", "OR", "NAND", "XOR")[a]
              val out = if (b == 1) "HIGH (1)" else "LOW (0)"
              "Simulated Circuit: $g Gate tested at truth output → $out"
            },
            hint = "AND outputs 1 iff both inputs equal 1; NAND and XOR output 0 for (1, 1).",
            explanation = "An AND gate produces HIGH (1) exclusively when every input pin is HIGH (1).",
          ),
          slider(
            id = "em_3",
            title = "Transformer Power Transmission",
            scenario = "An ideal transformer outputs 240 W at Vₛ = 12 V from a 240 V primary line.",
            objective = "Match primary current Iₚ (A) to supply the 240 W load with zero loss.",
            p1Label = "Primary Current Iₚ", p1Unit = "A", p1Range = 0.2f..5.0f, p1Init = 2.5f,
            outLabel = "Input Power", outUnit = "W", target = 240f, tol = 12f, gauge = 0f..600f,
            compute = { ip, _ -> 240f * ip },
            hint = "P_in = Vₚ Iₚ = 240 × Iₚ = 240 W → Iₚ = 1.0 A.",
            explanation = "Stepping down 240 V to 12 V multiplies current 20× while Iₚ remains 1.0 A.",
          ),
        )

      PracticalLabType.QUANTUM_NUCLEAR_LAB ->
        listOf(
          slider(
            id = "qn_1",
            title = "Radioactive Half-Life Countdown",
            scenario = "An isotope sample starts at N₀ = 160 g with half-life T₁/₂ = 4 hours. Stop the clock when remaining undecayed mass reaches 20.0 g.",
            objective = "Advance elapsed time t (hours) until N(t) = 20.0 g.",
            p1Label = "Elapsed Time t", p1Unit = "hrs", p1Range = 0f..20f, p1Init = 4f,
            outLabel = "Remaining Mass", outUnit = "g", target = 20.0f, tol = 1.5f, gauge = 0f..160f,
            compute = { t, _ -> 160f * 0.5f.pow(t / 4f) },
            hint = "160 → 80 → 40 → 20 takes 3 half-lives (3 × 4 hrs).",
            explanation = "After t = 12 hours (3 half-lives), 160 × (1/2)³ = 20.0 g remains.",
          ),
          slider(
            id = "qn_2",
            title = "Photoelectric Stopping Voltage",
            scenario = "Incident photons carry energy E = 4.2 eV onto caesium with work function Φ = 2.1 eV.",
            objective = "Adjust stopping potential Vₛ (V) to halt maximum kinetic electrons (eVₛ = K_max).",
            p1Label = "Stopping Potential Vₛ", p1Unit = "V", p1Range = 0.2f..4.0f, p1Init = 0.8f,
            outLabel = "Residual Electron Energy", outUnit = "eV", target = 0.0f, tol = 0.15f, gauge = -2.0f..2.5f,
            compute = { vs, _ -> (4.2f - 2.1f) - vs },
            hint = "K_max = hf − Φ = 4.2 − 2.1 = 2.1 eV.",
            explanation = "A retarding potential Vₛ = 2.1 V provides 2.1 eV work to halt fastest photoelectrons.",
          ),
          console(
            id = "qn_3",
            title = "Nuclear Decay Balance",
            scenario = "Parent nucleus ²³⁸₉₂U emits an alpha particle (⁴₂He) to form Thorium.",
            objective = "Select the daughter mass number A and atomic number Z after one α-emission.",
            cALabel = "Daughter Mass A", cAOptions = listOf("234", "236", "238", "242"),
            cBLabel = "Daughter Atomic Z", cBOptions = listOf("88", "90", "91", "94"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b ->
              val ms = listOf("234", "236", "238", "242")[a]
              val zs = listOf("88", "90", "91", "94")[b]
              "Balanced Equation Preview: ²³⁸₉₂U → $ms/$zs Th + ⁴₂He"
            },
            hint = "Alpha emission subtracts 4 from mass number A and 2 from atomic number Z.",
            explanation = "Conserving nucleons and charge gives A = 238 − 4 = 234 and Z = 92 − 2 = 90.",
          ),
        )

      PracticalLabType.NUMBER_BASE_INDICES_LAB ->
        listOf(
          slider(
            id = "nb_1",
            title = "Binary Value Synthesizer",
            scenario = "Decode binary 10110₂ into base 10 using positional powers 16 + 0 + 4 + 2 + 0.",
            objective = "Match the exact decimal integer equivalent to 10110₂.",
            p1Label = "Decimal Slider", p1Unit = "", p1Range = 10f..31f, p1Init = 14f,
            outLabel = "Difference from 10110₂", outUnit = "", target = 0f, tol = 0.4f, gauge = -12f..12f,
            compute = { n, _ -> n.roundToInt() - 22f },
            hint = "1×2⁴ + 0×2³ + 1×2² + 1×2¹ + 0×2⁰ = 16 + 4 + 2.",
            explanation = "10110₂ evaluates cleanly to 22₁₀.",
          ),
          slider(
            id = "nb_2",
            title = "Fractional Index Evaluator",
            scenario = "Solve (27/8)^(−2/3) by flipping the negative exponent and applying cube root before squaring.",
            objective = "Dial the exact simplified rational value as a decimal (to 2 d.p.).",
            p1Label = "Evaluated Value", p1Unit = "", p1Range = 0.10f..1.50f, p1Init = 0.80f,
            outLabel = "Target 4/9 Ratio", outUnit = "", target = 0.44f, tol = 0.03f, gauge = 0.1f..1.5f,
            compute = { v, _ -> v },
            hint = "(8/27)^(2/3) = (2/3)² = 4/9 ≈ 0.444.",
            explanation = "Flipping to (8/27)^(2/3) yields (³√(8/27))² = (2/3)² = 4/9 ≈ 0.44.",
          ),
          slider(
            id = "nb_3",
            title = "Modular Clock Residue",
            scenario = "Market rotation repeats every 7 days (mod 7). Today is Day 3. Advance 17 days forward.",
            objective = "Set the modulo-7 remainder for (3 + 17) mod 7.",
            p1Label = "Mod-7 Remainder", p1Unit = "", p1Range = 0f..6f, p1Init = 1f,
            outLabel = "Residue (0–6)", outUnit = "", target = 6f, tol = 0.4f, gauge = 0f..6f,
            compute = { r, _ -> r.roundToInt().toFloat() },
            hint = "20 = 2 × 7 + remainder.",
            explanation = "20 ÷ 7 leaves remainder 6 (20 ≡ 6 mod 7).",
          ),
        )

      PracticalLabType.SETS_VENN_LAB ->
        buildVennOrStatsRounds("sets", PracticalLabType.SETS_VENN_LAB)

      PracticalLabType.ALGEBRA_VARIATION_LAB ->
        listOf(
          slider(
            id = "av_1",
            title = "Inverse Variation Hyperbola",
            scenario = "Quantity y varies inversely as x (y = k/x). Given y = 12 when x = 3, find y when x = 9.",
            objective = "Adjust y-output slider to match y when x = 9.",
            p1Label = "Predicted y", p1Unit = "", p1Range = 1f..16f, p1Init = 8f,
            outLabel = "Selected Value", outUnit = "", target = 4.0f, tol = 0.35f, gauge = 0f..16f,
            compute = { v, _ -> v },
            hint = "Constant k = x·y = 3 × 12 = 36, so y = 36 / 9.",
            explanation = "With k = 36, x = 9 yields y = 36 / 9 = 4.0.",
          ),
          slider(
            id = "av_2",
            title = "Joint Variation Scaling",
            scenario = "Resistance R varies directly as length L and inversely as r². Doubling L and halving r multiplies R by factor F.",
            objective = "Dial factor F for the combined change.",
            p1Label = "Multiplier F", p1Unit = "×", p1Range = 1f..12f, p1Init = 2f,
            outLabel = "New / Old Ratio", outUnit = "×", target = 8.0f, tol = 0.4f, gauge = 1f..12f,
            compute = { f, _ -> f.roundToInt().toFloat() },
            hint = "Ratio = (2) / (0.5)² = 2 / 0.25.",
            explanation = "Doubling L gives ×2 while halving r divides by (1/2)² = 1/4, multiplying total R by 8×.",
          ),
          slider(
            id = "av_3",
            title = "Linear Gradient Balance",
            scenario = "Line y = mx − 4 passes through coordinate (5, 11). Tune slope m to thread the point cleanly.",
            objective = "Dial slope m so 5m − 4 = 11.",
            p1Label = "Gradient m", p1Unit = "", p1Range = 0f..6f, p1Init = 1f,
            outLabel = "y at x = 5", outUnit = "", target = 11.0f, tol = 0.4f, gauge = -4f..26f,
            compute = { m, _ -> 5f * m - 4f },
            hint = "5m = 11 + 4 = 15 → m = 3.",
            explanation = "At slope m = 3, y(5) = 3(5) − 4 = 11.",
          ),
        )

      PracticalLabType.QUADRATIC_TANGENT ->
        listOf(
          slider(
            id = "qt_1",
            title = "Zero Discriminant Tangent",
            scenario = "Equation x² + bx + 9 = 0 touches the x-axis at exactly one repeated root when Δ = b² − 4ac = 0.",
            objective = "Adjust positive coefficient b so Δ = b² − 36 hits 0.0.",
            p1Label = "Coefficient b", p1Unit = "", p1Range = 0f..12f, p1Init = 3f,
            outLabel = "Discriminant Δ", outUnit = "", target = 0.0f, tol = 3.0f, gauge = -36f..108f,
            compute = { b, _ -> b * b - 36f },
            hint = "b² = 4(1)(9) = 36 → b = 6.",
            explanation = "Setting b = 6 yields (x + 3)² = 0 with repeated tangent root x = −3.",
          ),
          slider(
            id = "qt_2",
            title = "Sum of Roots Tuner",
            scenario = "Parabola 2x² − kx + 6 = 0 has roots α and β summing to α + β = 5.0.",
            objective = "Adjust parameter k so −(−k)/2 equals 5.0.",
            p1Label = "Parameter k", p1Unit = "", p1Range = 2f..16f, p1Init = 5f,
            outLabel = "Sum α + β", outUnit = "", target = 5.0f, tol = 0.25f, gauge = 1f..8f,
            compute = { k, _ -> k / 2f },
            hint = "α + β = −b/a = k / 2 = 5.",
            explanation = "k = 10 gives α + β = 10 / 2 = 5.0.",
          ),
          slider(
            id = "qt_3",
            title = "Vertex Axis Finder",
            scenario = "Find the minimum value of y = x² − 6x + 11 by sweeping x along the parabola.",
            objective = "Locate x along the symmetry axis to minimize y.",
            p1Label = "Test x Coordinate", p1Unit = "", p1Range = -2f..8f, p1Init = 0f,
            outLabel = "Parabola Value y", outUnit = "", target = 2.0f, tol = 0.25f, gauge = 0f..25f,
            compute = { x, _ -> x * x - 6f * x + 11f },
            hint = "Axis of symmetry x = −b / (2a) = 6 / 2 = 3.",
            explanation = "At x = 3, minimum vertex value y = (3−3)² + 2 = 2.0.",
          ),
        )

      PracticalLabType.SEQUENCE_MATRIX_LAB ->
        listOf(
          slider(
            id = "sm_1",
            title = "2×2 Matrix Determinant Lock",
            scenario = "Matrix [[4, k], [2, 3]] is non-singular with exact determinant det = 6.",
            objective = "Adjust entry k so (4×3) − (2×k) = 6.",
            p1Label = "Matrix Entry k", p1Unit = "", p1Range = 0f..8f, p1Init = 5f,
            outLabel = "Determinant Δ", outUnit = "", target = 6.0f, tol = 0.4f, gauge = -4f..12f,
            compute = { k, _ -> 12f - 2f * k },
            hint = "12 − 2k = 6 → 2k = 6.",
            explanation = "At k = 3, det = (4×3) − (2×3) = 6.",
          ),
          slider(
            id = "sm_2",
            title = "Geometric Infinite Sum",
            scenario = "A GP has first term a = 12 and common ratio r < 1, converging to S∞ = 18.0.",
            objective = "Tune common ratio r so S∞ = 12 / (1 − r) = 18.0.",
            p1Label = "Common Ratio r", p1Unit = "", p1Range = 0.05f..0.70f, p1Init = 0.50f,
            outLabel = "Sum to Infinity S∞", outUnit = "", target = 18.0f, tol = 0.9f, gauge = 12f..40f,
            compute = { r, _ -> 12f / (1f - r) },
            hint = "12 / (1 − r) = 18 → 1 − r = 2/3 → r = 1/3 ≈ 0.33.",
            explanation = "At r = 0.33 (1/3), S∞ = 12 / (2/3) = 18.0.",
          ),
          slider(
            id = "sm_3",
            title = "AP Common Difference",
            scenario = "An Arithmetic Progression starts at a = 5 and reaches 6th term T₆ = 25.",
            objective = "Adjust common difference d so 5 + 5d = 25.",
            p1Label = "Difference d", p1Unit = "", p1Range = 1f..8f, p1Init = 2f,
            outLabel = "6th Term T₆", outUnit = "", target = 25.0f, tol = 0.5f, gauge = 10f..45f,
            compute = { d, _ -> 5f + 5f * d },
            hint = "T₆ = a + (6 − 1)d = 5 + 5d = 25 → d = 4.",
            explanation = "Common difference d = 4 yields sequence 5, 9, 13, 17, 21, 25.",
          ),
        )

      PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB ->
        listOf(
          slider(
            id = "pc_1",
            title = "Regular Polygon Interior Lock",
            scenario = "Adjust sides n of a regular polygon until each exterior angle equals 45° (interior = 135°).",
            objective = "Select polygon sides n for 45° exterior angle.",
            p1Label = "Sides Count n", p1Unit = "sides", p1Range = 3f..12f, p1Init = 5f,
            outLabel = "Exterior Angle", outUnit = "°", target = 45f, tol = 2f, gauge = 30f..120f,
            compute = { n, _ -> 360f / n.roundToInt().coerceAtLeast(3) },
            hint = "n = 360° / 45° = 8 (Octagon).",
            explanation = "A regular octagon (n = 8) has exterior angle 360°/8 = 45° and interior angle 135°.",
          ),
          slider(
            id = "pc_2",
            title = "Cyclic Quad Supplementary Match",
            scenario = "Opposite corners of a cyclic quadrilateral are 72° and x°.",
            objective = "Dial angle x (°) so opposite angles sum to 180°.",
            p1Label = "Opposite Angle x", p1Unit = "°", p1Range = 60f..140f, p1Init = 90f,
            outLabel = "Opposite Sum", outUnit = "°", target = 180f, tol = 2f, gauge = 132f..212f,
            compute = { x, _ -> 72f + x },
            hint = "x = 180° − 72°.",
            explanation = "Opposite angles of a cyclic quadrilateral sum to 180°, so x = 108°.",
          ),
          slider(
            id = "pc_3",
            title = "Tangent-Radius Right Angle",
            scenario = "Center angle ∠AOB = 2θ subtended at the centre when circumference angle θ = 54°.",
            objective = "Dial central angle ∠AOB (°).",
            p1Label = "Center Angle", p1Unit = "°", p1Range = 60f..150f, p1Init = 80f,
            outLabel = "Target 2θ", outUnit = "°", target = 108f, tol = 2f, gauge = 60f..150f,
            compute = { a, _ -> a },
            hint = "Angle at centre = 2 × angle at circumference = 2 × 54°.",
            explanation = "Centre angle equals 2 × 54° = 108°.",
          ),
        )

      PracticalLabType.MENSURATION_3D_LAB ->
        listOf(
          slider(
            id = "m3_1",
            title = "Cone Volume Scale Challenge",
            scenario = "A circular cone has fixed height h = 12 cm. Tune radius r (cm) so Volume V = ⅓πr²h equals 314.2 cm³ (≈ 100π).",
            objective = "Adjust radius r (cm) for V = 100π ≈ 314.2 cm³.",
            p1Label = "Base Radius r", p1Unit = "cm", p1Range = 2f..8f, p1Init = 3f,
            outLabel = "Cone Volume", outUnit = "cm³", target = 314.2f, tol = 18f, gauge = 50f..800f,
            compute = { r, _ -> (PI.toFloat() * r * r * 12f) / 3f },
            hint = "⅓πr²(12) = 4πr² = 100π → r² = 25 → r = 5 cm.",
            explanation = "At r = 5.0 cm and h = 12 cm, V = 4π(25) = 100π ≈ 314.2 cm³.",
          ),
          slider(
            id = "m3_2",
            title = "Cone Slant Height Pythagoras",
            scenario = "A 3-4-5 cone uses radius r = 6 cm and vertical height h = 8 cm.",
            objective = "Set curved surface slant length l (cm).",
            p1Label = "Slant Height l", p1Unit = "cm", p1Range = 6f..14f, p1Init = 7f,
            outLabel = "Selected l", outUnit = "cm", target = 10.0f, tol = 0.3f, gauge = 6f..14f,
            compute = { l, _ -> l },
            hint = "l = √(6² + 8²) = √100.",
            explanation = "Pythagoras gives slant height l = 10.0 cm cleanly.",
          ),
          slider(
            id = "m3_3",
            title = "Longitude Parallel Speed Scale",
            scenario = "Latitude θ = 60° N reduces Earth circle radius R_eff = R cos 60° (R = 6400 km).",
            objective = "Dial reduced parallel circle radius (km) at 60° N.",
            p1Label = "Parallel Radius r", p1Unit = "km", p1Range = 1600f..6400f, p1Init = 4800f,
            outLabel = "Selected r", outUnit = "km", target = 3200f, tol = 150f, gauge = 1600f..6400f,
            compute = { r, _ -> r },
            hint = "r = 6400 × cos 60° = 6400 × 0.5.",
            explanation = "At 60° latitude, parallel radius shrinks by cos 60° = 0.5 to 3200 km.",
          ),
        )

      PracticalLabType.TRIG_BEARING_LAB ->
        listOf(
          slider(
            id = "tb_1",
            title = "Back-Bearing Navigator",
            scenario = "Forward ship bearing from A to B is 065°. Compute the return back-bearing from B back to A.",
            objective = "Rotate compass dial (°) to the return back-bearing from B to A.",
            p1Label = "Return Bearing", p1Unit = "°", p1Range = 100f..340f, p1Init = 145f,
            outLabel = "Heading Set", outUnit = "°", target = 245f, tol = 4f, gauge = 100f..340f,
            compute = { b, _ -> b },
            hint = "For forward bearing < 180°, add 180°.",
            explanation = "Back-bearing = 065° + 180° = 245°.",
          ),
          slider(
            id = "tb_2",
            title = "Mast Elevation Height",
            scenario = "Stand 50 m from a radio mast base; angle of elevation to top is θ (°). Hit mast height h = 50 m.",
            objective = "Adjust sighting angle θ (°) so h = 50 tan θ reaches 50.0 m.",
            p1Label = "Elevation Angle θ", p1Unit = "°", p1Range = 20f..70f, p1Init = 30f,
            outLabel = "Mast Height h", outUnit = "m", target = 50.0f, tol = 2.5f, gauge = 18f..137f,
            compute = { deg, _ -> 50f * kotlin.math.tan(deg * PI.toFloat() / 180f) },
            hint = "tan θ = 50 / 50 = 1.0 → θ = 45°.",
            explanation = "At θ = 45°, tan 45° = 1.0 confirms height equals horizontal distance (50 m).",
          ),
          slider(
            id = "tb_3",
            title = "Sine Rule Side Lock",
            scenario = "In triangle ABC, side a = 10 cm opposite 30°, and angle B = 90°.",
            objective = "Dial side b (cm) using a / sin 30° = b / sin 90°.",
            p1Label = "Side b Length", p1Unit = "cm", p1Range = 10f..30f, p1Init = 15f,
            outLabel = "Computed b", outUnit = "cm", target = 20.0f, tol = 0.6f, gauge = 10f..30f,
            compute = { b, _ -> b },
            hint = "10 / 0.5 = b / 1.0.",
            explanation = "b = 10 / 0.5 = 20.0 cm.",
          ),
        )

      PracticalLabType.COORDINATE_CALCULUS_LAB ->
        listOf(
          slider(
            id = "cc_1",
            title = "Instantaneous Gradient Lock",
            scenario = "Track curve y = ½x². Move x₀ until tangent slope dy/dx = x₀ reaches exact slope m = 4.0.",
            objective = "Slide x₀ to hit tangent gradient 4.0.",
            p1Label = "Curve Point x₀", p1Unit = "", p1Range = 0f..6f, p1Init = 1f,
            outLabel = "Gradient dy/dx", outUnit = "", target = 4.0f, tol = 0.25f, gauge = 0f..6f,
            compute = { x, _ -> x },
            hint = "d/dx(½x²) = x, so set x₀ = 4.0.",
            explanation = "At x₀ = 4.0 on y = ½x², the tangent gradient equals 4.0.",
          ),
          slider(
            id = "cc_2",
            title = "Definite Integral Area Match",
            scenario = "Compute area under curve y = 3x² from x = 0 to upper limit b.",
            objective = "Slide upper limit b until ∫₀ᵇ 3x² dx = b³ equals 27.0.",
            p1Label = "Upper Limit b", p1Unit = "", p1Range = 1.0f..4.0f, p1Init = 2.0f,
            outLabel = "Area ∫ 3x² dx", outUnit = "", target = 27.0f, tol = 1.5f, gauge = 1f..64f,
            compute = { b, _ -> b * b * b },
            hint = "b³ = 27 → b = 3.",
            explanation = "[x³] from 0 to 3 evaluates to 3³ = 27.0.",
          ),
          slider(
            id = "cc_3",
            title = "Midpoint Distance Check",
            scenario = "Find the distance from origin (0, 0) to coordinate (x, 12). Hit exact distance d = 13.0.",
            objective = "Adjust x-coordinate so √(x² + 12²) = 13.0.",
            p1Label = "x Coordinate", p1Unit = "", p1Range = 1f..10f, p1Init = 8f,
            outLabel = "Distance d", outUnit = "", target = 13.0f, tol = 0.35f, gauge = 12f..16f,
            compute = { x, _ -> sqrt(x * x + 144f) },
            hint = "5-12-13 Pythagorean triple: x = 5.",
            explanation = "√(5² + 12²) = √169 = 13.0.",
          ),
        )

      PracticalLabType.PROBABILITY_STATISTICS_LAB ->
        buildVennOrStatsRounds("stats", PracticalLabType.PROBABILITY_STATISTICS_LAB)

      PracticalLabType.BONDING_PERIODIC_LAB ->
        buildChemistryDiscreteOrSlider(labType)
      PracticalLabType.SEPARATION_ATOMIC_LAB,
      PracticalLabType.STOICHIOMETRY_MOLE_LAB,
      PracticalLabType.GAS_LAW_PISTON,
      PracticalLabType.TITRATION_PH,
      PracticalLabType.ENERGETICS_KINETICS_LAB,
      PracticalLabType.REDOX_ELECTROLYSIS_LAB,
      PracticalLabType.INORGANIC_GASES_METALS_LAB,
      PracticalLabType.ORGANIC_HYDROCARBON_LAB,
      PracticalLabType.QUALITATIVE_ANALYSIS_LAB -> buildChemistryDiscreteOrSlider(labType)

      PracticalLabType.CELL_OSMOSIS_LAB,
      PracticalLabType.BIOENERGETICS_ENZYME_LAB,
      PracticalLabType.NUTRITION_TRANSPORT_LAB,
      PracticalLabType.RESPIRATION_KIDNEY_LAB,
      PracticalLabType.NERVOUS_ENDOCRINE_LAB,
      PracticalLabType.REPRODUCTION_FLOWER_LAB,
      PracticalLabType.MENDELIAN_GENETICS,
      PracticalLabType.EVOLUTION_VARIATION_LAB,
      PracticalLabType.ECOLOGY_ENERGY_PYRAMID,
      PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB -> buildBiologyChallenges(labType)

      PracticalLabType.PARTS_CLAUSES_LAB,
      PracticalLabType.SYNTAX_LEXIS_STUDIO,
      PracticalLabType.LEXIS_IDIOMS_REGISTER_LAB,
      PracticalLabType.ORAL_ENGLISH_STRESS,
      PracticalLabType.PHONETICS_CONSONANTS_LAB,
      PracticalLabType.COMPREHENSION_ESSAY_LAB -> buildEnglishChallenges(labType)

      PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
      PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
      PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB -> buildSocialScienceChallenges(labType)
    }

  private fun buildVennOrStatsRounds(kind: String, labType: PracticalLabType): List<GamifiedRound> =
    if (kind == "sets") {
      listOf(
        slider(
          id = "sv_1",
          title = "Venn Overlap Solver",
          scenario = "In a class of 40 students (all take at least one subject), 28 study Physics and 22 study Chemistry.",
          objective = "Set intersection n(P∩C) so n(P) + n(C) − n(P∩C) = 40.",
          p1Label = "Intersection n(P∩C)", p1Unit = "students", p1Range = 0f..22f, p1Init = 4f,
          outLabel = "Total Union n(P∪C)", outUnit = "students", target = 40f, tol = 0.5f, gauge = 28f..50f,
          compute = { inter, _ -> 28f + 22f - inter.roundToInt() },
          hint = "28 + 22 − overlap = 40 → overlap = 50 − 40.",
          explanation = "10 students take both Physics and Chemistry (28 + 22 − 10 = 40).",
        ),
        slider(
          id = "sv_2",
          title = "Subset Power Counter",
          scenario = "Set A has n elements and produces exactly 32 distinct subsets (2ⁿ = 32).",
          objective = "Adjust cardinality n(A) to generate 32 subsets.",
          p1Label = "Cardinality n", p1Unit = "items", p1Range = 1f..8f, p1Init = 3f,
          outLabel = "Subsets 2ⁿ", outUnit = "", target = 32f, tol = 1f, gauge = 2f..256f,
          compute = { n, _ -> 2f.pow(n.roundToInt()) },
          hint = "2⁵ = 32.",
          explanation = "A 5-element set possesses 2⁵ = 32 subsets (and 31 proper subsets).",
        ),
        slider(
          id = "sv_3",
          title = "Complement Zone Balance",
          scenario = "Universal set n(U) = 50, n(A∪B) = 38. Tune neither-set count n(A∪B)'.",
          objective = "Set n(A∪B)' to balance the 50-student sample.",
          p1Label = "Neither Count", p1Unit = "students", p1Range = 0f..25f, p1Init = 6f,
          outLabel = "Sum Check", outUnit = "students", target = 50f, tol = 0.5f, gauge = 38f..63f,
          compute = { c, _ -> 38f + c.roundToInt() },
          hint = "50 − 38 = 12.",
          explanation = "38 inside A∪B plus 12 outside totals n(U) = 50.",
        ),
      )
    } else {
      listOf(
        slider(
          id = "ps_1",
          title = "Committee Combination Gate",
          scenario = "Choose r students from 5 candidates where order does NOT matter to hit 10 distinct combinations.",
          objective = "Select committee size r where ⁵Cᵣ = 10.",
          p1Label = "Select Size r", p1Unit = "", p1Range = 0f..5f, p1Init = 1f,
          outLabel = "Combinations ⁵Cᵣ", outUnit = "", target = 10f, tol = 0.5f, gauge = 1f..10f,
          compute = { r, _ ->
            when (r.roundToInt()) {
              0, 5 -> 1f
              1, 4 -> 5f
              else -> 10f
            }
          },
          hint = "⁵C₂ = (5×4)/2 = 10 (and ⁵C₃ = 10 by symmetry).",
          explanation = "Selecting 2 (or 3) out of 5 yields 10 combinations.",
        ),
        slider(
          id = "ps_2",
          title = "Constant Shift SD Immunity",
          scenario = "A dataset has Standard Deviation SD = 4.0. Every score receives a +10 bonus point shift.",
          objective = "Set the new Standard Deviation after adding +10 to every score.",
          p1Label = "New SD", p1Unit = "", p1Range = 0f..16f, p1Init = 14f,
          outLabel = "Selected SD", outUnit = "", target = 4.0f, tol = 0.3f, gauge = 0f..16f,
          compute = { sd, _ -> sd },
          hint = "Adding a constant shifts the Mean by +10 without changing spread/SD!",
          explanation = "Spread metrics (Range, Variance, SD) stay completely unchanged (4.0) under constant addition.",
        ),
        slider(
          id = "ps_3",
          title = "Independent Probability Multiplier",
          scenario = "Two fair dice are thrown. Event A is rolling a 6 (P = 1/6) and Event B is getting Heads on a coin (P = 0.5).",
          objective = "Set P(6 AND Heads) in percent (%).",
          p1Label = "Combined Probability", p1Unit = "%", p1Range = 2f..35f, p1Init = 20f,
          outLabel = "Selected Prob", outUnit = "%", target = 8.3f, tol = 0.8f, gauge = 0f..35f,
          compute = { p, _ -> p },
          hint = "(1/6) × (1/2) = 1/12 ≈ 8.33%.",
          explanation = "P(6 and H) = 1/12 ≈ 8.3%.",
        ),
      )
    }

  private fun buildChemistryDiscreteOrSlider(labType: PracticalLabType): List<GamifiedRound> =
    when (labType) {
      PracticalLabType.STOICHIOMETRY_MOLE_LAB ->
        listOf(
          slider(
            id = "cm_1",
            title = "Molarity Dilution Target",
            scenario = "Dilute 20 cm³ of 0.50 mol/dm³ NaOH up to final volume V₂ (cm³) to reach target concentration 0.10 mol/dm³.",
            objective = "Adjust final solution volume V₂ (cm³) using C₁V₁ = C₂V₂.",
            p1Label = "Final Volume V₂", p1Unit = "cm³", p1Range = 25f..150f, p1Init = 50f,
            outLabel = "Final Molarity", outUnit = "M", target = 0.10f, tol = 0.008f, gauge = 0.05f..0.40f,
            compute = { v2, _ -> (0.50f * 20f) / v2 },
            hint = "0.50 × 20 = 0.10 × V₂ → V₂ = 10 / 0.10.",
            explanation = "Diluting 20 cm³ to 100 cm³ brings molarity to 0.10 mol/dm³.",
          ),
          slider(
            id = "cm_2",
            title = "STP Gas Volume Reaction",
            scenario = "CaCO₃ decomposes: CaCO₃ → CaO + CO₂. Adjust mass of CaCO₃ (M = 100 g/mol) decomposed to liberate 5.60 dm³ CO₂ at STP (Vm = 22.4 dm³).",
            objective = "Dial mass m (g) of CaCO₃ required for 5.60 dm³ CO₂.",
            p1Label = "CaCO₃ Mass m", p1Unit = "g", p1Range = 5f..60f, p1Init = 40f,
            outLabel = "CO₂ Volume at STP", outUnit = "dm³", target = 5.60f, tol = 0.45f, gauge = 1f..14f,
            compute = { m, _ -> (m / 100f) * 22.4f },
            hint = "5.60 / 22.4 = 0.25 mol CaCO₃ → 0.25 × 100 g.",
            explanation = "25.0 g of CaCO₃ equals 0.25 mol, generating 0.25 × 22.4 = 5.60 dm³ CO₂.",
          ),
          slider(
            id = "cm_3",
            title = "Water Synthesis Limiting Match",
            scenario = "2H₂ + O₂ → 2H₂O. You have 4.0 mol H₂. Supply exact stoichiometric moles of O₂ so neither reactant stays in excess.",
            objective = "Tune O₂ moles for exact 2:1 stoichiometric consumption.",
            p1Label = "Oxygen Moles", p1Unit = "mol", p1Range = 0.5f..5.0f, p1Init = 3.5f,
            outLabel = "Excess O₂ Left", outUnit = "mol", target = 0.0f, tol = 0.15f, gauge = -2f..3f,
            compute = { o2, _ -> o2 - 2.0f },
            hint = "4 mol H₂ needs 4 / 2 = 2.0 mol O₂.",
            explanation = "4.0 mol H₂ reacts completely with 2.0 mol O₂ to produce 4.0 mol H₂O.",
          ),
        )

      PracticalLabType.GAS_LAW_PISTON ->
        listOf(
          slider(
            id = "gl_1",
            title = "Boyle's Piston Compression",
            scenario = "600 cm³ of N₂ sits at 1.0 atm (T constant). Compress volume until pressure rises cleanly to 3.0 atm.",
            objective = "Slide cylinder volume V₂ (cm³) to read P₂ = 3.0 atm.",
            p1Label = "Cylinder Volume V₂", p1Unit = "cm³", p1Range = 100f..600f, p1Init = 400f,
            outLabel = "Gas Pressure P₂", outUnit = "atm", target = 3.0f, tol = 0.18f, gauge = 1.0f..6.0f,
            compute = { v2, _ -> 600f / v2 },
            hint = "P₁V₁ = P₂V₂ → 1 × 600 = 3 × V₂.",
            explanation = "Compressing from 600 cm³ to 200 cm³ triples pressure to 3.0 atm.",
          ),
          slider(
            id = "gl_2",
            title = "Graham's Effusion Race",
            scenario = "Methane (CH₄, M = 16) is compared against SO₂ (M = 64). Find how many times faster CH₄ effuses than SO₂.",
            objective = "Set relative diffusion rate ratio r(CH₄)/r(SO₂).",
            p1Label = "Speed Ratio", p1Unit = "×", p1Range = 0.5f..4.0f, p1Init = 3.0f,
            outLabel = "Selected Ratio", outUnit = "×", target = 2.0f, tol = 0.15f, gauge = 0.5f..4.0f,
            compute = { r, _ -> r },
            hint = "r₁/r₂ = √(M₂/M₁) = √(64/16) = √4.",
            explanation = "Methane effuses 2.0× faster because it is 4× lighter than SO₂.",
          ),
          slider(
            id = "gl_3",
            title = "Ideal Gas Moles Counter",
            scenario = "At T = 300 K, P = 1 atm, 2 mol of ideal gas expands at fixed P = 1 atm. Dial moles n until V = nRT/P = 49.2 L (R = 0.082).",
            objective = "Tune gas moles n for 49.2 L.",
            p1Label = "Gas Amount n", p1Unit = "mol", p1Range = 0.5f..4.0f, p1Init = 1.0f,
            outLabel = "Gas Volume V", outUnit = "L", target = 49.2f, tol = 2.5f, gauge = 12f..99f,
            compute = { n, _ -> n * 0.082f * 300f },
            hint = "1 mol at 300 K, 1 atm occupies 24.6 L.",
            explanation = "2.0 mol occupies 2 × 24.6 = 49.2 L cleanly.",
          ),
        )

      PracticalLabType.TITRATION_PH ->
        listOf(
          slider(
            id = "tp_1",
            title = "Burette Endpoint Neutralizer",
            scenario = "25.0 cm³ of 0.10 M NaOH sits in the conical flask. Titrate 0.10 M H₂SO₄ (dibasic!) to the exact Methyl Orange equivalence endpoint.",
            objective = "Open burette tap to exact H₂SO₄ titre V_A (cm³).",
            p1Label = "Acid Titre V_A", p1Unit = "cm³", p1Range = 5.0f..35.0f, p1Init = 25.0f,
            outLabel = "Excess Base (+) / Acid (−)", outUnit = "mmol H⁺/OH⁻", target = 0.0f, tol = 0.15f, gauge = -3f..3f,
            compute = { va, _ -> 25f * 0.10f - 2f * va * 0.10f },
            hint = "2NaOH + H₂SO₄ → Na₂SO₄ + 2H₂O gives n_a/n_b = 1/2 → V_A = 12.5 cm³.",
            explanation = "Because H₂SO₄ supplies 2 H⁺ per molecule, 12.5 cm³ neutralizes 25.0 cm³ of equimolar NaOH.",
          ),
          slider(
            id = "tp_2",
            title = "Strong Acid pH Log Check",
            scenario = "Calculate and dial the exact pH of a 0.001 mol/dm³ HNO₃ solution.",
            objective = "Set pH meter to match [H⁺] = 10⁻³ M.",
            p1Label = "pH Scale", p1Unit = "pH", p1Range = 0.0f..7.0f, p1Init = 1.0f,
            outLabel = "Selected pH", outUnit = "pH", target = 3.0f, tol = 0.25f, gauge = 0f..7f,
            compute = { p, _ -> p },
            hint = "pH = −log₁₀(10⁻³) = 3.0.",
            explanation = "Complete ionization of 10⁻³ M HNO₃ gives pH 3.0.",
          ),
          console(
            id = "tp_3",
            title = "Indicator Endpoint Match",
            scenario = "Titrating weak CH₃COOH against strong NaOH gives an alkaline equivalence pH (~8.7).",
            objective = "Pick the sharp indicator and its acid→alkali colour change.",
            cALabel = "Indicator", cAOptions = listOf("Methyl Orange", "Phenolphthalein"),
            cBLabel = "Endpoint Shift", cBOptions = listOf("Red → Yellow", "Colourless → Pink"),
            cAIdx = 1, cBIdx = 1,
            preview = { a, b ->
              val ind = if (a == 0) "Methyl Orange" else "Phenolphthalein"
              val shift = if (b == 0) "Red → Yellow (pH 3.1–4.4)" else "Colourless → Pink (pH 8.3–10.0)"
              "Selected Setup: $ind switching $shift"
            },
            hint = "Strong base + weak acid equivalence sits above pH 8.0, matching Phenolphthalein.",
            explanation = "Phenolphthalein changes sharply from colourless to pink around pH 8.3–10.0.",
          ),
        )

      PracticalLabType.REDOX_ELECTROLYSIS_LAB ->
        listOf(
          slider(
            id = "re_1",
            title = "Faraday Copper Plater",
            scenario = "Plate Cu²⁺ (M = 64 g/mol, z = 2, F = 96500 C/mol) at I = 2.0 A. Adjust plating time t (s) until exact deposit reaches 0.64 g.",
            objective = "Run electrolysis current 2.0 A for time t (s) to deposit 0.64 g Cu.",
            p1Label = "Duration t", p1Unit = "s", p1Range = 300f..1500f, p1Init = 500f,
            outLabel = "Deposited Copper", outUnit = "g", target = 0.64f, tol = 0.04f, gauge = 0f..1.2f,
            compute = { t, _ -> (2.0f * t * 64f) / (2f * 96500f) },
            hint = "0.64 g is 0.01 mol Cu (0.02 F = 1930 C). At 2.0 A, t = 1930 / 2.",
            explanation = "Passing 2.0 A for 965 s delivers 1930 C (0.02 F), plating 0.01 mol = 0.64 g Cu.",
          ),
          console(
            id = "re_2",
            title = "Brine Anode Preferential Discharge",
            scenario = "Concentrated NaCl(aq) is electrolyzed using inert graphite electrodes.",
            objective = "Select the ion preferentially discharged at the Anode and the gas evolved.",
            cALabel = "Anode Reaction", cAOptions = listOf("4OH⁻ → 2H₂O + O₂ + 4e⁻", "2Cl⁻ → Cl₂ + 2e⁻"),
            cBLabel = "Cathode Gas", cBOptions = listOf("H₂ Gas", "Na Metal"),
            cAIdx = 1, cBIdx = 0,
            preview = { a, b ->
              val anode = if (a == 0) "O₂ evolved" else "Greenish-yellow Cl₂ evolved"
              val cath = if (b == 0) "H₂ gas evolved (leaving NaOH)" else "Sodium deposited"
              "Electrolytic Cell Output: Anode → $anode | Cathode → $cath"
            },
            hint = "High [Cl⁻] overrides OH⁻ at the anode, while H⁺ beats Na⁺ at the cathode.",
            explanation = "Concentrated brine yields Cl₂ at the anode and H₂ at the cathode, leaving NaOH(aq).",
          ),
          slider(
            id = "re_3",
            title = "Oxidation Number Auditor",
            scenario = "Determine the oxidation state of Chromium in dichromate ion Cr₂O₇²⁻.",
            objective = "Set the oxidation number of Cr (+2 to +7).",
            p1Label = "Cr Oxidation State", p1Unit = "+", p1Range = 1f..7f, p1Init = 3f,
            outLabel = "Ion Net Charge", outUnit = "", target = -2f, tol = 0.4f, gauge = -12f..2f,
            compute = { cr, _ -> 2f * cr.roundToInt() - 14f },
            hint = "2x + 7(−2) = −2 → 2x = +12.",
            explanation = "Each Cr sits at +6 (2(+6) − 14 = −2).",
          ),
        )

      PracticalLabType.QUALITATIVE_ANALYSIS_LAB ->
        listOf(
          console(
            id = "qa_1",
            title = "Copper(II) vs Zinc(II) Bench",
            scenario = "You are handed an unknown blue-green salt solution containing Cu²⁺.",
            objective = "Add aqueous ammonia NH₃(aq) in excess to observe the confirmatory complex colour.",
            cALabel = "Reagent Added", cAOptions = listOf("NaOH(aq) excess", "NH₃(aq) excess"),
            cBLabel = "Observed Result", cBOptions = listOf("Clear colourless soluble complex", "Deep royal-blue solution"),
            cAIdx = 1, cBIdx = 1,
            preview = { a, b ->
              val r = if (a == 0) "NaOH(aq) excess" else "NH₃(aq) excess"
              val res = if (b == 0) "Colourless zincate complex" else "Deep royal-blue [Cu(NH₃)₄]²⁺"
              "Test Tube Log: Added $r → $res"
            },
            hint = "Cu(OH)₂ stays insoluble in excess NaOH, but dissolves in excess NH₃ to deep blue.",
            explanation = "Excess NH₃(aq) forms tetraamminecopper(II), giving an unmistakable deep blue solution.",
          ),
          console(
            id = "qa_2",
            title = "Zinc vs Aluminium Differentiator",
            scenario = "Both Zn²⁺ and Al³⁺ give white precipitates soluble in excess NaOH. Differentiate Zn²⁺ cleanly.",
            objective = "Add excess NH₃(aq) and identify which cation dissolves completely.",
            cALabel = "Reagent", cAOptions = listOf("Excess NaOH(aq)", "Excess NH₃(aq)"),
            cBLabel = "Soluble Cation", cBOptions = listOf("Zn²⁺ dissolves; Al³⁺ stays insoluble", "Al³⁺ dissolves; Zn²⁺ stays insoluble"),
            cAIdx = 1, cBIdx = 0,
            preview = { a, b ->
              val r = if (a == 0) "Excess NaOH" else "Excess NH₃"
              val out = if (b == 0) "Zn²⁺ clears to [Zn(NH₃)₄]²⁺" else "Al³⁺ clears"
              "Analytical Result ($r): $out"
            },
            hint = "Both Al³⁺ and Pb²⁺ stay white insoluble in excess NH₃(aq), whereas Zn²⁺ dissolves.",
            explanation = "Zn²⁺ uniquely forms soluble [Zn(NH₃)₄]²⁺ in excess aqueous ammonia.",
          ),
          console(
            id = "qa_3",
            title = "Sulfate vs Carbonate Confirmation",
            scenario = "Distinguish SO₄²⁻ from CO₃²⁻ using Barium Chloride followed by dilute HCl.",
            objective = "Select the anion whose white Ba²⁺ precipitate stays insoluble in excess dilute HCl.",
            cALabel = "Test Reagent Pair", cAOptions = listOf("BaCl₂(aq) + dilute HCl", "AgNO₃(aq) + HNO₃"),
            cBLabel = "Acid-Insoluble White ppt", cBOptions = listOf("BaCO₃ (Carbonate)", "BaSO₄ (Sulfate)"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b ->
              val reg = if (a == 0) "BaCl₂ + dil. HCl" else "AgNO₃ + dil. HNO₃"
              val ppt = if (b == 0) "BaCO₃ effervesces & dissolves" else "BaSO₄ remains white insoluble"
              "Anion Bench ($reg): $ppt"
            },
            hint = "BaCO₃ dissolves with CO₂ fizzing in HCl; BaSO₄ does not.",
            explanation = "Insolubility of white BaSO₄ in dilute HCl confirms sulfate SO₄²⁻.",
          ),
        )

      else ->
        listOf(
          console(
            id = "${labType.name}_1",
            title = "Core Mechanism Diagnosis",
            scenario = "Calibrate the key governing condition inside ${labType.title}.",
            objective = "Pair the primary governing variable with its true chemical outcome.",
            cALabel = "Apparatus Mode",
            cAOptions = when (labType) {
              PracticalLabType.SEPARATION_ATOMIC_LAB -> listOf("Simple Distillation", "Fractional Distillation")
              PracticalLabType.BONDING_PERIODIC_LAB -> listOf("High ΔEN (>1.7)", "Zero ΔEN (Pure Non-Polar)")
              PracticalLabType.ENERGETICS_KINETICS_LAB -> listOf("Catalyst Added", "Higher Temperature Alone")
              PracticalLabType.INORGANIC_GASES_METALS_LAB -> listOf("Ammonia (NH₃) Test", "Sulfur Dioxide (SO₂) Test")
              else -> listOf("Bromine Water Test", "Saturated Alkane Control")
            },
            cBLabel = "Observed Verification",
            cBOptions = when (labType) {
              PracticalLabType.SEPARATION_ATOMIC_LAB -> listOf("Separates miscible liquids with close b.p.", "Filter funnel only")
              PracticalLabType.BONDING_PERIODIC_LAB -> listOf("Ionic electron transfer lattice", "Equal covalent sharing")
              PracticalLabType.ENERGETICS_KINETICS_LAB -> listOf("Lowers activation energy Eₐ cleanly", "Shifts ΔH enthalpy change")
              PracticalLabType.INORGANIC_GASES_METALS_LAB -> listOf("Forms dense white NH₄Cl fumes with HCl", "Turns limewater milky alone")
              else -> listOf("Decolourises reddish-brown Br₂ rapidly", "No reaction in the dark")
            },
            cAIdx = when (labType) {
              PracticalLabType.SEPARATION_ATOMIC_LAB -> 1
              else -> 0
            },
            cBIdx = 0,
            preview = { a, b -> "Lab Bench State: Mode #${a + 1} paired with Outcome #${b + 1}" },
            hint = "Verify what specifically isolates the chemical identity or lowers energy barrier without altering equilibrium ΔH.",
            explanation = "Matching exact reagent/apparatus choice to its signature chemical outcome secures WAEC practical marks.",
          ),
          slider(
            id = "${labType.name}_2",
            title = "Quantitative Stability Check",
            scenario = "Octet shell / Haber yield optimization target: hit optimal parameter index 8.0 (full octet or target yield point).",
            objective = "Tune stability target control cleanly to 8.0.",
            p1Label = "Stability Index", p1Unit = "units", p1Range = 1f..12f, p1Init = 4f,
            outLabel = "Chamber Score", outUnit = "pts", target = 8.0f, tol = 0.4f, gauge = 1f..12f,
            compute = { v, _ -> v },
            hint = "Valence octet stability completes at 8 electrons.",
            explanation = "8 valence electrons achieve noble-gas electronic stability.",
          ),
          console(
            id = "${labType.name}_3",
            title = "Safety & Lab Protocol Check",
            scenario = "Ensure WAEC practical compliance when finalizing observations.",
            objective = "Select accurate chemical inference wording over vague descriptions.",
            cALabel = "Recording Rule", cAOptions = listOf("State colour + precipitate solubility", "Write 'something formed'"),
            cBLabel = "Confirmatory Test", cBOptions = listOf("Use lighted/glowing splint or specific paper", "Guess by smell only"),
            cAIdx = 0, cBIdx = 0,
            preview = { _, _ -> "Practical Notebook Audit Active" },
            hint = "Examiners award marks only for explicit colour, state, and reagent solubility.",
            explanation = "Precise visual observations paired with confirmatory tests avoid WAEC deductions.",
          ),
        )
    }

  private fun buildBiologyChallenges(labType: PracticalLabType): List<GamifiedRound> =
    when (labType) {
      PracticalLabType.CELL_OSMOSIS_LAB ->
        listOf(
          slider(
            id = "co_1",
            title = "Osmosis Isotonic Equilibrium",
            scenario = "Potato strips have internal cell sap concentration 0.30 M sucrose. Tune bath molarity so net mass change Δm hits 0.0%.",
            objective = "Set external sucrose molarity (M) to isotonic zero net water flow.",
            p1Label = "Bath Concentration", p1Unit = "M", p1Range = 0.00f..0.70f, p1Init = 0.05f,
            outLabel = "Net Mass Change", outUnit = "%", target = 0.0f, tol = 2.0f, gauge = -20f..20f,
            compute = { c, _ -> (0.30f - c) * 40f },
            hint = "Zero net osmosis happens when bath molarity matches cell sap (0.30 M).",
            explanation = "At 0.30 M, water potentials inside and outside match—no plasmolysis or turgor swelling occurs.",
          ),
          slider(
            id = "co_2",
            title = "Microscope Actual Size Solver",
            scenario = "An onion cell image measures 45 mm under ×1000 magnification. Compute actual cell diameter in micrometres (μm).",
            objective = "Adjust ruler slider to actual size (μm).",
            p1Label = "Actual Diameter", p1Unit = "μm", p1Range = 10f..100f, p1Init = 20f,
            outLabel = "Selected Size", outUnit = "μm", target = 45f, tol = 2.5f, gauge = 10f..100f,
            compute = { u, _ -> u },
            hint = "Actual = (45 × 1000 μm) / 1000 magnification.",
            explanation = "45 mm = 45,000 μm; divided by ×1000 equals 45.0 μm.",
          ),
          console(
            id = "co_3",
            title = "Plant vs Animal Hypotonic Fate",
            scenario = "Red blood cells vs onion cells are dropped into pure distilled water.",
            objective = "Match each cell type's true osmotic outcome in hypotonic water.",
            cALabel = "Red Blood Cell", cAOptions = listOf("Haemolysis (Bursts)", "Plasmolysis (Shrinks)"),
            cBLabel = "Plant Cell", cBOptions = listOf("Bursts completely", "Becomes Turgid (Cell wall resists bursting)"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b ->
              val rbc = if (a == 0) "RBC swells & bursts (no cell wall)" else "RBC shrinks"
              val plt = if (b == 0) "Plant bursts" else "Plant stays rigid & turgid"
              "Microscope Chamber: $rbc | $plt"
            },
            hint = "Cellulose cell walls protect plant cells from bursting.",
            explanation = "Animal RBCs lack a cell wall and haemolyse in pure water, while plant cells become turgid.",
          ),
        )

      PracticalLabType.BIOENERGETICS_ENZYME_LAB ->
        listOf(
          slider(
            id = "be_1",
            title = "Human Amylase Optimum Lock",
            scenario = "Test starch digestion rate across temperature (°C) to lock peak enzyme activity without denaturing active sites.",
            objective = "Adjust incubation bath temperature (°C) to achieve peak Amylase velocity (≥ 95%).",
            p1Label = "Bath Temperature", p1Unit = "°C", p1Range = 5f..65f, p1Init = 15f,
            outLabel = "Reaction Rate", outUnit = "% max", target = 100f, tol = 6f, gauge = 0f..100f,
            compute = { t, _ ->
              if (t <= 37f) (100f * kotlin.math.exp(-((t - 37f) * (t - 37f)) / 240f))
              else (100f * kotlin.math.exp(-((t - 37f) * (t - 37f)) / 65f))
            },
            hint = "Mammalian digestive enzymes peak around body temperature (37°C).",
            explanation = "At 37°C collisions peak before hydrogen bonds fracture above 45°C.",
          ),
          slider(
            id = "be_2",
            title = "Gastric Pepsin pH Sweet-Spot",
            scenario = "Stomach protease Pepsin works best in strongly acidic gastric HCl.",
            objective = "Dial chamber pH to peak Pepsin proteolysis.",
            p1Label = "Chamber pH", p1Unit = "pH", p1Range = 1.0f..9.0f, p1Init = 7.0f,
            outLabel = "Pepsin Efficiency", outUnit = "%", target = 100f, tol = 8f, gauge = 0f..100f,
            compute = { ph, _ -> 100f * kotlin.math.exp(-((ph - 2.0f) * (ph - 2.0f)) / 2.2f) },
            hint = "Pepsin operates optimally near pH 2.0 in the stomach.",
            explanation = "Optimal activity at pH 2.0 vs Alkaline Trypsin (~pH 8.5).",
          ),
          console(
            id = "be_3",
            title = "Limiting Factor Identifier",
            scenario = "A green pondweed receives bright light and excess water at 10°C; increasing light no longer speeds photosynthesis.",
            objective = "Identify the limiting factor and the gas bubble collected.",
            cALabel = "Limiting Factor", cAOptions = listOf("Temperature (enzyme kinetics)", "Nitrogen supply"),
            cBLabel = "Evolved Gas", cBOptions = listOf("CO₂", "Oxygen (O₂)"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b ->
              val lf = if (a == 0) "Cold 10°C slows Calvin enzymes" else "Nitrogen"
              val gas = if (b == 0) "CO₂" else "O₂ relights glowing splint"
              "Pondweed Bench: $lf → Evolves $gas"
            },
            hint = "Plateau under bright light at low temperature signals enzyme thermal limitation; photosynthesis liberates O₂.",
            explanation = "Temperature limits enzymatic dark-stage rate at 10°C while photolysis releases O₂.",
          ),
        )

      PracticalLabType.ECOLOGY_ENERGY_PYRAMID ->
        listOf(
          slider(
            id = "ep_1",
            title = "Lindeman 10% Trophic Transfer",
            scenario = "Savannah grass stores 20,000 kJ. Apply realistic 10% ecological transfer up to Secondary Consumers (Level 3).",
            objective = "Calculate and dial available Secondary Consumer energy (kJ).",
            p1Label = "Level 3 Energy", p1Unit = "kJ", p1Range = 50f..2500f, p1Init = 1500f,
            outLabel = "Selected Energy", outUnit = "kJ", target = 200f, tol = 30f, gauge = 50f..2500f,
            compute = { e, _ -> e },
            hint = "20,000 → (10%) 2,000 (Herbivores) → (10%) Secondary Consumers.",
            explanation = "Two 10% transfers reduce 20,000 kJ → 2,000 kJ → 200 kJ.",
          ),
          console(
            id = "ep_2",
            title = "Nitrogen Bacteria Specialist",
            scenario = "Track two critical soil transformations in Nigerian cowpea root nodules and aerobic soil.",
            objective = "Match Nitrogen Fixation vs Nitrification bacteria accurately.",
            cALabel = "Root Nodule Fixer (N₂ → NH₄⁺)", cAOptions = listOf("Rhizobium", "Pseudomonas"),
            cBLabel = "Nitrite → Nitrate Oxidizer", cBOptions = listOf("Nitrobacter", "Nitrosomonas"),
            cAIdx = 0, cBIdx = 0,
            preview = { a, b ->
              val f = if (a == 0) "Rhizobium (symbiotic legume nodule)" else "Pseudomonas (denitrifying)"
              val n = if (b == 0) "Nitrobacter (NO₂⁻ → NO₃⁻)" else "Nitrosomonas (NH₄⁺ → NO₂⁻)"
              "Soil Microbiome Trace: $f + $n"
            },
            hint = "Rhizobium fixes N₂ in legumes; Nitrobacter converts nitrite to absorbable nitrate.",
            explanation = "Rhizobium enriches legumes directly, while Nitrobacter completes aerobic nitrification.",
          ),
          slider(
            id = "ep_3",
            title = "Capture-Recapture Population Estimate",
            scenario = "Tag 40 tilapia in Lake Kainji (M). Next week catch 50 fish (C), finding 10 tagged (R). Estimate total population P = M×C/R.",
            objective = "Dial total estimated lake tilapia population P.",
            p1Label = "Estimated Population", p1Unit = "fish", p1Range = 50f..500f, p1Init = 100f,
            outLabel = "Lincoln Estimate", outUnit = "fish", target = 200f, tol = 12f, gauge = 50f..500f,
            compute = { p, _ -> p },
            hint = "P = (40 × 50) / 10.",
            explanation = "(40 × 50) / 10 = 200 tilapia.",
          ),
        )

      PracticalLabType.MENDELIAN_GENETICS ->
        listOf(
          slider(
            id = "mg_1",
            title = "Sickle-Cell Risk Calculator",
            scenario = "Both parents carry heterozygous genotype AS × AS. Calculate percentage probability of an SS offspring per pregnancy.",
            objective = "Set SS genetic probability (%).",
            p1Label = "Probability (%)", p1Unit = "%", p1Range = 0f..100f, p1Init = 50f,
            outLabel = "Punnett Outcome", outUnit = "%", target = 25f, tol = 2f, gauge = 0f..100f,
            compute = { p, _ -> p },
            hint = "Punnett square AS × AS gives 1 AA : 2 AS : 1 SS.",
            explanation = "1 out of 4 equal genotypes is SS (25%).",
          ),
          console(
            id = "mg_2",
            title = "Blood Group Co-Dominance Cross",
            scenario = "A father has Blood Group AB (IᴬIᴮ) and mother is Group O (ii).",
            objective = "Identify the possible phenotypes among their children.",
            cALabel = "Possible Offspring", cAOptions = listOf("Group A or Group B only", "Group AB or Group O"),
            cBLabel = "Dominance Mode", cBOptions = listOf("Iᴬ & Iᴮ co-dominant over recessive i", "O dominant over A"),
            cAIdx = 0, cBIdx = 0,
            preview = { a, b ->
              val kids = if (a == 0) "50% Iᴬi (A), 50% Iᴮi (B)" else "AB or O"
              val dom = if (b == 0) "Multiple alleles + Co-dominance" else "Recessive"
              "Genetic Forecast: $kids ($dom)"
            },
            hint = "Group AB parent cannot donate an 'i' allele, so neither Group O nor Group AB appears.",
            explanation = "Every child inherits either Iᴬ or Iᴮ from the AB parent and i from Group O → Group A or B.",
          ),
          slider(
            id = "mg_3",
            title = "Dihybrid Ratio Segment",
            scenario = "In Mendel's F₂ dihybrid cross (RrYy × RrYy), how many out of 16 offspring display BOTH dominant traits?",
            objective = "Dial dominant-dominant proportion out of 16.",
            p1Label = "Proportion (/16)", p1Unit = "/16", p1Range = 1f..16f, p1Init = 4f,
            outLabel = "Selected Count", outUnit = "/16", target = 9f, tol = 0.5f, gauge = 1f..16f,
            compute = { c, _ -> c.roundToInt().toFloat() },
            hint = "Classic Mendelian dihybrid ratio: 9 : 3 : 3 : 1.",
            explanation = "9/16 express both dominant traits.",
          ),
        )

      else ->
        listOf(
          console(
            id = "${labType.name}_1",
            title = "Physiological Flow & Diagnostics",
            scenario = "Verify core biological mechanism inside ${labType.title}.",
            objective = "Pair the organ/pathway control with its true physiological outcome.",
            cALabel = "Control / Test",
            cAOptions = when (labType) {
              PracticalLabType.NUTRITION_TRANSPORT_LAB -> listOf("Biuret Reagent (Protein)", "Benedict's Heat (Reducing Sugar)")
              PracticalLabType.RESPIRATION_KIDNEY_LAB -> listOf("High ADH Release", "Diaphragm Relaxes (Exhalation)")
              PracticalLabType.NERVOUS_ENDOCRINE_LAB -> listOf("Myopia Correction", "Hypermetropia Correction")
              PracticalLabType.REPRODUCTION_FLOWER_LAB -> listOf("LH Surge (Day 14)", "Progesterone Drop (Day 28)")
              else -> listOf("Industrial Soot Selection", "Continuous Height Variation")
            },
            cBLabel = "Verified Outcome",
            cBOptions = when (labType) {
              PracticalLabType.NUTRITION_TRANSPORT_LAB -> listOf("Purple / Violet ring without heating", "Blue stays unchanged")
              PracticalLabType.RESPIRATION_KIDNEY_LAB -> listOf("Permits max water reabsorption → concentrated urine", "Thorax expands")
              PracticalLabType.NERVOUS_ENDOCRINE_LAB -> listOf("Concave diverging lens focuses onto retina", "Convex converging lens only")
              PracticalLabType.REPRODUCTION_FLOWER_LAB -> listOf("Triggers ovulation from Graafian follicle", "Builds endometrium")
              else -> listOf("Dark melanic morph survives predation best", "Strictly 2 discrete categories")
            },
            cAIdx = 0, cBIdx = 0,
            preview = { a, b -> "Biological Trace: Input #${a + 1} → Response #${b + 1}" },
            hint = "Check the exact reagent/hormone/lens action studied in the syllabus lesson.",
            explanation = "Linking correct physiological stimulus directly to effector outcome masters WAEC/JAMB Section B questions.",
          ),
          slider(
            id = "${labType.name}_2",
            title = "Homeostasis Set-Point Lock",
            scenario = "Mammalian core set-point keeps blood temperature tightly regulated.",
            objective = "Tune thermoregulatory hypothalamus target (°C) to human normothermia.",
            p1Label = "Core Temperature", p1Unit = "°C", p1Range = 33.0f..41.0f, p1Init = 39.5f,
            outLabel = "Homeostatic Drift", outUnit = "°C", target = 0.0f, tol = 0.3f, gauge = -4f..4f,
            compute = { t, _ -> t - 37.0f },
            hint = "Normal human core body temperature is 37.0°C.",
            explanation = "Hypothalamic negative feedback stabilizes internal body temperature near 37.0°C.",
          ),
          console(
            id = "${labType.name}_3",
            title = "WAEC Specimen Diagnostic",
            scenario = "Confirm accurate biological structure-function alignment.",
            objective = "Select exact tissue transport pairing.",
            cALabel = "Plant Vascular Vessel", cAOptions = listOf("Xylem (dead lignified)", "Phloem (living sieve tubes)"),
            cBLabel = "Primary Cargo", cBOptions = listOf("Water & Mineral Salts", "Sucrose translocation"),
            cAIdx = 0, cBIdx = 0,
            preview = { _, _ -> "Vascular Audit Complete" },
            hint = "Xylem conducts water upwards passively; Phloem translocates sucrose bidirectionally.",
            explanation = "Dead lignified xylem supports transpiration pull of water and ions.",
          ),
        )
    }

  private fun buildEnglishChallenges(labType: PracticalLabType): List<GamifiedRound> =
    when (labType) {
      PracticalLabType.ORAL_ENGLISH_STRESS ->
        listOf(
          console(
            id = "eng_o_1",
            title = "Suffix Stress Locator",
            scenario = "Word: 'e-co-NO-mic' vs 'e-LEC-tri-cal'. Apply the -ic / -ical penultimate rule.",
            objective = "Select the stressed syllable index on 'pho-to-GRAPH-ic' and its part of speech shift.",
            cALabel = "Stressed Syllable ('pho-to-GRAPH-ic')", cAOptions = listOf("1st (PHO-to-graph-ic)", "3rd (pho-to-GRAPH-ic)"),
            cBLabel = "Noun vs Verb Stress ('CON-duct' / 'con-DUCT')", cBOptions = listOf("Noun = 1st | Verb = 2nd", "Noun = 2nd | Verb = 1st"),
            cAIdx = 1, cBIdx = 0,
            preview = { a, b ->
              val s1 = if (a == 0) "PHO-to-graph-ic" else "pho-to-GRAPH-ic"
              val nv = if (b == 0) "CON-duct (n.) / con-DUCT (v.)" else "Inverted"
              "Stress Studio Audio Pattern: $s1 | $nv"
            },
            hint = "Words ending in -ic pull primary stress directly onto the syllable before -ic.",
            explanation = "-ic forces penultimate stress (pho-to-GRAPH-ic), and two-syllable noun/verb pairs stress 1st for noun, 2nd for verb.",
          ),
          console(
            id = "eng_o_2",
            title = "JAMB Emphatic Contrast",
            scenario = "Target Sentence: 'ADA bought the BLUE dress yesterday.'",
            objective = "Select which interrogative question triggers contrast specifically on BLUE.",
            cALabel = "Targeted Focus", cAOptions = listOf("BLUE (adjective contrast)", "ADA (subject contrast)"),
            cBLabel = "Matching Question", cBOptions = listOf("Did Ada buy the RED dress yesterday?", "Did Chinedu buy the blue dress?"),
            cAIdx = 0, cBIdx = 0,
            preview = { a, b -> "Stress Focus = ${if (a == 0) "BLUE" else "ADA"} contrasted against Q${b + 1}" },
            hint = "The correct JAMB question contradicts ONLY the single capitalized word.",
            explanation = "'Did Ada buy the RED dress yesterday?' leaves every detail intact except the stressed adjective BLUE.",
          ),
          console(
            id = "eng_o_3",
            title = "-ation Penultimate Rule",
            scenario = "Test stress placement on 'e-du-CA-tion' and 're-con-ci-li-A-tion'.",
            objective = "Lock the syllable carrying primary stress in -tion / -sion nouns.",
            cALabel = "Stress Rule", cAOptions = listOf("2nd-to-last syllable (right before -tion)", "Always 1st syllable"),
            cBLabel = "Correct Output", cBOptions = listOf("e-du-CA-tion", "E-du-ca-tion"),
            cAIdx = 0, cBIdx = 0,
            preview = { _, b -> if (b == 0) "Primary Peak: -CA-tion" else "Primary Peak: E-" },
            hint = "English suffixes -tion, -sion, -ic always anchor stress immediately before the suffix.",
            explanation = "-tion places primary stress directly on the preceding syllable: e-du-CA-tion.",
          ),
        )

      else ->
        listOf(
          console(
            id = "${labType.name}_1",
            title = "Grammar Concord & Modifier Lock",
            scenario = "Sentence: 'The Principal, as well as the prefects, ___ present today.'",
            objective = "Select the true head noun and agreeing finite verb.",
            cALabel = "True Grammatical Subject", cAOptions = listOf("The Principal (singular before parenthetical)", "The prefects (plural)"),
            cBLabel = "Agreeing Finite Verb", cBOptions = listOf("is / was", "are / were"),
            cAIdx = 0, cBIdx = 0,
            preview = { a, b -> "Parsed Clause: Subject #${a + 1} + Verb '${if (b == 0) "is" else "are"}'" },
            hint = "Phrases introduced by 'as well as', 'together with', or 'along with' are parenthetical.",
            explanation = "'The Principal' governs singular concord ('is / was'), ignoring words inside commas.",
          ),
          console(
            id = "${labType.name}_2",
            title = "Polarity-Flip Question Tag",
            scenario = "Sentence: 'Scarcely had we arrived before the rain started, ___?'",
            objective = "Select statement polarity and matching WAEC tag.",
            cALabel = "Detected Statement Polarity", cAOptions = listOf("Negative (semi-negative 'scarcely')", "Positive"),
            cBLabel = "Matching Question Tag", cBOptions = listOf("had we?", "hadn't we?"),
            cAIdx = 0, cBIdx = 0,
            preview = { _, b -> "Tagged Output: '...started, ${if (b == 0) "had we?" else "hadn't we?"}'" },
            hint = "Semi-negatives (scarcely, seldom, rarely, hardly) count as negative and take a positive tag.",
            explanation = "Because 'scarcely' makes the clause negative, WAEC requires the positive inversion tag 'had we?'.",
          ),
          console(
            id = "${labType.name}_3",
            title = "WAEC Formal Letter & Lexis Gate",
            scenario = "Verify WAEC formal letter format and idiomatic register.",
            objective = "Pair correct formal salutation/closing with exact preposition usage.",
            cALabel = "Formal Closing", cAOptions = listOf("Yours faithfully + Signature + Full Name", "Yours sincerely without name"),
            cBLabel = "Collocation Check", cBOptions = listOf("Congratulate her ON her success", "Congratulate her FOR her success"),
            cAIdx = 0, cBIdx = 0,
            preview = { _, b -> "Verified Register: ${if (b == 0) "congratulate ON" else "congratulate FOR"}" },
            hint = "WAEC requires 'Yours faithfully' for official Dear Sir/Madam letters and 'congratulate ... on'.",
            explanation = "Use 'Yours faithfully' (no apostrophe in Yours) + signature + full name, and 'congratulate on'.",
          ),
        )
    }

  private fun buildSocialScienceChallenges(labType: PracticalLabType): List<GamifiedRound> =
    when (labType) {
      PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB ->
        listOf(
          slider(
            id = "ec_1",
            title = "PED Revenue Sensitivity",
            scenario = "A sachet water price jumps by 20% while quantity demanded drops by 10%.",
            objective = "Calculate and dial Price Elasticity of Demand |PED|.",
            p1Label = "Magnitude |PED|", p1Unit = "", p1Range = 0.1f..2.5f, p1Init = 1.5f,
            outLabel = "Selected |PED|", outUnit = "", target = 0.50f, tol = 0.08f, gauge = 0.1f..2.5f,
            compute = { p, _ -> p },
            hint = "|PED| = 10% / 20% = 0.50 (Price Inelastic → Total Revenue rises).",
            explanation = "|PED| = 0.50 (< 1) means demand is inelastic, so price hikes raise Total Revenue.",
          ),
          slider(
            id = "ec_2",
            title = "Market Clearing Equilibrium",
            scenario = "Demand Qd = 100 − 2P and Supply Qs = 20 + 3P. Tune Price P (₦) to clear the market with zero surplus or shortage.",
            objective = "Dial market price P (₦) so Qd − Qs = 0.",
            p1Label = "Market Price P", p1Unit = "₦", p1Range = 4f..32f, p1Init = 8f,
            outLabel = "Excess Demand (Qd − Qs)", outUnit = "units", target = 0f, tol = 2.5f, gauge = -60f..60f,
            compute = { p, _ -> (100f - 2f * p) - (20f + 3f * p) },
            hint = "100 − 2P = 20 + 3P → 5P = 80 → P = ₦16.",
            explanation = "At P = ₦16, both Qd and Qs clear equally at 68 units.",
          ),
          slider(
            id = "ec_3",
            title = "Average Cost Optimizer",
            scenario = "Total Cost TC = ₦400 fixed + ₦30 × Q variable at output Q = 20 units.",
            objective = "Set Average Cost AC = TC / Q (₦/unit).",
            p1Label = "Average Cost AC", p1Unit = "₦", p1Range = 20f..80f, p1Init = 30f,
            outLabel = "Selected AC", outUnit = "₦", target = 50f, tol = 2.5f, gauge = 20f..80f,
            compute = { ac, _ -> ac },
            hint = "TC = 400 + 600 = ₦1,000 → AC = 1000 / 20.",
            explanation = "AC = AFC (20) + AVC (30) = ₦50 per unit.",
          ),
        )

      PracticalLabType.ECONOMICS_MACRO_POLICY_LAB ->
        listOf(
          slider(
            id = "emac_1",
            title = "CBN Credit Multiplier",
            scenario = "CBN sets Cash Reserve Ratio (CRR) r = 20% (0.20). Commercial banks receive ₦100M new deposits.",
            objective = "Dial total credit expansion capacity (₦ Millions) = 100 / r.",
            p1Label = "Credit Expansion", p1Unit = "₦M", p1Range = 100f..1000f, p1Init = 200f,
            outLabel = "Banking Capacity", outUnit = "₦M", target = 500f, tol = 25f, gauge = 100f..1000f,
            compute = { c, _ -> c },
            hint = "Multiplier k = 1 / 0.20 = 5 → 5 × ₦100M.",
            explanation = "A 20% reserve ratio produces a 5× multiplier (₦500M total deposits).",
          ),
          slider(
            id = "emac_2",
            title = "Keynesian Expenditure Multiplier",
            scenario = "With MPC = 0.80, government injects ₦50B new road infrastructure investment.",
            objective = "Dial resulting total National Income expansion ΔY (₦ Billion).",
            p1Label = "Income Gain ΔY", p1Unit = "₦B", p1Range = 50f..400f, p1Init = 100f,
            outLabel = "Selected ΔY", outUnit = "₦B", target = 250f, tol = 15f, gauge = 50f..400f,
            compute = { y, _ -> y },
            hint = "k = 1 / (1 − 0.80) = 5 → ΔY = 5 × 50.",
            explanation = "Multiplier 1/(1 − 0.80) = 5 amplifies ₦50B into ₦250B GDP expansion.",
          ),
          console(
            id = "emac_3",
            title = "Anti-Inflation CBN Policy Mix",
            scenario = "Demand-pull inflation spikes rapidly. Configure CBN monetary levers to contract excess liquidity.",
            objective = "Choose contractionary Monetary Policy Rate (MPR) and Open Market Operation actions.",
            cALabel = "CBN Interest Rate (MPR)", cAOptions = listOf("Raise MPR (tight credit)", "Slash MPR to 2%"),
            cBLabel = "Open Market Operations", cBOptions = listOf("Sell Treasury Bills to banks", "Buy back bills"),
            cAIdx = 0, cBIdx = 0,
            preview = { a, b ->
              "Policy Stance: ${if (a == 0) "Higher MPR" else "Cheap credit"} + ${if (b == 0) "Selling OMO drains bank liquidity" else "Printing liquidity"}"
            },
            hint = "Contractionary policy raises borrowing costs and sells bonds to absorb cash.",
            explanation = "Raising MPR and selling government securities dampens aggregate money supply.",
          ),
        )

      else ->
        listOf(
          console(
            id = "gov_1",
            title = "1999 Legislative List Arbiter",
            scenario = "Sort policy powers cleanly across Nigeria's 1999 Federal Constitutional Lists.",
            objective = "Assign Currency/Defence vs Primary Education & Motor Parks to their constitutional tiers.",
            cALabel = "Currency, Defence & Aviation", cAOptions = listOf("Exclusive Legislative List", "Residual List"),
            cBLabel = "Primary Markets & Refuse Disposal", cBOptions = listOf("Exclusive Federal", "Fourth Schedule Local Government"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b -> "Constitutional Map: Tier A=${if (a == 0) "Exclusive" else "Residual"} | Tier B=${if (b == 0) "Federal" else "Local Govt"}" },
            hint = "Defence & currency sit strictly on the 68-item Exclusive List; markets & motor parks sit under Local Governments.",
            explanation = "The 1999 Constitution reserves national sovereignty items exclusively to the Federal Assembly.",
          ),
          console(
            id = "gov_2",
            title = "1963 vs 1979 System Switch",
            scenario = "Compare Nigeria's First Republic Westminster model (1963) vs Second Republic Executive model (1979).",
            objective = "Identify Head of State vs Executive accountability under the 1963 Parliamentary Republic.",
            cALabel = "1963 Power Split", cAOptions = listOf("Fused Cabinet + Ceremonial President", "Strict Separation of Powers"),
            cBLabel = "1963 Supreme Final Court", cBOptions = listOf("Privy Council in London", "Supreme Court of Nigeria"),
            cAIdx = 0, cBIdx = 1,
            preview = { a, b -> "1963 Republic Brief: ${if (a == 0) "Prime Minister + President" else "Presidential"} | Final Court: ${if (b == 1) "Supreme Court of Nigeria" else "Privy Council"}" },
            hint = "The 1963 Republican Constitution abolished appeals to the British Privy Council while keeping the parliamentary cabinet.",
            explanation = "Nigeria's 1963 Constitution made the Supreme Court final and separated Head of State (Azikiwe) from Head of Government (Balewa).",
          ),
          console(
            id = "gov_3",
            title = "Federalism Checks & Impeachment",
            scenario = "Maintain constitutional checks without violating Separation of Powers.",
            objective = "Pair Judicial Review with Executive Veto Override cleanly.",
            cALabel = "Strikes Down Unconstitutional Acts", cAOptions = listOf("Judiciary (Judicial Review)", "Civil Service"),
            cBLabel = "Overrides Presidential Veto", cBOptions = listOf("Simple majority", "2/3 Joint National Assembly Majority"),
            cAIdx = 0, cBIdx = 1,
            preview = { _, _ -> "Checks & Balances Engine Verified" },
            hint = "Only a two-thirds supermajority overrides a presidential veto under Section 58.",
            explanation = "Judicial review guards constitutional supremacy while a 2/3 legislative supermajority checks executive veto.",
          ),
        )
    }

  private fun slider(
    id: String,
    title: String,
    scenario: String,
    objective: String,
    p1Label: String,
    p1Unit: String,
    p1Range: ClosedFloatingPointRange<Float>,
    p1Init: Float,
    outLabel: String,
    outUnit: String,
    target: Float,
    tol: Float,
    gauge: ClosedFloatingPointRange<Float>,
    compute: (Float, Float) -> Float,
    hint: String,
    explanation: String,
  ) =
    GamifiedRound.SliderChallenge(
      id = id,
      title = title,
      scenario = scenario,
      objective = objective,
      param1Label = p1Label,
      param1Unit = p1Unit,
      param1Range = p1Range,
      param1Initial = p1Init,
      outputLabel = outLabel,
      outputUnit = outUnit,
      targetValue = target,
      tolerance = tol,
      computeOutput = compute,
      gaugeRange = gauge,
      hint = hint,
      explanation = explanation,
    )

  private fun console(
    id: String,
    title: String,
    scenario: String,
    objective: String,
    cALabel: String,
    cAOptions: List<String>,
    cBLabel: String,
    cBOptions: List<String>,
    cAIdx: Int,
    cBIdx: Int,
    preview: (Int, Int) -> String,
    hint: String,
    explanation: String,
  ) =
    GamifiedRound.ConsoleChallenge(
      id = id,
      title = title,
      scenario = scenario,
      objective = objective,
      controlALabel = cALabel,
      controlAOptions = cAOptions,
      controlBLabel = cBLabel,
      controlBOptions = cBOptions,
      correctAIndex = cAIdx,
      correctBIndex = cBIdx,
      observePreview = preview,
      hint = hint,
      explanation = explanation,
    )
}
