package com.example.ojsmobileapp.modelos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RevistasModelo {

    private String id_revista;
    private String urlimage_revista;
    private String nombre_revista;

    public RevistasModelo(JSONObject a) throws JSONException {
        this.id_revista = a.getString("journal_id");
        this.urlimage_revista = a.getString("portada");
        this.nombre_revista = a.getString("name");
    }

    public static ArrayList<RevistasModelo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<RevistasModelo> revistas = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            revistas.add(new RevistasModelo(datos.getJSONObject(i)));
        }
        return revistas;
    }

    // Getters
    public String getId_revista() {
        return id_revista;
    }

    public String getUrlimage_revista() {
        return urlimage_revista;
    }

    public String getNombre_revista() {
        return nombre_revista;
    }
}