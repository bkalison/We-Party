package br.udesc.weparty.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.URL;

import br.udesc.weparty.Model.Evento;
import br.udesc.weparty.R;

public class EventoActivity extends AppCompatActivity {

    private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        Bundle bundle = getIntent().getExtras();
        String id = null;
        if(bundle != null){
             id = bundle.getString("id");
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ImageView imageEvento = findViewById(R.id.imageEvento);
        TextView textEventoData = findViewById(R.id.textEventoData);
        TextView textEventoNome = findViewById(R.id.textEventoNome);
        TextView TextEventoDescricao = findViewById(R.id.TextEventoDescricao);
        ImageButton btnVoltar = findViewById(R.id.btnVoltarEvento);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("event").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    evento = snapshot.getValue(Evento.class);

                    textEventoData.setText(evento.dateString());
                    textEventoNome.setText(evento.getName());
                    TextEventoDescricao.setText(evento.getDescription());
                }

                if(evento.getUrlImage() != null) {
                    try {
                        URL url = new URL(evento.getUrlImage());
                        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        imageEvento.setImageBitmap(bmp);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}