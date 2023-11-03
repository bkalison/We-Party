package br.udesc.weparty;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.udesc.weparty.Model.Evento;


public class CriarEventoActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Evento event;
    final Calendar calendar = Calendar.getInstance();
    EditText dataEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                atualizaDataEHora();
            }
        };

        dataEvento = (EditText) findViewById(R.id.editTextDataDoEvento);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,day);
                new TimePickerDialog(CriarEventoActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };
        dataEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CriarEventoActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ImageButton btnVoltar = findViewById(R.id.btnVoltarCriarEvento);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void checkFieldsEvent() {
        EditText editTextNomeEvento = findViewById(R.id.editTextNomeEvento);
        EditText editTextDescricao = findViewById(R.id.editTextDescricao);
        EditText editTextDataDoEvento = findViewById(R.id.editTextDataDoEvento);
        EditText editTextCep = findViewById(R.id.editTextCep);
        EditText editTextCidade = findViewById(R.id.editTextCidade);
        EditText editTextUf = findViewById(R.id.editTextUf);
        EditText editTextBairro = findViewById(R.id.editTextBairro);
        EditText editTextRua = findViewById(R.id.editTextRua);
        EditText editTextNumero = findViewById(R.id.editTextNumero);
        EditText editTextComplemento = findViewById(R.id.editTextComplemento);

        String nome = editTextNomeEvento.getText().toString();
        String descricao = editTextDescricao.getText().toString();
        String data = editTextDataDoEvento.getText().toString();
        String cep = editTextCep.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String estado = editTextUf.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String rua = editTextRua.getText().toString();
        String numero = editTextNumero.getText().toString();
        String complemento = editTextComplemento.getText().toString();

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(descricao) || TextUtils.isEmpty(data) || TextUtils.isEmpty(cep) ||
                TextUtils.isEmpty(cidade) || TextUtils.isEmpty(estado) || TextUtils.isEmpty(bairro) || TextUtils.isEmpty(rua) || TextUtils.isEmpty(numero) || TextUtils.isEmpty(complemento)) {
            Toast.makeText(this, "Todos os campos são Obrigatorios", Toast.LENGTH_SHORT).show();
        }

        if (nome.isEmpty()) {
            Toast.makeText(this, "O campo 'Nome' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (descricao.isEmpty()) {
            Toast.makeText(this, "O campo 'Descrição' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (data.isEmpty()) {
            Toast.makeText(this, "O campo 'Data' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (cep.isEmpty()) {
            Toast.makeText(this, "O campo 'CEP' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (cidade.isEmpty()) {
            Toast.makeText(this, "O campo 'Cidade' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (estado.isEmpty()) {
            Toast.makeText(this, "O campo 'Estado' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (bairro.isEmpty()) {
            Toast.makeText(this, "O campo 'Bairro' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (rua.isEmpty()) {
            Toast.makeText(this, "O campo 'Rua' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (numero.isEmpty()) {
            Toast.makeText(this, "O campo 'Número' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else if (complemento.isEmpty()) {
            Toast.makeText(this, "O campo 'Complemento' é obrigatório.", Toast.LENGTH_SHORT).show();
        } else {
            // Todos os campos estão preenchidos, então crie o evento
            event = new Evento();
            event.setName(nome);
            event.setDescription(descricao);
            event.setCep(cep);
            event.setCity(cidade);
            event.setState(estado);
            event.setDistrict(bairro);
            event.setAddress(rua);
            event.setNumber(numero);
            event.setComplement(complemento);
            event.setDate(calendar);
        }
    }

    private void atualizaDataEHora() {
        String format = "dd/MM/yyyy HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dataEvento.setText(dateFormat.format(calendar.getTime()));
    }

}