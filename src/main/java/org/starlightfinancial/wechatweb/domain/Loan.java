package org.starlightfinancial.wechatweb.domain;

import java.io.Serializable;

public class Loan implements Serializable {

    private String contractNo;
    private String approvedAmount;
    private String approvedTerm;
    private String subject;
    private String guarantee;
    private String repaymentDate;
    private String repaymentStatus;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getApprovedTerm() {
        return approvedTerm;
    }

    public void setApprovedTerm(String approvedTerm) {
        this.approvedTerm = approvedTerm;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }
}
