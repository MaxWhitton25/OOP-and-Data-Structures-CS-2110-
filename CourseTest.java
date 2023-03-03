package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseTest {
    @Test
    void testConstructorandObservers() {
        // Constructor should yield a 3 credit course called CS1112 taught by professor Fan in
        // Statler, meeting every day at 1:00 PM for 75 minutes. The course should have an empty StudentSet.
        Course C = new Course("CS1112", 3, "Fan", "Statler", 13, 0, 75);
        assertEquals("CS1112", C.title());
        assertEquals(3, C.credits());
        assertEquals("Statler", C.location());
        assertEquals("Professor Fan", C.instructor());
    }

    @Test
    void testInstructor() {
        // Tests the instructor method. Should return "Professor Fan".
        Course C = new Course("CS1112", 3, "Fan", "Statler", 13, 0, 75);
        assertEquals("Professor Fan", C.instructor());
    }

    @Test
    void testFormatStartTime() {
        // Tests the case where the time is a PM time. Should return "1:00 PM"
        Course C = new Course("CS1112", 3, "Fan", "Statler", 13, 0, 75);
        assertEquals("1:00 PM", C.formatStartTime());

        // Tests the case where the time is an AM time. Should return "1:00 PM"
        Course D = new Course("CS1112", 3, "Fan", "Statler", 1, 0, 75);
        assertEquals("1:00 AM", D.formatStartTime());

        // Tests the case where the time is between 12:00 AM and 1:00 AM. Should return "12:30 AM"
        Course E = new Course("CS1112", 3, "Fan", "Statler", 0, 30, 75);
        assertEquals("12:30 AM", E.formatStartTime());

        // Tests the case where the time is between 12:00 PM and 1:00 PM. Should return "12:30 PM"
        Course F = new Course("CS1112", 3, "Fan", "Statler", 12, 30, 75);
        assertEquals("12:30 PM", F.formatStartTime());
    }

    @Test
    void testOverlap() {
        // Case where course A starts after course B ends. overlap should return false.
        Course A = new Course("A", 3, "aprof", "aloc", 10, 0, 60);
        Course B = new Course("B", 3, "bprof", "bloc", 8, 0, 60);
        assert !A.overlaps(B);

        // Case where course C ends before course D ends. overlap should return false.
        Course C = new Course("C", 3, "cprof", "cloc", 9, 0, 60);
        Course D = new Course("D", 3, "dprof", "dloc", 10, 0, 60);
        assert !C.overlaps(D);

        // Case where course E starts during course F. overlap should return true.
        Course E = new Course("E", 3, "eprof", "eloc", 10, 0, 60);
        Course F = new Course("F", 3, "fprof", "floc", 9, 0, 120);
        assert E.overlaps(F);

        // Case where course H starts during course G. overlap should return true.
        Course G = new Course("G", 3, "gprof", "gloc", 10, 0, 60);
        Course H = new Course("H", 3, "hprof", "hloc", 10, 59, 60);
        assert G.overlaps(H);
    }

    @Test
    void testEnroll() {
        // Case where student is already in the course. Should return false.
        Student s= new Student("first", "last");
        Course c = new Course("title", 3, "prof", "loc", 12, 0, 60);
        c.enrollStudent(s);
        boolean change =c.enrollStudent(s);
        assert !change;

        // Case where student is not already in the course. enrollStudent should return false,
        // and the student should now be enrolled in 3 credits.
        Student s1= new Student("first", "last");
        Course c1 = new Course("title", 3, "prof", "loc", 12, 0, 60);
        boolean change1 =c1.enrollStudent(s1);
        assert change1;
        assertEquals(3, s1.credits());


    }

    @Test
    void testdropStudent() {
        // Case where student is not already in the course. Should return false.
        Student s = new Student("first", "last");
        Course c = new Course("title", 3, "prof", "loc", 12, 0, 60);
        boolean change = c.dropStudent(s);
        assert !change;

        // Case where student is already in the course. dropStudent should return true,
        // and the student should now be enrolled in 0 credits.
        Student s1 = new Student("first", "last");
        Course c1 = new Course("title", 3, "prof", "loc", 12, 0, 60);
        c1.enrollStudent(s1);
        boolean change1 = c1.dropStudent(s1);
        assert change1;
        assertEquals(0, s1.credits());

    }
}
