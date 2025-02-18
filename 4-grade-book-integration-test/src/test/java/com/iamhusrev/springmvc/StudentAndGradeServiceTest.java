package com.iamhusrev.springmvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iamhusrev.springmvc.models.CollegeStudent;
import com.iamhusrev.springmvc.repository.StudentDao;
import com.iamhusrev.springmvc.service.StudentAndGradeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @BeforeEach
    public void setupDatabase() {
        jdbcTemplate.execute("insert into student(firstname, lastname, email_address) " +
            "values ('Husrev', 'Ozcan', 'iamhusrev@iamhusrev.com')");
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

        assertTrue(deletedCollegeStudent.isPresent(), "Return True");

        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return False");
    }

    @Test
    public void getGradeBookService() {
        Iterable<CollegeStudent> collegeStudentIterable = studentService.getGradeBook();

        List<CollegeStudent> studentList = new ArrayList<>();

        for (CollegeStudent student : collegeStudentIterable) {
            studentList.add(student);
        }

        assertEquals(1, studentList.size());

    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbcTemplate.execute("delete from student");
        jdbcTemplate.execute("alter table student ALTER COLUMN ID RESTART WITH 1");
    }
}
