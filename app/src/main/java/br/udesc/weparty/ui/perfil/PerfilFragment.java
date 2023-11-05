package br.udesc.weparty.ui.perfil;

import static br.udesc.weparty.Activity.RegistroActivity.PREFS_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import br.udesc.weparty.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String nomeDoUsuario = sharedPreferences.getString("nome", "Nome do Usuário Padrão");
        String emailDoUsuario = sharedPreferences.getString("email", "usuario@example.com");

        TextView nomeTextView = binding.textNomePerfil;
        TextView emailTextView = binding.textEmailPerfil;

        nomeTextView.setText(nomeDoUsuario);
        emailTextView.setText(emailDoUsuario);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}