package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ThreeTimesScopeConfig {
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new BeanFactoryPostProcessor() {
            class ThreeTimesScope implements Scope {
                private final Map<String, Object> scopedObjects = new HashMap<>();
                private int callCounter = 0;

                @Override
                public Object get(String s, ObjectFactory<?> objectFactory) {
                    if (callCounter++ % 3 == 0) {
                        scopedObjects.put(s, objectFactory.getObject());
                    }
                    return scopedObjects.get(s);
                }

                @Override
                public Object remove(String s) {
                    return null;
                }

                @Override
                public void registerDestructionCallback(String s, Runnable runnable) {

                }

                @Override
                public Object resolveContextualObject(String s) {
                    return null;
                }

                @Override
                public String getConversationId() {
                    return null;
                }
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
                configurableListableBeanFactory.registerScope("threeTimes", new ThreeTimesScope());
            }
        };
    }

}
