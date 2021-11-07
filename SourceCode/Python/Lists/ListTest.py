
#/* *** ODSATag: listiter *** */
L.moveToStart()
while not L.isAtEnd():
  it = L.getValue()
  doSomething(it)
  L.next()
#/* *** ODSAendTag: listiter *** */


#/* *** ODSATag: listiterNew1 *** */
it = L.__iter__()
while True:
    try:
        elem = it.__next__()
        doSomething(elem)
    except StopIteration:
        break
#/* *** ODSAendTag: listiterNew1 *** */


#/* *** ODSATag: listiterNew2 *** */
for elem in L:
    doSomething(elem)
#/* *** ODSAendTag: listiterNew2 *** */


#/* *** ODSATag: listfind *** */
def find(L, k):
    """Return true if k is in list L, false otherwise."""
    for n in L:
        if k == n:
            return true  # Found k
    return false         # k not found
#/* *** ODSAendTag: listfind *** */
