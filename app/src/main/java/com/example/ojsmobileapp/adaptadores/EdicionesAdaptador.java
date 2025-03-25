package com.example.ojsmobileapp.adaptadores;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ojsmobileapp.ArticulosActivity;
import com.example.ojsmobileapp.R;
import com.example.ojsmobileapp.modelos.EdicionesModelo;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EdicionesAdaptador extends RecyclerView.Adapter<EdicionesAdaptador.ViewHolder> {

    private ArrayList<EdicionesModelo> edicionesList;
    private String idioma;
    private String id_ediciones = "";

    public EdicionesAdaptador(ArrayList<EdicionesModelo> edicionesList, String idioma) {
        this.edicionesList = edicionesList;
        this.idioma = idioma;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.crdwediciones, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EdicionesModelo edicion = edicionesList.get(position);
        holder.txtNombre.setText(edicion.getDescripcion());
        id_ediciones = edicion.getId_ediciones();
        Picasso.get().load(edicion.getUrlimage_ediciones()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return edicionesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImage;
        public TextView txtNombre;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreEdiciones);
            itemImage = itemView.findViewById(R.id.imgEdiciones);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                id_ediciones = edicionesList.get(position).getId_ediciones();

                Snackbar.make(v, "Item Seleccionado " + id_ediciones,
                        Snackbar.LENGTH_LONG).setAction("Acción", null).show();

                Context context = v.getContext();
                Intent intent = new Intent(context, ArticulosActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idioma", idioma);
                bundle.putString("id", id_ediciones);
                intent.putExtras(bundle);
                ContextCompat.startActivity(context, intent, null);
            });
        }
    }
}