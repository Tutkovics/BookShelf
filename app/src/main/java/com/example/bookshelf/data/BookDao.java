package com.example.bookshelf.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM books_table")
    List<Book> getAll();

    @Query("SELECT * FROM books_table WHERE user_tag LIKE :nfcTag")
    List<Book> getAllBooksFromUser(String nfcTag);

    @Insert
    long insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void deleteBook(Book book);
}
