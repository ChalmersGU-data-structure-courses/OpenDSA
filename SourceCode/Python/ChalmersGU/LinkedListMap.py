
from API import Map

class LinkedListMap(Map):
    def __init__(self):
        self._head = None    # Pointer to list header
        self._listSize = 0   # Size of list

    def put(self, key, value):
        node = self._head
        while node is not None:
            if node.key == key:
                oldValue = node.value
                node.value = value
                return oldValue
            node = node.next
        self._head = KVNode(key, value, self._head)
        self._listSize += 1
        return None

    def get(self, key):
        node = self._head
        while node is not None:
            if node.key == key:
                return node.value
            node = node.next
        return None

    def remove(self, key):
        prev = None
        node = self._head
        while node is not None:
            if node.key == key:
                if prev is None:
                    self._head = node.next
                else:
                    prev.next = node.next
                node.next = None   # For garbage collection
                self._listSize -= 1
                return node.value
            prev = node;
            node = node.next
        return None

    def containsKey(self, key):
        node = self._head
        while node is not None:
            if node.key == key:
                return True
            node = node.next
        return False

    def isEmpty(self):
        return self._listSize == 0

    def size(self):
        return self._listSize

    def __iter__(self):
        current = self._head
        while current is not None:
            yield current.key
            current = current.next


# Python does not have internal classes, so we have to make the list node standalone.
class KVNode:
    def __init__(self, key, value, next):
        self.key = key       # Key for this node
        self.value = value   # Value for this node
        self.next = next     # Pointer to next node in list


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printMap(m):
    print("[", " ".join(f"{k}:{m.get(k)}" for k in m), "]", m.size())

if __name__ == '__main__':
    m = LinkedListMap()
    _printMap(m)
    print("Putting values")
    for i in range(40):
        m.put(chr((i%25)+65), i)
        if i % 5 == 0: _printMap(m)
    _printMap(m)
    print("Removing values")
    i = 7
    while not m.isEmpty():
        m.remove(chr((i%25)+65))
        i += 11
        if m.size() % 5 == 0: _printMap(m)
