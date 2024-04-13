package com.gugu.demo.util.lambda;


import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * https://mp.weixin.qq.com/s?__biz=MzkzNTM1MDI4MQ==&mid=2247497730&idx=3&sn=d5b2c99e795700f962b563e7fb6371ec&source=41#wechat_redirect
 */
public class StreamAPITest {

    @Test
    public void tsetIntermediate() {
        IntStream.range(1, 10)
            .peek(x -> System.out.print("\nA" + x))
            .limit(3)
            .peek(x -> System.out.print("B" + x))
            .forEach(x -> System.out.print("C" + x));
    }

    @Test
    public void tsetIntermediate2() {
        IntStream.range(1, 10)
            .peek(x -> System.out.print("\nA" + x))
            .skip(6)
            .peek(x -> System.out.print("B" + x))
            .forEach(x -> System.out.print("C" + x));
    }
}
