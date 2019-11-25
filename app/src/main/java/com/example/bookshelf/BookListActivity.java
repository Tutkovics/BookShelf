package com.example.bookshelf;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.bookshelf.adapter.BooksAdapter;
import com.example.bookshelf.data.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BooksAdapter adapter;
    private String userTag;

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
                // todo implement book item createion
            }
        });

        userTag = getIntent().getStringExtra("nfcTag");

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.BookRecyclerView);
        adapter = new BooksAdapter((BooksAdapter.BookItemClickListener) this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                return Profile.mydb.getUserBooks(userTag);
            }

            @Override
            protected void onPostExecute(List<Book> bookItem) {
                //adapter.update(bookItem);
            }
        }.execute();
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }
}
