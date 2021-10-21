
# Note: by implementing these methods,
# one can use the standard Python comparison operators (==, !=, <, <=, >, >=).
class Comparable:
    def __eq__(self, other): """Test if self == other."""
    def __ne__(self, other): """Test if self != other."""
    def __lt__(self, other): """Test if self < other."""
    def __le__(self, other): """Test if self <= other."""
    def __gt__(self, other): """Test if self > other."""
    def __ge__(self, other): """Test if self >= other."""


# Python 3 has nothing similar to the Java comparator interface.
#
# Instead Python 3 advocates to use a *key extractor*, which is a function
# that converts an element to another element which then can be compared
# using the natural ordering.
#
# The `functools` library has a function `cmp_to_key` which can
# convert a comparator-like function to a key extractor.


# Note: by implementing these methods,
# one can loop over the elements in a standard Python for-loop.
class Iterator:
    def __iter__(self): """Returns the iterator itself."""
    def __next__(self): """Returns the next item. Raises StopIteration if there are no more elements."""

class Iterable:
    def __iter__(self): """Returns a new iterator."""

