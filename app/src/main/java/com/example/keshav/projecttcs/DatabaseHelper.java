package com.example.keshav.projecttcs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.y;

/**
 * Created by keshav on 21-06-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME="contacts.db";

    private static final String TABLE_NAME="contacts";

    private static final String TABLE_UPDATEDB="updates";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PINCODE = "pincode";

    public static final String KEY_UPDATEDB = "upd";

    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_LDATE = "ldate";
    public static final String COLUMN_UAGE = "uage";
    public static final String COLUMN_UPHONE = "uphone";


   static SQLiteDatabase db;

    //write query

    private static final String TABLE_CREATE = "create table contacts(id integer primary key not null ," +
            "name text not null, email text not null, password text not null, city text not null, age text not null, pincode text not null, gender text not null, phone text not null);";

    private static final String TABLE_CREATE_UPDATE = "create table updates( upd text primary key not null," +
            " height text not null, weight text not null, ldate text not null, uage text not null, uphone text not null);";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_UPDATE);

        //this.db = db;

    }


    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);


       // String query1 = "DROP TABLE IF EXISTS" + TABLE_UPDATEDB;
        //db.execSQL(query1);

        onCreate(db);
    }


    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";    //get count of contacts
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();
        values.put(COLUMN_ID, count);

        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_AGE, c.getAge());
        values.put(COLUMN_GENDER, c.getGender());
        values.put(COLUMN_CITY, c.getCity());
        values.put(COLUMN_PHONE,c.getPhone());
        values.put(COLUMN_EMAIL, c.getEmail_add());
        values.put(COLUMN_PASS, c.getPassword());
        values.put(COLUMN_PINCODE, c.getPincode());

        db.insert(TABLE_NAME, null, values);
        //db.close();
    }

    public void insertContact1(UpdateDB c1)
    {

        db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();

        String query1 = "select * from updates";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor1 = db.rawQuery(query1,null);

        int count1 = cursor1.getCount();
        values1.put(KEY_UPDATEDB, count1);

        values1.put(COLUMN_HEIGHT, c1.getHeight());
        values1.put(COLUMN_WEIGHT, c1.getWeight());
        values1.put(COLUMN_LDATE, c1.getLdate());
        values1.put(COLUMN_UAGE, c1.getUage());
        values1.put(COLUMN_UPHONE, c1.getUphone());
        db.insert(TABLE_UPDATEDB, null, values1);
        db.close();
    }


        public Cursor get_info()         //Cursor class provides random read write interface
        {
           SQLiteDatabase db1 = this.getReadableDatabase();
            Cursor res = db1.rawQuery("select * from "+TABLE_UPDATEDB, null);

         return res;
        /*db1 = this.getReadableDatabase();

        String query1 = "select height, weight, ldate from "+TABLE_UPDATEDB;
        Cursor cursor1 = db1.rawQuery(query1,null);

        String h,w,d = null;
        h = "empty";
        while (cursor1.moveToNext()) {

            h = cursor1.getString(cursor1.getColumnIndex(COLUMN_HEIGHT));
            w = cursor1.getString(cursor1.getColumnIndex(COLUMN_WEIGHT));
            d = cursor1.getString(cursor1.getColumnIndex(COLUMN_LDATE));

            //return profile;
            //return h,w,d;
        }

        return h;*/
    }
    public String searchPass(String email)
    {
        db = this.getWritableDatabase();
        String query = "select email, password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a,b = null;
        b = "not found";
        if (cursor.moveToFirst())
        {
            do {
                Log.d("Error: ","Email matched");
                //Log.d("Cursor " + cursor," ");
                a = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));

                if (a.equals(email))
                {
                    //Log.d("Error: ","Email matched");
                    b = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
                    break;
                }

            }while (cursor.moveToNext());
        }
        return b;
    }

}
