package com.example.bookshelf;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.bookshelf.adapter.BooksAdapter;
import com.example.bookshelf.data.Book;
import com.example.bookshelf.fragments.newBookItemDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class BookListActivity extends AppCompatActivity implements BooksAdapter.BookItemClickListener, newBookItemDialog.NewBookItemDialogListener {

    private RecyclerView recyclerView;
    private BooksAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
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
                new newBookItemDialog().show(getSupportFragmentManager(),"Add new book");
            }
        });

        //userTag = getIntent().getStringExtra("nfcTag");
        userTag = "562E5302";

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new BooksAdapter(new DBHelper(this).getUserBooks(userTag));
        recyclerView.setAdapter(adapter);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.BookRecyclerView);
        adapter = new BooksAdapter(this);
        //loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    @Override
    public void onItemChanged(Book item) {
        Toast.makeText(this,"onItemChanged", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBookItemCreated(Book newItem) {
        newItem.userTag = userTag;
        DBHelper mydb = new DBHelper(this);
        mydb.insertBook(newItem);
        mydb.close();
        Toast.makeText(this,"Activity reloaded", Toast.LENGTH_LONG).show();
        finish();

        startActivity(getIntent());
       /* new AsyncTask<Void, Void, Book>() {

            @Override
            protected Book doInBackground(Void... voids) {
                newItem.id = // add to db
                return newItem;
            }

            @Override
            protected void onPostExecute(Book b) {
                adapter.addItem(b);
            }
        }.execute();
    }*/
    }
}
