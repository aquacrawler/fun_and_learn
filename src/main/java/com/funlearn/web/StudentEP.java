package com.funlearn.web;

import com.funlearn.domain.Student;
import com.funlearn.repo.StudentRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author kerwin
 */
@Controller
@RequestMapping("/students")
public class StudentEP {
    
    @Autowired
    StudentRepo studentRepo;
    
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentRepo.add(student);
        
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<List<Student>>(studentRepo.getAll(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        return new ResponseEntity<Student>(studentRepo.get(id), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        studentRepo.update(student);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
