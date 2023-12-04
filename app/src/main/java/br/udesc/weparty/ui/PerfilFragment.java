package br.udesc.weparty.ui;

import static br.udesc.weparty.Activity.RegistroActivity.PREFS_NAME;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.udesc.weparty.Activity.CriarEventoActivity;
import br.udesc.weparty.Activity.LoginRegistroActivity;
import br.udesc.weparty.Activity.MainActivity;
import br.udesc.weparty.Model.Evento;
import br.udesc.weparty.Model.User;
import br.udesc.weparty.Utils.FirebaseConfig;
import br.udesc.weparty.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView nomeTextView = binding.textNomePerfil;
        TextView emailTextView = binding.textEmailPerfil;
        Button btnDeslogar = binding.btnDeslogar;

        String userUuid = FirebaseConfig.FirebaseAuthentication().getCurrentUser().getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(userUuid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    User user = snapshot.getValue(User.class);

                    nomeTextView.setText(user.getName());
                    emailTextView.setText(user.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseConfig.FirebaseAuthentication().signOut();
                Intent intent = new Intent(getActivity(), LoginRegistroActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}