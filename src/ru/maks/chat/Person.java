package ru.maks.chat;

/**
 * Created by Maxim on 09.12.2016.
 */
public class Person {
    private String name = null;
    private String color = null;
    private long loginTime = -1;

    public Person(String name, String color, long loginTime) {
        this.name = name;
        this.color = color;
        this.loginTime = loginTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }
}
