
// Tester for Towers of Hanoi code
public class TowersOfHanoi {
    static int counter;

    // Fake an "enum" -- a Processing deficiency
    static final int MOVE = 13;
    static final int TOH = 15;

    static class Pole {
        int poleNum;

        Pole(int value) {
            poleNum = value;
        }

        // Override Object.toString
        public String toString() {
            return Integer.toString(poleNum);
        }
    }

    static void move(Pole start, Pole goal) {
        System.out.println(counter + ": Move " + start + " to " + goal);
        counter++;
    }

/* *** ODSATag: TOH *** */
// Compute the moves to solve a Tower of Hanoi puzzle.
// Function move does (or prints) the actual move of a disk
// from one pole to another.
//  - n: The number of disks
//  - start: The start pole
//  - goal: The goal pole
//  - temp: The other pole
/* *** ODSATag: TOHshort *** */
static void TOH_recursive(int n, Pole start, Pole goal, Pole temp) {
    if (n == 0)                              // Base case
        return;
    TOH_recursive(n-1, start, temp, goal);   // Recursive call: n-1 rings
    move(start, goal);                       // Move bottom disk to goal
    TOH_recursive(n-1, temp, goal, start);   // Recursive call: n-1 rings
}
/* *** ODSAendTag: TOHshort *** */
/* *** ODSAendTag: TOH *** */

/* *** ODSATag: TOHstack *** */
static void TOH_stack(int n, Pole start, Pole goal, Pole temp) {
    // Make a stack just big enough
    Stack<TOH_object> S = new LinkedStack<>();
    S.push(new TOH_object(TOH, n, start, goal, temp));
    while (S.size() > 0) {
        TOH_object it = (TOH_object) S.pop();  // Get next task
        if (it.op == MOVE) {
            move(it.start, it.goal);       // Do a move
        }
        else if (it.num > 0) {         // Imitate TOH recursive solution (in reverse)
            S.push(new TOH_object(TOH, it.num-1, it.temp, it.goal, it.start));
            S.push(new TOH_object(MOVE, it.start, it.goal));  // A move to do
            S.push(new TOH_object(TOH, it.num-1, it.start, it.temp, it.goal));
        }
    }
}

static class TOH_object {
    int op;
    int num;
    Pole start, goal, temp;
    // Recursive call operation
    TOH_object(int o, int n, Pole s, Pole g, Pole t) {
        op = o; num = n; start = s; goal = g; temp = t;
    }
    // MOVE operation
    TOH_object(int o, Pole s, Pole g) {
        op = o; start = s; goal = g;
    }
}
/* *** ODSAendTag: TOHstack *** */

    public static void main(String args[]) {
        int n = 4;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        }
        Pole start = new Pole(1);
        Pole goal = new Pole(2);
        Pole temp = new Pole(3);

        System.out.println("Recursive solution");
        counter = 1;
        TOH_recursive(n, start, goal, temp);
        System.out.println();
        System.out.println("Stack solution");
        counter = 1;
        TOH_stack(n, start, goal, temp);
    }
}
