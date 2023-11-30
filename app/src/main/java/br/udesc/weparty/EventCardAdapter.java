package br.udesc.weparty;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.udesc.weparty.Activity.EventoActivity;
import br.udesc.weparty.Model.EventCard;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.ViewHolder> {

    private Context context;
    List<EventCard> eventCards;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final TextView date;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textTitle);
            date = (TextView) view.findViewById(R.id.textDate);
        }
    }

    public EventCardAdapter(Context context, List<EventCard> eventCards) {
        super();
        this.context = context;
        this.eventCards = eventCards;
    }

    @NonNull
    @Override
    public EventCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_event_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        EventCard eventCard = eventCards.get(position);

        holder.title.setText(eventCard.getEventTitle());
        holder.date.setText(eventCard.getEventDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventoActivity.class);
                intent.putExtra("id", eventCard.getEvento().getUuidString());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventCards.size();
    }

}
