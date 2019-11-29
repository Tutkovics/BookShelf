package com.example.bookshelf;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.bookshelf.adapter.BooksAdapter;
import com.example.bookshelf.data.Book;
import com.example.bookshelf.data.BookListDatabase;
import com.example.bookshelf.fragments.newBookItemDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class BookListActivity extends AppCompatActivity implements BooksAdapter.BookItemClickListener, newBookItemDialog.NewBookItemDialogListener {

    private RecyclerView recyclerView;
    private BooksAdapter adapter;
    //private RecyclerView.LayoutManager layoutManager;
    private String userTag;

    private BookListDatabase booksDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new newBookItemDialog().show(getSupportFragmentManager(),"Add new book");
            }
        });

        userTag = getIntent().getStringExtra("nfcTag");
        Toast.makeText(this, "Current user: " + userTag, Toast.LENGTH_LONG).show();
        //userTag = "562E5302";

        booksDatabase = Room.databaseBuilder(
                getApplicationContext(),
                BookListDatabase.class,
                "book-shelf"
        ).build();

        initRecyclerView();
        
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.BookRecyclerView);
        adapter = new BooksAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                return booksDatabase.bookDao().getAllBooksFromUser(userTag);
            }

            @Override
            protected void onPostExecute(List<Book> shoppingItems) {
                adapter.update(shoppingItems);
            }
        }.execute();
    }


    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    @Override
    public void onItemChanged(final Book item) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                booksDatabase.bookDao().update(item);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "Book update was successful");
            }
        }.execute();
    }


    @Override
    public void onBookItemCreated(final Book newItem) {
        new AsyncTask<Void, Void, Book>() {

            @Override
            protected Book doInBackground(Void... voids) {
                newItem.userTag = userTag;
                newItem.id = booksDatabase.bookDao().insert(newItem);
                return newItem;
            }

            @Override
            protected void onPostExecute(Book shoppingItem) {
                adapter.addItem(shoppingItem);
            }
        }.execute();
    }
}
