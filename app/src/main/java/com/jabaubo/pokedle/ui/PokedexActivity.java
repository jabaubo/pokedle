package com.jabaubo.pokedle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jabaubo.pokedle.ControladorDB;
import com.jabaubo.pokedle.R;
import com.jabaubo.pokedle.objects.Filtro;
import com.jabaubo.pokedle.objects.PokedexPokemonAdapter;
import com.jabaubo.pokedle.objects.Pokemon;

import java.util.ArrayList;

public class PokedexActivity extends AppCompatActivity {

    private ControladorDB controladorDB;
    private Filtro filtro;
    private ArrayList<Pokemon> listaPokemon;
    private AutoCompleteTextView actvPokedex;
    private RecyclerView rvPokedex;

    private CardView cvTipo1;
    private CardView cvTipo2;
    private CardView cvPeso;
    private CardView cvAltura;
    private CardView cvEtapa;
    private CardView cvRegion;

    private TextView tvTipo1;
    private TextView tvTipo2;
    private TextView tvPeso;
    private TextView tvAltura;
    private TextView tvEtapa;
    private TextView tvRegion;

    private TextView tvNPokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pokedex_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cLayoutPokedex), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controladorDB = new ControladorDB(this);
        filtro = new Filtro();
        cvTipo1 = findViewById(R.id.cvTipo1);
        cvTipo2 = findViewById(R.id.cvTipo2);
        cvAltura = findViewById(R.id.cvAlturaPokemon);
        cvPeso = findViewById(R.id.cvPesoPokemon);
        cvEtapa = findViewById(R.id.cvEtapaPokemon);
        cvRegion = findViewById(R.id.cvRegionPokemon);

        tvTipo1 = findViewById(R.id.tvTipo1Pkmn);
        tvTipo2 = findViewById(R.id.tvTipo2Pkmn);
        tvAltura = findViewById(R.id.tvAlturaPkmn);
        tvPeso = findViewById(R.id.tvPesoPokemon);
        tvEtapa = findViewById(R.id.tvEtapaPkmn);
        tvRegion = findViewById(R.id.tvRegionPkmn);

        tvNPokemon = findViewById(R.id.tvCantidad);

        rvPokedex = findViewById(R.id.pokedexRv);

        listaPokemon = controladorDB.leerPokemonCompleto();
        PokedexPokemonAdapter adapter = new PokedexPokemonAdapter(listaPokemon,this);
        rvPokedex.setAdapter(adapter);
        rvPokedex.setLayoutManager(new LinearLayoutManager(this));
        tvNPokemon.setText(String.format("Pokemons: %d",listaPokemon.size()));

        cvRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegion();
            }
        });
        cvEtapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickEtapa();
            }
        });
        cvTipo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTipo2();
            }
        });
        cvTipo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTipo1();
            }
        });
    }
    private void clickTipo1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] tipos = {"Cualquiera","Bicho","Siniestro","Dragón","Eléctrico","Hada","Lucha","Fuego","Volador","Fantasma","Planta","Tierra","Hielo","Normal","Veneno","Psíquico","Roca","Acero","Agua"};
        int[] opciones = new int[1];
        builder.setTitle("Tipo principal");
        builder.setSingleChoiceItems(tipos, 0, (dialog, which) -> {
            opciones[0] = which;
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(opciones[0]);
                tvTipo1.setText(tipos[opciones[0]]);
                filtro.setTipo1(opciones[0]);
                if (filtro.getTipo1() == filtro.getTipo2()){
                    filtro.setTipo2(0);
                    tvTipo2.setText("Ninguno");
                }
                listaPokemon = controladorDB.leerPokemonConFiltro(filtro);
                actualizarRv();
            }
        });
        builder.create().getListView().setScrollbarFadingEnabled(false);
        builder.show();

    }
    private void clickTipo2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] tipos = {"Cualquiera","Ninguno","Bicho","Siniestro","Dragón","Eléctrico","Hada","Lucha","Fuego","Volador","Fantasma","Planta","Tierra","Hielo","Normal","Veneno","Psíquico","Roca","Acero","Agua"};
        int[] opciones = new int[1];
        builder.setTitle("Tipo secundario");
        builder.setSingleChoiceItems(tipos, 0, (dialog, which) -> {
            opciones[0] = which;
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(opciones[0]);
                tvTipo2.setText(tipos[opciones[0]]);
                filtro.setTipo2(opciones[0]-1);
                if (filtro.getTipo1() == filtro.getTipo2()){
                    filtro.setTipo2(0);
                    tvTipo2.setText("Ninguno");
                }
                listaPokemon = controladorDB.leerPokemonConFiltro(filtro);
                actualizarRv();
            }
        });
        builder.create().getListView().setScrollbarFadingEnabled(false);
        builder.show();

    }
    private void clickEtapa(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] etapas = {"Cualquiera","1","2","3"};
        int[] opciones = new int[1];
        builder.setTitle("Etapa");
        builder.setSingleChoiceItems(etapas, 0, (dialog, which) -> {
            opciones[0] = which;
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(opciones[0]);
                tvEtapa.setText(etapas[opciones[0]]);
                filtro.setEtapa(opciones[0]);
                listaPokemon = controladorDB.leerPokemonConFiltro(filtro);
                actualizarRv();
            }
        });
        builder.show();
    }
    private void clickRegion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] regiones = {"Cualquiera","Kanto","Johto","Hoenh","Sinnoh","Teselia","Kalos","Alola","Galar","Paldea"};
        int[] opciones = new int[1];
        builder.setTitle("Region");
        builder.setSingleChoiceItems(regiones, 0, (dialog, which) -> {
            opciones[0] = which;
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(opciones[0]);
                tvRegion.setText(regiones[opciones[0]]);
                filtro.setRegion(opciones[0]);
                listaPokemon = controladorDB.leerPokemonConFiltro(filtro);
                actualizarRv();
            }
        });
        builder.show();

    }

    private void actualizarRv(){
        PokedexPokemonAdapter adapter = new PokedexPokemonAdapter(listaPokemon,this);
        rvPokedex.setAdapter(adapter);
        rvPokedex.setLayoutManager(new LinearLayoutManager(this));
        tvNPokemon.setText(String.format("Pokemons: %d",listaPokemon.size()));
    }

}
