package com.example.ojsmobileapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
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
import com.example.ojsmobileapp.adaptadores.EdicionesAdaptador;
import com.example.ojsmobileapp.modelos.EdicionesModelo;

import org.json.JSONArray;

import java.util.ArrayList;

public class EdicionesActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<EdicionesAdaptador.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones);

        try {
            Bundle bundle = getIntent().getExtras();
            String idioma = bundle != null ? bundle.getString("idioma") : "";
            String id = bundle != null ? bundle.getString("id") : "";

            TextView txt = findViewById(R.id.texttituloediciones);
            RecyclerView recyclerView = findViewById(R.id.edicionesrecycler);
            layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);

            switch (idioma) {
                case "es_ES":
                    txt.setText("EDICIONES DE LAS REVISTAS UTEQ");
                    break;
                case "en_US":
                    txt.setText("EDITIONS OF UTEQ MAGAZINES");
                    break;
                case "pt_BR":
                    txt.setText("EDIÇÕES DAS REVISTA UTEQ");
                    break;
            }

            String url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + id + "&locale=" + idioma;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        try {
                            JSONArray JSONlista = new JSONArray(response);
                            ArrayList<EdicionesModelo> lstUsuarios = EdicionesModelo.JsonObjectsBuild(JSONlista);

                            int resId = R.anim.layout_animation_down_to_up;
                            recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, resId));

                            adapter = new EdicionesAdaptador(lstUsuarios, idioma);
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            txt.setText(e.getMessage());
                        }
                    },
                    error -> txt.setText(error.getMessage())
            );

            Volley.newRequestQueue(this).add(stringRequest);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}