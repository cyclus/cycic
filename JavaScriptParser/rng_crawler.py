import json
import os
import re

#cyclus_dir = "C:\\Users\\Kevin\\Documents\\GitHub\\core\\"
cyclus_dir = "/home/scopatz/cyclus"


# find image files
paths = []
for root, directory, files in os.walk(cyclus_dir):
    if '.git' in root:
        continue
    for file in files:
        match = re.search('[.]rng$', file)
        if match:
            fullpath = os.path.join(root, file)
            relpath = os.path.relpath(fullpath, cyclus_dir)
            paths.append(relpath)
print paths

f = open("rngdump.json", "w")
json.dump(paths, f, indent=2)
f.close()


