package br.udesc.weparty.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.udesc.weparty.Model.CepResponse;
import br.udesc.weparty.Model.CepService;
import br.udesc.weparty.Model.Evento;
import br.udesc.weparty.Model.UploadResponse;
import br.udesc.weparty.Model.UploadService;
import br.udesc.weparty.R;
import br.udesc.weparty.Utils.FirebaseConfig;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CriarEventoActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Evento event;
    final Calendar calendar = Calendar.getInstance();
    EditText dataEvento;
    EditText editTextNomeEvento;
    EditText editTextDescricao;
    EditText editTextDataDoEvento;
    EditText editTextCep;
    EditText editTextCidade;
    EditText editTextUf;
    EditText editTextBairro;
    EditText editTextRua;
    EditText editTextNumero;
    EditText editTextComplemento;
    Button btnAdicionarEvento;
    Button btnUpload;
    Bitmap image;

    String uploadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        btnUpload = findViewById(R.id.btn_upload);
        editTextNomeEvento = findViewById(R.id.editTextNomeEvento);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextDataDoEvento = findViewById(R.id.editTextDataDoEvento);
        editTextCep = findViewById(R.id.editTextCep);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextUf = findViewById(R.id.editTextUf);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextRua = findViewById(R.id.editTextRua);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        btnAdicionarEvento = findViewById(R.id.btnAdicionarEvento);

        editTextCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 8) {
                    buscarInfoCEP(editable.toString());
                }
            }
        });

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
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
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

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(CriarEventoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CriarEventoActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                } else {
                    tirarFoto();
                }
            }
        });

        btnAdicionarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CriarEventoActivity.this, "Evento Criado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CriarEventoActivity.this, HomeActivity.class);
                checkFields();
                startActivity(intent);
            }
        });
    }

    public void checkFields() {
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
        } else {

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
            event.setDate(calendar.getTime());
            event.setUrlImage(uploadedImage);
            event.setCreator(FirebaseConfig.FirebaseAuthentication().getCurrentUser().getUid());

            event.newEvent();

        }
    }

    private void buscarInfoCEP(String cep){
        String baseUrl = "https://viacep.com.br/ws/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CepService cepService = retrofit.create(CepService.class);

        Call<CepResponse> call = cepService.buscarCep(cep);

        call.enqueue(new Callback<CepResponse>() {
            @Override
            public void onResponse(Call<CepResponse> call, Response<CepResponse> response) {


                if (response.isSuccessful()) {
                    CepResponse cepInfo = response.body();
                    if (cepInfo != null) {
                        editTextRua.setText(cepInfo.getLogradouro());
                        editTextBairro.setText(cepInfo.getBairro());
                        editTextCidade.setText(cepInfo.getLocalidade());
                        editTextUf.setText(cepInfo.getUf());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "CEP não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CepResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao buscar informações de CEP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void atualizaDataEHora() {
        String format = "dd/MM/yyyy HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dataEvento.setText(dateFormat.format(calendar.getTime()));
    }

    public void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            uploadImage(imageBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private File bitmapToFile(Bitmap bitmap) {
        // Converte o Bitmap para um arquivo temporário
        File filesDir = getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, "temp_image.jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageFile;
    }

    public void uploadImage(Bitmap imageBitmap) {


        String BASE_URL = "https://generalapis.space/chat/api/public/";

        File file = bitmapToFile(imageBitmap);

        // Criação da parte da imagem (MultipartBody.Part)
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);



        // Criação do Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://generalapis.space/chat/api/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criação do serviço da API
        UploadService uploadService = retrofit.create(UploadService.class);

        // Chamada para enviar a imagem
        Call<UploadResponse> call = uploadService.uploadImage(photoPart);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                // Manipule a resposta aqui
                if (response.isSuccessful()) {
                    UploadResponse uploadResponse = response.body();
                    String image = (String) uploadResponse.getImageUrl();
                    setUploadedImage(image);
                    // Faça algo com a resposta
                } else {
                    // Trate os erros aqui
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {

            }
        });
    }

    public String getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(String uploadedImage) {
        this.uploadedImage = uploadedImage;
    }
}
