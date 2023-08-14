package com.example.activitieslec3;

public class MyDataManager {
    private static MyDataManager instance;
    private String title;
    private int counter;
    public static MyDataManager getInstance(){
        if(instance==null){
            instance = new MyDataManager();
        }
        return instance;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getCounter(){
        return counter;
    }
    public void setCounter(int counter){
        this.counter = counter;
    }
}
