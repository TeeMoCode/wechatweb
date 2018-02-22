package org.starlightfinancial.wechatweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starlightfinancial.wechatweb.utils.CheckCodeGenerator;
import org.starlightfinancial.wechatweb.utils.ResultModel;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * 验证码管理Controller
 */
@Controller
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 返回图形验证码
     *
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("/getCheckCode")
    public void getCheckCode(HttpServletResponse response, HttpSession session) throws Exception {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        CheckCodeGenerator checkCodeGenerator = new CheckCodeGenerator(120, 40, 4, 120);

        session.setAttribute("checkCode", checkCodeGenerator.getCode());
        checkCodeGenerator.write(response.getOutputStream());
        stringRedisTemplate.opsForValue().set("test","test",100, TimeUnit.SECONDS);

    }

    /**
     * 验证图形验证码
     *
     * @param checkCode
     * @param session
     * @return
     */
    @RequestMapping("/verifyCheckCode")
    @ResponseBody
    public ResultModel verifyCheckCode(String checkCode, HttpSession session) {
        String checkCodeOnServer = (String) session.getAttribute("checkCode");
        ResultModel resultModel = new ResultModel();
        if (checkCode.equals(checkCodeOnServer)) {
            resultModel.setCode("0000");
        } else {
            resultModel.setCode("0001");
            resultModel.setMessage("图形验证码输入错误,请刷新重试");
        }
        return resultModel;
    }


}
