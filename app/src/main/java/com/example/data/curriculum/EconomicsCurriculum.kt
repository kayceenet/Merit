package com.example.data.curriculum

object EconomicsCurriculum {

  val track: SubjectTrack by lazy {
    SubjectTrack(
      id = "economics",
      title = "Economics Specialization",
      shortName = "Economics",
      subtitle = "10 Zero-to-A1 lessons covering Scarcity, Elasticity, Market Structures, National Income & CBN Policy",
      modules = module1To3 + EconomicsCurriculumPart2.module4And5,
      pastQuestions = EconomicsCurriculumPart2.pastQuestions,
    )
  }

  internal fun ecoConcept(
    heading: String,
    explanation: String,
    keyFormula: String?,
    examTip: String,
    mechanism: String,
    intel: String,
    recap: String,
  ) = ConceptBlock(
    heading = heading.replace(Regex("""^\d+\.\s+"""), "").trim(),
    explanation = explanation,
    keyFormula = keyFormula,
    examTip = examTip,
    mechanismWhyHow = mechanism,
    examIntelligence = intel,
    recap = recap,
  )

  internal fun q(
    id: String,
    topic: String,
    board: String,
    year: Int,
    prompt: String,
    options: List<String>,
    correct: Int,
    hint: String,
    solution: String,
    scheme: String? = null,
  ) = PracticeQuestion(
    id = id,
    topic = topic,
    examType = board,
    year = year,
    question = prompt,
    options = options,
    correctIndex = correct,
    hint = hint,
    stepByStepSolution = solution,
    waecMarkingScheme = scheme,
  )

  val module1To3: List<CourseModule> =
    listOf(
      CourseModule(
        id = "eco_mod_1",
        subjectId = "economics",
        indexLabel = "Module 01",
        title = "Foundations of Choice, Scarcity & Consumer Elasticity",
        summary = "Scarcity, opportunity cost, PPC curves, price index tools, marginal utility & PED/YED/XED.",
        lessons =
          listOf(
            Lesson(
              id = "eco_01_scarcity_ppc",
              moduleId = "eco_mod_1",
              subjectId = "economics",
              title = "Scarcity, Opportunity Cost & the Production Possibility Curve",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 1 • WAEC Basic Economic Concepts",
              learningOutcomes =
                listOf(
                  "Distinguish scarce resources from free goods using Lionel Robbins' definition.",
                  "Rank wants on a Scale of Preference and quantify true Opportunity Cost.",
                  "Interpret unattainable, efficient, and under-employed points on a Production Possibility Curve (PPC).",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Robbins' Definition & the Core Dilemma",
                    "Suppose a family budget in Enugu has NGN 20,000 while rent, textbooks, data, and transport cost NGN 45,000 together. Economics studies how human beings allocate limited productive means (land, labour, capital, entrepreneurship) that have alternative uses among unlimited wants. Wants stay endless, so choice becomes compulsory.",
                    "\\text{Scarcity} \\implies \\text{Choice} \\implies \\text{Opportunity Cost}",
                    "Never equate scarcity with total absence or hoarding—crude oil is scarce even when millions of barrels exist because demand exceeds free supply.",
                    "Because land, time, and money serve competing ends, choosing one purpose automatically shuts the door on another.",
                    "JAMB frequently tests Lionel Robbins (1932): Economics as a science studying human behaviour between ends and scarce means.",
                    "Limited means + competing wants force rational ranking and sacrifice.",
                  ),
                  ecoConcept(
                    "2. Scale of Preference & True Opportunity Cost",
                    "A Scale of Preference lists wants from highest urgency down to least urgency. When an SS3 student puts (1) Exam Form NGN 15,000, (2) Physics Textbook NGN 15,000, and (3) Sneakers NGN 15,000, picking Item 1 means the true opportunity cost is ONLY Item 2—the next best foregone alternative—never the cash paid itself.",
                    "\\text{Opportunity Cost} = \\text{Value of Next Best Alternative Foregone}",
                    "Money cost measures naira spent; real (opportunity) cost measures the sacrificed second-best good.",
                    "Ranking prevents impulsive spending and maximizes marginal utility per naira.",
                    "WAEC Paper 2 awards marks for distinguishing money outlay from foregone real goods or leisure time.",
                    "Opportunity cost equals the highest-ranked option given up, not all skipped options combined.",
                  ),
                  ecoConcept(
                    "3. Reading the Production Possibility Curve (PPC)",
                    "Think of a factory floor running 24 hours: it can bake bread or mill bags of cement. Points ON the concave boundary show full employment of resources; points INSIDE show idle labour or recession; points OUTSIDE remain unattainable today unless technology or capital grows.",
                    "\\text{Slope of PPC} = MRT = \\left|\\frac{\\Delta Y}{\\Delta X}\\right|",
                    "A concave (bowed-outward) PPC reflects increasing opportunity cost because resources are specialized.",
                    "Moving more workers from farming into software engineering yields smaller extra output per shift, steepening opportunity cost.",
                    "Economic growth shifts the entire PPC outward; mobilizing unemployed youth moves an economy FROM inside TO the curve without shifting the curve.",
                    "On curve = efficient; inside = unemployment; outward shift = long-run growth.",
                  ),
                  ecoConcept(
                    "4. Economic Systems: Capitalism, Socialism & Mixed",
                    "Every society must answer What, How, and For Whom to produce. Free market (capitalist) economies rely on price signals and private profit; command (socialist) systems rely on central state boards; mixed economies like Nigeria combine private enterprise in telecoms/retail with public regulation in central banking, courts, and grid infrastructure.",
                    "\\text{Price Mechanism}: D > S \\implies P\\uparrow \\implies \\text{Supply Expands}",
                    "Consumer sovereignty is strongest in free-market capitalism, where buyer spending acts like votes.",
                    "Rising profits attract new firms into shortage sectors automatically without central orders.",
                    "NECO often contrasts equity/welfare under public ownership against allocative efficiency under competition.",
                    "Most real nations run mixed economies balancing market incentives with public safety nets.",
                  ),
                  ecoConcept(
                    "5. Quantitative Tools: Mean, Median & Index Numbers",
                    "Economists summarize price changes using simple statistical tools. Arithmetic mean captures average wage, median resists extreme billionaire outliers in income surveys, and a Price Index measures inflation from a base year set at 100.",
                    "\\bar{x} = \\frac{\\Sigma f x}{\\Sigma f} \\quad | \\quad \\text{Price Index} = \\frac{P_1}{P_0} \\times 100",
                    "If the Consumer Price Index moves from 100 to 135, prices rose by 35%, NOT 135%.",
                    "Weighting baskets by household spending shares keeps staple foods more influential than luxury watches.",
                    "JAMB includes 2–3 Economics tables testing weighted mean, mode, or simple index ratio calculations.",
                    "Subtract 100 from any base-100 index to read percentage inflation cleanly.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Calculating Marginal Opportunity Cost Along a PPC",
                    "JAMB UTME-Style Worked Problem",
                    "An economy moves from Combination A (100 tractors, 400 tonnes rice) to Combination B (140 tractors, 320 tonnes rice). Calculate the opportunity cost of producing one extra tractor.",
                    listOf(
                      "Step 1 — Gain in tractors: ΔTractors = 140 − 100 = 40 tractors.",
                      "Step 2 — Sacrifice in rice: ΔRice = 400 − 320 = 80 tonnes.",
                      "Step 3 — Opportunity cost per tractor = 80 / 40 = 2.0 tonnes of rice.",
                    ),
                    "2.0 tonnes of rice per tractor",
                    "Dividing 40 / 80 instead of Sacrifice / Gain inverts the unit.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Federal Budget Trade-Offs Between Power Grids & Highways",
                    "Public Sector Economics",
                    "Allocating capital revenue to build solar transmission corridors defers highway expansions until next fiscal year.",
                    "Directly illustrates scarcity, scale of preference, and movement along Nigeria's public PPC.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
              labBrief = "Explore how price signals, shifts, and resource trade-offs adjust market outcomes dynamically.",
              exercises =
                listOf(
                  q("ex_eco_01_1", "Scarcity & Definition", "JAMB", 2022, "According to Lionel Robbins, the root cause of all economic problems is:", listOf("Overpopulation alone", "Scarcity of means relative to unlimited wants", "Excess government taxation", "Lack of paper currency"), 1, "Think ends versus scarce means.", "Robbins defined scarcity of means with alternative uses against competing wants as universal."),
                  q("ex_eco_01_2", "Opportunity Cost", "WAEC", 2023, "Chidi has NGN 5,000. His preference list is: (1) Chemistry book, (2) Data bundle, (3) Cinema ticket. Buying the Chemistry book makes his opportunity cost:", listOf("NGN 5,000 cash", "The Cinema ticket", "The Data bundle", "Both Data bundle and Cinema ticket"), 2, "Only the NEXT best alternative sacrificed counts.", "Item 2 (Data bundle) is the next-highest foregone alternative."),
                  q("ex_eco_01_3", "Production Possibility Curve", "JAMB", 2021, "A point lying strictly inside a Production Possibility Curve represents:", listOf("Full employment and efficiency", "Unattainable output", "Idle resources and underemployment", "Technological breakthrough"), 2, "Inside the frontier means capacity is wasted.", "Unemployment or inefficient allocation places output inside the boundary."),
                  q("ex_eco_01_4", "PPC Shift", "WAEC", 2020, "Which event shifts the entire PPC outward over time?", listOf("Hiring unemployed graduates with existing machines", "Discovery of higher-yield seeds and skilled automation", "Printing extra naira notes", "Reducing working hours"), 1, "Growth expands productive capacity itself.", "Productivity-boosting innovation expands maximum output."),
                  q("ex_eco_01_5", "Price Index", "JAMB", 2023, "If the price of a basket rises from NGN 4,000 in base year to NGN 5,200, what is the Price Index?", listOf("120", "130", "140", "77"), 1, "(5200 / 4000) × 100.", "5200/4000 = 1.30 → Index = 130 (30% inflation)."),
                  q("ex_eco_01_6", "Economic Systems", "NECO", 2021, "Which feature dominates a pure capitalist economy?", listOf("Zero private ownership", "Consumer sovereignty and price mechanism", "State price boards setting every wage", "Absence of competition"), 1, "Market prices coordinate buyers and sellers.", "Private property and price signals define market capitalism."),
                ),
              prerequisites = listOf("Percentage ratios and line slopes"),
              dependencySummary = "Scarcity and opportunity cost underpin consumer utility, firm supply, and national budget choices.",
              vocabulary =
                listOf(
                  VocabularyEntry("Scale of Preference", "An ordered ranking of wants by urgency.", "Your top-to-bottom wishlist.", "Used to locate Item 2 as opportunity cost."),
                  VocabularyEntry("Ceteris Paribus", "Holding all other influencing factors constant.", "Freeze everything else while testing one variable.", "Mandatory phrase in demand/supply laws."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Money spent equals opportunity cost", "Cash feels like the visible loss.", "Real goods sacrificed next-in-line are the true opportunity cost.", "Ask: What second-choice item did I skip?"),
                  MisconceptionAnalysis("Reducing unemployment shifts the PPC outward", "More output looks like curve expansion.", "Mobilizing idle labour only moves a point FROM inside TO the existing PPC.", "Distinguish movement toward frontier vs outward shift."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "A local agro-firm switches capacity from 60 bags of garri and 20 bags of beans to 45 bags of garri and 30 bags of beans. Compute the opportunity cost of 1 extra bag of beans.",
                  "Foundational",
                  4,
                  listOf("Find the drop in garri output.", "Find the increase in beans output.", "Divide sacrificed garri by gained beans."),
                  "Apply Marginal Rate of Transformation = |ΔGarri / ΔBeans|.",
                  "ΔGarri = 60 − 45 = 15 bags sacrificed; ΔBeans = 30 − 20 = 10 bags gained. Opportunity cost = 15 / 10 = 1.5 bags of garri per bag of beans.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "SS3 Study Time & Micro-Enterprise PPC Audit",
                  "Decision Matrix",
                  "Balance finite weekly study hours between Mathematics problem sets and Economics essay practice.",
                  listOf("List fixed 20-hour constraint", "Compute marginal score gains", "Identify concave trade-off region"),
                  listOf("Correctly defines fixed constraint", "Identifies single next-best foregone item", "Interprets idle hours inside the PPC"),
                  "Inside-frontier hours reflect procrastination; efficient allocation equates marginal exam marks gained per hour across subjects.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Scarcity enforces choice; opportunity cost measures the next-best foregone alternative; PPC tracks efficiency and growth.",
                  listOf("MRT: Marginal Rate of Transformation", "P_0, P_1: Base and current price levels"),
                  listOf("Adding all skipped wants instead of ONLY #2", "Confusing inside-PPC movement with outward shift"),
                  "SO-PP: Scarcity → Option #1 picked → Option #2 is Opportunity Cost; Point Inside = Idle.",
                ),
            ),
            Lesson(
              id = "eco_02_utility_demand",
              moduleId = "eco_mod_1",
              subjectId = "economics",
              title = "Utility Theory, Demand Laws & Elasticity (PED/YED/XED)",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 2 • WAEC Demand & Elasticity",
              learningOutcomes =
                listOf(
                  "Relate Total Utility (TU) and Marginal Utility (MU) through the law of diminishing marginal utility.",
                  "Separate a movement ALONG a demand curve from a SHIFT of the entire demand curve.",
                  "Compute Price, Income, and Cross Elasticities of Demand and link PED to Total Revenue.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Marginal Utility & Consumer Equilibrium",
                    "When you drink cold water after football in Abuja, the 1st cup gives huge satisfaction (MU = 40 utils), the 2nd gives 25, and the 4th might give 0 (where TU hits maximum!). Consumer equilibrium occurs where marginal satisfaction per naira matches across all purchased goods.",
                    "MU = \\frac{\\Delta TU}{\\Delta Q} \\quad | \\quad \\frac{MU_x}{P_x} = \\frac{MU_y}{P_y}",
                    "When MU = 0, Total Utility reaches its MAXIMUM; when MU turns negative, Total Utility falls.",
                    "Because each extra unit adds smaller pleasure, buyers only purchase more units if price falls.",
                    "JAMB loves utility schedule tables with one blank TU or MU cell to fill.",
                    "TU peaks exactly at MU = 0; rational spending equalizes MU per naira.",
                  ),
                  ecoConcept(
                    "2. Movement Along vs Shift in Demand",
                    "Think of a car driving UP or DOWN a fixed road versus moving the entire highway sideways. Own Price ONLY triggers extension or contraction ALONG the existing curve. Changes in income, tastes, population, or prices of substitutes/complements SHIFT the whole curve left or right.",
                    "Q_d = a - bP \\quad (\\text{Normal inverse price slope})",
                    "An 'increase in quantity demanded' comes from lower OWN price; an 'increase in demand' comes from non-price determinants.",
                    "Exceptional upward demand curves occur for Giffen staples (inferior foods taking large income share) and Veblen ostentation goods.",
                    "WAEC penalizes students who write 'price rise shifts demand curve left'—price moves ALONG the curve!",
                    "Own price = move along curve; income/taste/substitute price = shift curve.",
                  ),
                  ecoConcept(
                    "3. Price Elasticity of Demand (PED) & Coefficient Types",
                    "Price Elasticity measures how strongly quantity reacts to a 1% price change. Salt or insulin is price inelastic (|PED| < 1: quantity barely budges); competitive soda brands are elastic (|PED| > 1: buyers flee fast to rivals).",
                    "PED = \\frac{\\%\\Delta Q_d}{\\%\\Delta P} = \\frac{\\Delta Q}{Q_1} \\div \\frac{\\Delta P}{P_1}",
                    "|PED| = 0 is vertical (perfectly inelastic); |PED| = ∞ is horizontal (perfectly elastic); |PED| = 1 is unitary rectangular hyperbola.",
                    "Availability of close substitutes, time horizon, habit/necessity, and proportion of income spent decide |PED|.",
                    "Always use absolute magnitude |PED| when judging whether demand is elastic (>1) or inelastic (<1).",
                    "Inelastic = vertical steep curve; Elastic = flat shallow curve.",
                  ),
                  ecoConcept(
                    "4. PED & Total Revenue (Pricing Power Rule)",
                    "Should a transport operator slash or raise fares? If demand is INELASTIC (no rival bus route), raising price increases Total Revenue because passengers cannot leave. If demand is ELASTIC (many rivals), raising price loses more riders in % terms than it gains in fare, shrinking revenue.",
                    "TR = P \\times Q \\quad | \\quad |PED| < 1: P\\uparrow \\implies TR\\uparrow",
                    "Governments tax price-inelastic goods (tobacco, fuel) heavily because consumption drops little while tax revenue swells.",
                    "Percentage loss in volume outweighs percentage price gain whenever |PED| > 1.",
                    "WAEC Essay Part II regularly pairs a PED calculation with a revenue recommendation for a monopolist or finance minister.",
                    "Elastic demand: cut price to grow revenue; Inelastic demand: raise price to grow revenue.",
                  ),
                  ecoConcept(
                    "5. Income (YED) & Cross Elasticity (XED)",
                    "When household income rises, normal goods rise (YED > 0) while low-grade inferior substitutes fall (YED < 0). Cross Elasticity compares two products: if Milo price rises and Bournvita sales jump, XED > 0 (substitutes); if petrol price jumps and car purchases fall, XED < 0 (complements).",
                    "YED = \\frac{\\%\\Delta Q_d}{\\%\\Delta Y} \\quad | \\quad XED_{AB} = \\frac{\\%\\Delta Q_A}{\\%\\Delta P_B}",
                    "Signs matter for YED and XED! Never drop negative signs when identifying inferior goods or complements.",
                    "Joint demand ties complements together; competitive demand links substitutes in opposite price directions.",
                    "Zero XED means two goods (say cement and mangoes) are completely unrelated.",
                    "XED > 0 = Substitutes; XED < 0 = Complements; YED < 0 = Inferior good.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Computing Price Elasticity & Revenue Direction",
                    "WAEC WASSCE-Style Worked Problem",
                    "When garri price rises from NGN 500 to NGN 600 per paint bucket, weekly demand falls from 200 to 180 buckets. Calculate PED and state its type.",
                    listOf(
                      "Step 1 — Percentage change in price: ΔP/P₁ = (600 − 500)/500 = +0.20 (+20%).",
                      "Step 2 — Percentage change in quantity: ΔQ/Q₁ = (180 − 200)/200 = −0.10 (−10%).",
                      "Step 3 — PED = −10% / +20% = −0.50 (magnitude |PED| = 0.50 < 1).",
                      "Step 4 — Since |PED| = 0.50 < 1, demand is fairly INELASTIC; total revenue rises from NGN 100,000 to NGN 108,000.",
                    ),
                    "|PED| = 0.50 (Inelastic); Total Revenue increases",
                    "Inverting %ΔP over %ΔQ gives 2.0 instead of 0.50—Quantity ALWAYS sits on top!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Telecom Data Bundle Pricing & Competitive Switching",
                    "Nigerian Telecommunications",
                    "Because voice/data subscribers switch SIM cards swiftly, network pricing displays high price elasticity and strong positive cross-elasticity.",
                    "Explains why rival tariffs act as close substitutes with XED > 0.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
              labBrief = "Slide |PED| from inelastic (0.3) to elastic (2.5) and observe live Total Revenue swings when price rises.",
              exercises =
                listOf(
                  q("ex_eco_02_1", "Marginal Utility", "JAMB", 2021, "At the exact consumption level where Marginal Utility equals zero, Total Utility is:", listOf("Zero", "Negative", "At its maximum", "Minimized"), 2, "TU stops rising and has not started falling yet.", "When MU = 0, TU reaches saturation maximum."),
                  q("ex_eco_02_2", "Consumer Equilibrium", "WAEC", 2022, "A two-commodity consumer reaches equilibrium when:", listOf("P_x = P_y", "MU_x / P_x = MU_y / P_y", "TU_x = 0", "MU_x = 0"), 1, "Equal marginal utils per naira spent.", "Equimarginal principle: MU_x/P_x = MU_y/P_y."),
                  q("ex_eco_02_3", "Cross Elasticity", "JAMB", 2023, "Cross elasticity of demand between bread and butter (complements) is expected to be:", listOf("Positive (+)", "Negative (−)", "Infinite", "Always greater than +2"), 1, "Higher butter price reduces bread demand too.", "Complements exhibit negative XED."),
                  q("ex_eco_02_4", "Revenue & PED", "WAEC", 2021, "If demand for a commodity has |PED| = 1.8, a 10% price cut will cause Total Revenue to:", listOf("Fall sharply", "Rise because quantity jumps by 18%", "Stay unchanged", "Drop to zero"), 1, "Quantity response (+18%) exceeds price drop (−10%).", "For elastic demand (|PED|>1), price and TR move inversely."),
                  q("ex_eco_02_5", "Demand Shift", "JAMB", 2020, "Which factor causes a RIGHTWARD SHIFT of the demand curve for umbrellas?", listOf("A drop in umbrella price", "Arrival of heavy rainy-season storms", "Higher umbrella leather tax", "Fall in population"), 1, "Non-price weather preference changes demand at every price.", "Weather shifts demand curve without changing own price."),
                  q("ex_eco_02_6", "Giffen Goods", "NECO", 2022, "Which abnormal good violates the law of demand when staples get costlier and absorb poor households' budget?", listOf("Luxury diamond", "Giffen good", "Normal good", "Free public good"), 1, "Named after Sir Robert Giffen.", "Giffen staples show positive income effect overpowering substitution."),
                ),
              prerequisites = listOf("Percentage change arithmetic", "Opportunity cost"),
              dependencySummary = "PED governs indirect tax incidence between buyers and firms in Supply & Equilibrium.",
              vocabulary =
                listOf(
                  VocabularyEntry("Unitary Elasticity", "Demand where %ΔQ equals %ΔP (|PED|=1).", "Price change leaves total revenue completely flat.", "Rectangular hyperbola demand curve."),
                  VocabularyEntry("Giffen Good", "An inferior staple whose quantity demanded rises when price rises.", "So poor households drop meat to afford more garri.", "Rare exception to downward demand."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Higher price ALWAYS boosts seller revenue", "More per unit sounds automatically richer.", "Only true if demand is inelastic (|PED|<1); elastic buyers walk away.", "Check |PED| before recommending price hikes."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Consumer income rises by 25%, causing bus-ticket demand to fall by 10%. Calculate YED and classify the service.",
                  "Standard",
                  4,
                  listOf("Use YED = %ΔQ / %ΔY.", "Preserve the negative sign on quantity change.", "Interpret negative YED."),
                  "Compute ratio −10% / +25% and interpret sign.",
                  "YED = −10% / +25% = −0.40. Since YED < 0, public bus travel in this income bracket behaves as an inferior good relative to private rides.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Retail Pricing & Elasticity Audit for a Bookshop",
                  "Elasticity Memo",
                  "Advise an Enugu bookshop on pricing mandatory exam past-question packs vs optional fancy notebooks.",
                  listOf("Estimate substitute availability", "Test 15% markup simulation", "Recommend differential pricing"),
                  listOf("Uses exact %ΔQ/%ΔP ratio", "Matches inelastic goods to markups", "Identifies close-substitute elasticity"),
                  "Mandatory syllabus packs face low elasticity (raise margin gently); generic notebooks face close market substitutes (keep competitive price).",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "MU=0 marks max TU; PED = %ΔQ/%ΔP; own price moves along curves while income/tastes shift them.",
                  listOf("TU/MU: Total & Marginal Utility", "PED/YED/XED: Price, Income, Cross Elasticities"),
                  listOf("Putting %ΔP in numerator", "Forgetting XED < 0 for complements"),
                  "Q ON TOP: Quantity change ALWAYS tops every Elasticity fraction.",
                ),
            ),
          ),
      ),

      CourseModule(
        id = "eco_mod_2",
        subjectId = "economics",
        indexLabel = "Module 02",
        title = "Supply, Market Equilibrium & Production Returns",
        summary = "Market clearing, price ceilings/floors, indirect tax incidence, factors of production, division of labour & diminishing returns.",
        lessons =
          listOf(
            Lesson(
              id = "eco_03_supply_equilibrium",
              moduleId = "eco_mod_2",
              subjectId = "economics",
              title = "Supply, Equilibrium Price & Tax Incidence",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 3 • WAEC Price Determination",
              learningOutcomes =
                listOf(
                  "Explain why supply slopes upward and separate own-price movement from input-cost shifts.",
                  "Solve algebraic linear demand and supply equations Qd = Qs simultaneously.",
                  "Analyze shortages from Maximum Price Ceilings, surpluses from Minimum Wage Floors, and Tax Incidence.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Law of Supply & Non-Price Shifters",
                    "Higher selling prices entice producers to expand output because marginal costs of overtime and extra raw inputs get covered. Factors that shift supply left include higher diesel/input costs, droughts, or excise taxes; technology and favourable subsidies shift supply right.",
                    "Q_s = c + dP \\quad (d > 0)",
                    "Joint supply occurs when refining crude oil produces petrol alongside kerosene; competitive supply splits finite farmland between yams and cassava.",
                    "Input-cost inflation squeezes profit margins at old prices, forcing firms to cut back supply at every price.",
                    "JAMB frequently distinguishes joint supply (beef & hides) from competitive supply (milk vs cheese uses).",
                    "Higher input costs shift Supply LEFT; cost-cutting tech shifts Supply RIGHT.",
                  ),
                  ecoConcept(
                    "2. Algebraic Market Equilibrium",
                    "Think of a Lagos market auction: if sellers ask too much, unsold stock piles up (Excess Supply = Qs − Qd > 0) pulling price down; if price sits too low, queues form (Excess Demand = Qd − Qs > 0) bidding price up until both match at equilibrium.",
                    "Q_d = Q_s \\implies a - bP^* = c + dP^*",
                    "Always plug your solved equilibrium price P* back into BOTH Qd and Qs to confirm identical quantities!",
                    "Surplus forces discounting; shortage creates upward bidding until excess disappears.",
                    "Nearly every JAMB paper features a pair like Qd = 40 − 2P and Qs = 10 + 4P.",
                    "Set Qd = Qs to get P*, then substitute P* to get Q*.",
                  ),
                  ecoConcept(
                    "3. Price Ceilings (Maximum) vs Price Floors (Minimum)",
                    "If a government caps rent or petrol BELOW equilibrium (Price Ceiling), buyers demand more while landlords/suppliers supply less—creating persistent queues, hoarding, and black markets. Conversely, a Minimum Price Floor ABOVE equilibrium (minimum agricultural price or wage) guarantees income yet creates excess supply/surplus.",
                    "P_{\\max} < P^* \\implies \\text{Shortage } (Q_d - Q_s) \\quad | \\quad P_{\\min} > P^* \\implies \\text{Surplus } (Q_s - Q_d)",
                    "A Price Ceiling set ABOVE equilibrium is non-binding and changes nothing!",
                    "Only a ceiling below market equilibrium or a floor above market equilibrium disrupts clearing.",
                    "WAEC asks why price controls often require rationing coupons or buffer-stock boards.",
                    "Effective Ceiling = below P* (shortage); Effective Floor = above P* (surplus).",
                  ),
                  ecoConcept(
                    "4. Tax Incidence & Elasticity Burden Sharing",
                    "Who truly pays a NGN 100 excise tax on soft drinks—the factory or you? Whoever has the MORE INELASTIC side absorbs the heavier share! Because fuel or medicine buyers cannot easily quit (|PED| is small), consumers bear most of the price hike.",
                    "\\text{Buyer Tax Share} \\propto \\frac{PES}{PES + |PED|}",
                    "If PED = 0 (perfectly inelastic vertical demand), consumers pay 100% of an indirect tax.",
                    "Whichever party is less flexible to adjust quantity ends up trapped bearing the tax wedge.",
                    "Equilibrium after per-unit tax t sets Qd(P) = Qs(P − t), shifting supply UP by t.",
                    "Inelastic side pays more tax; elastic side escapes more tax.",
                  ),
                  ecoConcept(
                    "5. Simultaneous Demand-Supply Shifts",
                    "If both household income rises (Demand right) AND bumper rain improves harvest (Supply right), Equilibrium Quantity definitely rises—yet Equilibrium Price might rise, fall, or stay flat depending on which shift is larger!",
                    "D\\uparrow \\&\\ S\\uparrow \\implies Q^*\\uparrow,\\ P^* \\text{ ambiguous}",
                    "Whenever Demand and Supply shift simultaneously, ONE of P* or Q* is certain while the other is indeterminate.",
                    "Draw two quick arrows: both push Q outward here, while D pulls P up and S pushes P down.",
                    "WAEC multiple-choice loves asking which equilibrium variable stays ambiguous under dual shifts.",
                    "Same direction shift -> Q certain; opposite direction shift -> P certain.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Solving Linear Market Equilibrium & Excess Supply",
                    "JAMB UTME-Style Worked Problem",
                    "Given Qd = 50 − 3P and Qs = 10 + 2P, find equilibrium price P*, quantity Q*, and market excess at P = 12.",
                    listOf(
                      "Step 1 — Equate Qd = Qs: 50 − 3P = 10 + 2P → 40 = 5P → P* = 8.",
                      "Step 2 — Substitute P* = 8: Q* = 50 − 3(8) = 26 units (check Qs = 10 + 16 = 26).",
                      "Step 3 — At P = 12: Qd = 50 − 36 = 14, Qs = 10 + 24 = 34 → Excess Supply = 34 − 14 = 20 units.",
                    ),
                    "P* = 8, Q* = 26; Excess supply of 20 units at P = 12",
                    "Writing +2P − 3P = −P instead of adding 3P across the equals sign.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Grain Buffer Stock Stabilization in Northern Grain Belts",
                    "Agricultural Policy",
                    "Buying excess maize at harvest minimum support prices stabilizes farmer incomes and releases grain during lean months.",
                    "Classic application of minimum price floors and government buffer storage.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
              labBrief = "Apply indirect tax per unit and watch buyer vs seller tax burden split live as elasticity shifts.",
              exercises =
                listOf(
                  q("ex_eco_03_1", "Equilibrium Algebra", "JAMB", 2022, "If Qd = 60 − 4P and Qs = 12 + 2P, find equilibrium price and quantity.", listOf("P = 8, Q = 28", "P = 12, Q = 12", "P = 6, Q = 36", "P = 10, Q = 20"), 0, "60 − 4P = 12 + 2P → 48 = 6P.", "P* = 8, Q* = 60 − 32 = 28."),
                  q("ex_eco_03_2", "Price Ceiling", "WAEC", 2023, "A government sets a legal maximum rent strictly below market equilibrium. The likely consequence is:", listOf("Housing surplus", "Chronic shortage, waiting lists, and parallel black-market fees", "Landlords building twice as many flats", "Instant fall in demand"), 1, "Ceiling below P* leaves Qd > Qs.", "Artificially low prices boost demand while discouraging supply."),
                  q("ex_eco_03_3", "Tax Incidence", "JAMB", 2021, "When demand for cigarettes is completely perfectly inelastic (PED = 0), a NGN 50 excise duty is paid:", listOf("Half by seller, half by buyer", "100% by the consumer", "100% by the producer", "Entirely by wholesalers"), 1, "Zero demand responsiveness passes all tax forward.", "Vertical demand absorbs full price hike."),
                  q("ex_eco_03_4", "Supply Shifts", "WAEC", 2020, "What shifts the supply curve of baked bread RIGHTWARD?", listOf("Higher wheat flour price", "New automated ovens reducing baking cost per loaf", "Higher bread excise tax", "Increase in consumer income"), 1, "Cost-reducing innovation boosts supply.", "Lower unit production cost shifts supply outward."),
                  q("ex_eco_03_5", "Price Floor", "JAMB", 2019, "A binding minimum agricultural guarantee set above equilibrium requires the marketing board to:", listOf("Import food", "Purchase and store the resulting unsold surplus", "Force farmers to cut harvest", "Print rationing cards"), 1, "At high P_min, Qs exceeds Qd.", "Buffer boards absorb surplus so farmers actually receive P_min."),
                  q("ex_eco_03_6", "Dual Shifts", "NECO", 2022, "If demand rises sharply while supply falls due to drought, equilibrium price will:", listOf("Definitely rise", "Definitely fall", "Remain constant", "Equal zero"), 0, "Both excess demand and reduced supply push price upward.", "Both D↑ and S↓ push P* higher; Q* is indeterminate."),
                ),
              prerequisites = listOf("PED concept", "Linear equation solving"),
              dependencySummary = "Supply and equilibrium determine competitive price-taking vs monopoly price-setting.",
              vocabulary =
                listOf(
                  VocabularyEntry("Tax Incidence", "Final distribution of indirect tax burden between buyer and seller.", "Who actually feels the squeeze.", "Depends on relative elasticity."),
                  VocabularyEntry("Joint Supply", "Two goods yielded naturally from one process (beef and leather).", "More cows slaughtered expands both.", "Different from competitive supply."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Minimum price floor is set below equilibrium", "Floor sounds low.", "A floor props price UP ABOVE P* (like a lower limit); a ceiling caps price DOWN BELOW P*.", "Visualize: ceiling stops rising UP (set below); floor stops falling DOWN (set above)."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Given Qd = 80 − 2P and Qs = 20 + 2P, find original P* and new equilibrium price after a specific tax of NGN 10 per unit on suppliers.",
                  "Intermediate",
                  5,
                  listOf("First solve 80 − 2P = 20 + 2P for P*.", "Replace P in supply with (P − 10) for seller net receipt.", "Re-solve 80 − 2P = 20 + 2(P − 10)."),
                  "Equate Qd to tax-adjusted supply Qs(P − t).",
                  "Original: 4P = 60 → P* = 15, Q* = 50. With tax t = 10: Qs' = 20 + 2(P − 10) = 2P. Setting 80 − 2P = 2P → 4P = 80 → P_new = 20 (buyers pay +5, sellers keep 10 = −5, splitting 50/50!).",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Fuel Excise & Commuter Incidence Brief",
                  "Policy Simulation",
                  "Evaluate why a per-litre fuel levy raises pump prices steeply while public bus substitutes remain scarce.",
                  listOf("Measure commuter elasticity", "Project new clearing price", "Suggest transit subsidy offsets"),
                  listOf("Correctly links low |PED| to high buyer tax burden", "Quantifies Qd = Qs clearing shift", "Explains black-market risks of arbitrary caps"),
                  "Because urban commuters face inelastic demand, fuel excise falls heavily on consumers unless public rail/BRT substitutes expand elasticity.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Clearing occurs at Qd=Qs; Ceilings<P* cause shortages; Floors>P* cause surpluses; inelastic party pays heavier tax.",
                  listOf("P*, Q*: Equilibrium price and quantity", "t: Specific per-unit indirect tax"),
                  listOf("Setting ceiling above P*", "Placing tax directly onto Qd instead of P − t inside Qs"),
                  "Inelastic = Stuck = Pays More Tax.",
                ),
            ),
            Lesson(
              id = "eco_04_production_division",
              moduleId = "eco_mod_2",
              subjectId = "economics",
              title = "Factors of Production, Division of Labour & Diminishing Returns",
              durationMinutes = 20,
              syllabusTag = "JAMB Sec. 4 • WAEC Production Theory",
              learningOutcomes =
                listOf(
                  "Match the four factors of production to their distinct factor rewards (Rent, Wages, Interest, Profit).",
                  "Contrast Internal and External Economies of Scale across growing industrial hubs.",
                  "Apply Adam Smith's Division of Labour and compute TP, AP, and MP under Diminishing Marginal Returns.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. The Four Factors & Factor Rewards",
                    "Producing any product blends four inputs: Land (all free gifts of nature, rewarded by RENT), Labour (human mental and physical effort, rewarded by WAGES), Capital (man-made tools/machines assisting further output, rewarded by INTEREST), and Entrepreneurship (risk-bearing decision coordination, rewarded by PROFIT).",
                    "\\text{Factors}: \\text{Land}\\to\\text{Rent},\\ \\text{Labour}\\to\\text{Wages},\\ \\text{Capital}\\to\\text{Interest},\\ \\text{Enterprise}\\to\\text{Profit}",
                    "Money notes alone are NOT real physical capital in production economics unless converted into machinery, warehouses, or equipment.",
                    "Only Entrepreneurial Profit can be positive, zero, or negative because the founder takes residual risk after paying all hired inputs.",
                    "JAMB classifies commercial oil reserves under Land, and factory sewing machines under Fixed Capital.",
                    "Land→Rent, Labour→Wages, Capital→Interest, Entrepreneur→Profit.",
                  ),
                  ecoConcept(
                    "2. Division of Labour & Its Limits",
                    "In an Aba shoe workshop, splitting work into sole cutting, upper stitching, lasting, and polishing raises dexterity and speed dramatically compared to one artisan crafting whole shoes alone. Yet extreme specialization brings monotony, high interdependence, and a strict limit set by the SIZE OF THE MARKET.",
                    "\\text{Efficiency} \\uparrow \\text{ via Specialization} \\quad \\text{bounded by Extent of Market}",
                    "A tiny village clinic cannot support 12 brain/eye sub-specialists—market size limits specialization.",
                    "Time saved switching tools plus machine automation drives per-unit cost downward.",
                    "WAEC Theory often asks students to state 3 advantages and 3 limitations of division of labour.",
                    "Specialization multiplies output per worker but stalls in narrow local markets.",
                  ),
                  ecoConcept(
                    "3. Short-Run Diminishing Returns (TP, AP, MP)",
                    "Picture adding extra cooks into one tiny school kitchen with fixed stoves: cooks 1 and 2 boost meals fast (Increasing MP), cook 3 adds less (Diminishing MP), and cook 6 bumps into others causing negative extra output (Negative MP)! Short run ALWAYS keeps at least one factor FIXED.",
                    "AP = \\frac{TP}{L} \\quad | \\quad MP = \\frac{\\Delta TP}{\\Delta L}",
                    "MP cuts AP at AP's EXACT MAXIMUM! While MP > AP, average product keeps rising; once MP < AP, average product declines.",
                    "Overcrowding the fixed factor (land or stoves) dilutes each variable worker's marginal contribution.",
                    "Memorize the 3 stages: Stage I (rising AP), Stage II (MP positive but declining—rational production zone!), Stage III (MP < 0).",
                    "Short run = fixed factor exists; Diminishing returns kicks in as MP falls.",
                  ),
                  ecoConcept(
                    "4. Internal vs External Economies of Scale",
                    "Unlike short-run diminishing returns, Long-Run Economies of Scale vary ALL inputs together! Internal economies come from a firm's own size (bulk discounts, specialist engineers, cheap bank loans). External economies benefit EVERY firm clustered in a hub like Nnewi auto parts or Computer Village (shared transport, trained artisans, parts suppliers).",
                    "LRAC\\downarrow \\implies \\text{Economies of Scale};\\ \\ LRAC\\uparrow \\implies \\text{Diseconomies}",
                    "Managerial red tape and communication bottlenecks cause Long-Run Diseconomies of Scale.",
                    "Spreading fixed research and machinery overheads over huge output lowers Long-Run Average Cost.",
                    "Distinguish firm-internal bulk buying from industry-wide cluster infrastructure.",
                    "Internal = firm grows big; External = whole industry cluster grows.",
                  ),
                  ecoConcept(
                    "5. Production Forms: Sole Trader, Partnership, PLC & Cooperatives",
                    "A Sole Proprietorship moves fast yet suffers UNLIMITED liability (personal house can settle shop debt!). Limited Liability Companies (Ltd / PLC) shield owners' personal wealth to share value and gain perpetual succession, while Cooperatives return surpluses proportionally to member patronage.",
                    "\\text{Shareholder Max Loss} = \\text{Subscribed Share Capital (Limited Liability)}",
                    "Public Limited Companies (PLC) sell shares to the general public via the stock exchange; Private Limited (Ltd) restricts share transfer.",
                    "Separate legal personality lets corporations borrow long-term and outlive individual founders.",
                    "WAEC checks why sole proprietors face capital scarcity while Public Corporations pursue social welfare over pure profit.",
                    "Limited Liability caps personal risk and unlocks large-scale equity financing.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Completing a Short-Run TP, AP, and MP Schedule",
                    "WAEC WASSCE-Style Worked Problem",
                    "With 3 workers Total Product TP = 60 bags; adding a 4th worker raises TP to 88 bags; a 5th raises TP to 100 bags. Find MP of the 4th and 5th workers and AP at 5 workers.",
                    listOf(
                      "Step 1 — MP(4th) = TP(4) − TP(3) = 88 − 60 = 28 bags.",
                      "Step 2 — MP(5th) = TP(5) − TP(4) = 100 − 88 = 12 bags (Diminishing marginal returns is active since 12 < 28).",
                      "Step 3 — AP(5) = TP(5) / 5 = 100 / 5 = 20 bags per worker.",
                    ),
                    "MP₄ = 28, MP₅ = 12, AP₅ = 20 bags",
                    "Forgetting to divide TP by ΔL when worker steps jump by 2 instead of 1.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Aba Leather Cluster External Economies",
                    "Nigerian Light Manufacturing",
                    "Thousands of independent footwear workshops share nearby buckle suppliers, diesel repair mechanics, and bulk logistics buyers.",
                    "Textbook illustration of localization of industry and external economies of scale.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
              labBrief = "Inspect supply shifts driven by scale economies and diminishing returns.",
              exercises =
                listOf(
                  q("ex_eco_04_1", "Factor Rewards", "JAMB", 2021, "Which factor reward can legitimately become zero or negative during a bad trade year?", listOf("Rent", "Contract wages", "Profit", "Fixed bank interest"), 2, "The entrepreneur bears residual uncertainty.", "Profit is residual surplus after contractual factor bills."),
                  q("ex_eco_04_2", "Diminishing Returns", "WAEC", 2022, "The Law of Diminishing Marginal Returns operates strictly in:", listOf("The long run when all inputs expand", "The short run where at least one input stays fixed", "Command economies only", "Monopolistic luxury retail"), 1, "Fixed capacity gets crowded by extra variable labour.", "Requires at least one fixed factor in the short run."),
                  q("ex_eco_04_3", "AP vs MP", "JAMB", 2023, "When Average Product (AP) reaches its maximum point:", listOf("MP equals zero", "MP equals AP", "TP equals zero", "MP exceeds AP by double"), 1, "Marginal cuts Average right at its peak.", "At maximum AP, MP = AP."),
                  q("ex_eco_04_4", "Division of Labour", "WAEC", 2019, "Which factor most strictly limits how far division of labour can be carried?", listOf("Size of the market", "Colour of uniforms", "Number of holidays", "Distance to sea"), 0, "Adam Smith's famous theorem.", "Specialization requires large enough demand to absorb output."),
                  q("ex_eco_04_5", "Economies of Scale", "JAMB", 2022, "A cluster benefit where new textile suppliers and transport hubs serve every factory in an area is an:", listOf("Internal financial economy", "External economy of scale", "Diseconomy of size", "Variable wage cost"), 1, "Shared across the whole industrial location.", "External economies accrue from industry concentration."),
                  q("ex_eco_04_6", "Business Units", "NECO", 2021, "Limited liability means a PLC shareholder risks losing at most:", listOf("Their entire home and car", "The amount invested/unpaid on their shares", "The company's total bank loan", "Their annual salary"), 1, "Personal assets remain shielded.", "Company debts stop at corporate equity."),
                ),
              prerequisites = listOf("Production possibility frontier"),
              dependencySummary = "TP/MP diminishing returns directly explain why Short-Run Marginal Cost slopes upward.",
              vocabulary =
                listOf(
                  VocabularyEntry("Short Run", "Planning period where at least one factor (land/plant) stays fixed.", "Can hire staff tomorrow, cannot build a new factory.", "Contrast with Long Run (all variable)."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Diminishing returns means Total Product falls immediately", "Diminishing sounds negative.", "Initially TP keeps rising at a SLOWER pace while MP drops but remains positive; TP only falls when MP < 0.", "Distinguish falling MP (Stage II) from falling TP (Stage III)."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "At 4 farmhands, TP = 120 kg cocoa; at 6 farmhands, TP = 156 kg. Calculate MP per added worker across this 2-worker jump.",
                  "Standard",
                  3,
                  listOf("Find ΔTP = 156 − 120.", "Find ΔL = 6 − 4 = 2.", "Divide ΔTP / ΔL."),
                  "Apply MP = ΔTP / ΔL whenever ΔL ≠ 1.",
                  "ΔTP = 36 kg, ΔL = 2 workers → MP = 36 / 2 = 18 kg per extra worker.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Agri-Tech Cooperative Scaling Charter",
                  "Enterprise Brief",
                  "Upgrade an unregistered Sole Trader rice mill into a Limited Liability Cooperative to qualify for bank equipment credit.",
                  listOf("Identify unlimited liability gaps", "Structure division of tasks", "Forecast external cluster gains"),
                  listOf("Contrasts sole trader vs limited liability risk", "Distinguishes internal vs external economies", "Computes AP and MP accurately"),
                  "Incorporation protects member farms from mill debts while shared dryer logistics lower drying costs per tonne across the cooperative.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "4 Factors (Land/Labour/Capital/Enterprise); Short-run diminishing returns drops MP first, then cuts AP at AP max.",
                  listOf("TP: Total Product", "AP = TP/L", "MP = ΔTP/ΔL"),
                  listOf("Calling Naira notes physical capital", "Thinking MP = AP happens when TP peaks (TP peaks at MP = 0!)"),
                  "AP peaks when MP meets AP; TP peaks when MP hits ZERO.",
                ),
            ),
          ),
      ),

      CourseModule(
        id = "eco_mod_3",
        subjectId = "economics",
        indexLabel = "Module 03",
        title = "Costs, Revenues, Market Structures & Public Finance",
        summary = "FC/VC/TC/MC cost curves, MR=MC profit rule, Competition vs Monopoly/Oligopoly & Taxation Canons.",
        lessons =
          listOf(
            Lesson(
              id = "eco_05_costs_revenue",
              moduleId = "eco_mod_3",
              subjectId = "economics",
              title = "Cost Curves (TC, AC, MC) & Profit Maximization",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 5 • WAEC Cost & Revenue Analysis",
              learningOutcomes =
                listOf(
                  "Calculate TFC, TVC, TC, AFC, AVC, ATC, and Marginal Cost from cost schedules.",
                  "Explain why MC intersects both AVC and ATC at their exact minimum points.",
                  "Apply the golden Profit Maximization rule MR = MC and shut-down point P = AVC.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Fixed Costs vs Variable Costs",
                    "Rent on an Abuja printing shop stays NGN 200,000 even if zero books print this month (Total Fixed Cost, TFC). Ink and paper rise with every extra copy printed (Total Variable Cost, TVC). Adding them gives Total Cost.",
                    "TC = TFC + TVC \\quad | \\quad ATC = AFC + AVC",
                    "Even at Q = 0 output, TC equals TFC (so TFC = TC at zero output!).",
                    "Average Fixed Cost (TFC/Q) falls continuously as overhead spreads across bigger batches—making a rectangular hyperbola.",
                    "JAMB cost tables always reveal TFC straight away from the Q = 0 row.",
                    "FC stays flat with Q; VC starts at zero and rises with output.",
                  ),
                  ecoConcept(
                    "2. Marginal Cost & the U-Shaped Relationship",
                    "Marginal Cost measures the extra cost of making ONE more unit (driven solely by Variable Cost, never Fixed Cost!). While MC < ATC, ATC falls; the instant MC exceeds ATC, it pulls ATC upward—crossing ATC right at its bottom.",
                    "MC = \\frac{\\Delta TC}{\\Delta Q} = \\frac{\\Delta TVC}{\\Delta Q}",
                    "Fixed costs NEVER enter Marginal Cost because ΔTFC = 0!",
                    "U-shaped Short-Run MC mirrors the inverse of Marginal Product: rising productivity drops MC; diminishing returns drives MC up.",
                    "WAEC checks why ATC − AVC = AFC shrinks steadily as Q increases.",
                    "MC cuts both AVC and ATC strictly at their lowest points.",
                  ),
                  ecoConcept(
                    "3. Total, Average & Marginal Revenue",
                    "If you sell bottled water at a flat NGN 200 price regardless of volume (Perfect Competition), every extra bottle brings NGN 200 extra revenue—so P = AR = MR horizontally. Under Monopoly, selling more requires cutting price, so MR slopes twice as steeply downward below Demand (AR).",
                    "TR = P \\times Q \\quad | \\quad AR = \\frac{TR}{Q} = P \\quad | \\quad MR = \\frac{\\Delta TR}{\\Delta Q}",
                    "Average Revenue (AR) is mathematically identical to Price (Demand curve)!",
                    "Lowering price on ALL previous units pulls a monopolist's MR below its selling price P.",
                    "TR peaks where MR = 0 (mid-point where |PED| = 1).",
                    "Price taker: P = AR = MR; Price maker: P = AR > MR.",
                  ),
                  ecoConcept(
                    "4. Profit Maximization (MR = MC) & Shut-Down Point",
                    "As long as making an extra sachet earns more revenue than its extra cost (MR > MC), produce it! Stop expanding right where MR = MC (while MC is rising). Abnormal Profit occurs if P > ATC; Normal Profit (zero economic profit!) occurs if P = ATC; and in the short run a firm stays open while P ≥ AVC, shutting down ONLY if P < AVC.",
                    "\\text{Max Profit}: MR = MC\\ (\\text{with } MC\\uparrow) \\quad | \\quad \\text{Shut down if } P < AVC",
                    "'Normal Profit' is an implicit cost INSIDE ATC—so earning Normal Profit means staying happily in business long-term!",
                    "If P covers variable running costs plus part of rent (P > AVC), staying open loses less than paying full rent with doors shut.",
                    "JAMB repeatedly asks for the exact shut-down threshold: P < AVC.",
                    "Equilibrium output sets MR = MC; continue short-run operations if P ≥ AVC.",
                  ),
                  ecoConcept(
                    "5. Accounting Profit vs Economic Profit",
                    "An accountant only subtracts explicit cash bills from revenue. An economist also subtracts IMPLICIT opportunity costs—like the salary you skipped and the interest your own capital could have earned in Treasury Bills.",
                    "\\text{Economic Profit} = TR - (\\text{Explicit Costs} + \\text{Implicit Opportunity Costs})",
                    "Positive accounting profit can still mean negative economic profit if your time could earn more elsewhere.",
                    "Zero economic profit (= Normal Profit) keeps resources exactly satisfied in their current industry.",
                    "Tested directly in both JAMB and WAEC Paper 2 cost definitions.",
                    "Economic Profit subtracts BOTH cash outlays and foregone owner opportunity costs.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Solving a Firm Cost Schedule & Break-Even Check",
                    "WAEC WASSCE-Style Worked Problem",
                    "At Q = 0, TC = NGN 400. At Q = 10, TC = NGN 900. At Q = 11, TC = NGN 980. Find TFC, TVC at Q=10, ATC at Q=10, and MC of the 11th unit.",
                    listOf(
                      "Step 1 — At Q = 0, TVC = 0 so TFC = 400.",
                      "Step 2 — At Q = 10: TVC = TC − TFC = 900 − 400 = NGN 500.",
                      "Step 3 — ATC(10) = 900 / 10 = NGN 90 per unit (AFC = 40, AVC = 50).",
                      "Step 4 — MC(11th) = 980 − 900 = NGN 80.",
                    ),
                    "TFC = 400; TVC₁₀ = 500; ATC₁₀ = 90; MC₁₁ = 80",
                    "Forgetting that TC at Q=0 represents Total Fixed Cost rather than zero!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Airline Off-Peak Seat Pricing & Shut-Down Logic",
                    "Transport & Aviation",
                    "Selling a standby flight ticket above jet-fuel and gate-fee AVC helps pay down fixed aircraft lease costs even during low season.",
                    "Proves why firms stay operating short-term as long as Price covers Average Variable Cost.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
              labBrief = "Explore how indirect cost shifts impact firm equilibrium output and revenue.",
              exercises =
                listOf(
                  q("ex_eco_05_1", "Fixed vs Variable", "JAMB", 2022, "If a firm produces zero output yet incurs NGN 120,000 in expenses, that sum equals:", listOf("Total Variable Cost", "Marginal Cost", "Total Fixed Cost", "Supernormal profit"), 2, "Only rent/fixed leases persist at Q = 0.", "At Q=0, TVC=0 so TC = TFC."),
                  q("ex_eco_05_2", "Cost Curves", "WAEC", 2023, "Which cost curve NEVER drops and continuously slopes downward toward zero as output grows?", listOf("Marginal Cost", "Average Fixed Cost (AFC)", "Total Fixed Cost", "Average Variable Cost"), 1, "TFC divided by larger Q shrinks continually.", "AFC = TFC / Q forms a declining rectangular hyperbola."),
                  q("ex_eco_05_3", "Profit Maximization", "JAMB", 2021, "Any profit-maximizing firm selects output where:", listOf("TR is maximum regardless of cost", "MR = MC while MC is rising", "AC = P only", "FC = VC"), 1, "Equate marginal revenue to rising marginal cost.", "MR = MC with MC upward sloping maximizes profit."),
                  q("ex_eco_05_4", "Shut-Down Rule", "WAEC", 2020, "A competitive firm should shut down operations in the short run when market price falls strictly below:", listOf("Average Total Cost (ATC)", "Average Fixed Cost (AFC)", "Average Variable Cost (AVC)", "Normal profit"), 2, "Must at least cover day-to-day running costs.", "P < AVC loses less by shutting down immediately."),
                  q("ex_eco_05_5", "Revenue Equivalence", "JAMB", 2019, "Under Perfect Competition, Average Revenue (AR) equals:", listOf("Total Revenue", "Marginal Cost only", "Market Price and Marginal Revenue", "Total Cost"), 2, "Price stays fixed per extra unit sold.", "P = AR = MR under price-taking competition."),
                  q("ex_eco_05_6", "Economic Profit", "NECO", 2022, "Economic profit differs from accounting profit because economists also deduct:", listOf("Sales tax twice", "Implicit opportunity costs of owner capital and time", "Depreciation twice", "Exports"), 1, "Include foregone best alternative return.", "Economic cost = Explicit + Implicit costs."),
                ),
              prerequisites = listOf("TP/MP relationships", "Algebraic graphs"),
              dependencySummary = "Cost curves + MR/AR shapes separate Perfect Competition from Monopoly in the next lesson.",
              vocabulary =
                listOf(
                  VocabularyEntry("Normal Profit", "Minimum profit keeping resources in their current use (P = ATC).", "Accounted inside ATC.", "Zero economic profit."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Firms maximize profit where Total Revenue peaks", "Highest sales sounds best.", "Highest sales ignores cost! Profit peaks at MR = MC (below max TR if MC > 0).", "Contrast revenue maximization (MR=0) with profit maximization (MR=MC)."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "A firm has TFC = NGN 600 and AVC = NGN 25 at Q = 30. Calculate TC and ATC at Q = 30.",
                  "Foundational",
                  3,
                  listOf("Compute TVC = AVC × Q.", "Add TFC + TVC for TC.", "Divide TC by 30 for ATC."),
                  "Use TC = TFC + AVC·Q.",
                  "TVC = 25 × 30 = 750. TC = 600 + 750 = NGN 1,350. ATC = 1,350 / 30 = NGN 45 (or AFC 20 + AVC 25 = 45).",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Bakery Break-Even & Short-Run Cost Audit",
                  "Cost Sheet",
                  "Analyze fixed oven rent vs variable flour cost for an Ilorin bread bakery.",
                  listOf("Classify fixed vs variable inputs", "Plot AFC dilution across loaves", "Pinpoint MR=MC batch size"),
                  listOf("Zero error separating TFC and TVC", "Identifies P ≥ AVC short-run survival rule", "States MR=MC optimal output"),
                  "Increasing daily loaf batches dilutes AFC rapidly until kitchen congestion pushes MC past selling price.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "TC = TFC + TVC; MC cuts AVC & ATC at min; max profit at MR = MC; shut down if P < AVC.",
                  listOf("TFC/TVC: Total Fixed/Variable Cost", "MC: Marginal Cost", "MR/AR: Marginal/Average Revenue"),
                  listOf("Including TFC inside MC", "Confusing MR = 0 (max TR) with MR = MC (max profit)"),
                  "Shut down < AVC; Long-run Normal = ATC; Max Profit = (MR = MC).",
                ),
            ),
            Lesson(
              id = "eco_06_market_structures",
              moduleId = "eco_mod_3",
              subjectId = "economics",
              title = "Perfect Competition, Monopoly, Oligopoly & Public Finance",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 6 & 9 • WAEC Market Structures & Taxation",
              learningOutcomes =
                listOf(
                  "Compare Perfect Competition, Monopoly, Monopolistic Competition, and Oligopoly across entry, price power, and long-run profit.",
                  "Explain why free entry erodes abnormal profits to Normal Profit under competition.",
                  "Classify Progressive, Proportional, and Regressive taxes and distinguish Fiscal vs Debt instruments.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Perfect Competition vs Monopoly",
                    "Think of open tomato stalls selling identical baskets (Perfect Competition: many tiny price takers, homogeneous product, zero entry barriers → long-run Normal Profit ONLY at P = min ATC!). Compare a single municipal water utility (Monopoly: sole seller, unique product, high barriers → can earn supernormal profit even in the long run).",
                    "\\text{Perf. Comp Long Run}: P = MR = MC = \\min ATC",
                    "No perfectly competitive firm can raise price independently above market equilibrium—its demand curve is perfectly horizontal!",
                    "If competitive sellers earn abnormal profits today, newcomers flood the market, pushing supply right until price falls back to minimum ATC.",
                    "Monopoly needs barriers to entry (patents, high fixed networks, exclusive state franchise) plus Price Discrimination feasibility across separated inelastic/elastic markets.",
                    "Competition kills long-run supernormal profit; Monopoly blocks entry to keep it.",
                  ),
                  ecoConcept(
                    "2. Monopolistic Competition & Oligopoly",
                    "Between those extremes live two real-world giants: Monopolistic Competition (hair salons, restaurants—many sellers using branding/packaging differentiation so each has mild downward demand yet normal long-run profit) and Oligopoly (cement or telecom giants—a FEW dominant firms whose pricing is mutually interdependent and rigid/'kinked').",
                    "\\text{Oligopoly Hallmark}: \\text{Few Dominant Firms + Interdependence + Non-Price Rivalry}",
                    "Oligopolists compete through advertising, data bonuses, and loyalty perks rather than brutal price wars; OPEC is a classic oil Cartel.",
                    "If one oligopolist cuts price, rivals follow fast; if it raises price, rivals stay put—producing price rigidity.",
                    "JAMB pairs Cement/Telecoms with Oligopoly and Retail Tailors/Restaurants with Monopolistic Competition.",
                    "Monopolistic = differentiated crowd; Oligopoly = interdependent giants.",
                  ),
                  ecoConcept(
                    "3. Direct vs Indirect Taxes & Progressive Canons",
                    "Adam Smith's canons demand equity, certainty, convenience, and economy. Direct taxes (PAYE income tax, Company Income Tax) hit earner income directly and cannot be shifted. Indirect taxes (VAT, customs, excise) hit spending and can be shifted into retail prices. Progressive taxes take a LARGER percentage as income rises; Regressive VAT takes a heavier percentage bite out of a poor worker's salary!",
                    "\\text{Progressive}: \\frac{\\text{Tax}}{\\text{Income}}\\uparrow \\text{ as } Y\\uparrow \\quad | \\quad \\text{Regressive}: \\frac{\\text{Tax}}{\\text{Income}}\\downarrow",
                    "Even a flat 7.5% VAT is REGRESSIVE against total income because lower-income earners spend nearly 100% of wages on taxed essentials.",
                    "Graduated income brackets narrow inequality while indirect consumption taxes raise reliable daily Treasury flow.",
                    "WAEC highlights PAYE as progressive direct tax vs flat consumption excises as regressive.",
                    "Direct = cannot shift; Indirect = shiftable via prices; Progressive narrows inequality.",
                  ),
                  ecoConcept(
                    "4. National Budget: Surplus, Balanced & Deficit Policy",
                    "During deep recession (idle factories, low spending), a Ministry of Finance deploys Expansionary Fiscal Policy: raising public infrastructure expenditure (G↑) and cutting income taxes (T↓) via a Budget Deficit to stimulate aggregate demand. During demand-pull overheating, a Budget Surplus cools spending.",
                    "\\text{Budget Deficit}: G > T \\quad | \\quad \\text{Budget Surplus}: T > G",
                    "Recurrent expenditure pays monthly salaries & upkeep; Capital expenditure builds bridges, hospitals, and power plants.",
                    "Public deficit spending multiplies household consumption across local contractors.",
                    "Distinguish productive infrastructure borrowing from wasteful recurrent deficit financing.",
                    "Use Fiscal Deficit against recession; use Surplus against overheating inflation.",
                  ),
                  ecoConcept(
                    "5. Monopoly Price Discrimination Conditions",
                    "A rail operator or cinema charges students lower fares than business executives for the exact same seat! Price discrimination only succeeds if: (1) seller holds monopoly power, (2) two buyer groups have DIFFERENT elasticities (charge the inelastic group higher!), and (3) NO resale/arbitrage is possible between groups.",
                    "P_{\\text{inelastic}} > P_{\\text{elastic}} \\quad (\\text{Zero Resale Arbitrage})",
                    "Without market separation stopping resale, cheap student tickets would immediately be resold to adults!",
                    "Equating MR₁ = MR₂ = MC maximizes combined segmented profit.",
                    "Standard WAEC Paper 2 three-condition question.",
                    "Monopoly power + differing |PED| + no resale = profitable price discrimination.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Classifying a Progressive vs Regressive Tax Schedule",
                    "WAEC WASSCE-Style Worked Problem",
                    "Kofi earns NGN 100,000 and pays NGN 10,000 tax. Amaka earns NGN 500,000 and pays NGN 25,000 tax. Classify this tax system.",
                    listOf(
                      "Step 1 — Kofi's effective tax rate = 10,000 / 100,000 = 10%.",
                      "Step 2 — Amaka's effective tax rate = 25,000 / 500,000 = 5%.",
                      "Step 3 — Although Amaka pays more Naira, her PERCENTAGE burden falls as income rises → Regressive tax!",
                    ),
                    "Regressive tax system (rate drops from 10% to 5%)",
                    "Assuming higher naira paid (25k > 10k) automatically makes a tax progressive—ALWAYS compare % rates!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Nigerian Telecoms & Cement Oligopolies",
                    "Industrial Organization",
                    "A handful of national carriers and cement producers use distribution reach, promos, and loyalty bundles rather than uncoordinated price drops.",
                    "Exemplifies non-price competition and strategic interdependence.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_ELASTICITY_MARKET_LAB,
              labBrief = "Compare buyer-seller incidence under competitive vs inelastic monopoly demand.",
              exercises =
                listOf(
                  q("ex_eco_06_1", "Perfect Competition", "JAMB", 2021, "Why can a perfectly competitive firm NOT earn supernormal profits in the long run?", listOf("Government confiscates revenue", "Free entry attracts new firms driving price down to minimum ATC", "Consumers boycott bargains", "Fixed costs become infinite"), 1, "Zero entry barriers bring new competitors whenever profits appear.", "Free entry shifts industry supply right until P = min ATC."),
                  q("ex_eco_06_2", "Oligopoly", "WAEC", 2022, "Which feature distinctly characterizes an oligopolistic market?", listOf("Thousands of tiny identical vendors", "A few dominant firms with mutual interdependence", "Single state utility", "Zero product branding"), 1, "Few sellers watching each rival closely.", "Oligopoly pairs few large firms with strategic interdependence."),
                  q("ex_eco_06_3", "Price Discrimination", "JAMB", 2023, "A monopolist practicing third-degree price discrimination charges the HIGHER price to the group with:", listOf("More elastic demand", "Less elastic (more inelastic) demand", "Lowest income", "Zero willingness to buy"), 1, "Inelastic buyers cannot switch easily.", "Higher markup targets the less price-sensitive segment."),
                  q("ex_eco_06_4", "Taxation Canons", "WAEC", 2021, "A tax whose percentage rate rises strictly as a taxpayer's income grows is:", listOf("Regressive", "Proportional", "Progressive", "Ad valorem excise"), 2, "Higher earners face higher % brackets.", "PAYE graduated income tax is progressive."),
                  q("ex_eco_06_5", "Fiscal Policy", "JAMB", 2020, "To combat severe unemployment and recession via fiscal policy, government should:", listOf("Raise income taxes sharply and cut roads", "Increase capital spending and cut direct taxes (Budget Deficit)", "Freeze central bank liquidity", "Ban exports"), 1, "Inject net aggregate demand.", "Expansionary fiscal policy uses G↑ and T↓."),
                  q("ex_eco_06_6", "Public Expenditure", "NECO", 2022, "Building an interstate railway and power substation falls under:", listOf("Recurrent expenditure", "Capital expenditure", "Transfer payments", "Hidden VAT"), 1, "Long-term physical asset creation.", "Infrastructure projects represent capital expenditure."),
                ),
              prerequisites = listOf("MR = MC equilibrium", "Elasticity concepts"),
              dependencySummary = "Fiscal deficits and public spending link directly into National Income (GDP) & Inflation.",
              vocabulary =
                listOf(
                  VocabularyEntry("Cartel", "Formal collusion among oligopolists (like OPEC) to restrict supply.", "Producers acting together like one giant monopoly.", "Quotas hold world price up."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Higher naira tax paid by rich = progressive", "Bigger amount feels progressive.", "Only if the PERCENTAGE of income paid increases with income is it progressive.", "Check Tax ÷ Income percentages first!"),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Explain two essential conditions required for an electricity utility to charge factories higher tariffs than residential households successfully.",
                  "Standard",
                  4,
                  listOf("Compare commercial vs household demand elasticity.", "Check whether factories can tap cheap residential meters (resale/arbitrage)."),
                  "Apply market power, elasticity gap, and ring-fencing against resale.",
                  "1. Monopoly power over grid connection. 2. Factories and homes have different price elasticities of demand. 3. Dedicated metering separates markets so cheap domestic power cannot be resold to factories.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "State Revenue & Tax Equity Reform Note",
                  "Fiscal Policy Brief",
                  "Advise a State Internal Revenue Service on balancing progressive PAYE property/income tiers vs regressive market levies.",
                  listOf("Test tax/income ratios across brackets", "Evaluate administrative collection cost", "Recommend progressive bracket adjustments"),
                  listOf("Computes effective tax % across earners", "Follows Smith's Economy & Equity canons", "Separates recurrent vs capital deployment"),
                  "Digital PAYE payroll collection lowers compliance costs while widening progressive bands spares low-wage traders from regressive flat tolls.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Perf Comp: Long-run Normal Profit; Monopoly: Barriers allow Supernormal Profit; Progressive Tax: Tax/Y rises with Y.",
                  listOf("ATC: Average Total Cost", "PAYE: Pay-As-You-Earn", "G > T: Fiscal Deficit"),
                  listOf("Thinking Perfect Comp firms earn long-run supernormal profit", "Mixing naira tax size with tax rate %"),
                  "Competition = Normal Long-Run; Oligopoly = Interdependent Few; Progressive = Rising % Rate.",
                ),
            ),
          ),
      ),
    )
}
