package ru.denisdyakin.models;

public class Book {

    private long isn;
    private String name;
    private String author;
    private String user_name;

    public Book(long isn, String name, String author, String user_name) {
        this.isn = isn;
        this.name = name;
        this.author = author;
        this.user_name = user_name;
    }

    public long getIsn() {
        return isn;
    }

    public void setIsn(long isn) {
        this.isn = isn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return String.format("Book[isn = %s, name = %s, author = %s, user_name = %s]", String.valueOf(isn), name, author, user_name);
    }
}
