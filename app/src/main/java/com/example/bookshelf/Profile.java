package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshelf.data.Book;
import com.example.bookshelf.data.BookListDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Profile extends AppCompatActivity {

    private TextView userTag;
    //private TextView userName;
    private Button saveButton;
    private Button booksButton;
    private TextView registrationDate;
    private TextView numberOfBooks;
    private Button logoutButton;

    private BookListDatabase booksDatabase;
    private List<Book> userBook;
    private String tag;

    static DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //userName = findViewById(R.id.userName);
        userTag = findViewById(R.id.userTag);
        //saveButton = findViewById(R.id.btnSave);
        booksButton = findViewById(R.id.btnBooks);
        registrationDate = findViewById(R.id.userRegDate);
        numberOfBooks = findViewById(R.id.userReadedBooks);
        logoutButton = findViewById(R.id.btnLogout);


        tag = getIntent().getStringExtra("nfcTag");

        // find in db
        mydb = new DBHelper(this);

        Cursor rs = mydb.getUserData(tag);
        //Toast.makeText(this, "Get user data: " + tag, Toast.LENGTH_LONG).show();

        if(rs != null && rs.getCount()>0){
            // user already in db
            rs.moveToFirst();

            String name = rs.getString(rs.getColumnIndex(DBHelper.USERS_COLUMN_NAME));
            String regDate = rs.getString(rs.getColumnIndex(DBHelper.USERS_COLUMN_REGISTRATION));
            String nfcTag = rs.getString(rs.getColumnIndex(DBHelper.USERS_COLUMN_TAG));

            registrationDate.setText(regDate);
            //userName.setText(name);
            userTag.setText(nfcTag);


        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            Boolean success = mydb.insertUser(tag, "Random Name", dateFormat.format(date));
            // Toast.makeText(this, "New user", Toast.LENGTH_LONG).show();
            if(success){
                Toast.makeText(this,"Successfully created new user", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent().putExtra("nfcTag", tag));
            }
        }

        if (!rs.isClosed())  {
            rs.close();
        }
        mydb.close();
        //userTag.setText(tag);

        booksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, BookListActivity.class);
                i.putExtra("nfcTag",tag.toString());
                startActivity(i);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });


        booksDatabase = Room.databaseBuilder(
                getApplicationContext(),
                BookListDatabase.class,
                "book-shelf"
        ).build();

        loadItemsInBackground();



        //List<Book> userBook = booksDatabase.bookDao().getAllBooksFromUser(tag);


    }


    @Override
    public void onResume(){
        super.onResume();
        loadItemsInBackground();

    }


    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                return booksDatabase.bookDao().getAllBooksFromUser(tag);
            }

            @Override
            protected void onPostExecute(List<Book> shoppingItems) {
                /*userBook = shoppingItems;
                String num = String.valueOf(shoppingItems.size());*/
                Integer num = 0;
                for (Book b:
                     shoppingItems) {
                    if(b.userTag == tag){
                        num += 1;
                    } else {
                        //Toast.makeText(Profile.this, "Book tag: " + b.userTag, Toast.LENGTH_LONG).show();
                    }

                }
                numberOfBooks.setText(String.valueOf(shoppingItems.size()));

            }
        }.execute();


    }




}
