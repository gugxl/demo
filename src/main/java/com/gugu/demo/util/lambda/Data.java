package com.gugu.demo.util.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @Classname Data
 * @Description TODO
 * @Date 2021/8/18 23:35
 */
public class Data {
    private static List<PersonModel> list = null;
    static {
        PersonModel zang = new PersonModel("zang shan",18,"男");
        PersonModel li = new PersonModel("li si",19,"女");
        PersonModel wang = new PersonModel("wang wu",20,"男");
        PersonModel zhao = new PersonModel("zhao liu",21,"女");
        PersonModel gugu = new PersonModel("gu gu",22,"女");
        list = Arrays.asList(zang, li, wang, zhao, gugu);
    }

    public static List<PersonModel> getData(){
        return list;
    }


}
