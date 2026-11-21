package com.example.tp5_devmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etPrenom, etPoids, etTaille;
    private RadioButton rbHomme, rbFemme;
    private Button btnCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrenom = findViewById(R.id.etPrenom);
        etPoids = findViewById(R.id.etPoids);
        etTaille = findViewById(R.id.etTaille);
        rbHomme = findViewById(R.id.rbHomme);
        rbFemme = findViewById(R.id.rbFemme);
        btnCalculer = findViewById(R.id.btnCalculer);

        btnCalculer.setOnClickListener(v -> {
            String prenom = etPrenom.getText().toString().trim();
            String sPoids = etPoids.getText().toString().trim();
            String sTaille = etTaille.getText().toString().trim();

            // Vérification des champs
            if (prenom.isEmpty() || sPoids.isEmpty() || sTaille.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double poids = Double.parseDouble(sPoids);
            double taille = Double.parseDouble(sTaille);

            if (taille <= 0 || poids <= 0) {
                Toast.makeText(this, "Valeurs invalides", Toast.LENGTH_SHORT).show();
                return;
            }
            double imc = poids / (taille * taille);
            String civilite = rbHomme.isChecked() ? "Monsieur" : "Madame";
            Intent intent = new Intent(MainActivity.this, Activity_Resultat.class);
            intent.putExtra("civilite", civilite);
            intent.putExtra("prenom", prenom);
            intent.putExtra("imc", imc);
            startActivity(intent);
        });
    }
}