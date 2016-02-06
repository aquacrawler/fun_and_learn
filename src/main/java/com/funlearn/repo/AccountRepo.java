package com.funlearn.repo;

import com.funlearn.domain.Account;
import java.util.List;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;
import org.springframework.stereotype.Repository;
import java.util.logging.Logger;
import org.ektorp.ComplexKey;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@Repository
@View(name = "all", map = "function(doc) { if (doc.type === 'Account' ) emit( doc._id, null )}")
public class AccountRepo extends CouchDbRepositorySupport<Account> {

    private static final Logger LOG = Logger.getLogger(AccountRepo.class.getName());

    @Autowired
    public AccountRepo(CouchDbConnector couchDbConnector) {
        super(Account.class, couchDbConnector);
        initStandardDesignDocument();
        LOG.info("Instantiating AccountRepo...");
    }

    @View(name = "byStudentAndSchoolPeriod", map = "function(doc) { if (doc.type === 'Account') { emit([ doc.studentId, doc.schoolPeriodId ], null); }}")
    public Account getAccountByStudentAndSchoolPeriod(String studentId, String schoolPeriodId) {
        ViewQuery viewQuery = createQuery("byStudentAndSchoolPeriod").key(ComplexKey.of(studentId, schoolPeriodId)).includeDocs(true);
        List<Account> result = db.queryView(viewQuery, Account.class);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @View(name = "bySchoolPeriod", map = "function(doc) { if (doc.type === 'Account') { emit(doc.schoolPeriodId, null); }}")
    public List<Account> getAccountsBySchoolPeriod(String schoolPeriodId) {
        ViewQuery viewQuery = createQuery("bySchoolPeriod").key(schoolPeriodId).includeDocs(true);
        return db.queryView(viewQuery, Account.class);
    }

    @View(name = "byStudentLatest", map = "function(doc) { if (doc.type === 'Account' ) emit( [doc.studentId, doc.latest], null )}")
    public Account getLatestAccount(String studentId) {
        ViewQuery viewQuery = createQuery("byStudentLatest").key(ComplexKey.of(studentId, true)).includeDocs(true);
        List<Account> accounts = db.queryView(viewQuery, Account.class);
        if (accounts.size() > 0) {
            return accounts.get(0);
        }

        return null;
    }
}
