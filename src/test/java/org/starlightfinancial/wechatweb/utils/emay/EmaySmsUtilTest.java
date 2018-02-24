package org.starlightfinancial.wechatweb.utils.emay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.starlightfinancial.wechatweb.EmayConfig;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
@Rollback
public class EmaySmsUtilTest {

    @Autowired
    private EmayConfig emayConfig;

    @Test
    public void request() {
        // appId
        String appId = emayConfig.getAppId();
        // 密钥
        String secretKey =emayConfig.getSecretKey();
        // 接口地址
        String host = emayConfig.getHost();
        // 加密算法
        String algorithm = "AES/ECB/PKCS5Padding";
        // 编码
        String encode = "UTF-8";
        // 是否压缩
        boolean isGizp = false;

        EmaySmsUtil.sendSingleSms(appId,secretKey,host,algorithm,"【润通小贷】新接口测试", null, null, "17784319480",isGizp,encode);





    }
}