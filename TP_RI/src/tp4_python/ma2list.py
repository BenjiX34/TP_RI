from collections import defaultdict

d = defaultdict(list)
maxkey = 0
for line in open("AdjacencyMatrix.tsv"):
    parts = [int(x) for x in line.split()]
    d[parts[0]].append(parts[1])
    maxkey = max([maxkey, parts[0], parts[1]])

print(maxkey+1)
for i in range(maxkey):
    l = d[i]
    l.sort()
    print(" ".join([str(x) for x in l]))
print("")
