
import sys

class Set:
    def __init__(self):
        self._set = set()
    def add(self, x):
        self._set.add(x)
    def remove(self, x):
        self._set.remove(x)
    def contains(self, x):
        return x in self._set
    def __iter__(self):
        return iter(self._set)
    def __str__(self):
        return str(self._set)


class SpellChecker:
    def __init__(self, list_of_valid_words):
        # Convert the list of words into a set.
        self.set_of_valid_words = Set()
        for word in list_of_valid_words:
            self.set_of_valid_words.add(word)

    def is_valid_word(self, word):
        return self.set_of_valid_words.contains(word)

if __name__ == '__main__':
    # Create a new spell checker.
    checker = SpellChecker(["cat", "dog"])

    # Select some words to spell check. 
    # If there are no command-line arguments, default to testing "dog" and "kat".
    list_of_words_to_check = sys.argv[1:] if len(sys.argv) > 1 else ["dog", "kat"]

    # Now we can spell-check a word easily.
    for word in list_of_words_to_check:
        if checker.is_valid_word(word):
            print(word, "is valid")
        else:
            print(word, "is INVALID")

