package br.udesc.weparty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(this::startApp, 5000);
    }

    private void startApp() {
        startActivity(new Intent(MainActivity.this, LoginRegistroActivity.class));
    }
}