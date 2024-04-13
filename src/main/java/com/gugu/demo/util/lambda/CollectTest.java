package com.gugu.demo.util.lambda;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://mp.weixin.qq.com/s/8IrQbU-No8E15xhKw-nwWg
 */
public class CollectTest {

    private static List<UserDTO> getUserList() {
        UserDTO userDTO = new UserDTO("小冬", 18, "男", false);
        UserDTO userDTO2 = new UserDTO("小秋", 30, "男", true);
        UserDTO userDTO3 = new UserDTO("春", 18, "女", true);
        List<UserDTO> userList = Lists.newArrayList(userDTO, userDTO2, userDTO3);
        return userList;
    }

    public static void main(String[] args) {
        // list2Set();
        // list2Array();
        multiStageGroup();
    }

    private static void multiStageGroup() {
        List<UserDTO> userList = getUserList();
        // 先按照年龄再按照性别分组
        Map<Integer, Map<String, List<UserDTO>>> result = userList.stream().collect(Collectors.groupingBy(
            UserDTO::getAge, Collectors.groupingBy(
                UserDTO::getSex
            )));
        System.out.println(result);

    }

    private static void list2Array() {
        List<UserDTO> userList = getUserList();
        Object[] objects = userList.stream().toArray();
        UserDTO[] userDTOS = userList.stream().toArray(UserDTO[]::new);

        System.out.println(objects);
        System.out.println(userDTOS);
    }

    private static void list2Set() {
        List<UserDTO> userList = getUserList();
        Set<UserDTO> userDTOHashSet = userList.stream().collect(Collectors.toCollection(HashSet::new));
        Set<UserDTO> userDTOHashSet2 = userList.stream().collect(Collectors.toSet());
        System.out.println(userDTOHashSet);
        System.out.println(userDTOHashSet2);
    }


    @Data
    @AllArgsConstructor
    static class UserDTO {
        private String name;
        private Integer age;
        private String sex;
        private Boolean hasOrientation;
    }
}
