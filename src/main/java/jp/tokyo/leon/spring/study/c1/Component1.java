package jp.tokyo.leon.spring.study.c1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


/**
 * @author leon
 * @date 2024/2/27 23:24
 */
@Component
public class Component1 {

    private static final Logger log = LoggerFactory.getLogger(Component1.class);

    @Autowired
    private ApplicationEventPublisher context;

    public void register() {
        log.info("用户注册");
        context.publishEvent(new UserRegisteredEvent(this));
    }
}
