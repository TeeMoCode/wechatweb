package org.starlightfinancial.wechatweb.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author senlin.deng
 */
public class Loan {


    private Integer id;
    private Integer dateId;
    private Integer creditSubjectType;
    private Integer creditSubjectId;
    private String contractNo;
    private String businessNo;
    private BigDecimal amount;
    private Integer terms;
    private String unit;
    private String guarantee;
    private String isRepayment;
    private Date repaymentDate;
    private String borrower;
    private String unit_cn;
    private Integer repaymentDay;
    private String status;
    private String conclusion;
    private String conclusionInChinese;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getCreditSubjectType() {
        return creditSubjectType;
    }

    public void setCreditSubjectType(Integer creditSubjectType) {
        this.creditSubjectType = creditSubjectType;
    }

    public Integer getCreditSubjectId() {
        return creditSubjectId;
    }

    public void setCreditSubjectId(Integer creditSubjectId) {
        this.creditSubjectId = creditSubjectId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerms() {
        return terms;
    }

    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getUnit_cn() {
        return unit_cn;
    }

    public void setUnit_cn(String unit_cn) {
        this.unit_cn = unit_cn;
    }

    public String getIsRepayment() {
        return isRepayment;
    }

    public void setIsRepayment(String isRepayment) {
        this.isRepayment = isRepayment;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Integer getRepaymentDay() {
        return repaymentDay;
    }

    public void setRepaymentDay(Integer repaymentDay) {
        this.repaymentDay = repaymentDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getConclusionInChinese() {
        return conclusionInChinese;
    }

    public void setConclusionInChinese(String conclusionInChinese) {
        this.conclusionInChinese = conclusionInChinese;
    }
}
