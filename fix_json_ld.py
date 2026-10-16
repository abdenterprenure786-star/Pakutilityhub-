with open('build_complete_applet.py', 'r', encoding='utf-8') as f:
    code = f.read()

# Fix double opening curly bracket in schema
code = code.replace("        {\n            {\n          \"@type\": \"LocalBusiness\",", "        {\n          \"@type\": \"LocalBusiness\",")
code = code.replace("{\n            {\n          \"@type\": \"LocalBusiness\",", "{\n          \"@type\": \"LocalBusiness\",")

with open('build_complete_applet.py', 'w', encoding='utf-8') as f:
    f.write(code)

print("Fixed JSON-LD syntax in build_complete_applet.py")
