
from LinkedStack import LinkedStack


#/* *** ODSATag: TOH *** */
# Compute the moves to solve a Tower of Hanoi puzzle.
# Function move does (or prints) the actual move of a disk
# from one pole to another.
#  - n: The number of disks
#  - start: The start pole
#  - goal: The goal pole
#  - temp: The other pole
#/* *** ODSATag: TOHshort *** */
def TOH_recursive(n, start, goal, temp):
    if n == 0:                              # Base case
        return
    TOH_recursive(n-1, start, temp, goal)  # Recursive call: n-1 rings
    move(start, goal)                      # Move bottom disk to goal
    TOH_recursive(n-1, temp, goal, start)  # Recursive call: n-1 rings
#/* *** ODSAendTag: TOHshort *** */
#/* *** ODSAendTag: TOH *** */


MOVE = 13
TOH = 15

#/* *** ODSATag: TOHstack *** */
def TOH_stack(n, start, goal, temp):
    S = LinkedStack()
    S.push(TOH_object(TOH, n, start, goal, temp))
    while S.size() > 0:
        it = S.pop()         # Get next task
        if it.op == MOVE:    # Do a move
            move(it.start, it.goal)
        elif it.num > 0:     # Imitate TOH recursive solution (in reverse)
            S.push(TOH_object(TOH, it.num-1, it.temp, it.goal, it.start));
            S.push(TOH_object(MOVE, 0, it.start, it.goal));   # A move to do
            S.push(TOH_object(TOH, it.num-1, it.start, it.temp, it.goal));

class TOH_object:
    def __init__(self, o, n, s, g, t=None):
        self.op = o
        self.num = n
        self.start = s
        self.goal = g
        self.temp = t
#/* *** ODSAendTag: TOHstack *** */

counter = None

def move(start, goal):
    global counter
    print(f"{counter}: Move {start} to {goal}")
    counter += 1


if __name__ == '__main__':
    import sys
    n = 4
    if len(sys.argv) == 2:
        n = int(sys.argv[1])
    start, goal, temp = 1, 2, 3

    print("Recursive solution")
    counter = 1
    TOH_recursive(n, start, goal, temp)
    print()
    print("Stack solution")
    counter = 1
    TOH_stack(n, start, goal, temp)
