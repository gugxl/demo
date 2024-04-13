package com.gugu.demo.util.redis;

import java.util.Map;

/**
 * @author Administrator
 * @Classname ITaskStore
 * @Description TODO
 * @Date 2021/11/21 12:51
 */
public interface ITaskStore {
    /**
     * @Description 存储器中任务列表版本号
     * @params
     * @return long
     * @auther Administrator
     */
    
    public long getRemoteVersion();

    /**
     * @Description 获取所有的任务触发器
     * @params
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @auther Administrator
     */
    
    public Map<String, String> getAllTriggers();

    /**
     * @Description 保存所有的任务触发器
     * @params 
     * @param version
     * @param triggers
     * @return void
     * @auther Administrator
     */
    
    public void saveAllTriggers(long version, Map<String, String> triggers);

    /**
     * @Description 抢占任务（是否可以抢到任务）
     * @params 
     * @param name
     * @return boolean
     * @auther Administrator
     */
    
    public boolean grabTask(String name);
}
