package com.example.ojsmobileapp.modelos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GaleysModelo {

    private String galley_id;
    private String label;
    private String file_id;
    private String urlViewGalley;

    public GaleysModelo(JSONObject a) throws JSONException {
        this.galley_id = a.getString("galley_id");
        this.label = a.getString("label");
        this.file_id = a.getString("file_id");
        this.urlViewGalley = a.getString("UrlViewGalley");
    }

    public static ArrayList<GaleysModelo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<GaleysModelo> galeys = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            galeys.add(new GaleysModelo(datos.getJSONObject(i)));
        }
        return galeys;
    }

    // Getters
    public String getGalley_id() {
        return galley_id;
    }

    public String getLabel() {
        return label;
    }

    public String getFile_id() {
        return file_id;
    }

    public String getUrlViewGalley() {
        return urlViewGalley;
    }
}