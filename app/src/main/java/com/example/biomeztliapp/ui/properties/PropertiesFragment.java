package com.example.biomeztliapp.ui.properties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;

public class PropertiesFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PropertiesViewModel notificationsViewModel =
                new ViewModelProvider(this).get(PropertiesViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;

        // Recuperar datos del Intent
        String propiedades = getActivity().getIntent().getStringExtra("PROPIEDADES");
        // Usar los datos en tu TextView
        String displayText = "Propiedades: " + propiedades;
        textView.setText(displayText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}