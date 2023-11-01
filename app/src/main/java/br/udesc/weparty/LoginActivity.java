package br.udesc.weparty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.udesc.weparty.Model.User;
import br.udesc.weparty.Utils.FirebaseConfig;

public class LoginActivity extends AppCompatActivity {
    User user;

    FirebaseAuth firebaseAuth;
    private static final String PREFS_NAME = "PREFS_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        firebaseAuth = FirebaseConfig.FirebaseAuthentication();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
    }

    public void checkFields() {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"Todos os campos são Obrigatorios", Toast.LENGTH_SHORT).show();
        }

        if (!email.isEmpty()){
            if (!password.isEmpty()) {
                user = new User();
                user.setEmail(email);
                user.setPassword(password);
                login(user);
            }else {
                Toast.makeText(this,"Preencha o email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(User user) {
        firebaseAuth.signInWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, RegistroSucessoActivity.class);
                    startActivity(intent);
                } else {
                    String exception = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        exception = "Usuário não está cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Email ou senha incorretos";
                    } catch (Exception e) {
                        exception = "Erro ao Logar"+e.getMessage();
                        e.printStackTrace();
                    }
                    Log.d("Error Firebase", task.getException().toString());
                    Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

//    private boolean checkCredentials(String email, String password) {
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        String savedEmail = sharedPreferences.getString("email", "");
//        String savedPassword = sharedPreferences.getString("password", "");
//
//        return email.equals(savedEmail) && password.equals(savedPassword);
//    }



}