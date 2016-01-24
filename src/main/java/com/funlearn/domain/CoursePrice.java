package com.funlearn.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * represents the fees for a specific course/level for a specific school period
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TypeDiscriminator("doc.type == 'CoursePrice'")
public class CoursePrice extends CouchDbDocument{
    private static String DOC_TYPE = "CoursePrice";

    public static String getDOC_TYPE() {
        return DOC_TYPE;
    }

    public static void setDOC_TYPE(String aDOC_TYPE) {
        DOC_TYPE = aDOC_TYPE;
    }
    
    @JsonProperty
    private String type;
    @JsonProperty
    private String schoolPeriodId;
    @JsonProperty
    private StudentLevel level;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private Map<String, BigDecimal> otherFees = new HashMap<>();
    
    public CoursePrice() {
        super();
        this.type = DOC_TYPE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchoolPeriodId() {
        return schoolPeriodId;
    }

    public void setSchoolPeriodId(String schoolPeriodId) {
        this.schoolPeriodId = schoolPeriodId;
    }

    public StudentLevel getLevel() {
        return level;
    }

    public void setLevel(StudentLevel level) {
        this.level = level;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<String, BigDecimal> getOtherFees() {
        return otherFees;
    }
    
    public void addOtherFee(String name, BigDecimal amount) {
        otherFees.put(name, amount);
    }
}
