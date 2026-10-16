# -*- coding: utf-8 -*-
import sys

with open('build_complete_applet.py', 'r', encoding='utf-8') as f:
    code = f.read()

# ---------------------------------------------------------------------------
# 1. ADD CSS FOR PRO MODE, AD-FREE REMOVAL & PUSH NOTIFICATIONS
# ---------------------------------------------------------------------------
pro_css = """
        /* =========================================
           VIP PRO MODE: 100% AD-FREE & NOTIFICATIONS
           ========================================= */
        body.vip-pro-active .ad-banner-space,
        body.vip-pro-active .ad-placeholder-label,
        body.vip-pro-active ins.adsbygoogle,
        body.vip-pro-active [class*="ad-"] {
            display: none !important;
            height: 0 !important;
            margin: 0 !important;
            padding: 0 !important;
            border: none !important;
            opacity: 0 !important;
            pointer-events: none !important;
            visibility: hidden !important;
        }

        .vip-pro-banner {
            display: none;
            background: linear-gradient(135deg, rgba(16, 185, 129, 0.16), rgba(6, 95, 70, 0.28));
            border: 1px solid var(--emerald);
            border-radius: var(--radius-sm);
            padding: 8px 12px;
            margin: 6px 12px;
            align-items: center;
            justify-content: space-between;
            animation: fadeIn 0.3s ease;
        }
        body.vip-pro-active .vip-pro-banner {
            display: flex !important;
        }

        /* Floating Push Notification Toast */
        .push-toast-container {
            position: fixed;
            top: 55px;
            left: 50%;
            transform: translateX(-50%) translateY(-120px);
            width: calc(100% - 24px);
            max-width: 440px;
            background: rgba(15, 23, 42, 0.96);
            backdrop-filter: blur(16px);
            -webkit-backdrop-filter: blur(16px);
            border: 1.5px solid var(--gold);
            box-shadow: 0 12px 36px rgba(0, 0, 0, 0.7), 0 0 25px rgba(245, 158, 11, 0.25);
            border-radius: 12px;
            padding: 12px 14px;
            z-index: 9999;
            transition: transform 0.35s cubic-bezier(0.16, 1, 0.3, 1), opacity 0.35s ease;
            opacity: 0;
            pointer-events: none;
            display: flex;
            align-items: flex-start;
            gap: 12px;
        }
        .push-toast-container.show {
            transform: translateX(-50%) translateY(0);
            opacity: 1;
            pointer-events: auto;
        }
        [data-theme="light"] .push-toast-container {
            background: rgba(255, 255, 255, 0.98);
            border-color: var(--gold);
        }
        .push-toast-icon {
            font-size: 22px;
            flex-shrink: 0;
            line-height: 1;
            padding-top: 2px;
        }
        .push-toast-body {
            flex: 1;
            min-width: 0;
        }
        .push-toast-title {
            font-size: 12px;
            font-weight: 800;
            color: var(--gold);
            margin-bottom: 2px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .push-toast-msg {
            font-size: 11px;
            color: var(--text-primary);
            line-height: 1.4;
        }
        .push-toast-close {
            background: transparent;
            border: none;
            color: var(--text-dim);
            font-size: 16px;
            cursor: pointer;
            padding: 0 4px;
            line-height: 1;
        }

        /* PRO UI Components */
        .pro-status-card {
            background: linear-gradient(135deg, rgba(245, 158, 11, 0.12), rgba(16, 185, 129, 0.10));
            border: 1.5px solid var(--gold);
            border-radius: var(--radius-md);
            padding: 16px;
            margin-bottom: 14px;
            position: relative;
            overflow: hidden;
        }
        .pro-toggle-row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 12px;
            margin-top: 10px;
        }
        .btn-pro-activate {
            background: linear-gradient(135deg, #F59E0B, #D97706);
            color: #000;
            border: none;
            border-radius: 8px;
            padding: 10px 18px;
            font-size: 13px;
            font-weight: 900;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 6px;
            box-shadow: 0 4px 15px rgba(245, 158, 11, 0.35);
            transition: all 0.2s ease;
        }
        .btn-pro-activate:hover {
            transform: translateY(-1px);
            box-shadow: 0 6px 20px rgba(245, 158, 11, 0.5);
        }
        .btn-pro-activate.active {
            background: linear-gradient(135deg, #10B981, #059669);
            color: #FFF;
            box-shadow: 0 4px 15px rgba(16, 185, 129, 0.35);
        }

        .notif-category-item {
            background: var(--bg-inner);
            border: 1px solid var(--border-dim);
            border-radius: var(--radius-sm);
            padding: 12px;
            margin-bottom: 8px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 12px;
            transition: all 0.2s ease;
        }
        .notif-category-item:hover {
            border-color: var(--gold-border);
        }
        .notif-category-left {
            display: flex;
            align-items: center;
            gap: 10px;
            flex: 1;
            min-width: 0;
        }
        .notif-category-icon {
            font-size: 20px;
            width: 36px;
            height: 36px;
            border-radius: 8px;
            background: rgba(245, 158, 11, 0.12);
            display: flex;
            align-items: center;
            justify-content: center;
            flex-shrink: 0;
        }
        .notif-category-title {
            font-size: 12px;
            font-weight: 800;
            color: var(--text-primary);
        }
        .notif-category-sub {
            font-size: 10px;
            color: var(--text-secondary);
            margin-top: 2px;
        }
        .notif-actions {
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .btn-test-notif {
            background: rgba(245, 158, 11, 0.12);
            border: 1px solid var(--gold-border);
            color: var(--gold);
            border-radius: 6px;
            padding: 5px 9px;
            font-size: 10px;
            font-weight: 800;
            cursor: pointer;
            white-space: nowrap;
        }
        .btn-test-notif:hover {
            background: var(--gold);
            color: #000;
        }
        .toggle-switch-btn {
            background: rgba(148, 163, 184, 0.2);
            border: 1px solid var(--border-dim);
            color: var(--text-secondary);
            border-radius: 14px;
            padding: 4px 10px;
            font-size: 10px;
            font-weight: 800;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 4px;
            min-width: 52px;
            justify-content: center;
        }
        .toggle-switch-btn.active {
            background: rgba(16, 185, 129, 0.2);
            border-color: var(--emerald);
            color: var(--emerald);
        }

        .notif-history-item {
            background: var(--bg-inner);
            border-left: 3px solid var(--gold);
            border-radius: 6px;
            padding: 10px 12px;
            margin-bottom: 6px;
            display: flex;
            align-items: flex-start;
            justify-content: space-between;
            gap: 8px;
        }
        .notif-history-item.unread {
            background: rgba(245, 158, 11, 0.08);
            border-left-color: var(--gold);
        }
        .notif-history-item.read {
            border-left-color: var(--border-dim);
            opacity: 0.75;
        }
"""

css_anchor = '/* Reduced Google Ads Container (Compact Space) */'
code = code.replace(css_anchor, pro_css + '\n        ' + css_anchor)

# ---------------------------------------------------------------------------
# 2. ADD FLOATING TOAST RIGHT AFTER BODY
# ---------------------------------------------------------------------------
toast_html = """
    <!-- Floating Push Notification Toast (Heads-Up Banner) -->
    <div class="push-toast-container" id="pushToastAlert" onclick="switchTab('pro')">
        <div class="push-toast-icon" id="pushToastIcon">🔔</div>
        <div class="push-toast-body">
            <div class="push-toast-title">
                <span id="pushToastTitle">PakUtility VIP Alert</span>
                <span style="font-size:9px; color:var(--text-dim);" id="pushToastTime">Just now</span>
            </div>
            <div class="push-toast-msg" id="pushToastMsg">Live rate push alert updated.</div>
        </div>
        <button class="push-toast-close" onclick="event.stopPropagation(); hidePushToast();">&times;</button>
    </div>
"""
code = code.replace('<body>', '<body>\n' + toast_html)

# ---------------------------------------------------------------------------
# 3. UPDATE TOP NAVBAR (ADD 👑 PRO BUTTON & 🔔 BELL ICON)
# ---------------------------------------------------------------------------
old_nav_right = """        <div class="nav-right">
            <button class="theme-toggle-btn" onclick="forceCacheBustRefresh()" title="Force Refresh & Clear Mobile Cache (کیشے صاف کریں)" style="background:rgba(217,119,6,0.18); border:1px solid rgba(217,119,6,0.5); color:var(--gold); font-size:10px; padding:4px 8px; width:auto; height:28px; font-weight:800; border-radius:6px; cursor:pointer;">
                🔄 Sync
            </button>
            <span class="badge gold" style="font-size:8px;">OCT 16 LIVE</span>
            <button class="theme-toggle-btn" id="themeToggleBtn" onclick="toggleTheme()" title="Toggle Dark/Light Mode">
                <span id="themeToggleIcon">☀️</span>
            </button>
        </div>"""

new_nav_right = """        <div class="nav-right">
            <button class="theme-toggle-btn" onclick="switchTab('pro')" id="navProBtn" title="VIP PRO Mode (Zero Ads & Live Push Alerts)" style="background:linear-gradient(135deg, rgba(245,158,11,0.22), rgba(16,185,129,0.18)); border:1.2px solid var(--gold); color:var(--gold); font-size:10px; padding:4px 8px; width:auto; height:28px; font-weight:900; border-radius:6px; cursor:pointer;">
                👑 PRO
            </button>
            <button class="theme-toggle-btn" onclick="switchTab('pro')" title="Notification Center (تمام الرٹس)" style="position:relative; width:30px; height:28px; font-size:13px; display:inline-flex; align-items:center; justify-content:center;">
                <span>🔔</span>
                <span id="navNotifBadge" style="position:absolute; top:-3px; right:-3px; background:var(--rose); color:#FFF; font-size:8px; font-weight:900; border-radius:50%; width:14px; height:14px; display:flex; align-items:center; justify-content:center;">8</span>
            </button>
            <button class="theme-toggle-btn" onclick="forceCacheBustRefresh()" title="Force Refresh & Clear Mobile Cache (کیشے صاف کریں)" style="background:rgba(217,119,6,0.18); border:1px solid rgba(217,119,6,0.5); color:var(--gold); font-size:10px; padding:4px 8px; width:auto; height:28px; font-weight:800; border-radius:6px; cursor:pointer;">
                🔄 Sync
            </button>
            <button class="theme-toggle-btn" id="themeToggleBtn" onclick="toggleTheme()" title="Toggle Dark/Light Mode">
                <span id="themeToggleIcon">☀️</span>
            </button>
        </div>"""

code = code.replace(old_nav_right, new_nav_right)

# ---------------------------------------------------------------------------
# 4. ADD VIP PRO AD-FREE BANNER UNDER HEADER
# ---------------------------------------------------------------------------
old_ad_banner = """    <!-- Reduced Compact Google AdSense Space (Header) -->
    <div class="ad-banner-space">
        <span class="ad-placeholder-label">Google AdSense Space (468x45 Compact Banner)</span>
    </div>"""

new_ad_banner = """    <!-- Reduced Compact Google AdSense Space (Header) - Hidden in PRO Mode -->
    <div class="ad-banner-space" id="headerAdSpace">
        <span class="ad-placeholder-label">Google AdSense Space (468x45 Compact Banner)</span>
    </div>
    <!-- VIP PRO 100% Ad-Free Banner -->
    <div class="vip-pro-banner" id="vipProBannerTop">
        <div style="display:flex; align-items:center; gap:8px;">
            <span style="font-size:16px;">🛡️</span>
            <div>
                <div style="font-size:11px; font-weight:800; color:var(--emerald);">VIP PRO ACTIVE: 100% AD-FREE & REAL-TIME PUSH ALERTS</div>
                <div style="font-size:9px; color:var(--text-secondary);">Tamam ads block hain. Har chiz ke push notifications on hain.</div>
            </div>
        </div>
        <button class="chip" onclick="switchTab('pro')" style="padding:2px 8px; font-size:9px; border-color:var(--emerald); color:var(--emerald);">SETTINGS ⚙️</button>
    </div>"""

code = code.replace(old_ad_banner, new_ad_banner)

# ---------------------------------------------------------------------------
# 5. ADD PRO TAB TO TAB BAR, DRAWER, AND BOTTOM NAV
# ---------------------------------------------------------------------------
code = code.replace(
    """<button class="tab-btn" data-tab="forex" onclick="switchTab('forex')">💱 Forex & FBR Tax</button>""",
    """<button class="tab-btn" data-tab="forex" onclick="switchTab('forex')">💱 Forex & FBR Tax</button>\n        <button class="tab-btn" data-tab="pro" onclick="switchTab('pro')">👑 VIP PRO & Push Alerts</button>"""
)

code = code.replace(
    """<div class="drawer-menu-item" onclick="switchTab('forex'); toggleDrawer(false);">💱 Currency Chart & FBR Income Tax</div>""",
    """<div class="drawer-menu-item" onclick="switchTab('forex'); toggleDrawer(false);">💱 Currency Chart & FBR Income Tax</div>\n            <div class="drawer-menu-item" onclick="switchTab('pro'); toggleDrawer(false);">👑 VIP PRO & Push Alerts (100% Ad-Free)</div>"""
)

code = code.replace(
    """        <button class="b-nav-item" onclick="switchTab('building')">
            <span class="icon">🏗️</span><span>House</span>
        </button>""",
    """        <button class="b-nav-item" onclick="switchTab('building')">
            <span class="icon">🏗️</span><span>House</span>
        </button>
        <button class="b-nav-item" onclick="switchTab('pro')">
            <span class="icon">👑</span><span>PRO</span>
        </button>"""
)

# ---------------------------------------------------------------------------
# 6. ADD VIEW-PRO SCREEN (SCREEN 8)
# ---------------------------------------------------------------------------
pro_screen_html = """
        <!-- ============================================================= -->
        <!-- 8. VIP PRO SCREEN: 100% AD-FREE & ALL-COMMODITY PUSH ALERTS   -->
        <!-- ============================================================= -->
        <section class="screen-view" id="view-pro">
            <!-- Master VIP PRO Controller Card -->
            <div class="pro-status-card" id="proStatusCard">
                <div style="display:flex; align-items:flex-start; justify-content:space-between; gap:12px;">
                    <div>
                        <div style="display:flex; align-items:center; gap:8px; margin-bottom:4px;">
                            <span style="font-size:24px;">👑</span>
                            <span style="font-size:16px; font-weight:900; color:var(--gold); letter-spacing:0.5px;">VIP PRO TIER CONTROLLER</span>
                        </div>
                        <div style="font-size:12px; color:var(--text-secondary); line-height:1.4;" id="proStatusSubtitle">
                            100% Ad-Free Experience (کوئی اشتہار نہیں) aur har commodity ke real-time push notifications!
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
                            Google AdSense banners removed across the portal.
                        </div>
                    </div>
                    <button class="btn-pro-activate" id="btnTogglePro" onclick="toggleVipPro()">
                        <span>👑</span> <span id="btnToggleProText">VIP PRO Activated (Ad-Free)</span>
                    </button>
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
        </section>
"""

# Insert view-pro right after </section> of view-forex
end_view_forex_str = "</section>    </main>"
code = code.replace(end_view_forex_str, "</section>\n" + pro_screen_html + "\n    </main>")

# ---------------------------------------------------------------------------
# 7. ADD JAVASCRIPT LOGIC FOR PRO MODE, AUDIO CHIME, AND PUSH NOTIFICATIONS
# ---------------------------------------------------------------------------
pro_js = """
    // =============================================================
    // VIP PRO CONTROLLER: 100% AD-FREE & REAL-TIME PUSH NOTIFICATIONS
    // =============================================================
    let isVipProActive = true; // Default PRO enabled as requested
    const NOTIF_STORAGE_KEY = 'pakutility_vip_pro_enabled';
    const NOTIF_CHANNELS_KEY = 'pakutility_push_channels';

    let enabledPushChannels = {
        gold: true,
        fuel: true,
        bills: true,
        mandi: true,
        building: true,
        forex: true,
        tax: true,
        speed: true
    };

    let pushAlertHistory = [
        { id: 1, cat: 'gold', icon: '🟡', title: '24K Gold Rate Shift', body: '24K Gold surged to Rs. 437,836/Tola (+Rs. 1,200). Sarafa market updated.', time: '2m ago', read: false },
        { id: 2, cat: 'fuel', icon: '⛽', title: 'OGRA Fuel Notification', body: 'Super Petrol Rs. 393.86/L & HSD Diesel Rs. 394.94/L verified live.', time: '14m ago', read: false },
        { id: 3, cat: 'bills', icon: '⚡', title: 'Peak Hours Reminder (5 PM - 11 PM)', body: 'NEPRA peak rate Rs. 65/unit active. Heavy appliances off rakhein.', time: '45m ago', read: false },
        { id: 4, cat: 'mandi', icon: '🍎', title: 'Mandi Opening Rates', body: 'New Potato arrival Rs. 95/kg, Kinnow Rs. 140/dozen wholesale.', time: '2h ago', read: true },
        { id: 5, cat: 'building', icon: '🏗️', title: 'Steel Rebar Benchmark', body: 'Mughal Grade 60 Rebar at Rs. 268/kg, Lucky Cement Rs. 1,490/bag.', time: '3h ago', read: true },
        { id: 6, cat: 'forex', icon: '💱', title: 'US Dollar (USD) Open Market', body: 'USD bought at PKR 279.10, sold at PKR 280.20.', time: '5h ago', read: true },
        { id: 7, cat: 'tax', icon: '📑', title: 'FBR Salaried Slabs Alert', body: 'Finance Act 2024-2026 tax slab calculation armed.', time: '8h ago', read: true },
        { id: 8, cat: 'speed', icon: '🚀', title: 'Net Speed Benchmark', body: '1-Minute high-precision test completed: Jitter 2ms, Ping 18ms.', time: '12h ago', read: true }
    ];

    function initVipPro() {
        const storedPro = localStorage.getItem(NOTIF_STORAGE_KEY);
        if (storedPro !== null) {
            isVipProActive = storedPro === 'true';
        } else {
            isVipProActive = true;
            localStorage.setItem(NOTIF_STORAGE_KEY, 'true');
        }

        const storedChannels = localStorage.getItem(NOTIF_CHANNELS_KEY);
        if (storedChannels) {
            try { enabledPushChannels = JSON.parse(storedChannels); } catch(e){}
        }

        applyProState();
        updatePushPermissionStatus();
        renderNotifHistory();
    }

    function applyProState() {
        if (isVipProActive) {
            document.body.classList.add('vip-pro-active');
            document.documentElement.classList.add('vip-pro-active');
            const badge = document.getElementById('proStatusBadge');
            if (badge) { badge.textContent = '100% AD-FREE & ALERTS ACTIVE'; badge.className = 'badge up'; }
            const sub = document.getElementById('proStatusSubtitle');
            if (sub) sub.textContent = '👑 VIP PRO ACTIVE: All Google Ads removed. Unlimited live push alerts enabled for all 8 utilities.';
            const lbl = document.getElementById('proCurrentModeLabel');
            if (lbl) lbl.textContent = 'STATUS: 🟢 VIP PRO ACTIVATED (100% AD-FREE)';
            const btn = document.getElementById('btnTogglePro');
            if (btn) {
                btn.className = 'btn-pro-activate active';
                btn.innerHTML = '<span>🛡️</span> <span>VIP PRO Active (No Ads)</span>';
            }
            const navBtn = document.getElementById('navProBtn');
            if (navBtn) {
                navBtn.style.background = 'linear-gradient(135deg, rgba(16,185,129,0.3), rgba(6,95,70,0.4))';
                navBtn.style.borderColor = 'var(--emerald)';
                navBtn.style.color = 'var(--emerald)';
                navBtn.textContent = '👑 PRO VIP';
            }
        } else {
            document.body.classList.remove('vip-pro-active');
            document.documentElement.classList.remove('vip-pro-active');
            const badge = document.getElementById('proStatusBadge');
            if (badge) { badge.textContent = 'FREE TIER (ADS ON)'; badge.className = 'badge gold'; }
            const sub = document.getElementById('proStatusSubtitle');
            if (sub) sub.textContent = 'Free Tier: Google Ads enabled. Tap below to activate VIP PRO for 100% Ad-Free experience.';
            const lbl = document.getElementById('proCurrentModeLabel');
            if (lbl) lbl.textContent = 'STATUS: ⚪ FREE TIER (ADS ENABLED)';
            const btn = document.getElementById('btnTogglePro');
            if (btn) {
                btn.className = 'btn-pro-activate';
                btn.innerHTML = '<span>👑</span> <span>Activate VIP PRO (Remove Ads)</span>';
            }
            const navBtn = document.getElementById('navProBtn');
            if (navBtn) {
                navBtn.style.background = 'linear-gradient(135deg, rgba(245,158,11,0.22), rgba(16,185,129,0.18))';
                navBtn.style.borderColor = 'var(--gold)';
                navBtn.style.color = 'var(--gold)';
                navBtn.textContent = '👑 PRO';
            }
        }
    }

    function toggleVipPro() {
        isVipProActive = !isVipProActive;
        localStorage.setItem(NOTIF_STORAGE_KEY, isVipProActive ? 'true' : 'false');
        applyProState();
        if (isVipProActive) {
            playNotificationChime();
            showPushToast('👑 VIP PRO ACTIVATED!', 'Tamam ads khatam kar diye gaye hain. Har chiz ke push alerts active hain!', '🛡️', 'pro');
            sendRealDevicePush('PakUtility Hub 👑', 'VIP PRO Active: 100% Ad-Free experience and instant commodity alerts armed!');
        } else {
            showPushToast('Free Tier Mode', 'Ads restored. Tap PRO anytime to re-activate Ad-Free mode.', 'ℹ️', 'pro');
        }
    }

    // Audio Chime via Web Audio API (Synthesized Bell - Zero MP3 dependency)
    function playNotificationChime() {
        try {
            const AudioContext = window.AudioContext || window.webkitAudioContext;
            if (!AudioContext) return;
            const ctx = new AudioContext();
            const now = ctx.currentTime;
            
            const osc = ctx.createOscillator();
            const gain = ctx.createGain();
            osc.connect(gain);
            gain.connect(ctx.destination);
            
            osc.type = 'sine';
            osc.frequency.setValueAtTime(587.33, now); // D5
            osc.frequency.exponentialRampToValueAtTime(880.00, now + 0.12); // A5
            
            gain.gain.setValueAtTime(0.2, now);
            gain.gain.exponentialRampToValueAtTime(0.001, now + 0.38);
            
            osc.start(now);
            osc.stop(now + 0.4);
        } catch(e){}
    }

    // Browser Notification API
    function updatePushPermissionStatus() {
        const lbl = document.getElementById('permStatusLabel');
        if (!lbl) return;
        if (!('Notification' in window)) {
            lbl.textContent = '⚠️ Not Supported by Browser (In-App Active)';
            lbl.style.color = 'var(--gold)';
            return;
        }
        if (Notification.permission === 'granted') {
            lbl.textContent = '🟢 GRANTED & ACTIVE (فون پر نوٹیفکیشن فعال ہے)';
            lbl.style.color = 'var(--emerald)';
        } else if (Notification.permission === 'denied') {
            lbl.textContent = '🔴 BLOCKED IN BROWSER SETTINGS (براؤزر میں اجازت دیں)';
            lbl.style.color = 'var(--rose)';
        } else {
            lbl.textContent = '🟡 PROMPT NEEDED (اجازت درکار ہے)';
            lbl.style.color = 'var(--gold)';
        }
    }

    function requestDevicePushPermission() {
        if (!('Notification' in window)) {
            showPushToast('Browser Notification Notice', 'Aapke phone browser me push permission auto-handled hai. In-app heads-up alert فعال hai.', '🔔', 'system');
            return;
        }
        Notification.requestPermission().then(perm => {
            updatePushPermissionStatus();
            if (perm === 'granted') {
                playNotificationChime();
                showPushToast('Permission Granted! 🔔', 'Shukriya! Har utility ka live push notification aapke phone par direct aayega.', '✅', 'system');
                sendRealDevicePush('PakUtility Hub 👑', 'Live Push Notification Alerts are now connected to your device!');
            } else {
                showPushToast('Permission Notice', 'Browser settings me jakar notifications allow karein.', 'ℹ️', 'system');
            }
        });
    }

    function sendRealDevicePush(title, body, tag = 'pakutility-alert') {
        if ('Notification' in window && Notification.permission === 'granted') {
            try {
                new Notification(title, {
                    body: body,
                    icon: 'https://cdn-icons-png.flaticon.com/512/3135/3135715.png',
                    tag: tag
                });
            } catch(e){}
        }
    }

    // Floating Push Toast UI
    let toastTimeout = null;
    function showPushToast(title, body, icon = '🔔', cat = 'general') {
        const toast = document.getElementById('pushToastAlert');
        if (!toast) return;
        document.getElementById('pushToastTitle').textContent = title;
        document.getElementById('pushToastMsg').textContent = body;
        document.getElementById('pushToastIcon').textContent = icon;
        document.getElementById('pushToastTime').textContent = 'Just now';
        
        toast.classList.add('show');
        playNotificationChime();

        if (toastTimeout) clearTimeout(toastTimeout);
        toastTimeout = setTimeout(() => {
            hidePushToast();
        }, 4500);
    }

    function hidePushToast() {
        const toast = document.getElementById('pushToastAlert');
        if (toast) toast.classList.remove('show');
    }

    function toggleCategoryPush(cat, btn) {
        enabledPushChannels[cat] = !enabledPushChannels[cat];
        localStorage.setItem(NOTIF_CHANNELS_KEY, JSON.stringify(enabledPushChannels));
        if (enabledPushChannels[cat]) {
            btn.className = 'toggle-switch-btn active';
            btn.textContent = 'ON 🔔';
            showPushToast(`${cat.toUpperCase()} Alert Enabled`, `${cat} ke liye live push notifications on ho gaye hain.`, '🔔', cat);
        } else {
            btn.className = 'toggle-switch-btn';
            btn.textContent = 'OFF 🔕';
            showPushToast(`${cat.toUpperCase()} Alert Muted`, `${cat} ke notifications band kar diye gaye hain.`, '🔕', cat);
        }
    }

    const CATEGORY_PAYLOADS = {
        gold: { title: '🟡 Gold Rate Shift Alert', body: '24K Gold Rate PKR 437,836/Tola (+Rs. 1,200 intraday jump). 22K: Rs. 396,458.', icon: '🟡' },
        fuel: { title: '⛽ OGRA Petrol & Diesel Alert', body: 'Super Petrol Rs. 393.86/L & HSD Diesel Rs. 394.94/L. Fortnightly review live.', icon: '⛽' },
        bills: { title: '⚡ NEPRA Peak Hours Alert', body: 'Peak Hours Reminder: 5:00 PM to 11:00 PM (Rs. 65/unit). Duplicate bills ready.', icon: '⚡' },
        mandi: { title: '🍎 Mandi Bazaar Wholesale Alert', body: 'Mandi Arrival: New Potato Rs. 95/kg, Kinnow Rs. 140/doz, Onion Rs. 135/kg.', icon: '🍎' },
        building: { title: '🏗️ Building Materials Alert', body: 'Mughal Rebar Grade 60 at Rs. 268/kg, Lucky Cement bag Rs. 1,490.', icon: '🏗️' },
        forex: { title: '💱 Forex Currency Alert', body: 'USD Open Market PKR 280.20, Saudi Riyal SAR PKR 75.05. Remittance peak.', icon: '💱' },
        tax: { title: '📑 FBR Salaried Tax Notice', body: 'Monthly salary tax deduction active under Finance Act 2024-2026.', icon: '📑' },
        speed: { title: '🚀 Net Speed Monitor Alert', body: '1-Minute high-precision telemetry: Bandwidth stable at 100+ Mbps.', icon: '🚀' }
    };

    function triggerCategoryAlert(cat) {
        const payload = CATEGORY_PAYLOADS[cat] || { title: 'Alert', body: 'Commodity update', icon: '🔔' };
        showPushToast(payload.title, payload.body, payload.icon, cat);
        sendRealDevicePush(payload.title, payload.body, `pakutility-${cat}`);
        
        // Add to history
        pushAlertHistory.unshift({
            id: Date.now(),
            cat: cat,
            icon: payload.icon,
            title: payload.title,
            body: payload.body,
            time: 'Just now',
            read: false
        });
        renderNotifHistory();
    }

    function sendMasterTestPush() {
        const payload = {
            title: '👑 VIP PRO Master Alert (All Commodities)',
            body: '🟡 Gold 437,836 | ⛽ Petrol 393.86 | ⚡ Peak Hours Alert | 🍎 Mandi Rates. All 100% Ad-Free!',
            icon: '👑'
        };
        showPushToast(payload.title, payload.body, payload.icon, 'all');
        sendRealDevicePush(payload.title, payload.body, 'pakutility-master');
    }

    function renderNotifHistory() {
        const container = document.getElementById('notifHistoryContainer');
        if (!container) return;
        if (!pushAlertHistory.length) {
            container.innerHTML = '<div style="text-align:center; padding:16px; color:var(--text-dim); font-size:11px;">Koi notification nahi hai.</div>';
            return;
        }

        const unreadCount = pushAlertHistory.filter(i => !i.read).length;
        const navBadge = document.getElementById('navNotifBadge');
        if (navBadge) {
            navBadge.textContent = unreadCount > 0 ? unreadCount : '';
            navBadge.style.display = unreadCount > 0 ? 'flex' : 'none';
        }

        container.innerHTML = pushAlertHistory.map(item => `
            <div class="notif-history-item ${item.read ? 'read' : 'unread'}">
                <div style="font-size:18px; line-height:1; padding-top:2px;">${item.icon}</div>
                <div style="flex:1; min-width:0;">
                    <div style="font-size:11px; font-weight:800; color:var(--text-primary); display:flex; justify-content:space-between;">
                        <span>${item.title}</span>
                        <span style="font-size:9px; color:var(--text-dim); font-weight:normal;">${item.time}</span>
                    </div>
                    <div style="font-size:10px; color:var(--text-secondary); margin-top:2px; line-height:1.3;">${item.body}</div>
                </div>
            </div>
        `).join('');
    }

    function markAllNotificationsRead() {
        pushAlertHistory.forEach(i => i.read = true);
        renderNotifHistory();
        showPushToast('Marked as Read', 'Tamam notifications parh liye gaye hain.', '✅', 'system');
    }

    function clearNotificationHistory() {
        pushAlertHistory = [];
        renderNotifHistory();
        showPushToast('Cleared', 'Notification history saaf kar di gayi hai.', '🗑️', 'system');
    }
"""

# Insert pro_js right before window.addEventListener('DOMContentLoaded', ...)
dom_anchor = "window.addEventListener('DOMContentLoaded', () => {"
code = code.replace(dom_anchor, pro_js + '\n    ' + dom_anchor)

# Also ensure initVipPro() is called on DOMContentLoaded
code = code.replace(
    "renderMainRecharts();",
    "initVipPro();\n        renderMainRecharts();"
)

with open('build_complete_applet.py', 'w', encoding='utf-8') as f:
    f.write(code)

print("build_complete_applet.py successfully updated with PRO Ad-Free & Push features.")
