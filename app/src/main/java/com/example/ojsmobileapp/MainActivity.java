package com.example.ojsmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ojsmobileapp.extras.Permissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Permissions adminPermisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spineridiomas = findViewById(R.id.spinner);
        List<String> listaidiomas = Arrays.asList("ESPAÑOL", "ENGLISH", "PORTUGUêS");

        ArrayAdapter<String> adaptadoridiomas = new ArrayAdapter<>(this, R.layout.spinner_team, listaidiomas);
        spineridiomas.setAdapter(adaptadoridiomas);

        adminPermisos = new Permissions(this);

        ArrayList<String> permisosSolicitados = new ArrayList<>();

        ArrayList<String> permisosAprobados = adminPermisos.getPermisosAprobados(permisosSolicitados);
        ArrayList<String> listPermisosNOAprob = adminPermisos.getPermisosNoAprobados(permisosSolicitados);

        adminPermisos.getPermission(listPermisosNOAprob);
    }

    public void onClick(View v) {
        Spinner spineridiomas = findViewById(R.id.spinner);
        String text = spineridiomas.getSelectedItem().toString();
        String idioma;
        Intent intent = new Intent(this, RevistasActivity.class);
        Bundle bundle = new Bundle();

        switch (text) {
            case "ESPAÑOL":
                Toast.makeText(getApplicationContext(), "BIENVENIDO", Toast.LENGTH_SHORT).show();
                idioma = "es_ES";
                bundle.putString("idioma", idioma);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case "ENGLISH":
                Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_SHORT).show();
                idioma = "en_US";
                bundle.putString("idioma", idioma);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case "PORTUGUêS":
                Toast.makeText(getApplicationContext(), "BEM VINDO", Toast.LENGTH_SHORT).show();
                idioma = "pt_BR";
                bundle.putString("idioma", idioma);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}