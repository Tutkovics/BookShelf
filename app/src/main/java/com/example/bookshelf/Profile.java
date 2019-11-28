package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Profile extends AppCompatActivity {

    private TextView userTag;
    private TextView userName;
    private Button saveButton;
    private Button booksButton;
    private TextView registrationDate;
    private TextView numberOfBooks;

    static DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.userName);
        userTag = findViewById(R.id.userTag);
        //saveButton = findViewById(R.id.btnSave);
        booksButton = findViewById(R.id.btnBooks);
        registrationDate = findViewById(R.id.userRegDate);
        numberOfBooks = findViewById(R.id.userReadedBooks);


        final String tag = getIntent().getStringExtra("nfcTag");

        // find in db
        mydb = new DBHelper(this);

        Cursor rs = mydb.getUserData(tag);
        // Toast.makeText(this, "Get user data: " + tag, Toast.LENGTH_LONG).show();

        if(rs != null && rs.getCount()>0){
            // user already in db
            rs.moveToFirst();

            String name = rs.getString(rs.getColumnIndex(DBHelper.USERS_COLUMN_NAME));
            String regDate = rs.getString(rs.getColumnIndex(DBHelper.USERS_COLUMN_REGISTRATION));
            String nfcTag = rs.getString(rs.getColumnIndex(DBHelper.USERS_COLUMN_TAG));

            registrationDate.setText(regDate);
            userName.setText(name);
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
                //i.putExtra("nfcTag",tag);
                startActivity(i);
            }
        });



    }

}
