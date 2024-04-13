package com.gugu.demo.util.bookmarks;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gugu
 * @Classname BookmarksDeal
 * @Description TODO
 * @Date 2023/4/30 17:12
 */
public class BookmarksDeal {
    static Set<String> url = new HashSet<>();
    static Set<String> print_url = new HashSet<>();
    static Set<String> names = new HashSet<>();
    static Set<String> print_names = new HashSet<>();
    static List<Element> rs = new ArrayList<>();

    @SneakyThrows
    public static void main(String[] args) {
        File input = new File("C:\\Users\\Administrator\\Desktop\\bookmarks_2024_4_11.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        int i = 0;
        for (Element element : doc.getElementsByTag("a")) {
            String href = element.attr("href");
            String name = null;
            if (element.childNodeSize() > 0) {
                name = element.childNode(0).outerHtml();
            }

            if (url.contains(href)) {
                if (!print_url.contains(href)){
                    i++;
                    System.out.println(href);
                }else {
                    i++;
                    print_url.add(href);
                }
            } else if (!StringUtils.isEmpty(name) && names.contains(name)) {
                if (!print_names.contains(name)){
                    i++;
                    System.out.println(name);
                }else {
                    print_names.add(name);
                }
            } else {
                url.add(href);
                names.add(name);
            }
        }

        System.out.println(i);
//        String s = doc.html();
//        File f = new File("C:\\Users\\Administrator\\Desktop\\b2.html");
//        f.createNewFile();
//        FileWriter fw = new FileWriter(f.getAbsoluteFile());
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(s);
//        bw.close();

    }
}
