package com.gugu.demo.util.beanutil;


import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gugu
 * @Classname CommonsBeanUtilsPropertiesCopier
 * @Description TODO
 * @Date 2022/7/24 22:03
 */
public class CommonsBeanUtilsPropertiesCopier implements PropertiesCopier  {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanUtils.copyProperties(target, source);
    }
}
