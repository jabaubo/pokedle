package com.jabaubo.pokedle.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jabaubo.pokedle.PvpDialog;
import com.jabaubo.pokedle.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity {
    private Button btJuegoLibre;
    private Button btRetoDiario;
    private Button btPvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cLayoutHome), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btJuegoLibre = findViewById(R.id.btJuegoLibre);
        btRetoDiario = findViewById(R.id.btRetoDiario);
        btPvp = findViewById(R.id.btPvpHome);

        btJuegoLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btRetoDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRetoDiario();
            }
        });
        btPvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPvp();
            }
        });
    }

    public void clickPvp(){
        PvpDialog dialog = new PvpDialog(this);
        dialog.show(getSupportFragmentManager(),"PvpDialog");
    }
    public void clickRetoDiario() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        if (networkInfo!= null){
            if (networkInfo.isConnectedOrConnecting()){
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("https://www.google.com");
                            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                            conn.setRequestMethod("HEAD");
                            conn.setConnectTimeout(10000);
                            conn.setReadTimeout(10000);
                            System.out.println("PASO 1");
                            conn.connect();
                            System.out.println("PASO 2");

                            LocalDate fecha = LocalDateTime.ofInstant(Instant.ofEpochMilli(conn.getDate()), ZoneId.systemDefault()).toLocalDate();

                            System.out.println("PASO 3");
                            int seed = Integer.valueOf(fecha.toString().replace("-",""))*192;


                            Intent intent = new Intent(getBaseContext(), GameActivity.class);
                            Bundle bundleValores = new Bundle();
                            bundleValores.putString("modo","DAILY");
                            bundleValores.putInt("seed",seed);
                            intent.putExtras(bundleValores);
                            startActivity(intent);

                        } catch (MalformedURLException e) {
                            System.out.println("ERROR 1");
                            throw new RuntimeException(e);
                        } catch (ProtocolException e) {
                            System.out.println("ERROR 2");
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            System.out.println("Entro aqui");
                        }
                    }
                };
                Thread hilo = new Thread(runnable);
                hilo.start();
            }
            else {
                AlertDialog adError = new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("No se ha podido conectar con internet")
                        .setPositiveButton(R.string.ok, null)
                        .create();
                adError.show();
            }
        }else {
            AlertDialog adError = new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("No se ha podido conectar con internet")
                    .setPositiveButton(R.string.ok, null)
                    .create();
            adError.show();
        }


    }


}
