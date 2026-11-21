package com.example.data.curriculum

/**
 * Curated, strictly verified external simulations, animations, interactive math tools, and study
 * guides drawn exclusively from the approved educational resource portals.
 */
object VerifiedResourceCatalog {

  fun forLesson(lesson: Lesson): List<VerifiedExternalResource> {
    val labSpecific = byLabType[lesson.labType].orEmpty()
    val subjectCore = bySubject[lesson.subjectId].orEmpty()
    return (labSpecific + subjectCore).distinctBy { it.url }.take(5)
  }

  fun forLabType(labType: PracticalLabType): List<VerifiedExternalResource> =
    byLabType[labType].orEmpty()

  private val byLabType: Map<PracticalLabType, List<VerifiedExternalResource>> =
    mapOf(
      PracticalLabType.MEASUREMENT_VECTOR_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Vernier Calliper & Screw Gauge Virtual Practical",
            provider = "OLabs (Online Labs)",
            kind = "Interactive Virtual Lab",
            url = "https://www.olabs.edu.in",
            description = "Practice reading main-scale and vernier/circular-thimble divisions with zero-error calibration.",
          ),
          VerifiedExternalResource(
            title = "Forces and Motion & Vector Addition",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/en/simulations/forces-and-motion-basics",
            description = "Explore balanced vs unbalanced resultant force vectors interactively.",
          ),
          VerifiedExternalResource(
            title = "Mechanics & Vectors Reference",
            provider = "HyperPhysics (GSU)",
            kind = "Equation & Concept Map",
            url = "http://hyperphysics.phy-astr.gsu.edu/",
            description = "Structured vector resolution, units, and mechanics reference sheets.",
          ),
        ),
      PracticalLabType.PROJECTILE_KINEMATICS to
        listOf(
          VerifiedExternalResource(
            title = "Projectile Motion HTML5 Lab",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/sims/html/projectile-motion/latest/projectile-motion_en.html",
            description = "Launch projectiles across angles and initial speeds to measure range, peak height, and flight time.",
          ),
          VerifiedExternalResource(
            title = "Kinematics & Projectile Interactives",
            provider = "The Physics Classroom",
            kind = "Interactive Animation",
            url = "https://www.physicsclassroom.com/Physics-Interactives",
            description = "Interactive motion graphs, vector components, and trajectory animations.",
          ),
        ),
      PracticalLabType.TORQUE_MACHINES_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Forces, Equilibrium & Rotational Mechanics",
            provider = "MyPhysicsLab",
            kind = "Interactive Physics Model",
            url = "https://www.myphysicslab.com/",
            description = "Simulate rigid-body equilibrium, torque, and coupled mechanical constraints in real time.",
          ),
          VerifiedExternalResource(
            title = "Classical Mechanics OpenCourseWare (8.01SC)",
            provider = "MIT OpenCourseWare",
            kind = "Structured Study Guide",
            url = "https://ocw.mit.edu/courses/8-01sc-classical-mechanics-fall-2011/",
            description = "Worked derivations for torque, static equilibrium, work, and mechanical energy.",
          ),
        ),
      PracticalLabType.SHM_FLUIDS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Pendulum Lab & SHM Oscillation",
            provider = "PhET Interactive Simulations",
            kind = "Interactive Simulation",
            url = "https://phet.colorado.edu/en/simulations/pendulum-lab",
            description = "Verify period independence from bob mass and square-root scaling with length.",
          ),
          VerifiedExternalResource(
            title = "Spring & Double Pendulum SHM Simulator",
            provider = "MyPhysicsLab",
            kind = "Interactive Physics Model",
            url = "https://www.myphysicslab.com/",
            description = "Visualize displacement, velocity, acceleration, and energy exchange in harmonic motion.",
          ),
        ),
      PracticalLabType.THERMAL_EXPANSION_CALORIMETRY to
        listOf(
          VerifiedExternalResource(
            title = "Gas Properties & Kinetic Thermal Chamber",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/sims/html/gas-properties/latest/gas-properties_en.html",
            description = "Heat or compress ideal gas particles and track kinetic temperature vs pressure.",
          ),
          VerifiedExternalResource(
            title = "Thermodynamics & Phase Changes",
            provider = "Isaac Science",
            kind = "Problem-Solving Guide",
            url = "https://isaacscience.org",
            description = "Graduated problem sets and diagnostic feedback on calorimetry and thermal physics.",
          ),
        ),
      PracticalLabType.WAVE_OPTICS_BENCH to
        listOf(
          VerifiedExternalResource(
            title = "Wave on a String HTML5 Simulator",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/sims/html/wave-on-a-string/latest/wave-on-a-string_en.html",
            description = "Adjust frequency, tension, and damping to inspect wavelength and standing wave nodes.",
          ),
          VerifiedExternalResource(
            title = "Geometric Optics Lens & Mirror Lab",
            provider = "PhET Interactive Simulations",
            kind = "Interactive Simulation",
            url = "https://phet.colorado.edu/en/simulations/geometric-optics",
            description = "Trace principal rays through converging lenses to inspect real vs virtual images.",
          ),
          VerifiedExternalResource(
            title = "Transverse & Longitudinal Wave Animations",
            provider = "Wikimedia Commons",
            kind = "Educational Animation",
            url = "https://commons.wikimedia.org/wiki/File:Wave.gif",
            description = "Clear looping visualization comparing particle oscillation against wave propagation.",
          ),
          VerifiedExternalResource(
            title = "Wave Interference Ripple Pattern",
            provider = "Wikimedia Commons",
            kind = "Educational Animation",
            url = "https://commons.wikimedia.org/wiki/File:Waventerference.gif",
            description = "Looping two-source wave constructive and destructive interference fringes.",
          ),
        ),
      PracticalLabType.ELECTROSTATIC_CAPACITOR_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Physics Electricity & Electrostatics Interactives",
            provider = "The Physics Classroom",
            kind = "Interactive Animation",
            url = "https://www.physicsclassroom.com/Physics-Interactives",
            description = "Visualize electric field lines, Coulomb forces, and charge distribution.",
          ),
          VerifiedExternalResource(
            title = "Physics Simulations Portal",
            provider = "PhET Interactive Simulations",
            kind = "Simulation Library",
            url = "https://phet.colorado.edu/en/simulations/category/physics",
            description = "Explore charge interactions, capacitance, and electric potential labs.",
          ),
        ),
      PracticalLabType.OHMS_CIRCUIT to
        listOf(
          VerifiedExternalResource(
            title = "Circuit Construction Kit: DC HTML5 Lab",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/sims/html/circuit-construction-kit-dc/latest/circuit-construction-kit-dc_en.html",
            description = "Build series and parallel resistor networks with ammeters, voltmeters, and internal resistance.",
          ),
          VerifiedExternalResource(
            title = "Ohm's Law & Resistance Verification",
            provider = "OLabs (Online Labs)",
            kind = "Interactive Virtual Lab",
            url = "https://www.olabs.edu.in",
            description = "Plot V-I characteristics and determine wire resistivity in virtual practical mode.",
          ),
        ),
      PracticalLabType.EM_TRANSFORMER_AC_LAB to
        listOf(
          VerifiedExternalResource(
            title = "DC/AC Circuit & Electromagnetism Library",
            provider = "PhET Interactive Simulations",
            kind = "Simulation Library",
            url = "https://phet.colorado.edu/en/simulations/category/physics",
            description = "Explore Faraday induction, transformers, and alternating current waveforms.",
          ),
          VerifiedExternalResource(
            title = "Electromagnetism & AC Concept Guide",
            provider = "HyperPhysics (GSU)",
            kind = "Equation & Concept Map",
            url = "http://hyperphysics.phy-astr.gsu.edu/",
            description = "Reference guides for Lenz's law, transformer efficiency, and RLC impedance.",
          ),
        ),
      PracticalLabType.QUANTUM_NUCLEAR_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Quantum & Nuclear Physics Sims",
            provider = "PhET Interactive Simulations",
            kind = "Simulation Library",
            url = "https://phet.colorado.edu/en/simulations/category/physics",
            description = "Experiment with photoelectric threshold frequency and nuclear half-life decay curves.",
          ),
          VerifiedExternalResource(
            title = "Modern & Nuclear Physics Summaries",
            provider = "Isaac Science",
            kind = "Structured Study Guide",
            url = "https://isaacscience.org",
            description = "Practice mass defect, binding energy, and quantum photon calculations.",
          ),
        ),

      // Mathematics
      PracticalLabType.NUMBER_BASE_INDICES_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Desmos Scientific Calculator & Explorer",
            provider = "Desmos",
            kind = "Interactive Math Tool",
            url = "https://www.desmos.com/scientific",
            description = "Verify powers, roots, standard form, and logarithmic evaluations accurately.",
          ),
          VerifiedExternalResource(
            title = "Algebra & Number Theory Lessons",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Step-by-step practice on exponents, logarithms, surds, and modular systems.",
          ),
        ),
      PracticalLabType.SETS_VENN_LAB to
        listOf(
          VerifiedExternalResource(
            title = "GeoGebra Interactive Mathematics",
            provider = "GeoGebra",
            kind = "Interactive Modeling Tool",
            url = "https://www.geogebra.org",
            description = "Visualize set intersections, domain/range function mappings, and logical regions.",
          ),
          VerifiedExternalResource(
            title = "Sets & Functions Study Path",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Master Venn cardinalities, function composition, and inverse relations.",
          ),
        ),
      PracticalLabType.ALGEBRA_VARIATION_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Desmos Graphing Calculator",
            provider = "Desmos",
            kind = "Interactive Graphing Tool",
            url = "https://www.desmos.com/calculator",
            description = "Plot direct y = kx lines, inverse hyperbolas y = k/x, and inequality half-planes.",
          ),
        ),
      PracticalLabType.QUADRATIC_TANGENT to
        listOf(
          VerifiedExternalResource(
            title = "Desmos Parabola & Roots Plotter",
            provider = "Desmos",
            kind = "Interactive Graphing Tool",
            url = "https://www.desmos.com/calculator",
            description = "Slide a, b, c parameters to watch roots, vertex symmetry, and discriminant b² − 4ac shift live.",
          ),
        ),
      PracticalLabType.SEQUENCE_MATRIX_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Desmos Matrix Calculator",
            provider = "Desmos",
            kind = "Interactive Matrix Tool",
            url = "https://www.desmos.com/matrix",
            description = "Compute matrix products, determinants, and inverses step-by-step.",
          ),
        ),
      PracticalLabType.POLYGON_CIRCLE_THEOREM_LAB to
        listOf(
          VerifiedExternalResource(
            title = "GeoGebra Geometry & Circle Theorems",
            provider = "GeoGebra",
            kind = "Interactive Geometry Tool",
            url = "https://www.geogebra.org",
            description = "Drag chord vertices around a circle to verify alternate-segment and cyclic quadrilateral theorems.",
          ),
          VerifiedExternalResource(
            title = "Desmos Geometry Constructions",
            provider = "Desmos",
            kind = "Interactive Geometry Tool",
            url = "https://www.desmos.com/geometry",
            description = "Construct perpendicular bisectors, angle bisectors, and polygon angle sums.",
          ),
        ),
      PracticalLabType.MENSURATION_3D_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Desmos 3D Solids Explorer",
            provider = "Desmos",
            kind = "Interactive 3D Tool",
            url = "https://www.desmos.com/3d",
            description = "Inspect cones, spheres, cylinders, and cross-sectional planes in 3D space.",
          ),
        ),
      PracticalLabType.TRIG_BEARING_LAB to
        listOf(
          VerifiedExternalResource(
            title = "GeoGebra Unit Circle & Bearings",
            provider = "GeoGebra",
            kind = "Interactive Geometry Tool",
            url = "https://www.geogebra.org",
            description = "Compare sine/cosine waves with CAST quadrant signs and 3-figure bearing triangles.",
          ),
        ),
      PracticalLabType.COORDINATE_CALCULUS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Single Variable Calculus (18.01SC)",
            provider = "MIT OpenCourseWare",
            kind = "Structured Course Guide",
            url = "https://ocw.mit.edu/courses/18-01sc-single-variable-calculus-fall-2010/",
            description = "Authoritative notes and worked problems on differentiation, stationary points, and definite integrals.",
          ),
          VerifiedExternalResource(
            title = "Desmos Tangent & Integral Grapher",
            provider = "Desmos",
            kind = "Interactive Graphing Tool",
            url = "https://www.desmos.com/calculator",
            description = "Plot f(x), tangent slopes f'(x), and shaded definite integral areas.",
          ),
        ),
      PracticalLabType.PROBABILITY_STATISTICS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Desmos Statistics & Distribution Lab",
            provider = "Desmos",
            kind = "Interactive Math Tool",
            url = "https://www.desmos.com/calculator",
            description = "Compute mean, variance, standard deviation, and cumulative ogive plots.",
          ),
        ),

      // Chemistry
      PracticalLabType.SEPARATION_ATOMIC_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Chemistry Separation & Salt Practicals",
            provider = "OLabs (Online Labs)",
            kind = "Interactive Virtual Lab",
            url = "https://www.olabs.edu.in",
            description = "Perform filtration, distillation, and crystallization virtual bench procedures.",
          ),
          VerifiedExternalResource(
            title = "Principles of Chemical Science (5.111SC)",
            provider = "MIT OpenCourseWare",
            kind = "Structured Course Guide",
            url = "https://ocw.mit.edu/courses/5-111sc-principles-of-chemical-science-fall-2014/",
            description = "Explore atomic orbitals, Aufbau filling, and quantum configurations.",
          ),
        ),
      PracticalLabType.BONDING_PERIODIC_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Periodic Trends & Chemical Bonding",
            provider = "Royal Society of Chemistry",
            kind = "Interactive Resource",
            url = "https://edu.rsc.org",
            description = "Visual periodic table, electronegativity maps, and ionic/covalent bonding guides.",
          ),
        ),
      PracticalLabType.STOICHIOMETRY_MOLE_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Balancing Chemical Equations Simulation",
            provider = "PhET Interactive Simulations",
            kind = "Interactive Simulation",
            url = "https://phet.colorado.edu/en/simulations/balancing-chemical-equations",
            description = "Balance atom counts visually and identify excess vs limiting reactants.",
          ),
          VerifiedExternalResource(
            title = "Virtual Chemistry Lab Stoichiometry",
            provider = "ChemCollective",
            kind = "Interactive Virtual Lab",
            url = "https://chemcollective.org",
            description = "Mix aqueous reagents virtually to track limiting reactants and molar yields.",
          ),
          VerifiedExternalResource(
            title = "Chemical Equation Balancer & Molar Mass Tool",
            provider = "ChemicalAid",
            kind = "Equation Calculator",
            url = "https://www.chemicalaid.com",
            description = "Verify balanced stoichiometry, limiting reagents, and molar mass percentages.",
          ),
        ),
      PracticalLabType.GAS_LAW_PISTON to
        listOf(
          VerifiedExternalResource(
            title = "Gas Properties Piston Simulation",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/sims/html/gas-properties/latest/gas-properties_en.html",
            description = "Pump gas particles, lock volume or temperature, and verify Boyle's, Charles', and Ideal Gas laws.",
          ),
        ),
      PracticalLabType.TITRATION_PH to
        listOf(
          VerifiedExternalResource(
            title = "Acid-Base Solutions HTML5 Simulation",
            provider = "PhET Interactive Simulations",
            kind = "HTML5 Simulation",
            url = "https://phet.colorado.edu/sims/html/acid-base-solutions/latest/acid-base-solutions_en.html",
            description = "Compare strong vs weak acids/bases using pH meters, conductivity probes, and indicators.",
          ),
          VerifiedExternalResource(
            title = "pH Scale Interactive Bench",
            provider = "PhET Interactive Simulations",
            kind = "Interactive Simulation",
            url = "https://phet.colorado.edu/en/simulations/ph-scale",
            description = "Dilute solutions and observe logarithmic H₃O⁺ and OH⁻ concentration shifts.",
          ),
          VerifiedExternalResource(
            title = "Volumetric Titration Endpoint Animation",
            provider = "Wikimedia Commons",
            kind = "Educational Animation",
            url = "https://commons.wikimedia.org/wiki/File:Titration.gif",
            description = "Looping animation of burette drop addition and indicator endpoint colour change.",
          ),
        ),
      PracticalLabType.ENERGETICS_KINETICS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "ChemReaX Kinetics & Chemical Equilibrium",
            provider = "ChemReaX (Science by Simulation)",
            kind = "Reaction Simulator",
            url = "https://www.sciencebysimulation.com",
            description = "Run Haber process equilibrium, enthalpy ΔH, and temperature/pressure shift experiments.",
          ),
          VerifiedExternalResource(
            title = "Concord Molecular Workbench Simulations",
            provider = "The Concord Consortium",
            kind = "Interactive Simulation",
            url = "https://concord.org",
            description = "Inspect activation energy barriers, collision orientation, and catalytic pathways.",
          ),
        ),
      PracticalLabType.REDOX_ELECTROLYSIS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Electrochemistry & Electrolysis Resources",
            provider = "Royal Society of Chemistry",
            kind = "Study Guide & Interactive",
            url = "https://edu.rsc.org",
            description = "Compare electrolytic vs electrochemical cells, preferential discharge, and Faraday laws.",
          ),
        ),
      PracticalLabType.INORGANIC_GASES_METALS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Inorganic Chemistry & Extraction Guides",
            provider = "Royal Society of Chemistry",
            kind = "Structured Study Guide",
            url = "https://edu.rsc.org",
            description = "Review non-metal gas preparation, Haber/Contact processes, and blast-furnace extraction.",
          ),
        ),
      PracticalLabType.ORGANIC_HYDROCARBON_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Organic Functional Groups & Isomerism",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Master IUPAC nomenclature, alkane substitution, alkene addition, and esterification.",
          ),
        ),
      PracticalLabType.QUALITATIVE_ANALYSIS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Qualitative Ion Analysis Bench",
            provider = "ChemCollective",
            kind = "Interactive Virtual Lab",
            url = "https://chemcollective.org",
            description = "Test cation precipitation in NaOH(aq) and NH₃(aq) plus confirmatory anion tests.",
          ),
          VerifiedExternalResource(
            title = "Salt Analysis Virtual Practical",
            provider = "OLabs (Online Labs)",
            kind = "Interactive Virtual Lab",
            url = "https://www.olabs.edu.in",
            description = "Follow systematic WAEC-style wet reagent tests for soluble and insoluble salts.",
          ),
        ),

      // Biology
      PracticalLabType.CELL_OSMOSIS_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Osmosis & Microscopy Virtual Lab",
            provider = "OLabs (Online Labs)",
            kind = "Interactive Virtual Lab",
            url = "https://www.olabs.edu.in",
            description = "Measure potato osmometer changes and inspect plant vs animal cells under light microscopy.",
          ),
          VerifiedExternalResource(
            title = "Mitosis Cell Division Animation",
            provider = "Wikimedia Commons",
            kind = "Educational Animation",
            url = "https://commons.wikimedia.org/wiki/File:Major_events_in_mitosis.svg",
            description = "Visual sequence of prophase, metaphase, anaphase, and telophase chromosome separation.",
          ),
        ),
      PracticalLabType.BIOENERGETICS_ENZYME_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Enzymatic, Respiration & Photosynthesis Games",
            provider = "BioMan Biology",
            kind = "Gamified Biology Sim",
            url = "https://biomanbio.com",
            description = "Control enzyme substrate collisions, ATP synthesis, and chloroplast light reactions interactively.",
          ),
          VerifiedExternalResource(
            title = "Photosynthesis & Cellular BioInteractive",
            provider = "HHMI BioInteractive",
            kind = "3D Concept Model",
            url = "https://www.biointeractive.org/classroom-resources",
            description = "Interactive animations tracing Photosystems I/II, Calvin cycle, and mitochondrial ATP synthase.",
          ),
        ),
      PracticalLabType.NUTRITION_TRANSPORT_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Mammalian Cardiac Cycle Animation",
            provider = "Wikimedia Commons",
            kind = "Educational Animation",
            url = "https://commons.wikimedia.org/wiki/File:Cardiac-Cycle-Animated.gif",
            description = "Watch atrial systole, ventricular systole, and valve closure in double circulation.",
          ),
          VerifiedExternalResource(
            title = "Fundamentals of Biology (7.01SC)",
            provider = "MIT OpenCourseWare",
            kind = "Structured Course Guide",
            url = "https://ocw.mit.edu/courses/7-01sc-fundamentals-of-biology-fall-2011/",
            description = "Biochemistry of carbohydrates, proteins, lipids, and vascular physiology.",
          ),
        ),
      PracticalLabType.RESPIRATION_KIDNEY_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Biology Virtual Simulations & Physiology",
            provider = "LabXchange",
            kind = "Interactive Pathway",
            url = "https://www.labxchange.org",
            description = "Interactive physiological pathways covering alveolar gas exchange and nephron osmoregulation.",
          ),
        ),
      PracticalLabType.NERVOUS_ENDOCRINE_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Nervous & Endocrine Regulation Sims",
            provider = "BioMan Biology",
            kind = "Interactive Biology Tool",
            url = "https://biomanbio.com",
            description = "Explore reflex arcs, eye lens accommodation, and hormonal feedback loops.",
          ),
        ),
      PracticalLabType.REPRODUCTION_FLOWER_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Biology Interactives & Reproductive Physiology",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Trace menstrual hormone peaks (FSH, LH, Estrogen, Progesterone) and plant double fertilization.",
          ),
        ),
      PracticalLabType.MENDELIAN_GENETICS to
        listOf(
          VerifiedExternalResource(
            title = "Snurfle Meiosis & Genetics Lab",
            provider = "BioMan Biology",
            kind = "Gamified Genetics Sim",
            url = "https://biomanbio.com",
            description = "Perform monohybrid, dihybrid, and sex-linked crosses with interactive Punnett tracking.",
          ),
          VerifiedExternalResource(
            title = "DNA Double Helix Replication Animation",
            provider = "Wikimedia Commons",
            kind = "Educational Animation",
            url = "https://commons.wikimedia.org/wiki/File:DNA_animation.gif",
            description = "Looping 3D model showing complementary base pairing and helical structure.",
          ),
        ),
      PracticalLabType.EVOLUTION_VARIATION_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Natural Selection Simulation",
            provider = "PhET Interactive Simulations",
            kind = "Interactive Simulation",
            url = "https://phet.colorado.edu/en/simulations/natural-selection",
            description = "Add selection pressures and mutations to observe population allele frequency shifts.",
          ),
          VerifiedExternalResource(
            title = "Evolution & Adaptation Classroom Resources",
            provider = "HHMI BioInteractive",
            kind = "3D Animation & Case Study",
            url = "https://www.biointeractive.org/classroom-resources",
            description = "Authentic evidence-based animations covering adaptive radiation and natural selection.",
          ),
        ),
      PracticalLabType.ECOLOGY_ENERGY_PYRAMID to
        listOf(
          VerifiedExternalResource(
            title = "Ecology & Trophic Dynamics Interactives",
            provider = "BioMan Biology",
            kind = "Interactive Ecology Lab",
            url = "https://biomanbio.com",
            description = "Simulate predator-prey cycles, ecological succession, and 10% trophic energy transfer.",
          ),
        ),
      PracticalLabType.BIOGEOCHEMICAL_SOIL_LAB to
        listOf(
          VerifiedExternalResource(
            title = "Nutrient Cycles & Soil Microbiology",
            provider = "HHMI BioInteractive",
            kind = "Interactive Concept Model",
            url = "https://www.biointeractive.org/classroom-resources",
            description = "Interactive Earth-system nutrient cycles showing Nitrogen and Carbon flows.",
          ),
        ),
    )

  private val bySubject: Map<String, List<VerifiedExternalResource>> =
    mapOf(
      "physics" to
        listOf(
          VerifiedExternalResource(
            title = "PhET Physics Simulations Library",
            provider = "PhET Interactive Simulations",
            kind = "Simulation Portal",
            url = "https://phet.colorado.edu/en/simulations/category/physics",
            description = "Full library of interactive Physics experiments covering mechanics, waves, optics, and circuits.",
          ),
          VerifiedExternalResource(
            title = "HyperPhysics Illustrated Equations",
            provider = "HyperPhysics (GSU)",
            kind = "Quick Reference",
            url = "http://hyperphysics.phy-astr.gsu.edu/",
            description = "Interlinked formula calculators and conceptual flowcharts across every Physics branch.",
          ),
        ),
      "mathematics" to
        listOf(
          VerifiedExternalResource(
            title = "Desmos Interactive Graphing Suite",
            provider = "Desmos",
            kind = "Interactive Math Tool",
            url = "https://www.desmos.com/calculator",
            description = "Plot algebraic, trigonometric, coordinate, and calculus curves dynamically.",
          ),
          VerifiedExternalResource(
            title = "GeoGebra Geometry & Algebra Portal",
            provider = "GeoGebra",
            kind = "Interactive Math Tool",
            url = "https://www.geogebra.org",
            description = "Construct geometric theorems, loci, vectors, and graphs interactively.",
          ),
        ),
      "chemistry" to
        listOf(
          VerifiedExternalResource(
            title = "PhET Chemistry Simulations Library",
            provider = "PhET Interactive Simulations",
            kind = "Simulation Portal",
            url = "https://phet.colorado.edu/en/simulations/category/chemistry",
            description = "Interactive Chemistry labs covering reactions, gases, acids/bases, and quantum atoms.",
          ),
          VerifiedExternalResource(
            title = "ChemicalAid Equation & Stoichiometry Solver",
            provider = "ChemicalAid",
            kind = "Quick Reference Calculator",
            url = "https://www.chemicalaid.com",
            description = "Balance reactions, compute molar masses, and check limiting reagent ratios.",
          ),
        ),
      "biology" to
        listOf(
          VerifiedExternalResource(
            title = "PhET Biology Simulations Library",
            provider = "PhET Interactive Simulations",
            kind = "Simulation Portal",
            url = "https://phet.colorado.edu/en/simulations/category/biology",
            description = "Interactive simulations on membrane transport, natural selection, and gene expression.",
          ),
          VerifiedExternalResource(
            title = "HHMI BioInteractive Classroom Portal",
            provider = "HHMI BioInteractive",
            kind = "2D/3D Biology Models",
            url = "https://www.biointeractive.org/classroom-resources",
            description = "Award-winning biological animations for genetics, physiology, evolution, and ecology.",
          ),
        ),
      "english" to
        listOf(
          VerifiedExternalResource(
            title = "Grammar, Syntax & Reading Mastery",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Master clauses, modifiers, punctuation, concord, and analytical reading comprehension.",
          ),
          VerifiedExternalResource(
            title = "MIT OpenCourseWare Writing & Rhetoric",
            provider = "MIT OpenCourseWare",
            kind = "Structured Course Guide",
            url = "https://ocw.mit.edu",
            description = "University-grade style, essay organization, and summary condensation frameworks.",
          ),
        ),
      "economics" to
        listOf(
          VerifiedExternalResource(
            title = "Microeconomics & Macroeconomics Curves",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Step-by-step graphs and worked calculations on elasticity, PPC, multiplier, and monetary policy.",
          ),
          VerifiedExternalResource(
            title = "Desmos Demand, Supply & Tax Plotter",
            provider = "Desmos",
            kind = "Interactive Graphing Tool",
            url = "https://www.desmos.com/calculator",
            description = "Plot linear supply/demand functions, equilibrium shifts, and cost curves.",
          ),
        ),
      "government" to
        listOf(
          VerifiedExternalResource(
            title = "Constitutional & Civic Institutions",
            provider = "Khan Academy",
            kind = "Structured Study Guide",
            url = "https://www.khanacademy.org",
            description = "Comparative studies of federalism, separation of powers, and constitutional checks and balances.",
          ),
          VerifiedExternalResource(
            title = "MIT OpenCourseWare Political Science",
            provider = "MIT OpenCourseWare",
            kind = "Structured Course Guide",
            url = "https://ocw.mit.edu",
            description = "Foundational frameworks on state sovereignty, electoral systems, and international organizations.",
          ),
        ),
    )
}
