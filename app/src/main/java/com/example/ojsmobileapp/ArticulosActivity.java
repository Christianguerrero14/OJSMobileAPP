package com.example.ojsmobileapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ojsmobileapp.R;
import com.example.ojsmobileapp.adaptadores.ArticulosAdaptador;
import com.example.ojsmobileapp.modelos.ArticuloModelo;

import org.json.JSONArray;

import java.util.ArrayList;

public class ArticulosActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<ArticulosAdaptador.ViewHolder> adapter;
    private String id;
    private String idioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);

        try {
            Bundle bundle = getIntent().getExtras();
            idioma = bundle != null ? bundle.getString("idioma") : "";
            id = bundle != null ? bundle.getString("id") : "";

            TextView txt = findViewById(R.id.texttituloarticulos);
            RecyclerView recyclerView = findViewById(R.id.articulosrecycler);
            layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            switch (idioma) {
                case "es_ES":
                    txt.setText("ARTÍCULOS DE LAS REVISTAS UTEQ");
                    break;
                case "en_US":
                    txt.setText("ARTICLES FROM UTEQ MAGAZINES");
                    break;
                case "pt_BR":
                    txt.setText("ARTIGOS DA REVISTA UTEQ");
                    break;
            }

            String url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + id + "&locale=" + idioma;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        try {
                            JSONArray JSONlista = new JSONArray(response);
                            ArrayList<ArticuloModelo> lstUsuarios = ArticuloModelo.JsonObjectsBuild(JSONlista);

                            int resId = R.anim.layout_animation_down_to_up;
                            recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, resId));

                            adapter = new ArticulosAdaptador(lstUsuarios, idioma);
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            txt.setText(e.getMessage());
                        }
                    },
                    error -> txt.setText(error.getMessage()));

            Volley.newRequestQueue(this).add(stringRequest);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void viewpdf(View v) {
        Toast.makeText(getApplicationContext(), "YEAH", Toast.LENGTH_LONG).show();
    }
}