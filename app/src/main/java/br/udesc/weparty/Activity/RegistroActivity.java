package br.udesc.weparty.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.udesc.weparty.Model.User;
import br.udesc.weparty.R;
import br.udesc.weparty.Utils.FirebaseConfig;

public class RegistroActivity extends AppCompatActivity {

    User user;
    FirebaseAuth firebaseAuth;

    public static final String PREFS_NAME = "PREFS_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                checkFields();
            }
        });

    }

    public void checkFields() {
        EditText FieldName = findViewById(R.id.editTextName);
        EditText FieldEmail = findViewById(R.id.editTextEmail);
        EditText FieldPassword = findViewById(R.id.editTextPassword);
        EditText FieldPasswordVerify = findViewById(R.id.editTextVerify);

        String name = FieldName.getText().toString();
        String email = FieldEmail.getText().toString();
        String password = FieldPassword.getText().toString().trim();
        String confirmPassword = FieldPasswordVerify.getText().toString().trim();


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this,"Todos os campos são Obrigatorios", Toast.LENGTH_SHORT).show();
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Senhas diferentes", Toast.LENGTH_SHORT).show();
        }

        if (!name.isEmpty()){
            if (!email.isEmpty()) {
                if (!password.isEmpty()) {
                    if (!confirmPassword.isEmpty()) {

                        if (password.equals(confirmPassword)) {
                            // CHAMA CADASTRO

                            user = new User();
                            user.setName(name);
                            user.setEmail(email);

                            register(email, password);

                        }else {
                            Toast.makeText(this,"As Senhas devem ser iguais", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this,"Preencha a confirmação de senha", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,"Preencha a senha", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"Preencha o email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(String email, String password){

        firebaseAuth = FirebaseConfig.FirebaseAuthentication();

        firebaseAuth.createUserWithEmailAndPassword(
                email, password
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegistroActivity.this,"Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                    user.newUser(firebaseAuth.getCurrentUser().getUid());
                } else {
                    String exception = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        exception = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Digite um email válido";
                    } catch (FirebaseAuthUserCollisionException e ) {
                        exception = "Esta conta já existe, tente logar";
                        e.printStackTrace();
                    } catch (Exception e) {
                        exception = "Erro ao cadastrar usuário"+e.getMessage();
                        e.printStackTrace();
                    }
                    Log.d("Error Firebase", task.getException().toString());
                    Toast.makeText(RegistroActivity.this, exception, Toast.LENGTH_SHORT).show();

                }
            }
        });

        /*SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nome", user.getName());
        editor.putString("email", user.getEmail());
        editor.apply();*/

        Intent intent = new Intent(RegistroActivity.this, RegistroSucessoActivity.class);
        startActivity(intent);

    }


}