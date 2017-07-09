package com.example.keshav.projecttcs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by keshav on 07-07-2017.
 */

public class DonorList extends MainActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    TextView donorname;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorlist);

        donorname = (TextView) findViewById(R.id.donor);
        get_donor();
    }

    public void get_donor() {
        Cursor don = helper.get_city();

        don.moveToFirst();

        if (don.getCount() == 0) {
            Toast.makeText(DonorList.this, "No data Found", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (don.moveToNext()) {

            donorname.setText(don.getString(4));
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DonorList.this,MainActivity.class));
        super.onBackPressed();
    }
}
