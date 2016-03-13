package com.funlearn.web;

import com.funlearn.domain.CoursePrice;
import com.funlearn.repo.CoursePriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kerwin
 */
@Controller
@RequestMapping("/price")
public class PriceEP {
    
    @Autowired
    private CoursePriceRepo cpRepo;
    
    @RequestMapping(method = RequestMethod.POST, value = "/course")
    public ResponseEntity<CoursePrice> newCoursePrice(@RequestBody CoursePrice coursePrice) {
        cpRepo.add(coursePrice);
        return new ResponseEntity<>(coursePrice, HttpStatus.OK);
    }
    
}
