with open('build_complete_applet.py', 'r', encoding='utf-8') as f:
    code = f.read()

# Fix missing { before FinancialService
bad_str = """          ]
        },
              "@type": "FinancialService","""

good_str = """          ]
        },
        {
          "@type": "FinancialService","""

if bad_str in code:
    code = code.replace(bad_str, good_str)
else:
    # Also handle flexible spaces
    import re
    code = re.sub(r'\]\s*\}\s*,\s*\"@type\":\s*\"FinancialService\"', ']\n        },\n        {\n          "@type": "FinancialService"', code)

with open('build_complete_applet.py', 'w', encoding='utf-8') as f:
    f.write(code)

print("Fixed FinancialService bracket in build_complete_applet.py")
