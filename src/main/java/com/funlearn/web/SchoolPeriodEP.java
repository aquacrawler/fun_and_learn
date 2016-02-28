package com.funlearn.web;

import com.funlearn.domain.SchoolPeriod;
import com.funlearn.operation.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author kerwin
 */
@Controller
@RequestMapping("/school-period")
public class SchoolPeriodEP {
    
    @Autowired
    School school;
    
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<SchoolPeriod> newSchoolPeriod(@RequestParam String spName) {
        
        SchoolPeriod sp = school.newSchoolPeriod(spName);
        
        return new ResponseEntity<>(sp, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "")
    public ResponseEntity<SchoolPeriod> updateSchoolPeriod(@RequestBody SchoolPeriod sp) {
        
        school.updateSchoolPeriod(sp);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
