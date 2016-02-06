package com.funlearn.domain;

import java.math.BigDecimal;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * Account object linked to a school period;
 *    Note: Every new SchoolPeriod created should
 *          create new Account for each student who has still have some balance
 *          or will be enrolling
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TypeDiscriminator("doc.type == 'Account'")
public class Account extends CouchDbDocument {
    public static final String DOC_TYPE = "Account";
    
    @JsonProperty
    private String type;
    @JsonProperty
    private String schoolPeriodId;
    @JsonProperty
    private String studentId;
    @JsonProperty
    private BigDecimal balanceForwarded;   // balance from previous in another school period account that is forwarded to this account
    @JsonProperty
    private BigDecimal currentBalance;
    @JsonProperty
    private List<AccountEntry> entries;
    @JsonProperty
    private boolean latest;
    
    public Account() {
        super();
        this.type = DOC_TYPE;
    }
    
    public Account(BigDecimal balanceForwarded) {
        super();
        this.balanceForwarded = balanceForwarded;
        this.type = DOC_TYPE;
    }

    public String getSchoolPeriodId() {
        return schoolPeriodId;
    }

    public Account setSchoolPeriodId(String schoolPeriodId) {
        this.schoolPeriodId = schoolPeriodId;
        return this;
    }

    public String getStudentId() {
        return studentId;
    }

    public Account setStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public List<AccountEntry> getEntries() {
        return entries;
    }
    
    public Account setEntries(List<AccountEntry> entries) {
        this.entries = entries;
        recomputeBalance();
        return this;
    }
    
    private void recomputeBalance() {
        BigDecimal balance = new BigDecimal(0);
        balance.add(balanceForwarded);
        
        for (AccountEntry entry : entries) {
            if (entry.getType().equals(AccountEntry.DEBIT)) {
                balance.add(entry.getAmount());
            } else if (entry.getType().equals(AccountEntry.CREDIT)) {
                balance.subtract(entry.getAmount());
            }
        }
        
        this.currentBalance = balance;
    }

    public boolean isLatest() {
        return latest;
    }

    public Account setLatest(boolean current) {
        this.latest = current;
        return this;
    }
}
