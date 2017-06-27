package com.example.keshav.projecttcs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.jar.Attributes;

/**
 * Created by keshav on 25-06-2017.
 */

public class Profile extends MainActivity {

    Button btnAddData;
    Button btnviewAll;

    int flag;

    DatabaseHelper helper = new DatabaseHelper(this);

    Profile profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i = new Intent(this,MainActivity.class);
        flag = 1;

        //helper.get_info(String x, String y, String z);

        btnviewAll = (Button)findViewById(R.id.btn_change);
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
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=  helper.get_info();
                        if(res.getCount() == 0)
                        {
                            //show msg
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext())
                        {
                            buffer.append("Name: "+res.getString(res.getColumnIndex(COLUMN_NAME))+"\n");

                            buffer.append("Age: "+res.getString(res.getColumnIndex(COLUMN_AGE))+"\n");

                            buffer.append("Height: "+res.getString(res.getColumnIndex(COLUMN_HEIGHT))+"\n");

                            buffer.append("Weight: "+res.getString(res.getColumnIndex(COLUMN_WEIGHT))+"\n");

                            buffer.append("Date: "+res.getString(res.getColumnIndex(COLUMN_LDATE))+"\n");

                            buffer.append("Contact: "+res.getString(res.getColumnIndex(COLUMN_PHONE))+"\n");

                        }
                        //show all data
                    }
                }
        );
    }



    public void Profile(String x, String y, String z)

}
    {
}
