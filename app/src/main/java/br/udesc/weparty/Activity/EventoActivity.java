package br.udesc.weparty.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.udesc.weparty.Model.EventCard;
import br.udesc.weparty.R;

public class EventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        Bundle bundle = getIntent().getExtras();
        String id = null;
        if(bundle != null){
             id = bundle.getString("id");
        }

        TextView textEventoData = findViewById(R.id.textEventoData);
        TextView textEventoNome = findViewById(R.id.textEventoNome);
        TextView textDescricao = findViewById(R.id.textDescricao);
        TextView TextEventoDescricao = findViewById(R.id.TextEventoDescricao);
        ImageButton btnVoltar = findViewById(R.id.btnVoltarEvento);

        textEventoData.setText(id);
        textEventoNome.setText(id);
        textDescricao.setText(id);
        TextEventoDescricao.setText(id);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}