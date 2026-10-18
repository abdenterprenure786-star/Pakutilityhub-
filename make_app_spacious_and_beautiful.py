# -*- coding: utf-8 -*-
"""
Script to transform PakUtility Hub into a spacious, luxury, beautiful, and attractive web app
with generous typography, ample line-height, letter-spacing, and breathing room.
"""
import os
import re

with open('index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# ---------------------------------------------------------------------------
# 1. NEW COMPREHENSIVE LUXURY SPATIOUS DESIGN STYLES
# ---------------------------------------------------------------------------
spacious_css = """
        /* =================================================================== */
        /* ✨ SPACIOUS, LUXURIOUS, MODERN & ACCESSIBLE DESIGN SYSTEM ✨       */
        /* Eliminates all cramped spaces, tiny text, and squished layouts      */
        /* =================================================================== */

        /* Root Typography & Sizing Resets */
        body {
            font-family: 'Plus Jakarta Sans', system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif !important;
            font-size: 15px !important;
            line-height: 1.7 !important;
            letter-spacing: 0.3px !important;
            word-spacing: 0.5px !important;
            background-color: var(--bg-base);
            color: var(--text-primary);
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        /* Urdu Nastaliq Professional Calligraphy & Generous Vertical Room */
        .urdu-font,
        .brand-text-sub-urdu,
        .video-hero-subtitle-urdu,
        .narrator-msg-urdu,
        [class*="urdu"] {
            font-family: 'Noto Nastaliq Urdu', -apple-system, serif !important;
            line-height: 2.2 !important;
            letter-spacing: 0.5px !important;
            word-spacing: 3px !important;
            padding: 4px 0 !important;
        }

        /* Spacious Centered Main Content Area */
        .content-area {
            flex: 1 !important;
            padding: 28px 24px 100px !important;
            max-width: 1040px !important; /* Wide luxury container */
            margin: 0 auto !important;
            width: 100% !important;
        }
        @media (max-width: 768px) {
            .content-area {
                padding: 18px 14px 90px !important;
            }
        }

        /* Elevated Spacious Cards with Smooth Lighting */
        .card {
            background: linear-gradient(165deg, rgba(17, 24, 39, 0.94) 0%, rgba(11, 17, 32, 0.98) 100%) !important;
            border: 1.5px solid rgba(255, 255, 255, 0.08) !important;
            border-radius: 20px !important;
            padding: 26px 30px !important;
            margin-bottom: 26px !important;
            box-shadow: 0 14px 40px -8px rgba(0, 0, 0, 0.5), 0 0 24px rgba(16, 185, 129, 0.07) !important;
            position: relative;
            transition: transform 0.25s ease, box-shadow 0.25s ease, border-color 0.25s ease;
        }
        [data-theme="light"] .card {
            background: linear-gradient(165deg, #FFFFFF 0%, #F8FAFC 100%) !important;
            border: 1.5px solid rgba(226, 232, 240, 0.9) !important;
            box-shadow: 0 10px 30px -4px rgba(0, 0, 0, 0.06), 0 0 15px rgba(5, 150, 105, 0.05) !important;
        }
        .card:hover {
            border-color: rgba(245, 158, 11, 0.3) !important;
            box-shadow: 0 18px 48px -6px rgba(0, 0, 0, 0.6), 0 0 30px rgba(16, 185, 129, 0.12) !important;
        }
        @media (max-width: 768px) {
            .card {
                padding: 20px 18px !important;
                border-radius: 16px !important;
                margin-bottom: 20px !important;
            }
        }

        /* Card Header & Title Breathing Space */
        .card-header {
            display: flex !important;
            align-items: center !important;
            justify-content: space-between !important;
            margin-bottom: 22px !important;
            padding-bottom: 14px !important;
            border-bottom: 1px solid rgba(255, 255, 255, 0.07) !important;
            flex-wrap: wrap !important;
            gap: 12px !important;
        }
        [data-theme="light"] .card-header {
            border-bottom: 1px solid rgba(226, 232, 240, 0.9) !important;
        }
        .card-title {
            font-size: 17px !important;
            font-weight: 800 !important;
            letter-spacing: 0.4px !important;
            color: var(--text-primary) !important;
            line-height: 1.4 !important;
        }
        @media (max-width: 768px) {
            .card-title {
                font-size: 15px !important;
            }
        }

        /* Top Navbar Spacious Styling */
        .top-navbar {
            padding: 14px 24px !important;
            border-bottom: 1px solid rgba(255, 255, 255, 0.08) !important;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3) !important;
        }
        .brand-logo-container {
            width: 44px !important;
            height: 44px !important;
            border-radius: 12px !important;
        }
        .brand-text-title-gradient {
            font-size: 17px !important;
            letter-spacing: 0.3px !important;
            gap: 8px !important;
        }
        .hub-pill {
            font-size: 10px !important;
            padding: 2px 7px !important;
            border-radius: 6px !important;
            letter-spacing: 0.8px !important;
        }
        .brand-text-sub-urdu {
            font-size: 12.5px !important;
            line-height: 2.1 !important;
            margin-top: 2px !important;
        }
        .nav-right {
            gap: 8px !important;
        }

        /* Market Ticker Ribbon Spaciousness */
        .market-ticker-ribbon {
            padding: 10px 20px !important;
            font-size: 13px !important;
            letter-spacing: 0.3px !important;
        }
        .ticker-tag-badge {
            font-size: 11px !important;
            padding: 4px 10px !important;
            border-radius: 6px !important;
            margin-right: 14px !important;
        }
        .ticker-track {
            gap: 36px !important;
        }

        /* Horizontal Tab Scroller Spaciousness */
        .tabs-scroller {
            padding: 12px 20px !important;
            gap: 10px !important;
            background: rgba(11, 17, 32, 0.95) !important;
            border-bottom: 1px solid rgba(255, 255, 255, 0.08) !important;
        }
        [data-theme="light"] .tabs-scroller {
            background: #FFFFFF !important;
        }
        .tab-btn {
            padding: 10px 20px !important;
            font-size: 13.5px !important;
            font-weight: 700 !important;
            border-radius: 24px !important;
            gap: 8px !important;
            letter-spacing: 0.3px !important;
            border: 1.5px solid transparent !important;
        }
        .tab-btn:hover {
            border-color: rgba(245, 158, 11, 0.4) !important;
            color: var(--text-primary) !important;
        }
        .tab-btn.active {
            background: linear-gradient(135deg, #F59E0B, #D97706) !important;
            color: #000 !important;
            font-weight: 900 !important;
            box-shadow: 0 4px 14px var(--gold-glow) !important;
        }

        /* Form Inputs & Touch Targets */
        .form-row {
            gap: 16px !important;
            margin-bottom: 16px !important;
        }
        .form-group {
            margin-bottom: 16px !important;
        }
        .form-label {
            font-size: 12.5px !important;
            font-weight: 800 !important;
            letter-spacing: 0.5px !important;
            margin-bottom: 8px !important;
            color: var(--text-secondary) !important;
        }
        .form-control {
            padding: 12px 18px !important;
            font-size: 15px !important;
            border-radius: 12px !important;
            border: 1.5px solid var(--border-subtle) !important;
            height: 48px !important;
            line-height: 1.5 !important;
            background: var(--bg-inner) !important;
            transition: all 0.2s ease !important;
        }
        .form-control:focus {
            border-color: var(--gold) !important;
            box-shadow: 0 0 12px var(--gold-glow) !important;
        }

        /* Chips & Filter Pills */
        .chip-group {
            gap: 10px !important;
            margin-bottom: 18px !important;
        }
        .chip {
            padding: 8px 16px !important;
            font-size: 12.5px !important;
            font-weight: 700 !important;
            letter-spacing: 0.3px !important;
            border-radius: 20px !important;
            border: 1.5px solid var(--border-dim) !important;
            transition: all 0.2s ease !important;
        }
        .chip:hover {
            border-color: var(--gold) !important;
            transform: translateY(-1px) !important;
        }
        .chip.active {
            box-shadow: 0 2px 10px var(--gold-glow) !important;
        }

        /* Badges */
        .badge {
            font-size: 11px !important;
            font-weight: 800 !important;
            padding: 4px 10px !important;
            border-radius: 6px !important;
            letter-spacing: 0.5px !important;
        }

        /* 4-Stat Grid Spacious Overhaul */
        .stat-grid-4 {
            grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)) !important;
            gap: 14px !important;
            margin-top: 18px !important;
        }
        .stat-box {
            padding: 16px 14px !important;
            border-radius: 14px !important;
            border: 1.5px solid var(--border-subtle) !important;
            background: var(--bg-inner) !important;
        }
        .stat-lbl {
            font-size: 11px !important;
            font-weight: 800 !important;
            letter-spacing: 0.6px !important;
            color: var(--text-dim) !important;
        }
        .stat-val {
            font-size: 16px !important;
            font-weight: 900 !important;
            margin-top: 6px !important;
            letter-spacing: 0.3px !important;
        }

        /* Rate Mini Cards */
        .rate-grid-2 {
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)) !important;
            gap: 16px !important;
            margin-bottom: 18px !important;
        }
        .rate-card-mini {
            padding: 18px 20px !important;
            border-radius: 14px !important;
            border: 1.5px solid var(--border-subtle) !important;
        }
        .rate-card-mini .head {
            font-size: 12px !important;
            letter-spacing: 0.4px !important;
        }
        .rate-card-mini .price {
            font-size: 22px !important;
            font-weight: 900 !important;
            margin: 8px 0 4px !important;
            letter-spacing: 0.3px !important;
        }
        .rate-card-mini .sub {
            font-size: 11.5px !important;
            line-height: 1.5 !important;
        }

        /* DISCO Electricity Bill Grid */
        .disco-grid {
            grid-template-columns: repeat(auto-fit, minmax(240px, 1fr)) !important;
            gap: 14px !important;
            margin: 16px 0 !important;
        }
        .disco-badge-btn {
            padding: 14px 18px !important;
            border-radius: 12px !important;
            border: 1.5px solid var(--border-subtle) !important;
        }
        .disco-name {
            font-size: 13.5px !important;
            font-weight: 800 !important;
            letter-spacing: 0.3px !important;
        }
        .disco-region {
            font-size: 11.5px !important;
            margin-top: 3px !important;
        }
        .disco-arrow {
            font-size: 16px !important;
        }

        /* Mandi Products Grid */
        .mandi-grid {
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)) !important;
            gap: 16px !important;
            margin-top: 14px !important;
        }
        .mandi-item-card {
            padding: 18px 20px !important;
            border-radius: 14px !important;
            border: 1.5px solid var(--border-subtle) !important;
        }

        /* Result Boxes */
        .result-box {
            padding: 22px 26px !important;
            border-radius: 16px !important;
            margin-top: 18px !important;
        }
        .result-lbl {
            font-size: 12px !important;
            font-weight: 800 !important;
            letter-spacing: 0.6px !important;
        }
        .result-val {
            font-size: 30px !important;
            font-weight: 900 !important;
            margin: 8px 0 !important;
            letter-spacing: 0.5px !important;
        }
        .result-sub {
            font-size: 12.5px !important;
            line-height: 1.6 !important;
        }

        /* Building Materials Table */
        .materials-table {
            margin-top: 16px !important;
        }
        .materials-table th {
            padding: 12px 16px !important;
            font-size: 12px !important;
            letter-spacing: 0.6px !important;
        }
        .materials-table td {
            padding: 14px 16px !important;
            font-size: 13.5px !important;
            letter-spacing: 0.2px !important;
        }

        /* VIP Tier Cards Grid */
        .tier-select-grid {
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)) !important;
            gap: 14px !important;
            margin-bottom: 16px !important;
        }
        .tier-card {
            padding: 16px 18px !important;
            border-radius: 14px !important;
        }
        .tier-price {
            font-size: 20px !important;
            margin: 4px 0 !important;
        }
        .tier-title {
            font-size: 13px !important;
        }
        .tier-desc {
            font-size: 11px !important;
            line-height: 1.4 !important;
        }

        /* Primary Action Buttons */
        .btn-pro-activate,
        #speedBtn,
        .chart-pill {
            letter-spacing: 0.4px !important;
        }
        #speedBtn {
            padding: 14px 32px !important;
            font-size: 14px !important;
            border-radius: 14px !important;
            margin-top: 16px !important;
        }

        /* Hero Video Showcase Card Polish */
        .video-hero-showcase {
            padding: 24px 28px !important;
            border-radius: 22px !important;
            margin-bottom: 28px !important;
        }
        .video-hero-title {
            font-size: 17px !important;
            letter-spacing: 0.4px !important;
        }
        .video-hero-subtitle-urdu {
            font-size: 13px !important;
            line-height: 2.2 !important;
        }
        .video-controls-bar {
            padding: 14px 18px !important;
            gap: 12px !important;
            border-radius: 14px !important;
        }
        .v-ctrl-btn {
            padding: 8px 16px !important;
            font-size: 12.5px !important;
            border-radius: 8px !important;
        }
        .scene-chip {
            padding: 6px 14px !important;
            font-size: 12px !important;
            border-radius: 8px !important;
        }
        .video-time-text {
            font-size: 13px !important;
        }

        /* In-Card Urdu Voice Listen Button */
        .card-urdu-listen-btn {
            font-size: 11.5px !important;
            padding: 6px 14px !important;
            border-radius: 8px !important;
            gap: 6px !important;
        }

        /* Bottom Fixed Navigation Bar (Mobile) */
        .bottom-nav-bar {
            padding: 8px 12px 12px !important;
            height: 64px !important;
            border-top: 1px solid rgba(255, 255, 255, 0.08) !important;
            background: rgba(11, 17, 32, 0.96) !important;
            backdrop-filter: blur(16px) !important;
        }
        .b-nav-item {
            gap: 3px !important;
        }
        .b-nav-item .icon {
            font-size: 19px !important;
        }
        .b-nav-item span:last-child {
            font-size: 10.5px !important;
            font-weight: 700 !important;
            letter-spacing: 0.3px !important;
        }
        .b-nav-item.active {
            color: var(--gold) !important;
        }
"""

if '/* ✨ SPACIOUS, LUXURIOUS, MODERN & ACCESSIBLE DESIGN SYSTEM ✨ */' not in html:
    html = html.replace('</style>', spacious_css + '\n    </style>')
    print("Spacious CSS injected successfully.")
else:
    # Replace existing block if already present
    pattern = r'/\* =+ \*/\s*/\* ✨ SPACIOUS.+?</style>'
    html = re.sub(pattern, spacious_css + '\n    </style>', html, flags=re.DOTALL)
    print("Spacious CSS updated.")

# ---------------------------------------------------------------------------
# 2. WRITE TO ALL DESTINATIONS (index.html, docs/index.html, app/applet/index.html)
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

print("All destinations updated with spacious & beautiful design!")
