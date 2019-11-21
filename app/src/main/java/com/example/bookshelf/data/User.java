package com.example.bookshelf.data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private Date dateOfRegistratioin;
    private String nfcTag;
    private List<Book> wishList;
    private List<Book> readed;
    private int id;

    public User(String nfcTag) {
        this.nfcTag = nfcTag;
        this.dateOfRegistratioin = Calendar.getInstance().getTime();
    }

    // getters
    public String getName(){
        return name;
    }

    public Date getRegistration(){
        return dateOfRegistratioin;
    }
    
    public String getNfcTag(){
        return nfcTag;
    }
    
    public List<Book> getWishList(){
        return wishList;
    }
    
    public List<Book> getReaded(){
        return readed;
    }

    // setters and data manipulators
    public void setName(String newName){
        this.name = newName;
    }

    public void addWishBook(Book book){
        wishList.add(book);
    }

    public void addReadedBook(Book book){
        readed.add(book);
    }
}
