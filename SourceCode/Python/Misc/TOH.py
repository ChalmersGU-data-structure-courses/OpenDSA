
#/* *** ODSATag: TOH *** */
# Compute the moves to solve a Tower of Hanoi puzzle.
# Function move does (or prints) the actual move of a disk
# from one pole to another.
# n: The number of disks
# start: The start pole
# goal: The goal pole
# temp: The other pole
#/* *** ODSATag: TOHshort *** */
def TOHr(n, start, goal, temp):
    if n == 0: return             # Base case
    TOHr(n-1, start, temp, goal)  # Recursive call: n-1 rings
    move(start, goal)             # Move bottom disk to goal
    TOHr(n-1, temp, goal, start)  # Recursive call: n-1 rings
#/* *** ODSAendTag: TOHshort *** */
#/* *** ODSAendTag: TOH *** */

#/* *** ODSATag: TOHstack *** */
class TOHobj:
    def __init__(self, o, n, s, g, t=None):
        self.op = o
        self.num = n
        self.start = s
        self.goal = g
        self.temp = t

def TOHs(n, start, goal, temp):
    # Make a stack just big enough
    S = AStack(2*n+1)
    S.push(TOHobj(TOH, n, start, goal, temp))
    while S.length() > 0:
        TOHobj it = S.pop()  # Get next task
        if it.op == MOVE:    # Do a move
            move(it.start, it.goal)
        elif it.num > 0:     # Imitate TOH recursive solution (in reverse)
            S.push(TOHobj(TOH, it.num-1, it.temp, it.goal, it.start));
            S.push(TOHobj(MOVE, 0, it.start, it.goal));   # A move to do
            S.push(TOHobj(TOH, it.num-1, it.start, it.temp, it.goal));
#/* *** ODSAendTag: TOHstack *** */
