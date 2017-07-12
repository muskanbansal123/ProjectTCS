package com.example.keshav.projecttcs;

import android.content.Intent;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keshav on 19-06-2017.
 */

public class loginn extends MainActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    DatabaseHelper helper = new DatabaseHelper(loginn.this);

    Button login, signup;
    LoginButton loginButton;
    CallbackManager callbackManager;

    private Button Signout;
    private SignInButton SignIn;

   // private TextView
    public GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_loginn);

         EditText a = (EditText) findViewById(R.id.edemail);
        String str = a.getText().toString();

        EditText b = (EditText) findViewById(R.id.edpassword);
        String pass = b.getText().toString();


        SignIn = (SignInButton)findViewById(R.id.btn_google);
        Signout = (Button)findViewById(R.id.btn_logout);

        Signout.setOnClickListener(this);

        SignIn.setOnClickListener(this);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        login = (Button) findViewById(R.id.blogin);
        signup = (Button)findViewById(R.id.bsignup);
        loginButton = (Button)findViewById(R.id.fb_login_bn);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(loginn.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {

                Toast temp = Toast.makeText(loginn.this, "Login Cancelled", Toast.LENGTH_SHORT);
                temp.show();

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view)
        {
            EditText a = (EditText) findViewById(R.id.edemail);
            String str = a.getText().toString();

            EditText b = (EditText) findViewById(R.id.edpassword);
            String pass = b.getText().toString();

            if(TextUtils.isEmpty(str)) {
                a.setError("Please specify the Email id");
                return;
            }

            if(TextUtils.isEmpty(pass)) {
                b.setError("Please specify the Password");
                return;
            }

            String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                Toast temp = Toast.makeText(loginn.this, "Welcome", Toast.LENGTH_SHORT);
                temp.show();
                Profile.flag=true;

                Intent intent = new Intent(loginn.this, MainActivity.class);
                //intent.putExtra("1", st);
                startActivity(intent);


            }

            else
            {
                Toast temp = Toast.makeText(loginn.this, "Email address and password don't match", Toast.LENGTH_SHORT);
                temp.show();
            }

        }

        });
                signup.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view)
                    {

                        Intent i = new Intent(loginn.this, Signup.class);
                        startActivity(i);
                    }
                }
                );
    }


    @Override
    public void onBackPressed() {
       startActivity(new Intent(loginn.this,MainActivity.class));
        super.onBackPressed();
    }

    @Override


    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_google:
                signIn();

                /*Intent intent = new Intent(this, Profile.class);
                startActivity(intent);*/
                break;

            case R.id.btn_logout:
                signOut();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn()
    {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }

   public void signOut()
    {

     /*Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
         @Override
         public void onResult(@NonNull Status status) {
             updateUI(false);

            }
        });*/




    }
    private void handleResult(GoogleSignInResult result)
    {
        if (result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();

            Toast temp = Toast.makeText(loginn.this, "Welcome", Toast.LENGTH_SHORT);
            temp.show();

           Profile.flag = true;
            updateUI(true);

            Intent intent = new Intent(loginn.this, MainActivity.class);
            //intent.putExtra("1", st);
            startActivity(intent);
        }
        else {
            updateUI(false);
        }

    }
    private void updateUI(boolean isLogin)
    {

        if (isLogin)
        {
            //Profile.Prof_section.setVisibility(View.VISIBLE);
            Profile.flag = true;
        }
        else
        {
            //Profile.Prof_section.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode==REQ_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

             handleResult(result);
        }

        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    /*public void signOut()
    {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }

    });
    }*/



}

