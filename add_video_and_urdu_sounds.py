# -*- coding: utf-8 -*-
"""
Script to add Interactive Video Showcase & Generator, Urdu Voice Narration & SFX,
and Attractive Layout to PakUtility Hub.
"""
import os

with open('index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# ---------------------------------------------------------------------------
# 1. NEW CSS STYLES FOR ATTRACTIVE LAYOUT, VIDEO SHOWCASE, AND URDU SOUNDS
# ---------------------------------------------------------------------------
new_css = """
        /* ======================================================== */
        /* ATTRACTIVE LAYOUT, VIDEO SHOWCASE & URDU AUDIO ENGINE    */
        /* ======================================================== */
        
        /* Urdu Nastaliq & Typography Enhancements */
        .urdu-font {
            font-family: 'Noto Nastaliq Urdu', -apple-system, sans-serif;
            direction: rtl;
            line-height: 1.6;
        }

        /* Live Market Ticker Ribbon */
        .market-ticker-ribbon {
            background: linear-gradient(90deg, rgba(6, 9, 20, 0.98), rgba(11, 17, 32, 0.98), rgba(6, 9, 20, 0.98));
            border-bottom: 1px solid rgba(245, 158, 11, 0.25);
            padding: 6px 12px;
            display: flex;
            align-items: center;
            overflow: hidden;
            white-space: nowrap;
            position: relative;
            font-size: 11px;
            font-weight: 700;
            color: var(--text-secondary);
        }
        [data-theme="light"] .market-ticker-ribbon {
            background: linear-gradient(90deg, #F1F5F9, #FFFFFF, #F1F5F9);
            border-bottom: 1px solid rgba(217, 119, 6, 0.2);
            color: #475569;
        }
        .ticker-tag-badge {
            background: linear-gradient(135deg, #10B981, #047857);
            color: #FFF;
            font-size: 9px;
            font-weight: 900;
            padding: 2px 7px;
            border-radius: 4px;
            margin-right: 10px;
            display: inline-flex;
            align-items: center;
            gap: 4px;
            box-shadow: 0 0 8px rgba(16, 185, 129, 0.4);
            flex-shrink: 0;
        }
        .ticker-track {
            display: inline-flex;
            gap: 22px;
            animation: tickerScroll 32s linear infinite;
        }
        .ticker-track:hover {
            animation-play-state: paused;
        }
        @keyframes tickerScroll {
            0% { transform: translateX(0); }
            100% { transform: translateX(-50%); }
        }
        .ticker-item {
            display: inline-flex;
            align-items: center;
            gap: 5px;
        }
        .ticker-val {
            color: var(--gold);
            font-weight: 800;
        }
        .ticker-up {
            color: var(--emerald);
            font-size: 10px;
        }

        /* Top Header Action Buttons */
        .video-tour-header-btn {
            background: linear-gradient(135deg, #10B981, #047857) !important;
            border: 1.2px solid var(--gold) !important;
            color: #FFF !important;
            font-size: 10px !important;
            padding: 4px 9px !important;
            height: 28px !important;
            font-weight: 900 !important;
            border-radius: 6px !important;
            display: inline-flex !important;
            align-items: center !important;
            gap: 5px !important;
            cursor: pointer;
            box-shadow: 0 0 12px rgba(16, 185, 129, 0.35);
            transition: all 0.2s ease;
        }
        .video-tour-header-btn:hover {
            transform: translateY(-1px);
            box-shadow: 0 0 16px rgba(16, 185, 129, 0.55);
        }

        .urdu-voice-master-btn {
            background: rgba(245, 158, 11, 0.15) !important;
            border: 1.2px solid var(--gold) !important;
            color: var(--gold) !important;
            font-size: 10px !important;
            padding: 4px 9px !important;
            height: 28px !important;
            font-weight: 900 !important;
            border-radius: 6px !important;
            display: inline-flex !important;
            align-items: center !important;
            gap: 5px !important;
            cursor: pointer;
            transition: all 0.2s ease;
        }
        .urdu-voice-master-btn.active {
            background: linear-gradient(135deg, rgba(245, 158, 11, 0.35), rgba(16, 185, 129, 0.25)) !important;
            border-color: var(--emerald) !important;
            color: var(--emerald) !important;
            box-shadow: 0 0 12px rgba(16, 185, 129, 0.4);
        }
        .urdu-header-wave {
            display: inline-flex;
            align-items: flex-end;
            gap: 2px;
            height: 12px;
        }
        .urdu-header-wave .b {
            width: 2.5px;
            background: currentColor;
            border-radius: 1px;
            height: 4px;
        }
        .urdu-voice-master-btn.speaking .urdu-header-wave .b:nth-child(1) {
            animation: waveBounce 0.5s ease-in-out infinite alternate;
        }
        .urdu-voice-master-btn.speaking .urdu-header-wave .b:nth-child(2) {
            animation: waveBounce 0.7s ease-in-out infinite alternate 0.15s;
        }
        .urdu-voice-master-btn.speaking .urdu-header-wave .b:nth-child(3) {
            animation: waveBounce 0.6s ease-in-out infinite alternate 0.3s;
        }
        @keyframes waveBounce {
            0% { height: 3px; }
            100% { height: 12px; }
        }

        /* Hero Video Showcase Card */
        .video-hero-showcase {
            background: linear-gradient(145deg, rgba(15, 23, 42, 0.95), rgba(8, 13, 26, 0.98));
            border: 1.5px solid rgba(245, 158, 11, 0.45);
            border-radius: 16px;
            padding: 16px;
            margin-bottom: 16px;
            box-shadow: 0 10px 32px rgba(0, 0, 0, 0.6), 0 0 20px rgba(16, 185, 129, 0.18);
            position: relative;
            overflow: hidden;
        }
        [data-theme="light"] .video-hero-showcase {
            background: linear-gradient(145deg, #FFFFFF, #F8FAFC);
            border: 1.5px solid rgba(217, 119, 6, 0.4);
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08), 0 0 15px rgba(5, 150, 105, 0.12);
        }
        .video-hero-glow-accent {
            position: absolute;
            top: -40px;
            right: -40px;
            width: 140px;
            height: 140px;
            background: radial-gradient(circle, rgba(16, 185, 129, 0.25), transparent 70%);
            border-radius: 50%;
            pointer-events: none;
        }
        .video-hero-header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 12px;
            flex-wrap: wrap;
            gap: 8px;
        }
        .video-hero-title-group {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .video-hero-icon {
            width: 36px;
            height: 36px;
            border-radius: 10px;
            background: linear-gradient(135deg, #10B981, #047857);
            border: 1.5px solid var(--gold);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            box-shadow: 0 0 14px rgba(16, 185, 129, 0.4);
        }
        .video-hero-title {
            font-size: 15px;
            font-weight: 900;
            letter-spacing: 0.3px;
            color: var(--text-primary);
        }
        .video-hero-subtitle-urdu {
            font-size: 11px;
            color: var(--gold);
            font-weight: 700;
            font-family: 'Noto Nastaliq Urdu', serif;
        }

        /* 16:9 Video Canvas Viewport */
        .video-canvas-container {
            position: relative;
            width: 100%;
            padding-top: 56.25%; /* 16:9 Aspect Ratio */
            background: #040711;
            border-radius: 12px;
            overflow: hidden;
            border: 1.5px solid rgba(245, 158, 11, 0.35);
            box-shadow: inset 0 0 25px rgba(0, 0, 0, 0.8), 0 6px 20px rgba(0, 0, 0, 0.4);
        }
        .video-canvas-screen {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: block;
        }
        .video-overlay-play-btn {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 64px;
            height: 64px;
            border-radius: 50%;
            background: linear-gradient(135deg, #10B981, #059669);
            border: 2.5px solid #F59E0B;
            color: #FFF;
            font-size: 24px;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            box-shadow: 0 0 28px rgba(16, 185, 129, 0.65), 0 0 15px rgba(245, 158, 11, 0.5);
            transition: all 0.25s cubic-bezier(0.16, 1, 0.3, 1);
            z-index: 10;
        }
        .video-overlay-play-btn:hover {
            transform: translate(-50%, -50%) scale(1.1);
            box-shadow: 0 0 35px rgba(16, 185, 129, 0.85);
        }
        .video-overlay-play-btn.playing {
            opacity: 0;
            pointer-events: none;
        }
        .video-live-recording-badge {
            position: absolute;
            top: 10px;
            left: 10px;
            background: rgba(225, 29, 72, 0.9);
            border: 1px solid #FFF;
            color: #FFF;
            font-size: 9px;
            font-weight: 900;
            padding: 3px 8px;
            border-radius: 4px;
            display: none;
            align-items: center;
            gap: 5px;
            z-index: 12;
            box-shadow: 0 0 10px rgba(225, 29, 72, 0.7);
        }
        .video-live-recording-badge.active {
            display: inline-flex;
        }
        .rec-dot {
            width: 6px;
            height: 6px;
            background: #FFF;
            border-radius: 50%;
            animation: pulseRec 1s infinite alternate;
        }
        @keyframes pulseRec {
            from { opacity: 0.2; }
            to { opacity: 1; }
        }

        /* Video Controls Bar */
        .video-controls-bar {
            background: rgba(11, 17, 32, 0.95);
            border: 1px solid var(--border-dim);
            border-radius: 10px;
            padding: 10px 12px;
            margin-top: 10px;
            display: flex;
            flex-direction: column;
            gap: 8px;
        }
        [data-theme="light"] .video-controls-bar {
            background: #F1F5F9;
        }
        .video-scrub-row {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .video-timeline-slider {
            flex: 1;
            -webkit-appearance: none;
            appearance: none;
            height: 6px;
            border-radius: 3px;
            background: #1E293B;
            outline: none;
            cursor: pointer;
            transition: background 0.2s;
        }
        .video-timeline-slider::-webkit-slider-thumb {
            -webkit-appearance: none;
            appearance: none;
            width: 14px;
            height: 14px;
            border-radius: 50%;
            background: var(--gold);
            cursor: pointer;
            box-shadow: 0 0 8px var(--gold-glow);
            border: 2px solid #FFF;
        }
        .video-time-text {
            font-size: 11px;
            font-weight: 800;
            color: var(--text-secondary);
            font-family: monospace;
            min-width: 80px;
            text-align: right;
        }

        .video-actions-row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
            gap: 8px;
        }
        .video-btn-group-left {
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .video-btn-group-right {
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .v-ctrl-btn {
            background: var(--bg-card);
            border: 1px solid var(--border-subtle);
            color: var(--text-primary);
            font-size: 11px;
            font-weight: 700;
            padding: 5px 10px;
            border-radius: 6px;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 4px;
            transition: all 0.2s ease;
        }
        .v-ctrl-btn:hover {
            border-color: var(--gold);
            color: var(--gold);
        }
        .v-ctrl-btn.primary {
            background: linear-gradient(135deg, #10B981, #059669);
            color: #FFF;
            border-color: var(--gold);
            box-shadow: 0 2px 10px rgba(16, 185, 129, 0.3);
        }
        .v-ctrl-btn.primary:hover {
            box-shadow: 0 4px 14px rgba(16, 185, 129, 0.5);
            color: #FFF;
        }
        .v-ctrl-btn.download-btn {
            background: linear-gradient(135deg, #F59E0B, #D97706);
            color: #000;
            font-weight: 900;
            border-color: #FCD34D;
            box-shadow: 0 2px 10px rgba(245, 158, 11, 0.35);
        }
        .v-ctrl-btn.download-btn:hover {
            box-shadow: 0 4px 16px rgba(245, 158, 11, 0.55);
            color: #000;
        }

        /* Video Scene Jump Buttons */
        .video-scenes-nav {
            display: flex;
            gap: 6px;
            overflow-x: auto;
            padding: 4px 0 2px 0;
            scrollbar-width: none;
        }
        .video-scenes-nav::-webkit-scrollbar { display: none; }
        .scene-chip {
            background: var(--bg-inner);
            border: 1px solid var(--border-dim);
            color: var(--text-secondary);
            font-size: 10px;
            font-weight: 700;
            padding: 3px 8px;
            border-radius: 6px;
            cursor: pointer;
            white-space: nowrap;
            transition: all 0.2s ease;
        }
        .scene-chip:hover, .scene-chip.active {
            background: rgba(245, 158, 11, 0.15);
            border-color: var(--gold);
            color: var(--gold);
        }

        /* In-Card Urdu Voice Listen Button */
        .card-urdu-listen-btn {
            background: rgba(245, 158, 11, 0.12);
            border: 1px solid var(--gold-border);
            color: var(--gold);
            font-size: 10px;
            font-weight: 800;
            padding: 4px 10px;
            border-radius: 6px;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            gap: 5px;
            transition: all 0.2s ease;
            box-shadow: 0 2px 6px rgba(0,0,0,0.2);
        }
        .card-urdu-listen-btn:hover {
            background: rgba(245, 158, 11, 0.25);
            border-color: var(--gold);
            transform: translateY(-1px);
        }
        .card-urdu-listen-btn.speaking {
            background: linear-gradient(135deg, rgba(16, 185, 129, 0.2), rgba(245, 158, 11, 0.2));
            border-color: var(--emerald);
            color: var(--emerald);
            box-shadow: 0 0 12px rgba(16, 185, 129, 0.4);
        }

        /* Floating Audio Narrator Dock Toast */
        .audio-narrator-toast {
            position: fixed;
            bottom: 65px;
            left: 50%;
            transform: translateX(-50%) translateY(120px);
            background: rgba(11, 17, 32, 0.96);
            backdrop-filter: blur(16px);
            -webkit-backdrop-filter: blur(16px);
            border: 1.5px solid var(--gold);
            border-radius: 30px;
            padding: 8px 16px;
            display: flex;
            align-items: center;
            gap: 12px;
            z-index: 9999;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.6), 0 0 20px rgba(245, 158, 11, 0.3);
            max-width: 90vw;
            width: 480px;
            transition: transform 0.35s cubic-bezier(0.16, 1, 0.3, 1), opacity 0.3s;
            opacity: 0;
            pointer-events: none;
        }
        .audio-narrator-toast.show {
            transform: translateX(-50%) translateY(0);
            opacity: 1;
            pointer-events: auto;
        }
        [data-theme="light"] .audio-narrator-toast {
            background: rgba(255, 255, 255, 0.96);
            border-color: var(--gold);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15), 0 0 15px rgba(217, 119, 6, 0.25);
        }
        .narrator-wave-anim {
            display: flex;
            align-items: flex-end;
            gap: 3px;
            height: 16px;
            flex-shrink: 0;
        }
        .narrator-wave-anim span {
            width: 3px;
            background: var(--gold);
            border-radius: 2px;
            height: 4px;
            animation: waveActive 0.6s ease-in-out infinite alternate;
        }
        .narrator-wave-anim span:nth-child(2) { animation-delay: 0.15s; }
        .narrator-wave-anim span:nth-child(3) { animation-delay: 0.3s; }
        .narrator-wave-anim span:nth-child(4) { animation-delay: 0.45s; }
        @keyframes waveActive {
            0% { height: 4px; }
            100% { height: 16px; }
        }
        .narrator-text-box {
            flex: 1;
            overflow: hidden;
        }
        .narrator-tag {
            font-size: 8px;
            font-weight: 800;
            color: var(--emerald);
            letter-spacing: 0.5px;
        }
        .narrator-msg-urdu {
            font-size: 11px;
            font-weight: 700;
            color: var(--text-primary);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            font-family: 'Noto Nastaliq Urdu', serif;
            direction: rtl;
        }
        .narrator-close-btn {
            background: rgba(244, 63, 94, 0.2);
            border: 1px solid var(--rose);
            color: var(--rose);
            width: 24px;
            height: 24px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 13px;
            cursor: pointer;
            flex-shrink: 0;
        }

        /* Floating Quick Action Dock (Right Side) */
        .floating-action-dock {
            position: fixed;
            right: 14px;
            bottom: 74px;
            display: flex;
            flex-direction: column;
            gap: 8px;
            z-index: 999;
        }
        .dock-circle-btn {
            width: 44px;
            height: 44px;
            border-radius: 50%;
            background: rgba(11, 17, 32, 0.92);
            backdrop-filter: blur(10px);
            border: 1.5px solid var(--border-subtle);
            color: var(--text-primary);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            font-size: 16px;
            cursor: pointer;
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.45);
            transition: all 0.22s cubic-bezier(0.16, 1, 0.3, 1);
            position: relative;
        }
        [data-theme="light"] .dock-circle-btn {
            background: rgba(255, 255, 255, 0.92);
            box-shadow: 0 4px 14px rgba(0, 0, 0, 0.15);
        }
        .dock-circle-btn:hover {
            transform: scale(1.1);
            border-color: var(--gold);
            box-shadow: 0 0 16px var(--gold-glow);
        }
        .dock-circle-btn.gold-accent {
            border-color: var(--gold);
            color: var(--gold);
        }
        .dock-circle-btn.emerald-accent {
            background: linear-gradient(135deg, #10B981, #047857);
            border-color: var(--gold);
            color: #FFF;
            box-shadow: 0 0 14px rgba(16, 185, 129, 0.5);
        }
        .dock-btn-label {
            font-size: 7px;
            font-weight: 800;
            margin-top: 1px;
            text-transform: uppercase;
        }
"""

if '/* ATTRACTIVE LAYOUT, VIDEO SHOWCASE & URDU AUDIO ENGINE */' not in html:
    html = html.replace('</style>', new_css + '\n    </style>')

print("Step 1: CSS injected.")

# ---------------------------------------------------------------------------
# 2. INJECT MARKET TICKER RIBBON UNDER TOP NAVBAR
# ---------------------------------------------------------------------------
ticker_html = """    <!-- Attractive Live Telemetry Ticker Ribbon -->
    <div class="market-ticker-ribbon">
        <span class="ticker-tag-badge">🇵🇰 LIVE OCT 2026</span>
        <div class="ticker-track">
            <span class="ticker-item">🟡 24K Gold: <strong class="ticker-val">PKR 437,836/tola</strong> <span class="ticker-up">▲ +0.2%</span></span>
            <span class="ticker-item">⛽ Super Petrol: <strong class="ticker-val">PKR 393.86/L</strong> <span class="ticker-up">OGRA Verified</span></span>
            <span class="ticker-item">🚚 HSD Diesel: <strong class="ticker-val">PKR 394.94/L</strong> <span class="ticker-up">Official</span></span>
            <span class="ticker-item">⚡ Net Speed: <strong class="ticker-val">105.4 MB/s</strong> <span class="ticker-up">12ms Ping</span></span>
            <span class="ticker-item">💡 NEPRA Base Unit: <strong class="ticker-val">PKR 32.50/unit</strong> <span class="ticker-up">LESCO/IESCO/KE</span></span>
            <span class="ticker-item">🍎 Mandi Tomato: <strong class="ticker-val">PKR 135/kg</strong> <span class="ticker-up">Badami Bagh</span></span>
            <span class="ticker-item">🏗️ Mughal Saria Grade 60: <strong class="ticker-val">PKR 278/kg</strong> <span class="ticker-up">Lucky Cement Rs. 1480</span></span>
            <span class="ticker-item">💱 USD/PKR: <strong class="ticker-val">282.50</strong> <span class="ticker-up">Open Market</span></span>
            <!-- Repeating duplicate for seamless infinite loop -->
            <span class="ticker-item">🟡 24K Gold: <strong class="ticker-val">PKR 437,836/tola</strong> <span class="ticker-up">▲ +0.2%</span></span>
            <span class="ticker-item">⛽ Super Petrol: <strong class="ticker-val">PKR 393.86/L</strong> <span class="ticker-up">OGRA Verified</span></span>
            <span class="ticker-item">🚚 HSD Diesel: <strong class="ticker-val">PKR 394.94/L</strong> <span class="ticker-up">Official</span></span>
            <span class="ticker-item">⚡ Net Speed: <strong class="ticker-val">105.4 MB/s</strong> <span class="ticker-up">12ms Ping</span></span>
        </div>
    </div>
"""

# Header Buttons: Add "🎬 App Video" and "🔊 اردو آواز" to nav-right
header_target = '<button class="theme-toggle-btn" onclick="switchTab(\'pro\')" id="navProBtn"'
new_header_buttons = """<button class="video-tour-header-btn" onclick="scrollToVideoShowcase()" title="Watch App Video & Urdu Tour (ویڈیو شوکیس)">
                <span>🎬</span> <span>App Video</span>
            </button>
            <button class="urdu-voice-master-btn active" id="urduVoiceMasterBtn" onclick="toggleUrduVoiceMaster()" title="اردو صوتی گائیڈ آن/آف (Urdu Audio Guide)">
                <span id="urduVoiceIcon">🔊</span>
                <span id="urduVoiceLabel">اردو آواز</span>
                <span class="urdu-header-wave" id="urduHeaderWave">
                    <span class="b"></span><span class="b"></span><span class="b"></span>
                </span>
            </button>
            """ + header_target

if 'video-tour-header-btn' not in html:
    html = html.replace(header_target, new_header_buttons)

if 'market-ticker-ribbon' not in html:
    html = html.replace('</header>', '</header>\n' + ticker_html)

print("Step 2: Header buttons & Ticker ribbon injected.")

# ---------------------------------------------------------------------------
# 3. ADD VIDEO TAB BUTTON TO TAB SCROLLERS & SIDE DRAWER
# ---------------------------------------------------------------------------
# Add to horizontal tabs
video_tab_btn = '<button class="tab-btn" data-tab="video" onclick="switchTab(\'video\')">🎬 App Video & Tour</button>\n        '
if 'data-tab="video"' not in html:
    html = html.replace('<button class="tab-btn active" data-tab="trends"', video_tab_btn + '<button class="tab-btn active" data-tab="trends"')

# Add to side drawer
drawer_video_item = '<div class="drawer-menu-item" onclick="switchTab(\'video\'); toggleDrawer(false);">🎬 App Video & Urdu Showcase</div>\n                '
if 'switchTab(\'video\'); toggleDrawer(false);' not in html:
    html = html.replace('<div class="drawer-menu-item active" onclick="switchTab(\'trends\'); toggleDrawer(false);', drawer_video_item + '<div class="drawer-menu-item active" onclick="switchTab(\'trends\'); toggleDrawer(false);')

print("Step 3: Tab buttons added.")

# ---------------------------------------------------------------------------
# 4. BUILD THE HERO VIDEO SHOWCASE COMPONENT (FOR HOME SCREEN & VIDEO TAB)
# ---------------------------------------------------------------------------
video_hero_html = """
            <!-- ======================================================== -->
            <!-- 🎬 PAKUTILITY HUB INTERACTIVE VIDEO SHOWCASE & GENERATOR -->
            <!-- ======================================================== -->
            <div class="video-hero-showcase" id="appVideoShowcaseSection">
                <div class="video-hero-glow-accent"></div>
                <div class="video-hero-header">
                    <div class="video-hero-title-group">
                        <div class="video-hero-icon">🎬</div>
                        <div>
                            <div class="video-hero-title">PAKUTILITY HUB — OFFICIAL APP VIDEO & URDU TOUR</div>
                            <div class="video-hero-subtitle-urdu">مکمل ویڈیو شوکیس اور تمام فنکشنز کی اردو صوتی گائیڈ</div>
                        </div>
                    </div>
                    <div style="display:flex; align-items:center; gap:8px;">
                        <button class="card-urdu-listen-btn" onclick="speakFunctionUrdu('video')">
                            <span>🔊</span> <span>سنیں (Listen)</span>
                        </button>
                        <span class="badge gold">1080p HD TOUR</span>
                    </div>
                </div>

                <!-- 16:9 Canvas Video Viewport -->
                <div class="video-canvas-container" id="videoCanvasContainer">
                    <canvas id="pakVideoCanvas" class="video-canvas-screen" width="1280" height="720"></canvas>
                    <div class="video-overlay-play-btn" id="videoBigPlayBtn" onclick="togglePlayVideoTour()">
                        ▶
                    </div>
                    <div class="video-live-recording-badge" id="videoRecBadge">
                        <span class="rec-dot"></span>
                        <span id="videoRecText">REC VIDEO (0%)</span>
                    </div>
                </div>

                <!-- Video Controls Bar -->
                <div class="video-controls-bar">
                    <!-- Progress Scrubber -->
                    <div class="video-scrub-row">
                        <input type="range" class="video-timeline-slider" id="videoTimelineSlider" min="0" max="54" step="0.1" value="0" oninput="seekVideoTour(this.value)">
                        <span class="video-time-text" id="videoTimeDisplay">00:00 / 00:54</span>
                    </div>

                    <!-- Actions Row -->
                    <div class="video-actions-row">
                        <div class="video-btn-group-left">
                            <button class="v-ctrl-btn primary" id="videoPlayPauseBtn" onclick="togglePlayVideoTour()">
                                <span id="vPlayIcon">▶</span> <span id="vPlayText">Play Video</span>
                            </button>
                            <button class="v-ctrl-btn" onclick="restartVideoTour()" title="Restart Video">
                                🔄 Restart
                            </button>
                            <button class="v-ctrl-btn" id="videoMuteBtn" onclick="toggleVideoVoiceover()" title="Mute/Unmute Urdu Voice">
                                <span id="vMuteIcon">🔊</span> <span>Urdu Voice</span>
                            </button>
                            <button class="v-ctrl-btn" id="videoSpeedBtn" onclick="cycleVideoSpeed()">
                                <span id="vSpeedLabel">1.0x</span>
                            </button>
                        </div>

                        <div class="video-btn-group-right">
                            <button class="v-ctrl-btn download-btn" id="btnDownloadAppVideo" onclick="exportAndDownloadVideo()" title="Export Video File to Phone/PC (ویڈیو ڈاؤن لوڈ کریں)">
                                <span>📥</span> <span>Download Video (WebM)</span>
                            </button>
                            <button class="v-ctrl-btn" onclick="toggleVideoFullscreen()" title="Fullscreen Video">
                                ⛶ Fullscreen
                            </button>
                        </div>
                    </div>

                    <!-- Quick Scene Jump Pills -->
                    <div class="video-scenes-nav" id="videoSceneChips">
                        <button class="scene-chip active" onclick="jumpToScene(0)">🌟 تعارف (Intro)</button>
                        <button class="scene-chip" onclick="jumpToScene(1)">🟡 سونا (Gold)</button>
                        <button class="scene-chip" onclick="jumpToScene(2)">⛽ پیٹرول (Fuel)</button>
                        <button class="scene-chip" onclick="jumpToScene(3)">⚡ سپیڈ ٹیسٹ (Speed)</button>
                        <button class="scene-chip" onclick="jumpToScene(4)">💡 بجلی بل (Bills)</button>
                        <button class="scene-chip" onclick="jumpToScene(5)">🍎 منڈی ریٹس (Mandi)</button>
                        <button class="scene-chip" onclick="jumpToScene(6)">🏗️ تعمیرات (House)</button>
                        <button class="scene-chip" onclick="jumpToScene(7)">💱 کرنسی و ٹیکس (Forex)</button>
                        <button class="scene-chip" onclick="jumpToScene(8)">👑 وی آئی پی (VIP Pro)</button>
                    </div>
                </div>
            </div>
"""

# Inject Video Hero inside view-trends at the top
if 'id="appVideoShowcaseSection"' not in html:
    html = html.replace('<section class="screen-view active" id="view-trends">\n            <div class="card elevated" id="recharts-main-dashboard-card">', 
                        '<section class="screen-view active" id="view-trends">\n' + video_hero_html + '\n            <div class="card elevated" id="recharts-main-dashboard-card">')

# Also create dedicated view-video section right before view-trends
dedicated_video_view = """
        <!-- ======================================================== -->
        <!-- 🎬 DEDICATED APP VIDEO & URDU SHOWCASE SCREEN            -->
        <!-- ======================================================== -->
        <section class="screen-view" id="view-video">
            <div class="card elevated" style="border:1.5px solid var(--gold);">
                <div class="card-header">
                    <div class="card-title-group">
                        <span class="card-title">PAKUTILITY HUB VIDEO PORTAL & INTERACTIVE TOUR</span>
                    </div>
                    <span class="badge gold">URDU AUDIO GUIDE</span>
                </div>
                <p style="font-size:12px; color:var(--text-secondary); margin-bottom:14px; line-height:1.5;">
                    Welcome to the official video presentation of PakUtility Hub. Watch live demonstrations of all 8 core utility tools with verified benchmarks and fluent Urdu commentary. You can also export and download this video directly to your phone or computer.
                </p>
                <div id="videoEmbedTarget"></div>
            </div>
        </section>
"""

if 'id="view-video"' not in html:
    html = html.replace('<section class="screen-view active" id="view-trends">', dedicated_video_view + '\n        <section class="screen-view active" id="view-trends">')

print("Step 4: Video hero and view-video section injected.")

# ---------------------------------------------------------------------------
# 5. ADD URDU SOUND BUTTONS TO ALL INDIVIDUAL FUNCTION CARDS
# ---------------------------------------------------------------------------
# Recharts Trends Card
html = html.replace('<span class="badge gold" id="trendMainBadge">OCT 16 LIVE</span>',
                    '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'trends\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge gold" id="trendMainBadge">OCT 16 LIVE</span></div>')

# Gold Card
gold_target = '<span class="badge gold">APGJSA BENCHMARK</span>'
if gold_target in html:
    html = html.replace(gold_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'gold\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge gold">APGJSA BENCHMARK</span></div>')

# Fuel Card
fuel_target = '<span class="badge emerald">OGRA VERIFIED</span>'
if fuel_target in html:
    html = html.replace(fuel_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'fuel\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge emerald">OGRA VERIFIED</span></div>')

# Speed Test Card
speed_target = '<span class="badge cyan">TELEMETRY</span>'
if speed_target in html:
    html = html.replace(speed_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'speed\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge cyan">TELEMETRY</span></div>')

# Bills Card
bill_target = '<span class="badge gold">NEPRA TARIFF</span>'
if bill_target in html:
    html = html.replace(bill_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'bills\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge gold">NEPRA TARIFF</span></div>')

# Mandi Card
mandi_target = '<span class="badge emerald">SEASONAL BAZAAR</span>'
if mandi_target in html:
    html = html.replace(mandi_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'mandi\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge emerald">SEASONAL BAZAAR</span></div>')

# Building Card
building_target = '<span class="badge gold">1-100 STOREYS</span>'
if building_target in html:
    html = html.replace(building_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'building\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge gold">1-100 STOREYS</span></div>')

# Forex Card
forex_target = '<span class="badge cyan">OPEN MARKET</span>'
if forex_target in html:
    html = html.replace(forex_target,
                        '<div style="display:flex; align-items:center; gap:8px;"><button class="card-urdu-listen-btn" onclick="speakFunctionUrdu(\'forex\')"><span>🔊</span> <span>سنیں (Urdu)</span></button><span class="badge cyan">OPEN MARKET</span></div>')

print("Step 5: Urdu voice buttons added to all cards.")

# ---------------------------------------------------------------------------
# 6. INJECT FLOATING DOCK AND AUDIO NARRATOR TOAST BEFORE </body>
# ---------------------------------------------------------------------------
floating_elements = """
    <!-- Floating Audio Narrator Subtitle & Equalizer Bar -->
    <div class="audio-narrator-toast" id="audioNarratorToast">
        <div class="narrator-wave-anim">
            <span></span><span></span><span></span><span></span>
        </div>
        <div class="narrator-text-box">
            <div class="narrator-tag">URDU AUDIO GUIDE • پاک صوتی گائیڈ</div>
            <div class="narrator-msg-urdu" id="narratorUrduText">پاک یوٹیلیٹی ہب میں خوش آمدید</div>
        </div>
        <button class="narrator-close-btn" onclick="stopUrduVoice()" title="Stop Audio">✕</button>
    </div>

    <!-- Floating Quick Action Dock -->
    <div class="floating-action-dock">
        <button class="dock-circle-btn emerald-accent" onclick="scrollToVideoShowcase()" title="Watch App Video Tour">
            <span>🎬</span>
            <span class="dock-btn-label">Video</span>
        </button>
        <button class="dock-circle-btn gold-accent" onclick="toggleUrduVoiceMaster()" id="dockUrduBtn" title="Toggle Urdu Voice Guide">
            <span id="dockUrduIcon">🔊</span>
            <span class="dock-btn-label">Urdu</span>
        </button>
        <button class="dock-circle-btn" onclick="quickLaunchSpeedTest()" title="Quick Speed Test">
            <span>⚡</span>
            <span class="dock-btn-label">Speed</span>
        </button>
        <button class="dock-circle-btn" onclick="window.scrollTo({top:0, behavior:'smooth'})" title="Back to Top">
            <span>▲</span>
            <span class="dock-btn-label">Top</span>
        </button>
    </div>
"""

if 'id="audioNarratorToast"' not in html:
    html = html.replace('</body>', floating_elements + '\n</body>')

print("Step 6: Floating dock and narrator toast injected.")

# ---------------------------------------------------------------------------
# 7. ADD JS LOGIC FOR VIDEO TOUR ENGINE, CANVAS RENDERER & URDU AUDIO SFX
# ---------------------------------------------------------------------------
video_js = """
    // =============================================================
    // 🔊 URDU AUDIO NARRATION & WEB AUDIO SFX ENGINE
    // =============================================================
    let isUrduVoiceMasterActive = true;
    let currentSpeechUtterance = null;
    let audioCtxInstance = null;

    function getAudioCtx() {
        if (!audioCtxInstance) {
            const AudioCtx = window.AudioContext || window.webkitAudioContext;
            if (AudioCtx) audioCtxInstance = new AudioCtx();
        }
        if (audioCtxInstance && audioCtxInstance.state === 'suspended') {
            audioCtxInstance.resume();
        }
        return audioCtxInstance;
    }

    // Rich Web Audio API Tactile Sound Effects (100% Dependency-Free)
    function playSfx(type) {
        try {
            const ctx = getAudioCtx();
            if (!ctx) return;
            const now = ctx.currentTime;

            if (type === 'click') {
                const osc = ctx.createOscillator();
                const gain = ctx.createGain();
                osc.type = 'triangle';
                osc.frequency.setValueAtTime(600, now);
                osc.frequency.exponentialRampToValueAtTime(300, now + 0.05);
                gain.gain.setValueAtTime(0.15, now);
                gain.gain.exponentialRampToValueAtTime(0.001, now + 0.05);
                osc.connect(gain);
                gain.connect(ctx.destination);
                osc.start(now);
                osc.stop(now + 0.06);
            } else if (type === 'tab' || type === 'whoosh') {
                const osc = ctx.createOscillator();
                const gain = ctx.createGain();
                osc.type = 'sine';
                osc.frequency.setValueAtTime(320, now);
                osc.frequency.exponentialRampToValueAtTime(740, now + 0.14);
                gain.gain.setValueAtTime(0.12, now);
                gain.gain.exponentialRampToValueAtTime(0.001, now + 0.18);
                osc.connect(gain);
                gain.connect(ctx.destination);
                osc.start(now);
                osc.stop(now + 0.2);
            } else if (type === 'kaching' || type === 'calc') {
                // Crisp cash register / calculation bell
                const freqs = [987.77, 1318.51, 1567.98]; // B5, E6, G6
                freqs.forEach((f, idx) => {
                    const osc = ctx.createOscillator();
                    const gain = ctx.createGain();
                    osc.type = 'sine';
                    osc.frequency.setValueAtTime(f, now + idx * 0.06);
                    gain.gain.setValueAtTime(0.16, now + idx * 0.06);
                    gain.gain.exponentialRampToValueAtTime(0.001, now + idx * 0.06 + 0.25);
                    osc.connect(gain);
                    gain.connect(ctx.destination);
                    osc.start(now + idx * 0.06);
                    osc.stop(now + idx * 0.06 + 0.26);
                });
            } else if (type === 'speed_rev') {
                const osc = ctx.createOscillator();
                const gain = ctx.createGain();
                osc.type = 'sawtooth';
                osc.frequency.setValueAtTime(140, now);
                osc.frequency.exponentialRampToValueAtTime(420, now + 0.4);
                gain.gain.setValueAtTime(0.08, now);
                gain.gain.exponentialRampToValueAtTime(0.001, now + 0.45);
                osc.connect(gain);
                gain.connect(ctx.destination);
                osc.start(now);
                osc.stop(now + 0.46);
            } else if (type === 'success') {
                // Triumphant 4-tone celebration chord
                const chord = [523.25, 659.25, 783.99, 1046.50]; // C5, E5, G5, C6
                chord.forEach((freq, i) => {
                    const osc = ctx.createOscillator();
                    const gain = ctx.createGain();
                    osc.type = 'sine';
                    osc.frequency.setValueAtTime(freq, now + i * 0.08);
                    gain.gain.setValueAtTime(0.14, now + i * 0.08);
                    gain.gain.exponentialRampToValueAtTime(0.001, now + i * 0.08 + 0.45);
                    osc.connect(gain);
                    gain.connect(ctx.destination);
                    osc.start(now + i * 0.08);
                    osc.stop(now + i * 0.08 + 0.46);
                });
            }
        } catch(e){}
    }

    // Urdu Function Narration Scripts
    const URDU_FUNCTION_SCRIPTS = {
        video: "پاک یوٹیلیٹی ہب میں خوش آمدید۔ پاکستان کا نمبر 1 لائیو یوٹیلیٹی پورٹل۔ اس ویڈیو ٹور میں تمام فیچرز اور لائیو ریٹس دیکھیں۔",
        trends: "سات روزہ مارکیٹ ٹرینڈز اور ری چارٹس تجزیہ۔ یہاں آپ سونے اور پیٹرول کی روزانہ کی قیمتوں کے اتار چڑھاؤ کا درست گراف دیکھ سکتے ہیں۔",
        gold: "سونے کے لائیو ریٹس۔ چوبیس قیراط سونا چار لاکھ سینتیس ہزار آٹھ سو چھتیس روپے فی تولہ، اور بائیس قیراط تین لاکھ چھیانوے ہزار روپے فی تولہ ہے۔ اپنا گولڈ ٹریڈ پرافٹ بھی سمولیٹ کریں۔",
        fuel: "اوگرا کے تصدیق شدہ پیٹرول اور ڈیزل ریٹس۔ سپر پیٹرول تین سو ترانوے روپے چھیاسی پیسے اور ڈیزل تین سو چورانوے روپے چورانوے پیسے فی لیٹر ہے۔ اپنی گاڑی کا فیول خرچ معلوم کریں۔",
        speed: "ایک منٹ کا ہائی پریسیشن انٹرنیٹ سپیڈ ٹیسٹ۔ ابھی ٹیسٹ شروع کریں اور ڈاؤن لوڈ رفتار، اپ لوڈ سپیڈ اور پنگ کی تصدیق کریں۔",
        bills: "نیپرا بجلی کا بل کیلکولیٹر اور ڈپلیکیٹ بل فائنڈر۔ لیسکو، آئیسکو، اور کے الیکٹرک کے بل چند سیکنڈ میں حاصل کریں۔",
        mandi: "روزمرہ سبزی اور پھل منڈی کے ہول سیل اور ریٹیل نرخ نامے۔ بادامی باغ لاہور اور سپر ہائی وے کراچی کی تصدیق شدہ قیمتیں۔",
        building: "ایک سے سو منزلہ مکان کی تعمیر کا مکمل تخمینہ۔ مغل گریڈ 60 سریا اور لکی سیمنٹ بوری کے تازہ ترین ریٹس۔",
        forex: "اوپن مارکیٹ کرنسی ریٹس اور تنخواہ پر سالانہ انکم ٹیکس کیلکولیٹر۔ امریکی ڈالر، یورو اور ریال کی تازہ قیمتیں۔",
        pro: "وی آئی پی پرو موڈ فعال کریں۔ تمام اشتہارات سے پاک تیز ترین براؤزنگ اور لائیو پش نوٹیفکیشن الرٹس حاصل کریں۔"
    };

    function speakUrdu(text, onComplete) {
        stopUrduVoice();
        playSfx('click');

        // Show Toast with Urdu text
        const toast = document.getElementById('audioNarratorToast');
        const textEl = document.getElementById('narratorUrduText');
        if (toast && textEl) {
            textEl.textContent = text;
            toast.classList.add('show');
        }

        const masterBtn = document.getElementById('urduVoiceMasterBtn');
        if (masterBtn) masterBtn.classList.add('speaking');

        if (!('speechSynthesis' in window)) {
            // Fallback: beep then complete
            playSfx('success');
            setTimeout(() => {
                if (toast) toast.classList.remove('show');
                if (masterBtn) masterBtn.classList.remove('speaking');
                if (onComplete) onComplete();
            }, 3000);
            return;
        }

        try {
            window.speechSynthesis.cancel();
            const utterance = new SpeechSynthesisUtterance(text);
            utterance.rate = 0.95;
            utterance.pitch = 1.0;
            utterance.volume = 1.0;

            // Find best Urdu/Hindustani voice
            const voices = window.speechSynthesis.getVoices() || [];
            const urduVoice = voices.find(v => v.lang === 'ur-PK' || v.lang === 'ur' || v.lang.startsWith('ur')) ||
                              voices.find(v => v.lang === 'hi-IN' || v.lang.startsWith('hi')) ||
                              voices.find(v => v.name.toLowerCase().includes('urdu') || v.name.toLowerCase().includes('hindi'));
            if (urduVoice) {
                utterance.voice = urduVoice;
                utterance.lang = urduVoice.lang;
            } else {
                utterance.lang = 'ur-PK';
            }

            utterance.onend = () => {
                if (toast) toast.classList.remove('show');
                if (masterBtn) masterBtn.classList.remove('speaking');
                currentSpeechUtterance = null;
                if (onComplete) onComplete();
            };

            utterance.onerror = () => {
                if (toast) toast.classList.remove('show');
                if (masterBtn) masterBtn.classList.remove('speaking');
                currentSpeechUtterance = null;
                if (onComplete) onComplete();
            };

            currentSpeechUtterance = utterance;
            window.speechSynthesis.speak(utterance);
        } catch(e) {
            if (toast) toast.classList.remove('show');
            if (masterBtn) masterBtn.classList.remove('speaking');
            if (onComplete) onComplete();
        }
    }

    function stopUrduVoice() {
        if ('speechSynthesis' in window) {
            try { window.speechSynthesis.cancel(); } catch(e){}
        }
        currentSpeechUtterance = null;
        const toast = document.getElementById('audioNarratorToast');
        if (toast) toast.classList.remove('show');
        const masterBtn = document.getElementById('urduVoiceMasterBtn');
        if (masterBtn) masterBtn.classList.remove('speaking');
        document.querySelectorAll('.card-urdu-listen-btn').forEach(b => b.classList.remove('speaking'));
    }

    function speakFunctionUrdu(funcKey) {
        const script = URDU_FUNCTION_SCRIPTS[funcKey];
        if (!script) return;
        stopUrduVoice();
        
        // Highlight corresponding button
        const btns = document.querySelectorAll('.card-urdu-listen-btn');
        btns.forEach(b => {
            if (b.getAttribute('onclick')?.includes(funcKey)) {
                b.classList.add('speaking');
            }
        });

        speakUrdu(script, () => {
            btns.forEach(b => b.classList.remove('speaking'));
        });
    }

    function toggleUrduVoiceMaster() {
        isUrduVoiceMasterActive = !isUrduVoiceMasterActive;
        const btn = document.getElementById('urduVoiceMasterBtn');
        const icon = document.getElementById('urduVoiceIcon');
        const label = document.getElementById('urduVoiceLabel');
        const dockIcon = document.getElementById('dockUrduIcon');

        if (isUrduVoiceMasterActive) {
            btn?.classList.add('active');
            if (icon) icon.textContent = '🔊';
            if (label) label.textContent = 'اردو آواز فعال';
            if (dockIcon) dockIcon.textContent = '🔊';
            playSfx('success');
            showPushToast('اردو آواز فعال ہے! 🔊', 'اب ہر فنکشن کے لائیو ریٹس اردو میں سنے جا سکتے ہیں۔', '📢', 'system');
            speakUrdu("اردو آواز کا نظام فعال کر دیا گیا ہے۔");
        } else {
            stopUrduVoice();
            btn?.classList.remove('active');
            if (icon) icon.textContent = '🔇';
            if (label) label.textContent = 'اردو میوٹ';
            if (dockIcon) dockIcon.textContent = '🔇';
            showPushToast('اردو آواز میوٹ 🔇', 'صوتی رہنمائی عارضی طور پر بند کر دی گئی ہے۔', '🔇', 'system');
        }
    }

    function scrollToVideoShowcase() {
        switchTab('trends');
        const el = document.getElementById('appVideoShowcaseSection');
        if (el) {
            el.scrollIntoView({ behavior: 'smooth', block: 'center' });
            if (!isVideoPlaying) {
                setTimeout(togglePlayVideoTour, 400);
            }
        }
    }

    function quickLaunchSpeedTest() {
        switchTab('speed');
        playSfx('tab');
        setTimeout(() => {
            if (typeof startSpeedTest === 'function') startSpeedTest();
        }, 300);
    }

    // =============================================================
    // 🎬 HIGH-DEFINITION CANVAS VIDEO TOUR & RECORDER ENGINE
    // =============================================================
    const VIDEO_SCENES = [
        { id: 0, title: "PAKUTILITY HUB", sub: "پاکستان کا نمبر 1 لائیو یوٹیلیٹی پورٹل", duration: 6, urdu: "پاک یوٹیلیٹی ہب میں خوش آمدید۔ پاکستان کا نمبر 1 لائیو یوٹیلیٹی پورٹل، جہاں آپ کو ملتے ہیں تمام تصدیق شدہ سرکاری ریٹس۔" },
        { id: 1, title: "24K GOLD BULLION", sub: "چوبیس قیراط سونا اور صرافہ بازار ٹریڈ", duration: 6, urdu: "سونے کے لائیو ریٹس! چوبیس قیراط سونا چار لاکھ سینتیس ہزار آٹھ سو چھتیس روپے فی تولہ، صرافہ بازار کے تصدیق شدہ گراف کے ساتھ۔" },
        { id: 2, title: "OGRA FUEL & PETROL", sub: "پیٹرول اور ڈیزل کے تصدیق شدہ ریٹس", duration: 6, urdu: "اوگرا پیٹرول اور ڈیزل کے تصدیق شدہ نرخ، سپر پیٹرول تین سو ترانوے روپے چھیاسی پیسے فی لیٹر، اور گاڑی کا فیول خرچ معلوم کریں۔" },
        { id: 3, title: "NET SPEED TEST", sub: "ایک منٹ کا ہائی پریسیشن سپیڈ ٹیسٹ", duration: 6, urdu: "انٹرنیٹ سپیڈ ٹیسٹ! ڈاؤن لوڈ رفتار اور پنگ کی درست پیمائش ایک منٹ کے اندر۔" },
        { id: 4, title: "ELECTRICITY BILLS", sub: "نیپرا بجلی بل اور ڈپلیکیٹ بلز", duration: 6, urdu: "نیپرا بجلی بل کیلکولیٹر اور لیسکو، آئیسکو، اور کے الیکٹرک کے ڈپلیکیٹ بلز فوری حاصل کریں۔" },
        { id: 5, title: "MANDI BAZAAR", sub: "سبزی و پھل منڈی کے ہول سیل نرخ", duration: 6, urdu: "روزمرہ سبزی و پھل منڈی کے ہول سیل اور ریٹیل نرخ نامے۔" },
        { id: 6, title: "HOUSE CONSTRUCTION", sub: "ایک سے سو منزلہ عمارت کا تخمینہ", duration: 6, urdu: "ایک سے سو منزلہ مکان کی تعمیر کا تخمینہ، مغل گریڈ 60 سریا اور لکی سیمنٹ کے تازہ ریٹس۔" },
        { id: 7, title: "FOREX & FBR TAX", sub: "کرنسی ایکسچینج اور انکم ٹیکس", duration: 6, urdu: "امریکی ڈالر اوپن مارکیٹ ریٹس اور فنانس ایکٹ کے مطابق تنخواہ پر انکم ٹیکس کا حساب۔" },
        { id: 8, title: "VIP PRO & VERIFIED", sub: "تمام اشتہارات سے پاک اور لائیو الرٹس", duration: 6, urdu: "پاک یوٹیلیٹی ہب، آپ کی تمام ضروریات کا ایک مکمل حل۔ آج ہی وزٹ کریں۔" }
    ];

    const TOTAL_VIDEO_DURATION = VIDEO_SCENES.reduce((acc, s) => acc + s.duration, 0); // 54 seconds
    let videoCurrentTime = 0;
    let isVideoPlaying = false;
    let videoPlaybackRate = 1.0;
    let isVideoVoiceoverEnabled = true;
    let videoLastFrameTimestamp = null;
    let videoAnimFrameId = null;
    let activeSceneIndex = -1;

    function initVideoTourCanvas() {
        const canvas = document.getElementById('pakVideoCanvas');
        if (!canvas) return;
        renderVideoFrame(canvas, 0);
    }

    function togglePlayVideoTour() {
        isVideoPlaying = !isVideoPlaying;
        const playBtn = document.getElementById('videoBigPlayBtn');
        const playIcon = document.getElementById('vPlayIcon');
        const playText = document.getElementById('vPlayText');

        if (isVideoPlaying) {
            playSfx('whoosh');
            if (playBtn) playBtn.classList.add('playing');
            if (playIcon) playIcon.textContent = '⏸';
            if (playText) playText.textContent = 'Pause';
            videoLastFrameTimestamp = performance.now();
            requestAnimationFrame(videoTourLoop);
        } else {
            playSfx('click');
            if (playBtn) playBtn.classList.remove('playing');
            if (playIcon) playIcon.textContent = '▶';
            if (playText) playText.textContent = 'Play Video';
            if (videoAnimFrameId) cancelAnimationFrame(videoAnimFrameId);
            stopUrduVoice();
        }
    }

    function restartVideoTour() {
        videoCurrentTime = 0;
        activeSceneIndex = -1;
        const slider = document.getElementById('videoTimelineSlider');
        if (slider) slider.value = 0;
        updateVideoTimeDisplay();
        if (!isVideoPlaying) togglePlayVideoTour();
        else {
            playSfx('whoosh');
            checkSceneVoiceover(0);
        }
    }

    function seekVideoTour(newSec) {
        videoCurrentTime = parseFloat(newSec);
        updateVideoTimeDisplay();
        const canvas = document.getElementById('pakVideoCanvas');
        if (canvas) renderVideoFrame(canvas, videoCurrentTime);
        checkSceneVoiceover(videoCurrentTime);
    }

    function jumpToScene(sceneIdx) {
        let t = 0;
        for (let i = 0; i < sceneIdx; i++) {
            t += VIDEO_SCENES[i].duration;
        }
        seekVideoTour(t);
        const slider = document.getElementById('videoTimelineSlider');
        if (slider) slider.value = t;
        if (!isVideoPlaying) togglePlayVideoTour();
    }

    function cycleVideoSpeed() {
        const rates = [1.0, 1.25, 1.5];
        const curIdx = rates.indexOf(videoPlaybackRate);
        videoPlaybackRate = rates[(curIdx + 1) % rates.length];
        const label = document.getElementById('vSpeedLabel');
        if (label) label.textContent = `${videoPlaybackRate}x`;
        playSfx('click');
    }

    function toggleVideoVoiceover() {
        isVideoVoiceoverEnabled = !isVideoVoiceoverEnabled;
        const icon = document.getElementById('vMuteIcon');
        if (icon) icon.textContent = isVideoVoiceoverEnabled ? '🔊' : '🔇';
        if (!isVideoVoiceoverEnabled) stopUrduVoice();
        else checkSceneVoiceover(videoCurrentTime);
        playSfx('click');
    }

    function toggleVideoFullscreen() {
        const container = document.getElementById('videoCanvasContainer');
        if (!container) return;
        if (!document.fullscreenElement) {
            container.requestFullscreen?.().catch(()=>{});
        } else {
            document.exitFullscreen?.().catch(()=>{});
        }
    }

    function updateVideoTimeDisplay() {
        const disp = document.getElementById('videoTimeDisplay');
        if (disp) {
            const curM = Math.floor(videoCurrentTime / 60);
            const curS = Math.floor(videoCurrentTime % 60);
            const totM = Math.floor(TOTAL_VIDEO_DURATION / 60);
            const totS = Math.floor(TOTAL_VIDEO_DURATION % 60);
            disp.textContent = `${String(curM).padStart(2,'0')}:${String(curS).padStart(2,'0')} / ${String(totM).padStart(2,'0')}:${String(totS).padStart(2,'0')}`;
        }
    }

    function videoTourLoop(timestamp) {
        if (!isVideoPlaying) return;
        const delta = (timestamp - videoLastFrameTimestamp) / 1000;
        videoLastFrameTimestamp = timestamp;

        videoCurrentTime += delta * videoPlaybackRate;
        if (videoCurrentTime >= TOTAL_VIDEO_DURATION) {
            videoCurrentTime = TOTAL_VIDEO_DURATION;
            isVideoPlaying = false;
            const playBtn = document.getElementById('videoBigPlayBtn');
            const playIcon = document.getElementById('vPlayIcon');
            const playText = document.getElementById('vPlayText');
            if (playBtn) playBtn.classList.remove('playing');
            if (playIcon) playIcon.textContent = '🔄';
            if (playText) playText.textContent = 'Replay';
            playSfx('success');
        }

        const slider = document.getElementById('videoTimelineSlider');
        if (slider) slider.value = videoCurrentTime;
        updateVideoTimeDisplay();

        const canvas = document.getElementById('pakVideoCanvas');
        if (canvas) renderVideoFrame(canvas, videoCurrentTime);

        checkSceneVoiceover(videoCurrentTime);

        if (isVideoPlaying) {
            videoAnimFrameId = requestAnimationFrame(videoTourLoop);
        }
    }

    function checkSceneVoiceover(t) {
        let accum = 0;
        let sIdx = 0;
        for (let i = 0; i < VIDEO_SCENES.length; i++) {
            if (t >= accum && t < accum + VIDEO_SCENES[i].duration) {
                sIdx = i;
                break;
            }
            accum += VIDEO_SCENES[i].duration;
        }

        // Highlight chip
        const chips = document.querySelectorAll('.scene-chip');
        chips.forEach((c, i) => c.classList.toggle('active', i === sIdx));

        if (sIdx !== activeSceneIndex) {
            activeSceneIndex = sIdx;
            playSfx('whoosh');
            if (isVideoVoiceoverEnabled && isUrduVoiceMasterActive) {
                speakUrdu(VIDEO_SCENES[sIdx].urdu);
            }
        }
    }

    // -------------------------------------------------------------
    // RENDER CANVASES FOR EACH SCENE (1280 x 720 16:9 HD)
    // -------------------------------------------------------------
    function renderVideoFrame(canvas, time) {
        const ctx = canvas.getContext('2d');
        const W = canvas.width, H = canvas.height;

        // Background gradient
        const bgGrad = ctx.createLinearGradient(0, 0, W, H);
        bgGrad.addColorStop(0, '#040711');
        bgGrad.addColorStop(0.5, '#0B132B');
        bgGrad.addColorStop(1, '#060A17');
        ctx.fillStyle = bgGrad;
        ctx.fillRect(0, 0, W, H);

        // Dynamic star particles
        const seed = Math.floor(time * 3);
        ctx.fillStyle = 'rgba(245, 158, 11, 0.4)';
        for (let i = 0; i < 40; i++) {
            const px = ((i * 137 + seed * 2) % W);
            const py = ((i * 229 + seed * 3) % (H - 120));
            const pr = (i % 3) + 1;
            ctx.beginPath();
            ctx.arc(px, py, pr, 0, Math.PI * 2);
            ctx.fill();
        }

        // Animated subtle radar wave
        const waveRadius = ((time * 60) % (W / 1.5));
        ctx.strokeStyle = 'rgba(16, 185, 129, 0.12)';
        ctx.lineWidth = 2;
        ctx.beginPath();
        ctx.arc(W / 2, H / 2, waveRadius, 0, Math.PI * 2);
        ctx.stroke();

        // Identify current scene
        let accum = 0;
        let sIdx = 0;
        for (let i = 0; i < VIDEO_SCENES.length; i++) {
            if (time >= accum && time < accum + VIDEO_SCENES[i].duration) {
                sIdx = i;
                break;
            }
            accum += VIDEO_SCENES[i].duration;
        }
        const scene = VIDEO_SCENES[sIdx];
        const sceneTime = time - accum;
        const progress = Math.min(1, Math.max(0, sceneTime / scene.duration));

        // Draw Scene Content
        switch (sIdx) {
            case 0: renderScene0Intro(ctx, W, H, progress); break;
            case 1: renderScene1Gold(ctx, W, H, progress); break;
            case 2: renderScene2Fuel(ctx, W, H, progress); break;
            case 3: renderScene3Speed(ctx, W, H, progress); break;
            case 4: renderScene4Bills(ctx, W, H, progress); break;
            case 5: renderScene5Mandi(ctx, W, H, progress); break;
            case 6: renderScene6Building(ctx, W, H, progress); break;
            case 7: renderScene7Forex(ctx, W, H, progress); break;
            case 8: renderScene8Outro(ctx, W, H, progress); break;
        }

        // Bottom Subtitle & Branding Banner
        renderVideoBannerBar(ctx, W, H, scene, time);
    }

    function renderVideoBannerBar(ctx, W, H, scene, time) {
        // Bottom overlay strip
        const barH = 100;
        const barY = H - barH;
        ctx.fillStyle = 'rgba(6, 9, 20, 0.94)';
        ctx.fillRect(0, barY, W, barH);

        // Glowing separator line
        const lineGrad = ctx.createLinearGradient(0, barY, W, barY);
        lineGrad.addColorStop(0, '#10B981');
        lineGrad.addColorStop(0.5, '#F59E0B');
        lineGrad.addColorStop(1, '#10B981');
        ctx.strokeStyle = lineGrad;
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.moveTo(0, barY);
        ctx.lineTo(W, barY);
        ctx.stroke();

        // Left Branding Tag
        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 15px sans-serif';
        ctx.textAlign = 'left';
        ctx.fillText('🇵🇰 PAKUTILITY HUB', 30, barY + 38);

        ctx.fillStyle = '#F59E0B';
        ctx.font = 'bold 12px sans-serif';
        ctx.fillText('VERIFIED LIVE BENCHMARKS', 30, barY + 62);

        // Center Urdu Subtitles
        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 22px "Noto Nastaliq Urdu", sans-serif';
        ctx.textAlign = 'center';
        ctx.fillText(scene.sub, W / 2, barY + 45);

        ctx.fillStyle = '#94A3B8';
        ctx.font = '14px sans-serif';
        ctx.fillText(scene.title, W / 2, barY + 76);

        // Right timestamp & Live beacon
        ctx.textAlign = 'right';
        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 14px monospace';
        const curM = Math.floor(time / 60);
        const curS = Math.floor(time % 60);
        ctx.fillText(`● LIVE HD  ${String(curM).padStart(2,'0')}:${String(curS).padStart(2,'0')}`, W - 30, barY + 54);
    }

    // --- Scene 0: Introduction ---
    function renderScene0Intro(ctx, W, H, p) {
        const cy = H / 2 - 40;
        // Central glowing badge
        ctx.save();
        ctx.translate(W / 2, cy);
        const scale = 0.8 + 0.2 * Math.sin(p * Math.PI);
        ctx.scale(scale, scale);

        // Radial gold starburst
        const radGrad = ctx.createRadialGradient(0, 0, 20, 0, 0, 180);
        radGrad.addColorStop(0, 'rgba(245, 158, 11, 0.45)');
        radGrad.addColorStop(1, 'rgba(16, 185, 129, 0)');
        ctx.fillStyle = radGrad;
        ctx.beginPath();
        ctx.arc(0, 0, 180, 0, Math.PI * 2);
        ctx.fill();

        // Emblem Box
        ctx.fillStyle = '#0B1120';
        ctx.strokeStyle = '#F59E0B';
        ctx.lineWidth = 4;
        ctx.beginPath();
        ctx.roundRect(-70, -70, 140, 140, 24);
        ctx.fill();
        ctx.stroke();

        ctx.font = '64px sans-serif';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText('🇵🇰', 0, 0);
        ctx.restore();

        // Main Title
        ctx.textAlign = 'center';
        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 44px sans-serif';
        ctx.fillText('PakUtility Hub', W / 2, cy + 120);

        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 26px "Noto Nastaliq Urdu", sans-serif';
        ctx.fillText('پاکستان کا ہمہ گیر یوٹیلیٹی اور لائیو مارکیٹ پورٹل', W / 2, cy + 165);

        // Verified pills
        const pills = ['✓ 24K GOLD BULLION', '✓ OGRA PETROLEUM', '✓ SPEED TEST', '✓ NEPRA BILLS'];
        pills.forEach((pill, i) => {
            const px = W / 2 - 300 + i * 200;
            ctx.fillStyle = 'rgba(245, 158, 11, 0.18)';
            ctx.strokeStyle = '#F59E0B';
            ctx.lineWidth = 1.5;
            ctx.beginPath();
            ctx.roundRect(px - 85, cy + 200, 170, 32, 16);
            ctx.fill();
            ctx.stroke();

            ctx.fillStyle = '#FCD34D';
            ctx.font = 'bold 12px sans-serif';
            ctx.fillText(pill, px, cy + 221);
        });
    }

    // --- Scene 1: 24K Gold Bullion ---
    function renderScene1Gold(ctx, W, H, p) {
        const cy = H / 2 - 40;
        // 3D Gold Bar simulation
        ctx.save();
        ctx.translate(W / 2 - 240, cy);
        
        // Gold bar body
        const goldGrad = ctx.createLinearGradient(-120, -60, 120, 60);
        goldGrad.addColorStop(0, '#F59E0B');
        goldGrad.addColorStop(0.3, '#FDE68A');
        goldGrad.addColorStop(0.6, '#D97706');
        goldGrad.addColorStop(1, '#B45309');
        ctx.fillStyle = goldGrad;
        ctx.strokeStyle = '#FEF08A';
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.roundRect(-120, -60, 240, 120, 14);
        ctx.fill();
        ctx.stroke();

        ctx.fillStyle = '#78350F';
        ctx.font = 'bold 22px sans-serif';
        ctx.textAlign = 'center';
        ctx.fillText('999.9 FINE GOLD', 0, -18);
        ctx.font = 'bold 36px sans-serif';
        ctx.fillText('1 TOLA', 0, 24);
        ctx.restore();

        // Rates Panel
        const rx = W / 2 + 100;
        ctx.textAlign = 'left';
        ctx.fillStyle = '#F59E0B';
        ctx.font = 'bold 18px sans-serif';
        ctx.fillText('SARAFA BAZAAR PAKISTAN BENCHMARK', rx, cy - 80);

        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 42px sans-serif';
        ctx.fillText('PKR 437,836', rx, cy - 30);
        ctx.font = 'bold 20px "Noto Nastaliq Urdu", sans-serif';
        ctx.fillStyle = '#10B981';
        ctx.fillText('فی تولہ 24 قیراط سونا  ▲ +0.2%', rx, cy + 10);

        ctx.fillStyle = '#94A3B8';
        ctx.font = 'bold 22px sans-serif';
        ctx.fillText('22K Jewellery: PKR 396,458 / Tola', rx, cy + 50);
        ctx.fillText('10 Grams: PKR 375,373', rx, cy + 85);

        // Trend line mini-chart
        ctx.strokeStyle = '#10B981';
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.moveTo(rx, cy + 130);
        ctx.lineTo(rx + 60, cy + 120);
        ctx.lineTo(rx + 120, cy + 125);
        ctx.lineTo(rx + 180, cy + 105);
        ctx.lineTo(rx + 240, cy + 95);
        ctx.stroke();
    }

    // --- Scene 2: Fuel & Petrol ---
    function renderScene2Fuel(ctx, W, H, p) {
        const cy = H / 2 - 40;
        // Dispenser Graphic Left
        const lx = W / 2 - 220;
        ctx.fillStyle = '#0F172A';
        ctx.strokeStyle = '#38BDF8';
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.roundRect(lx - 100, cy - 110, 200, 220, 16);
        ctx.fill();
        ctx.stroke();

        ctx.font = '48px sans-serif';
        ctx.textAlign = 'center';
        ctx.fillText('⛽', lx, cy - 20);
        ctx.fillStyle = '#38BDF8';
        ctx.font = 'bold 16px sans-serif';
        ctx.fillText('OGRA OFFICIAL', lx, cy + 40);
        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 18px "Noto Nastaliq Urdu", sans-serif';
        ctx.fillText('پیٹرولیم نرخ', lx, cy + 70);

        // Right Fuel Prices
        const rx = W / 2 + 60;
        ctx.textAlign = 'left';

        // Petrol Card
        ctx.fillStyle = 'rgba(56, 189, 248, 0.12)';
        ctx.strokeStyle = '#38BDF8';
        ctx.beginPath();
        ctx.roundRect(rx - 20, cy - 110, 360, 100, 12);
        ctx.fill();
        ctx.stroke();
        ctx.fillStyle = '#38BDF8';
        ctx.font = 'bold 15px sans-serif';
        ctx.fillText('SUPER PETROL (سپر پیٹرول)', rx, cy - 78);
        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 36px sans-serif';
        ctx.fillText('PKR 393.86 / L', rx, cy - 34);

        // Diesel Card
        ctx.fillStyle = 'rgba(245, 158, 11, 0.12)';
        ctx.strokeStyle = '#F59E0B';
        ctx.beginPath();
        ctx.roundRect(rx - 20, cy + 8, 360, 100, 12);
        ctx.fill();
        ctx.stroke();
        ctx.fillStyle = '#F59E0B';
        ctx.font = 'bold 15px sans-serif';
        ctx.fillText('HIGH SPEED DIESEL (ڈیزل)', rx, cy + 40);
        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 36px sans-serif';
        ctx.fillText('PKR 394.94 / L', rx, cy + 84);
    }

    // --- Scene 3: Net Speed Test ---
    function renderScene3Speed(ctx, W, H, p) {
        const cy = H / 2 - 40;
        const cx = W / 2;

        // Circular Speedometer Gauge
        const radius = 120;
        ctx.save();
        ctx.translate(cx, cy);

        // Outer Arc
        ctx.strokeStyle = 'rgba(255, 255, 255, 0.15)';
        ctx.lineWidth = 14;
        ctx.beginPath();
        ctx.arc(0, 0, radius, Math.PI * 0.75, Math.PI * 2.25);
        ctx.stroke();

        // Active Speed Arc (smoothly revving up to 105.4 MB/s)
        const targetSpeed = 105.4;
        const speedVal = targetSpeed * (0.3 + 0.7 * Math.sin(p * Math.PI));
        const angle = Math.PI * 0.75 + (speedVal / 150) * (Math.PI * 1.5);

        const arcGrad = ctx.createLinearGradient(-radius, 0, radius, 0);
        arcGrad.addColorStop(0, '#38BDF8');
        arcGrad.addColorStop(1, '#10B981');
        ctx.strokeStyle = arcGrad;
        ctx.lineWidth = 14;
        ctx.beginPath();
        ctx.arc(0, 0, radius, Math.PI * 0.75, angle);
        ctx.stroke();

        // Speed Number Display
        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 48px monospace';
        ctx.textAlign = 'center';
        ctx.fillText(speedVal.toFixed(1), 0, 10);

        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 18px sans-serif';
        ctx.fillText('MB / SEC', 0, 42);

        ctx.fillStyle = '#94A3B8';
        ctx.font = '14px sans-serif';
        ctx.fillText('HIGH PRECISION TELEMETRY', 0, 70);

        ctx.restore();

        // Telemetry stats row
        const stats = [
            { label: 'PING LATENCY', val: '12 ms', c: '#10B981' },
            { label: 'JITTER', val: '1.8 ms', c: '#38BDF8' },
            { label: 'UPLOAD SPEED', val: '48.2 MB/s', c: '#F59E0B' },
            { label: 'SERVER ROUTE', val: 'PTCL Lahore', c: '#FFFFFF' }
        ];
        stats.forEach((s, idx) => {
            const sx = cx - 360 + idx * 240;
            ctx.fillStyle = 'rgba(15, 23, 42, 0.85)';
            ctx.strokeStyle = 'rgba(255,255,255,0.1)';
            ctx.beginPath();
            ctx.roundRect(sx - 100, cy + 150, 200, 50, 10);
            ctx.fill();
            ctx.stroke();

            ctx.fillStyle = s.c;
            ctx.font = 'bold 16px monospace';
            ctx.textAlign = 'center';
            ctx.fillText(s.val, sx, cy + 174);
            ctx.fillStyle = '#94A3B8';
            ctx.font = '10px sans-serif';
            ctx.fillText(s.label, sx, cy + 190);
        });
    }

    // --- Scene 4: Electricity Bills & Duplicate Bills ---
    function renderScene4Bills(ctx, W, H, p) {
        const cy = H / 2 - 40;
        // Bill Mockup Card
        const bx = W / 2 - 200;
        ctx.fillStyle = '#FFFFFF';
        ctx.strokeStyle = '#F59E0B';
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.roundRect(bx - 120, cy - 120, 240, 240, 12);
        ctx.fill();
        ctx.stroke();

        ctx.fillStyle = '#060914';
        ctx.font = 'bold 16px sans-serif';
        ctx.textAlign = 'center';
        ctx.fillText('NEPRA ELECTRICITY BILL', bx, cy - 85);
        ctx.font = '11px monospace';
        ctx.fillStyle = '#64748B';
        ctx.fillText('REF: 08 11512 0456789 U', bx, cy - 65);

        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 28px sans-serif';
        ctx.fillText('340 UNITS', bx, cy - 20);

        ctx.fillStyle = '#D97706';
        ctx.font = 'bold 22px monospace';
        ctx.fillText('PKR 14,820', bx, cy + 20);

        // Verification Stamp
        ctx.save();
        ctx.translate(bx, cy + 65);
        ctx.rotate(-0.15);
        ctx.strokeStyle = '#10B981';
        ctx.lineWidth = 2.5;
        ctx.strokeRect(-55, -15, 110, 30);
        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 14px sans-serif';
        ctx.fillText('PAID / تصدیق', 0, 5);
        ctx.restore();

        // Right side DISCO list
        const rx = W / 2 + 100;
        ctx.textAlign = 'left';
        ctx.fillStyle = '#F59E0B';
        ctx.font = 'bold 18px sans-serif';
        ctx.fillText('DUPLICATE BILL FINDER (ڈپلیکیٹ بل)', rx, cy - 90);

        const discos = [
            '⚡ LESCO — Lahore Electric Supply Company',
            '⚡ IESCO — Islamabad & Rawalpindi',
            '⚡ K-ELECTRIC — Karachi Megacity',
            '⚡ MEPCO — Multan & South Punjab',
            '⚡ FESCO / GEPCO / PESCO Online'
        ];
        discos.forEach((d, i) => {
            ctx.fillStyle = '#FFFFFF';
            ctx.font = 'bold 16px sans-serif';
            ctx.fillText(d, rx, cy - 45 + i * 36);
        });
    }

    // --- Scene 5: Mandi Bazaar ---
    function renderScene5Mandi(ctx, W, H, p) {
        const cy = H / 2 - 40;
        ctx.textAlign = 'center';
        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 22px "Noto Nastaliq Urdu", sans-serif';
        ctx.fillText('سبزی و پھل منڈی کے روزانہ کے سرکاری و ہول سیل نرخ', W / 2, cy - 100);

        const items = [
            { icon: '🍅', name: 'Tomato (ٹماٹر)', rate: 'PKR 135 / kg', tag: 'Badami Bagh' },
            { icon: '🧅', name: 'Onion (پیاز)', rate: 'PKR 110 / kg', tag: 'Super Highway' },
            { icon: '🥔', name: 'Potato (آلو)', rate: 'PKR 85 / kg', tag: 'Mandi Wholesale' },
            { icon: '🧄', name: 'Garlic (لہسن)', rate: 'PKR 420 / kg', tag: 'Grade A China' }
        ];

        items.forEach((it, i) => {
            const ix = W / 2 - 390 + i * 260;
            ctx.fillStyle = 'rgba(15, 23, 42, 0.9)';
            ctx.strokeStyle = '#10B981';
            ctx.lineWidth = 2;
            ctx.beginPath();
            ctx.roundRect(ix - 110, cy - 50, 220, 160, 14);
            ctx.fill();
            ctx.stroke();

            ctx.font = '40px sans-serif';
            ctx.fillText(it.icon, ix, cy);
            ctx.fillStyle = '#FFFFFF';
            ctx.font = 'bold 15px sans-serif';
            ctx.fillText(it.name, ix, cy + 35);
            ctx.fillStyle = '#F59E0B';
            ctx.font = 'bold 18px monospace';
            ctx.fillText(it.rate, ix, cy + 65);
            ctx.fillStyle = '#94A3B8';
            ctx.font = '11px sans-serif';
            ctx.fillText(it.tag, ix, cy + 90);
        });
    }

    // --- Scene 6: 1-100 Storey House Construction Cost ---
    function renderScene6Building(ctx, W, H, p) {
        const cy = H / 2 - 40;
        // Blueprint wireframe building
        const bx = W / 2 - 200;
        ctx.strokeStyle = '#38BDF8';
        ctx.lineWidth = 2;
        ctx.strokeRect(bx - 100, cy - 110, 200, 220);

        // Storey lines
        for (let s = 1; s <= 5; s++) {
            ctx.beginPath();
            ctx.moveTo(bx - 100, cy + 110 - s * 40);
            ctx.lineTo(bx + 100, cy + 110 - s * 40);
            ctx.stroke();
        }
        ctx.fillStyle = '#38BDF8';
        ctx.font = 'bold 18px sans-serif';
        ctx.textAlign = 'center';
        ctx.fillText('1-100 STOREYS', bx, cy + 70);

        // Materials Rates List Right
        const rx = W / 2 + 60;
        ctx.textAlign = 'left';
        ctx.fillStyle = '#F59E0B';
        ctx.font = 'bold 20px sans-serif';
        ctx.fillText('CONSTRUCTION MATERIALS BENCHMARK', rx, cy - 80);

        const mats = [
            { name: 'Mughal Saria Grade 60 (سریا)', price: 'PKR 278 / kg' },
            { name: 'Lucky Cement Bag (سیمنٹ)', price: 'PKR 1,480 / bag' },
            { name: 'Awwal Bricks (اول اینٹیں)', price: 'PKR 14.50 / brick' },
            { name: 'Ravi Sand & Crushed Bajri', price: 'PKR 65 / cft' }
        ];
        mats.forEach((m, idx) => {
            ctx.fillStyle = '#FFFFFF';
            ctx.font = 'bold 16px sans-serif';
            ctx.fillText(m.name, rx, cy - 35 + idx * 45);
            ctx.fillStyle = '#10B981';
            ctx.font = 'bold 18px monospace';
            ctx.fillText(m.price, rx, cy - 15 + idx * 45);
        });
    }

    // --- Scene 7: Forex Currency & FBR Tax ---
    function renderScene7Forex(ctx, W, H, p) {
        const cy = H / 2 - 40;
        ctx.textAlign = 'center';
        ctx.fillStyle = '#38BDF8';
        ctx.font = 'bold 22px sans-serif';
        ctx.fillText('OPEN MARKET FOREX & FBR SALARY TAX CALCULATOR', W / 2, cy - 90);

        const fx = [
            { flag: '🇺🇸', code: 'USD/PKR', rate: '282.50' },
            { flag: '🇪🇺', code: 'EUR/PKR', rate: '308.20' },
            { flag: '🇸🇦', code: 'SAR/PKR', rate: '75.30' },
            { flag: '🇦🇪', code: 'AED/PKR', rate: '76.90' }
        ];

        fx.forEach((item, i) => {
            const fxX = W / 2 - 360 + i * 240;
            ctx.fillStyle = 'rgba(15, 23, 42, 0.9)';
            ctx.strokeStyle = '#38BDF8';
            ctx.lineWidth = 1.5;
            ctx.beginPath();
            ctx.roundRect(fxX - 100, cy - 40, 200, 70, 10);
            ctx.fill();
            ctx.stroke();

            ctx.font = '24px sans-serif';
            ctx.fillText(item.flag, fxX - 60, cy);
            ctx.fillStyle = '#FFFFFF';
            ctx.font = 'bold 15px sans-serif';
            ctx.fillText(item.code, fxX + 15, cy - 8);
            ctx.fillStyle = '#F59E0B';
            ctx.font = 'bold 18px monospace';
            ctx.fillText(`PKR ${item.rate}`, fxX + 15, cy + 18);
        });

        // FBR Tax Banner
        ctx.fillStyle = 'rgba(16, 185, 129, 0.15)';
        ctx.strokeStyle = '#10B981';
        ctx.beginPath();
        ctx.roundRect(W / 2 - 360, cy + 60, 720, 80, 12);
        ctx.fill();
        ctx.stroke();

        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 20px "Noto Nastaliq Urdu", sans-serif';
        ctx.fillText('ایف بی آر فنانس ایکٹ 2025-2026 کے مطابق تنخواہ پر ماہانہ انکم ٹیکس کا حساب', W / 2, cy + 96);
        ctx.fillStyle = '#FCD34D';
        ctx.font = 'bold 14px sans-serif';
        ctx.fillText('Accurate Slabs • Zero Confusion • Instant Salary Slip Projection', W / 2, cy + 124);
    }

    // --- Scene 8: VIP Pro & Outro ---
    function renderScene8Outro(ctx, W, H, p) {
        const cy = H / 2 - 40;
        ctx.textAlign = 'center';
        
        ctx.fillStyle = '#F59E0B';
        ctx.font = 'bold 48px sans-serif';
        ctx.fillText('👑 VIP PRO ACTIVATED', W / 2, cy - 60);

        ctx.fillStyle = '#10B981';
        ctx.font = 'bold 28px "Noto Nastaliq Urdu", sans-serif';
        ctx.fillText('100% اشتہارات سے پاک اور لائیو نوٹیفکیشن الرٹس', W / 2, cy);

        ctx.fillStyle = '#FFFFFF';
        ctx.font = 'bold 24px monospace';
        ctx.fillText('JAZZCASH VERIFIED: 0306-4678975', W / 2, cy + 60);

        ctx.fillStyle = '#38BDF8';
        ctx.font = 'bold 22px sans-serif';
        ctx.fillText('VISIT TODAY: HTTPS://PAKUTILITY.COM', W / 2, cy + 110);
    }

    // -------------------------------------------------------------
    // EXPORT & DOWNLOAD VIDEO (WEBM / MP4) VIA MediaRecorder
    // -------------------------------------------------------------
    let mediaRecorderInstance = null;
    let recordedVideoChunks = [];

    function exportAndDownloadVideo() {
        const canvas = document.getElementById('pakVideoCanvas');
        if (!canvas) return;

        if (!('MediaRecorder' in window) || typeof canvas.captureStream !== 'function') {
            alert('Your browser does not support in-browser canvas video recording. Please use Google Chrome or Microsoft Edge.');
            return;
        }

        const btn = document.getElementById('btnDownloadAppVideo');
        const badge = document.getElementById('videoRecBadge');
        const recText = document.getElementById('videoRecText');

        if (btn) btn.disabled = true;
        if (badge) badge.classList.add('active');

        playSfx('whoosh');
        showPushToast('Recording Video... 🎬', 'App video tour is being captured in 1080p HD. Please wait...', '📹', 'system');

        // Rewind to start
        videoCurrentTime = 0;
        activeSceneIndex = -1;
        isVideoPlaying = true;
        videoLastFrameTimestamp = performance.now();

        const stream = canvas.captureStream(30); // 30 FPS
        const options = { mimeType: 'video/webm;codecs=vp9,opus' };
        let finalMime = 'video/webm';
        if (MediaRecorder.isTypeSupported('video/webm;codecs=vp9')) {
            finalMime = 'video/webm;codecs=vp9';
        } else if (MediaRecorder.isTypeSupported('video/webm')) {
            finalMime = 'video/webm';
        }

        recordedVideoChunks = [];
        try {
            mediaRecorderInstance = new MediaRecorder(stream, { mimeType: finalMime });
        } catch(e) {
            mediaRecorderInstance = new MediaRecorder(stream);
        }

        mediaRecorderInstance.ondataavailable = (e) => {
            if (e.data && e.data.size > 0) {
                recordedVideoChunks.push(e.data);
            }
        };

        mediaRecorderInstance.onstop = () => {
            const blob = new Blob(recordedVideoChunks, { type: 'video/webm' });
            const url = URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'PakUtilityHub_Official_App_Video.webm';
            document.body.appendChild(a);
            a.click();
            setTimeout(() => {
                document.body.removeChild(a);
                window.URL.revokeObjectURL(url);
            }, 100);

            if (btn) btn.disabled = false;
            if (badge) badge.classList.remove('active');
            playSfx('success');
            showPushToast('Video Downloaded! 📥', 'PakUtilityHub_Official_App_Video.webm has been saved to your device.', '✅', 'system');
        };

        mediaRecorderInstance.start();

        // High-speed render loop to record full 54 seconds
        const recordInterval = setInterval(() => {
            if (!mediaRecorderInstance || mediaRecorderInstance.state !== 'recording') {
                clearInterval(recordInterval);
                return;
            }
            const pct = Math.min(100, Math.floor((videoCurrentTime / TOTAL_VIDEO_DURATION) * 100));
            if (recText) recText.textContent = `REC VIDEO (${pct}%)`;

            if (videoCurrentTime >= TOTAL_VIDEO_DURATION) {
                clearInterval(recordInterval);
                mediaRecorderInstance.stop();
                isVideoPlaying = false;
            }
        }, 300);

        requestAnimationFrame(videoTourLoop);
    }
"""

# Enhance switchTab to also handle audio SFX and Urdu Voice Auto-Guide
old_switch_tab = """    function switchTab(tabKey) {
        document.querySelectorAll('.tab-btn').forEach(btn => {
            btn.classList.toggle('active', btn.getAttribute('data-tab') === tabKey);
        });
        document.querySelectorAll('.screen-view').forEach(view => {
            view.classList.toggle('active', view.id === `view-${tabKey}`);
        });
        document.querySelectorAll('.b-nav-item').forEach(b => {
            b.classList.toggle('active', b.getAttribute('onclick')?.includes(tabKey));
        });
        window.scrollTo({ top: 0, behavior: 'smooth' });

        if (tabKey === 'trends') {
            setTimeout(renderMainRecharts, 50);
        }
    }"""

new_switch_tab = """    function switchTab(tabKey) {
        playSfx('tab');
        document.querySelectorAll('.tab-btn').forEach(btn => {
            btn.classList.toggle('active', btn.getAttribute('data-tab') === tabKey);
        });
        document.querySelectorAll('.screen-view').forEach(view => {
            view.classList.toggle('active', view.id === `view-${tabKey}`);
        });
        document.querySelectorAll('.b-nav-item').forEach(b => {
            b.classList.toggle('active', b.getAttribute('onclick')?.includes(tabKey));
        });
        window.scrollTo({ top: 0, behavior: 'smooth' });

        if (tabKey === 'trends') {
            setTimeout(renderMainRecharts, 50);
        }

        // Auto Urdu Audio Narration on Tab Switch if enabled
        if (isUrduVoiceMasterActive && tabKey !== 'video') {
            setTimeout(() => {
                const script = URDU_FUNCTION_SCRIPTS[tabKey];
                if (script) speakUrdu(script);
            }, 350);
        }
    }"""

if old_switch_tab in html:
    html = html.replace(old_switch_tab, new_switch_tab)
    print("switchTab replaced with audio support.")
else:
    print("WARNING: switchTab exact match not found, looking for alternative.")

# Insert Video Tour JS before window.onload or DOMContentLoaded
dom_load_target = "window.addEventListener('DOMContentLoaded', () => {"
if dom_load_target in html:
    html = html.replace(dom_load_target, video_js + '\n    ' + dom_load_target + '\n        initVideoTourCanvas();')
    print("Step 7: Video Tour JS and Canvas Init injected.")

# ---------------------------------------------------------------------------
# 8. WRITE TO ALL DESTINATIONS (index.html, docs/index.html, app/applet/index.html)
# ---------------------------------------------------------------------------
destinations = [
    './index.html',
    './docs/index.html',
    './app/applet/index.html'
]

for dest in destinations:
    dir_name = os.path.dirname(dest)
    if dir_name and not os.path.exists(dir_name):
        os.makedirs(dir_name, exist_ok=True)
    with open(dest, 'w', encoding='utf-8') as f:
        f.write(html)
    print(f"Successfully updated: {dest} ({len(html)} bytes)")

print("All files updated successfully!")
