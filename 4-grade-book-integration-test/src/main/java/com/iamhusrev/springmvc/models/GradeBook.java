package com.iamhusrev.springmvc.models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GradeBook {

    private List<GradeBookCollegeStudent> students = new ArrayList<>();

    public GradeBook() {

    }

    public GradeBook(List<GradeBookCollegeStudent> students) {
        this.students = students;
    }

    public List<GradeBookCollegeStudent> getStudents() {
        return students;
    }

    public void setStudents(List<GradeBookCollegeStudent> students) {
        this.students = students;
    }
}
