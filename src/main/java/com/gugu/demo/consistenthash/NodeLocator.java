package com.gugu.demo.consistenthash;

/**
 * @author gugu
 * @Classname NodeLocator
 * @Description TODO
 * @Date 2022/9/3 16:27
 */
public interface NodeLocator {
    MemcachedNode getPrimary(String k);
}
