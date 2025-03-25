package com.example.ojsmobileapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.ojsmobileapp.R;
import com.example.ojsmobileapp.adaptadores.RevistasAdaptador;
import com.example.ojsmobileapp.extras.TrustedManager;
import com.example.ojsmobileapp.extras.MySingleton;
import com.example.ojsmobileapp.modelos.RevistasModelo;

import org.json.JSONArray;

import java.util.ArrayList;

public class RevistasActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter<RevistasAdaptador.ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revistas);

        try {
            Bundle bundle = getIntent().getExtras();
            String idioma = bundle != null ? bundle.getString("idioma") : "";

            TextView txt = findViewById(R.id.texttitulo);
            RecyclerView recyclerView = findViewById(R.id.revistasrecycler);
            layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            switch (idioma) {
                case "es_ES":
                    txt.setText("LISTAS DE REVISTAS UTEQ");
                    break;
                case "en_US":
                    txt.setText("UTEQ MAGAZINE LISTS");
                    break;
                case "pt_BR":
                    txt.setText("LISTAS DA REVISTA UTEQ");
                    break;
            }

            TrustedManager.allowAllSSL();

            String url = "https://revistas.uteq.edu.ec/ws/journals.php?locale=" + idioma;
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    response -> {
                        try {
                            JSONArray JSONlista = new JSONArray(response);
                            ArrayList<RevistasModelo> lstUsuarios = RevistasModelo.JsonObjectsBuild(JSONlista);

                            int resId = R.anim.layout_animation_down_to_up;
                            recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, resId));

                            adapter = new RevistasAdaptador(lstUsuarios, idioma);
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            txt.setText(e.getMessage());
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> {
                        txt.setText(error.getMessage());
                        Toast.makeText(getApplicationContext(), "Error 2: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {

    }
}