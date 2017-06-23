package com.example.keshav.projecttcs;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by keshav on 19-06-2017.
 */

public class requestpage extends MainActivity  {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_request);
    }

    public void onButtonClick(View view)
    {
        if (view.getId() == R.id.btn_request)
        {

            EditText etUserName = (EditText) findViewById(R.id.editText_bloodgroups);
            String strUserName = etUserName.getText().toString();

            EditText etunits = (EditText)findViewById(R.id.editText_unitss);
            String strunits = etunits.getText().toString();

            EditText etcity = (EditText)findViewById(R.id.editText_city);
            String strcity = etcity.getText().toString();

            if(TextUtils.isEmpty(strUserName)) {
                etUserName.setError("Please specify the blood group");
                return;
            }

            if(TextUtils.isEmpty(strunits)) {
                etunits.setError("Please specify the Units");
                return;
            }

            if(TextUtils.isEmpty(strcity)) {
                etcity.setError("Please specify the city");
                return;
            }
            else
            {
                Toast t = Toast.makeText(this, "You made a request, Press back button", Toast.LENGTH_SHORT);
                t.show();
            }

        }
    }

@Override
    public void onBackPressed() {
        startActivity(new Intent(requestpage.this,MainActivity.class));
        super.onBackPressed();
    }
}