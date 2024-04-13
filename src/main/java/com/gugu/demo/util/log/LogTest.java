package com.gugu.demo.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gugu
 * @Classname LogTest
 * @Description TODO
 * @Date 2022/9/12 8:05
 */
public class LogTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        LOGGER.debug("Processing trade with id:[{}] and symbol : [{}] ", "id", "symbol");

    }

}
