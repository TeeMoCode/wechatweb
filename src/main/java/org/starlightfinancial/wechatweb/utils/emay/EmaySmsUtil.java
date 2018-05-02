package org.starlightfinancial.wechatweb.utils.emay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.starlightfinancial.wechatweb.domain.EmaySmsMessage;
import org.starlightfinancial.wechatweb.utils.emay.inter.http.v1.dto.request.SmsSingleRequest;
import org.starlightfinancial.wechatweb.utils.emay.inter.http.v1.dto.response.SmsResponse;
import org.starlightfinancial.wechatweb.utils.emay.util.AES;
import org.starlightfinancial.wechatweb.utils.emay.util.GZIPUtils;
import org.starlightfinancial.wechatweb.utils.emay.util.JsonHelper;
import org.starlightfinancial.wechatweb.utils.emay.util.http.*;

import java.util.HashMap;
import java.util.Map;

public class EmaySmsUtil {

    private static final Logger log = LoggerFactory.getLogger(EmaySmsUtil.class);

    /**
     * 发送单条短信
     *
     * @param isGzip 是否压缩
     */
    public static void sendSingleSms(String appId, String secretKey, String host, String algorithm, String content, String customSmsId, String extendCode, String mobile, boolean isGzip, String encode) {
        log.info("=============begin setSingleSms==================");
        SmsSingleRequest pamars = new SmsSingleRequest();
        pamars.setContent(content);
        pamars.setCustomSmsId(customSmsId);
        pamars.setExtendedCode(extendCode);
        pamars.setMobile(mobile);
        ResultModel result = request(appId, secretKey, algorithm, pamars, "http://" + host + "/inter/sendSingleSMS", isGzip, encode);
        log.info("result code :" + result.getCode());
        if ("SUCCESS".equals(result.getCode())) {
            SmsResponse response = JsonHelper.fromJson(SmsResponse.class, result.getResult());
            if (response != null) {
                log.info("data : " + response.getMobile() + "," + response.getSmsId() + "," + response.getCustomSmsId());
            }
        }
        log.info("=============end setSingleSms==================");
    }


    /**
     * 发送单条短信
     */
    public static void sendSingleSms(EmaySmsMessage emaySmsMessage) {
        log.info("=============begin sendSingleSms==================");
        SmsSingleRequest pamars = new SmsSingleRequest();
        pamars.setContent(emaySmsMessage.getContent());
        pamars.setCustomSmsId(null);
        pamars.setExtendedCode(null);
        pamars.setMobile(emaySmsMessage.getMobile());
        ResultModel result = request(emaySmsMessage.getAppId(), emaySmsMessage.getSecretKey(), emaySmsMessage.getAlgorithm(), pamars,
                "http://" + emaySmsMessage.getHost() + "/inter/sendSingleSMS", emaySmsMessage.isGzip(), emaySmsMessage.getEncode());
        log.info("result code :" + result.getCode());
        if ("SUCCESS".equals(result.getCode())) {
            SmsResponse response = JsonHelper.fromJson(SmsResponse.class, result.getResult());
            if (response != null) {
                log.info("data : " + response.getMobile() + "," + response.getSmsId() + "," + response.getCustomSmsId());
            }
        }else{
            log.info("发送短信失败,接收短信的号码:"+emaySmsMessage.getMobile()+",emay返回的信息:"+result);
        }
        log.info("=============end sendSingleSms==================");
    }


    /**
     * 公共请求方法
     */
    public static ResultModel request(String appId, String secretKey, String algorithm, Object content, String url, final boolean isGzip, String encode) {
        Map<String, String> headers = new HashMap<String, String>();
        EmayHttpRequestBytes request = null;
        try {
            headers.put("appId", appId);
            headers.put("encode", encode);
            String requestJson = JsonHelper.toJsonString(content);
            log.info("result json: " + requestJson);
            byte[] bytes = requestJson.getBytes(encode);
            log.info("request data size : " + bytes.length);
            if (isGzip) {
                headers.put("gzip", "on");
                bytes = GZIPUtils.compress(bytes);
                log.info("request data size [com]: " + bytes.length);
            }
            byte[] parambytes = AES.encrypt(bytes, secretKey.getBytes(), algorithm);
            log.info("request data size [en] : " + parambytes.length);
            request = new EmayHttpRequestBytes(url, encode, "POST", headers, null, parambytes);
        } catch (Exception e) {
            log.error("加密异常", e);
            e.printStackTrace();
        }
        EmayHttpClient client = new EmayHttpClient();
        String code = null;
        String result = null;
        try {
            EmayHttpResponseBytes res = client.service(request, new EmayHttpResponseBytesPraser());
            if (res == null) {
                log.error("请求接口异常");
                return new ResultModel(code, result);
            }
            if (res.getResultCode().equals(EmayHttpResultCode.SUCCESS)) {
                if (res.getHttpCode() == 200) {
                    code = res.getHeaders().get("result");
                    if ("SUCCESS".equals(code)) {
                        byte[] data = res.getResultBytes();
                        log.info("response data size [en and com] : " + data.length);
                        data = AES.decrypt(data, secretKey.getBytes(), algorithm);
                        if (isGzip) {
                            data = GZIPUtils.decompress(data);
                        }
                        log.info("response data size : " + data.length);
                        result = new String(data, encode);
                        log.info("response json: " + result);
                    }
                } else {
                    log.error("请求接口异常,请求码:" + res.getHttpCode());
                }
            } else {
                log.error("请求接口网络异常:" + res.getResultCode().getCode());
            }
        } catch (Exception e) {
            log.error("解析失败", e);
            e.printStackTrace();
        }
        ResultModel re = new ResultModel(code, result);
        return re;
    }


}
