package com.example.data.curriculum

object EconomicsCurriculumPart2 {

  private fun ecoConcept(
    heading: String,
    explanation: String,
    keyFormula: String?,
    examTip: String,
    mechanism: String,
    intel: String,
    recap: String,
  ) = EconomicsCurriculum.ecoConcept(heading, explanation, keyFormula, examTip, mechanism, intel, recap)

  private fun q(
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
  ) = EconomicsCurriculum.q(id, topic, board, year, prompt, options, correct, hint, solution, scheme)

  val module4And5: List<CourseModule> =
    listOf(
      CourseModule(
        id = "eco_mod_4",
        subjectId = "economics",
        indexLabel = "Module 04",
        title = "National Income, Money, Banking & Inflation",
        summary = "GDP/GNP approaches, Keynesian multiplier, commercial vs CBN credit creation, money demand & inflation.",
        lessons =
          listOf(
            Lesson(
              id = "eco_07_national_income_multiplier",
              moduleId = "eco_mod_4",
              subjectId = "economics",
              title = "National Income Accounting & the Keynesian Multiplier",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 7 • WAEC National Income & Multiplier",
              learningOutcomes =
                listOf(
                  "Reconcile GDP, GNP, NNP, and Net National Income at market prices vs factor cost.",
                  "Apply Output, Income, and Expenditure methods without double counting intermediate inputs.",
                  "Compute equilibrium income changes using Marginal Propensity to Consume (MPC) and the Multiplier k.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. GDP vs GNP & Domestic vs National Boundaries",
                    "Gross Domestic Product (GDP) measures total final output produced WITHIN Nigeria's borders regardless of producer nationality. Add Net Factor Income from Abroad (NFIA = earnings by Nigerians abroad minus earnings by foreign firms remitted abroad) to get Gross National Product (GNP).",
                    "\\text{GNP} = \\text{GDP} + \\text{NFIA}",
                    "Oil/gas profits repatriated by foreign joint ventures count inside Nigeria's GDP, NOT inside GNP!",
                    "Geographical territory defines GDP ('Domestic'); citizenship/ownership of factors defines GNP ('National').",
                    "JAMB frequently gives GDP, inflows, and outflows and asks for GNP = GDP + Inflows − Outflows.",
                    "GDP = inside borders; GNP = GDP + Net Property/Factor Income from Abroad.",
                  ),
                  ecoConcept(
                    "2. Net Product, Factor Cost & Transfer Exclusions",
                    "Machines wear out every year (Depreciation / Capital Consumption). Subtracting depreciation converts Gross to Net (NNP = GNP − Depreciation). Subtracting Indirect Taxes and adding Subsidies converts Market Price into Factor Cost (National Income = NNP_FC). Exclude transfer payments (pensions, bursaries, pocket money) because no current output occurred.",
                    "\\text{NI} = \\text{NNP}_{\\text{FC}} = \\text{GNP}_{\\text{MP}} - \\text{Dep} - T_{\\text{ind}} + \\text{Sub}",
                    "Counting wheat flour AND baked bread separately commits Double Counting; sum ONLY final goods or Value Added at each stage.",
                    "Subsidies lower shelf prices below factor cost, while VAT raises market price above factor cost—reversing both reveals true factor earnings.",
                    "WAEC asks for two exclusions in income computation: transfer payments and illegal/unmarketed subsistence gaps.",
                    "Net = Gross − Depreciation; Factor Cost = Market Price − Indirect Tax + Subsidy.",
                  ),
                  ecoConcept(
                    "3. Three Equivalent Circular-Flow Approaches",
                    "Because every naira spent by buyers becomes income earned by workers and firms for newly produced goods, Output Value Added ≡ Factor Incomes ≡ Aggregate Expenditure: Y = C + I + G + (X − M).",
                    "Y = C + I + G + (X - M)",
                    "Use (X − M) carefully: when imports M exceed exports X, net exports subtract from aggregate expenditure.",
                    "Circular flow leaks via Saving (S), Taxes (T), and Imports (M) and injects back via Investment (I), Government Spending (G), and Exports (X).",
                    "Equilibrium income holds when total Injections equal total Leakages: I + G + X = S + T + M.",
                    "Income = Output = Expenditure = C + I + G + (X − M); Equilibrium when J = W.",
                  ),
                  ecoConcept(
                    "4. Consumption Function, MPC, MPS & Average Propensities",
                    "Households with zero income still spend autonomous subsistence amounts (a), then spend a fraction b = MPC of every extra disposable naira: C = a + bY. What is not consumed is saved: MPS = 1 − MPC.",
                    "MPC = \\frac{\\Delta C}{\\Delta Y}, \\quad MPS = \\frac{\\Delta S}{\\Delta Y}, \\quad MPC + MPS = 1",
                    "Do not confuse APC (total C / total Y) with MPC (change in C / change in Y).",
                    "As household income grows, subsistence needs stay modest while savings share rises, keeping 0 < MPC < 1.",
                    "NECO computes ΔS directly once given ΔY and MPC.",
                    "MPC + MPS = 1 always holds for disposable income changes.",
                  ),
                  ecoConcept(
                    "5. The Keynesian Investment Multiplier (k)",
                    "When a state government injects NGN 10B into a highway, contractors spend 80% (MPC = 0.8) on food/cement, whose sellers spend 80% again—rippling into NGN 50B total GDP! Multiplier k = 1 / (1 − MPC) = 1 / MPS.",
                    "k = \\frac{1}{1 - MPC} = \\frac{1}{MPS}, \\quad \\Delta Y = k \\cdot \\Delta I",
                    "Higher leakage (higher MPS or higher tax/import rates) shrinks the multiplier!",
                    "Geometric series 1 + b + b² + ... converges to 1 / (1 − b), magnifying initial injections across rounds.",
                    "Standard JAMB numerical: given C = 50 + 0.75Y and ΔI = 200, find ΔY = 4 × 200 = 800.",
                    "k = 1 / MPS; smaller savings leakage produces a larger national income ripple.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "GNP, NNP at Factor Cost & Multiplier Calculation",
                    "JAMB UTME-Style Worked Problem",
                    "Given GDP at market price = NGN 400B, NFIA = -NGN 25B, Depreciation = NGN 30B, Indirect Taxes = NGN 40B, Subsidies = NGN 15B, and MPC = 0.75. Compute (a) NNP at Factor Cost and (b) ΔY from ΔI = NGN 50B.",
                    listOf(
                      "Step 1 — GNP_MP = GDP_MP + NFIA = 400 + (-25) = NGN 375B.",
                      "Step 2 — NNP_MP = 375 - Depreciation (30) = NGN 345B.",
                      "Step 3 — NNP_FC = 345 - Indirect Taxes (40) + Subsidies (15) = NGN 320B.",
                      "Step 4 — k = 1 / (1 - 0.75) = 1 / 0.25 = 4 → ΔY = 4 × 50 = NGN 200B.",
                    ),
                    "NNP_FC = NGN 320B; ΔY = NGN 200B",
                    "Adding indirect taxes instead of subtracting them—market shelf price includes VAT, so strip VAT out to reach Factor Cost!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Kaduna Industrial Park Construction Ripple",
                    "Macroeconomic Growth",
                    "Upfront civil spending circulates from bricklayers to food vendors, transport operators, and textile shops across successive weekly paychecks.",
                    "Demonstrates multiplier k = 1 / (1 − MPC).",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
              labBrief = "Adjust MPC, fiscal injection ΔG/ΔI, and money reserve ratio to observe GDP multiplier waves and price pressure.",
              exercises =
                listOf(
                  q("ex_eco_07_1", "GNP vs GDP", "JAMB", 2021, "If Nigeria's GDP is NGN 500B, receipts from Nigerians abroad equal NGN 40B, and foreign profit outflows equal NGN 65B, what is GNP?", listOf("NGN 605B", "NGN 525B", "NGN 475B", "NGN 395B"), 2, "NFIA = Inflows − Outflows = 40 − 65 = −25.", "GNP = 500 + (40 − 65) = NGN 475B."),
                  q("ex_eco_07_2", "Double Counting", "WAEC", 2022, "Which method avoids double counting in National Income accounting?", listOf("Summing gross sales of wheat, flour, and bread", "Summing strictly Value Added at each production stage", "Including second-hand car sales", "Counting retirement pensions"), 1, "Count net value added, not intermediate turnover.", "Value Added excludes intermediate cost previously counted."),
                  q("ex_eco_07_3", "Keynesian Multiplier", "JAMB", 2023, "When MPC = 0.80, an autonomous investment rise of NGN 25M increases equilibrium National Income by:", listOf("NGN 20M", "NGN 31.25M", "NGN 100M", "NGN 125M"), 3, "k = 1 / (1 − 0.80) = 5.", "ΔY = 5 × 25M = NGN 125M."),
                  q("ex_eco_07_4", "Transfer Payments", "JAMB", 2020, "Which item is EXCLUDED from GDP calculations?", listOf("Teacher salaries", "Hospital surgeries", "Student NYSC stipend / pension transfer", "Factory cement output"), 2, "Transfers involve zero current goods produced.", "Transfer payments redistribute income without adding output."),
                  q("ex_eco_07_5", "Factor Cost", "WAEC", 2021, "To convert NNP at Market Prices into NNP at Factor Cost, one must:", listOf("Add Indirect Taxes and subtract Subsidies", "Subtract Indirect Taxes and add Subsidies", "Add Depreciation", "Subtract Exports"), 1, "Remove tax markup and restore subsidy support.", "NNP_FC = NNP_MP − Indirect Taxes + Subsidies."),
                  q("ex_eco_07_6", "Circular Flow", "NECO", 2022, "In an open four-sector economy, macroeconomic equilibrium requires total Injections equal to:", listOf("Total consumption only", "Saving + Taxes + Imports (S + T + M)", "Wages minus rent", "Indirect taxes alone"), 1, "Equilibrium equates J = W.", "Injections (I + G + X) = Withdrawals (S + T + M)."),
                ),
              prerequisites = listOf("Fiscal budget components", "Circular output logic"),
              dependencySummary = "Multiplied demand shifts directly feed into Central Bank monetary management and inflation control.",
              vocabulary =
                listOf(
                  VocabularyEntry("Marginal Propensity to Save (MPS)", "Fraction of extra disposable income saved rather than spent.", "1 − MPC.", "Determines multiplier k = 1 / MPS."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Higher MPC lowers the multiplier", "Spending more feels wasteful.", "Every naira spent becomes someone else's income—higher MPC INCREASES k!", "Recall k = 1 / (1 − MPC)."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Given C = 100 + 0.6Y and I = 220 in a closed economy, find equilibrium national income Y.",
                  "Standard",
                  4,
                  listOf("Set Y = C + I.", "Solve Y - 0.6Y = 320."),
                  "Equate planned expenditure to output.",
                  "Y = 100 + 0.6Y + 220 → 0.4Y = 320 → Y = 800.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "National Income & Stimulus Audit",
                  "Macroeconomic Brief",
                  "Compare a NGN 100B infrastructure injection across high-saving vs high-consumption states.",
                  listOf("Audit GDP-to-NNP_FC bridge", "Compute state multipliers k", "Flag import leakage risks"),
                  listOf("Accurate NFIA and depreciation deductions", "Correct 1/(1-MPC) derivation", "Identifies import leakages reducing k"),
                  "If 40% of stimulus leaks into imported machinery rather than local value added, domestic multiplier shrinks sharply.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "GNP = GDP + NFIA; NNP_FC = GNP − Dep − Tax_ind + Sub; Y = C+I+G+(X−M); k = 1/(1−MPC).",
                  listOf("NFIA: Net Factor Income from Abroad", "MPC: ΔC/ΔY", "MPS: ΔS/ΔY"),
                  listOf("Adding indirect taxes to reach factor cost", "Double counting intermediate raw materials"),
                  "J = W (I+G+X = S+T+M) & k = 1/MPS.",
                ),
            ),
            Lesson(
              id = "eco_08_money_banking_inflation",
              moduleId = "eco_mod_4",
              subjectId = "economics",
              title = "Money, Commercial Banking, CBN Policy & Inflation",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 8 • WAEC Financial Institutions & Inflation",
              learningOutcomes =
                listOf(
                  "State money's four core functions and Keynes' three motives for holding cash balances.",
                  "Calculate commercial bank credit creation using Cash Reserve Ratio (r) and 1/r.",
                  "Select Central Bank Open Market Operations, Bank Rate & Reserve tools to tame inflation.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Functions of Money & Liquidity Preference Motives",
                    "Think back to pure barter: finding someone who wants yam AND carries sandals ('double coincidence of wants') stalls trade. Money eliminates barter through four roles: Medium of Exchange, Unit of Account, Store of Value, and Standard of Deferred Payment. Keynes explained WHY we hold cash: Transaction motive (daily transport/food), Precautionary motive (medical emergencies), and Speculative motive (waiting for bond prices to drop/rates to rise).",
                    "M^d = L_1(Y) + L_2(r)",
                    "Only the Speculative money demand depends inversely on interest rate r; Transaction and Precautionary depend chiefly on income Y.",
                    "High bond interest rates raise the opportunity cost of idle cash, pulling money out of speculative wallets into bonds.",
                    "JAMB separates Functions of Money (standard deferred payment enables credit loans) from Qualities (portability, durability, homogeneity, acceptability).",
                    "Four functions defeat barter; Speculative cash falls as interest rate r rises.",
                  ),
                  ecoConcept(
                    "2. Quantity Theory of Money (MV = PT)",
                    "Irving Fisher linked Money Stock (M), Velocity of circulation (V), Price Level (P), and Transaction Volume (T): MV = PT. At full employment with stable V and T, doubling money supply M simply doubles the general price level P without producing more factories.",
                    "M \\cdot V = P \\cdot T \\implies P = \\frac{M V}{T}",
                    "Velocity V means how many times one naira note changes hands in a year.",
                    "Printing currency faster than real factory output T chases fixed goods, driving prices upward.",
                    "WAEC tests numerical P changes given M, V, and T.",
                    "MV = PT: excess money growth above real output growth drives prices up.",
                  ),
                  ecoConcept(
                    "3. Commercial Bank Credit Multiplier (1 / r)",
                    "Suppose you deposit NGN 100,000 into a commercial bank facing a CBN Cash Reserve Ratio r = 20% (0.20). The bank locks NGN 20,000 in vault reserves and lends NGN 80,000 to a trader, whose supplier re-deposits NGN 80,000 in Bank B—multiplying total deposits up to 5× (NGN 500,000) and new credit up to NGN 400,000!",
                    "\\text{Money Multiplier } m = \\frac{1}{r}, \\quad \\Delta D = \\frac{\\text{Initial Deposit}}{r}",
                    "Do NOT confuse Total Deposits created (1/r × Deposit) with NEW Credit Created (Total Deposits − Initial Deposit).",
                    "Fractional-reserve lending recycles excess reserves repeatedly unless borrowers hoard cash outside banks or banks hold extra idle reserves.",
                    "JAMB frequently gives Cash Ratio = 10% and initial deposit = NGN 5,000 → Total deposits NGN 50,000, new credit NGN 45,000.",
                    "Credit multiplier = 1 / r; raising Cash Reserve Ratio r shrinks bank lending.",
                  ),
                  ecoConcept(
                    "4. Central Bank (CBN) Quantitative & Qualitative Tools",
                    "Commercial banks aim for profit and liquidity; the Central Bank issues currency, acts as Lender of Last Resort, and steers inflation via: (1) Open Market Operations (selling Treasury Bills drains bank cash to curb inflation; buying T-Bills injects liquidity), (2) Bank / Monetary Policy Rate (raising MPR makes borrowing costlier), and (3) Cash Reserve Ratio (r↑ cools credit).",
                    "\\text{Anti-Inflation Mix}: \\text{Sell T-Bills (OMO)} + \\text{MPR}\\uparrow + r\\uparrow",
                    "Qualitative tools include Moral Suasion and Selective Credit Control targeting priority agriculture/manufacturing without hiking rates across the whole economy.",
                    "Selling government securities pulls excess naira out of commercial bank reserve accounts directly.",
                    "WAEC contrasts Commercial Banks (accept public retail deposits for profit) vs CBN (monopoly note issue, govt banker, no direct retail public accounts).",
                    "Fight inflation: Sell bonds (OMO), raise Bank Rate, raise Cash Reserve Ratio.",
                  ),
                  ecoConcept(
                    "5. Types, Causes & Remedies of Inflation",
                    "Demand-Pull inflation erupts when 'too much money chases too few goods' (budget deficits, rapid wage booms past full employment). Cost-Push inflation hits when rising diesel, imported raw materials, or union wages squeeze supply curves leftward (raising prices while output stagnates—Stagflation).",
                    "\\text{Inflation Rate} = \\frac{CPI_1 - CPI_0}{CPI_0} \\times 100\\%",
                    "Inflation hurts fixed-income pensioners and creditors (repaid in weaker naira) while benefitting debtors and equity/real-asset holders.",
                    "Monetarists cure demand inflation by contracting money growth; cost-push inflation requires supply-side power, transport, and productivity upgrades.",
                    "NECO often asks losers vs gainers during sustained inflation.",
                    "Demand-pull = excess spending; Cost-push = input shocks; CPI tracks rate.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Credit Creation & Fisher MV = PT Application",
                    "WAEC WASSCE-Style Worked Problem",
                    "A customer deposits NGN 20,000 in a banking system where the required reserve ratio is 25%. (a) Find maximum total deposits created and net new credit. (b) Explain what CBN should do in OMO if inflation spikes.",
                    listOf(
                      "Step 1 — Reserve ratio r = 0.25 → Multiplier m = 1 / 0.25 = 4.",
                      "Step 2 — Maximum Total Deposits = 4 × 20,000 = NGN 80,000.",
                      "Step 3 — Net New Credit Created = 80,000 − 20,000 = NGN 60,000.",
                      "Step 4 — To curb inflation, CBN SELLS government Treasury Bills via OMO, shrinking commercial bank reserves.",
                    ),
                    "Total Deposits = NGN 80,000; Net New Credit = NGN 60,000; Sell T-Bills",
                    "Reporting NGN 80,000 as 'new credit created' instead of deducting the NGN 20,000 original deposit.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "CBN Monetary Policy Committee (MPC) Rate Decisions",
                    "Central Banking",
                    "When headline food and fuel CPI climbs rapidly, CBN hikes the Monetary Policy Rate and Liquidity Ratio to anchor naira stability and cool excess credit.",
                    "Real Nigerian application of MPR and CRR tightening.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
              labBrief = "Dial Cash Reserve Ratio r and Money Supply M to test bank deposit expansion (1/r) and MV=PT price level.",
              exercises =
                listOf(
                  q("ex_eco_08_1", "Bank Multiplier", "JAMB", 2022, "If the statutory cash reserve ratio is 10%, an initial bank deposit of NGN 50,000 can generate maximum total deposits of:", listOf("NGN 5,000", "NGN 55,000", "NGN 450,000", "NGN 500,000"), 3, "m = 1 / 0.10 = 10.", "10 × NGN 50,000 = NGN 500,000."),
                  q("ex_eco_08_2", "OMO Contraction", "WAEC", 2021, "During severe demand-pull inflation, the Central Bank uses Open Market Operations by:", listOf("Buying Treasury Bills from the public", "Selling Treasury Bills/bonds to banks and the public", "Printing higher denominations", "Abolishing the reserve ratio"), 1, "Selling securities withdraws excess naira cash.", "CBN sells securities to mop up excess liquidity."),
                  q("ex_eco_08_3", "Keynes Motives", "JAMB", 2023, "Which Keynesian motive for holding money varies inversely with interest rates?", listOf("Transactionary motive", "Precautionary motive", "Speculative motive", "Barter motive"), 2, "Cash vs interest-bearing bonds.", "Speculative demand drops when bond yields rise."),
                  q("ex_eco_08_4", "Fisher Equation", "JAMB", 2020, "In Fisher's equation MV = PT, if M = 200, V = 4, and T = 100, what is the Price level P?", listOf("2", "8", "12.5", "50"), 1, "P = MV / T.", "P = (200 × 4) / 100 = 8."),
                  q("ex_eco_08_5", "Inflation Impact", "WAEC", 2022, "Who suffers the sharpest loss in real purchasing power during rapid unanticipated inflation?", listOf("Fixed-salary pensioners and lenders", "Borrowers at fixed nominal interest", "Owners of physical real estate", "Equity shareholders"), 0, "Fixed naira receipts buy fewer goods.", "Pensioners & creditors lose real value when currency weakens."),
                  q("ex_eco_08_6", "Central Bank Role", "NECO", 2021, "Acting as 'Lender of Last Resort' means the Central Bank:", listOf("Grants farm mortgages to individuals", "Provides emergency liquidity to sound commercial banks facing short-term runs", "Fixes shop retail prices", "Audits private household budgets"), 1, "Prevents systemic banking panics.", "CBN backstops solvent commercial banks during liquidity squeezes."),
                ),
              prerequisites = listOf("National Income equilibrium"),
              dependencySummary = "Domestic inflation & money rates directly affect Exchange Rates and Balance of Payments in Module 5.",
              vocabulary =
                listOf(
                  VocabularyEntry("Lender of Last Resort", "CBN duty to supply emergency cash to solvent commercial banks under temporary liquidity stress.", "Safety net stopping bank runs.", "Distinguishes CBN from profit-driven retail banks."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Central Bank buys bonds to fight inflation", "Feels like 'Central Bank taking action'.", "Buying bonds PAYS CASH into the economy, worsening inflation—CBN must SELL bonds to mop cash up!", "Sell = Suck cash out; Buy = Boost cash in."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "With initial cash deposit NGN 12,000 and reserve ratio 20%, calculate net new credit created (excluding the original deposit).",
                  "Standard",
                  3,
                  listOf("m = 1 / 0.20 = 5.", "Total deposits = 5 × 12,000; subtract initial 12,000."),
                  "Compute total expansion minus initial deposit.",
                  "Total = 5 × 12,000 = NGN 60,000; Net New Credit = 60,000 − 12,000 = NGN 48,000.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "CBN Monetary Stabilization Brief",
                  "Policy Simulation Report",
                  "Inflation reaches 24% alongside rapid credit expansion. Draft a 3-tool stabilization package.",
                  listOf("Set CRR and Bank Rate direction", "Design OMO Treasury Bill auction", "Address cost-push supply bottlenecks"),
                  listOf("Uses OMO selling + rate hike correctly", "Calculates reserve multiplier contraction", "Balances demand tightening with supply relief"),
                  "Combine OMO T-bill sales and +150 bps MPR with FX/power logistics relief so tightening cools excess credit without crushing SME output.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "MV = PT; Deposit Multiplier = 1/r; Anti-Inflation = Sell Bonds (OMO) + Raise MPR + Raise CRR.",
                  listOf("r: Cash Reserve Ratio", "MPR: Monetary Policy Rate", "CPI: Consumer Price Index"),
                  listOf("Mixing Total Deposits with New Credit Created", "Thinking CBN buys bonds to curb inflation"),
                  "Sell = Suck Liquidity; Cred-Loss = Fixed-Income Pensioners.",
                ),
            ),
          ),
      ),
      CourseModule(
        id = "eco_mod_5",
        subjectId = "economics",
        indexLabel = "Module 05",
        title = "International Trade, Balance of Payments & Economic Growth",
        summary = "Comparative advantage, Terms of Trade, protectionism, BoP accounting, ECOWAS & growth vs development.",
        lessons =
          listOf(
            Lesson(
              id = "eco_09_intl_trade_bop",
              moduleId = "eco_mod_5",
              subjectId = "economics",
              title = "Comparative Advantage, Exchange Rates & Balance of Payments",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 10 • WAEC International Trade & BoP",
              learningOutcomes =
                listOf(
                  "Prove mutual trade gains using David Ricardo's Law of Comparative Cost Advantage.",
                  "Compute Terms of Trade (ToT) index and analyze tariff vs quota trade barriers.",
                  "Classify Balance of Payments Visible, Invisible, Current, and Capital Account deficits.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Absolute vs Comparative Cost Advantage",
                    "Suppose a senior lawyer types faster than her clerk (Absolute Advantage in both typing and advocacy). Should she type all letters herself? No—her hourly opportunity cost of leaving courtroom advocacy is huge! Ricardo proved countries should specialize where their OPPORTUNITY COST is lowest (Comparative Advantage), even if one nation produces everything more efficiently.",
                    "\\text{Country A specializes in Good } X \\text{ iff } OC_A(X) < OC_B(X)",
                    "Compute the ratio of units sacrificed per unit gained—NEVER look solely at raw total tons produced!",
                    "Trading between domestic opportunity-cost ratios places both partners strictly outside their solitary PPC frontiers.",
                    "Classic JAMB table gives Cocoa vs Textiles output in Ghana and Nigeria—always calculate unit opportunity cost first.",
                    "Absolute = more output per worker; Comparative = lower domestic sacrifice ratio.",
                  ),
                  ecoConcept(
                    "2. Terms of Trade (ToT) & Tariff vs Quota",
                    "Terms of Trade compares a country's average export price index to its import price index. If export prices climb faster than import prices, ToT improves (favorable)—fewer cocoa bags buy one tractor.",
                    "ToT = \\frac{\\text{Index of Export Prices}}{\\text{Index of Import Prices}} \\times 100",
                    "Tariffs are customs TAXES raising import prices while collecting Treasury revenue; Quotas cap physical import QUANTITY without directly collecting customs duty unless licenses are auctioned.",
                    "Developing nations protect Infant Industries temporarily, though long-term protection breeds domestic complacency.",
                    "JAMB numericals give Export Index = 120, Import Index = 150 → ToT = 80 (unfavorable < 100).",
                    "ToT > 100 means favorable import purchasing power; Tariff taxes price, Quota caps volume.",
                  ),
                  ecoConcept(
                    "3. Balance of Trade vs Balance of Payments (BoP)",
                    "Balance of Trade covers strictly VISIBLE physical merchandise (crude oil exports minus machine imports). Balance of Payments is the COMPLETE ledger of all international transactions: Current Account (Visibles + Invisible services like shipping/insurance/tourism + remittances) PLUS Capital & Financial Account (FDI, external loans) plus Reserve Assets.",
                    "\\text{Current Account} = \\text{Visible Trade} + \\text{Invisible Services} + \\text{Transfers}",
                    "Even with a Visible Trade SURPLUS (oil exports > goods imports), heavy shipping, banking, and foreign medical/travel payouts can tip the Current Account into deficit!",
                    "True BoP always balances on paper via official reserves/IMF accommodations—when textbooks speak of a 'BoP deficit', they mean autonomous outflows exceed inflows, draining FX reserves.",
                    "Remedies for chronic BoP deficit include currency devaluation (when Marshall-Lerner |PED_x| + |PED_m| > 1), export diversification, and import substitution.",
                    "BoT = physical goods only; BoP = Current (goods + invisibles) + Capital Account.",
                  ),
                  ecoConcept(
                    "4. Flexible vs Fixed Exchange Rates & Devaluation",
                    "Under a Floating (Flexible) regime, market FX supply and demand shift exchange rate daily (Depreciation vs Appreciation). Under a Pegged/Fixed regime, the Central Bank deliberately lowers official parity (Devaluation) to make exports cheaper abroad in USD and foreign imports costlier in Naira.",
                    "\\text{Devaluation succeeds iff } |PED_{\\text{exports}}| + |PED_{\\text{imports}}| > 1",
                    "If domestic export and import demands are price-inelastic, devaluation shrinks export revenue and spikes import bills ('J-curve' / worsening deficit).",
                    "Cheaper export prices expand foreign volume only when foreign buyers respond elastically.",
                    "Distinguish Devaluation (official policy cut under fixed peg) from Depreciation (market supply-demand drop under float).",
                    "Depreciation = market-driven; Devaluation = official cut (works best with elastic trade).",
                  ),
                  ecoConcept(
                    "5. Regional & Multilateral Economic Integration",
                    "Trade blocs progress in stages: Free Trade Area (zero internal tariffs) → Customs Union (plus Common External Tariff) → Common Market (plus free labour/capital mobility like ECOWAS vision) → Economic Union (monetary/fiscal harmonization like Eurozone). Key bodies: ECOWAS, AfCFTA, IMF (short-term BoP relief), World Bank/IBRD (long-term infrastructure loans).",
                    "\\text{FTA} \\subset \\text{Customs Union} \\subset \\text{Common Market} \\subset \\text{Monetary Union}",
                    "IMF stabilizes short-term FX/BoP crises; World Bank finances multi-decade roads, dams, and education.",
                    "Removing internal African border tariffs under AfCFTA expands economies of scale for regional manufacturing.",
                    "JAMB separates IMF (short-term BoP stabilization) from IBRD/World Bank (long-term reconstruction & development).",
                    "IMF = short-run BoP stability; World Bank = long-run projects; ECOWAS/AfCFTA = regional markets.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Comparative Cost Table & Terms of Trade",
                    "JAMB UTME-Style Worked Problem",
                    "Country X produces 100 units of Rice or 50 units of Cloth with equal resources. Country Y produces 80 Rice or 20 Cloth. Also, Export Price Index = 135 while Import Price Index = 112.5. Find which good X exports and calculate ToT.",
                    listOf(
                      "Step 1 — Opportunity cost of 1 Cloth in X = 100 / 50 = 2 Rice; in Y = 80 / 20 = 4 Rice → X has LOWER OC in Cloth!",
                      "Step 2 — Opportunity cost of 1 Rice in X = 50 / 100 = 0.5 Cloth; in Y = 20 / 80 = 0.25 Cloth → Y exports Rice, X exports Cloth.",
                      "Step 3 — ToT = (135 / 112.5) × 100 = 120 (favorable since 120 > 100).",
                    ),
                    "Country X exports Cloth, Country Y exports Rice; ToT = 120",
                    "Concluding Country X shouldn't trade because X produces more of BOTH goods (100 > 80 and 50 > 20)—comparative ratios govern mutual gains!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Lagos-Abidjan Corridor & AfCFTA Tariff Removal",
                    "Regional Trade Integration",
                    "Harmonized customs standards lower border delays for processed foods and plastics shipped across West Africa.",
                    "Illustrates Trade Creation inside a Customs Union.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
              labBrief = "Explore how export price shifts and policy demand shocks alter Terms of Trade and Macroeconomic output.",
              exercises =
                listOf(
                  q("ex_eco_09_1", "Comparative Advantage", "JAMB", 2022, "Even when Nation A produces all goods with fewer workers than Nation B, mutually beneficial trade holds under:", listOf("Absolute Advantage", "Comparative Cost Advantage", "Gold Standard", "Mercantilism"), 1, "David Ricardo's relative opportunity cost rule.", "Lower domestic opportunity cost drives profitable specialization."),
                  q("ex_eco_09_2", "Terms of Trade", "WAEC", 2021, "If Export Price Index drops from 100 to 90 while Import Price Index rises from 100 to 120, Terms of Trade is:", listOf("133.3 (Favorable)", "75.0 (Unfavorable)", "90.0", "30.0"), 1, "ToT = (Px / Pm) × 100.", "(90 / 120) × 100 = 75.0 (< 100 unfavorable)."),
                  q("ex_eco_09_3", "Balance of Trade vs BoP", "JAMB", 2023, "Maritime freight fees, banking insurance, and tourism spending enter the Balance of Payments under:", listOf("Visible trade", "Capital reserves", "Invisible items in Current Account", "Customs tariff"), 2, "Intangible services traded across borders.", "Services belong to Invisible items in the Current Account."),
                  q("ex_eco_09_4", "IMF vs World Bank", "WAEC", 2022, "Which institution primarily extends short-term stabilization credit to correct Balance of Payments deficits?", listOf("World Bank (IBRD)", "International Monetary Fund (IMF)", "UNESCO", "ECOWAS Bank"), 1, "Short-term currency & BoP balance support.", "IMF provides short-run BoP stabilization; IBRD funds long-term projects."),
                  q("ex_eco_09_5", "Tariffs vs Quotas", "JAMB", 2020, "Unlike an import quota, an import tariff directly generates:", listOf("Physical limits per port", "Tax revenue for the government treasury", "Free labour movement", "Guaranteed domestic inflation fall"), 1, "Customs duty enters Treasury.", "Tariffs tax price per unit imported, yielding customs revenue."),
                  q("ex_eco_09_6", "Economic Union", "NECO", 2021, "A regional bloc combining internal free trade, a Common External Tariff, AND free mobility of labour/capital is a:", listOf("Free Trade Area", "Customs Union", "Common Market", "Preferential Zone"), 2, "Factor mobility defines Common Market.", "Common Market adds free movement of labour & capital over Customs Union."),
                ),
              prerequisites = listOf("Opportunity cost", "Elasticity & currency markets"),
              dependencySummary = "Foreign exchange stability and export diversification underpin sustainable Growth & Development.",
              vocabulary =
                listOf(
                  VocabularyEntry("Devaluation", "Official downward adjustment of a pegged currency's exchange rate relative to foreign currencies.", "Cheaper exports, costlier imports.", "Effective when trade elasticities exceed 1."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Balance of Trade = Balance of Payments", "Both count international money.", "BoT covers ONLY physical goods; BoP adds services (invisibles), transfers, capital flows, and FX reserves.", "BoT is only ONE line inside BoP Current Account."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Nigeria produces 60 Cocoa or 30 Shoes per day; Benin produces 20 Cocoa or 20 Shoes per day. State each nation's opportunity cost of 1 Shoe and identify the Shoe exporter.",
                  "Standard",
                  4,
                  listOf("Nigeria: 1 Shoe sacrifices 60/30 = 2 Cocoa.", "Benin: 1 Shoe sacrifices 20/20 = 1 Cocoa."),
                  "Select whichever country gives up less Cocoa per Shoe.",
                  "Nigeria OC(1 Shoe) = 2 Cocoa; Benin OC(1 Shoe) = 1 Cocoa. Benin has lower cost in Shoes (exports Shoes); Nigeria exports Cocoa.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Export Diversification & BoP Recovery Plan",
                  "Trade Policy Brief",
                  "Examine why relying >80% on raw crude exports leaves Nigeria's Current Account vulnerable to global price swings.",
                  listOf("Compute ToT vulnerability to crude slumps", "Contrast tariff protection vs local power/rail upgrades", "Map non-oil AfCFTA exports"),
                  listOf("Separates Visible surplus vs Service invisible deficit", "Applies comparative advantage to agro-processing", "Avoids chronic infant-industry inefficiency"),
                  "Exporting cocoa butter, refined petrochemicals, and fintech services stabilizes ToT and reduces invisible shipping/refinery outflows.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Compare OC ratios for Ricardo trade; ToT = (Px/Pm)×100; Current Acct = Visibles + Invisibles + Transfers.",
                  listOf("ToT: Terms of Trade", "BoT: Visible Goods Balance", "BoP: Full International Ledger"),
                  listOf("Using raw output instead of Opportunity Cost ratio", "Thinking devaluation boosts revenue with inelastic demand"),
                  "Lower OC Exports; ToT > 100 Favorable; IMF = Short-Run BoP.",
                ),
            ),
            Lesson(
              id = "eco_10_growth_development",
              moduleId = "eco_mod_5",
              subjectId = "economics",
              title = "Economic Growth, Development Planning & Petroleum Economics",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 11–12 • WAEC Development, Agriculture & Petroleum",
              learningOutcomes =
                listOf(
                  "Contrast quantitative Economic Growth with qualitative Economic Development (HDI, Gini).",
                  "Diagnose structural obstacles facing developing economies (dualism, primary commodity trap, infrastructure).",
                  "Evaluate Agriculture's historical roles, NNPC/downstream refining economics, and National Development Plans.",
                ),
              concepts =
                listOf(
                  ecoConcept(
                    "1. Economic Growth vs Economic Development",
                    "Think of a teenager growing four inches taller vs maturing in strength, education, and health. Economic Growth is purely a quantitative rise in Real GDP per capita (output expands). Economic Development adds structural transformation: higher literacy, longer life expectancy, reliable clean water, lower poverty, and narrower inequality (Human Development Index — HDI).",
                    "\\text{Real GDP Growth Rate} = \\frac{GDP_1 - GDP_0}{GDP_0} \\times 100\\%",
                    "A country can achieve 7% GDP growth driven by oil enclaves WITHOUT Development if poverty, illiteracy, and infant mortality worsen!",
                    "Inclusive development channels capital surplus into human health, technical schooling, electricity, and manufacturing diversification.",
                    "JAMB separates HDI components: Longevity (life expectancy), Education (mean/expected schooling), and Standard of Living (GNI per capita PPP).",
                    "Growth = bigger output number; Development = growth + better living standards & structural shift.",
                  ),
                  ecoConcept(
                    "2. Structural Features of Developing Economies",
                    "Developing nations frequently exhibit Economic Dualism (a sleek urban fintech/oil enclave beside low-yield rural hoe farming), high dependency ratios (large under-15 populations), raw primary-product export dependence, weak power/rail grids, and the Vicious Circle of Poverty (low income → low saving → low investment → low productivity → low income).",
                    "\\text{Low } Y \\implies \\text{Low } S \\implies \\text{Low } I \\implies \\text{Low Productivity}",
                    "Breaking the poverty circle needs coordinated human-capital investment, secure property/credit access, and stable macroeconomic institutions.",
                    "Rapid population growth above GDP growth actually lowers GDP per head even while total GDP rises.",
                    "WAEC asks for 4 causes of low capital formation: low income, conspicuous consumption, capital flight, and weak credit intermediation.",
                    "Break low-saving loops via reliable infrastructure, credit access, and education.",
                  ),
                  ecoConcept(
                    "3. Role of Agriculture & Agro-Industrial Transition",
                    "Agriculture feeds households, supplies agro-raw materials (cocoa, cotton, palm oil, rubber) to local factories, earns foreign exchange, and frees rural labour as mechanization lifts productivity. Nigeria shifted from 1960s regional cash-crop leadership after the 1970s Oil Boom triggered 'Dutch Disease' (neglecting farms while surging FX imports).",
                    "\\text{Real Per-Capita Growth} \\approx \\%\\Delta GDP - \\%\\Delta \\text{Population}",
                    "Mechanized storage (silos, cold chains) plus rural feeder roads cut post-harvest spoilage dramatically.",
                    "Industrialization succeeds fastest when anchored on domestic agro-processing value chains rather than isolated import assembly.",
                    "NECO often links rural-urban migration directly to urban unemployment and farm labour shortages.",
                    "Modernized agriculture supplies food, factory inputs, FX, and rural jobs.",
                  ),
                  ecoConcept(
                    "4. Petroleum Economics, NNPC & Local Refining",
                    "Crude petroleum supplies >80% of Nigeria's export earnings and public FX inflows, yet exporting crude only to re-import petrol/diesel forfeits refining value added, petrochemical feedstocks (plastics, fertilizers), and thousands of engineering jobs. Downstream domestic refining eliminates FX transport drain and stabilizes domestic fuel supply.",
                    "\\text{Value Added} = \\text{Refined Products} - \\text{Crude Input Cost}",
                    "Oil is an exhaustible non-renewable asset: sovereign savings funds (like Excess Crude/SWF) convert volatile booms into lasting physical assets.",
                    "Diversifying into natural gas liquefaction (LNG), petrochemicals, and solid minerals shields budgets from oil-price crashes.",
                    "JAMB lists NNPC joint ventures, upstream exploration/extraction vs downstream refining/marketing.",
                    "Upstream = drilling crude; Downstream = refining/petrochemicals; diversify revenue.",
                  ),
                  ecoConcept(
                    "5. Economic Development Planning & Objectives",
                    "Development planning sets coordinated multi-year public targets (First Plan 1962–68 through Vision blueprints) to avoid ad-hoc spending, allocate capital toward power/roads/schools, reduce regional imbalance, and stabilize prices. Plans fail when data is unreliable, projects lack continuity across administrations, or inflation/corruption inflates costs.",
                    "\\text{Dependency Ratio} = \\frac{\\text{Pop}_{0-14} + \\text{Pop}_{65+}}{\\text{Pop}_{15-64}} \\times 100\\%",
                    "Distinguish Rolling Plans (revised annually for rolling 3-year horizons) from Fixed Medium-Term Plans.",
                    "Clear project monitoring, credible statistics, and merit-based procurement bridge planning paper into actual factories and clinics.",
                    "Standard WAEC Paper 2 topic: objectives vs obstacles of Nigerian National Development Plans.",
                    "Planning aligns scarce public funds; execution fails without data, continuity & discipline.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Per-Capita Real Growth & Development Diagnosis",
                    "WAEC WASSCE-Style Worked Problem",
                    "Country Z's Real GDP expanded by 5.5% in a year while population grew by 3.0%, yet Gini inequality climbed from 0.38 to 0.51 and rural electrification fell. Calculate approximate per-capita growth and evaluate whether Economic Development occurred.",
                    listOf(
                      "Step 1 — Approximate Real GDP per-capita growth ≈ 5.5% − 3.0% = +2.5%.",
                      "Step 2 — Evaluate living standards: higher income concentration (Gini ↑) plus falling rural electricity undermine broad wellbeing.",
                      "Step 3 — Conclusion: Economic GROWTH occurred (+5.5%), NOT holistic Economic Development.",
                    ),
                    "+2.5% per-capita growth; Growth without equitable Development",
                    "Calling any GDP jump 'economic development' while ignoring income distribution, jobs, and basic services.",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Nigeria's Domestic Refining & Petrochemical Push",
                    "Resource Value Addition",
                    "Converting crude into domestic PMS, diesel, jet fuel, and urea fertilizer keeps refining margins onshore and saves scarce FX.",
                    "Demonstrates downstream forward-linkage industrialization.",
                  ),
                ),
              labType = PracticalLabType.ECONOMICS_MACRO_POLICY_LAB,
              labBrief = "Simulate investment, MPC, and macro balance shifts impacting long-run real output vs price stability.",
              exercises =
                listOf(
                  q("ex_eco_10_1", "Growth vs Development", "JAMB", 2022, "Which statement accurately differentiates Economic Development from Economic Growth?", listOf("Development measures strictly money supply expansion", "Development combines sustained real GDP growth with improved health, education & living standards", "Growth occurs only in socialist states", "They mean the exact same metric"), 1, "Quality + structure vs quantity alone.", "Development encompasses HDI gains alongside output growth."),
                  q("ex_eco_10_2", "HDI Dimensions", "WAEC", 2021, "The UN Human Development Index (HDI) combines all EXCEPT:", listOf("Life expectancy at birth", "Education attainment / schooling years", "Per-capita GNI (PPP)", "Military defense budget"), 3, "Health, Education, Income.", "Military spending is excluded from HDI."),
                  q("ex_eco_10_3", "Vicious Circle", "JAMB", 2023, "In Nurkse's Vicious Circle of Poverty on the supply side, low productivity stems directly from:", listOf("Excessive foreign aid", "Low capital formation caused by low domestic savings", "Zero population growth", "Over-abundance of power plants"), 1, "Low income leaves minimal savings for capital.", "Low Y → Low S → Low I keeps productivity depressed."),
                  q("ex_eco_10_4", "Petroleum Downstream", "NECO", 2022, "Extracting crude oil out of Niger Delta wells belongs to ____ sector, whereas refining petrol is ____:", listOf("Downstream; Upstream", "Upstream; Downstream", "Tertiary; Quaternary", "Monetary; Fiscal"), 1, "Extraction up; refining/retail down.", "Upstream extracts crude; downstream refines & distributes."),
                  q("ex_eco_10_5", "Planning Obstacles", "WAEC", 2020, "A primary cause of under-implementation in Nigerian National Development Plans has historically been:", listOf("Excessive statistical transparency", "Inadequate reliable data, policy discontinuity & funding leaks", "Absence of human wants", "Too much domestic steel"), 1, "Data gaps & political discontinuity.", "Reliable data and project continuity are vital."),
                  q("ex_eco_10_6", "Agricultural Linkages", "NECO", 2021, "Supplying cocoa, cotton, and palm oil to domestic processing factories illustrates Agriculture's role in providing:", listOf("Inflation hedges", "Industrial raw materials & forward linkages", "Monetary tightening", "External debt relief directly"), 1, "Agro-industrial input supply.", "Factories rely on farm raw materials for value-added goods."),
                ),
              prerequisites = listOf("National Income & International Trade"),
              dependencySummary = "Completes the SS1–SS3 Economics roadmap connecting micro elasticity up to national growth strategy.",
              vocabulary =
                listOf(
                  VocabularyEntry("Dutch Disease", "Resource-boom FX appreciation that makes non-oil farming and factories uncompetitive.", "Oil surge crowds out farms & factories.", "Explains Nigeria's 1970s cash-crop decline."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Higher total GDP always improves average citizen wealth", "Big aggregate sounds prosperous.", "If Population grows FASTER than Real GDP, average GDP per person actually FALLS.", "Always compare %ΔGDP against %ΔPopulation!"),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "If Real GDP grows by 4.0% while Population increases by 4.8% per year, compute approximate change in Real GDP per capita and explain its policy urgency.",
                  "Standard",
                  3,
                  listOf("Real per-capita growth ≈ GDP growth − Population growth.", "4.0% − 4.8% = −0.8%."),
                  "Evaluate real living standard trend.",
                  "Real GDP per capita falls by ~0.8% annually; policymakers must boost non-oil productivity above 4.8% while expanding education/skills.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "2030 State Economic Transformation Blueprint",
                  "Capstone Strategic Plan",
                  "Draft a multi-year diversification strategy shifting a commodity-dependent state toward agro-processing and tech.",
                  listOf("Measure HDI baseline gaps", "Target power & cold-chain capital spending", "Establish monitoring & anti-leakage audits"),
                  listOf("Separates growth metrics from HDI targets", "Connects rural farms to processing factories", "Includes fiscal & BoP risk safeguards"),
                  "Channel sovereign revenue into rail/power corridors and agro-industrial hubs so non-oil exports outpace population growth.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Growth = %Δ Real GDP; Development = Growth + HDI + Equity; Per Capita ≈ %ΔGDP − %ΔPop.",
                  listOf("HDI: Health + Education + Income", "Upstream: Extraction", "Downstream: Refining"),
                  listOf("Equating enclave oil GDP jump with human development", "Ignoring population growth denominator"),
                  "Growth = Size; Development = Quality + Structure; Diversify Away From Enclave.",
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      q("pq_eco_01", "Scarcity & Choice", "JAMB", 2021, "Lionel Robbins defined Economics principally as a science studying:", listOf("Wealth maximization alone", "Human behaviour as a relationship between ends and scarce means having alternative uses", "Government tax collection only", "Accounting ledgers"), 1, "Ends vs scarce means with alternative uses.", "Robbins (1932) centers on scarcity and choice."),
      q("pq_eco_02", "Production Possibility Curve", "JAMB", 2022, "An economy producing INSIDE its PPC boundary is experiencing:", listOf("Economic growth", "Resource unemployment / inefficiency", "Hyperinflation", "Unattainable output"), 1, "Below full capacity.", "Points strictly inside the PPC indicate idle or misallocated resources."),
      q("pq_eco_03", "Elasticity", "JAMB", 2023, "Price rises from NGN 40 to NGN 50 causing quantity demanded to fall from 100 to 60 units. Find |PED|.", listOf("0.40", "1.00", "1.60", "2.50"), 2, "|PED| = (40/100) / (10/40) = 0.40 / 0.25.", "0.40 ÷ 0.25 = 1.60 (elastic)."),
      q("pq_eco_04", "Consumer Utility", "WAEC", 2021, "Total Utility reaches its maximum precisely when Marginal Utility equals:", listOf("Infinity", "Average Utility", "Zero", "One"), 2, "Slope of TU hits zero at peak.", "Beyond MU = 0, extra units reduce TU."),
      q("pq_eco_05", "Market Equilibrium", "JAMB", 2020, "Suppose Q_d = 120 - 3P and Q_s = 20 + 2P. What is equilibrium price P?", listOf("NGN 10", "NGN 20", "NGN 28", "NGN 40"), 1, "Set 120 - 3P = 20 + 2P.", "100 = 5P → P = NGN 20."),
      q("pq_eco_06", "Production Scale", "JAMB", 2022, "Which returns regime appears as the Law of Diminishing Returns operates?", listOf("Short run with at least one fixed factor", "Long run when all inputs vary together", "Zero capital stock", "Monopoly merger"), 0, "Short run fixed factor constraint.", "Adding variable labour to fixed land causes MP to drop eventually."),
      q("pq_eco_07", "Population Theory", "WAEC", 2022, "Malthus argued that unchecked population grows ____ while food output grows ____:", listOf("Arithmetically; geometrically", "Geometrically; arithmetically", "Equally forever", "Logarithmically; exponentially"), 1, "Doubling population vs linear food increments.", "Population grows geometrically (2, 4, 8) vs food arithmetically (1, 2, 3)."),
      q("pq_eco_08", "Cost Curves", "JAMB", 2021, "Marginal Cost (MC) intersects Average Total Cost (ATC) at:", listOf("ATC's maximum", "ATC's minimum", "Zero output", "Fixed cost intercept"), 1, "Marginal pulls average.", "Below min ATC, MC < ATC pulls ATC down; at min ATC, MC = ATC."),
      q("pq_eco_09", "Market Structures", "JAMB", 2023, "A market featuring many tiny sellers, identical standardized produce, and free entry/exit is:", listOf("Monopoly", "Duopoly", "Perfect Competition", "Monopsony"), 2, "Price-taking agricultural grain/tomato benchmark.", "Perfect Competition."),
      q("pq_eco_10", "Price Control", "NECO", 2023, "Imposing a binding maximum rent ceiling STRICTLY BELOW equilibrium creates:", listOf("Housing surplus", "Chronic housing shortage & black-market key money", "Zero landlords leaving", "Equilibrium clearance"), 1, "Quantity demanded exceeds constrained supply.", "Binding ceiling below equilibrium causes excess demand (shortage)."),
      q("pq_eco_11", "Public Finance", "JAMB", 2021, "Which tax tends to be REGRESSIVE as a share of low-income household earnings?", listOf("Graduated PAYE", "Inheritance tax", "Flat-rate indirect VAT on essentials", "Capital gains surtax"), 2, "Poorer earners spend almost 100% of wages.", "Flat VAT consumes a higher % of poor budgets than wealthy ones."),
      q("pq_eco_12", "National Income", "JAMB", 2022, "GDP at market price differs from GNP at market price strictly by:", listOf("Depreciation", "Subsidies", "Net Factor Income from Abroad", "Corporate dividends alone"), 2, "Borders vs citizen ownership.", "GNP = GDP + NFIA."),
      q("pq_eco_13", "Multiplier", "WAEC", 2021, "If MPS = 0.25, what is the value of the simple Keynesian multiplier?", listOf("0.25", "1.33", "4.0", "25.0"), 2, "k = 1 / MPS.", "k = 1 / 0.25 = 4.0."),
      q("pq_eco_14", "Money Demand", "JAMB", 2020, "According to Keynes, people hold idle speculative money balances mostly when they expect:", listOf("Bond prices to rise sharply tomorrow", "Bond prices to fall (interest rates to rise soon)", "Barter to return", "Taxes to drop to zero"), 1, "Wait to buy cheaper bonds at higher yields.", "Rising expected interest rates motivate waiting in cash."),
      q("pq_eco_15", "Banking Credit", "JAMB", 2023, "If Cash Reserve Ratio increases from 10% to 20%, commercial banks' maximum deposit creation power:", listOf("Doubles", "Halves", "Stays fixed", "Rises by 10%"), 1, "1/0.10 = 10 vs 1/0.20 = 5.", "Raising r shrinks multiplier from 10 to 5 (halves)."),
      q("pq_eco_16", "Inflation", "WAEC", 2023, "A simultaneous combination of stagnant economic output, high unemployment, and rising prices is called:", listOf("Deflation", "Disinflation", "Stagflation", "Reflation"), 2, "Stagnation + inflation.", "Usually triggered by severe cost-push energy/supply shocks."),
      q("pq_eco_17", "International Trade", "JAMB", 2021, "A quantitative physical cap limiting annual cars imported into a country is called:", listOf("Ad valorem tariff", "Specific tariff", "Import quota", "Export subsidy"), 2, "Quantity cap rather than tax rate.", "Quota limits physical units imported."),
      q("pq_eco_18", "Balance of Payments", "JAMB", 2022, "Remittances sent home by Nigerian doctors working in London enter the Balance of Payments under:", listOf("Visible trade", "Capital reserves", "Current Account (Net Current Transfers)", "Customs duties"), 2, "Unrequited international household transfers.", "Diaspora remittances belong to Current Transfers inside Current Account."),
      q("pq_eco_19", "Regional Bodies", "NECO", 2022, "Which institution specializes in funding long-term dams, highways, and power plants in developing nations?", listOf("IMF", "World Bank (IBRD)", "WTO", "OPEC"), 1, "Long-term reconstruction & capital projects.", "IBRD/World Bank handles long-run project lending."),
      q("pq_eco_20", "Economic Development", "JAMB", 2023, "Real GDP rises 3% while population rises 3.5%. Living standards on average have:", listOf("Doubled", "Slightly declined (negative per-capita growth)", "Remained 100% static", "Achieved industrialization"), 1, "3.0 − 3.5 = −0.5%.", "Population outpaced output growth."),
      q("pq_eco_21", "Utility Paradox", "WAEC", 2020, "Why do diamonds cost vastly more than water despite water being essential to survival?", listOf("Water has negative total utility", "Price reflects MARGINAL utility of the last scarce unit, not Total Utility", "Diamonds have zero supply cost", "Governments subsidize water completely"), 1, "Water is abundant (low MU); diamonds are scarce (high MU).", "Diamond-water paradox solved by Marginal Utility."),
      q("pq_eco_22", "Production Types", "JAMB", 2022, "Which firm enjoys 'Risk-Bearing Internal Economies of Scale'?", listOf("Diversifying products across multiple regions so one slump won't bankrupt the firm", "Bulk shipping discount", "Bank interest rebate", "Lower municipal property tax"), 0, "Portfolio resilience across products/markets.", "Diversification cushions demand risk within one large enterprise."),
      q("pq_eco_23", "Fiscal Policy", "NECO", 2021, "Borrowing abroad solely to pay recurrent civil-service wages rather than productive assets risks:", listOf("Guaranteed export boom", "Rising debt service without future output growth", "Zero interest burden", "Immediate deflation"), 1, "No productive capital return to repay debt.", "Unsustainable debt burden when recurrent payroll absorbs loans."),
      q("pq_eco_24", "Index Numbers", "JAMB", 2021, "A basket costs NGN 4,000 in Base Year 2018 and NGN 5,200 in 2022. What is the 2022 Price Index?", listOf("76.9", "120.0", "130.0", "152.0"), 2, "(5,200 / 4,000) × 100.", "1.30 × 100 = 130.0."),
    )
}
