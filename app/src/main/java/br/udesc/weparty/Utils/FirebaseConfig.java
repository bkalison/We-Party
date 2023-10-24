package br.udesc.weparty.Utils;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConfig {
    private static FirebaseAuth auth;

    public static FirebaseAuth FirebaseAuthentication() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }

        return auth;
    }
}
