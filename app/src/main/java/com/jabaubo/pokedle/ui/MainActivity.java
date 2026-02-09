package com.jabaubo.pokedle.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jabaubo.pokedle.R;
import com.jabaubo.pokedle.objects.menuButtonAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
        });
        menuButtonAdapter adapter = new menuButtonAdapter(this);
        rv = findViewById(R.id.rvMenu);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


    }

    public void actividadJuego(Intent intent){
        startActivity(intent);
    }
}