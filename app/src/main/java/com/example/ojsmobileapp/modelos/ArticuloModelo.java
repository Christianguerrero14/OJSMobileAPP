package com.example.ojsmobileapp.modelos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class ArticuloModelo {
    private String id_ediciones;
    private String titulo;
    private String doi;
    private String fecha;
    private JSONArray galeys;
    private JSONArray authors;

    public ArticuloModelo(JSONObject a) throws JSONException {
        this.id_ediciones = a.getString("publication_id");
        this.titulo = a.getString("title");
        this.doi = a.getString("doi");
        this.fecha = a.getString("date_published");
        this.galeys = a.getJSONArray("galeys");
        this.authors = a.getJSONArray("authors");
    }

    public static ArrayList<ArticuloModelo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<ArticuloModelo> articulos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            articulos.add(new ArticuloModelo(datos.getJSONObject(i)));
        }
        return articulos;
    }

    // Getters
    public String getId_ediciones() {
        return id_ediciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDoi() {
        return doi;
    }

    public String getFecha() {
        return fecha;
    }

    public JSONArray getGaleys() {
        return galeys;
    }
    public JSONArray getAuthors(){
        return authors;
    }
}
