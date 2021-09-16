
#/* *** ODSATag: listiter *** */
L.moveToStart()
while not L.isAtEnd():
  it = L.getValue()
  doSomething(it)
  L.next()
#/* *** ODSAendTag: listiter *** */


#/* *** ODSATag: listfind *** */
# Return true if k is in list L, false otherwise
def find(L, k):
  L.moveToStart()
  while not L.isAtEnd():
    if k == L.getValue():
      return true    # Found k
    L.next()
  return false       # k not found
#/* *** ODSAendTag: listfind *** */
