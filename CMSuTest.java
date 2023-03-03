package cs2110;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CMSuTest {
    @Test
    void testHasConflict() {
    // Tests the case where the courses overlap. Should return true.
        Course C = new Course("C", 3, "cprof", "cloc", 9, 0, 60);
        Course C1 = new Course("C1", 3, "c1prof", "c1loc", 9, 30, 60);
        Student s = new Student("first", "last");
        C.enrollStudent(s);
        C1.enrollStudent(s);
        CMSu cms = new CMSu();
        cms.addCourse(C);
        cms.addCourse(C1);
        assert cms.hasConflict(s);

        // Tests the case where the courses do not overlap. Should return false.
        Course C2 = new Course("C2", 3, "c2prof", "c2loc", 9, 0, 60);
        Course C3 = new Course("C3", 3, "c3prof", "c3loc", 10, 0, 60);
        Student s1 = new Student("first", "last");
        C.enrollStudent(s1);
        C1.enrollStudent(s1);
        CMSu cms1 = new CMSu();
        cms1.addCourse(C2);
        cms1.addCourse(C3);
        assert !cms1.hasConflict(s1);
    }

    @Test
    void testauditCredits() {
        // The case where the student is over the credit limit. The set returned by auditCredits()
        // should include the student.
        Student s = new Student("first", "last");
        Course C = new Course("C", 11, "cprof", "cloc", 9, 0, 60);
        CMSu cms = new CMSu();
        cms.addCourse(C);
        cms.addStudent(s);
        C.enrollStudent(s);
        StudentSet setOfOver = new StudentSet();
        setOfOver= cms.auditCredits(10);
        assert setOfOver.contains(s);

        // The case where the student is under the credit limit. The set returned by auditCredits()
        // should not include the student.
        Student s1 = new Student("first", "last");
        Course C1 = new Course("C1", 11, "c1prof", "c1loc", 9, 0, 60);
        CMSu cms1 = new CMSu();
        cms.addCourse(C1);
        cms.addStudent(s1);
        C1.enrollStudent(s1);
        StudentSet setOfOver1 = new StudentSet();
        setOfOver= cms.auditCredits(12);
        assert !setOfOver.contains(s1);
    }

    @Test
    void checkCreditConsistency() {
        // The case where the student's credit total is consistent. Should return true.
        Student s = new Student("first", "last");
        Course C = new Course("C", 11, "cprof", "cloc", 9, 0, 60);
        CMSu cms = new CMSu();
        cms.addCourse(C);
        cms.addStudent(s);
        C.enrollStudent(s);
        assert cms.checkCreditConsistency();

        // The case where the student's credit total is not consistent. Should return false.
        Student s1 = new Student("first", "last");
        Course C1 = new Course("C1", 11, "c1prof", "c1loc", 9, 0, 60);
        CMSu cms1 = new CMSu();
        cms1.addCourse(C1);
        cms1.addStudent(s1);
        s1.adjustCredits(3);
        assert !cms1.checkCreditConsistency();
    }
}


