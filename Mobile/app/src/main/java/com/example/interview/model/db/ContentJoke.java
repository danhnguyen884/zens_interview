package com.example.interview.model.db;

public class ContentJoke {
    int id;
    String contentText;
    int isLike;
    int isRead;

    public ContentJoke() {
    }

    public ContentJoke(String contentText, int isLike, int isRead) {
        this.contentText = contentText;
        this.isLike = isLike;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
