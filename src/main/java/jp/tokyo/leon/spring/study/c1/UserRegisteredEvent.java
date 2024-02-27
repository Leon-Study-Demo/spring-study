package jp.tokyo.leon.spring.study.c1;

import org.springframework.context.ApplicationEvent;

/**
 * @author leon
 * @date 2024/2/28 00:09
 */
public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
