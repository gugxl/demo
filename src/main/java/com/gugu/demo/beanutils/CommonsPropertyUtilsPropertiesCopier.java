package com.gugu.demo.beanutils;

/**
 * @author gugu
 * @Classname CommonsPropertyUtilsPropertiesCopier
 * @Description TODO
 * @Date 2022/7/24 22:30
 */
public class CommonsPropertyUtilsPropertiesCopier implements PropertiesCopier{
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        org.apache.commons.beanutils.PropertyUtils.copyProperties(target, source);
    }
}
