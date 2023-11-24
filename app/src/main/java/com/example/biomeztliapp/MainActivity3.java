package com.example.biomeztliapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.example.biomeztliapp.R;
import com.example.biomeztliapp.databinding.ActivityMain3Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main3);

        // Set up the BottomNavigationView with NavController
        NavigationUI.setupWithNavController(navView, navController);

        ImageView imageView = findViewById(R.id.img1);
        TextView textView = findViewById(R.id.nombrePlanta);

        // Obtengo la información de la imagen y el nombre a través de un EXTRA de Intent
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");
        String nombre = getIntent().getStringExtra("NOMBRE");

        // Carga la imagen en el ImageView usando Glide
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        // Asigno el nombre al TextView
        textView.setText(nombre);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    // Override onSupportNavigateUp to provide proper Up navigation
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main3);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
