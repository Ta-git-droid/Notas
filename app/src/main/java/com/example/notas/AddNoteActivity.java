package com.example.notas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editText;
    private Button btnSave;
    private BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editText = findViewById(R.id.editText);
        btnSave = findViewById(R.id.btnSave);

        baseDatos = new BaseDatos(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                 * Aquí debemos guardar la nota en SQLite
                 * */
                String note = editText.getText().toString().trim();
                if (!note.isEmpty()) {
                    baseDatos.insertarNota(note);
                }

                finish();
            }
        });
    }
}
