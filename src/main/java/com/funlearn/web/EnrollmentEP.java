package com.funlearn.web;

import com.funlearn.domain.Enrollment;
import com.funlearn.domain.StudentLevel;
import com.funlearn.operation.School;
import com.funlearn.repo.EnrollmentRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kerwin
 */
@Controller
@RequestMapping("/enrollments")
public class EnrollmentEP {

    @Autowired
    School school;
    
    @Autowired
    EnrollmentRepo enrRepo;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Enrollment> enroll(@RequestParam String studentId,
                                                @RequestParam String studentLevel,
                                                @RequestParam String schoolPeriodId) {

        Enrollment enr = school.enroll(studentId, StudentLevel.valueOf(studentLevel), schoolPeriodId);
        
        return new ResponseEntity<>(enr, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/student-level/{level}")
    public ResponseEntity<List<Enrollment>> getEnrollmentForLevel(@PathVariable String level) {
        
        List<Enrollment> enrollments = enrRepo.getCurrentEnrollmentByLevel(StudentLevel.valueOf(level));
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Enrollment> getEnrollment(@PathVariable String id) {
        
        Enrollment enr = enrRepo.get(id);
        
        if (enr == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(enr, HttpStatus.OK);
    }
    
    
}
