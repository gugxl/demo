package com.gugu.demo.util.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Administrator
 * @Classname JMHSample_04_DefaultState
 * @Description TODO
 * @Date 2022/2/28 23:02
 */
@State(Scope.Thread)
public class JMHSample_04_DefaultState {
    double x = Math.PI;

    @Benchmark
    public void measure(){
        x++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_04_DefaultState.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
