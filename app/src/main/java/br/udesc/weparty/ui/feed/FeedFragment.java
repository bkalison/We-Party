package br.udesc.weparty.ui.feed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.udesc.weparty.EventCardAdapter;
import br.udesc.weparty.Model.EventCard;
import br.udesc.weparty.R;
import br.udesc.weparty.databinding.FragmentFeedBinding;

public class FeedFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context context;
    private FragmentFeedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*FeedViewModel feedViewModel =
                new ViewModelProvider(this).get(FeedViewModel.class);*/

        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleFeed);

        List<EventCard> eventCards = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            EventCard eventCard = new EventCard();
            eventCard.setEventTitle("Evento " + (i + 1));
            eventCard.setEventDate("Dia " + (i + 7));
            eventCards.add(eventCard);
        }

        recyclerView.setAdapter(new EventCardAdapter(this.getActivity(), eventCards));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

        //recyclerView.setOnItem(this.getActivity());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this.getActivity(),
                new StringBuilder("teste: ").append(position).toString(),
                Toast.LENGTH_LONG).show();
    }
}