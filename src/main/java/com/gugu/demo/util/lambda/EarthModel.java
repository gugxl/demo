package com.gugu.demo.util.lambda;

import java.util.List;

/**
 * @author Administrator
 * @Classname EarthModel
 * @Description TODO
 * @Date 2021/8/28 7:23
 */
public class EarthModel {
    private TeaModel tea;
    private List<PersonModels> persons;

    public TeaModel getTea() {
        return tea;
    }

    public void setTea(TeaModel tea) {
        this.tea = tea;
    }

    public List<PersonModels> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonModels> persons) {
        this.persons = persons;
    }
}
