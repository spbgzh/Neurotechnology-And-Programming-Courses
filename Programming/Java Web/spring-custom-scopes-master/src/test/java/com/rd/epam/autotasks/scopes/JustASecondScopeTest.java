package com.rd.epam.autotasks.scopes;

import com.rd.epam.autotasks.scopes.config.JustASecondScopeTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class JustASecondScopeTest {

    @Test
    void testTenThreeTimesBeans() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JustASecondScopeTestConfig.class);

        final Random rand = new Random();
        final Object[] beans11 = Stream.generate(() -> context.getBean("jasBean1"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);
        final Object[] beans12 = Stream.generate(() -> context.getBean("jasBean2"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);

        Thread.sleep(100);

        final Object[] beans21 = Stream.generate(() -> context.getBean("jasBean1"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);
        final Object[] beans22 = Stream.generate(() -> context.getBean("jasBean2"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);

        Thread.sleep(100);

        final Object[] beans31 = Stream.generate(() -> context.getBean("jasBean1"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);
        final Object[] beans32 = Stream.generate(() -> context.getBean("jasBean2"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);

        Thread.sleep(900);

        final Object[] beans41 = Stream.generate(() -> context.getBean("jasBean1"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);
        final Object[] beans42 = Stream.generate(() -> context.getBean("jasBean2"))
                .limit(5 + rand.nextInt(10))
                .toArray(Object[]::new);

        for (Object bean : beans11) {
            assertSame(bean, beans11[0]);
        }
        for (Object bean : beans12) {
            assertSame(bean, beans12[0]);
        }
        for (Object bean : beans21) {
            assertSame(bean, beans21[0]);
        }
        for (Object bean : beans22) {
            assertSame(bean, beans22[0]);
        }
        for (Object bean : beans31) {
            assertSame(bean, beans31[0]);
        }
        for (Object bean : beans32) {
            assertSame(bean, beans32[0]);
        }
        for (Object bean : beans41) {
            assertSame(bean, beans41[0]);
        }
        for (Object bean : beans42) {
            assertSame(bean, beans42[0]);
        }


        for (Object b11 : beans11) {
            for (Object b21 : beans21) {
                assertSame(b11, b21);
            }
        }
        for (Object b11 : beans11) {
            for (Object b31 : beans31) {
                assertSame(b11, b31);
            }
        }
        for (Object b12 : beans12) {
            for (Object b22 : beans22) {
                assertSame(b12, b22);
            }
        }
        for (Object b12 : beans12) {
            for (Object b32 : beans32) {
                assertSame(b12, b32);
            }
        }

        for (Object b11 : beans11) {
            for (Object b41 : beans41) {
                assertNotSame(b11, b41);
            }
        }
        for (Object b12 : beans12) {
            for (Object b42 : beans42) {
                assertNotSame(b12, b42);
            }
        }


        for (Object b11 : beans11) {
            for (Object b12 : beans12) {
                assertNotSame(b11, b12);
            }
        }
        for (Object b41 : beans41) {
            for (Object b42 : beans42) {
                assertNotSame(b41, b42);
            }
        }
    }

}
