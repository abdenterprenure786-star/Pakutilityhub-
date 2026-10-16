with open('build_complete_applet.py', 'r', encoding='utf-8') as f:
    code = f.read()

# 1. Add favicon and touch icon to head
favicon_tags = """    <!-- Favicon & Web App Icons -->
    <link rel="icon" type="image/png" href="./favicon.png">
    <link rel="apple-touch-icon" href="./icon-192.png">
    <link rel="manifest" href="./manifest.json">
"""

if '<link rel="icon"' not in code:
    code = code.replace('<link rel="canonical"', favicon_tags + '    <link rel="canonical"')

# 2. Add brand-logo-container CSS
logo_css = """
        /* Attractive All-Rates App Logo & Brand Header */
        .brand-logo-container {
            position: relative;
            width: 40px;
            height: 40px;
            border-radius: 10px;
            background: #0B1120;
            border: 1.5px solid var(--gold);
            box-shadow: 0 4px 16px rgba(245, 158, 11, 0.35), 0 0 12px rgba(16, 185, 129, 0.3);
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: visible;
            flex-shrink: 0;
            transition: transform 0.25s cubic-bezier(0.16, 1, 0.3, 1);
        }
        .brand-logo-container:hover {
            transform: scale(1.08) rotate(-2deg);
        }
        .brand-logo-img {
            width: 100%;
            height: 100%;
            border-radius: 9px;
            object-fit: cover;
            display: block;
        }
        .live-rate-beacon {
            position: absolute;
            top: -3px;
            right: -3px;
            width: 10px;
            height: 10px;
            background: #10B981;
            border: 2px solid var(--bg-surface);
            border-radius: 50%;
            animation: pulseBeacon 1.6s infinite;
        }
        @keyframes pulseBeacon {
            0% { box-shadow: 0 0 0 0 rgba(16, 185, 129, 0.85); }
            70% { box-shadow: 0 0 0 8px rgba(16, 185, 129, 0); }
            100% { box-shadow: 0 0 0 0 rgba(16, 185, 129, 0); }
        }
        .brand-text-sub-urdu {
            font-size: 9px;
            color: var(--gold);
            font-weight: 700;
            font-family: 'Noto Nastaliq Urdu', serif;
            line-height: 1.25;
            letter-spacing: 0.2px;
        }
"""

if '.brand-logo-container' not in code:
    code = code.replace('/* Top App Bar */', logo_css + '\n        /* Top App Bar */')

# 3. Update top navbar brand-box with the all-rates logo image
old_brand_html = """            <div class="brand-box" onclick="switchTab('trends')" style="cursor:pointer;" title="PakUtility Hub - Live Pakistani Rates">
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

new_brand_html = """            <div class="brand-box" onclick="switchTab('trends')" style="cursor:pointer;" title="PakUtility Hub - پاکستان کے تمام لائیو ریٹس">
                <div class="brand-logo-container">
                    <img src="./app_logo.png" alt="PakUtility Rates Logo" class="brand-logo-img" onerror="this.src='./app_logo.jpg'; this.onerror=null;">
                    <span class="live-rate-beacon" title="Live Market Telemetry Active"></span>
                </div>
                <div>
                    <div class="brand-text-title-gradient">
                        <span>PakUtility</span> <span class="hub-pill">HUB</span>
                    </div>
                    <div class="brand-text-sub-urdu">ہر چیز کے لائیو ریٹس • سونا، پیٹرول، بجلی، منڈی</div>
                </div>
            </div>"""

if old_brand_html in code:
    code = code.replace(old_brand_html, new_brand_html)
elif 'class="brand-box"' in code:
    # replace first occurrence
    import re
    code = re.sub(r'<div class="brand-box"[\s\S]*?</div>\s*</div>\s*</div>', new_brand_html, code, count=1)

# 4. Also update sideDrawer brand header
drawer_brand_old = """                <div class="brand-box">
                    <div class="brand-icon">PK</div>
                    <div>
                        <div class="brand-text-title">PakUtility Hub</div>
                        <div class="brand-text-sub">پاکستان کا مصدقہ یوٹیلیٹی پورٹل</div>
                    </div>
                </div>"""

drawer_brand_new = """                <div class="brand-box">
                    <div class="brand-logo-container">
                        <img src="./app_logo.png" alt="PakUtility Rates Logo" class="brand-logo-img" onerror="this.src='./app_logo.jpg'; this.onerror=null;">
                        <span class="live-rate-beacon"></span>
                    </div>
                    <div>
                        <div class="brand-text-title-gradient">
                            <span>PakUtility</span> <span class="hub-pill">HUB</span>
                        </div>
                        <div class="brand-text-sub-urdu">تمام چیزوں کے لائیو ریٹس پورٹل</div>
                    </div>
                </div>"""

if drawer_brand_old in code:
    code = code.replace(drawer_brand_old, drawer_brand_new)

with open('build_complete_applet.py', 'w', encoding='utf-8') as f:
    f.write(code)

print("build_complete_applet.py updated with all-rates logo and favicon.")
