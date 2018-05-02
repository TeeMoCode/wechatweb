package org.starlightfinancial.wechatweb.domain;

import java.math.BigDecimal;

/**
 * @Author: SiliChen
 * @Description:
 * @Date: Created in 15:54 2018/3/22
 * @Modified By:
 */

public class Application {


    private Integer dateId;


    private BigDecimal amount;

    private Integer flowNo;


    private String flow_title;


    private Integer terms;

    private String unit;

    private String unit_cn;
    private String conclusion;
    private String conclusionInChinese;

    public Integer getDateId() {

        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Integer flowNo) {
        this.flowNo = flowNo;
    }

    public String getFlow_title() {
        return flow_title;
    }

    public void setFlow_title(String flow_title) {
        this.flow_title = flow_title;
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

    public String getUnit_cn() {
        return unit_cn;
    }

    public void setUnit_cn(String unit_cn) {
        this.unit_cn = unit_cn;
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
