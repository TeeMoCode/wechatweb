package org.starlightfinancial.wechatweb.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.starlightfinancial.wechatweb.domain.Application;
import org.starlightfinancial.wechatweb.domain.Loan;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.enums.ErrorCodeEnum;
import org.starlightfinancial.wechatweb.utils.HttpClientUtil;
import org.starlightfinancial.wechatweb.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author senlin.deng
 */
@Controller
@RequestMapping("/loan")
public class LoanController {
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
    @Value(value = "${loan.history.url}")
    public String historyUrl;

    @Value(value = "${loan.detail.url}")
    public String detailUrl;

    @Value(value = "${loan.progress.url}")
    public String progressUrl;

    @RequestMapping("/loan-history.do")
    public String loanHistory( Model model) {
        User currentUser = Util.getCurrentUser();
        ArrayList<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();
        basicNameValuePairs.add(new BasicNameValuePair("idNumber", currentUser.getCertificateNo()));
        basicNameValuePairs.add(new BasicNameValuePair("name", currentUser.getUsername()));
//        String resultStr = HttpClientUtil.post(historyUrl, basicNameValuePairs);
        String resultStr = HttpClientUtil.post("http://localhost:8080/myloan", basicNameValuePairs);
        logger.info("查询贷款记录返回数据" + resultStr);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        List<Loan> loans = null;
        if (jsonObject != null && ErrorCodeEnum.ERROR_CODE_01.getCode().equals(jsonObject.getString("error_code"))) {
            loans = JSONArray.parseArray(jsonObject.getString("result"), Loan.class);
        }
        model.addAttribute("loanHistory", loans);
        return "loan-history";
    }

    @RequestMapping("/loan-detail.do")
    public String loanDetail(String contractNo, Model model) {
        // TODO: 2018/2/26 根据合同编号查询贷款具体信息
        ArrayList<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();
        basicNameValuePairs.add(new BasicNameValuePair("contractNo", contractNo));
//        String resultStr = HttpClientUtil.post(detailUrl, basicNameValuePairs);
        String resultStr = HttpClientUtil.post("http://localhost:8080/myloandetail", basicNameValuePairs);
        logger.info("查询贷款详细信息返回数据" + resultStr);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        Loan loan = null;
        if (jsonObject != null && ErrorCodeEnum.ERROR_CODE_01.getCode().equals(jsonObject.getString("error_code"))) {
            loan = JSONArray.parseArray(jsonObject.getString("result"), Loan.class).get(0);
        }
        model.addAttribute("loan", loan);
        return "loan-detail";
    }


    @RequestMapping("/loan-progress.do")
    public String loanProgress( Model model) {
        User currentUser = Util.getCurrentUser();
        ArrayList<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();
        basicNameValuePairs.add(new BasicNameValuePair("idNumber", currentUser.getCertificateNo()));
        basicNameValuePairs.add(new BasicNameValuePair("name", currentUser.getUsername()));
//         String resultStr = HttpClientUtil.post(progressUrl, basicNameValuePairs);
        String resultStr = HttpClientUtil.post("http://localhost:8080/myapplication", basicNameValuePairs);
        logger.info("查询贷款详细信息返回数据" + resultStr);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        Application application = null;
        if (jsonObject != null && ErrorCodeEnum.ERROR_CODE_01.getCode().equals(jsonObject.getString("error_code"))) {
            List<Application> result = JSONArray.parseArray(jsonObject.getString("result"), Application.class);
            if(!result.isEmpty()){
                 application = result.get(0);
            }

        }
        model.addAttribute("progress", application);
        return "loan-progress";
    }


}
