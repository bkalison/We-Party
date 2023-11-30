package br.udesc.weparty.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventCard {

    private Evento evento;
    String eventId;
    int eventImage;
    String eventDate;
    String eventTitle;
    int eventLikes;
    int eventComents;

    public EventCard() {
    }

    public EventCard(Evento evento) {
        Date date = evento.getDate().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        String dataEvento = dateFormat.format(date);

        this.evento = evento;
        this.eventDate = dataEvento;
        this.eventTitle = evento.getName();
        this.eventId = evento.getUuidString();
    }

    public EventCard ( int eventImage,String eventDate, String eventTitle, int eventLikes, int eventComents) {
        this.eventImage = eventImage;
        this.eventDate = eventDate;
        this.eventTitle = eventTitle;
        this.eventLikes = eventLikes;
        this.eventComents = eventComents;
    }

    public int getEventImage() {
        return eventImage;
    }

    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public int getEventLikes() {
        return eventLikes;
    }

    public void setEventLikes(int eventLikes) {
        this.eventLikes = eventLikes;
    }

    public int getEventComents() {
        return eventComents;
    }

    public void setEventComents(int eventComents) {
        this.eventComents = eventComents;
    }

    public Evento getEvento() {
        return evento;
    }
}
