package com.iamhusrev.service;

import com.iamhusrev.dao.ApplicationDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;

    public double addGradeResultsForSingleClass(List<Double> numbers) {
        return applicationDao.addGradeResultsForSingleClass(numbers);
    }

    public double findGradePointAverage(List<Double> grades) {
        return applicationDao.findGradePointAverage(grades);
    }

    public Object checkNull(Object obj) {
        return applicationDao.checkNull(obj);
    }

}
