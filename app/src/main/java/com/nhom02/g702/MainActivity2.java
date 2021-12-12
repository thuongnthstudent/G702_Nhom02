package com.nhom02.g702;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nhom02.g702.adapter.BookAdapter;
import com.nhom02.g702.model.Book;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lvServices;
    BookAdapter adapter;
    ArrayList<Book> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linkViews();
        loadData();
    }


    private void linkViews(){
        lvServices=findViewById(R.id.lvSach);
    }

    //Lưu dữ liệu vào Listview
    private void loadData() {
        services=new ArrayList<>();
        Cursor cursor=MainActivity.db.getData("SELECT * FROM "+MyDataBase.TBL_NAME);
        while(cursor.moveToNext()){
            services.add(new Book(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getBlob(5)));
        }
        cursor.close();
        adapter=new BookAdapter(MainActivity2.this,R.layout.item_layout,services);
        lvServices.setAdapter(adapter);
    }



}