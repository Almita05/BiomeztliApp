package com.example.biomeztliapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

public class MainActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inicializa FirebaseApp antes de acceder a Firebase
        FirebaseApp.initializeApp(this);

        // Configurar NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);

        // Configurar el AppBarConfiguration si es necesario
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        // Comenta o elimina la siguiente línea si no estás utilizando un ActionBar
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Configura el BottomNavigationView con NavController
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);

        // Usar la variable de instancia appBarConfiguration
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
