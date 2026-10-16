# -*- coding: utf-8 -*-
with open('build_complete_applet.py', 'r', encoding='utf-8') as f:
    text = f.read()

# ---------------------------------------------------------------------------
# 1. EXPAND META KEYWORDS WITH SPECIFIC REGIONAL UTILITY OFFICE LOCATIONS
# ---------------------------------------------------------------------------
old_keywords = """    <meta name="keywords" content="gold rate in pakistan today 437836, petrol price in pakistan today 393.86, diesel price pakistan 394.94, high octane 415, 22k gold tola 396458, net speed test mbps mbs, 1 minute internet speed test, nepra electricity bill calculator, lesco duplicate bill, iesco bill check online, kelectric duplicate bill, ghar banane ka kharcha 1-100 storeys, building material rates pakistan, saria rate today, mandi rate punjab sindh seasonal, fbr salary tax calculator 2025 2026, pakistan currency open market">"""

new_keywords = """    <meta name="keywords" content="lesco head office lahore queens road duplicate bill, lesco customer facilitation center gulberg lahore, lesco bill kasur okara sheikhupura nankana, iesco head office sector g-7/4 islamabad duplicate bill check online, iesco customer service center rawalpindi chakwal jhelum attock, ke house sunset boulevard dha karachi duplicate bill, k-electric customer care clifton gulshan iqbal korangi north nazimabad karachi, fesco head office west canal road faisalabad jhang sargodha toba tek singh, mepco complex khanewal road multan bahawalpur d g khan sahiwal rahim yar khan, gepco head office gt road gujranwala sialkot gujrat narowal hafizabad, pesco wapda house shami road peshawar mardan abbottabad swat kohat, hesco wapda complex hussainabad hyderabad mirpurkhas badin nawabshah, qesco zarghoon road quetta chaman sibi khuzdar, sepco thermal power station sukkur larkana khairpur jacobabad, pitc duplicate bill online portal pakistan 14 digit reference number, sarafa bazaar lahore karachi rawalpindi gold rate today 437836, ogra petrol price pakistan today 393.86, diesel price 394.94, badami bagh sabzi mandi lahore commodity rates, sabzi mandi super highway karachi wholesale, fbr salary income tax calculator 2025 2026 finance act, ghar banane ka kharcha 1 to 100 storeys, saria rate today mughal amreli grade 60, lucky cement bag rate pakistan">"""

if old_keywords in text:
    text = text.replace(old_keywords, new_keywords)

# ---------------------------------------------------------------------------
# 2. ENHANCE JSON-LD SCHEMA WITH COMPLETE 'LocalBusiness' ENTITY
# ---------------------------------------------------------------------------
local_business_schema = """        {
          "@type": "LocalBusiness",
          "@id": "https://pakutility.com/#localbusiness",
          "name": "PakUtility Hub — Pakistan Regional Utility & Bullion Intelligence",
          "alternateName": "پاک یوٹیلیٹی ہب لوکل سروسز سنٹرز",
          "image": "https://pakutility.com/app_logo.png",
          "telephone": "+92-306-4678975",
          "priceRange": "Rs. 0 - Rs. 300",
          "currenciesAccepted": "PKR",
          "paymentAccepted": "JazzCash, Cash",
          "url": "https://pakutility.com/",
          "address": {
            "@type": "PostalAddress",
            "streetAddress": "22-A Queens Road, Mozang Chungi",
            "addressLocality": "Lahore",
            "addressRegion": "Punjab",
            "postalCode": "54000",
            "addressCountry": "PK"
          },
          "geo": {
            "@type": "GeoCoordinates",
            "latitude": 31.5546,
            "longitude": 74.3168
          },
          "openingHoursSpecification": [
            {
              "@type": "OpeningHoursSpecification",
              "dayOfWeek": ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
              "opens": "00:00",
              "closes": "23:59"
            }
          ],
          "areaServed": [
            { "@type": "City", "name": "Lahore" },
            { "@type": "City", "name": "Islamabad" },
            { "@type": "City", "name": "Rawalpindi" },
            { "@type": "City", "name": "Karachi" },
            { "@type": "City", "name": "Faisalabad" },
            { "@type": "City", "name": "Multan" },
            { "@type": "City", "name": "Gujranwala" },
            { "@type": "City", "name": "Peshawar" },
            { "@type": "City", "name": "Hyderabad" },
            { "@type": "City", "name": "Quetta" }
          ],
          "department": [
            {
              "@type": "LocalBusiness",
              "name": "LESCO Customer Facilitation Center Lahore",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "22-A Queens Road",
                "addressLocality": "Lahore",
                "addressRegion": "Punjab",
                "postalCode": "54000",
                "addressCountry": "PK"
              },
              "telephone": "+92-42-99204814"
            },
            {
              "@type": "LocalBusiness",
              "name": "IESCO Regional Billing Center Islamabad",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "Sector G-7/4, Street 40",
                "addressLocality": "Islamabad",
                "addressRegion": "Islamabad Capital Territory",
                "postalCode": "44000",
                "addressCountry": "PK"
              },
              "telephone": "+92-51-9252937"
            },
            {
              "@type": "LocalBusiness",
              "name": "K-Electric Central Billing Care Karachi",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "KE House, 39-B Sunset Boulevard, Phase-II DHA",
                "addressLocality": "Karachi",
                "addressRegion": "Sindh",
                "postalCode": "75500",
                "addressCountry": "PK"
              },
              "telephone": "+92-21-118"
            },
            {
              "@type": "LocalBusiness",
              "name": "FESCO Regional Head Office Faisalabad",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "West Canal Road, Abdullahpur",
                "addressLocality": "Faisalabad",
                "addressRegion": "Punjab",
                "postalCode": "38000",
                "addressCountry": "PK"
              },
              "telephone": "+92-41-9220184"
            },
            {
              "@type": "LocalBusiness",
              "name": "MEPCO Facilitation Directorate Multan",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "MEPCO Complex, Khanewal Road",
                "addressLocality": "Multan",
                "addressRegion": "Punjab",
                "postalCode": "60000",
                "addressCountry": "PK"
              },
              "telephone": "+92-61-9220315"
            },
            {
              "@type": "LocalBusiness",
              "name": "GEPCO Regional Center Gujranwala",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "565-A GT Road",
                "addressLocality": "Gujranwala",
                "addressRegion": "Punjab",
                "postalCode": "52250",
                "addressCountry": "PK"
              },
              "telephone": "+92-55-9200519"
            },
            {
              "@type": "LocalBusiness",
              "name": "PESCO Head Office Directorate Peshawar",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "WAPDA House, Shami Road",
                "addressLocality": "Peshawar",
                "addressRegion": "Khyber Pakhtunkhwa",
                "postalCode": "25000",
                "addressCountry": "PK"
              },
              "telephone": "+92-91-9212013"
            },
            {
              "@type": "LocalBusiness",
              "name": "HESCO Customer Center Hyderabad",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "WAPDA Complex, Hussainabad",
                "addressLocality": "Hyderabad",
                "addressRegion": "Sindh",
                "postalCode": "71000",
                "addressCountry": "PK"
              },
              "telephone": "+92-22-9260161"
            },
            {
              "@type": "LocalBusiness",
              "name": "QESCO Directorate Quetta",
              "address": {
                "@type": "PostalAddress",
                "streetAddress": "Zarghoon Road",
                "addressLocality": "Quetta",
                "addressRegion": "Balochistan",
                "postalCode": "87300",
                "addressCountry": "PK"
              },
              "telephone": "+92-81-9201445"
            }
          ]
        },"""

if '"@type": "LocalBusiness"' not in text:
    target_schema_point = '      "@type": "FinancialService",'
    text = text.replace(target_schema_point, local_business_schema + '\n        ' + target_schema_point)

# ---------------------------------------------------------------------------
# 3. ADD OFFLINE BANNER CSS & UI
# ---------------------------------------------------------------------------
offline_css = """
        /* PWA Offline Mode Status Indicator */
        .offline-status-banner {
            display: none;
            background: linear-gradient(135deg, rgba(239, 68, 68, 0.2), rgba(185, 28, 28, 0.3));
            border-bottom: 1.5px solid var(--rose);
            color: #FFF;
            padding: 8px 14px;
            font-size: 11px;
            font-weight: 800;
            text-align: center;
            z-index: 10000;
            position: sticky;
            top: 0;
            animation: slideDown 0.3s ease;
        }
        .offline-status-banner.show {
            display: flex !important;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }
        @keyframes slideDown {
            from { transform: translateY(-100%); }
            to { transform: translateY(0); }
        }
"""

if '.offline-status-banner' not in text:
    text = text.replace('/* Top App Bar */', offline_css + '\n        /* Top App Bar */')

# Add offline banner right at start of body
offline_banner_html = """    <!-- Real-Time Offline Mode Status Banner -->
    <div class="offline-status-banner" id="offlineBanner">
        <span>📶</span>
        <span><strong>OFFLINE MODE ACTIVE (آف لائن موڈ):</strong> Internet disconnected. Tamam calculators, benchmark rates aur saved duplicate bill data bina internet ke 100% chal rahe hain!</span>
    </div>
"""

if 'id="offlineBanner"' not in text:
    text = text.replace('<body>', '<body>\n' + offline_banner_html)

# ---------------------------------------------------------------------------
# 4. ADD SERVICE WORKER REGISTRATION & OFFLINE DETECTION IN JS
# ---------------------------------------------------------------------------
offline_js = """
    // =============================================================
    // PWA SERVICE WORKER & OFFLINE MODE TELEMETRY
    // =============================================================
    function initOfflineTelemetry() {
        function updateNetworkStatus() {
            const banner = document.getElementById('offlineBanner');
            if (!banner) return;
            if (navigator.onLine) {
                banner.classList.remove('show');
            } else {
                banner.classList.add('show');
                showPushToast('Offline Mode Active 📶', 'Aapka internet band hai. Tamam calculators aur saved rates offline chal rahe hain.', '📶', 'offline');
            }
        }

        window.addEventListener('online', updateNetworkStatus);
        window.addEventListener('offline', updateNetworkStatus);
        updateNetworkStatus();

        // Register Service Worker for true offline caching
        if ('serviceWorker' in navigator) {
            window.addEventListener('load', () => {
                navigator.serviceWorker.register('./sw.js').then((registration) => {
                    console.log('[PWA] Service Worker registered with scope:', registration.scope);
                }).catch((error) => {
                    console.warn('[PWA] Service Worker registration skipped or failed:', error);
                });
            });
        }
    }
"""

if 'initOfflineTelemetry' not in text:
    text = text.replace("window.addEventListener('DOMContentLoaded', () => {", offline_js + "\n    window.addEventListener('DOMContentLoaded', () => {\n        initOfflineTelemetry();")

with open('build_complete_applet.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("build_complete_applet.py updated with LocalBusiness schema, regional office keywords, and offline PWA mode.")
