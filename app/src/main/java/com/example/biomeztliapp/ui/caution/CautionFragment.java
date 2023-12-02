package com.example.biomeztliapp.ui.caution;

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
import com.example.biomeztliapp.databinding.FragmentCautionBinding;
import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;

public class CautionFragment extends Fragment {

    private FragmentCautionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CautionViewModel notificationsViewModel =
                new ViewModelProvider(this).get(CautionViewModel.class);

        binding = FragmentCautionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.caution;

        // Recuperar datos del Intent
        String precaucion = getActivity().getIntent().getStringExtra("PRECAUCION");
        // Usar los datos en el TextView
        String displayText = precaucion;
        textView.setText(displayText);
        textView.setTypeface(null, Typeface.NORMAL); // Tipo de texto en negrita
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28); // Tamaño del texto en sp
        textView.setTextColor(Color.BLACK); // Color del texto en azul
        root.setBackgroundColor(getResources().getColor(R.color.fondoDemas)); //Color de fondo
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}