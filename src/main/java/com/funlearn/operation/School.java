package com.funlearn.operation;

import com.funlearn.domain.Account;
import com.funlearn.domain.AccountEntry;
import com.funlearn.domain.CoursePrice;
import com.funlearn.domain.Enrollment;
import com.funlearn.domain.SchoolPeriod;
import com.funlearn.domain.StudentLevel;
import com.funlearn.repo.AccountRepo;
import com.funlearn.repo.CoursePriceRepo;
import com.funlearn.repo.EnrollmentRepo;
import com.funlearn.repo.SchoolPeriodRepo;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class School {
    
    private static final Logger LOG = Logger.getLogger(School.class.getName());
    
    @Autowired
    SchoolPeriodRepo schoolPeriodRepo;
    
    @Autowired
    AccountRepo accountRepo;
    
    @Autowired
    EnrollmentRepo enrollmentRepo;
    
    @Autowired
    CoursePriceRepo coursePriceRepo;
    
    public SchoolPeriod newSchoolPeriod(String periodName) {
        // retrieve the latest school period and update it to not current
        SchoolPeriod sp = schoolPeriodRepo.getCurrentPeriod();
        int seqNo = 0;
        if (sp != null) {
            sp.setCurrent(false);
            schoolPeriodRepo.update(sp);
            seqNo = sp.getSeqNo();
        }

        // create a new school period and set it to current and update
        SchoolPeriod newSp = new SchoolPeriod();
        newSp.setCurrent(true)
                .setSeqNo(seqNo + 1)
                .setName(periodName);
        schoolPeriodRepo.add(newSp);
        
        return newSp;
    }
    
    public void updateSchoolPeriod(SchoolPeriod sp) {
        SchoolPeriod savedSP = schoolPeriodRepo.get(sp.getId());
        savedSP.setName(sp.getName());
        
        schoolPeriodRepo.update(savedSP);
    }
    
    public Account addAccountForSchoolPeriod(String studentId, String schoolPeriodId) {
        // get the latest account for student
        Account account = accountRepo.getLatestAccount(studentId);
        
        if (account == null) {
            // no latest account so we create one
            LOG.log(Level.INFO, "No account for student {0}. Creating new one.", studentId);
            Account newAccount = new Account().setLatest(true)
                    .setStudentId(studentId)
                    .setSchoolPeriodId(schoolPeriodId);
            accountRepo.add(newAccount);
            return newAccount;
        } else {
            LOG.log(Level.INFO, "Retrieved Account:{0}", account.getId());
            if (account.getSchoolPeriodId().equals(schoolPeriodId)) {
                return account;
            }

            // latest account will not be the latest now as we will create a new one
            account.setLatest(false);
            accountRepo.update(account);

            // create a new account and pass in the latest balance from previous
            Account newAccount = new Account(account.getCurrentBalance()).setLatest(true)
                    .setStudentId(studentId)
                    .setSchoolPeriodId(schoolPeriodId);
            
            accountRepo.add(newAccount);
            return newAccount;
        }
    }
    
    public Enrollment enroll(String studentId, StudentLevel level, String schoolPeriodId) {
        // check if there is an existing record already
        Enrollment enrmt = enrollmentRepo.getEnrollmentBySchoolPeriodAndStudent(schoolPeriodId, studentId);
        if (enrmt != null) {
            return enrmt;
        }
        
        Account account = addAccountForSchoolPeriod(studentId, schoolPeriodId);
        
        enrmt = new Enrollment().setAccountId(account.getId())
                .setStudentId(studentId)
                .setStudentLevel(level)
                .setSchoolPeriodId(schoolPeriodId);
        
        enrollmentRepo.add(enrmt);

        // add the initial fees up front to account
        CoursePrice cp = coursePriceRepo.getCoursePriceBySchoolPeriodAndLevel(schoolPeriodId, level);
        Map<String, BigDecimal> initFees = cp.getOtherFees();
        for (String key : initFees.keySet()) {
            account.getEntries().add(new AccountEntry().setDate(Calendar.getInstance().getTime())
                    .setType(AccountEntry.DEBIT)
                    .setDescription(key)
                    .setAmount(initFees.get(key)));
        }
        
        accountRepo.update(account);
        
        return enrmt;
    }

    /**
     * create an 'empty' CoursePrice object
     *
     * @param schoolPeriodId
     * @param level
     * @return
     */
    public CoursePrice newCoursePrice(StudentLevel level) {
        // check first if existing
        SchoolPeriod sp = schoolPeriodRepo.getCurrentPeriod();
        
        if (sp == null) {
            LOG.severe("No existing School Period. Should create School Period first");
            return null;
        }
        
        CoursePrice cp = coursePriceRepo.getCoursePriceBySchoolPeriodAndLevel(sp.getId(), level);
        if (cp != null) {
            LOG.severe("Can't create a new CoursePrice as there is already an existing one. A new SchoolPeriod should be create first");
            return cp;
        }
        
        CoursePrice newCp = new CoursePrice().setSchoolPeriodId(sp.getId())
                .setLevel(level);
        
        coursePriceRepo.add(newCp);
        
        return newCp;
    }
    
    public void updateCoursePrice(CoursePrice cp) {
        coursePriceRepo.update(cp);
    }
}
