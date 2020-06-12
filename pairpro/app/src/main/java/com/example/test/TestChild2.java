package com.example.test;

public class TestChild2 extends TestObject{
    private String keyboard;

    public TestChild2(String name, String keyboard) {
        super(name);
        this.keyboard = keyboard;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }
}
