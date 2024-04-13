package com.gugu.demo.util.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Administrator
 * @Classname JMHSample_05_StateFixtures
 * @Description TODO
 * @Date 2022/2/28 23:06
 */
@State(Scope.Thread)
public class JMHSample_05_StateFixtures {
    double x ;

    @Setup
    public void prepare(){
        x = Math.PI;
    }

    @TearDown
    public void check(){
        assert x > Math.PI : "Nothing changed?";
    }

    @Benchmark
    public void measureRight(){
        x++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_05_StateFixtures.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .build();
        new Runner(opt).run();
    }
}
