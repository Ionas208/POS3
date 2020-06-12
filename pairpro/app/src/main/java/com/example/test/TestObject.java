package com.example.test;

import android.net.Uri;

import java.io.Serializable;

public class TestObject implements Serializable {
    private String name;
    private static final long serialVersionUID = 1234567890L;

    public TestObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TestObject reader(String line){
        String[] tokens = line.split(";");
        String name = tokens[1];
        if(tokens[0].equals("1")){
            Uri uri = Uri.parse(tokens[2]);
            return new TestChild1(name, uri);
        }
        else{
            String keyboard = tokens[2];
            return new TestChild2(name, keyboard);
        }
    }
}
