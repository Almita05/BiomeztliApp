package com.example.biomeztliapp.ui.caution;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;

public class CautionFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CautionViewModel notificationsViewModel =
                new ViewModelProvider(this).get(CautionViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;

        // Recuperar datos del Intent
        String precaucion = getActivity().getIntent().getStringExtra("PRECAUCION");
        // Usar los datos en tu TextView
        String displayText = "Precuacion y contraindicaciones: " + precaucion;
        textView.setText(displayText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}