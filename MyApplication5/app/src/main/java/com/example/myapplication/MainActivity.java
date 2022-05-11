package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.Funcionalidad.LoginPackage.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private PartyingApp mApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApp = PartyingApp.getApp();
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2*1000);

                    Intent i=new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}