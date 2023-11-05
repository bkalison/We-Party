package br.udesc.weparty.ui.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import br.udesc.weparty.Activity.CriarEventoActivity;
import br.udesc.weparty.Activity.EventoActivity;
import br.udesc.weparty.R;
import br.udesc.weparty.databinding.FragmentEventosBinding;

public class EventosFragment extends Fragment {

    private FragmentEventosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EventosViewModel eventosViewModel =
                new ViewModelProvider(this).get(EventosViewModel.class);

        binding = FragmentEventosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button btnAddEvento = (Button) view.findViewById(R.id.btnAddEvento);
        btnAddEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CriarEventoActivity.class);
                startActivity(intent);
            }
        });

        Button btnProvisorio = (Button) view.findViewById(R.id.btnProvisorio);
        btnProvisorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}