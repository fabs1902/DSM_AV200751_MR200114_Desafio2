package com.example.dsm_av200751_mr200114_desafio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class usuario extends AppCompatActivity {

    Button btnRegistrarTicket, btnHistorialTicket, btnListaTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        btnRegistrarTicket = findViewById(R.id.btnRegistrarTicket2);
        btnListaTickets = findViewById(R.id.btnControlTicket);
        btnHistorialTicket = findViewById(R.id.btnHistorialTicket);
        btnRegistrarTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(usuario.this,RegistrarTicket.class));
            }
        });

        btnListaTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(usuario.this,ListaTicket.class));
            }
        });

        btnHistorialTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(usuario.this,HistorialTicket.class));
            }
        });
    }
}