
class Person:
    def __init__(self, first, last):
        self.firstName = first
        self.lastName = last

    def __hash__(self):
        # Note: The function ``hash(x)`` calls ``x.__hash__()``
        return hash(self.firstName) + hash(self.lastName)

    def __str__(self):
        return self.firstName + " " + self.lastName

if __name__ == '__main__':
    import sys
    someone = Person(sys.argv[1], sys.argv[2])
    print(f"The hash code for {someone} is {hash(someone)}")

    def hashInt(x):
        return x % 16

    def hashString(s, M):
        sum = 0
        for c in s:
            sum += ord(c)
        return sum % M

    def hashStringImproved(s, M):
        sum = 0
        for c in s:
            sum = 31 * sum + ord(c)
        return sum % M
