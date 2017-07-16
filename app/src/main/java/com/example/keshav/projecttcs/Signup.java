package com.example.keshav.projecttcs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keshav on 21-06-2017.
 */

public class Signup extends MainActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    Button l_login, bsignup;
    private EditText gender;

    private Context mContext;
    private Activity mActivity;

    private LinearLayout mCLayout;

    String MobilePattern = "[0-9]{10}";
    String PinPattern = "[0-9]{6}";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


    l_login = (Button)findViewById(R.id.link_login);
    bsignup = (Button)findViewById(R.id.Bsignupbutton);
    gender = (EditText)findViewById(R.id.input_gender);
        mContext = getApplicationContext();
        mActivity = Signup.this;

        // Get the widget reference from XML layout
        mCLayout = (LinearLayout) findViewById(R.id.llayout);

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initializing a new alert dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

                // Set the alert dialog title
                builder.setTitle("Gender");

                // Initializing an array of flowers
                final String[] genders = new String[]{
                        "Male",
                        "Female",
                        "Other"
                };


                // Set a single choice items list for alert dialog
                builder.setSingleChoiceItems(
                        genders, // Items list
                        -1, // Index of checked item (-1 = no selection)
                        new DialogInterface.OnClickListener() // Item click listener
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Get the alert dialog selected item's text
                                String selectedItem = Arrays.asList(genders).get(i);

                                gender.setText(selectedItem);
                                dialogInterface.dismiss();

                            }
                        });

                // Set the a;ert dialog positive button
               /* builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Just dismiss the alert dialog after selection
                        dialogInterface.dismiss();
                        // Or do something now
                    }
                });*/

                // Create the alert dialog
                AlertDialog dialog = builder.create();

                // Finally, display the alert dialog
                dialog.show();
            }
        });

                bsignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        EditText name = (EditText) findViewById(R.id.input_name);

                        EditText email = (EditText) findViewById(R.id.input_email);

                        EditText pass1 = (EditText) findViewById(R.id.input_password);

                        EditText pass2 = (EditText) findViewById(R.id.input_confirmpassword);

                        EditText age = (EditText) findViewById(R.id.input_Age);

                        EditText city = (EditText) findViewById(R.id.input_City);

                        EditText pincode = (EditText) findViewById(R.id.input_Pincode);

                        EditText gender = (EditText) findViewById(R.id.input_gender);

                        EditText phone = (EditText) findViewById(R.id.input_phone);


                        String namestr = name.getText().toString();

                        String emailstr = email.getText().toString();

                        String pass1str = pass1.getText().toString();

                        String pass2str = pass2.getText().toString();

                        String agestr = age.getText().toString();

                        String citystr = city.getText().toString();

                        String pincodestr = pincode.getText().toString();

                        String genderstr = gender.getText().toString();

                        String phonestr = phone.getText().toString();


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
                        if (TextUtils.isEmpty(genderstr)) {
                            gender.setError("Please specify your gender");
                            return;
                        }
                        if (TextUtils.isEmpty(phonestr)) {
                            phone.setError("Please specify your contact number");
                            return;
                        }
                        if (isValidEmail(emailstr)) {
                            //Toast temp = Toast.makeText(Signup.this, "You are successfully registered!", Toast.LENGTH_SHORT);
                            //temp.show();
                        }
                        if (!isValidEmail(emailstr)) {
                            Toast.makeText(getApplicationContext(), "Enter Valid Email-Id", Toast.LENGTH_SHORT).show();
                        }
                        if (phone.getText().toString().matches(MobilePattern))
                        {
                            //Toast temp = Toast.makeText(Signup.this, "You are successfully registered!", Toast.LENGTH_SHORT);
                            //temp.show();
                        }
                        if (!phone.getText().toString().matches(MobilePattern))
                        {
                            Toast.makeText(getApplicationContext(), "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                        }
                        if (pincode.getText().toString().matches(PinPattern))
                        {
                            //Toast temp = Toast.makeText(Signup.this, "You are successfully registered!", Toast.LENGTH_SHORT);
                            //temp.show();
                        }
                        if (!pincode.getText().toString().matches(PinPattern))
                        {
                            Toast.makeText(getApplicationContext(), "Enter Valid Pincode", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast temp = Toast.makeText(Signup.this, "You are successfully registered!", Toast.LENGTH_SHORT);
                            temp.show();

                            Contact c = new Contact();
                            c.setName(namestr);
                            c.setEmail_add(emailstr);
                            c.setPassword(pass1str);
                            c.setAge(agestr);
                            c.setCity(citystr);
                            c.setPincode(pincodestr);
                            c.setGender(genderstr);
                            c.setPhone(phonestr);
                            helper.insertContact(c);
                        }
                    }
                });

            l_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
         {
            Intent intent = new Intent(Signup.this, loginn.class);
            startActivity(intent);
        }

    }
            );}

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

