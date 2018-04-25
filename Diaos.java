package com.lee.demo;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Date;

public class Diaos {


    private  String name;
    private String age;
    private String[] aihao;
    private String desc;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Diaos() {

    }


    public Diaos(String name, String age, String[] aihao, String desc) {
        this.name = name;
        this.age = age;
        this.aihao = aihao;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String[] getAihao() {
        return aihao;
    }

    public void setAihao(String[] aihao) {
        this.aihao = aihao;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Diaos{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", aihao=" + Arrays.toString(aihao) +
                ", desc='" + desc + '\'' +
                ",birthday='"+birthday+
                '}';
    }
}