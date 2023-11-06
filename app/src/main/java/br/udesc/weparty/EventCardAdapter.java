package br.udesc.weparty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.udesc.weparty.Model.EventCard;

public class EventCardAdapter extends RecyclerView.Adapter {

    private Context context;
    List<EventCard> eventCards;

    public EventCardAdapter(Context context, List<EventCard> eventCards) {
        super();
        this.context = context;
        this.eventCards = eventCards;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_event_card, parent, false);
        EventCardHolder holder = new EventCardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        EventCardHolder holder = (EventCardHolder) viewHolder;
        EventCard eventCard = eventCards.get(position);

        holder.title.setText(eventCard.getEventTitle());
        holder.date.setText(eventCard.getEventDate());
    }

    @Override
    public int getItemCount() {
        return eventCards.size();
    }

}
