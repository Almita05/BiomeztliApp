package com.example.biomeztliapp.ui.use;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;

public class UseFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UseViewModel notificationsViewModel =
                new ViewModelProvider(this).get(UseViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;

        // Recuperar datos del Intent
        String uso = getActivity().getIntent().getStringExtra("USO");
        // Usar los datos en tu TextView
        String displayText = "Uso: " + uso;
        textView.setText(displayText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}