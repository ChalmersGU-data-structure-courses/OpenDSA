
#/* *** ODSATag: RFact *** */
# Recursively compute and return n!
def rfact(n):
    if n < 0: raise ValueError
    if n <= 1: return 1    # Base case: return base solution
    return n * rfact(n-1)  # Recursive call for n > 1
#/* *** ODSAendTag: RFact *** */

#/* *** ODSATag: Sfact *** */
# Return n!
def sfact(n):
    if n < 0: raise ValueError
    # Make a stack just big enough
    S = AStack(n)
    while n > 1:
        S.push(n)
        n -= 1
    result = 1
    while S.length() > 0:
        result = result * S.pop()
    return result
#/* *** ODSAendTag: Sfact *** */
