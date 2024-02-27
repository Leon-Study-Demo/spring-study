package jp.tokyo.leon.spring.study.c1;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

/**
 * @author leon
 * @date 2024/2/27 23:02
 *
 * Beanfactory 与 ApplicationContext 的区别
 */

@SpringBootApplication
public class A01Application {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        ConfigurableApplicationContext context = SpringApplication.run(A01Application.class, args);

        /*
            1. 到底什么是 BeanFactory
                - 它是 ApplicationContext 的父接口
                - 它才是 Spring 的核心容器，主要的 ApplicationContext 实现都「组合」了它的功能
         */

        System.out.println(context);

        /*
            2. BeanFactory 能干点啥
                - 表面上只有 getBean
                - 实际上控制反转、基本的依赖注入、直至 Bean 的生命周期的各种功能，都由它实现
         */
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.entrySet().stream().filter(e -> e.getKey().startsWith("component")).forEach(e -> System.out.println(e.getKey() + "=" + e.getValue()));

        /*
            3. ApplicationContext 比 BeanFactory 多点啥
         */

        System.out.println(context.getMessage("hi", null, Locale.CHINESE));
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
        System.out.println(context.getMessage("hi", null, Locale.JAPANESE));

        Resource[] resources = context.getResources("classpath:application.properties");
        Arrays.stream(resources).forEach(System.out::println);

        System.out.println(context.getEnvironment().getProperty("java_home"));
        System.out.println(context.getEnvironment().getProperty("server.port"));


        context.getBean(Component1.class).register();
    }
}
