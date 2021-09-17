def findhelp(root, k):
    if root is None:
        return None   # val not found
    elif k == root.lkey():
        return root.lval()
    elif root.rkey() is not None and k == root.rkey():
        return root.rval()
    elif k < root.lkey():
        return findhelp(root.lchild(), k)  # Search left
    elif root.rkey() is None:
        return findhelp(root.cchild(), k)  # Search center
    elif k < root.rkey()
        return findhelp(root.cchild(), k)  # Search center
    else:
        return findhelp(root.rchild(), k)  # Search right
