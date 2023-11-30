package br.udesc.weparty.ui.feed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.udesc.weparty.EventCardAdapter;
import br.udesc.weparty.Model.EventCard;
import br.udesc.weparty.Model.Evento;
import br.udesc.weparty.R;
import br.udesc.weparty.databinding.FragmentFeedBinding;

public class FeedFragment extends Fragment {

    private Context context;
    private FragmentFeedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleFeed);

        List<EventCard> eventCards = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, 11, 29, 22, 00);

            Evento evento = new Evento();
            evento.setName("Nome");
            evento.setDate(calendar);

            EventCard eventCard = new EventCard(evento);
            eventCards.add(eventCard);
        }

        recyclerView.setAdapter(new EventCardAdapter(this.getActivity(), eventCards));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}