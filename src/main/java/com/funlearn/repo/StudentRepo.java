package com.funlearn.repo;

import com.funlearn.domain.Student;
import java.util.logging.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kerwin
 */
@Repository
@View( name = "all", map = "function(doc) { if (doc.type === 'Student' ) emit( doc.lastname, null )}")
public class StudentRepo extends CouchDbRepositorySupport<Student> {
    private static final Logger LOG = Logger.getLogger(StudentRepo.class.getName());
    
    
    @Autowired
    public StudentRepo(CouchDbConnector couchDbConnector) {
        super(Student.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating StudentRepo...");
    }
}
