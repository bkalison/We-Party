package br.udesc.weparty.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

import br.udesc.weparty.R;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    String TAG = "TOKEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(this::startApp, 1000);
        FirebaseApp.initializeApp(this);

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(token -> {
            if (!TextUtils.isEmpty(token)) {
                Log.d(TAG, "retrieve token successful : " + token);
            } else {
                Log.w(TAG, "token should not be null...");
            }
        }).addOnFailureListener(e -> {
            //handle e
        }).addOnCanceledListener(() -> {
            //handle cancel
        }).addOnCompleteListener(task -> Log.v(TAG, "This is the token : " + task.getResult()));

//        FirebaseMessaging.getInstance().subscribeToTopic("News")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = "Done";
//                        if (!task.isSuccessful()) {
//                            msg = "Failed";
//                        }
//
//                    }
//                });
    }

    public void startService(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startService(intent);
    }

    private void startApp() {
        startActivity(new Intent(MainActivity.this, LoginRegistroActivity.class));
    }
}