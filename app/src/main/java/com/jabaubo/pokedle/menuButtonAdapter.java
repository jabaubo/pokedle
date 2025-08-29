package com.jabaubo.pokedle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class menuButtonAdapter extends RecyclerView.Adapter<menuButtonAdapter.MyViewHolder> {
    private static MainActivity mainActivity;
    public menuButtonAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_button, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.region.setText("Kanto");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.mewtwo));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.pikachu));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_kanto);
                break;
            case 1:
                holder.region.setText("Johto");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.ho_oh));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.lugia));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_johto);
                break;
            case 2:
                holder.region.setText("Hoennh");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.kyogre));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.groudon));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_hoenh);

                break;
            case 3:
                holder.region.setText("Sinnoh");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.dialga));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.palkia));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_sinnoh);

                break;
            case 4:
                holder.region.setText("Teselia");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.zekrom));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.reshiram));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_teselia);

                break;
            case 5:
                holder.region.setText("Kalos");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.xerneas));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.yveltal));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_kalos);
                break;
            case 6:
                holder.region.setText("Alola");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.solgaleo));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.lunala));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_alola);
                break;
            case 7:
                holder.region.setText("Galar");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.zacian));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.zamazenta));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_galar);
                break;
            case 8:
                holder.region.setText("Paldea");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.koraidon));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.miraidon));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_paldea);
                break;
            case 9:
                holder.region.setText("Pokedex completa");
                holder.imagen1.setImageDrawable(mainActivity.getDrawable(R.drawable.bulbasaur));
                holder.imagen2.setImageDrawable(mainActivity.getDrawable(R.drawable.pecharunt));
                holder.constraintLayout.setBackgroundResource(R.drawable.degradado_full);

                break;
        }

        holder.menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.getBaseContext(),GameActivity.class);
                Bundle valores = new Bundle();
                if (holder.getAdapterPosition()==9){
                    valores.putInt("region",0);
                }
                else {
                    valores.putInt("region",(holder.getAdapterPosition()+1));
                }
                intent.putExtras(valores);
                mainActivity.actividadJuego(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView region;
        ImageView imagen1;
        ImageView imagen2;
        CardView menuView;
        ConstraintLayout constraintLayout;
        MyViewHolder(View itemView) {
            super(itemView);
            //Los cargamos
            constraintLayout = itemView.findViewById(R.id.constraintMenu);
            region = itemView.findViewById(R.id.tvMenu);
            imagen1 = itemView.findViewById(R.id.ivMenu1);
            imagen2 = itemView.findViewById(R.id.ivMenu2);
            menuView = itemView.findViewById(R.id.menuView);
        }
    }
}
