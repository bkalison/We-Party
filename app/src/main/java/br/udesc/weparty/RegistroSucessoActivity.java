package br.udesc.weparty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroSucessoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_sucesso);
        Button btnLogarAbrir = findViewById(R.id.btnLogarAbrir);

        btnLogarAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent();
            }
        });
    }
    public void intent(){
        Intent intent = new Intent(RegistroSucessoActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}