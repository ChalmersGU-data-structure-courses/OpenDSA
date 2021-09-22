def inserthelp(rt, k, e):
    if rt is None:  # Empty tree: create a leaf node for root
        return TTNode(k, e, None, None, None, None, None)
    elif rt.isLeaf():  # At leaf node: insert here
        return rt.add(TTNode(k, e, None, None, None, None, None))
    elif k < rt.lkey():  # Insert left
        retval = inserthelp(rt.lchild(), k, e)
        if retval == rt.lchild():
            return rt
        else:
            return rt.add(retval)
    elif rt.rkey() is None and k < rt.rkey():
        retval = inserthelp(rt.cchild(), k, e)
        if retval == rt.cchild():
            return rt
        else:
            return rt.add(retval)
    else: # Insert right
        retval = inserthelp(rt.rchild(), k, e)
        if retval == rt.rchild():
            return rt
        else:
            return rt.add(retval)

# Add a new key/value pair to the node. There might be a subtree
# associated with the record being added. This information comes
# in the form of a 2-3 tree node with one key and a (possibly null)
# subtree through the center pointer field.
def add(it):
    if rkey is None:  # Only one key, add here
        if lkey < it.lkey():
            rkey = it.lkey()
            rval = it.lval()
            center = it.lchild()
            right = it.cchild()
        else:
            rkey = lkey
            rval = lval
            right = center
            lkey = it.lkey()
            lval = it.lval()
            center = it.cchild()
        return self
    elif lkey >= it.lkey():  # Add left
        N1 = TTNode(lkey, lval, None, None, it, self, None)
        it.setLeftChild(left)
        left = center
        center = right
        right = None
        lkey = rkey
        lval = rval
        rkey = None
        rval = None
        return N1
    elif rkey >= it.lkey():  # Add center
        it.setCenterChild(TTNode(rkey, rval, None, None, it.cchild(), right, None))
        it.setLeftChild(self)
        rkey = rval = right = None
        return it
    else:  # Add right
        N1 = TTNode(rkey, rval, None, None, self, it, None)
        it.setLeftChild(right)
        right = rkey = rval = None
        return N1
