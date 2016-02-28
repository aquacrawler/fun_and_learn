package com.funlearn.repo;

import com.funlearn.domain.Enrollment;
import com.funlearn.domain.SchoolPeriod;
import com.funlearn.domain.StudentLevel;
import java.util.List;
import java.util.logging.Logger;
import org.ektorp.ComplexKey;
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
@View(name = "all", map = "function(doc) { if (doc.type === 'Enrollment' ) emit( doc._id, null )}")
public class EnrollmentRepo extends CouchDbRepositorySupport<Enrollment> {

    private static final Logger LOG = Logger.getLogger(EnrollmentRepo.class.getName());

    @Autowired
    private SchoolPeriodRepo spRepo;

    @Autowired
    public EnrollmentRepo(CouchDbConnector couchDbConnector) {
        super(Enrollment.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating EnrollmentRepo...");
    }

    @View(name = "bySchoolPeriod", map = "function(doc) { if (doc.type === 'Enrollment') { emit(doc.schoolPeriodId, null); }}")
    public List<Enrollment> getEnrollmentBySchoolPeriod(String schoolPeriodId) {
        ViewQuery viewQuery = createQuery("bySchoolPeriod").key(schoolPeriodId).includeDocs(true);
        return db.queryView(viewQuery, Enrollment.class);
    }

    public List<Enrollment> getCurrentEnrollmentByLevel(StudentLevel sl) {

        SchoolPeriod sp = spRepo.getCurrentPeriod();

        return getEnrollmentBySchoolPeriodAndLevel(sp.getId(), sl);
    }

    @View(name = "bySchoolPeriodAndStudentLevel", map = "function(doc) { if (doc.type === 'Enrollment') { emit([doc.schoolPeriodId, doc.studentLevel], null); }}")
    public List<Enrollment> getEnrollmentBySchoolPeriodAndLevel(String spId, StudentLevel level) {
        ViewQuery viewQuery = createQuery("bySchoolPeriodAndStudentLevel").key(ComplexKey.of(spId, level)).includeDocs(true);
        return db.queryView(viewQuery, Enrollment.class);
    }

    @View(name = "bySchoolPeriodAndStudent", map = "function(doc) { if (doc.type === 'Enrollment') { emit([doc.schoolPeriodId, doc.studentId], null); }}")
    public Enrollment getEnrollmentBySchoolPeriodAndStudent(String schoolPeriodId, String studentId) {
        ViewQuery viewQuery = createQuery("bySchoolPeriodAndStudent").key(ComplexKey.of(schoolPeriodId, studentId)).includeDocs(true);
        List<Enrollment> enrlmts = db.queryView(viewQuery, Enrollment.class);

        if (enrlmts.size() > 0) {
            return enrlmts.get(0);
        }

        return null;
    }
}
