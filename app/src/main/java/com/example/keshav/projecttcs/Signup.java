package com.example.keshav.projecttcs;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keshav on 21-06-2017.
 */

public class Signup extends MainActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       /* EditText name = (EditText)findViewById(R.id.input_name);
        EditText email = (EditText)findViewById(R.id.input_email);
        EditText pass1 = (EditText)findViewById(R.id.input_password);
        EditText pass2 = (EditText)findViewById(R.id.input_confirmpassword);
        EditText age = (EditText)findViewById(R.id.input_Age);
        EditText city = (EditText)findViewById(R.id.input_City);
        EditText pincode = (EditText)findViewById(R.id.input_Pincode);

        String namestr = name.getText().toString();
        String emailstr = email.getText().toString();
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        String agestr = age.getText().toString();
        String citystr = city.getText().toString();
        String pincodestr = pincode.getText().toString();*/
    }

    public void onButtonClick(View v) {
        EditText name = (EditText) findViewById(R.id.input_name);
        EditText email = (EditText) findViewById(R.id.input_email);
        EditText pass1 = (EditText) findViewById(R.id.input_password);
        EditText pass2 = (EditText) findViewById(R.id.input_confirmpassword);
        EditText age = (EditText) findViewById(R.id.input_Age);
        EditText city = (EditText) findViewById(R.id.input_City);
        EditText pincode = (EditText) findViewById(R.id.input_Pincode);


        String namestr = name.getText().toString();
        String emailstr = email.getText().toString();
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        String agestr = age.getText().toString();
        String citystr = city.getText().toString();
        String pincodestr = pincode.getText().toString();
        if (v.getId() == R.id.Bsignupbutton) {

            isValidEmail(emailstr);

            if (!pass1str.equals(pass2str)) {
                Toast pass = Toast.makeText(Signup.this, "Password doesn't match", Toast.LENGTH_SHORT);
                pass.show();
            }


            if (TextUtils.isEmpty(namestr)) {
                name.setError("Please specify your name");
                return;
            }

            if (TextUtils.isEmpty(emailstr)) {
                email.setError("Please specify the Email id");
                return;
            }

            if (TextUtils.isEmpty(pass1str)) {
                pass1.setError("Please specify the password");
                return;
            }

            if (TextUtils.isEmpty(pass2str)) {
                pass2.setError("Please specify the password");
                return;
            }
            if (TextUtils.isEmpty(agestr)) {
                age.setError("Please specify the age");
                return;
            }

            if (TextUtils.isEmpty(citystr)) {
                city.setError("Please specify the city");
                return;
            }
            if (TextUtils.isEmpty(pincodestr)) {
                pincode.setError("Please specify the pincode");
                return;
            }

            if (isValidEmail(emailstr) == true) {
                Toast temp = Toast.makeText(Signup.this, "You are successfully registered!", Toast.LENGTH_SHORT);
                temp.show();
            }

            if (isValidEmail(emailstr) == false)
            {
                Toast.makeText(getApplicationContext(), "Enter Valid Email-Id", Toast.LENGTH_LONG).show();
            }
         /* public boolean isValidEmail (String email)
            {

                    Pattern pattern;
                    Matcher matcher;
                    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    pattern = Pattern.compile(EMAIL_PATTERN);
                    matcher = pattern.matcher(email);
                    return matcher.matches();



        }*/


        }
        if (v.getId() == R.id.link_login) {
            Intent intent = new Intent(this, loginn.class);
            startActivity(intent);
        }

    }

    /*private boolean isValidEmail(CharSequence target) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }*/


    /*public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate();
    }

     public void onButtonClick(View v)
     {
         if (v.getId() == R.id.btn_Register)
         {
             EditText a = (EditText)findViewById(R.id.);
             String str = a.getText().toString();

             Intent i = new Intent(this, MainActivity.class);
             i.putExtra()
         }
     }*/
    public void onBackPressed() {
        startActivity(new Intent(Signup.this, MainActivity.class));
        super.onBackPressed();
    }

    public boolean isValidEmail(String email) {

        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";


        String email1 = email;

        Matcher matcher = Pattern.compile(validemail).matcher(email);


        if (matcher.matches()) {
            //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
            return true;


        } else {

            return false;
        }
    }



}

