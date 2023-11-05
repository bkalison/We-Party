package br.udesc.weparty.Model;

public class EventCard {
    int eventImage;
    String eventDate;
    String eventTitle;
    int eventLikes;
    int eventComents;

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
}
