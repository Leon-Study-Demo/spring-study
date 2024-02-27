package jp.tokyo.leon.spring.study.c1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author leon
 * @date 2024/2/27 23:24
 */
@Component
public class Component2 {
    private static final Logger log = LoggerFactory.getLogger(Component2.class);

    @EventListener
    public void hear(UserRegisteredEvent event) {
        log.info("发送短信");
    }

}
