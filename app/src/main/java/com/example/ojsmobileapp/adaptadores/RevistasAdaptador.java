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

import com.example.ojsmobileapp.EdicionesActivity;
import com.example.ojsmobileapp.R;
import com.example.ojsmobileapp.modelos.RevistasModelo;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RevistasAdaptador extends RecyclerView.Adapter<RevistasAdaptador.ViewHolder> {

    private ArrayList<RevistasModelo> revistasList;
    private String idioma;
    private String id_revistas = "";

    public RevistasAdaptador(ArrayList<RevistasModelo> revistasList, String idioma) {
        this.revistasList = revistasList;
        this.idioma = idioma;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.crdwrevistas, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RevistasModelo revista = revistasList.get(position);
        holder.txtNombre.setText(revista.getNombre_revista());
        id_revistas = revista.getId_revista();
        Picasso.get().load(revista.getUrlimage_revista()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return revistasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImage;
        public TextView txtNombre;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreRevista);
            itemImage = itemView.findViewById(R.id.imgRevistas);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                id_revistas = revistasList.get(position).getId_revista();

                Snackbar.make(v, "Item Seleccionado " + id_revistas,
                        Snackbar.LENGTH_LONG).setAction("Acción", null).show();

                Context context = v.getContext();
                Intent intent = new Intent(context, EdicionesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idioma", idioma);
                bundle.putString("id", id_revistas);
                intent.putExtras(bundle);
                ContextCompat.startActivity(context, intent, null);
            });
        }
    }
}