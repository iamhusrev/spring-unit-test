package com.iamhusrev.springmvc.service;

import com.iamhusrev.springmvc.models.CollegeStudent;
import com.iamhusrev.springmvc.models.Grade;
import com.iamhusrev.springmvc.models.GradeBookCollegeStudent;
import com.iamhusrev.springmvc.models.HistoryGrade;
import com.iamhusrev.springmvc.models.MathGrade;
import com.iamhusrev.springmvc.models.ScienceGrade;
import com.iamhusrev.springmvc.models.StudentGrades;
import com.iamhusrev.springmvc.repository.HistoryGradeDao;
import com.iamhusrev.springmvc.repository.MathGradeDao;
import com.iamhusrev.springmvc.repository.ScienceGradeDao;
import com.iamhusrev.springmvc.repository.StudentDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private StudentGrades studentGrades;

    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrade;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrade;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    public void createStudent(String firstname, String lastname, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstname, lastname, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if (checkIfStudentIsNull(id)) {
            studentDao.deleteById(id);
            mathGradeDao.deleteById(id);
            scienceGradeDao.deleteById(id);
            historyGradeDao.deleteById(id);
        }
    }

    public Iterable<CollegeStudent> getGradeBook() {
        return studentDao.findAll();
    }

    public boolean createGrade(double grade, int studentId, String gradeType) {

        if (!checkIfStudentIsNull(studentId)) {
            return false;
        }

        if (grade >= 0 && grade <= 100) {
            if (gradeType.equals("math")) {
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradeDao.save(mathGrade);
                return true;
            }
            if (gradeType.equals("science")) {
                scienceGrade.setId(0);
                scienceGrade.setGrade(grade);
                scienceGrade.setStudentId(studentId);
                scienceGradeDao.save(scienceGrade);
                return true;
            }
            if (gradeType.equals("history")) {
                historyGrade.setId(0);
                historyGrade.setGrade(grade);
                historyGrade.setStudentId(studentId);
                historyGradeDao.save(historyGrade);
                return true;
            }
        }

        return false;
    }

    public int deleteGrade(int id, String gradeType) {
        int studentId = 0;

        if (gradeType.equals("math")) {
            Optional<MathGrade> grade = mathGradeDao.findById(id);
            if (grade.isEmpty()) {
                return studentId;
            }
            studentId = grade.get().getStudentId();
            mathGradeDao.deleteById(id);
        }
        if (gradeType.equals("science")) {
            Optional<ScienceGrade> grade = scienceGradeDao.findById(id);
            if (grade.isEmpty()) {
                return studentId;
            }
            studentId = grade.get().getStudentId();
            scienceGradeDao.deleteById(id);
        }
        if (gradeType.equals("history")) {
            Optional<HistoryGrade> grade = historyGradeDao.findById(id);
            if (grade.isEmpty()) {
                return studentId;
            }
            studentId = grade.get().getStudentId();
            historyGradeDao.deleteById(id);
        }

        return studentId;
    }

    public GradeBookCollegeStudent studentInformation(int i) {

        if (!checkIfStudentIsNull(i)) {
            return null;
        }

        Optional<CollegeStudent> student = studentDao.findById(i);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(i);
        Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(i);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(i);

        List<Grade> mathGradesList = new ArrayList<>();
        mathGrades.forEach(mathGradesList::add);

        List<Grade> scienceGradesList = new ArrayList<>();
        scienceGrades.forEach(scienceGradesList::add);

        List<Grade> historyGradesList = new ArrayList<>();
        historyGrades.forEach(historyGradesList::add);

        studentGrades.setHistoryGradeResults(historyGradesList);
        studentGrades.setMathGradeResults(mathGradesList);
        studentGrades.setScienceGradeResults(scienceGradesList);

        return new GradeBookCollegeStudent(student.get().getId(),
            student.get().getFirstname(), student.get().getLastname(), student.get().getEmailAddress(), studentGrades);
    }
}
