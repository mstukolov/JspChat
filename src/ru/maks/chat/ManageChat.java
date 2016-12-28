package ru.maks.chat;

import java.util.*;

/**
 * Created by Maxim on 07.12.2016.
 */
public class ManageChat {

    private static ManageChat instance;

    public static ManageChat getInstance(){
        if(instance == null){
            instance = new ManageChat();
        }
        return instance;
    }

    private int messages_size = 15;
    private List<Message> messages = new LinkedList<>();
    private Map<String, Person> activeUsers = new HashMap<>();

    public synchronized void addPerson(String name, String color) {
        Person person = null;
        switch (color){
            case "Красный": color = "red";break;
            case "Синий": color = "blue";break;
            case "Черный": color = "black";break;
            case "Зеленый": color = "green";break;
        }
        person = new Person(name, color, new java.util.Date().getTime());
        activeUsers.put(name, person);
    }
    public synchronized void exitPerson(String name){
        activeUsers.remove(name);
    }

    public Map<String, Person> getActiveUsers(){
        return activeUsers;
    }

    public synchronized void addMessage(Message msg)
    {
        if(messages.size()==messages_size)
        {
            ((LinkedList<Message>)messages).removeFirst();
        }
        messages.add(msg);
    }
    public ListIterator<Message> getMessages()
    {
        return messages.listIterator();
    }
}
