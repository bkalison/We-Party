package br.udesc.weparty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

import br.udesc.weparty.Model.EventCard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (new Handler()).postDelayed(this::startApp, 1000);
        FirebaseApp.initializeApp(this);
        setMockEventsCards();
    }

    private void startApp() {
        startActivity(new Intent(MainActivity.this, LoginRegistroActivity.class));
    }

    public void setMockEventsCards() {
        List<EventCard> eventCards = new ArrayList<>();

        eventCards.add(new EventCard(1, "SEXTA ÀS 1PM", "Evento Teste 1", 10, 10));
        eventCards.add(new EventCard(2, "SEXTA ÀS 2PM", "Evento Teste 2", 20, 20));
        eventCards.add(new EventCard(3, "SEXTA ÀS 3PM", "Evento Teste 3", 30, 30));
        eventCards.add(new EventCard(4, "SEXTA ÀS 4PM", "Evento Teste 4", 40, 40));
        eventCards.add(new EventCard(5, "SEXTA ÀS 5PM", "Evento Teste 5", 50, 50));
        eventCards.add(new EventCard(6, "SEXTA ÀS 6PM", "Evento Teste 6", 60, 60));
        eventCards.add(new EventCard(7, "SEXTA ÀS 7PM", "Evento Teste 7", 70, 70));

    }
}