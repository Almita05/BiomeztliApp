package com.example.biomeztliapp.ui.notifications;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biomeztliapp.MainAdapter;
import com.example.biomeztliapp.MainModel;
import com.example.biomeztliapp.R;
import com.example.biomeztliapp.databinding.FragmentNotificationsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private List<MainModel> favoritosList = new ArrayList<>();
    private RecyclerView recyclerView;

    private MainAdapter combinedAdapter, mainAdapter;
    private SearchView searchView;
    private FirebaseRecyclerOptions<MainModel> options; // Declarar options como campo de clase

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = view.findViewById(R.id.rvFavoritos);
        searchView = view.findViewById(R.id.searchView3);

        // Configuración del GridLayoutManager con 2 columnas
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("plantas");


        Query combinedQuery = databaseReference
                .orderByChild("favorito")
                .equalTo(true);

        // Configurar el SearchView
        search_view();

        FirebaseRecyclerOptions<MainModel> combinedOptions = new FirebaseRecyclerOptions.Builder<MainModel>()
                .setQuery(combinedQuery, MainModel.class)
                .build();

        combinedAdapter = new MainAdapter(combinedOptions, this);
        mainAdapter = new MainAdapter(combinedOptions, this); // Cambia options por combinedOptions
        recyclerView.setAdapter(mainAdapter);


        return view;
    }

    private void txtSearch(String s) {
        // Configurar las opciones de FirebaseRecyclerAdapter con una consulta filtrada por destino
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("plantas").orderByChild("nombre").startAt(s).endAt(s + "\uf8ff"), MainModel.class)
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        combinedAdapter.stopListening();
    }


    public void addFavorito(MainModel model) {
        favoritosList.add(model);
        combinedAdapter.notifyDataSetChanged();
    }

    public void removeFavorito(MainModel model) {
        favoritosList.remove(model);
        combinedAdapter.notifyDataSetChanged();
    }


}
