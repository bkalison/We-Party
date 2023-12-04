package br.udesc.weparty.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.udesc.weparty.Activity.CriarEventoActivity;
import br.udesc.weparty.Activity.EventoActivity;
import br.udesc.weparty.EventCardAdapter;
import br.udesc.weparty.Model.Evento;
import br.udesc.weparty.R;
import br.udesc.weparty.Utils.FirebaseConfig;
import br.udesc.weparty.databinding.FragmentEventosBinding;

public class EventosFragment extends Fragment {

    private FragmentEventosBinding binding;
    List<Evento> eventos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEventosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        eventos = new ArrayList<>();

        Button btnAddEvento = (Button) view.findViewById(R.id.btnAddEvento);
        btnAddEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CriarEventoActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycleEventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                    Evento evento = dn.getValue(Evento.class);
                    if(evento.getCreator().equals(FirebaseConfig.FirebaseAuthentication().getCurrentUser().getUid()))  {
                        eventos.add(evento);
                    }
                }
                recyclerView.setAdapter(new EventCardAdapter(eventos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}