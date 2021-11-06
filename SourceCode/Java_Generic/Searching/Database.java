
import java.util.Map;
import java.util.HashMap;

/* *** ODSATag: Database *** */
class Person {
    public String pnr;    // personnummer
    public String name;   // person's name

    Person(String pnr, String name) {
        this.pnr = pnr;
        this.name = name;
    }
/* *** ODSAendTag: Database *** */

    public String toString() {
        return name + " (" + pnr + ")";
    }
/* *** ODSATag: Database *** */
}

class PersonDatabase {
    Map<String, Person> database;
    PersonDatabase() {
        database = new HashMap<>();
    }

    // Put the person in the database.
    public void put(Person p) {
        database.put(p.pnr, p);
    }

    // Remove a person from the database.
    public void remove(Person p) {
        database.remove(p.pnr);
    }

    // Find the person who has a given personnummer.
    public Person find(String pnr) {
        return database.get(pnr);
    }
}
/* *** ODSAendTag: Database *** */

class Database {
    // Testing the database.
    public static void main(String args[]) {
        PersonDatabase db = new PersonDatabase();
        db.put(new Person("19481102-2345", "Lisa"));
        db.put(new Person("19711031-1234", "Peter"));
        db.put(new Person("20010622-4564", "Ylva"));

        System.out.println(db.find("20010622-4564"));

        db.remove(new Person("20010622-4564", "Ylva"));

        System.out.println(db.find("20010622-4564"));
    }
}
