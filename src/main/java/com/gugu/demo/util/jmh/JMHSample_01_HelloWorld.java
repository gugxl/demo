package com.gugu.demo.util.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Classname JMHSample_01_HelloWorld
 * @Description JMH基准方法测试类
 * @Date 2022/2/25 23:32
 */

/**
 * @Description
 * @Warmup 系统预热参数
 * @Measurement
 * iterations 一般基准测试需要预热，表示预热次数
 * time 每次预热间隔
 * timeUnit 时间间隔
 */
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class JMHSample_01_HelloWorld {
    static class Demo{
        int id;
        String name;

        public Demo(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
    static List<Demo> demoList;
    static {
        demoList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            demoList.add(new Demo(i, "test"));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testHashMapWithoutSize(){
        Map map = new HashMap<>();
        for (Demo demo : demoList) {
            map.put(demo.id, demo.name);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testHashMap(){
        Map map = new HashMap((int)(demoList.size()/0.75f) + 1);
        for (Demo demo : demoList) {
            map.put(demo.id, demo.name);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHSample_01_HelloWorld.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }

}
