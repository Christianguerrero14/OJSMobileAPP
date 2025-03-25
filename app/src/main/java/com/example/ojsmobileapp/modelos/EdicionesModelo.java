package com.example.ojsmobileapp.modelos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EdicionesModelo {

    private String id_ediciones;
    private String urlimage_ediciones;
    private String descripcion;

    public EdicionesModelo(JSONObject a) throws JSONException {
        this.id_ediciones = a.getString("issue_id");
        this.urlimage_ediciones = a.getString("cover");
        this.descripcion = "Volumen: " + a.getString("volume") +
                "\nNumber: " + a.getString("number") +
                "\nYear: " + a.getString("year");
    }

    public static ArrayList<EdicionesModelo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<EdicionesModelo> ediciones = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            ediciones.add(new EdicionesModelo(datos.getJSONObject(i)));
        }
        return ediciones;
    }

    // Getters
    public String getId_ediciones() {
        return id_ediciones;
    }

    public String getUrlimage_ediciones() {
        return urlimage_ediciones;
    }

    public String getDescripcion() {
        return descripcion;
    }
}