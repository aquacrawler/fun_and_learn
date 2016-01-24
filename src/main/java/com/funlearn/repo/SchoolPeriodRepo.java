package com.funlearn.repo;

import com.funlearn.domain.SchoolPeriod;
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
@View( name = "all", map = "function(doc) { if (doc.type === 'SchoolPeriod' ) emit( doc._id, null )}")
public class SchoolPeriodRepo extends CouchDbRepositorySupport<SchoolPeriod> {
    private static final Logger LOG = Logger.getLogger(SchoolPeriod.class.getName());
    
    @Autowired
    public SchoolPeriodRepo(CouchDbConnector couchDbConnector) {
        super(SchoolPeriod.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating SchoolPeriodRepo...");
    }
    
    
    @View( name = "currentPeriod", map = "function(doc) { if (doc.type === 'SchoolPeriod' ) emit( doc.current, null )}" )
    public SchoolPeriod getCurrentPeriod() {
        ViewQuery viewQuery = createQuery("currentPeriod").key(true).includeDocs(true);
        List<SchoolPeriod> result = db.queryView(viewQuery, SchoolPeriod.class);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
}
