package com.gugu.demo.util.lambda;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * https://mp.weixin.qq.com/s/ipVKY43xQkYSnMOlK87JJg
 */
public class TreeTest {

    public static void main(String[] args) {
        // 模拟从数据库查询出来
        List<Menu> menus = Arrays.asList(
            new Menu(1, "根节点", 0),
            new Menu(2, "子节点1", 1),
            new Menu(3, "子节点1.1", 2),
            new Menu(4, "子节点1.2", 2),
            new Menu(5, "根节点1.3", 2),
            new Menu(6, "根节点2", 1),
            new Menu(7, "根节点2.1", 6),
            new Menu(8, "根节点2.2", 6),
            new Menu(9, "根节点2.2.1", 7),
            new Menu(10, "根节点2.2.2", 7),
            new Menu(11, "根节点3", 1),
            new Menu(12, "根节点3.1", 11));

        // 获取父节点
        List<Menu> collect = menus.stream().filter(m -> m.getParentId() == 0).map(
            (m) -> {
                m.setChildList(getChildrens(m, menus));
                return m;
            }
        ).collect(Collectors.toList());
        System.out.println("-------转json输出结果-------");
        System.out.println(JSON.toJSON(collect));

    }

    private static List<Menu> getChildrens(Menu root, List<Menu> all) {
        List<Menu> children = all.stream().filter(m ->
            Objects.equals(m.getParentId(), root.getId())
        ).map(
            (m) -> {
                m.setChildList(getChildrens(m, all));
                return m;
            }
        ).collect(Collectors.toList());
        return children;
    }


}

@Data
@Builder
class Menu {
    public Integer id;
    public String name;
    public Integer parentId;
    public List<Menu> childList;

    public Menu(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Menu(Integer id, String name, Integer parentId, List<Menu> childList) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childList = childList;
    }
}