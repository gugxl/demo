package com.gugu.demo.util.lambda;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @Classname OptionalDemo
 * @Description TODO
 * @Date 2021/8/20 20:54
 */
public class OptionalDemo {
    public static void main(String[] args) throws Exception{
        PersonModel personModel = new PersonModel();

        Optional<PersonModel> personModel1 = Optional.of(personModel); // Optional.of 参数不能为null，会空指针
        System.out.println(personModel1.isPresent()?personModel1.get():"-");

        Optional<String> name = Optional.ofNullable(personModel.getName()); // Optional.ofNullable 参数可以为空
        System.out.println(name.isPresent()?name.get():"-");

        Optional.ofNullable("test").ifPresent(na -> {
            System.out.println(na+"ifPresent");
        });

        System.out.println(Optional.ofNullable(null).orElse("-"));
        System.out.println(Optional.ofNullable("1").orElse("-"));

        System.out.println(Optional.ofNullable(null).orElseGet(()->{return "hahaha";}));
        System.out.println(Optional.ofNullable("1").orElseGet(()->{return "hahaha";}));

        try {
            System.out.println(Optional.ofNullable(null).orElseThrow(()->{
                throw new RuntimeException("new exception");
            }));
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }

        EarthModel earthModel = new EarthModel();

        System.out.println(Optional.ofNullable(earthModel).map(EarthModel::getTea).map(TeaModel::getName).isPresent());

        Optional.ofNullable(earthModel).map(EarthModel::getPersons).map(per -> per.stream().map(PersonModels::getName)
                .collect(Collectors.toList())).ifPresent(per -> System.out.println(per));
    }
}
