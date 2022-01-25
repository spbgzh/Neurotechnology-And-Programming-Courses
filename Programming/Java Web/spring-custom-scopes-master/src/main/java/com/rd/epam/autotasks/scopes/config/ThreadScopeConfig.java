package com.rd.epam.autotasks.scopes.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;

import java.util.*;

@Configuration
public class ThreadScopeConfig {
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new BeanFactoryPostProcessor() {
            class ThreadScope implements Scope {
                private final ThreadLocal<Map<String, Object>> threadScope =
                        new NamedThreadLocal<>(ThreadScope.class.getName()) {
                            @Override
                            protected Map<String, Object> initialValue() {
                                return new HashMap<>();
                            }
                        };

                @Override
                public Object get(String name, ObjectFactory<?> objectFactory) {
                    Map<String, Object> scope = this.threadScope.get();
                    Object object = scope.get(name);
                    if (object == null) {
                        object = objectFactory.getObject();
                        scope.put(name, object);
                    }
                    return object;
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
                configurableListableBeanFactory.registerScope("thread", new ThreadScope());
            }
        };
    }
}
