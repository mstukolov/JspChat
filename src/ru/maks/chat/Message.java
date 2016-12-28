package ru.maks.chat;

/**
 * Created by Maxim on 09.12.2016.
 */
public class Message {
    private String personName = null;
    private String message = null;
    private String timeStamp;
    private String color = null;

    public Message(String personName, String message, String timeStamp) {
        this.personName = personName;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Message(String personName, String message, String timeStamp, String color) {
        this.personName = personName;
        this.message = message;
        this.timeStamp = timeStamp;
        this.color = color;
    }

    public String getPersonName() {
        return personName;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getColor() {
        return color;
    }
}
