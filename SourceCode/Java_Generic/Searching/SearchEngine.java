
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/* *** ODSATag: SearchEngine *** */
// We model a document as a list of words.
class Document {
    public String[] contents;
    Document(String[] contents) {
        this.contents = contents;
    }
/* *** ODSAendTag: SearchEngine *** */

    public String toString() {
        return "Document" + Arrays.toString(contents);
    }
/* *** ODSATag: SearchEngine *** */
}

class SearchEngine {
    Map<String, Set<Document>> database;
    SearchEngine() {
        database = new HashMap<>();
    }

    // Add a new document to the database.
    public void add(Document doc) {
        for (String word : doc.contents) {
            // Get the set of documents containing this word.
            Set<Document> set = database.get(word);
            if (set == null) 
                // This is the first document containing this word.
                set = new HashSet<>();

            // Add the document to the set.
            set.add(doc);
            database.put(word, set);
        }
    }

    // Find all documents containing a given word.
    public Set<Document> find(String word) {
        if (database.containsKey(word))
            return database.get(word);
        else
            // If the word is not found, return an empty set rather than null.
            return new HashSet<>();
    }
/* *** ODSAendTag: SearchEngine *** */

public static void main(String args[]) {
    SearchEngine se = new SearchEngine();
    Document doc1 = new Document(new String[]{"a","b","c","d"});
    Document doc2 = new Document(new String[]{"c","d","e","f"});
    se.add(doc1);
    se.add(doc2);
    System.out.println("b " +  se.find("b"));
    System.out.println("d " +  se.find("d"));
    System.out.println("x " +  se.find("x"));
}
/* *** ODSATag: SearchEngine *** */
}
/* *** ODSAendTag: SearchEngine *** */
