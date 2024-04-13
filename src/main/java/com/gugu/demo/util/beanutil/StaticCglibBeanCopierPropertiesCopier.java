package com.gugu.demo.util.beanutil;

import org.springframework.cglib.beans.BeanCopier;

/**
 * @author gugu
 * @Classname StaticCglibBeanCopierPropertiesCopier
 * @Description TODO 静态copy 防止重复创建对象
 * @Date 2022/7/24 9:21
 */
public class StaticCglibBeanCopierPropertiesCopier implements PropertiesCopier {
    private static BeanCopier copier = BeanCopier.create(Account.class, Account.class, false);

    @Override
    public void copyProperties(Object source, Object target) throws Exception {
        copier.copy(source, target, null);
    }
}
