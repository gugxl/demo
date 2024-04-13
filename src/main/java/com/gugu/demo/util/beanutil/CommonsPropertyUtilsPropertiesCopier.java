package com.gugu.demo.util.beanutil;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author gugu
 * @Classname CommonsPropertyUtilsPropertiesCopier
 * @Description TODO
 * @Date 2022/7/24 22:30
 */
public class CommonsPropertyUtilsPropertiesCopier implements PropertiesCopier{
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        PropertyUtils.copyProperties(target, source);
    }
}
