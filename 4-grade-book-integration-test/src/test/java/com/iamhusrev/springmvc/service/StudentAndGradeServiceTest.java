package com.iamhusrev.springmvc.service;

import com.iamhusrev.springmvc.models.CollegeStudent;
import com.iamhusrev.springmvc.models.GradeBookCollegeStudent;
import com.iamhusrev.springmvc.models.HistoryGrade;
import com.iamhusrev.springmvc.models.MathGrade;
import com.iamhusrev.springmvc.models.ScienceGrade;
import com.iamhusrev.springmvc.repository.HistoryGradeDao;
import com.iamhusrev.springmvc.repository.MathGradeDao;
import com.iamhusrev.springmvc.repository.ScienceGradeDao;
import com.iamhusrev.springmvc.repository.StudentDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    @Value("${sql.script.create.student}")
    private String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    public void setupDatabase() {

        jdbcTemplate.execute(sqlAddStudent);
        jdbcTemplate.execute(sqlAddMathGrade);
        jdbcTemplate.execute(sqlAddScienceGrade);
        jdbcTemplate.execute(sqlAddHistoryGrade);

    }

    @Test
    public void createStudentService() {

        studentService.createStudent("Husrev", "Ozcan",
            "iamhusrev2@iamhusrev.com");

        CollegeStudent student = studentDao.
            findByEmailAddress("iamhusrev@iamhusrev.com");

        assertEquals("iamhusrev@iamhusrev.com",
            student.getEmailAddress(), "find by email");
    }

    @Test
    public void isStudentNullCheck() {
        assertTrue(studentService.checkIfStudentIsNull(1));

        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService() {

        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        Optional<ScienceGrade> deletedScienceGrade = scienceGradeDao.findById(1);
        Optional<HistoryGrade> deletedHistoryGrade = historyGradeDao.findById(1);
        Optional<MathGrade> deletedMathGrade = mathGradeDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return True");
        assertTrue(deletedScienceGrade.isPresent(), "Return True");
        assertTrue(deletedHistoryGrade.isPresent(), "Return True");
        assertTrue(deletedMathGrade.isPresent(), "Return True");
        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);
        deletedScienceGrade = scienceGradeDao.findById(1);
        deletedHistoryGrade = historyGradeDao.findById(1);
        deletedMathGrade = mathGradeDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return False");
        assertFalse(deletedScienceGrade.isPresent(), "Return False");
        assertFalse(deletedHistoryGrade.isPresent(), "Return False");
        assertFalse(deletedMathGrade.isPresent(), "Return False");

    }

    @Test
    @Sql("/insertData.sql")
    public void getGradeBookService() {
        Iterable<CollegeStudent> collegeStudentIterable = studentService.getGradeBook();

        List<CollegeStudent> studentList = new ArrayList<>();

        for (CollegeStudent student : collegeStudentIterable) {
            studentList.add(student);
        }

        assertEquals(5, studentList.size());

    }

    @Test
    public void createGradeService() {

        // Create the grade
        assertTrue(studentService.createGrade(80.50, 1, "math"));
        assertTrue(studentService.createGrade(80.50, 1, "science"));
        assertTrue(studentService.createGrade(80.50, 1, "history"));

        // Get all grades with studentId
        Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(1);

        // Verify there is grades
        assertTrue(mathGrades.iterator().hasNext(), "Student has math grades");
        assertTrue(scienceGrades.iterator().hasNext());
        assertTrue(historyGrades.iterator().hasNext());
    }

    @Test
    public void createInvalidGradeService() {
        assertFalse(studentService.createGrade(-10, 1, "math"));
        assertFalse(studentService.createGrade(110, 1, "science"));
        assertFalse(studentService.createGrade(200, 1, "history"));
    }

    @Test
    public void deleteGradeService() {
        assertEquals(1, studentService.deleteGrade(1, "math"),
            "Returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1, "science"),
            "Returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1, "history"),
            "Returns student id after delete");
    }

    @Test
    public void deleteGradeServiceReturnMinusOne() {
        assertEquals(0, studentService.deleteGrade(0, "math"),
            "Returns -1 when student id is not found");
        assertEquals(0, studentService.deleteGrade(0, "science"),
            "Returns -1 when student id is not found");
    }

    @Test
    public void studentInformation() {

        GradeBookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(1);

        assertNotNull(gradebookCollegeStudent);
        assertEquals(1, gradebookCollegeStudent.getId());
        assertEquals("Husrev", gradebookCollegeStudent.getFirstname());
        assertEquals("Ozcan", gradebookCollegeStudent.getLastname());
        assertEquals("iamhusrev@iamhusrev.com", gradebookCollegeStudent.getEmailAddress());
        assertEquals(1, gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size());
        assertEquals(1, gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size());
        assertEquals(1, gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size());
    }


    @Test
    public void studentInformationReturnNull() {
        GradeBookCollegeStudent gradebookCollegeStudent = studentService.studentInformation(0);
        assertNull(gradebookCollegeStudent);
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbcTemplate.execute(sqlDeleteStudent);
        jdbcTemplate.execute(sqlDeleteMathGrade);
        jdbcTemplate.execute(sqlDeleteScienceGrade);
        jdbcTemplate.execute(sqlDeleteHistoryGrade);
    }

}
