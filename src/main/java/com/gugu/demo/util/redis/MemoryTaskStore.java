package com.gugu.demo.util.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Classname MemoryTaskStore
 * @Description TODO
 * @Date 2021/11/21 20:51
 */
public class MemoryTaskStore implements ITaskStore {

    private Map<String, String> triggers = new HashMap<String, String>();
    private long version;

    @Override
    public long getRemoteVersion() {
        return version;
    }

    @Override
    public Map<String, String> getAllTriggers() {
        return triggers;
    }

    @Override
    public void saveAllTriggers(long version, Map<String, String> triggers) {
        this.triggers = triggers;
        this.version = version;
    }

    @Override
    public boolean grabTask(String name) {
        return true;
    }
}
