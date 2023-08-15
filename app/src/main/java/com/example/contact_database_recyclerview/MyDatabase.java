package com.example.contact_database_recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(Context context) {
        super(context, "CONTACTBOOK", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Contacts (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, SUBNAME TEXT, NUMBER TEXT,IMGURI TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddContacts(String name, String subname, String number, Uri imguri) {
        String query = "INSERT INTO Contacts (NAME,SUBNAME,NUMBER,IMGURI) VALUES ('"+name+"','"+subname+"','"+number+"','"+imguri+"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor ShowContact() {
        String query = "SELECT * FROM Contacts";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void DeleteContact(int id) {
        String query = "DELETE FROM Contacts WHERE ID = "+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void UpdateContact(int id, String name, String subname, String number,Uri imguri) {
        String query = "UPDATE Contacts set NAME='"+name+"',SUBNAME='"+subname+"',NUMBER='"+number+"',IMGURI='"+imguri+"' WHERE ID = "+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
}
