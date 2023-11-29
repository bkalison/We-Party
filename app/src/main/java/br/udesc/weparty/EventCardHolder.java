package br.udesc.weparty;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import br.udesc.weparty.Model.EventCard;

public class EventCardHolder extends RecyclerView.ViewHolder {

    final TextView title;
    final TextView date;

    public EventCardHolder(@NonNull View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.textTitle);
        date = (TextView) view.findViewById(R.id.textDate);
    }
}
