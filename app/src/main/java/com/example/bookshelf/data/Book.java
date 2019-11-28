package com.example.bookshelf.data;

public class Book {
    public boolean isReaded;
    public String author;
    public String title;
    public String description;

    public String userTag;

    // Only readed books
    public String review;

    public Book(){
        this.author = "";
        this.title = "";
        this.description = "";
        this.review = "";
        this.isReaded = false;
    }


    public void setTitle(String string) {
        this.title = string;
    }

    public void setAuthor(String string) {
        this.author = string;
    }

    public void setReaded(String string) {
        if(string == "true"){
            this.isReaded = true;
        } else {
            this.isReaded = false;
        }
    }

    public void setDescription(String string) {
        this.description = string;
    }

    public void setReview(String string) {
        this.review = string;
    }
}
