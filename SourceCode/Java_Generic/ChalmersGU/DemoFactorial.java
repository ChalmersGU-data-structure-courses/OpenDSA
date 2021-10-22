
// Tester for factorial code
public class DemoFactorial {

/* *** ODSATag: RFact *** */
// Recursively compute and return n!
static long factorial_recursive(int n) {
    if (0 > n) throw new IllegalArgumentException("Argument must be >= 0");
    if (n > 20) throw new IllegalArgumentException("Cannot handle larger values than 20!");
    if (n <= 1)  {
        return 1;   // Base case: return base solution
    } else {
        return n * factorial_recursive(n-1);   // Recursive call for n > 1
    }
}
/* *** ODSAendTag: RFact *** */

/* *** ODSATag: SFact *** */
// Return n!
static long factorial_stack(int n) {
    if (0 > n) throw new IllegalArgumentException("Argument must be >= 0");
    if (n > 20) throw new IllegalArgumentException("Cannot handle larger values than 20!");
    Stack<Integer> S = new LinkedStack<>();
    while (n > 1) {
        S.push(n);
        n--;
    }
    long result = 1;
    while (S.size() > 0) {
        result = result * S.pop();
    }
    return result;
}
/* *** ODSAendTag: SFact *** */

public static void main(String args[]) {
    int n = 10;
    if (args.length == 1) {
        n = Integer.parseInt(args[0]);
    }
    long rf = factorial_recursive(n);
    long sf = factorial_stack(n);
    System.out.println(n + "!  ==  " + rf + "  ==  " + sf);
    if (rf != sf) {
        System.out.println("Testing failed");
    }
}

}
