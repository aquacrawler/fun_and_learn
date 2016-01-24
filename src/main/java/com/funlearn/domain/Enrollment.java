package com.funlearn.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TypeDiscriminator("doc.type == 'Enrollment'")
public class Enrollment extends CouchDbDocument {
    public static final String DOC_TYPE = "Enrollment";
    
    @JsonProperty
    private String type;
    @JsonProperty
    private String schoolPeriodId;
    @JsonProperty
    private StudentLevel studentLevel;
    @JsonProperty
    private String studentId;
    @JsonProperty
    private String accountId;
    
    public Enrollment() {
        super();
        this.type = DOC_TYPE;
    }

    public String getSchoolPeriodId() {
        return schoolPeriodId;
    }

    public void setSchoolPeriodId(String schoolPeriodId) {
        this.schoolPeriodId = schoolPeriodId;
    }

    public StudentLevel getStudentLevel() {
        return studentLevel;
    }

    public void setStudentLevel(StudentLevel studentLevel) {
        this.studentLevel = studentLevel;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
