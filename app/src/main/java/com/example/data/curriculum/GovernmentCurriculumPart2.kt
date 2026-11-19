package com.example.data.curriculum

object GovernmentCurriculumPart2 {

  private fun govConcept(
    heading: String,
    explanation: String,
    keyFormula: String?,
    examTip: String,
    mechanism: String,
    intel: String,
    recap: String,
  ) = GovernmentCurriculum.govConcept(heading, explanation, keyFormula, examTip, mechanism, intel, recap)

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
  ) = GovernmentCurriculum.q(id, topic, board, year, prompt, options, correct, hint, solution, scheme)

  val module4And5: List<CourseModule> =
    listOf(
      CourseModule(
        id = "gov_mod_4",
        subjectId = "government",
        indexLabel = "Module 04",
        title = "Nationalism, Constitutional Milestones & Military Interventions",
        summary = "Clifford 1922 to Lyttelton 1954, 1960/1963 Republics, 1979/1999 Constitutions, Crises & Military Rule.",
        lessons =
          listOf(
            Lesson(
              id = "gov_07_nationalism_1963_const",
              moduleId = "gov_mod_4",
              subjectId = "government",
              title = "Nigerian Nationalism & Constitutional Evolution (1922–1963)",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 11–12 • WAEC Constitutions 1922–1963",
              learningOutcomes =
                listOf(
                  "Trace Nationalist drivers (WASU, Macaulay's NNDP, Azikiwe's West African Pilot, WWII Atlantic Charter).",
                  "Sequence Nigeria's colonial milestones: Clifford 1922 → Richards 1946 → Macpherson 1951 → Lyttelton 1954 → Independence 1960.",
                  "Distinguish the 1960 Independence Constitution (Queen as Head of State) from the 1963 Republican Constitution.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Drivers of Nationalism & Early Movements",
                    "Educated elites barred by Indirect Rule, racial salary disparities, Press pioneers (Herbert Macaulay's Lagos Daily News, Nnamdi Azikiwe's West African Pilot), Student Unions (WASU in London), and World War II returning soldiers who fought alongside Europeans sparked nationalist demand for self-rule—sharpened by the 1941 Atlantic Charter proclaiming peoples' right to self-government.",
                    "\\text{NNDP (1923)} \\rightarrow \\text{NYM (1934)} \\rightarrow \\text{NCNC (1944)} \\rightarrow \\text{AG/NPC (1951)}",
                    "Herbert Macaulay formed Nigeria's first political party, the NNDP, in 1923 right after Clifford introduced Lagos/Calabar elective seats!",
                    "Newspapers unified urban clerks, traders, and veterans into national protest movements.",
                    "JAMB connects Clifford 1922's Elective Principle directly to NNDP (1923) formation.",
                    "NNDP was Nigeria's 1st party; WWII + Press + Atlantic Charter accelerated independence.",
                  ),
                  govConcept(
                    "2. Clifford (1922) & Richards (1946) Constitutions",
                    "Sir Hugh Clifford's 1922 Constitution made history by introducing the ELECTIVE PRINCIPLE—3 elected seats for Lagos and 1 for Calabar—while still excluding Northern Nigeria from the Legislative Council! Sir Arthur Richards' 1946 Constitution finally linked North and South inside ONE Central Legislative Council and divided Nigeria into three Regions (North, West, East), though Nigerians protested zero public consultation!",
                    "\\text{Clifford 1922 (Elective Principle 4 Seats)} \\rightarrow \\text{Richards 1946 (Regionalism + North-South Link)}",
                    "Richards introduced Regionalism without consulting Nigerians—sparking the 1947 NCNC London delegation protest.",
                    "Keeping North and South legislatively separate until 1946 slowed earlier political integration.",
                    "Standard WAEC fact: Elective Principle = Clifford 1922; Tri-Regionalism = Richards 1946.",
                    "Clifford = 4 elective seats; Richards = Regionalism + North-South legislative link (no consultation).",
                  ),
                  govConcept(
                    "3. Macpherson (1951) & Lyttelton (1954) Federalism",
                    "Learning from Richards' mistake, Sir John Macpherson held wide townhall/Ibadan consultations (1950) before drafting the 1951 quasi-federal Constitution creating regional cabinets and House of Representatives—yet the 1953 Kano Riot & motion for 'self-government in 1956' (moved by Chief Anthony Enahoro) exposed fatal center-region tensions. Oliver Lyttelton's 1954 Constitution solved them by making Nigeria a TRUE FEDERATION with Exclusive/Concurrent/Residual lists, Regional Premiers (Awolowo, Bello, Azikiwe), and Lagos as Federal Capital!",
                    "\\text{1953 Kano Crisis / Enahoro Motion} \\implies \\text{1954 Lyttelton TRUE Federalism}",
                    "1954 Lyttelton is remembered as Nigeria's FIRST genuine Federal Constitution!",
                    "Assigning regional Premiers and clear legislative lists let West/East win internal self-rule in 1957 and North in 1959.",
                    "JAMB repeatedly pairs 1954 Lyttelton with True Federalism & Regional Premiers.",
                    "Macpherson = broad consultation (quasi-federal); Lyttelton 1954 = True Federalism.",
                  ),
                  govConcept(
                    "4. 1957/58 Conferences & 1960 Independence Constitution",
                    "The 1957/58 London Constitutional Conferences resolved minority fears via the Willink Minorities Commission (1958)—which recommended enshrining Fundamental Human Rights rather than carving new states immediately—and paved the way for 1st October 1960 Independence. Under 1960, Nigeria kept a Westminster Parliamentary model with Abubakar Tafawa Balewa as Prime Minister, yet Queen Elizabeth II remained ceremonial Head of State (represented by Governor-General Nnamdi Azikiwe) and final appeals went to London's Privy Council!",
                    "\\text{1960 Model}: \\text{Queen (Head of State)} + \\text{Balewa (PM)} + \\text{London Privy Council}",
                    "Because final court appeals still went to London's Judicial Committee of the Privy Council, 1960 was not yet full judicial sovereignty.",
                    "Willink Commission prioritized Human Rights guarantees inside the constitution before Independence.",
                    "WAEC distinguishes 1960 (Monarchical Parliamentary) vs 1963 (Republican Parliamentary).",
                    "1960 kept the Queen & Privy Council appeals; Willink added Human Rights.",
                  ),
                  govConcept(
                    "5. The 1963 First Republican Constitution & Collapse",
                    "On 1st October 1963, Nigeria shed Queen Elizabeth as Head of State, becoming a Federal Republic with Dr Nnamdi Azikiwe as Ceremonial President and Nigeria's Supreme Court replacing the Privy Council as final court! Yet four crises tore the First Republic apart: Action Group split (1962), 1962/63 Census controversy, 1964 Federal Election boycott, and the 1965 Western Region election ('Wetie') crisis—ending in the January 1966 coup.",
                    "\\text{1963 Republic}: \\text{Ceremonial President Replaces Queen} + \\text{Supreme Court Final Appeals}",
                    "Remember: 1963 kept the PARLIAMENTARY Dual Executive—it did NOT create an American-style Executive President (that waited until 1979!).",
                    "Winner-takes-all regional politics + census/election distrust eroded democratic legitimacy.",
                    "JAMB tests 'Supreme Court became highest court in 1963' & 1965 Western crisis trigger.",
                    "1963 abolished Queen & Privy Council; 1962–65 crises triggered 1966 military takeover.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Sequencing 1922–1963 Constitutional Milestones",
                    "JAMB UTME-Style Worked Problem",
                    "Compare what changed specifically at three milestones: 1922 Clifford, 1954 Lyttelton, and 1963 Republican Constitution.",
                    listOf(
                      "Step 1 — 1922 Clifford: Introduced the Elective Principle (3 Lagos + 1 Calabar) giving birth to NNDP.",
                      "Step 2 — 1954 Lyttelton: Established True Federalism splitting Exclusive/Concurrent/Residual powers and creating Regional Premiers.",
                      "Step 3 — 1963 Republic: Replaced British Monarch with an indigenous Ceremonial President and ended Privy Council appeals (making Nigeria's Supreme Court supreme).",
                    ),
                    "1922 = Elective Principle; 1954 = True Federalism; 1963 = Indigenous Head of State & Final Supreme Court",
                    "Saying 1963 switched Nigeria to a Presidential System—First Republic remained Parliamentary until 1966!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Enahoro's 1953 Motion & Willink's 1958 Rights Legacy",
                    "Constitutional Negotiation",
                    "Minority petitions in 1957–58 led directly to Nigeria's tradition of entrenching Fundamental Human Rights in Chapter IV of every subsequent constitution.",
                    "Explains origin of Nigeria's codified Bill of Rights.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Compare Dual Parliamentary (1960/1963) vs Single Presidential (1979/1999) systems and federal list shifts.",
              exercises =
                listOf(
                  q("ex_gov_07_1", "Clifford 1922", "JAMB", 2021, "The landmark democratic feature of Nigeria's 1922 Clifford Constitution was:", listOf("Creation of 36 states", "Introduction of the Elective Principle (Lagos & Calabar)", "Presidential system", "Independence"), 1, "First 4 elected Nigerian councilors.", "Clifford introduced 4 elective seats triggering party politics."),
                  q("ex_gov_07_2", "First Party", "WAEC", 2022, "Which political party was founded by Herbert Macaulay in 1923?", listOf("Action Group (AG)", "National Council of Nigeria and the Cameroons (NCNC)", "Nigerian National Democratic Party (NNDP)", "Northern People's Congress (NPC)"), 2, "Contested 1923 Lagos seats.", "NNDP won Lagos seats following Clifford's reform."),
                  q("ex_gov_07_3", "Lyttelton 1954", "JAMB", 2023, "Nigeria transitioned into a true Federation with Exclusive, Concurrent, and Residual lists under the:", listOf("Richards Constitution 1946", "Macpherson Constitution 1951", "Lyttelton Constitution 1954", "1979 Constitution"), 2, "Established full federal division and regional Premiers.", "1954 Lyttelton Constitution formalized true federalism."),
                  q("ex_gov_07_4", "1963 Republic", "JAMB", 2020, "Under Nigeria's 1963 First Republican Constitution, the highest court of appeal became:", listOf("The Privy Council in London", "The Supreme Court of Nigeria", "ECOWAS Court", "Warrant Chief Tribunal"), 1, "Ended judicial link to London.", "1963 ended Privy Council appeals in favour of Nigeria's Supreme Court."),
                  q("ex_gov_07_5", "Willink 1958", "WAEC", 2021, "To calm minority fears before 1960 without delaying independence, the Willink Commission recommended:", listOf("Immediate military rule", "Entrenching Fundamental Human Rights in the Constitution", "Secession of Lagos", "Unitary abolition of regions"), 1, "Chapter of enforceable rights.", "Human Rights provisions safeguarded minorities peacefully."),
                  q("ex_gov_07_6", "1953 Motion", "NECO", 2022, "Who moved the famous 1953 motion calling for Nigeria's 'self-government in 1956'?", listOf("Chief Obafemi Awolowo", "Chief Anthony Enahoro", "Sir Ahmadu Bello", "Dr Nnamdi Azikiwe"), 1, "Action Group legislator in Federal House.", "Chief Anthony Enahoro moved the 1956 self-government motion."),
                ),
              prerequisites = listOf("Indirect Rule & Pre-Colonial systems"),
              dependencySummary = "First Republic collapse sets up Military Regimes, 1979/1999 Presidential Transitions & Third/Fourth Republics.",
              vocabulary =
                listOf(
                  VocabularyEntry("Elective Principle", "Constitutional grant allowing qualified citizens to vote representatives into legislature.", "Starting point of modern party elections in Nigeria.", "Clifford 1922 gave Lagos 3 seats & Calabar 1."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Richards 1946 created Nigeria's true Federation", "Confusing Regionalism with Federalism.", "Richards 1946 created purely deliberative Regional Houses WITHOUT autonomous federal lists; Lyttelton 1954 instituted True Federalism!", "1946 = Regionalism; 1954 = True Federalism."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Identify three constitutional changes made in 1963 that turned Nigeria from a Dominion Monarchy (1960) into a Republic.",
                  "Standard",
                  3,
                  listOf("Check Head of State, highest court of appeal, and appointment source.", "Note Parliamentary fusion remained untouched."),
                  "Compare 1960 vs 1963 institutions.",
                  "1. Queen Elizabeth II ceased to be Nigeria's Head of State, replaced by Nigerian Ceremonial President Dr Nnamdi Azikiwe. 2. Appeals to London's Privy Council stopped; Nigeria's Supreme Court became final court. 3. Judicial Service Commission changed; Nigerian citizenship allegiance replaced Crown loyalty.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "1922–1963 Constitutional Timeline & Crisis Map",
                  "Constitutional History Dossier",
                  "Map how 1922–1963 institutions evolved and diagnose why regional mistrust broke the First Republic.",
                  listOf("Annotate 1922/1946/1951/1954/1960/1963", "Map Action Group 1962, Census 1963, and 1964/65 crises", "Identify reform lessons"),
                  listOf("Accurate chronology & actors", "Separates 1954 federalism vs 1946 regionalism", "Links 1965 election crisis to military entry"),
                  "Independent electoral oversight, credible censuses, and inclusive executive spread were missing in 1965, motivating the 1979 Presidential pivot.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "1922 Clifford (Elective) → 1946 Richards (3 Regions) → 1951 Macpherson → 1954 Lyttelton (Federalism) → 1960 Indep → 1963 Republic.",
                  listOf("NNDP: 1st Party 1923", "Willink: Human Rights", "1963: Privy Council Ended"),
                  listOf("Calling 1963 a Presidential system", "Confusing 1946 vs 1954 federalism"),
                  "Enahoro 1953 Motion; Queen Out & Supreme Court Supreme in 1963.",
                ),
            ),
            Lesson(
              id = "gov_08_1979_1999_military_democ",
              moduleId = "gov_mod_4",
              subjectId = "government",
              title = "Military Rule, Transition & 1979 / 1999 Presidential Constitutions",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 13–14 • WAEC Military Rule & Second/Fourth Republics",
              learningOutcomes =
                listOf(
                  "Explain causes, organizational traits (Decrees, Supreme Military Council), and civilian return failures under Military Regimes.",
                  "Compare the 1979 Second Republic (FEDECO, Shehu Shagari) and 1999 Fourth Republic Presidential Constitutions.",
                  "Identify landmark military structural legacies (12→19→36 States, NYSC 1973, 1976 LG Reform, Abuja FCT).",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Causes & Character of Military Intervention (1966–1999)",
                    "Why do armies enter politics? Electoral rigging (1965, 1983), ethno-regional distrust, corruption, inflation/economic crisis, and intra-army contagion across West Africa. Unlike democracies, Military Regimes suspend key parts of the constitution, fuse Executive + Legislative powers inside a ruling junta (Supreme Military Council / AFRC / PRC), rule by non-justiciable DECREES (Federal) and EDICTS (States), oust court jurisdiction, and curb press freedom.",
                    "\\text{Military Hierarchy}: \\text{SMC/PRC (Fusion)} \\implies \\text{Federal Decrees} + \\text{State Edicts}",
                    "Never call a military order an 'Act of Parliament'—Federal military laws are DECREES; State military laws are EDICTS!",
                    "Command discipline lets juntas create states rapidly, yet absence of ballot accountability weakens rule of law and long-run succession.",
                    "JAMB highlights Decrees vs Edicts & suspension of legislative houses + Constitution.",
                    "Juntas fuse executive & legislature, rule via Decrees/Edicts, and restrict court challenges.",
                  ),
                  govConcept(
                    "2. Structural Reforms Under Military Administrations",
                    "Despite suppressing multi-party elections, Nigerian military governments created lasting federal architecture: Gowon's 12 States (1967, dismantling four giant regions), NYSC (1973), Murtala/Obasanjo's 19 States & 1976 Local Government Reform + Abuja Capital Decree (1976), Babangida's 21/30 States + Option A4 / June 12 1993 annulment crisis, and Abacha's 36 States (1996) plus Abdulsalami's smooth 1999 return to civilian rule.",
                    "4\\text{ Regions} \\xrightarrow{1967} 12\\text{ States} \\xrightarrow{1976} 19 \\xrightarrow{1991} 30 \\xrightarrow{1996} 36\\text{ States}",
                    "Why did Gowon split the 4 giant regions into 12 states in 1967? To pacify minorities and prevent any single super-region from overshadowing the Federation!",
                    "Creating 36 smaller states weakened separatist blocs while expanding federal representation via Federal Character.",
                    "WAEC asks for 4 concrete military legacies: 36-State map, NYSC, 1976 LG tier, and Abuja FCT.",
                    "Military administrations built the 36-State structure, 1976 LG system, NYSC, and Abuja.",
                  ),
                  govConcept(
                    "3. The 1979 Presidential Pivot (Second Republic)",
                    "To cure First Republic friction between a Ceremonial President and PM, the Rotimi Williams / Udo Udoma 1979 Constitution abandoned Westminster Parliamentarism for an EXECUTIVE PRESIDENTIAL MODEL: Single Head of State & Government (Alhaji Shehu Shagari, NPN), strict Separation of Powers, Federal Character requirement (appointing at least one Minister per state!), and national Electoral Spread (25% across 2/3 of 19 States).",
                    "\\text{1979 Pivot}: \\text{Parliamentary Dual Head} \\longrightarrow \\text{Single Executive President}",
                    "Recall the famous 1979 '12 two-thirds of 19 States' Supreme Court dispute settled in favour of Shagari!",
                    "Requiring nationwide 25% spread forced presidential candidates to campaign across ethnic regions instead of leaning on one home enclave.",
                    "JAMB compares 1963 (Parliamentary) directly against 1979 (Executive Presidential).",
                    "1979 launched Nigeria's Presidential System, Federal Character Cabinet, and 25% state spread.",
                  ),
                  govConcept(
                    "4. The Ill-Fated Third Republic & June 12, 1993",
                    "General Babangida's transition ran a strict state-created Two-Party experiment (Social Democratic Party SDP vs National Republican Convention NRC) using transparent Option A4 / Open Ballot elections. Though Chief M.K.O. Abiola (SDP) led nationwide in the 12 June 1993 poll, military annulment triggered fierce pro-democracy resistance (NADECO), an Interim National Government (Shonekan), Abacha's dictatorship, and eventual civilian rebirth on May 29, 1999.",
                    "\\text{1993 Two-Party (SDP vs NRC)} \\implies \\text{June 12 Annulment} \\implies \\text{1999 Democracy}",
                    "June 12, 1993 demonstrated Nigerians could cross religious/regional divides to back one ticket.",
                    "Prolonged transition setbacks taught future republics never to compromise INEC autonomy or ballot finality.",
                    "NECO often references SDP/NRC as Nigeria's sole two-party general election experiment.",
                    "1993 tested two decreed parties (SDP/NRC); 1999 inaugurated uninterrupted civilian rule.",
                  ),
                  govConcept(
                    "5. 1999 Constitution Highlights & Democratic Safeguards",
                    "Nigeria's 1999 Constitution (as amended) retains the 1979 Presidential blueprint across 36 States + FCT + 774 LGAs: 109 Senators + 360 Reps, rigid 2/3 NASS + 2/3 State Assembly amendment rule, Code of Conduct Bureau (CCB) asset declarations, Economic/Financial oversight institutions, INEC multi-party pluralism, and Independent Judiciary.",
                    "\\text{1999 Fed}: 36\\text{ States} + \\text{FCT} + 774\\text{ LGAs} \\mid \\text{Max Two 4-Year Terms}",
                    "Both President and State Governors serve at most two 4-year terms (8 years max).",
                    "Anti-corruption asset declarations (CCB), press freedom, and peaceful elections anchor Fourth Republic continuity.",
                    "WAEC tests Code of Conduct Bureau duties & 2-term Presidential/Gubernatorial limits.",
                    "1999 keeps 36 States + 774 LGAs, 2-term limits, Separation, and Asset Declarations.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Diagnosing Military Rule Traits vs 1979 Constitutional Design",
                    "WAEC WASSCE-Style Worked Problem",
                    "Explain (a) why Military Regimes suspend Chapter IV rights or court challenges via ouster clauses, and (b) three features introduced in 1979 specifically to prevent First Republic regional domination.",
                    listOf(
                      "Step 1 — Military traits: Juntas seize power unconstitutionally, fuse legislative/executive power in SMC/PRC, and insert 'ouster clauses' in Decrees so judges cannot invalidate junta orders.",
                      "Step 2 — 1979 safeguards: (1) Single directly elected Executive President eliminating Dual-Executive friction, (2) 25% votes in 2/3 of states compelling national coalition building, and (3) Federal Character clause requiring at least one Minister per state.",
                    ),
                    "Decree fusion & ouster clauses vs Executive Presidency, 25%-in-2/3 spread & Federal Character",
                    "Saying Nigeria remained Parliamentary after 1979—1979 definitively switched Nigeria to Presidentialism!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Code of Conduct Tribunal & Peaceful Alternation (2015)",
                    "Fourth Republic Resilience",
                    "Public officers declare assets to the CCB while rival parties alternate federal power smoothly through nationwide ballots rather than barracks coups.",
                    "Highlights Fourth Republic institutional durability.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Model Presidential supermajority checks, cabinet spread, and 3-tier list allocation across federal states.",
              exercises =
                listOf(
                  q("ex_gov_08_1", "Military Law", "JAMB", 2021, "Laws enacted by a Federal Military Government in Nigeria were officially termed ____ while State military laws were ____:", listOf("Acts; Statutes", "Decrees; Edicts", "Bye-laws; Charters", "Bills; Treaties"), 1, "Federal decrees, State edicts.", "Decrees at center; Edicts by Military Governors."),
                  q("ex_gov_08_2", "1979 Shift", "WAEC", 2022, "The single greatest structural change introduced by Nigeria's 1979 Constitution was replacing:", listOf("Federalism with Unitary rule", "The Westminster Parliamentary model with an Executive Presidential system", "36 States with 3 Regions", "Multi-party democracy with monarchy"), 1, "PM + Ceremonial Head gave way to Executive President.", "1979 instituted separation of powers and an Executive President."),
                  q("ex_gov_08_3", "1967 States", "JAMB", 2023, "General Yakubu Gowon's creation of 12 States in May 1967 sought primarily to:", listOf("Create a French commune", "Dismantle giant regional blocs and reassure minority ethnic groups", "Replace the Supreme Court", "Launch two parties"), 1, "Curb regional dominance.", "12 states broke the four large regional strongholds."),
                  q("ex_gov_08_4", "1993 Parties", "JAMB", 2020, "Which two government-registered parties contested Nigeria's June 12, 1993 Presidential election?", listOf("NCNC and AG", "NPN and UPN", "SDP and NRC", "PDP and APC"), 2, "Babangida's decreed 'little to left, little to right'.", "SDP vs NRC contested June 12, 1993."),
                  q("ex_gov_08_5", "Term Limits", "WAEC", 2021, "Under Nigeria's 1999 Constitution, the maximum tenure an elected President or State Governor may serve is:", listOf("One 6-year term", "Two 4-year terms (8 years)", "Unlimited renewable terms", "Three 3-year terms"), 1, "Maximum 2 terms of 4 years.", "Section 137 caps elected executives at two 4-year terms."),
                  q("ex_gov_08_6", "Anti-Corruption", "NECO", 2022, "Which constitutional body collects and verifies mandatory public-officer Asset Declarations in Nigeria?", listOf("INEC", "Code of Conduct Bureau (CCB)", "NNPC", "FEDECO"), 1, "Monitors conflicts of interest & assets.", "Public servants declare assets before and after office to CCB."),
                ),
              prerequisites = listOf("1922–1963 Constitutional milestones"),
              dependencySummary = "Executive institutions rely on permanent Civil Service & Public Corporations examined in Module 5.",
              vocabulary =
                listOf(
                  VocabularyEntry("Ouster Clause", "Provision inside a military Decree barring regular law courts from questioning its validity.", "Blocks judicial review during military rule.", "Reverses A.V. Dicey's Rule of Law."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Military rule improved Nigeria's Judiciary", "Noting 12/36 state courts.", "Military juntas repeatedly inserted ouster clauses, bypassed open trials via special tribunals, and fused judicial oversight under Decrees!", "Rule by decree inherently weakens judicial supremacy."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "List four concrete causes commonly cited for Military Intervention in West African First Republic governments.",
                  "Standard",
                  3,
                  listOf("Think of election rigging, ethnic intolerance, corruption, and economic distress.", "Include military ambition / regional contagion."),
                  "State structural political & military drivers.",
                  "1. Widespread electoral fraud & violent rigging (e.g., 1965 Western crisis). 2. Acute corruption and nepotism eroding public trust. 3. Ethno-regional polarization threatening state collapse. 4. Corporate ambition & coup contagion across neighboring states.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Civilian Democratic Consolidation Strategy",
                  "Governance Reform Brief",
                  "Identify institutional safeguards preserving Fourth Republic alternation since 1999 without military relapse.",
                  listOf("Audit INEC + BVAS credibility", "Enforce CCB + EFCC/ICPC accountability", "Preserve press & judicial autonomy"),
                  listOf("Contrasts 1963 vs 1979/1999 Presidential checks", "Names exact anti-rigging & spread rules", "Eliminates military ouster weaknesses"),
                  "Credible INEC logistics, zero-impunity anti-graft enforcement, and constitutional civil-military subordination anchor lasting democracy.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Military = Fusion + Decrees/Edicts + Ouster; 1979/1999 = Executive President + Spread + Federal Character.",
                  listOf("1967: 12 States", "1979: Presidential Pivot", "1993: SDP vs NRC"),
                  listOf("Calling State military orders Decrees (they're Edicts!)", "Forgetting 2-term cap & CCB"),
                  "1996 = 36 States; May 29 1999 = Fourth Republic Rebirth.",
                ),
            ),
          ),
      ),
      CourseModule(
        id = "gov_mod_5",
        subjectId = "government",
        indexLabel = "Module 05",
        title = "Public Administration & Nigeria's Foreign Policy",
        summary = "Civil Service, Public Corporations vs Privatization, Foreign Policy, Africa Centerpiece, AU, ECOWAS, Commonwealth & UN.",
        lessons =
          listOf(
            Lesson(
              id = "gov_09_public_service_corporations",
              moduleId = "gov_mod_5",
              subjectId = "government",
              title = "Civil Service, Ombudsman & Public Corporations vs Privatization",
              durationMinutes = 22,
              syllabusTag = "JAMB Sec. 15–16 • WAEC Civil Service & Parastatals",
              learningOutcomes =
                listOf(
                  "Apply core Civil Service norms: Anonymity, Neutrality, Impartiality, and Permanence.",
                  "Contrast Civil Service Ministries with commercial/utility Public Corporations (Parastatals).",
                  "Evaluate Commercialization vs Privatization and explain Nigeria's Public Complaints Commission (Ombudsman).",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Civil Service Principles: Anonymity, Neutrality & Permanence",
                    "Ministers arrive and leave every 4 years, yet Permanent Secretaries and Civil Servants stay across administrations (PERMANENCE / CONTINUITY)! They obey two strict rules: (1) POLITICAL NEUTRALITY (serve whichever party wins faithfully without partisan speeches) and (2) ANONYMITY (Ministers face press & parliament praise or blame while civil servants remain faceless backstage advisers).",
                    "\\text{Civil Service Norms}: \\text{Permanence} + \\text{Impartiality} + \\text{Political Neutrality} + \\text{Anonymity}",
                    "Recall Max Weber's Bureaucracy traits: hierarchy, fixed rules, specialization, merit recruitment, and written files ('Red-Tapism' when overly rigid!).",
                    "Neutral permanence ensures national immunization, customs, and budgets keep running smoothly during election transitions.",
                    "JAMB matches 'Minister takes blame/praise publicly' squarely with Civil Service ANONYMITY.",
                    "Permanence provides continuity; Neutrality serves any govt; Anonymity shields career staff.",
                  ),
                  govConcept(
                    "2. Civil Service Commission & Red-Tapism Controls",
                    "To prevent party loyalists from flooding ministries ('Spoils System'), the independent Civil Service Commission handles merit-based recruitment, promotion, and discipline—while the Public Complaints Commission (Ombudsman) investigates citizen complaints of maladministration, delay, or unfair dismissal free of courtroom fees!",
                    "\\text{Ombudsman (PCC)} = \\text{Free Non-Court Citizen Redress vs Bureaucratic Abuse}",
                    "Nigeria established the Public Complaints Commission (Ombudsman) in 1975 under General Murtala Mohammed.",
                    "Merit commissions protect professionalism; Ombudsman remedies counter arrogance and excessive Red-Tapism.",
                    "WAEC tests Ombudsman role: 'Protects ordinary citizens from administrative injustice / bureaucratic high-handedness'.",
                    "CSC ensures merit hiring; Ombudsman (PCC) offers citizens fast free administrative redress.",
                  ),
                  govConcept(
                    "3. Public Corporations (Parastatals) vs Civil Ministries",
                    "A Ministry of Health runs under strict annual Treasury budgets and civil-service pay scales, whereas a Public Corporation / Parastatal (e.g., FAAN, NPA, NIPOST, NRC) is set up by a specific Act of Parliament with independent legal personality ('sue and be sued'), a Chairman & Board of Directors, and business-style service fees to deliver essential utilities where private investors lack capital or natural monopolies exist.",
                    "\\text{Ministry (Tax-Funded/Minister)} \\;\\neq\\; \\text{Parastatal (Board + Statute + Service Fees)}",
                    "Despite autonomy, Parastatals remain accountable via Ministerial directives, Parliamentary Public Accounts Committee audits, and statutory annual reports.",
                    "Separate legal corporate status lets utilities procure spare parts quickly without waiting on civil-service treasury bottlenecks—unless political interference overstaffs them!",
                    "JAMB lists political interference, overstaffing, corruption, and obsolete equipment as classic causes of Parastatal losses.",
                    "Parastatals deliver strategic utilities via statutory autonomy, though Board corruption often drags losses.",
                  ),
                  govConcept(
                    "4. Privatization vs Commercialization",
                    "When state utilities bleed billions in subsidies, governments apply two distinct fixes: COMMERCIALIZATION keeps 100% government ownership while ending budget bailouts and ordering managers to run strictly for profit; PRIVATIZATION sells equity ownership partially or wholly to private investors (e.g., BPE sales of telecoms/power gen/banks) to inject private capital, tech, and efficiency.",
                    "\\text{Commercialization}: \\text{Public Owner + Profit Rule} \\;\\mid\\; \\text{Privatization}: \\text{Ownership Sold to Private Sector}",
                    "Do NOT swap them! Commercialized firms stay state-owned; Privatized firms change ownership!",
                    "Competitive private markets cut waste and expand consumer choice (like GSM telecom liberalization since 2001), yet monopolies require strong regulators (e.g., NERC, NCC).",
                    "WAEC often asks for two dangers of unchecked Privatization: job losses/retrenchment and monopoly tariff hikes on poor citizens.",
                    "Commercialization = profit mandate under state ownership; Privatization = selling ownership shares.",
                  ),
                  govConcept(
                    "5. Deregulation & Regulatory Safeguards",
                    "Deregulation removes restrictive statutory price caps or state monopoly bars so rival firms enter freely (as banking & telecoms opened up). Pairing deregulation with independent competition/consumer watchdogs prevents cartels from exploiting consumers.",
                    "\\text{Deregulation} = \\text{Removing Statutory Entry/Price Barriers to Boost Competition}",
                    "Civil Service reforms (e.g., 1988 Dotun Philips / 1995 Ayida Review) debated making Directors-General political vs permanent career heads—Ayida restored permanent career Permanent Secretaries!",
                    "Transparent procurement (Due Process / BPP) cuts contract inflation inside public service.",
                    "NECO identifies Ayida Panel (1995) as restoring career Permanent Secretaries after 1988 politicization.",
                    "Deregulation invites market rivals; independent regulators protect consumer fairness.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Comparing Parastatals, Commercialization & Privatization",
                    "WAEC WASSCE-Style Worked Problem",
                    "State State-Water Board runs chronic deficits despite heavy subsidies. Contrast what happens if government chooses (a) Commercialization vs (b) Privatization, and explain how a citizen challenges unfair tariff billing via the Ombudsman.",
                    listOf(
                      "Step 1 — Commercialization: State keeps 100% ownership, cancels automatic bailouts, and mandates cost-recovery/profit billing.",
                      "Step 2 — Privatization: State sells controlling shares to private operators who supply fresh capital and management.",
                      "Step 3 — Ombudsman (PCC): Aggrieved citizens lodge free formal complaints of administrative unfairness or delay without costly High Court fees.",
                    ),
                    "Commercialization keeps ownership; Privatization transfers shares; Ombudsman investigates administrative unfairness",
                    "Writing that Commercialization sells off public enterprises to private billionaires—ownership NEVER changes under pure Commercialization!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "Telecoms Deregulation vs Unregulated Private Monopoly",
                    "Public Sector Reform",
                    "Opening Nigeria's telecoms licensing (NCC) multiplied mobile lines exponentially compared to old monopoly waitlists, demonstrating gains from regulated competition.",
                    "Illustrates Deregulation paired with Regulatory Oversight.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Explore institutional accountability across Ministries, Parastatals, and Regulatory oversight.",
              exercises =
                listOf(
                  q("ex_gov_09_1", "Civil Service Norms", "JAMB", 2021, "The civil service principle holding that career officers must remain out of partisan politics and serve any ruling party loyally is:", listOf("Anonymity", "Political Neutrality", "Red-tapism", "Spoils system"), 1, "Loyalty across alternating governments.", "Political neutrality keeps civil administration non-partisan."),
                  q("ex_gov_09_2", "Anonymity", "WAEC", 2022, "Civil service ANONYMITY means that:", listOf("Civil servants never sign payrolls", "The Minister accepts public/parliamentary praise or blame for ministry actions rather than career civil servants", "Names are kept secret from citizens permanently", "Only military officers answer parliament"), 1, "Ministers speak publicly; staff advise quietly.", "Ministers shoulder political responsibility in parliament."),
                  q("ex_gov_09_3", "Ombudsman", "JAMB", 2023, "In Nigeria, the statutory body functioning as Ombudsman investigating administrative injustice against citizens is the:", listOf("Code of Conduct Bureau", "Public Complaints Commission (PCC)", "Civil Service Commission", "Economic Summit Group"), 1, "Established 1975 for free citizen redress.", "Public Complaints Commission serves as Nigeria's Ombudsman."),
                  q("ex_gov_09_4", "Privatization", "JAMB", 2020, "Transferring ownership and management control of a state-owned corporation to private investors is:", listOf("Nationalization", "Commercialization", "Privatization", "Socialization"), 2, "Equity ownership shifts to private hands.", "Privatization sells ownership; Commercialization only adds profit mandate."),
                  q("ex_gov_09_5", "Parastatals Control", "WAEC", 2021, "Which pair explains why many African Public Corporations underperformed historically?", listOf("Too much automated IT", "Political interference in hiring, corruption & inadequate funding", "Excessive consumer profit", "Too few employees"), 1, "Patronage jobs and weak autonomy.", "Partisan interference and weak financial discipline eroded performance."),
                  q("ex_gov_09_6", "Bureaucracy", "NECO", 2022, "Excessive strictness with official forms and multi-desk approvals slowing urgent service is termed:", listOf("Anonymity", "Delegation", "Red-tapism", "Judicial review"), 2, "Rigid procedural delay.", "Red-tapism describes slow adherence to tedious bureaucratic routines."),
                ),
              prerequisites = listOf("Executive Organs & Separation"),
              dependencySummary = "State administration feeds directly into executing Nigeria's Foreign Policy & Regional Diplomacy in Lesson 10.",
              vocabulary =
                listOf(
                  VocabularyEntry("Public Corporation (Parastatal)", "Statutory utility enterprise set up by Parliament to deliver essential goods/services with business-like corporate identity.", "Commercial public agency (like NPA/FAAN).", "Controlled indirectly via Minister & Parliamentary PAC audits."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Ombudsman replaces the High Court completely", "Confusing administrative redress with criminal trials.", "The Ombudsman issues recommendations and mediates bureaucratic delays/unfairness; it cannot sentence criminals or overrule Supreme Court precedents.", "Free mediation vs binding judicial trials."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Distinguish Civil Service Commission duties from Public Complaints Commission (Ombudsman) duties cleanly.",
                  "Standard",
                  3,
                  listOf("Who handles internal merit hiring/promotion?", "Who handles external citizen petitions over maladministration?"),
                  "Separate internal HR integrity vs external citizen protection.",
                  "Civil Service Commission recruits, promotes, and disciplines career civil servants on merit (blocking nepotism). Public Complaints Commission (Ombudsman) investigates external citizen complaints of bureaucratic abuse, delay, or unfairness freely.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Parastatal Reform & Citizen Service Charter",
                  "Public Administration Brief",
                  "Redesign a loss-making state transit corporation using Commercialization benchmarks and Ombudsman SLA targets.",
                  listOf("Ring-fence Board from partisan payroll stuffing", "Publish 14-day Ombudsman resolution SLAs", "Audit revenue vs maintenance"),
                  listOf("Applies Anonymity/Neutrality cleanly", "Separates Commercialization vs Privatization", "Eliminates Red-Tapism delays"),
                  "Performance contracts + open digital ticketing + Ombudsman response timelines slash deficits without forfeiting essential public access.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Civil Service: Permanence, Neutrality, Anonymity; CSC = Merit Hiring; PCC = Ombudsman; Privatize = Sell Ownership.",
                  listOf("PCC: Ombudsman (1975)", "Parastatal: Statutory Board", "Red Tape: Slow Formalism"),
                  listOf("Swapping Commercialization with Privatization", "Confusing CSC with Ombudsman"),
                  "Commercialize = Profit Under State Owner; Minister Faces Public Blame (Anonymity).",
                ),
            ),
            Lesson(
              id = "gov_10_foreign_policy_ecowas_un",
              moduleId = "gov_mod_5",
              subjectId = "government",
              title = "Nigeria's Foreign Policy, Africa Centerpiece, ECOWAS, AU, Commonwealth & UN",
              durationMinutes = 24,
              syllabusTag = "JAMB Sec. 17–18 • WAEC Foreign Policy & International Organizations",
              learningOutcomes =
                listOf(
                  "Analyze determinants of Nigeria's Foreign Policy and the 'Africa as Centerpiece' doctrine.",
                  "Evaluate Non-Aligned Movement principles, Technical Aid Corps (TAC), and ECOMOG peacekeeping.",
                  "Compare mandates and organs of ECOWAS, AU, Commonwealth of Nations, OPEC, and United Nations.",
                ),
              concepts =
                listOf(
                  govConcept(
                    "1. Determinants & 'Africa as Centerpiece' Doctrine",
                    "A nation's Foreign Policy safeguards its National Interest—security, trade, sovereignty, and citizen welfare abroad. Factors shaping Nigeria's stance include Geography (Gulf of Guinea/West Africa), Population & Economy (largest African market), Military capacity, and Colonial heritage. Since Balewa through Murtala/Obasanjo ('Africa has come of age!', Angola 1975/anti-apartheid), Nigeria declared AFRICA AS THE CENTERPIECE of its foreign policy: opposing colonialism/apartheid, backing liberation struggles, respecting territorial borders, and fostering African integration.",
                    "\\text{National Interest} \\implies \\text{Africa Centerpiece} + \\text{Non-Alignment} + \\text{Multilateral Peace}",
                    "Remember Nigeria funded Frontline States & ANC/SWAPO against apartheid, earning honour as an honorary Frontline State despite geographic distance!",
                    "Regional stability prevents refugee shocks and cross-border conflict from spilling inside Nigeria.",
                    "JAMB frequently quotes Murtala Mohammed (1976 OAU): 'Africa has come of age' over Angola recognition.",
                    "National interest steers Foreign Policy; Africa remains Nigeria's priority diplomatic theatre.",
                  ),
                  govConcept(
                    "2. Non-Alignment & Technical Aid Corps (TAC)",
                    "During the Cold War (USA West vs USSR East), Nigeria championed NON-ALIGNMENT—refusing permanent military blocs with either superpower while judging global crises independently (though economic ties leaned West!). Nigeria also launched the Technical Aid Corps (TAC, 1987) dispatching Nigerian doctors, engineers, nurses, and teachers freely to African, Caribbean, and Pacific (ACP) partners—projecting soft-power diplomacy.",
                    "\\text{Non-Alignment} \\neq \\text{Isolationism} \\quad (\\text{Independent Judgment Without Superpower Blocs})",
                    "Non-Alignment does NOT mean staying silent or isolated—it means independent voting free of Cold War alliances.",
                    "TAC builds lasting goodwill across Africa/Caribbean at lower cost than cash grants.",
                    "WAEC tests Technical Aid Corps (1987, Babangida/Akinyemi) as Nigeria's flagship soft-power export.",
                    "Non-Alignment rejected military blocs; TAC shares Nigerian skilled experts abroad.",
                  ),
                  govConcept(
                    "3. ECOWAS (1975) & ECOMOG Regional Peacekeeping",
                    "Co-championed by General Yakubu Gowon (Nigeria) and Gnassingbé Eyadéma (Togo), the Treaty of Lagos founded ECOWAS on May 28, 1975 (HQ: Abuja!) to remove tariffs, guarantee Visa-Free Protocol movement, integrate power/highways, and mediate crises via Authority of Heads of State (highest), Council of Ministers, ECOWAS Parliament, and Community Court. When civil wars tore Liberia and Sierra Leone in the 1990s, Nigeria spearheaded ECOMOG peacekeeping!",
                    "\\text{ECOWAS (1975, Abuja HQ)} \\implies \\text{Free Movement + Customs Union + ECOMOG Peace}",
                    "Note challenges: French-Anglophone currency/political divides (CFA Franc vs Naira/Cedi), unpaid member dues, and recent military coups disrupting West African unity.",
                    "Nigerian manpower and finance carried >70% of ECOMOG peacekeeping operations in Liberia and Sierra Leone.",
                    "JAMB verifies ECOWAS date (May 28, 1975), Treaty of Lagos, Abuja Secretariat, and Gowon-Eyadéma co-founding.",
                    "ECOWAS (1975, Abuja) integrates West Africa; ECOMOG enforced regional peace.",
                  ),
                  govConcept(
                    "4. OAU (1963) → African Union (2002) & Commonwealth",
                    "Founded May 25, 1963 in Addis Ababa, the Organization of African Unity (OAU) liberated Africa from colonial/apartheid rule yet suffered a strict 'Non-Interference' clause that paralyzed action against dictators. Transformed in July 2002 (Durban) into the AFRICAN UNION (AU), the charter added collective intervention against genocide/unconstitutional coups, the Peace & Security Council, Pan-African Parliament, and AfCFTA! Meanwhile, the COMMONWEALTH OF NATIONS links former British territories voluntarily (King Charles as ceremonial head, Secretariat in London, CHOGM summits; suspended Nigeria 1995–99 over human-rights/Ken Saro-Wiwa crisis).",
                    "\\text{OAU (1963, Anti-Colonial)} \\xrightarrow{2002} \\text{AU (Right to Intervene + AfCFTA + Peace Council)}",
                    "Remember OAU → AU shift replaced absolute 'Non-Interference' with 'Non-Indifference' against atrocities and coups!",
                    "Commonwealth scholarships, legal exchange, and trade ties bridge diverse continents using English common-law heritage.",
                    "WAEC compares OAU (non-interference weakness) with AU (coup suspension & humanitarian intervention).",
                    "AU upgraded OAU in 2002 for unity & crisis intervention; Commonwealth suspended Nigeria 1995–99.",
                  ),
                  govConcept(
                    "5. The United Nations (UN, 1945) & Security Council Veto",
                    "Founded October 24, 1945 (HQ: New York) to prevent future world wars, the UN runs 6 core organs: General Assembly (1 nation = 1 vote parliament), Security Council (15 members: 5 Permanent Veto powers USA, UK, France, Russia, China + 10 rotating 2-year seats—sole organ passing binding military/sanction resolutions!), Secretariat (Secretary-General), International Court of Justice (ICJ at The Hague), ECOSOC, and suspended Trusteeship Council.",
                    "\\text{UN Security Council}: 5\\text{ Permanent Veto (P5)} + 10\\text{ Elected (2-Yr)} = 15",
                    "One single NO vote (Veto) by any of the P5 blocks a substantive Security Council resolution even if 14 other members vote YES! Nigeria contributed vastly to UN peacekeeping from Congo (1960) to Sudan/Darfur.",
                    "Specialized agencies tackle specific human needs: WHO (health), UNESCO (education/culture), UNICEF (children), FAO (food), ILO (labour).",
                    "JAMB routinely tests P5 Veto powers, ICJ location (The Hague, Netherlands), and October 24, 1945 founding.",
                    "UNSC P5 hold veto power; ICJ sits in The Hague; Nigeria excels in peacekeeping.",
                  ),
                ),
              workedExamples =
                listOf(
                  WorkedExample(
                    "Auditing Nigeria's UN, ECOWAS & AU Roles",
                    "WAEC WASSCE-Style Worked Problem",
                    "Trace Nigeria's diplomatic leadership across (a) 1975 ECOWAS founding, (b) OAU's transition to AU, and (c) structural critique of the UN Security Council.",
                    listOf(
                      "Step 1 — ECOWAS (1975): Gowon & Eyadéma signed Treaty of Lagos; HQ later moved to Abuja; Nigeria bankrolled ECOMOG in Liberia & Sierra Leone.",
                      "Step 2 — OAU → AU (2002): OAU freed colonised states yet barred interfering against coups; AU (2002) added Peace & Security Council & non-indifference to genocide/coups.",
                      "Step 3 — UN Security Council critique: Only 5 countries (USA, UK, France, Russia, China) hold permanent veto power while Africa & Latin America lack permanent veto seats.",
                    ),
                    "ECOWAS (1975/ECOMOG); AU coup-intervention upgrade (2002); UNSC P5 Veto imbalance",
                    "Saying UN Headquarters and ICJ sit in the same city—UN Secretariat/Assembly sit in New York while ICJ sits in The Hague!",
                  ),
                ),
              scenarios =
                listOf(
                  RealLifeScenario(
                    "ECOWAS Passport & Technical Aid Corps Volunteers",
                    "Regional Soft Power",
                    "West African citizens cross regional borders using ECOWAS travel certificates while Nigerian TAC lecturers serve across Banjul, Freetown, and the Caribbean.",
                    "Shows functional regional integration and soft-power diplomacy.",
                  ),
                ),
              labType = PracticalLabType.GOVERNMENT_ORGANS_FEDERALISM_LAB,
              labBrief = "Review multilevel international vs domestic checks and constitutional treaty ratification mechanics.",
              exercises =
                listOf(
                  q("ex_gov_10_1", "Africa Centerpiece", "JAMB", 2021, "The enduring cornerstone principle guiding Nigerian foreign policy since independence is:", listOf("Colonial expansion", "Africa as the Centerpiece (anchored on National Interest)", "Joining Warsaw Pact", "Absolute isolationism"), 1, "Balewa → Murtala → Modern diplomacy.", "Africa remains Nigeria's primary foreign policy theatre."),
                  q("ex_gov_10_2", "ECOWAS Founding", "WAEC", 2022, "ECOWAS was formally established on May 28, 1975 under the Treaty of Lagos through joint leadership by:", listOf("Nkrumah and Balewa", "Yakubu Gowon (Nigeria) and Gnassingbé Eyadéma (Togo)", "Azikiwe and Senghor", "Abacha and Rawlings"), 1, "Nigerian & Togolese heads of state.", "Gowon and Eyadéma spearheaded ECOWAS creation."),
                  q("ex_gov_10_3", "UN Security Council", "JAMB", 2023, "Which five UN member states permanently hold Veto Power inside the Security Council?", listOf("USA, Germany, Japan, Brazil, Nigeria", "USA, UK, France, Russia, China", "Canada, Italy, India, China, UK", "USA, Spain, France, Egypt, Russia"), 1, "1945 Allied P5 founders.", "USA, UK, France, Russia, and China form the P5."),
                  q("ex_gov_10_4", "ICJ Venue", "JAMB", 2020, "The International Court of Justice (World Court), which settled the Bakassi Peninsula dispute, sits permanently in:", listOf("New York, USA", "Geneva, Switzerland", "The Hague, Netherlands", "Addis Ababa, Ethiopia"), 2, "The Peace Palace in Europe.", "ICJ sits at The Hague, Netherlands."),
                  q("ex_gov_10_5", "OAU vs AU", "WAEC", 2021, "A decisive reform of the African Union (2002) over the old OAU (1963) is its authority to:", listOf("Abolish all African borders immediately", "Reject unconstitutional military coups & intervene against grave war crimes/genocide", "Move UN headquarters to Cairo", "Adopt the Euro currency"), 1, "Shift from strict Non-Interference to Non-Indifference.", "AU Charter Art. 4(h) sanctions intervention against atrocities and suspends coup leaders."),
                  q("ex_gov_10_6", "Commonwealth", "NECO", 2022, "Which organization suspended Nigeria in 1995 (restored May 1999) over human-rights violations under military rule?", listOf("OPEC", "The Commonwealth of Nations", "ECOWAS Parliament", "WHO"), 1, "Voluntary association of former British territories.", "Commonwealth suspended Nigeria at Auckland 1995 CHOGM until 1999 democracy return."),
                ),
              prerequisites = listOf("Public Administration & Nigerian History"),
              dependencySummary = "Completes the SS1–SS3 Government mastery sequence across theory, history, federalism, and global diplomacy.",
              vocabulary =
                listOf(
                  VocabularyEntry("Veto Power", "Constitutional right of any P5 member (USA, UK, France, Russia, China) to defeat any substantive UN Security Council resolution alone.", "Single permanent 'No' blocks binding action.", "Core subject of African UNSC reform calls."),
                ),
              misconceptions =
                listOf(
                  MisconceptionAnalysis("Non-Alignment meant Nigeria refused to trade with Western or Eastern nations", "Confusing non-alignment with boycott.", "Non-Alignment refused MILITARY ALLIANCES (NATO/Warsaw Pact), while maintaining active trade, scholarships, and aid ties across both blocs!", "Zero military blocs ≠ economic isolation."),
                ),
              guidedPractice =
                GuidedPracticeTask(
                  "Identify three obstacles confronting ECOWAS full monetary and economic integration despite its 1975 founding.",
                  "Standard",
                  4,
                  listOf("Compare colonial language/currency divides (CFA vs Naira) and unpaid member dues.", "Note smuggling, border closures, and military coups."),
                  "Diagnose economic, linguistic, and security challenges.",
                  "1. Anglophone-Francophone currency splits (CFA Franc tied externally vs Naira/Cedi) delaying the common ECO currency. 2. Non-payment of community levies and border closures blocking free trade. 3. Political instability, military coups, and Sahel insecurity weakening collective solidarity.",
                ),
              masteryProject =
                TopicMasteryProject(
                  "Nigerian Diplomatic & Multilateral Strategy Dossier",
                  "Capstone Foreign Policy Brief",
                  "Assess how AfCFTA, ECOWAS reform, Technical Aid Corps, and UNSC democratization serve Nigeria's 21st-century national interest.",
                  listOf("Align trade/security priorities with Africa Centerpiece", "Strengthen TAC & ECOWAS border corridors", "Make Africa's UNSC P5 reform case"),
                  listOf("Links domestic stability to foreign credibility", "States exact UN/AU/ECOWAS dates & venues", "Balances soft power (TAC) & security (ECOMOG)"),
                  "Strengthening domestic economic capacity alongside principled ECOWAS democracy mediation and AfCFTA exports multiplies Nigeria's continental influence.",
                ),
              cheatSheet =
                TopicCheatSheet(
                  "Africa Centerpiece + Non-Alignment + TAC (1987); ECOWAS (1975, Abuja); OAU (1963)→AU (2002); UN (1945, P5 Veto, ICJ Hague).",
                  listOf("P5: USA/UK/FR/RU/CN", "TAC: Technical Aid Corps", "ICJ: The Hague"),
                  listOf("Putting ICJ in New York instead of The Hague", "Equating Non-Alignment with zero trade"),
                  "1975 Lagos Treaty (Gowon-Eyadéma); 1995–99 Commonwealth Suspension; AU Non-Indifference.",
                ),
            ),
          ),
      ),
    )

  val pastQuestions: List<PracticeQuestion> =
    listOf(
      q("pq_gov_01", "Legitimacy", "JAMB", 2021, "A government whose authority citizens accept as rightful and moral possesses:", listOf("Coercive monopoly alone", "Legitimacy", "De facto decree", "Secession"), 1, "Voluntary public consent.", "Legitimacy reflects citizen belief that rule is rightful."),
      q("pq_gov_02", "Sovereignty", "JAMB", 2022, "Which scholar published the classic 1576 treatise on Sovereignty ('Six Livres de la République')?", listOf("Montesquieu", "Jean Bodin", "A.V. Dicey", "Aristotle"), 1, "16th-century French theorist.", "Jean Bodin formulated sovereign supremacy doctrine."),
      q("pq_gov_03", "Rule of Law", "JAMB", 2023, "Which clause constitutes a formal exception to A.V. Dicey's Equality Before the Law?", listOf("Universal adult suffrage", "Diplomatic immunity of foreign ambassadors", "Secret ballot", "Bicameral concurrence"), 1, "Accredited diplomats answer home courts.", "Diplomatic Immunity & Exec Sec. 308 are classic exceptions."),
      q("pq_gov_04", "Written Constitution", "WAEC", 2021, "An advantage of a Rigid Constitution for a multi-ethnic Federation is that it:", listOf("Permits overnight abolition of regions by simple majority", "Prevents rushed alteration of minority and state guarantees", "Costs zero time to amend", "Eliminates judicial review"), 1, "Special supermajority shields federal compact.", "Rigidity secures federal balance against transient majorities."),
      q("pq_gov_05", "Separation of Powers", "JAMB", 2020, "Under Separation of Powers, Judicial Review empowers courts to:", listOf("Pass national budgets", "Declare unconstitutional legislative or executive acts void", "Command army troops", "Appoint cabinet ministers"), 1, "Negative constitutional safeguard.", "Courts strike down ultra vires laws and orders."),
      q("pq_gov_06", "Parliamentary System", "JAMB", 2022, "Which feature separates the British Parliamentary model cleanly from Nigeria's 1999 Presidential System?", listOf("Written federal lists", "Fusion of Executive Ministers inside Legislature + Collective Responsibility", "Fixed 4-year non-removable cabinet", "109 Senators"), 1, "Ministers sit as MPs and rise/fall together.", "Parliamentarism fuses Cabinet & Parliament."),
      q("pq_gov_07", "Legislature Structure", "WAEC", 2022, "Nigeria's federal Senate contains ____ elected Senators:", listOf("774", "360", "109 (3 per state + 1 FCT)", "36"), 2, "3 × 36 States + 1 Abuja.", "109 Senators balance state representation."),
      q("pq_gov_08", "Federal Lists", "JAMB", 2021, "Customs, Defense, Aviation, and Currency sit strictly under Nigeria's:", listOf("Residual List", "Concurrent List", "Exclusive Federal List", "Local Government Schedule"), 2, "Federal center alone legislates here.", "National security, currency & borders sit in Exclusive List."),
      q("pq_gov_09", "Local Government", "JAMB", 2023, "Nigeria's Local Government tier was uniformly institutionalized nationwide under the landmark reform of:", listOf("1954", "1963", "1976", "1991"), 2, "Obasanjo administration grassroots reform.", "1976 standardized elected single-tier LGAs with statutory funding."),
      q("pq_gov_10", "Electoral Systems", "NECO", 2023, "Awarding parliamentary seats directly in ratio to each party's percentage of popular votes is:", listOf("First-Past-The-Post", "Gerrymandering", "Proportional Representation", "Zero-party selection"), 2, "Votes % ≈ Seats %.", "Proportional Representation avoids wasted minority votes."),
      q("pq_gov_11", "Public Opinion", "JAMB", 2021, "Which method uses scientifically chosen random citizens to measure public sentiment before policy rollout?", listOf("Military curfew", "Opinion poll", "Impeachment", "Prerogative writ"), 1, "Statistical sampling.", "Random opinion polls sample voter views reliably."),
      q("pq_gov_12", "Pre-Colonial Systems", "JAMB", 2022, "In pre-colonial Old Oyo, the body balancing the Oyo Mesi and preventing royal/kingmaker tyranny was the:", listOf("Warrant Chiefs", "Ogboni secret society", "Alkali court", "Age Grades"), 1, "Pious elders & civic ritual checks.", "Ogboni moderated disputes between Alaafin and Bashorun/Oyo Mesi."),
      q("pq_gov_13", "French Colonial Rule", "WAEC", 2021, "When French Assimilation proved costly and resisted in West Africa, Paris shifted toward:", listOf("Indirect Rule in Lagos", "Policy of Association", "Confederation", "Federal Character"), 1, "Respecting selected indigenous chiefs more pragmatically.", "France transitioned from Assimilation toward Association."),
      q("pq_gov_14", "Clifford 1922", "JAMB", 2020, "Which city outside Lagos received 1 elected seat under the 1922 Clifford Constitution?", listOf("Kano", "Enugu", "Calabar", "Ibadan"), 2, "3 Lagos + 1 eastern port city.", "Lagos (3) and Calabar (1)."),
      q("pq_gov_15", "Macpherson 1951", "JAMB", 2023, "Unlike Richards (1946), why did the 1951 Macpherson Constitution earn wider initial acceptance?", listOf("It declared instant independence", "Wide village-to-Ibadan public consultations preceded its drafting", "It introduced 36 states", "It installed Queen Victoria"), 1, "Grassroots Nigerian debates.", "Macpherson organized broad national consultations culminating at Ibadan 1950."),
      q("pq_gov_16", "1960 Independence", "WAEC", 2023, "Between 1960 and 1963, Nigeria's Head of State remained:", listOf("Sir Abubakar Tafawa Balewa", "Queen Elizabeth II (represented by Governor-General)", "Dr Nnamdi Azikiwe as Executive President", "General Aguiyi-Ironsi"), 1, "Balewa was PM; Monarch stayed Head of State until 1963.", "Queen Elizabeth II remained Head of State until 1963 Republic."),
      q("pq_gov_17", "1979 Constitution", "JAMB", 2021, "Who served as Chairman of the 1975 Constitution Drafting Committee that shaped Nigeria's 1979 Presidential Constitution?", listOf("Chief F.R.A. Williams", "Sir Hugh Clifford", "Lord Lugard", "Chief Anthony Enahoro"), 0, "Eminent Nigerian SAN ('Timi the Law').", "Chief F.R.A. Williams chaired the 49-wise-men CDC."),
      q("pq_gov_18", "Military Legacies", "JAMB", 2022, "In what year was Nigeria's National Youth Service Corps (NYSC) established to deepen national integration?", listOf("1960", "1967", "1973", "1979"), 2, "Post-civil-war Gowon reconciliation program.", "NYSC started in May 1973."),
      q("pq_gov_19", "Civil Service", "NECO", 2022, "Which 1995 review panel reversed the 1988 politicization and restored career Permanent Secretaries?", listOf("Willink Commission", "Alison Ayida Review Panel", "Udoji Commission only", "Chick Commission"), 1, "Restored classical Weberian continuity.", "Ayida Panel restored career Permanent Secretaries & CSC safeguards."),
      q("pq_gov_20", "ECOWAS HQ", "JAMB", 2023, "Which city permanently hosts the ECOWAS Commission Secretariat today?", listOf("Lomé", "Abuja", "Dakar", "Accra"), 1, "Moved from Lagos to Nigeria's Federal Capital.", "ECOWAS Commission headquarters is in Abuja."),
      q("pq_gov_21", "United Nations", "WAEC", 2020, "On which exact date is United Nations Day celebrated marking the 1945 UN Charter entry into force?", listOf("1st October", "24th October", "25th May", "28th May"), 1, "October 24, 1945.", "UN Charter entered into force October 24, 1945."),
      q("pq_gov_22", "Franchise", "JAMB", 2022, "A voting restriction granting suffrage ONLY to citizens paying specific income taxes or owning land is:", listOf("Universal adult suffrage", "Restricted / Property franchise", "Proportional representation", "Referendum"), 1, "Pre-democratic wealth bar.", "Property/tax gates restricted early suffrage (e.g., 1922 £100 rule)."),
      q("pq_gov_23", "AU Organs", "NECO", 2021, "Which organ of the African Union coordinates continental conflict intervention against coups and mass atrocities?", listOf("Trusteeship Council", "Peace and Security Council (PSC)", "Colonial Office", "Tariff Board"), 1, "AU equivalent of UNSC intervention arm.", "AU Peace & Security Council leads rapid conflict diplomacy & sanctions."),
      q("pq_gov_24", "Foreign Policy", "JAMB", 2021, "Which Nigerian foreign policy initiative deployed young Nigerian doctors, engineers, and teachers to ACP states?", listOf("Operation Feed the Nation", "Technical Aid Corps (TAC)", "SAP Structural Adjustment", "Green Revolution"), 1, "Launched 1987 soft-power volunteer corps.", "Technical Aid Corps exports skilled Nigerian human capital freely."),
    )
}
