package com.example.bookshelf.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books_table")
public class Book {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "is_readed")
    public boolean isReaded;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "user_tag")
    public String userTag;

    @ColumnInfo(name = "review")
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
