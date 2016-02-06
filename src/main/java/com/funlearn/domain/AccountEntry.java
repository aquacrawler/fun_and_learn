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

    public AccountEntry setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getType() {
        return type;
    }

    public AccountEntry setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccountEntry setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReferenceDoc() {
        return referenceDoc;
    }

    public AccountEntry setReferenceDoc(String referenceDoc) {
        this.referenceDoc = referenceDoc;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public AccountEntry setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
