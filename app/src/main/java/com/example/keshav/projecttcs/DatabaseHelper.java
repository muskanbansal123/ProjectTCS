package com.example.keshav.projecttcs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by keshav on 21-06-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME="people.db";
    private static final String TABLE_NAME="people_table";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_BGROUP = "bgroup";
    private static final String COLUMN_PINCODE = "pincode";

    SQLiteDatabase db;

    //write query
    private static final String TABLE_CREATE = "create table peope_table(id integer primary key not null ," +
            "name text not null, email text not null, password text not null, city text not null, bgroup text not null, age integer not null, contact integer not null);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public void insertContact (Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";    //get count of contacts
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();
        values.put(COLUMN_ID, count);

        values.put(COLUMN_NAME, c.getName());
       // values.put(COLUMN_AGE, c.getAge());
        //values.put(COLUMN_BGROUP, c.getBgroup());
        values.put(COLUMN_CITY, c.getCity());
        //values.put(COLUMN_CONTACT,c.getContact());
        values.put(COLUMN_EMAIL, c.getEmail_add());
        values.put(COLUMN_PASS, c.getPassword());
        //values.put(COLUMN_PINCODE, c.getPincode());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public String searchPass(String email)
    {
        db = this.getReadableDatabase();
        String query = "select email address, pass from"+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a,b = null;
        b = "not found";
        if (cursor.moveToFirst())
        {
            do {

                a = cursor.getString(0);

                if (a.equals(email))
                {
                    b = cursor.getString(1);
                    break;
                }

            }while (cursor.moveToNext());
        }
        return b;
    }

}
