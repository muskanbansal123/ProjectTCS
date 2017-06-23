package com.example.keshav.projecttcs;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keshav on 19-06-2017.
 */

public class loginn extends MainActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);
    }

  /* public boolean emailValidator(String email)
    {
        try {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();

        } catch (Exception e)
        {
            Toast em = Toast.makeText(this, "Email address doesn't exist", Toast.LENGTH_SHORT);
            em.show();

        }
        return matcher.matches();
    }*/
    public void onButtonClick (View v)
    {
        if(v.getId() == R.id.blogin)
        {
            EditText a = (EditText) findViewById(R.id.input_email);
            String str = a.getText().toString();

            EditText b = (EditText) findViewById(R.id.input_email);
            String pass = b.getText().toString();

            /*String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Email address", str);
                startActivity(intent);

            }*/

            if(TextUtils.isEmpty(str)) {
                a.setError("Please specify the Email id");
                return;
            }

            if(TextUtils.isEmpty(pass)) {
                b.setError("Please specify the Password");
                return;
            }

            else
            {
                Toast temp = Toast.makeText(this, "Email address and password don't match", Toast.LENGTH_SHORT);
                temp.show();
            }

        }

        if (v.getId() == R.id.bsignup)
        {
            Intent i = new Intent(this, Signup.class);
            startActivity(i);
        }


    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(loginn.this,MainActivity.class));
        super.onBackPressed();
    }
}
