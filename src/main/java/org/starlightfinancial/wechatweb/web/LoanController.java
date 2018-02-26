package org.starlightfinancial.wechatweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.domain.Loan;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @RequestMapping("/loan-history.do")
    public String loanHistory(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // TODO: 2018/2/26 根据用户信息查询历史贷款记录
        ArrayList<Loan> loans = new ArrayList<Loan>();
        for (int i = 0; i < 3; i++) {
            Loan loan = new Loan();
            loan.setApprovedAmount((10000 + i * 10000) + "");
            loan.setApprovedTerm("12个月");
            loan.setContractNo("JK" + i + "0000");
            loans.add(loan);
        }
        model.addAttribute("loanHistory", loans);
        return "loan-history";
    }

    @RequestMapping("/loan-detail.do")
    public String loanDetail(String contractNo, Model model) {
        // TODO: 2018/2/26 根据合同编号查询贷款具体信息
        Loan loan = new Loan();
        loan.setContractNo("JK123123");
        loan.setApprovedAmount("200000");
        loan.setApprovedTerm("12个月");
        loan.setSubject("张三");
        loan.setGuarantee("王五");
        loan.setRepaymentDate("15日");
        loan.setRepaymentStatus("正常");
        model.addAttribute("loan", loan);
        return "loan-detail";
    }


}
