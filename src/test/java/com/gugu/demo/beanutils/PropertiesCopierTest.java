package com.gugu.demo.beanutils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author gugu
 * @Classname PropertiesCopierTest
 * @Description TODO
 * @Date 2022/7/24 22:45
 */
@RunWith(Parameterized.class)
public class PropertiesCopierTest {
    @Parameterized.Parameter(0)
    public PropertiesCopier propertiesCopier;

    // 测试次数
    private static List<Integer> testTimes = Arrays.asList(100, 1000, 10000, 100000, 1000000);
    private static StringBuilder resultBuilder = new StringBuilder("|实现|100|1,000|10,000|100,000|1,000,000|\r\n").append("|----|----|----|----|----|----|\r\n");

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new StaticCglibBeanCopierPropertiesCopier()});
        params.add(new Object[]{new CglibBeanCopierPropertiesCopier()});
        params.add(new Object[]{new SpringBeanUtilsPropertiesCopier()});
        params.add(new Object[]{new CommonsPropertyUtilsPropertiesCopier()});
        params.add(new Object[]{new CommonsBeanUtilsPropertiesCopier()});
        return params;
    }

    @Before
    public void setUp(){
        String name = this.propertiesCopier.getClass().getSimpleName().replace("PropertiesCopier", "");
        resultBuilder.append("|").append(name).append("|");
    }

    @Test
    public void copyProperties() throws Exception {
        Account source = new Account(1, "test1",10D);
        Account target = new Account();
        // 预热
        propertiesCopier.copyProperties(source, target);
        for (Integer testTime : testTimes) {
            long start = System.nanoTime();
            for (int j = 0; j < testTime; j++) {
                propertiesCopier.copyProperties(source, target);
            }
            resultBuilder.append((System.nanoTime() - start)/1_000_000).append("|");
        }
        resultBuilder.append("\r\n");
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("结果");
        System.out.println(resultBuilder);
    }

}
