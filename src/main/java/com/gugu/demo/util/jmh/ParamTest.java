package com.gugu.demo.util.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Administrator
 * @Classname ParamTest
 * @Description TODO
 * @Date 2022/2/28 23:15
 */
@State(Scope.Benchmark)
public class ParamTest {
    @Param({"1","2","3"})
    int testNum;

    @Benchmark
    public String test(){
        return String.valueOf(testNum);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ParamTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
