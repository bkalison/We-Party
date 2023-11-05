package br.udesc.weparty;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import br.udesc.weparty.Model.EventCard;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {

    private List<EventCard> eventCards;

    @NonNull
    @Override
    public AdapterUsers.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventcard_adapter, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsers.MyViewHolder holder, int position) {
        holder.textView.setText(eventCards.get(position).getEventTitle());
    }

    @Override
    public int getItemCount(){
        return eventCards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textName);
        }

        public void bind(String data) {
            textView.setText(data);
        }
    }
}
