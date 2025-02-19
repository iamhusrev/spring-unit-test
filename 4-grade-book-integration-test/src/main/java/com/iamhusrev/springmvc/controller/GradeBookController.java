package com.iamhusrev.springmvc.controller;

import com.iamhusrev.springmvc.models.CollegeStudent;
import com.iamhusrev.springmvc.models.GradeBook;
import com.iamhusrev.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GradeBookController {

    @Autowired
    private GradeBook gradebook;

    @Autowired
    private StudentAndGradeService studentAndGradeService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudents(Model m) {
        Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradeBook();
        m.addAttribute("students", collegeStudents);
        return "index";
    }

    @PostMapping(value = "/")
    public String createStudent(@ModelAttribute("student") CollegeStudent student, Model m) {
        studentAndGradeService.createStudent(student.getFirstname(), student.getLastname(),
            student.getEmailAddress());
        Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradeBook();
        m.addAttribute("students", collegeStudents);
        return "redirect:/";
    }

    @GetMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable(name = "id") Integer id, Model m) {

        if (!studentAndGradeService.checkIfStudentIsNull(id)){
            return "error";
        }

        studentAndGradeService.deleteStudent(id);
        Iterable<CollegeStudent> collegeStudents = studentAndGradeService.getGradeBook();
        m.addAttribute("students", collegeStudents);
        return "redirect:/";
    }

    @GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model m) {
        return "studentInformation";
    }

}
