package com.example.bookshelf.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity(tableName = "users_table")
public class User {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "nfc")
    private String nfcTag;



    public User(String nfcTag) {
        this.nfcTag = nfcTag;
        //this.dateOfRegistratioin = Calendar.getInstance().getTime().toString();
    }

    // getters
    public String getName(){
        return name;
    }

    
    public String getNfcTag(){
        return nfcTag;
    }


    // setters and data manipulators
    public void setName(String newName){
        this.name = newName;
    }


}
