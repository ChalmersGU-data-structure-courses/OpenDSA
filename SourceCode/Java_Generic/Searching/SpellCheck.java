
import java.util.Set;
import java.util.HashSet;

/* *** ODSATag: SpellCheck *** */
class SpellChecker {
    Set<String> set_of_valid_words;

    SpellChecker(String[] list_of_valid_words) {
        // Convert the list of words into a set.
        set_of_valid_words = new HashSet<>();
        for (String word : list_of_valid_words)
            set_of_valid_words.add(word);
    }

    public boolean is_valid_word(String word) {
        return set_of_valid_words.contains(word);
    }

/* *** ODSAendTag: SpellCheck *** */
}

class SpellCheck {
/* *** ODSATag: SpellCheck *** */
    public static void main(String args[]) {
        // Create a new spell checker.
        SpellChecker checker = new SpellChecker(new String[]{"cat", "dog"});

        // Select some words to spell check. 
        // If there are no command-line arguments, default to testing "dog" and "kat".
        String[] list_of_words_to_check = args.length > 0 ? args : new String[]{"dog", "kat"};  

        // Now we can spell-check a word easily.
        for (String word : list_of_words_to_check) {
            if (checker.is_valid_word(word)) {
                System.out.println(word + " is valid");
            } else {
                System.out.println(word + " is INVALID");
            }
        }
    }
}
/* *** ODSAendTag: SpellCheck *** */
