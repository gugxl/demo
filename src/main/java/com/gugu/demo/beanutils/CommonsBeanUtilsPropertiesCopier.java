package com.gugu.demo.beanutils;

/**
 * @author gugu
 * @Classname CommonsBeanUtilsPropertiesCopier
 * @Description TODO
 * @Date 2022/7/24 22:03
 */
public class CommonsBeanUtilsPropertiesCopier implements PropertiesCopier  {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
    }
}
