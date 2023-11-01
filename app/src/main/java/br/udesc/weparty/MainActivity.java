package br.udesc.weparty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(this::startApp, 1000);
        FirebaseApp.initializeApp(this);
    }

    private void startApp() {
        startActivity(new Intent(MainActivity.this, LoginRegistroActivity.class));
        //startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }
}