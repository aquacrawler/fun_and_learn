package com.funlearn.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 */
public class AccountEntry {
    public static String DEBIT = "Debit";
    public static String CREDIT = "Credit";
    
    @JsonProperty
    private Date date;
    @JsonProperty
    private String type;                // DEBIT/CREDIT
    @JsonProperty
    private String description;
    @JsonProperty
    private String referenceDoc;        // doc number of transaction reference like OR#
    @JsonProperty
    private BigDecimal amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceDoc() {
        return referenceDoc;
    }

    public void setReferenceDoc(String referenceDoc) {
        this.referenceDoc = referenceDoc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
