
/* *** ODSATag: HuffTree *** */
class HuffTree implements Comparable<HuffTree> {
    public char element;
    public int weight;
    public HuffTree left;
    public HuffTree right;

    HuffTree(char el, int wt) {
        element = el; weight = wt;
    }

    HuffTree(HuffTree l, HuffTree r, int wt) {
        left = l; right = r; weight = wt;
    }

    public boolean isLeaf() {
        return left == null || right == null;
    }

    public int compareTo(HuffTree other) {
        return Integer.compare(this.weight, other.weight);
    }
/* *** ODSAendTag: HuffTree *** */

    public Map<Character, String> encodingMap() {
        Map<Character, String> result = new SeparateChainingHashMap<>();
        if (isLeaf()) {
            result.put(element, "");
            return result;
        }

        Map<Character, String> leftEncoding = left.encodingMap();
        for (char ch : leftEncoding)
            result.put(ch, "0" + leftEncoding.get(ch));
        Map<Character, String> rightEncoding = right.encodingMap();
        for (char ch : rightEncoding)
            result.put(ch, "1" + rightEncoding.get(ch));
        return result;
    }

    public String toString() {
        Map<Character, String> enc = encodingMap();
        String s = weight + "\n";
        for (char c : enc) {
            s += "  " + c + ": <" + enc.get(c) + ">\n";
        }
        return s;
    }
/* *** ODSATag: HuffTree *** */
}
/* *** ODSAendTag: HuffTree *** */

class Huffman {
/* *** ODSATag: HuffTreeBuild *** */
public static HuffTree buildHuffTree(Map<Character, Integer> frequencies) {
    MinHeap<HuffTree> huffHeap = new MinHeap<>();
    for (char ch : frequencies) { // Initialise the heap with singleton Huffman trees
        int freq = frequencies.get(ch);
        huffHeap.add(new HuffTree(ch, freq));
    }
    while (huffHeap.size() > 1) { // While at least two trees left on heap
        HuffTree t1 = huffHeap.removeMin();
        HuffTree t2 = huffHeap.removeMin();
        HuffTree t3 = new HuffTree(t1, t2, t1.weight + t2.weight);
        huffHeap.add(t3);         // Return new tree to heap
    }
    return huffHeap.removeMin();  // Return the final Huffman tree
}
/* *** ODSAendTag: HuffTreeBuild *** */

public static void main(String[] args) {
    Map<Character, Integer> freqs = new SeparateChainingHashMap<>();
    freqs.put('a', 5);
    freqs.put('b', 2);
    freqs.put('c', 1);
    freqs.put('d', 1);
    freqs.put('r', 2);
    freqs.put('!', 1);
    HuffTree tree = buildHuffTree(freqs);
    System.out.println(tree);
}
}

