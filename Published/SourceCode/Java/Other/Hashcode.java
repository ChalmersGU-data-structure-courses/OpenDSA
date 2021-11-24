

class Person {
    public String firstName;
    public String lastName;

    Person(String first, String last) {
        firstName = first;
        lastName = last;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}


class Hashcode {

    int hashInt(int x) {
        return x % 16;
    }

    int hashString(String s, int M) {
        int sum = 0;
        for (char c : s.toCharArray())
            sum += c;
        return sum % M;
    }
  
    int hashStringImproved(String s, int M) {
        int sum = 0;
        for (char c : s.toCharArray())
            sum = 31 * sum + c;
        return sum % M;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            args = new String[] {"Peter", "Ljunglöf"};
        Person someone = new Person(args[0], args[1]);
        System.out.println("The hash code for " + someone + " is " + someone.hashCode());
    }
}
