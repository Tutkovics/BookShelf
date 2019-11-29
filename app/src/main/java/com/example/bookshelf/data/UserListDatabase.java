package com.example.bookshelf.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {User.class},
        version = 1,
        exportSchema = false
)
public abstract class UserListDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

