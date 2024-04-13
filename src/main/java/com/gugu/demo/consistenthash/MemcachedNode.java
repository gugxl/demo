package com.gugu.demo.consistenthash;

import java.net.SocketAddress;

/**
 * @author gugu
 * @Classname MemcachedNode
 * @Description TODO
 * @Date 2022/9/3 16:28
 */
public class MemcachedNode {
    private final SocketAddress socketAddress;

    public MemcachedNode(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
