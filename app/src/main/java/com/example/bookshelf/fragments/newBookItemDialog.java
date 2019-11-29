package com.example.bookshelf.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.bookshelf.R;
import com.example.bookshelf.data.Book;

public class newBookItemDialog extends DialogFragment {
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText descriptionEditText;
    private EditText reviewEditText;
    private CheckBox alreadyReadedCheckBox;

    private String title;
    private String author;
    private String description;
    private String review;
    private boolean readed;
    private Book book;

    public String userTag;

    public newBookItemDialog(Book book){
        if(book != null){
            this.title = book.title;
            this.author = book.author;
            this.review = book.review;
            this.description = book.description;
            this.readed = book.isReaded;
            this.book = book;
        } else {
            this.title = "";
            this.author = "";
            this.review = "";
            this.description = "";
            this.readed = false;
            this.book = null;
        }
    }

    public interface NewBookItemDialogListener {
        void onBookItemCreated(Book newItem);

        void onItemEdit(Book book);
    }

    private NewBookItemDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity instanceof NewBookItemDialogListener) {
            listener = (NewBookItemDialogListener) activity;
        } else {
            throw new RuntimeException("Activity must implement the NewBookItemDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog alert;
        if(this.book == null){
             alert = new AlertDialog.Builder(requireContext())
                    .setTitle(R.string.new_book_item)
                    .setView(getContentView())
                    .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (isValid()) {
                                listener.onBookItemCreated(getBook());
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .create();

        } else {
             alert = new AlertDialog.Builder(requireContext())
                    .setTitle(R.string.new_book_item)
                    .setView(getContentView())

                    .setPositiveButton(R.string.edit,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (isValid()) {
                                listener.onBookItemCreated(getBook());
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .create();
        }

        return alert;


    }

    private View getContentView() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_book_item, null);
        titleEditText = contentView.findViewById(R.id.BookTitleEditText);
        authorEditText = contentView.findViewById(R.id.BookAuthorEditText);
        descriptionEditText = contentView.findViewById(R.id.BookDescriptionEditText);
        reviewEditText = contentView.findViewById(R.id.ReviewEditText);
        alreadyReadedCheckBox = contentView.findViewById(R.id.BookReaded);

        titleEditText.setText(title);
        authorEditText.setText(author);
        descriptionEditText.setText(description);
        reviewEditText.setText(review);
        alreadyReadedCheckBox.setChecked(readed);

        //estimatedPriceEditText = contentView.findViewById(R.id.ShoppingItemEstimatedPriceEditText);
        //categorySpinner = contentView.findViewById(R.id.ShoppingItemCategorySpinner);
        //categorySpinner.setAdapter(new ArrayAdapter<>(requireContext(),
         //       android.R.layout.simple_spinner_dropdown_item,
          //      getResources().getStringArray(R.array.category_items)));


        return contentView;
    }

    private boolean isValid() {
        return true;
        //return (titleEditText.getText().length() > 0) && (authorEditText.getText().length() > 0);
    }

    private Book getBook() {
        Book b = new Book();
        b.title = titleEditText.getText().toString();
        b.author = authorEditText.getText().toString();
        b.description = descriptionEditText.getText().toString();
        b.review = reviewEditText.getText().toString();
        b.isReaded = alreadyReadedCheckBox.isChecked();


        return b;
    }
}
