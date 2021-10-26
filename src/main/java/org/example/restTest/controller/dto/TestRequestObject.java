package org.example.restTest.controller.dto;

import java.io.Serializable;

public class TestRequestObject implements Serializable {
    public String name;
    public String age;
    public String weburl;

    public TestRequestObject(String name,String age,String weburl){
        this.name = name;
        this.age = age;
        this.weburl = weburl;
    }
    @Override
    public String toString(){
        return name + " " + age + " " + weburl;
    }

}
