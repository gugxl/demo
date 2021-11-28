package com.gugu.demo.self4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * @Classname TestSelf4j
 * @Description TODO
 * @Date 2021/11/28 16:58
 */
public class TestSelf4j {
    @Test
    public void testSelf4j(){
        Logger logger = LoggerFactory.getLogger(TestSelf4j.class);
        logger.error("testSelf4j");
    }
}
