package cs2110;

/**
 * A mutable set of students.
 */
public class StudentSet {
    // Implementation: the StudentSet is implemented as a resizable array data structure.
    // Implementation constraint: do not use any classes from java.util.
    // Implementation constraint: assert preconditions for all method parameters and assert that the
    //     invariant is satisfied at least at the end of every method that mutates any fields.

    /**
     * Array containing the students in the set.  Elements `store[0..size-1]` contain the distinct
     * students in this set, none of which are null.  All elements in `store[size..]` are null.  The
     * length of `store` is the current capacity of the data structure and is at least 1.  Two
     * students `s1` and `s2` are distinct if `s1.equals(s2)` is false.
     */
    private Student[] store;

    /**
     * The number of distinct students in this set.  Non-negative and no greater than
     * `store.length`.
     */
    private int size;

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {
        assert store != null && store.length > 0;
        assert size >= 0 && size <= store.length;

        for (int i = 0; i < size; ++i) {
            // Check that elements in use are non-null
            assert store[i] != null;

            // Check that students are all distinct
            for (int j = i + 1; j < size; ++j) {
                assert !store[i].equals(store[j]);
            }
        }
        // Check that unused capacity is all null
        for (int i = size; i < store.length; ++i) {
            assert store[i] == null;
        }
    }

    /**
     * Create an empty set of students of capacity 10.
     */
    public StudentSet() {
        this.store = new Student[10];
        this.size=0;
        assertInv();
    }

    /**
     * Return the number of students in this set.
     */
    public int size() {
        assertInv();
        return this.size;
    }

    /**
     * Effect: Add student `s` to the set.  Requires `s` is not already in the set.
     */
    public void add(Student s) {
        assert(!this.contains(s)); // Check precondition.
        assertInv();
        if (this.size >= this.store.length) {
            Student[] oldStore = this.store;
            int newBackingLength = 2 * this.store.length;
            this.store = new Student[newBackingLength];
            for (int i = 0; i < this.size; i++) {
                this.store[i] = oldStore[i];
            }
        }
        this.store[this.size()] = s;
        this.size = this.size + 1;
        assertInv();
    }

    /**
     * Return whether this set contains student `s`.
     */
    public boolean contains(Student s) {
        assertInv();
        boolean found = false;
        for (int i = 0; i < this.size; ++i) {
            if (store[i].equals(s)) {
                found = true;
            }
        }
        assertInv();
        return found;
    }

    /**
     * Effect: If student `s` is in this set, remove `s` from the set and return true. Otherwise
     * return false.
     */
    public boolean remove(Student s) {
        if (this.contains(s)) {
            for (int i = 0; i < this.size; i++) { // Loop to find student s.
                if (this.store[i].equals(s)) // Set the index corresponding to student s to null
                    this.store[i] = null;
                for (int j = i; j < this.size - 1; j++) { // Move every other student down one index
                    this.store[j] = this.store[j + 1];
                }
                this.size = this.size - 1;
            }

            return true;
        }
        else{
            return false;
        }
        // TODO 13: Implement this method according to its specification
        // You are welcome to decompose this task into operations that can be performed by
        // "helper methods", which you may define below.
    }

    /**
     * Return the String representation of this StudentSet.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(store[i]);
        }
        sb.append("}");
        return sb.toString();
    }
}
