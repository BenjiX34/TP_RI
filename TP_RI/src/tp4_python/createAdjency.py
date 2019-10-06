

def parseAdjencyFile(file="AdjacencyMatrix.100.txt"):
    """

        :param file: fichier qui contient les data 
    """
    pairs = []
    noeuds = []
    with open(file, 'r') as f:
        pairs = list(map(lambda x: list(map(int, x.strip().split())), f.readlines())) # 
    temp = list(zip(*pairs))
    noeuds = list(set(temp[0] + temp[1])) # on récupère la liste de tout les noeuds
    return pairs, noeuds

pairs, noeuds = parseAdjencyFile()
nbr_noeuds = len(noeuds)
print(pairs)


indice_vers_noeud = {x: noeuds[x] for x in range(nbr_noeuds)}
noeud_vers_indice = {noeuds[x]: x for x in range(nbr_noeuds)}

M = [[0 for _ in range(nbr_noeuds)] for _ in range(nbr_noeuds)] # On construit la matrice d'adjacence ave des 0


for a, b in pairs:
    M[noeud_vers_indice[a]][noeud_vers_indice[b]] = 1 # On met des 1 en M[i][j] pour toutes les pairs de noeuds


with open('out.txt', 'w') as f:
    for e in M:
        f.write("{")
        f.write(', '.join(list(map(str, e))) + "},") # on écrit dans un fichier texte le résultat au format Java

print(indice_vers_noeud)
print(noeud_vers_indice)