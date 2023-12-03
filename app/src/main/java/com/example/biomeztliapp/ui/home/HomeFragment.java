package com.example.biomeztliapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.biomeztliapp.MainAdapter;
import com.example.biomeztliapp.MainModel;
import com.example.biomeztliapp.R;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private SearchView searchView;

    public HomeFragment() {
        // Constructor vacío requerido por Fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Buscar el SearchView en la vista del fragmento
        searchView = view.findViewById(R.id.searchView);
        setupSearchView();

        recyclerView = view.findViewById(R.id.rv);

        // Configuración del GridLayoutManager con 2 columnas
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<MainModel> options =
                        new FirebaseRecyclerOptions.Builder<MainModel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("plantas"), MainModel.class)
                                .build();

        mainAdapter = new MainAdapter(options, this);
        recyclerView.setAdapter(mainAdapter);

        return view;
    }

    // Método para manejar cambios en los datos
    private void onDataChange(List<MainModel> newData) {
        mainAdapter.getOriginalList().clear();
        mainAdapter.getOriginalList().addAll(newData);
        mainAdapter.getFilteredList().clear();
        mainAdapter.getFilteredList().addAll(newData);
        mainAdapter.notifyDataSetChanged();
    }

    // Configurar SearchView
    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // No es necesario en este contexto
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("HomeFragment", "onQueryTextChange: " + newText);
                mainAdapter.filter(newText);
                return true;
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
