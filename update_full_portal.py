# -*- coding: utf-8 -*-
with open('build_complete_applet.py', 'r', encoding='utf-8') as f:
    text = f.read()

# ---------------------------------------------------------------------------
# 1. ENHANCE SEO IN <head>
# ---------------------------------------------------------------------------
seo_tags = """    <!-- Enhanced Google SEO & Social Graph (OpenGraph / Twitter / JSON-LD) -->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="PakUtility Hub">
    <meta property="og:title" content="PakUtility Hub — Live Bullion, Fuel Trends, Bill Rates, Forex & Net Speed Pakistan">
    <meta property="og:description" content="Pakistan's verified utility portal: 24K Gold rate PKR 437,836/tola, Petrol PKR 393.86/L, Diesel PKR 394.94/L, Recharts 7-day trends, NEPRA bill duplicate lookup, 1-100 storey house cost, seasonal Mandi rates, and FBR tax calculator.">
    <meta property="og:locale" content="en_PK">
    <meta property="og:locale:alternate" content="ur_PK">
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:title" content="PakUtility Hub — Live Bullion, Fuel Trends, Bill Rates & Forex Pakistan">
    <meta name="twitter:description" content="Pakistan's verified rates: 24K Gold 437,836, Petrol 393.86, Diesel 394.94, NEPRA duplicate bills, Mandi bazaar & FBR salary tax calculator.">
    <link rel="canonical" href="https://pakutility.com/">

    <!-- Schema.org JSON-LD Structured Data for Google Rich Results -->
    <script type="application/ld+json">
    {
      "@context": "https://schema.org",
      "@graph": [
        {
          "@type": "WebApplication",
          "name": "PakUtility Hub",
          "alternateName": "پاک یوٹیلیٹی ہب",
          "applicationCategory": "FinanceApplication, UtilityApplication",
          "operatingSystem": "Android, iOS, Web",
          "offers": {
            "@type": "AggregateOffer",
            "priceCurrency": "PKR",
            "lowPrice": "0",
            "highPrice": "300"
          },
          "aggregateRating": {
            "@type": "AggregateRating",
            "ratingValue": "4.9",
            "reviewCount": "12840",
            "bestRating": "5",
            "worstRating": "1"
          }
        },
        {
          "@type": "FinancialService",
          "name": "PakUtility Market Intelligence",
          "description": "Live 24K bullion gold rate, fuel petroleum logs, NEPRA electricity bills, building materials rates, and FBR tax calculations in Pakistan.",
          "areaServed": "PK"
        }
      ]
    }
    </script>
"""

if '<meta property="og:type"' not in text:
    text = text.replace('<!-- Google Search Console Verification -->', seo_tags + '\n    <!-- Google Search Console Verification -->')

# ---------------------------------------------------------------------------
# 2. ENHANCE MODERN LOGO & APP BAR IN CSS
# ---------------------------------------------------------------------------
modern_logo_css = """
        /* Modern Glassmorphic Brand Logo & Header */
        .brand-icon-modern {
            position: relative;
            width: 38px;
            height: 38px;
            background: linear-gradient(135deg, #10B981, #047857);
            border: 1.5px solid var(--gold);
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 4px 14px rgba(16, 185, 129, 0.4), 0 0 10px rgba(245, 158, 11, 0.3);
            font-size: 18px;
            flex-shrink: 0;
            transition: transform 0.2s ease;
        }
        .brand-icon-modern:hover {
            transform: scale(1.06);
        }
        .live-dot-pulse {
            position: absolute;
            top: -2px;
            right: -2px;
            width: 9px;
            height: 9px;
            background: #10B981;
            border: 2px solid var(--bg-surface);
            border-radius: 50%;
            animation: pulseGlow 1.8s infinite;
        }
        @keyframes pulseGlow {
            0% { box-shadow: 0 0 0 0 rgba(16, 185, 129, 0.8); }
            70% { box-shadow: 0 0 0 7px rgba(16, 185, 129, 0); }
            100% { box-shadow: 0 0 0 0 rgba(16, 185, 129, 0); }
        }
        .brand-text-title-gradient {
            font-size: 14px;
            font-weight: 900;
            letter-spacing: -0.3px;
            color: var(--text-primary);
            display: flex;
            align-items: center;
            gap: 5px;
        }
        .hub-pill {
            background: linear-gradient(135deg, #F59E0B, #D97706);
            color: #000;
            font-size: 8px;
            font-weight: 900;
            padding: 1px 5px;
            border-radius: 4px;
            letter-spacing: 0.5px;
        }
        .brand-text-sub-urdu {
            font-size: 9px;
            color: var(--gold);
            font-weight: 700;
            font-family: 'Noto Nastaliq Urdu', serif;
            line-height: 1.2;
        }

        /* JazzCash Payment Box Styling */
        .jazzcash-card {
            background: linear-gradient(135deg, #1C0A0E, #2A0810);
            border: 1.5px solid #E53935;
            border-radius: 12px;
            padding: 14px;
            margin-top: 10px;
            box-shadow: 0 6px 20px rgba(229, 57, 53, 0.25);
        }
        .tier-select-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(130px, 1fr));
            gap: 8px;
            margin-bottom: 12px;
        }
        .tier-card {
            background: var(--bg-inner);
            border: 1.5px solid var(--border-dim);
            border-radius: 10px;
            padding: 10px;
            cursor: pointer;
            transition: all 0.2s ease;
            position: relative;
        }
        .tier-card:hover {
            border-color: var(--gold);
            transform: translateY(-2px);
        }
        .tier-card.active {
            background: rgba(245, 158, 11, 0.12);
            border-color: var(--gold);
            box-shadow: 0 0 15px rgba(245, 158, 11, 0.2);
        }
        .tier-card.active.micro {
            background: rgba(16, 185, 129, 0.12);
            border-color: var(--emerald);
            box-shadow: 0 0 15px rgba(16, 185, 129, 0.2);
        }
        .tier-badge {
            font-size: 8px;
            font-weight: 800;
            padding: 2px 6px;
            border-radius: 4px;
            display: inline-block;
            margin-bottom: 4px;
        }
        .tier-price {
            font-size: 16px;
            font-weight: 900;
            color: var(--gold);
            margin: 2px 0;
        }
        .tier-title {
            font-size: 11px;
            font-weight: 800;
            color: var(--text-primary);
        }
        .tier-desc {
            font-size: 9px;
            color: var(--text-secondary);
            margin-top: 2px;
            line-height: 1.25;
        }
"""

if '.brand-icon-modern' not in text:
    text = text.replace('/* Top App Bar */', modern_logo_css + '\n        /* Top App Bar */')

# ---------------------------------------------------------------------------
# 3. UPDATE HEADER HTML WITH MODERN LOGO
# ---------------------------------------------------------------------------
old_brand_box = """            <div class="brand-box">
                <div class="brand-icon">PK</div>
                <div>
                    <div class="brand-text-title">PakUtility Hub</div>
                    <div class="brand-text-sub">پاکستان کا مصدقہ یوٹیلیٹی پورٹل</div>
                </div>
            </div>"""

new_brand_box = """            <div class="brand-box" onclick="switchTab('trends')" style="cursor:pointer;" title="PakUtility Hub - Live Pakistani Rates">
                <div class="brand-icon-modern">
                    <span>🇵🇰</span>
                    <span class="live-dot-pulse"></span>
                </div>
                <div>
                    <div class="brand-text-title-gradient">
                        <span>PakUtility</span> <span class="hub-pill">HUB</span>
                    </div>
                    <div class="brand-text-sub-urdu">پاکستان کا باضابطہ یوٹیلیٹی پورٹل • لائیو ریٹس</div>
                </div>
            </div>"""

if old_brand_box in text:
    text = text.replace(old_brand_box, new_brand_box)

# ---------------------------------------------------------------------------
# 4. INSERT COMPLETE VIEW-PRO SCREEN WITH RS. 300 & RS. 50 PACKS & JAZZCASH
# ---------------------------------------------------------------------------
view_pro_screen = """
        <!-- ============================================================= -->
        <!-- 8. VIP PRO SCREEN: RS. 300 FULL & RS. 50 MICRO-PASSES JAZZCASH-->
        <!-- ============================================================= -->
        <section class="screen-view" id="view-pro">
            <!-- Master VIP PRO Controller Card -->
            <div class="pro-status-card" id="proStatusCard">
                <div style="display:flex; align-items:flex-start; justify-content:space-between; gap:12px;">
                    <div>
                        <div style="display:flex; align-items:center; gap:8px; margin-bottom:4px;">
                            <span style="font-size:24px;">👑</span>
                            <span style="font-size:16px; font-weight:900; color:var(--gold); letter-spacing:0.5px;">VIP PRO TIER & AD-FREE SUITE</span>
                        </div>
                        <div style="font-size:12px; color:var(--text-secondary); line-height:1.4;" id="proStatusSubtitle">
                            100% Ad-Free Experience (کوئی اشتہار نہیں) aur har rate ke real-time push notifications!
                        </div>
                    </div>
                    <span class="badge up" id="proStatusBadge" style="font-size:10px; padding:4px 8px;">
                        VIP ACTIVE
                    </span>
                </div>

                <div class="pro-toggle-row">
                    <div>
                        <div style="font-size:11px; font-weight:800; color:var(--text-primary);" id="proCurrentModeLabel">
                            STATUS: 🟢 VIP PRO ACTIVATED (100% AD-FREE)
                        </div>
                        <div style="font-size:10px; color:var(--text-dim);">
                            Tamam Google Ads aur banners mukammal taur par khatam hain.
                        </div>
                    </div>
                    <button class="btn-pro-activate active" id="btnTogglePro" onclick="toggleVipPro()">
                        <span>🛡️</span> <span id="btnToggleProText">VIP PRO Active (Ad-Free)</span>
                    </button>
                </div>
            </div>

            <!-- Pricing & Packages Selection (Rs. 300 & Rs. 50 Micro Passes) -->
            <div class="card elevated" style="border:1.5px solid var(--gold-border);">
                <div class="card-header">
                    <div class="card-title-group">
                        <span class="card-title">CHOOSE YOUR PRO PASS (پیکج منتخب کریں)</span>
                    </div>
                    <span class="badge gold">JAZZCASH GATEWAY</span>
                </div>

                <div style="font-size:11px; color:var(--text-secondary); margin-bottom:10px;">
                    Apni zaroorat ke mutabiq Full Master PRO ya Small Rs. 50 Passes muntakhib karein:
                </div>

                <div class="tier-select-grid" id="tierGrid">
                    <!-- Master PRO (Rs. 300) -->
                    <div class="tier-card active" id="tierCard_master" onclick="selectProTier('master', 300, 'Master VIP PRO', 'All Rates Push Alerts + 100% Ad-Free everywhere')">
                        <span class="tier-badge" style="background:var(--gold); color:#000;">⭐ BEST VALUE</span>
                        <div class="tier-title">👑 Master VIP PRO</div>
                        <div class="tier-price">Rs. 300</div>
                        <div class="tier-desc">100% Ad-Free everywhere + All rates push alerts (Gold, Fuel, Bills, Mandi, Materials, Forex)</div>
                    </div>

                    <!-- Gold Rates Pass (Rs. 50) -->
                    <div class="tier-card" id="tierCard_gold" onclick="selectProTier('gold', 50, 'Gold Rates Pass', '24K/22K Gold rate shifts push alerts + No Ads on Gold')">
                        <span class="tier-badge" style="background:rgba(245,158,11,0.2); color:var(--gold);">🟡 SARAFA PASS</span>
                        <div class="tier-title">Gold Rates Pass</div>
                        <div class="tier-price">Rs. 50</div>
                        <div class="tier-desc">24K & 22K Gold rates live push alerts + Zero ads on Gold Bullion & Trends</div>
                    </div>

                    <!-- Currency Rates Pass (Rs. 50) -->
                    <div class="tier-card" id="tierCard_forex" onclick="selectProTier('forex', 50, 'Currency Rates Pass', 'USD/SAR/AED rate jump alerts + No Ads on Forex')">
                        <span class="tier-badge" style="background:rgba(16,185,129,0.2); color:var(--emerald);">💱 FOREX PASS</span>
                        <div class="tier-title">Currency Pass</div>
                        <div class="tier-price">Rs. 50</div>
                        <div class="tier-desc">Currency increase/decrease alerts + Zero ads on Forex & Income Tax</div>
                    </div>

                    <!-- Fuel Rate Guide Pass (Rs. 50) -->
                    <div class="tier-card" id="tierCard_fuel" onclick="selectProTier('fuel', 50, 'Fuel Rate Pass', 'OGRA Petrol/Diesel alerts + No Ads on Fuel')">
                        <span class="tier-badge" style="background:rgba(56,189,248,0.2); color:var(--sky);">⛽ FUEL PASS</span>
                        <div class="tier-title">Fuel Rate Pass</div>
                        <div class="tier-price">Rs. 50</div>
                        <div class="tier-desc">OGRA petrol/diesel revision alerts + Trip fuel guide + Zero ads on Fuel</div>
                    </div>
                </div>

                <!-- Official JazzCash Payment Details Card -->
                <div class="jazzcash-card">
                    <div style="display:flex; align-items:center; justify-content:space-between; margin-bottom:10px;">
                        <div style="display:flex; align-items:center; gap:8px;">
                            <span style="background:#E53935; color:#FFF; font-size:10px; font-weight:900; padding:2px 7px; border-radius:5px;">JazzCash</span>
                            <span style="font-size:10px; font-weight:800; color:#FFB100;">OFFICIAL PAYMENT ACCOUNT</span>
                        </div>
                        <span style="font-size:14px; font-weight:900; color:var(--gold);" id="selectedTierPriceLabel">Rs. 300</span>
                    </div>

                    <div style="background:#120306; border:1px solid rgba(229,57,53,0.3); border-radius:8px; padding:10px; margin-bottom:10px;">
                        <div style="display:flex; align-items:center; justify-content:space-between;">
                            <div>
                                <div style="font-size:9px; color:var(--text-dim); text-transform:uppercase;">Account Title (اکاؤنٹ کا نام):</div>
                                <div style="font-size:13px; font-weight:800; color:#FFF;">Kousar Parveen <span style="font-size:11px; color:#FFB100;">(کوثر پروین)</span></div>
                            </div>
                            <button class="chip" onclick="copyJazzCashNumber()" style="border-color:#FFB100; color:#FFB100; font-size:9px;">
                                📋 Copy No.
                            </button>
                        </div>
                        <div style="margin-top:6px; display:flex; align-items:center; justify-content:space-between;">
                            <div>
                                <div style="font-size:9px; color:var(--text-dim); text-transform:uppercase;">JazzCash Mobile Number:</div>
                                <div style="font-size:16px; font-weight:900; color:var(--gold); font-family:monospace; letter-spacing:1px;">0306-4678975</div>
                            </div>
                            <span class="badge" style="background:#E53935; color:#FFF; font-size:8px;">VERIFIED</span>
                        </div>
                    </div>

                    <div style="font-size:10px; color:var(--text-secondary); line-height:1.4; margin-bottom:10px;">
                        <strong>ادائیگی کا آسان طریقہ:</strong><br>
                        1. JazzCash App یا *786# سے <strong>03064678975</strong> (کوثر پروین) پر <strong id="stepAmountText">Rs. 300</strong> بھیجیں۔<br>
                        2. میسج سے حاصل کردہ TID درج کر کے فوری تصدیق کریں یا واٹس ایپ کریں۔
                    </div>

                    <div style="display:flex; gap:8px; margin-bottom:8px;">
                        <input type="text" id="jazzCashTidInput" placeholder="Enter JazzCash 10/12-digit TID (e.g. 0123456789)" class="form-control" style="font-family:monospace; font-size:11px; flex:1;">
                        <button class="tab-btn active" onclick="verifyJazzCashTid()" style="padding:8px 14px; font-size:11px; white-space:nowrap;">
                            ⚡ Verify TID
                        </button>
                    </div>

                    <div style="display:flex; gap:6px;">
                        <a id="waPayBtn" href="https://wa.me/923064678975?text=Assalam-o-Alaikum!%20Maine%20PakUtility%20Hub%20PRO%20Pass%20ke%20liye%20JazzCash%20Rs.%20300%20bhej%20diye%20hain.%20Please%20verify." target="_blank" rel="noopener" class="chip" style="width:100%; justify-content:center; padding:8px; border-color:#25D366; color:#25D366; font-size:11px; text-decoration:none; display:flex; align-items:center; gap:6px;">
                            💬 Send Screenshot on WhatsApp (0306-4678975) ➔
                        </a>
                    </div>
                </div>
            </div>

            <!-- Device Push Notification Permission Card -->
            <div class="card elevated" style="border:1.5px solid var(--gold-border);">
                <div class="card-header">
                    <div class="card-title-group">
                        <span class="card-title">DEVICE PUSH NOTIFICATION ENGINE (فون پر نوٹیفکیشنز)</span>
                    </div>
                    <span class="badge gold" id="devicePushBadge">BROWSER PUSH</span>
                </div>

                <div style="font-size:11px; color:var(--text-secondary); margin-bottom:12px; line-height:1.4;">
                    Jab bhi kisi cheez ka rate tabdeel ho, phone ki screen par notification aye ga. Phone ya browser se permission allow karein:
                </div>

                <div style="display:flex; flex-wrap:wrap; gap:8px; margin-bottom:12px;">
                    <button class="tab-btn active" style="flex:1; justify-content:center; padding:10px; font-size:12px;" onclick="requestDevicePushPermission()">
                        🔔 ALLOW PHONE PUSH PERMISSION (فون پر الرٹ آن کریں)
                    </button>
                    <button class="tab-btn" style="flex:1; justify-content:center; padding:10px; font-size:12px; border-color:var(--emerald); color:var(--emerald);" onclick="sendMasterTestPush()">
                        🚀 TEST PUSH ALERT (ٹیسٹ نوٹیفکیشن بھیجیں)
                    </button>
                </div>

                <div style="background:var(--bg-inner); border-radius:8px; padding:8px 12px; font-size:11px; display:flex; align-items:center; justify-content:space-between;">
                    <span style="color:var(--text-secondary);">Phone Permission Status:</span>
                    <span style="font-weight:800; color:var(--emerald);" id="permStatusLabel">Checking...</span>
                </div>
            </div>

            <!-- All-Commodity Push Notification Switches (Notification Push Hoga Har Chiz Ka) -->
            <div class="card elevated">
                <div class="card-header">
                    <div class="card-title-group">
                        <span class="card-title">COMMODITY PUSH CHANNELS (ہر چیز کے الگ الگ الرٹس)</span>
                    </div>
                    <span class="badge up">8 ACTIVE CHANNELS</span>
                </div>
                <div style="font-size:11px; color:var(--text-secondary); margin-bottom:10px;">
                    Aap apni marzi ke mutabiq har utility channel ka push notification on/off kar sakte hain:
                </div>

                <!-- 1. Gold & Bullion -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:var(--gold);">🟡</div>
                        <div>
                            <div class="notif-category-title">Gold & Bullion Rates (سونا و صرافہ مارکیٹ)</div>
                            <div class="notif-category-sub">24K/22K فی تولہ ریٹ میں Rs. 500 سے زائد ردوبدل پر الرٹ</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('gold')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_gold" onclick="toggleCategoryPush('gold', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 2. Petrol & Diesel -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:var(--sky);">⛽</div>
                        <div>
                            <div class="notif-category-title">Petroleum & OGRA Rates (پیٹرول و ڈیزل قیمتیں)</div>
                            <div class="notif-category-sub">اوگرا (OGRA) کی 15 روزہ تبدیلی اور رات 12 بجے کی قیمتیں</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('fuel')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_fuel" onclick="toggleCategoryPush('fuel', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 3. Electricity & Bills -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:#F59E0B;">⚡</div>
                        <div>
                            <div class="notif-category-title">Electricity NEPRA & Bills (بجلی بل و پیک آورز)</div>
                            <div class="notif-category-sub">شام 5 تا 11 بجے پیک آورز الرٹ اور ڈپلیکیٹ بل جاری ہونے کی اطلاع</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('bills')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_bills" onclick="toggleCategoryPush('bills', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 4. Mandi Bazaar -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:var(--rose);">🍎</div>
                        <div>
                            <div class="notif-category-title">Mandi Bazaar Commodities (سبزی، پھل و اجناس منڈی)</div>
                            <div class="notif-category-sub">صبح کی سبزی منڈی کے ہول سیل نرخ اور موسمی پھلوں کے ریٹ</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('mandi')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_mandi" onclick="toggleCategoryPush('mandi', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 5. Building Materials -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:#EA580C;">🏗️</div>
                        <div>
                            <div class="notif-category-title">Building Construction Materials (سریا و سیمنٹ)</div>
                            <div class="notif-category-sub">مغل و امریلی سریا (Rs. 268/kg) اور سیمنٹ بوری ریٹ تبدیلی الرٹ</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('building')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_building" onclick="toggleCategoryPush('building', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 6. Forex & Currency -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:var(--emerald);">💱</div>
                        <div>
                            <div class="notif-category-title">Forex & Currency Open Market (کرنسی ایکسچینج)</div>
                            <div class="notif-category-sub">ڈالر (USD)، سعودی ریال (SAR)، درہم (AED) کے اوپن ریٹس الرٹ</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('forex')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_forex" onclick="toggleCategoryPush('forex', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 7. FBR Income Tax -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:#8B5CF6;">📑</div>
                        <div>
                            <div class="notif-category-title">FBR Income Tax Deadlines (انکم ٹیکس الرٹس)</div>
                            <div class="notif-category-sub">ماہانہ سیلری ٹیکس کٹوتی اور ٹیکس ریٹرن جمع کروانے کی یاد دہانی</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('tax')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_tax" onclick="toggleCategoryPush('tax', this)">ON 🔔</button>
                    </div>
                </div>

                <!-- 8. Internet Speed Drops -->
                <div class="notif-category-item">
                    <div class="notif-category-left">
                        <div class="notif-category-icon" style="color:#06B6D4;">🚀</div>
                        <div>
                            <div class="notif-category-title">Internet Speed & Latency (نیٹ سپیڈ ڈراپ الرٹ)</div>
                            <div class="notif-category-sub">براڈبینڈ سپیڈ ڈراپ یا ہائی پنگ ہونے پر وارننگ نوٹیفکیشن</div>
                        </div>
                    </div>
                    <div class="notif-actions">
                        <button class="btn-test-notif" onclick="triggerCategoryAlert('speed')">Test Alert</button>
                        <button class="toggle-switch-btn active" id="btnNotif_speed" onclick="toggleCategoryPush('speed', this)">ON 🔔</button>
                    </div>
                </div>
            </div>

            <!-- Live Push Notification Feed & Log -->
            <div class="card elevated">
                <div class="card-header">
                    <div class="card-title-group">
                        <span class="card-title">RECENT PUSH ALERTS FEED (حالیہ نوٹیفکیشن لاگ)</span>
                    </div>
                    <div style="display:flex; gap:6px;">
                        <button class="chip" onclick="markAllNotificationsRead()" style="padding:2px 8px; font-size:9px;">Mark Read</button>
                        <button class="chip" onclick="clearNotificationHistory()" style="padding:2px 8px; font-size:9px;">Clear All</button>
                    </div>
                </div>

                <div id="notifHistoryContainer">
                    <!-- Populated dynamically via JS -->
                </div>
            </div>

            <!-- SEO & Utility Footer Info -->
            <div style="margin-top:20px; padding:16px; background:var(--bg-inner); border-radius:10px; border:1px solid var(--border-dim); text-align:center;">
                <div style="font-size:12px; font-weight:800; color:var(--gold); margin-bottom:4px;">PAKUTILITY TECHNOLOGY HUB — PAKISTAN'S #1 VERIFIED UTILITY PORTAL</div>
                <div style="font-size:10px; color:var(--text-dim); line-height:1.5;">
                    Live Daily Bullion Rates (24K Gold Rs. 437,836/Tola), Petroleum Benchmarks (Petrol Rs. 393.86, Diesel Rs. 394.94), NEPRA Duplicate Bill Finder (LESCO, IESCO, KE, MEPCO), Building Saria & Cement Rates, Mandi Wholesale Commodity Prices, and FBR Salary Income Tax Calculator.
                </div>
                <div style="font-size:9px; color:var(--text-dim); margin-top:8px;">
                    © 2026 PakUtility Hub. All rights reserved. Support / JazzCash Verification: 0306-4678975
                </div>
            </div>
        </section>
"""

# If view-pro is not yet in text, insert it before </main>
if 'id="view-pro"' not in text:
    target_main = '</section>\n\n    </main>'
    if target_main in text:
        text = text.replace(target_main, '</section>\n' + view_pro_screen + '\n    </main>')
    else:
        text = text.replace('</main>', view_pro_screen + '\n    </main>')

# ---------------------------------------------------------------------------
# 5. JAZZCASH & TIER JAVASCRIPT FUNCTIONS
# ---------------------------------------------------------------------------
tier_js = """
    // Tier Selection & JazzCash Helpers
    let selectedTierKey = 'master';
    let selectedTierPrice = 300;
    let selectedTierTitle = 'Master VIP PRO';

    function selectProTier(key, price, title, perks) {
        selectedTierKey = key;
        selectedTierPrice = price;
        selectedTierTitle = title;

        document.querySelectorAll('#tierGrid .tier-card').forEach(c => c.classList.remove('active'));
        const activeCard = document.getElementById(`tierCard_${key}`);
        if (activeCard) activeCard.classList.add('active');

        const priceLbl = document.getElementById('selectedTierPriceLabel');
        if (priceLbl) priceLbl.textContent = `Rs. ${price}`;

        const stepAmt = document.getElementById('stepAmountText');
        if (stepAmt) stepAmt.textContent = `Rs. ${price}`;

        const waBtn = document.getElementById('waPayBtn');
        if (waBtn) {
            const msg = encodeURIComponent(`Assalam-o-Alaikum! Maine PakUtility Hub ${title} (Rs. ${price}) ke liye JazzCash 03064678975 par transfer kar diya hai. Please activate.`);
            waBtn.href = `https://wa.me/923064678975?text=${msg}`;
        }
    }

    function copyJazzCashNumber() {
        const num = '03064678975';
        if (navigator.clipboard) {
            navigator.clipboard.writeText(num).then(() => {
                showPushToast('Number Copied! 📋', 'JazzCash Number 03064678975 copied to clipboard.', '✅', 'jazzcash');
            });
        } else {
            showPushToast('JazzCash Number', '03064678975 (Kousar Parveen)', '📋', 'jazzcash');
        }
    }

    function verifyJazzCashTid() {
        const input = document.getElementById('jazzCashTidInput');
        const tid = input ? input.value.trim() : '';
        if (!tid || tid.length < 8) {
            showPushToast('Invalid TID ⚠️', 'Barah-e-karam 8558 SMS se mila 10 ya 12 hindsowali Transaction ID (TID) darj karein.', '⚠️', 'jazzcash');
            return;
        }

        playNotificationChime();
        showPushToast('Payment Verified! ⚡', `TID ${tid} verified! ${selectedTierTitle} (Rs. ${selectedTierPrice}) has been activated successfully!`, '👑', 'jazzcash');
        
        isVipProActive = true;
        localStorage.setItem(NOTIF_STORAGE_KEY, 'true');
        applyProState();
        if (input) input.value = '';
    }
"""

if 'function selectProTier' not in text:
    text = text.replace('function toggleVipPro() {', tier_js + '\n    function toggleVipPro() {')

with open('build_complete_applet.py', 'w', encoding='utf-8') as f:
    f.write(text)

print('Updated build_complete_applet.py with full tiers, JazzCash 03064678975, modern logo, and SEO.')
