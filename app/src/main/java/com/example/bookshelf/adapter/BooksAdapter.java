package com.example.bookshelf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.R;
import com.example.bookshelf.data.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    private final List<Book> books;
    private BookItemClickListener listener;

    public BooksAdapter(BookItemClickListener listener){
        this.listener = listener;
        books = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_list, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book item = books.get(position);

        holder.readedCheckBox.setChecked(item.isReaded);;
        holder.bookTitleTextView.setText(item.title);
        holder.bookAuthorTextView.setText(item.author);

        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public interface BookItemClickListener{
        void onItemChanged(Book item);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        CheckBox readedCheckBox;
        TextView bookAuthorTextView;
        TextView bookTitleTextView;
        ImageButton removeButton;

        Book item;

        BookViewHolder(View itemView) {
            super(itemView);

            readedCheckBox = itemView.findViewById(R.id.BookItemReadedCheckBox);
            bookAuthorTextView = itemView.findViewById(R.id.BookAuthor);
            bookTitleTextView = itemView.findViewById(R.id.BookTitle);
            removeButton = itemView.findViewById(R.id.BookItemRemoveButton);

            readedCheckBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    if(item != null){
                        item.isReaded = isChecked;
                        listener.onItemChanged(item);
                    }
                }
            });
        }
    }
}
