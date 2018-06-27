package org.starlightfinancial.wechatweb.utils.emay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.GenericTypeResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: Senlin.Deng
 * @Description:
 * @date: Created in 2018/6/14 16:16
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback
public class MyTest {




    @Test
    public  void request(){
        ObjectPostProcessor<FilterSecurityInterceptor> objectPostProcessor = new ObjectPostProcessor<FilterSecurityInterceptor>() {
            /**
             * Initialize the object possibly returning a modified instance that should be used
             * instead.
             *
             * @param object the object to initialize
             * @return the initialized version of the object
             */
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setSecurityMetadataSource(new DefaultFilterInvocationSecurityMetadataSource(null));
                return null;
            }
        };

        Class<?> oppClass = objectPostProcessor.getClass();
        Class<?> oppType = GenericTypeResolver.resolveTypeArgument(oppClass,
                ObjectPostProcessor.class);
        System.out.println(oppType);


    }
}
