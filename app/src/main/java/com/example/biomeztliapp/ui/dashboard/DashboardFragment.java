package com.example.biomeztliapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biomeztliapp.MainActivity3;
import com.example.biomeztliapp.MainActivity4;
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

        // Inicializa databaseReference con la referencia correcta de tu base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference().child("enfermedades"); // Reemplaza "tu_nodo_de_datos" con el nombre de tu nodo

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(databaseReference, MainModel.class)
                        .build();

        adapter = new MainAdapter(options, this); // Pasa una referencia al fragmento

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Configurar el clic en el adaptador (puedes hacerlo aquí o en tu adaptador)
        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String imageUrl, String nombre, String descripcion, String propiedades, String uso, String precaucion) {
                // Crear un Intent y agregar la información extra
                Intent intent = new Intent(getContext(), MainActivity4.class);
                intent.putExtra("IMAGE_URL", imageUrl);
                intent.putExtra("NOMBRE", nombre);
                intent.putExtra("DESCRIPCION", descripcion);
                intent.putExtra("PROPIEDADES", propiedades);
                intent.putExtra("USO", uso);
                intent.putExtra("PRECAUCION", precaucion);

                // También puedes agregar información adicional para Activity4 aquí
                // intent.putExtra("OTRO_DATO", otroDato);

                // Iniciar la actividad
                startActivity(intent);
            }
        });

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
