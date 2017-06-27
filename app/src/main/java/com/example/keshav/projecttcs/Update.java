package com.example.keshav.projecttcs;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by keshav on 19-06-2017.
 */

public class Update extends MainActivity  {

    DatabaseHelper helper = new DatabaseHelper(this);

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    public void onUpdateClick(View view)
    {
        if (view.getId() == R.id.btn_update)
        {
            EditText etAge = (EditText) findViewById(R.id.editText_age);
            String strA = etAge.getText().toString();

            EditText eth = (EditText)findViewById(R.id.editText_height);
            String strH = eth.getText().toString();

            EditText etw = (EditText)findViewById(R.id.editText_weight);
            String strW = etw.getText().toString();

            EditText etd = (EditText)findViewById(R.id.editText_date);
            String strD = etd.getText().toString();

            if(TextUtils.isEmpty(strA)) {
                etAge.setError("Please specify your age");
                return;
            }

            if(TextUtils.isEmpty(strH)) {
                eth.setError("Please specify your height");
                return;
            }

            if(TextUtils.isEmpty(strW)) {
                etw.setError("Please specify your weight");
                return;
            }

            if(TextUtils.isEmpty(strD)) {
                etd.setError("Please specify the date because you can not donate before 3 months of last donation");
                return;
            }

            else {

                UpdateDB c1  = new UpdateDB();
                c1.setHeight(strH);
                c1.setWeight(strW);
                c1.setLdate(strD);

                helper.insertContact1(c1);

                Toast tem = Toast.makeText(this, "Your profile has been updated!, Press the back button", Toast.LENGTH_SHORT);
                tem.show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Update.this, MainActivity.class));
        super.onBackPressed();
    }
}
