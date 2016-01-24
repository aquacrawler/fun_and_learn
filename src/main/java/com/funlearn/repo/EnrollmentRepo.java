package com.funlearn.repo;

import com.funlearn.domain.Enrollment;
import java.util.List;
import java.util.logging.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
@View( name = "all", map = "function(doc) { if (doc.type === 'Enrollment' ) emit( doc._id, null )}")
public class EnrollmentRepo extends CouchDbRepositorySupport<Enrollment> {
    private static final Logger LOG = Logger.getLogger(EnrollmentRepo.class.getName());
    
    @Autowired
    public EnrollmentRepo(CouchDbConnector couchDbConnector) {
        super(Enrollment.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating EnrollmentRepo...");
    }
    
    @View( name = "bySchoolPeriod", map = "function(doc) { if (doc.type === 'Enrollment') { emit(doc.schoolPeriodId, null); }}")
    public List<Enrollment> getEnrollmentBySchoolPeriod(String schoolPeriodId) {
        ViewQuery viewQuery = createQuery("bySchoolPeriod").key(schoolPeriodId).includeDocs(true);
        return db.queryView(viewQuery, Enrollment.class);
    }
}
