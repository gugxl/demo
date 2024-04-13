package com.gugu.demo.util.beanutil;


import net.sf.cglib.beans.BeanCopier;

/**
 * @author gugu
 * @Classname CglibBeanCopierPropertiesCopier
 * @Description TODO
 * @Date 2022/7/24 9:17
 */
public class CglibBeanCopierPropertiesCopier implements PropertiesCopier {
    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }
}
