
from LinkedStack import LinkedStack

# Recursively compute and return n!
def factorial_recursive(n):
    if n < 0: raise ValueError("Argument must be >= 0")
    if n <= 1:
        return 1   # Base case: return base solution
    else:
        return n * factorial_recursive(n-1)   # Recursive call for n > 1

# Return n!
def factorial_stack(n):
    if n < 0: raise ValueError("Argument must be >= 0")
    S = LinkedStack()
    while n > 1:
        S.push(n)
        n -= 1
    result = 1
    while S.size() > 0:
        result = result * S.pop()
    return result

if __name__ == '__main__':
    import sys
    n = 10
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    rf = factorial_recursive(n)
    sf = factorial_stack(n)
    print(f"{n}!  ==  {rf}  ==  {sf}");
    if (rf != sf):
        print("Testing failed")
