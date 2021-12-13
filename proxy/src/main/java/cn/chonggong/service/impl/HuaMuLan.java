package cn.chonggong.service.impl;

import cn.chonggong.service.Girl;

/**
 * @author wenguanghua
 * @since 2021-05-21 10:34
 */
public class HuaMuLan implements Girl {
    private String name;
    private int age;
    public void date(String name) {
        System.out.println("与花木兰约会中。。。。");
    }

    public void watchMovie() {
        System.out.println("正在与花木兰看电影。。。。。");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "HuaMuLan{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
