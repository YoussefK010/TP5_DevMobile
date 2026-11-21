package com.example.tp5_devmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class Activity_Resultat extends AppCompatActivity {

    private TextView textIMC, textETAT;
    private Button btnBack, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        textIMC = findViewById(R.id.textIMC);
        textETAT = findViewById(R.id.textETAT);
        btnBack = findViewById(R.id.btnBack);
        btnCall = findViewById(R.id.btnCall);
        Intent intent = getIntent();
        String civilite = intent.getStringExtra("civilite");
        String prenom = intent.getStringExtra("prenom");
        double imc = intent.getDoubleExtra("imc", 0);

        textIMC.setText(String.format(Locale.US,"%s %s votre IMC est : %.2f", civilite, prenom, imc));
        String etat;
        int couleur;
        if (imc < 18.5) {
            etat = "maigreur";
            couleur = R.color.colorOrange;
        } else if (imc < 25) {
            etat = "normal";
            couleur = R.color.colorGreen;
        } else if (imc < 30) {
            etat = "surpoids";
            couleur = R.color.colorOrange;
        } else {
            etat = "obésité";
            couleur = R.color.colorRed;
        }
        textETAT.setText(etat);
        textETAT.setTextColor(ContextCompat.getColor(this, couleur));
        btnBack.setOnClickListener(v -> finish());
        btnCall.setOnClickListener(v -> {
            Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123"));
            startActivity(call);
        });
    }
}