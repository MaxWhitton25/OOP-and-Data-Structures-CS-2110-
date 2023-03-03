package cs2110;

/**
 * A student tracked by the CMSμ course management system.
 */
public class Student {
    /**
     * The student's first name. Cannot be empty or null.
     */
    private final String first;
    /**
     * The student's first name. Cannot be empty or null.
     */
    private final String last;
    /**
     * The number of credits the student is enrolled in. Must be a non-negative
     * integer that is equal to the sum of the credits of all courses this student is enrolled in.
     */
    private int nCredits; //

    /**
     * Assert that this object satisfies its class invariants.
     */
    private void assertInv() {
        assert first != null && !first.isEmpty();
        assert last != null && !last.isEmpty();
        assert nCredits>=0;
    }

    /**
     * Create a new Student with first name `firstName` and last name `lastName` who is not enrolled
     * for any credits.  Requires firstName and lastName are not empty.
     */
    public Student(String firstName, String lastName) {
        assert firstName != null && !(firstName.isEmpty()); // checking class invariant and
        // preconditions are satisfied
        assert lastName != null && !(lastName.isEmpty());
        this.first=firstName;
        this.last=lastName;
        assertInv();
    }

    /**
     * Return the first name of this Student.  Will not be empty.
     */
    public String firstName() {
        assertInv();
        String f=this.first;
        return f;
    }

    /**
     * Return the last name of this Student.  Will not be empty.
     */
    public String lastName() {
        assertInv();
        String last=this.last;
        return last;
    }

    /**
     * Return the full name of this student, formed by joining their first and last names separated
     * by a space.
     */
    public String fullName() {
        // Observe that, by invoking methods instead of referencing this fields, this method was
        // implemented without knowing how you will name your fields.
        return firstName() + " " + lastName();
    }

    /**
     * Return the number of credits this student is currently enrolled in.  Will not be negative.
     */
    public int credits() {
        assertInv();
        return this.nCredits;
    }

    /**
     * Change the number of credits this student is enrolled in by `deltaCredits`. For example, if
     * this student were enrolled in 12 credits, then `this.adjustCredits(3)` would result in their
     * credits changing to 15, whereas `this.adjustCredits(-4)` would result in their credits
     * changing to 8.  Requires that the change would not cause the student's credits to become
     * negative.
     */
    void adjustCredits(int deltaCredits) {
        assertInv();
        assert (this.nCredits+deltaCredits)>=0; // Checking the precondition
        this.nCredits=this.nCredits+deltaCredits;
        assertInv();
    }

    /**
     * Return the full name of this student as its string representation.
     */
    @Override
    public String toString() {
        return fullName();
    }
}
