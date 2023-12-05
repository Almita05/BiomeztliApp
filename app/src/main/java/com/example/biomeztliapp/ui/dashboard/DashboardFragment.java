package com.example.biomeztliapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
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
    private MainAdapter adapter, mainAdapter;
    private DatabaseReference databaseReference;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = view.findViewById(R.id.rvEnfermedades);
        searchView = view.findViewById(R.id.searchView2);

        // Inicializa databaseReference con la referencia correcta de la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference().child("enfermedades");
        // Configurar el SearchView
        search_view();
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(databaseReference, MainModel.class)
                        .build();

        adapter = new MainAdapter(options, this);

        // Configurar el clic en el adaptador
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
                // Iniciar la actividad
                startActivity(intent);
            }
        });

        // Asigna el adaptador al RecyclerView después de configurarlo
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mainAdapter = new MainAdapter(options, this);
        recyclerView.setAdapter(mainAdapter);

        return view;
    }

    private void txtSearch(String s) {
        // Configurar las opciones de FirebaseRecyclerAdapter con una consulta filtrada por destino
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("enfermedades").orderByChild("nombre").startAt(s).endAt(s + "\uf8ff"), MainModel.class)
                        .build();

        mainAdapter.updateOptions(options);
        mainAdapter.notifyDataSetChanged();
    }



    // Configurar el SearchView
    private void search_view() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}

