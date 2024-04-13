package com.gugu.demo.util.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Classname JMHSample_26_BatchSize
 * @Description TODO
 * @Date 2022/2/28 23:33
 */
@State(Scope.Thread)
public class JMHSample_26_BatchSize {
    List<String> list = new ArrayList<>();

    // 每个iteration中做5000次Invocation
    @Benchmark
    @Warmup(iterations = 5, batchSize = 5000)
    @Measurement(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<String> measureRight(){
        list.add(list.size() / 2, "something");
        return list;
    }

    @Setup(Level.Invocation)
    public void setup(){
        list.clear();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_26_BatchSize.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    private int add2(int x1, int x2){
        return x1 + x2;
    }

    private int add4(int x1, int x2, int x3, int x4){
        return x1 + x2 + x3 + x4;
    }
}
