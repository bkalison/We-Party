package br.udesc.weparty.Model;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class Evento {
    private String name, address, complement, description, cep, city, state, district, number;
    private Calendar date;

    UUID uuid = UUID.randomUUID();
    String uuidString = uuid.toString();

    public Evento() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getUuidString() {
        return uuidString;
    }

    public void newEvent(){
        Log.e(TAG, "Esta é uma mensagem de erro.");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("event").child(uuidString).setValue(this);
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        // Sucesso ao configurar o valor no banco de dados
//                        Log.d("Firebase", "Valor configurado com sucesso no banco de dados.");
//                        return true;
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // Falha ao configurar o valor no banco de dados
//                        Log.e("Firebase", "Erro ao configurar o valor no banco de dados: " + e.getMessage());
//                        return false;
//                    }
//                });
    }

}
