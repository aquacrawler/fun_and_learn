package com.funlearn.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * Represents a period where the school operates like
 *   1st semester SY 2015-16, summer SY 2015-16
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TypeDiscriminator("doc.type == 'SchoolPeriod'")
public class SchoolPeriod extends CouchDbDocument {
    public static final String DOC_TYPE = "SchoolPeriod";
    
    @JsonProperty
    private String type;
    @JsonProperty
    private String name;
    @JsonProperty
    private boolean current;            // tells whether this SchoolPeriod is the current one
    @JsonProperty
    private int seqNo;                  // school period sequence number
    
    public SchoolPeriod() {
        super();
        this.type = DOC_TYPE;
    }

    public String getType() {
        return type;
    }

    public SchoolPeriod setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolPeriod setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isCurrent() {
        return current;
    }

    public SchoolPeriod setCurrent(boolean current) {
        this.current = current;
        return this;
    }
    public int getSeqNo() {
        return seqNo;
    }

    public SchoolPeriod setSeqNo(int seqNo) {
        this.seqNo = seqNo;
        return this;
    }
}
