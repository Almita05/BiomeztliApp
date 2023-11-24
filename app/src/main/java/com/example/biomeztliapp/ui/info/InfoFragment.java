package com.example.biomeztliapp.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;

public class InfoFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InfoViewModel notificationsViewModel =
                new ViewModelProvider(this).get(InfoViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;

        // Recuperar datos del Intent
        String descripcion = getActivity().getIntent().getStringExtra("DESCRIPCION");
        // Usar los datos en tu TextView
        String displayText = "Descripción: " + descripcion;
        textView.setText(displayText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}