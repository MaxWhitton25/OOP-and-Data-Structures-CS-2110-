package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentSetTest {
    @Test
    void testConstructorAndSize() {
        // Constructor should yield an empty set
        StudentSet students = new StudentSet();
        assertEquals(0, students.size());
    }
    @Test
    void testAdd() {
        // Adds a student to an empty student set, and size should be 1
        StudentSet s = new StudentSet();
        Student s1 = new Student("first", "last");
        s.add(s1);
        assertEquals(1, s.size());

        // Triggers a resize. Add 11 students, more than initial capacity. Size should be 11.
        StudentSet t = new StudentSet();
        for (int i = 0; i < 11; ++i) {
            String firstName= "first"+i;
            Student x = new Student(firstName, "last");
            t.add(x);
        }
        assertEquals(11, t.size());
    }

    @Test
    void testContains() {
        // Case where the set ss contains the student s. Should return true.
        Student s = new Student("first", "last");
        StudentSet ss= new StudentSet();
        ss.add(s);
        assert(ss.contains(s));
        // Case where the set tt does not contain the student t. Should return false.
        Student t = new Student("first", "last");
        StudentSet tt= new StudentSet();
        assert(!tt.contains(t));
    }

    @Test
    void testRemove() {
        // Case where the student is in the set. remove should return true, the set should no
        // longer contain the student, and the length of the set should be 0.
        Student s = new Student("first", "last");
        StudentSet ss= new StudentSet();
        ss.add(s);
        boolean removed=ss.remove(s);
        assert(removed);
        assert(!(ss.contains(s)));
        assertEquals(0, ss.size());

        // Case where the student is not in the set. remove should return false.
        Student t = new Student("first", "last");
        StudentSet tt= new StudentSet();
        boolean r = tt.remove(t);
        assert(!r);
        assert(!ss.contains(s));
    }
}
