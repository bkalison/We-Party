package br.udesc.weparty;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import br.udesc.weparty.Activity.EventoActivity;
import br.udesc.weparty.Model.Evento;

public class EventCardAdapter extends RecyclerView.Adapter<EventCardAdapter.ViewHolder> {

    List<Evento> eventos;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final TextView date;
        final ImageView image;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.textTitle);
            date = view.findViewById(R.id.textDate);
            image = view.findViewById(R.id.imagePhoto);
        }
    }

    public EventCardAdapter(List<Evento> eventos) {
        super();
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public EventCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_event_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Evento evento = eventos.get(position);

        holder.title.setText(evento.getName());
        holder.date.setText(evento.dateString());
        if(evento.getUrlImage() != null) {
            try {
                URL url = new URL(evento.getUrlImage());
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                holder.image.setImageBitmap(bmp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventoActivity.class);
                intent.putExtra("id", evento.getUuidString());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

}
