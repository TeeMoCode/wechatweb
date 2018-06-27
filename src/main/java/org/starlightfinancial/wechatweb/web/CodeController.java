package org.starlightfinancial.wechatweb.web;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starlightfinancial.wechatweb.EmayConfig;
import org.starlightfinancial.wechatweb.domain.EmaySmsMessage;
import org.starlightfinancial.wechatweb.enums.ConstantsEnum;
import org.starlightfinancial.wechatweb.utils.CheckCodeGenerator;
import org.starlightfinancial.wechatweb.utils.WebResultModel;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * 验证码管理Controller
 *
 * @author senlin.deng
 */
@Controller
@RequestMapping("/code")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmayConfig emayConfig;

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

    }

    /**
     * 发送短信验证码
     */
    @RequestMapping("/getSmsCode")
    @ResponseBody
    public WebResultModel getSmsCode(String mobile, HttpSession session) throws Exception {
        if (StringUtils.isEmpty(mobile)){
            return WebResultModel.fail("手机号码不能为空");
        }
        String smsCode = stringRedisTemplate.opsForValue().get(mobile);
        Long expire = 0L;
        //获得过期时间
        if (StringUtils.isNotBlank(smsCode)) {
            expire = stringRedisTemplate.getExpire(mobile);
        }
        //如果验证码是空或者过期时间小于30s,重新生成短信验证码
        if (StringUtils.isBlank(smsCode) || expire <= 30) {
            smsCode = RandomStringUtils.randomNumeric(6);
            //过期时间设置为3.5分钟,提醒用户有效期为3分钟
            stringRedisTemplate.opsForValue().set(mobile, smsCode, 60 * 7 / 2, TimeUnit.SECONDS);
        }
        EmaySmsMessage smsMessage = new EmaySmsMessage();
        smsMessage.setSmsCode(smsCode);
        smsMessage.setMobile(mobile);
        smsMessage.setAppId(emayConfig.getAppId());
        smsMessage.setSecretKey(emayConfig.getSecretKey());
        smsMessage.setAlgorithm(emayConfig.getAlgorithm());
        smsMessage.setEncode(emayConfig.getEncode());
        smsMessage.setHost(emayConfig.getHost());
        System.out.println(smsCode);
//        EmaySmsUtil.sendSingleSms(smsMessage);
        session.setAttribute("isSendSms", true);
        return WebResultModel.success();
    }

    /**
     * 对图形验证码进行验证
     *
     * @param checkCode
     * @param session
     * @return
     */
    @RequestMapping("/verifyCheckCode")
    @ResponseBody
    public WebResultModel verifyCheckCode(String checkCode, HttpSession session) {
        String checkCodeOnServer = (String) session.getAttribute("checkCode");
        WebResultModel resultModel = new WebResultModel();
        if (checkCode.equalsIgnoreCase(checkCodeOnServer)) {
            resultModel.setCode(ConstantsEnum.REQUESTSUCCESS.getCode());
        } else {
            resultModel.setCode(ConstantsEnum.REQUESTFAIL.getCode());
            resultModel.setMessage("图形验证码输入错误,请重新输入");
        }
        return resultModel;
    }

    /**
     * 校验短信验证码
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    @RequestMapping("/verifySmsCode")
    @ResponseBody
    public WebResultModel checkSmsCode(String mobile, String smsCode) {
        String smsCodeCache = stringRedisTemplate.opsForValue().get(mobile);
        WebResultModel resultModel = null;
        if (smsCode.equals(smsCodeCache)) {
            resultModel = WebResultModel.success();
        } else {
            resultModel = WebResultModel.fail("手机验证码错误");
        }
        return resultModel;
    }


}
