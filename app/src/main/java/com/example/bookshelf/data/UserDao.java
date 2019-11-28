package com.example.bookshelf.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users_table")
    List<User> getAll();

    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void deleteUser(User user);
}
