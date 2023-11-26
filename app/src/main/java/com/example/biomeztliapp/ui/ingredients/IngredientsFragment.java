package com.example.biomeztliapp.ui.ingredients;

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
import com.example.biomeztliapp.databinding.FragmentIngredientsBinding;
import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;

public class IngredientsFragment extends Fragment {

    private FragmentIngredientsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IngredientsModel ingredientsViewModel =
                new ViewModelProvider(this).get(IngredientsModel.class);

        binding = FragmentIngredientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.ingredients;

        // Recuperar datos del Intent
        String ingredientes = getActivity().getIntent().getStringExtra("INGREDIENTES");
        // Usar los datos en tu TextView
        String displayText = ingredientes;
        textView.setText(displayText);
        textView.setTypeface(null, Typeface.NORMAL); // Tipo de texto en negrita
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28); // Tamaño del texto en sp
        textView.setTextColor(Color.BLACK); // Color del texto en azul
        root.setBackgroundColor(getResources().getColor(R.color.verdeBonito)); //Color de fondo
        textView.setTypeface(null, Typeface.NORMAL); // Tipo de texto en negrita
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28); // Tamaño del texto en sp
        textView.setTextColor(Color.BLACK); // Color del texto en azul
        root.setBackgroundColor(getResources().getColor(R.color.morado)); //Color de fondo

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
