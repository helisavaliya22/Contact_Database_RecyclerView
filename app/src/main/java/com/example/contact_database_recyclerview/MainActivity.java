package com.example.contact_database_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.contact_database_recyclerview.Model.Contacts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView add;
    ArrayList<Contacts> contactslist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showData();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Create_Contact_Activity.class);
                startActivity(intent);
            }
        });
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(MainActivity.this,contactslist);
        recyclerView.setAdapter(adapter);
    }

    private void showData() {
        MyDatabase myDatabase = new MyDatabase(MainActivity.this);
        Cursor cursor = myDatabase.ShowContact();

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String subname = cursor.getString(2);
            String number = cursor.getString(3);
            String imguri = cursor.getString(4);
            Contacts contacts = new Contacts(id,name,subname,number,imguri);
            contactslist.add(contacts);
            //Log.d("AAA", "showData: contacts"+contacts);
        }
    }
}