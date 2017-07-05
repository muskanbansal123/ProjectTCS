package com.example.keshav.projecttcs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

/**
 * Created by keshav on 25-06-2017.
 */

public class Profile extends MainActivity {


    TextView name,age,height,weight,ldate,phone;
    Button btnviewAll;

    Button display;

    static boolean flag;

    DatabaseHelper helper = new DatabaseHelper(this);

    Profile profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        name = (TextView)findViewById(R.id.prname);
        age=(TextView)findViewById(R.id.prage);
        height=(TextView)findViewById(R.id.prheight);
        weight=(TextView)findViewById(R.id.prweight);
        ldate=(TextView)findViewById(R.id.prdate);
        phone=(TextView)findViewById(R.id.prphone);

        //Intent i = new Intent(this,MainActivity.class);

        //helper.get_info(String x, String y, String z);

        btnviewAll = (Button)findViewById(R.id.btn_change);
        display = (Button)findViewById(R.id.btn_display);
        viewAll();

    }

    public void onButtonClick(View view)
    {
        if(view.getId() == R.id.btn_change)
        {
            Intent intent = new Intent(this, Update.class);
            startActivity(intent);

        }
    }

    public void viewAll()
    {
        //display.setOnClickListener(
               new View.OnClickListener() {
                    @Override
                   public void onClick(View v) {
                        Cursor res = helper.get_info();
                        if (res.getCount() == 0) {
                            Toast.makeText(Profile.this, "No data Found", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) {
                            name.setText(res.getString(res.getColumnIndex(DatabaseHelper.COLUMN_NAME)));

                            age.setText(res.getString(res.getColumnIndex(DatabaseHelper.COLUMN_AGE)) + "\n");

                            height.setText(res.getString(res.getColumnIndex(DatabaseHelper.COLUMN_HEIGHT)) + "\n");

                            weight.setText(res.getString(res.getColumnIndex(DatabaseHelper.COLUMN_WEIGHT)) + "\n");

                            ldate.setText(res.getString(res.getColumnIndex(DatabaseHelper.COLUMN_LDATE)) + "\n");

                            phone.setText(res.getString(res.getColumnIndex(DatabaseHelper.COLUMN_PHONE)) + "\n");

                        }
                        //show all data
                    }
                };
        //);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }

}
