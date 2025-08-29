package com.jabaubo.pokedle;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class pokemonAdapter extends RecyclerView.Adapter<pokemonAdapter.MyViewHolder> {
    private ArrayList<Pokemon> datalist;
    private Resources resources;
    private AssetManager assetManager;


    public pokemonAdapter(ArrayList<Pokemon> datalist, Context context) {
        this.datalist = datalist;
        this.resources = context.getResources();
        this.assetManager = context.getAssets();
    }

    @NonNull
    @Override
    public pokemonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_layout_placeholder, parent, false);
        return new pokemonAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pokemonAdapter.MyViewHolder holder, int position) {
        //Cargamos datos
        Pokemon data = datalist.get(position);
        holder.tvNombre.setText(data.getNombre());
        holder.tvTipo1.setText(Pokemon.getNombreTipo(data.getTipo1()));
        holder.tvTipo2.setText(Pokemon.getNombreTipo(data.getTipo2()));
        holder.tvAltura.setText(String.format("%.2f m",data.getAltura()));
        holder.tvPeso.setText(String.format("%.2f kg",data.getPeso()));
        holder.tvRegion.setText(Pokemon.getNombreRegion(data.getRegion()));
        holder.tvEtapa.setText(String.format("Etapa: %d",data.getEtapaEvolutiva()));
        //Editamos según la comparación
        int[] comparacion = data.getComparacion();
        try
        {
            InputStream ims = assetManager.open(String.format("sprites/%d.png",data.getNumero()));
            Drawable d = Drawable.createFromStream(ims, null);
            holder.ivSprite.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            System.out.println("error al cargar la imagen");
        }
        //nombre
        if (comparacion[0]==1){
            holder.tvNombre.setBackgroundColor(resources.getColor(R.color.verde,null));
            holder.ivSprite.setBackgroundColor(resources.getColor(R.color.verde,null));
        }
        else {
            holder.tvNombre.setBackgroundColor(resources.getColor(R.color.rojo,null));
            holder.ivSprite.setBackgroundColor(resources.getColor(R.color.rojo,null));
        }
        //tipo1
        if (comparacion[1]==1){
            holder.tvTipo1.setBackgroundColor(resources.getColor(R.color.verde,null));
        } else if (comparacion[1]==-1) {
            holder.tvTipo1.setBackgroundColor(resources.getColor(R.color.naranja,null));
        }
        else {
            holder.tvTipo1.setBackgroundColor(resources.getColor(R.color.rojo,null));
        }
        //tipo2
        if (comparacion[2]==1){
            holder.tvTipo2.setBackgroundColor(resources.getColor(R.color.verde,null));
        } else if (comparacion[2]==-1) {
            holder.tvTipo2.setBackgroundColor(resources.getColor(R.color.naranja,null));
        }
        else {
            holder.tvTipo2.setBackgroundColor(resources.getColor(R.color.rojo,null));
        }
        //altura
        if (comparacion[3]==1){
            holder.tvAltura.setBackgroundColor(resources.getColor(R.color.verde,null));
        }
        else if(comparacion[3]==-1){
            holder.tvAltura.setBackgroundColor(resources.getColor(R.color.rojo,null));
            holder.tvAltura.setText("<"+holder.tvAltura.getText());

        }
        else {
            holder.tvAltura.setBackgroundColor(resources.getColor(R.color.rojo,null));
            holder.tvAltura.setText(">"+holder.tvAltura.getText());
        }
        //peso
        if (comparacion[4]==1){
            holder.tvPeso.setBackgroundColor(resources.getColor(R.color.verde,null));
        }
        else if(comparacion[4]==-1){
            holder.tvPeso.setBackgroundColor(resources.getColor(R.color.rojo,null));
            holder.tvPeso.setText("<"+holder.tvPeso.getText());
        }
        else {
            holder.tvPeso.setBackgroundColor(resources.getColor(R.color.rojo,null));
            holder.tvPeso.setText(">"+holder.tvPeso.getText());

        }
        //etapa
        if (comparacion[5]==1){
            holder.tvEtapa.setBackgroundColor(resources.getColor(R.color.verde,null));
        }
        else {
            holder.tvEtapa.setBackgroundColor(resources.getColor(R.color.rojo,null));
        }
        //region
        if (comparacion[6]==1){
            holder.tvRegion.setBackgroundColor(resources.getColor(R.color.verde,null));
        }
        else {
            holder.tvRegion.setBackgroundColor(resources.getColor(R.color.rojo,null));
        }


        System.out.println(data.toStringComparacion());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvTipo1;
        TextView tvTipo2;
        TextView tvPeso;
        TextView tvAltura;
        TextView tvEtapa;
        TextView tvRegion;
        ImageView ivSprite;
        CardView cvNombre;
        CardView cvTipo1;
        CardView cvTipo2;
        CardView cvPeso;
        CardView cvAltura;
        CardView cvEtapa;
        CardView cvRegion;
        MyViewHolder(View itemView) {
            super(itemView);
            tvNombre= itemView.findViewById(R.id.tvNombrePkmn);
            tvTipo1= itemView.findViewById(R.id.tvTipo1Pkmn);
            tvTipo2= itemView.findViewById(R.id.tvTipo2Pkmn);
            tvPeso= itemView.findViewById(R.id.tvPesoPokemon);
            tvAltura= itemView.findViewById(R.id.tvAlturaPkmn);
            tvEtapa= itemView.findViewById(R.id.tvEtapaPkmn);
            tvRegion= itemView.findViewById(R.id.tvRegionPkmn);
            ivSprite= itemView.findViewById(R.id.ivSpritePkmn);


            cvNombre= itemView.findViewById(R.id.cvNombrePokemon);
            cvTipo1= itemView.findViewById(R.id.cvTipo1);
            cvTipo2= itemView.findViewById(R.id.cvTipo2);
            cvPeso= itemView.findViewById(R.id.cvPesoPokemon);
            cvAltura= itemView.findViewById(R.id.cvAlturaPokemon);
            cvEtapa= itemView.findViewById(R.id.cvEtapaPokemon);
            cvRegion= itemView.findViewById(R.id.cvRegionPokemon);
        }
    }
}
