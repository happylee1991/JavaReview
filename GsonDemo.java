package com.lee.demo;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class GsonDemo {
    public static void main(String[] args) {
        gson04();

    }
    /*
    * 设置json的dataformat
    * */
    private static void gson04() {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd");
        Gson gson = gsonBuilder.create();
    }

    private static void gson03() {
        File file = new File(GsonDemo.class.getResource("wxer.json").getFile());
        try {
            String jsonStr=FileUtils.readFileToString(file, "utf-8");
            Gson gson = new Gson();
            Diaos diaosi = gson.fromJson(jsonStr, Diaos.class);

            System.out.println(diaosi.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void gson02() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy() {
            public String translateName(Field field) {
                if (field.getName().charAt(0) > 'a' && field.getName().charAt(0) < 'z') {
                    return field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                } else {
                    return field.getName();
                }

            }
        });
        Gson gson = gsonBuilder.create();
        Diaos diaos = new Diaos();
        diaos.setName("赵闪闪");
        diaos.setAge("25");
        diaos.setAihao(new String[]{"泡面", "小电影", "D8"});
        diaos.setDesc("绝代屌丝");
        System.out.println(gson.toJson(diaos));



    }

    private static void gson01() {
        Gson gson = new Gson();

        Diaos diaos = new Diaos();
        diaos.setName("赵闪闪");
        diaos.setAge("25");
        diaos.setAihao(new String[]{"泡面", "小电影", "D8"});
        diaos.setDesc("绝代屌丝");
        System.out.println(gson.toJson(diaos));
    }
}
