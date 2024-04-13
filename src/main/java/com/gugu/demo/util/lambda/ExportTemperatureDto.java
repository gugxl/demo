package com.gugu.demo.util.lambda;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ExportTemperatureDto {
    private String name;
    private Double morningTemperature;
    private Double afternoonTemperature;
    private String classId;
    private String gradeId;
    private Integer personId;

    public ExportTemperatureDto(Integer personId, String name) {
        this.name = name;
        this.personId = personId;
    }

    public static void main(String[] args) {
        List<ExportTemperatureDto> temperatureList = Lists.newArrayList();
        temperatureList.add(new ExportTemperatureDto(1, "haha"));
        temperatureList.add(new ExportTemperatureDto(2, "haha"));
        temperatureList.add(new ExportTemperatureDto(3, "haha"));
        temperatureList.add(new ExportTemperatureDto(4, "haha"));

        temperatureList.add(new ExportTemperatureDto(1, "hahaasdas"));
        temperatureList.add(new ExportTemperatureDto(2, "hahaasdas"));
        ArrayList<ExportTemperatureDto> result =
            temperatureList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(ExportTemperatureDto::getPersonId))), ArrayList::new));
        result.forEach(System.out::println);
    }
}
