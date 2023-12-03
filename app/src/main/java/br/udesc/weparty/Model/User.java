package br.udesc.weparty.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private String uuidString, name, email;

    public User() {}

    public String getUuidString() {
        return uuidString;
    }

    public void setUuidString(String uuidString) {
        this.uuidString = uuidString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void newUser(String uuidString) {
        this.uuidString = uuidString;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("user").child(uuidString).setValue(this);
    }
}
