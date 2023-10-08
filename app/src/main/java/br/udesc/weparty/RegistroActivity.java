package br.udesc.weparty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {


    private static final String PREFS_NAME = "PREFS_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    public void register(){

        EditText FieldName = findViewById(R.id.editTextName);
        EditText FieldEmail = findViewById(R.id.editTextEmail);
        EditText FieldPassword = findViewById(R.id.editTextPassword);
        EditText FieldPasswordVerify = findViewById(R.id.editTextVerify);

        String name = FieldName.getText().toString().trim();
        String email = FieldEmail.getText().toString().trim();
        String password = FieldPassword.getText().toString().trim();
        String passwordVerify = FieldPasswordVerify.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordVerify)){
            Toast.makeText(this,"Todos os campos são Obrigatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordVerify)) {
            Toast.makeText(this, "Senhas diferentes", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();

        Intent intent = new Intent(RegistroActivity.this, RegistroSucessoActivity.class);
        startActivity(intent);

    }


}