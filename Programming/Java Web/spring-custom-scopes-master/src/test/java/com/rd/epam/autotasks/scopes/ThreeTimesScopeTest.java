package com.rd.epam.autotasks.scopes;

import com.rd.epam.autotasks.scopes.config.ThreeTimesScopeTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class ThreeTimesScopeTest {

    @Test
    void testTenThreeTimesBeans() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreeTimesScopeTestConfig.class);
        final Object[] beans = Stream.generate(() -> context.getBean("threeTimesBean1"))
                .limit(10)
                .toArray(Object[]::new);

        assertSame(beans[0], beans[1]);
        assertSame(beans[0], beans[2]);

        assertSame(beans[3], beans[4]);
        assertSame(beans[3], beans[5]);

        assertSame(beans[6], beans[7]);
        assertSame(beans[6], beans[8]);

        assertNotSame(beans[0], beans[3]);
        assertNotSame(beans[3], beans[6]);
        assertNotSame(beans[6], beans[9]);
    }


    @Test
    void testDifferentThreeTimesBeans() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreeTimesScopeTestConfig.class);
        final Object[] beans1 = Stream.generate(() -> context.getBean("threeTimesBean1"))
                .limit(4)
                .toArray(Object[]::new);
        final Object[] beans2 = Stream.generate(() -> context.getBean("threeTimesBean2"))
                .limit(2)
                .toArray(Object[]::new);

        assertSame(beans1[0], beans1[1]);
        assertSame(beans1[0], beans1[2]);
        assertNotSame(beans1[0], beans1[3]);

        assertSame(beans2[0], beans2[1]);

        for (Object bean1 : beans1) {
            for (Object bean2 : beans2) {
                assertNotSame(bean1, bean2);
            }
        }
    }
}
