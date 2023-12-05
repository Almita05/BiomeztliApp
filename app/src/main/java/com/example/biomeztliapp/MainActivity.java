package com.example.biomeztliapp;


import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtengo la referencia de la imagen
        ImageView logoImageView = findViewById(R.id.logoImageView);

        // Obtengo la referencia del botón
        Button botonPrincipal = findViewById(R.id.botonPrincipal);

        // Creo la animación para la imagen y boton
        ObjectAnimator descentAnimationImage = ObjectAnimator.ofFloat(
                logoImageView,
                "translationY",
                250f // La distancia a la que desciende la imagen
        );
        descentAnimationImage.setDuration(1400); // Duración de la animación en milisegundos

        ObjectAnimator descentAnimationButton = ObjectAnimator.ofFloat(
                botonPrincipal,
                "translationY",
                250f // La distancia a la que desciende el botón
        );
        descentAnimationButton.setDuration(1400);

        // Inicia ambas animaciones
        descentAnimationImage.start();
        descentAnimationButton.start();

         //Configura un listener para el clic del botón
        botonPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pasamos a la actividad 2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}
