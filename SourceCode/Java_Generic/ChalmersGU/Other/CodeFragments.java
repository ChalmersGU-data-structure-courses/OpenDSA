
// This file contains small code fragments that are used in the book.
// Most of them don't do anything interesting...

import java.util.Iterator;

class CodeFragments {
// Allows doSomething to produce result without arguments so it can be tested.
static String doSomethingResult;

static void doSomething(Object it) {
    doSomethingResult += it;
}

    
static <E> void listIter1(List<E> L) {
/* *** ODSATag: ListIter1 *** */
Iterator<E> it = L.iterator();
while (it.hasNext()) {
    E elem = it.next();
    doSomething(elem);
}
/* *** ODSAendTag: ListIter1 *** */
}

static <E> void listIter2(List<E> L) {
/* *** ODSATag: ListIter2 *** */
for (E elem : L) {
    doSomething(elem);
}
/* *** ODSAendTag: ListIter2 *** */
}

/* *** ODSATag: ListFind *** */
// Return true if k is in list L, false otherwise.
static <E> boolean find(List<E> L, E k) {
    for (E n : L) {
        if (k.equals(n))
            return true;  // Found k
    }
    return false;         // k not found
}
/* *** ODSAendTag: ListFind *** */

public static void main(String[] args) {
    System.out.println("I don't know what to do...");
}

}
