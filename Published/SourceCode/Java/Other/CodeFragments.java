
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
Iterator<E> it = L.iterator();
while (it.hasNext()) {
    E elem = it.next();
    doSomething(elem);
}
}

static <E> void listIter2(List<E> L) {
for (E elem : L) {
    doSomething(elem);
}
}

// Return true if k is in list L, false otherwise.
static <E> boolean find(List<E> L, E k) {
    for (E n : L) {
        if (k.equals(n))
            return true;  // Found k
    }
    return false;         // k not found
}

public static void main(String[] args) {
    System.out.println("I don't know what to do...");
}

}
