package com.iamhusrev.springmvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.iamhusrev.springmvc.models.CollegeStudent;
import com.iamhusrev.springmvc.repository.StudentDao;
import com.iamhusrev.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void createStudentService() {

        studentService.createStudent("Chad", "Darby",
            "chad.darby@luv2code_school.com");

        CollegeStudent student = studentDao.
            findByEmailAddress("chad.darby@luv2code_school.com");

        assertEquals("chad.darby@luv2code_school.com",
            student.getEmailAddress(), "find by email");
    }
}
