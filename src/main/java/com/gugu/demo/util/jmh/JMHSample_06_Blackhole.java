package com.gugu.demo.util.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Administrator
 * @Classname JMHSample_06_Blackhole
 * @Description TODO
 * @Date 2022/2/28 23:25
 */
@State(Scope.Thread)
public class JMHSample_06_Blackhole {
    double x1 = Math.PI;
    double x2 = Math.PI;

    @Benchmark
    public double measureRight_1(){
        return  Math.log(x1) + Math.log(x2);
    }

    @Benchmark
    public void measureRight_2(Blackhole blackhole){
        blackhole.consume(x1);
        blackhole.consume(x2);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_06_Blackhole.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
