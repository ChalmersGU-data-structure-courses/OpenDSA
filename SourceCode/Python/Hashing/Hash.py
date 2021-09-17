
#/* *** ODSATag: Mod *** */
def h(int x):
    return x % 16
#/* *** ODSAendTag: Mod *** */

#/* *** ODSATag: sascii *** */
def sascii(x, M):
    sum = sum(ord(c) for c in x)
    return sum % M
#/* *** ODSAendTag: sascii *** */

#// This revised sfold implementation was contributed by
#//     Torben Haagh <tbhaagh@gmail.com>.
    
#/* *** ODSATag: sfold *** */
# Use folding on a string, summed 4 bytes at a time
def sfold(s, M):
    sum = 0
    mul = 1
    for i in range(s.length()):
        mul = 1 if i % 4 == 0 else mul * 256
        sum += ord(s[i]) * mul
    return abs(sum) % M
#/* *** ODSAendTag: sfold *** */

#/* *** ODSATag: hashInsert *** */
# Insert e into hash table HT
def hashInsert(k, e):
    int home = h(k)   # Home position for e
    int pos = home    # Init probe sequence
    i = 1
    while EMPTYKEY != HT[pos].key():
        if k == HT[pos].key():
            raise ValueError("Duplicates not allowed")
        pos = (home + p(k, i)) % M  # probe
        HT[pos] = e
#/* *** ODSAendTag: hashInsert *** */

#/* *** ODSATag: hashSearch *** */
# Search for the record with Key K
def hashSearch(K, e):
    home = h(K)   # Home position for K
    pos = home    # Initial position is the home slot
    i = 1
    while K != HT[pos]).key() and EMPTYKEY != HT[pos]).key():
        pos = (home + p(K, i)) % M  # Next on probe sequence
        i += 1
    if K == HT[pos].key()  # Found it
        e = HT[pos]
        return True
    else:
        return False  # K not in hash table
#/* *** ODSAendTag: hashSearch *** */
