package com.example.keshav.projecttcs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by keshav on 07-07-2017.
 */

public class DonorList extends MainActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    TextView donorname;

    Cursor cursor;
    SQLiteListAdapter ListAdapter ;


    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> PHONE_NUMBER_ArrayList = new ArrayList<String>();
    ArrayList<String> SUBJECT_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorlist);

        //donorname = (TextView) findViewById(R.id.donor);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        //get_donor();

        ShowSQLiteDBdata();
    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        //SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        //cursor = SQLITEDATABASE.rawQuery("SELECT * FROM demoTable", null);

        //Cursor don = helper.get_city();

        //don.moveToFirst();

        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        PHONE_NUMBER_ArrayList.clear();
        SUBJECT_ArrayList.clear();

        Cursor don = helper.get_city();


        don.moveToFirst();
        if(don.getCount()==0) {

            Toast.makeText(DonorList.this, "No data Found", Toast.LENGTH_SHORT).show();
            return;

        }
        while (don.moveToNext())  {
                //ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));

                NAME_ArrayList.add(don.getString(1));
                PHONE_NUMBER_ArrayList.add(don.getString(8));
                SUBJECT_ArrayList.add(don.getString(4));

                //PHONE_NUMBER_ArrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE)));

                //SUBJECT_ArrayList.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CITY)));


        }

        ListAdapter = new SQLiteListAdapter(DonorList.this,


               // ID_ArrayList,
                NAME_ArrayList,
                PHONE_NUMBER_ArrayList,
                SUBJECT_ArrayList

        );

        LISTVIEW.setAdapter(ListAdapter);

        //cursor.close();
    }

   /* public void get_donor() {
        Cursor don = helper.get_city();

        don.moveToFirst();
       // donorname.setText(don.getString(4));


        if (don.getCount() == 0) {
            Toast.makeText(DonorList.this, "No data Found", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (don.moveToNext()) {

            donorname.setText(don.getString(1));
        }
    }*/

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DonorList.this,MainActivity.class));
        super.onBackPressed();
    }
}
