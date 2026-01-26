package com.jabaubo.pokedle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Random;


public class PvpDialog extends DialogFragment {

    private Context context;
    private Button btUsarClave;
    private Button btGenerarClave;
    private EditText etClave;

    public PvpDialog(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.pvp_dialog,null);
        builder.setView(view);
        btUsarClave = view.findViewById(R.id.btUsarDialog);
        etClave = view.findViewById(R.id.etClaveDialog);
        btGenerarClave = view.findViewById(R.id.btGenerarDialog);
        btUsarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickUsar();
            }
        });
        btGenerarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickGenerar();
            }
        });

        return builder.create();

    }

    public void clickUsar(){
        int seed = Integer.valueOf(etClave.getText().toString());
        Intent intent = new Intent(getContext(), GameActivity.class);
        Bundle bundleValores = new Bundle();
        bundleValores.putString("modo","PVP");
        bundleValores.putInt("seed",seed);
        intent.putExtras(bundleValores);
        startActivity(intent);
    }

    public void clickGenerar(){
        String clave = "";
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            clave += String.valueOf(random.nextInt(10));
        }
        etClave.setText(clave);
    }
}
