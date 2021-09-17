
#/* *** ODSATag: EffCnt *** */
def count(root):
    if root is None:
        return 0   # Nothing to count
    return 1 + count(root.left()) + count(root.right())
#/* *** ODSAendTag: EffCnt *** */


#/* *** ODSATag: IneffCnt *** */
def ineff_count(root):
    if root is None:
        return 0   # Nothing to count
    count = 0
    if root.left() is not None:
        count = 1 + ineff_count(root.left())
    if root.right() is not None:
        count = 1 + ineff_count(root.right())
    if root.left() is None and root.right() is None:
        return 1
    return 1 + count
#/* *** ODSAendTag: IneffCnt *** */ 


#/* *** ODSATag: IneffbtInc *** */
def ineff_BTinc(root);
    if root is not None:
        root.setValue(root.value() + 1)
        if root.left() is not None:
            root.left().setValue(root.left().value() + 1)
            ineff_BTinc(root.left().left())
        if root.right() is not None:
            root.right().setValue(root.right().value() + 1)
            ineff_BTinc(root.right().right())
#/* *** ODSAendTag: IneffbtInc *** */

#/* *** ODSATag: bad_count *** */
def bad_count(root):
    if root is None:
        return 0   # Nothing to count
    bad_count(root.left())
    1 + bad_count(root.left()) + bad_count(root.right())
#/* *** ODSAendTag: bad_count *** */
