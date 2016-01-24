package com.funlearn.domain;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TypeDiscriminator("doc.type == 'Student'")
public class Student extends CouchDbDocument {
    
    public static final String DOC_TYPE = "Student";
    
    @JsonProperty
    private final String type;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private String firstname;
    @JsonProperty
    private String middlename;
    @JsonProperty
    private Date birthdate;
    @JsonProperty
    private String address;
    @JsonProperty
    private String fathersname;
    @JsonProperty
    private String fathersContactNo;
    @JsonProperty
    private String mothersname;
    @JsonProperty
    private String mothersContactNo;
    @JsonProperty
    private StudentLevel level;
    
    public Student() {
        super();
        this.type = DOC_TYPE;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFathersname() {
        return fathersname;
    }

    public void setFathersname(String fathersname) {
        this.fathersname = fathersname;
    }

    public String getFathersContactNo() {
        return fathersContactNo;
    }

    public void setFathersContactNo(String fathersContactNo) {
        this.fathersContactNo = fathersContactNo;
    }

    public String getMothersname() {
        return mothersname;
    }

    public void setMothersname(String mothersname) {
        this.mothersname = mothersname;
    }

    public String getMothersContactNo() {
        return mothersContactNo;
    }

    public void setMothersContactNo(String mothersContactNo) {
        this.mothersContactNo = mothersContactNo;
    }

    public StudentLevel getLevel() {
        return level;
    }

    public void setLevel(StudentLevel level) {
        this.level = level;
    }
}
