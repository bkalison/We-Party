package br.udesc.weparty.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

import br.udesc.weparty.R;

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
    }
}