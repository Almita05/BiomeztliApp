package com.example.biomeztliapp.ui.info;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.biomeztliapp.R;
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
        String displayText = descripcion;
        textView.setText(displayText);
        // Modificar el tipo de texto, tamaño y color
        textView.setText(displayText);
        textView.setTypeface(null, Typeface.NORMAL); // Tipo de texto en negrita
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36); // Tamaño del texto en sp
        textView.setTextColor(Color.BLACK); // Color del texto en azul
        root.setBackgroundColor(getResources().getColor(R.color.info)); //Color de fondo

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}