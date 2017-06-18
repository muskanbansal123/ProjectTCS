package com.example.keshav.projecttcs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    Intent openStartingPoint = new Intent(Splashscreen.this, MainActivity.class);
                    startActivity(openStartingPoint);
                    finish();
                }
            }

        };
        myThread.start();
    }
}
