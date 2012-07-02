import json
import os
import re

dir = "C:\\Users\\Kevin\\Documents\\GitHub\\core\\"

# find image files
paths = []
for root, directory, files in os.walk(dir):
    if '.git' in root:
        continue
    for file in files:
        match = re.search('[.]rng$', file)
        if match:
            paths.append(os.path.join(root,file))
print paths

f = open("dump.json", "w")
json.dump(paths, f)
f.close()


