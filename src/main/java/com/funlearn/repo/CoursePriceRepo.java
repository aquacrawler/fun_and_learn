package com.funlearn.repo;

import com.funlearn.domain.CoursePrice;
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
@View( name = "all", map = "function(doc) { if (doc.type === 'CoursePrice' ) emit( doc._id, null )}")
public class CoursePriceRepo extends CouchDbRepositorySupport<CoursePrice> {
    private static final Logger LOG = Logger.getLogger(CoursePriceRepo.class.getName());
    
    @Autowired
    public CoursePriceRepo(CouchDbConnector couchDbConnector) {
        super(CoursePrice.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating CoursePriceRepo...");
    }
    
    @View( name = "bySchoolPeriod", map = "function(doc) { if (doc.type === 'CoursePrice') { emit(doc.schoolPeriodId, null); }}")
    public CoursePrice getCoursePriceBySchoolPeriod(String schoolPeriodId) {
        ViewQuery viewQuery = createQuery("bySchoolPeriod").key(schoolPeriodId).includeDocs(true);
        List<CoursePrice> prices = db.queryView(viewQuery, CoursePrice.class);
        
        if (prices.size() > 0) {
            return prices.get(0);
        } else {
            return null;
        }
    }
}
