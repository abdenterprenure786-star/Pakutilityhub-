# -*- coding: utf-8 -*-
"""
Refine SFX integration, speed test voice celebration, bill calculation chime,
and video tab embedding across index.html, docs/index.html, and app/applet/index.html.
"""
import os

with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# 1. Enhance switchTab with tabKey === 'video' handling
old_switch_tab_block = """        if (tabKey === 'trends') {
            setTimeout(renderMainRecharts, 50);
        }

        // Auto Urdu Audio Narration on Tab Switch if enabled"""

new_switch_tab_block = """        if (tabKey === 'video') {
            const videoSec = document.getElementById('appVideoShowcaseSection');
            const target = document.getElementById('videoEmbedTarget');
            if (videoSec && target && !target.contains(videoSec)) {
                target.appendChild(videoSec);
            }
        } else {
            const videoSec = document.getElementById('appVideoShowcaseSection');
            const origParent = document.getElementById('view-trends');
            const rechartsCard = document.getElementById('recharts-main-dashboard-card');
            if (videoSec && origParent && rechartsCard && !origParent.contains(videoSec)) {
                origParent.insertBefore(videoSec, rechartsCard);
            }
        }

        if (tabKey === 'trends') {
            setTimeout(renderMainRecharts, 50);
        }

        // Auto Urdu Audio Narration on Tab Switch if enabled"""

if old_switch_tab_block in text:
    text = text.replace(old_switch_tab_block, new_switch_tab_block)
    print("switchTab enhanced with video section docking.")

# 2. Add SFX to speed test start and finish
old_speed_start = """        isSpeedRunning = true;
        document.getElementById('speedBtnText').textContent = "STOP SPEED TEST";"""

new_speed_start = """        isSpeedRunning = true;
        playSfx('speed_rev');
        if (isUrduVoiceMasterActive) speakUrdu("انٹرنیٹ سپیڈ ٹیسٹ شروع ہو رہا ہے۔");
        document.getElementById('speedBtnText').textContent = "STOP SPEED TEST";"""

if old_speed_start in text:
    text = text.replace(old_speed_start, new_speed_start)
    print("Speed test start SFX & voice added.")

old_speed_finish = """            if (remaining <= 0) {
                clearInterval(speedTimer);
                isSpeedRunning = false;
                document.getElementById('speedBtnText').textContent = "RE-TEST SPEED";
                document.getElementById('speedTimerCountdown').textContent = "🎉 1-Minute Speed Test Finished!";
            }"""

new_speed_finish = """            if (remaining <= 0) {
                clearInterval(speedTimer);
                isSpeedRunning = false;
                document.getElementById('speedBtnText').textContent = "RE-TEST SPEED";
                document.getElementById('speedTimerCountdown').textContent = "🎉 1-Minute Speed Test Finished!";
                playSfx('success');
                if (isUrduVoiceMasterActive) {
                    speakUrdu("انٹرنیٹ سپیڈ ٹیسٹ مکمل ہو گیا ہے۔ آپ کی ڈاؤن لوڈ سپیڈ " + liveMBps.toFixed(2) + " میگا بائٹس فی سیکنڈ ہے۔");
                }
            }"""

if old_speed_finish in text:
    text = text.replace(old_speed_finish, new_speed_finish)
    print("Speed test finish SFX & voice added.")

# 3. Add SFX to calculateElectricityBill
old_bill_calc = """    function calculateElectricityBill() {
        const unitsInput = document.getElementById('billUnits');"""

new_bill_calc = """    function calculateElectricityBill() {
        playSfx('kaching');
        const unitsInput = document.getElementById('billUnits');"""

if old_bill_calc in text:
    text = text.replace(old_bill_calc, new_bill_calc)
    print("calculateElectricityBill SFX added.")

# 4. Add SFX to calculateGoldTrade
old_gold_calc = """    function calculateGoldTrade() {
        const tolaInput = document.getElementById('tradeTolaInput');"""

new_gold_calc = """    function calculateGoldTrade() {
        playSfx('kaching');
        const tolaInput = document.getElementById('tradeTolaInput');"""

if old_gold_calc in text:
    text = text.replace(old_gold_calc, new_gold_calc)
    print("calculateGoldTrade SFX added.")

# 5. Add SFX to calculateTripCost
old_trip_calc = """    function calculateTripCost() {
        const km = parseFloat(document.getElementById('tripKm')?.value) || 0;"""

new_trip_calc = """    function calculateTripCost() {
        playSfx('calc');
        const km = parseFloat(document.getElementById('tripKm')?.value) || 0;"""

if old_trip_calc in text:
    text = text.replace(old_trip_calc, new_trip_calc)
    print("calculateTripCost SFX added.")

# 6. Add SFX to calculateConstructionCost
old_const_calc = """    function calculateConstructionCost() {
        const areaSqft = parseFloat(document.getElementById('constArea')?.value) || 2250;"""

new_const_calc = """    function calculateConstructionCost() {
        playSfx('calc');
        const areaSqft = parseFloat(document.getElementById('constArea')?.value) || 2250;"""

if old_const_calc in text:
    text = text.replace(old_const_calc, new_const_calc)
    print("calculateConstructionCost SFX added.")

# 7. Add SFX to calculateFbrTax
old_tax_calc = """    function calculateFbrTax(type) {
        let monthly = 0;"""

new_tax_calc = """    function calculateFbrTax(type) {
        playSfx('kaching');
        let monthly = 0;"""

if old_tax_calc in text:
    text = text.replace(old_tax_calc, new_tax_calc)
    print("calculateFbrTax SFX added.")

# Write to all 3 deployment destinations
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
        f.write(text)
    print(f"Refined and written: {dest} ({len(text)} bytes)")

print("All refinements complete.")
