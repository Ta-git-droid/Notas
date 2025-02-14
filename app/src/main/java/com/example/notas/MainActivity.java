package com.example.notas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter adapter;
    private List<String> notes;
    private BaseDatos baseDatos;


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

        listView = findViewById(R.id.listView);
        Button btnAddNote = findViewById(R.id.btnAddNote);

        notes = new ArrayList<>();

        /*
         * Aquí debemos cargar las notas desde SQLite
         * */

        // Inicializa la base de datos
        baseDatos = new BaseDatos(this);


        adapter = new CustomAdapter(this, notes);
        listView.setAdapter(adapter);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        notes.clear();
        /*
         * Aquí también debemos cargar las notas desde SQLite
         * */
        notes.addAll(baseDatos.obtenerTodasLasNotas()); // Recargar notas
        adapter.notifyDataSetChanged();
    }
}