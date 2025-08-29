package com.jabaubo.pokedle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private RecyclerView rv;
    private Button btReset;
    private Button btAyuda;
    private TextView tvIntentos;
    private AutoCompleteTextView autoCompleteTextView;
    private Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gameMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rv=findViewById(R.id.gameRv);
        btReset = findViewById(R.id.btResetGame);
        autoCompleteTextView = findViewById(R.id.actvGame);
        tvIntentos = findViewById(R.id.tvIntentos);
        btAyuda = findViewById(R.id.btAyuda);
        controlador = new Controlador(rv,this);
        controlador.setRegion(getIntent().getExtras().getInt("region"));
        controlador.cargarPokemon();
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(GameActivity.this, android.R.layout.simple_list_item_activated_1,controlador.getNombresPkmn()));
        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER & event.getAction()==KeyEvent.ACTION_DOWN){
                    autoCompleteTextView.setAdapter(null);
                    String nombre = String.valueOf(autoCompleteTextView.getText());
                    ArrayList<Pokemon> lista = controlador.getPokedexEnUso();
                    final boolean[] existe = {false};
                    for (int i = 0 ; i < lista.size();i++){
                        if (nombre.equalsIgnoreCase(lista.get(i).getNombre())){
                            existe[0] = true;
                            boolean resultado = controlador.comparar(lista.get(i));
                            tvIntentos.setText("Intentos: "+controlador.getIntento());
                            lista.remove(i);
                            autoCompleteTextView.setAdapter(new ArrayAdapter<>(GameActivity.this, android.R.layout.simple_list_item_activated_1,controlador.getNombresPkmn()));
                            if(resultado){
                                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this)
                                        .setTitle("Has acertado en " + controlador.getIntento() + " intento(s)")
                                        .setMessage("¿Quieres reiniciar el juego?")
                                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                controlador.cargarPokemon();
                                                autoCompleteTextView.setAdapter(new ArrayAdapter<>(GameActivity.this, android.R.layout.simple_list_item_activated_1,controlador.getNombresPkmn()));
                                                tvIntentos.setText("Intentos: "+controlador.getIntento());

                                            }
                                        })
                                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                existe[0] = false;
                                            }
                                        }).create();
                                alertDialog.show();
                            }
                        }
                    }
                    autoCompleteTextView.setText("");
                    if (!existe[0]){
                        AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this)
                                .setTitle("Error")
                                .setMessage("El pokemon no existe o ha sido mencionado ya")
                                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                                       @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                }).create();
                        alertDialog.show();
                    }
                }
                return false;            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this)
                        .setTitle("Advertencia")
                        .setMessage("¿Quieres reiniciar el juego?")
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                controlador.cargarPokemon();
                                autoCompleteTextView.setAdapter(new ArrayAdapter<>(GameActivity.this, android.R.layout.simple_list_item_activated_1,controlador.getNombresPkmn()));
                                tvIntentos.setText("Intentos: "+controlador.getIntento());

                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert).create();
                alertDialog.show();
            }
        });
        btAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this)
                        .setTitle("Ayuda")
                        .setMessage("Los colores de los campos indican lo siguiente:\nVerde - correcto\nNaranja - Tipo correcto , posición incorrecta(Ninguno como tipo secundario indica que el " +
                                "Pokémon es monotipo)" +
                                "\nRojo - " +
                                "Incorrecto\n\nLos" +
                                " indicadores en peso y " +
                                "altura indican lo siguiente:\n> El Pokémon a adivinar mide o pesa más que este Pokémon\n< El Pokémon a adivinar mide o pesa menos que este Pokémon ")
                        .setPositiveButton(R.string.ok, null).create();
                alertDialog.show();
            }
        });
        tvIntentos.setText("Intentos: "+controlador.getIntento());

    }
}
