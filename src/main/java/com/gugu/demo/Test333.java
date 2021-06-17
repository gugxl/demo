package com.gugu.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.atomic.AtomicInteger;

import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.replace;

/**
 * @author Administrator
 * @Classname Test333
 * @Description TODO
 * @Date 2021/6/2 7:06
 */
@PrepareForTest(Integer.class)
@RunWith(PowerMockRunner.class)
public class Test333 {
    @Before
    public void before(){
        AtomicInteger value = new AtomicInteger(1);
        replace(method(Integer.class, "intValue")).with((proxy, method, args) -> value.getAndIncrement());
    }

    @Test
    public void test() {

        Integer a = 1;
        if( a == 1 && a == 2 && a == 3 ){
            System.out.println("Success");
        } else {
            Assert.fail("(a == 1 && a == 2 && a == 3) != true , a =" + a.intValue());
        }
    }
}
