package com.funlearn.repo;

import com.funlearn.domain.CoursePrice;
import com.funlearn.domain.StudentLevel;
import java.util.List;
import java.util.logging.Level;
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
@View(name = "all", map = "function(doc) { if (doc.type === 'CoursePrice' ) emit( doc._id, null )}")
public class CoursePriceRepo extends CouchDbRepositorySupport<CoursePrice> {

    private static final Logger LOG = Logger.getLogger(CoursePriceRepo.class.getName());
    private static final String BY_SCHOOL_PERIOD_AND_LEVEL = "bySchoolPeriodAndLevel";

    @Autowired
    public CoursePriceRepo(CouchDbConnector couchDbConnector) {
        super(CoursePrice.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating CoursePriceRepo...");
    }

    @View(name = BY_SCHOOL_PERIOD_AND_LEVEL, map = "function(doc) { if (doc.type === 'CoursePrice') { emit([doc.schoolPeriodId, doc.level], null); }}")
    public CoursePrice getCoursePriceBySchoolPeriodAndLevel(String schoolPeriodId, StudentLevel level) {
        ViewQuery viewQuery = createQuery(BY_SCHOOL_PERIOD_AND_LEVEL).key(ComplexKey.of(schoolPeriodId, level.toString())).includeDocs(true);
        List<CoursePrice> prices = db.queryView(viewQuery, CoursePrice.class);

        if (prices.size() > 0) {
            LOG.log(Level.INFO, "CoursePrice:{0}", prices.get(0));
            return prices.get(0);
        } else {
            return null;
        }
    }
}
