package com.API.pojos;

public class TodosPojo {

    /*
 Request body{
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false

     */

    // 1- degiskenler private yapilir

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // 2- bu degerlere ulasabilmek icin public getter ve setter methodlar olusturulur
    //    actualData lara buradaki getter() lar üzerinden ulasiriz


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 3- parametreli ve parametresiz constructor olusturulur

    public TodosPojo() {
    }

    public TodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;

    }


    // 4- toString methodu olusturulur


    @Override
    public String toString() {
        return "TodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}