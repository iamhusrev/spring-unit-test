package iamhusrev._3_spring_boot;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import iamhusrev._3_spring_boot.models.CollegeStudent;
import iamhusrev._3_spring_boot.models.StudentGrades;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ExampleApplicationTest {

    private static int COUNT = 0;

    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;
    @Autowired
    CollegeStudent student;
    @Autowired
    StudentGrades studentGrades;
    @Autowired
    ApplicationContext context;


    @BeforeEach
    public void beforeEach() {
        COUNT = COUNT + 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription +
            "  Version: " + appVersion + ". Execution of test method " + COUNT);
        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@luv2code_school.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }


    @Test
    @DisplayName("Add grade results for student grades not equal")
    public void addGradeResultsForStudentGradesAssertNotEquals() {
        assertNotEquals(10, studentGrades.addGradeResultsForSingleClass(
            student.getStudentGrades().getMathGradeResults()
        ));
    }

    @Test
    @DisplayName("Is grade greater")
    public void isGradeGreaterStudentGrades() {
        assertTrue(studentGrades.isGradeGreater(90, 75), "failure - should be true"
        );
    }


    @Test
    @DisplayName("Is grade greater false")
    public void isGradeGreaterStudentGradesAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(89, 92), "failure - should be false"
        );
    }

    @Test
    @DisplayName("Check null for student grades")
    public void checkNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()), "object should not be null"
        );
    }

    @Test
    @DisplayName("Create student without grade init")
    public void createStudentWithoutGradesInit() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Husrev");
        studentTwo.setLastname("Ozcan");
        studentTwo.setEmailAddress("chad.darby@luv2code_school.com");
        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }

    @Test
    @DisplayName("Verify students are prototypes")
    public void verifyStudentsArePrototypes() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);

        assertNotSame(student, studentTwo);
    }

    @Test
    @DisplayName("Find Grade Point Average")
    public void findGradePointAverage() {
        assertAll("Testing all assertEquals",
            ()-> assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults())),
            ()-> assertEquals(88.31, studentGrades.findGradePointAverage(
                student.getStudentGrades().getMathGradeResults()))
        );
    }

}