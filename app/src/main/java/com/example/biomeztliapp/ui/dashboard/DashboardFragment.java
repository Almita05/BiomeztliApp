package com.example.biomeztliapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.biomeztliapp.MainAdapter;
import com.example.biomeztliapp.MainModel;
import com.example.biomeztliapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainAdapter adapter;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        recyclerView = view.findViewById(R.id.rvEnfermedades);

        // Configuración del GridLayoutManager con 2 columnas
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        // Obtén una referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference().child("enfermedades");

        // Configura las opciones del adaptador
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(databaseReference, MainModel.class)
                        .build();

        // Inicializa el adaptador con las opciones configuradas
        adapter = new MainAdapter(options);

        // Asigna el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Conecta el adaptador a la base de datos y empieza a escuchar cambios
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Detén la escucha de cambios cuando el fragmento se detenga
        adapter.stopListening();
    }
}
