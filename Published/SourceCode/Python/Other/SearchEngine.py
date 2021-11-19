
from SpellChecker import Set
from PersonDatabase import Map

# We model a document as a list of words.
class Document:
    def __init__(self, contents):
        self.contents = contents

    def __str__(self):
        return str(self.contents)
    def __repr__(self):
        return "Document(" + repr(self.contents) + ")"

class SearchEngine:
    def __init__(self):
        self.database = Map()

    def add(self, doc):
        """Add a new document to the database."""
        for word in doc.contents:
            # Get the set of documents containing this word.
            set = self.database.get(word)
            if set is None:
                # This is the first document containing this word.
                set = Set()

            # Add the document to the set.
            set.add(doc)
            self.database.put(word, set)

    def find(self, word):
        """Find all documents containing a given word."""
        if self.database.containsKey(word):
            return self.database.get(word)
        else:
            # If the word is not found, return an empty set rather than None.
            return Set()


if __name__ == '__main__':
    doc1 = Document("a b c d".split())
    doc2 = Document("c d e f".split())
    se = SearchEngine()
    se.add(doc1)
    se.add(doc2)
    print("b", se.find("b"))
    print("d", se.find("d"))
    print("x", se.find("x"))
