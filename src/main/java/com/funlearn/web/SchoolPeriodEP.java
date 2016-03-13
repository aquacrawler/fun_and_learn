package com.funlearn.web;

import com.funlearn.domain.SchoolPeriod;
import com.funlearn.operation.School;
import com.funlearn.repo.SchoolPeriodRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    SchoolPeriodRepo spRepo;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<SchoolPeriod> getSchoolPeriod(@PathVariable String id) {
        return new ResponseEntity<>(spRepo.get(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public ResponseEntity<SchoolPeriod> getCurrentSchoolPeriod() {
        return new ResponseEntity<>(spRepo.getCurrentPeriod(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<SchoolPeriod>> getSchoolPeriods() {
        return new ResponseEntity<>(spRepo.getAll(), HttpStatus.OK);
    }

}
