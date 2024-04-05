package com.example.dsm_av200751_mr200114_desafio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dsm_av200751_mr200114_desafio2.model.Ticket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaTicket extends AppCompatActivity {

    private List<Ticket> listaTickets = new ArrayList<Ticket>();
    ArrayAdapter<Ticket> arrayAdapterTicket;
    ListView listaV_Ticket;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ticket);

        listaV_Ticket = findViewById(R.id.lstTickets);
        inicializarFirebase();
        listaDatos();
        listaV_Ticket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ticket ticketSeleccionado = listaTickets.get(position);
                // Crear un Intent para abrir la actividad ActualizarTicket
                Intent intent = new Intent(ListaTicket.this, ActualizarTicket.class);
                // Pasar los datos del ticket seleccionado como extras en el Intent
                intent.putExtra("tid", ticketSeleccionado.getTid());
                intent.putExtra("titulo", ticketSeleccionado.getTitulo());
                intent.putExtra("numero", ticketSeleccionado.getNumero());
                intent.putExtra("descripcion", ticketSeleccionado.getDescripcion());
                intent.putExtra("departamento", ticketSeleccionado.getDepartamento());
                intent.putExtra("autor", ticketSeleccionado.getAutor());
                intent.putExtra("correo", ticketSeleccionado.getCorreo());
                intent.putExtra("fechaCreacion", ticketSeleccionado.getFechaCreacion());
                intent.putExtra("estado", ticketSeleccionado.getEstado());
                intent.putExtra("fechaFinalizacion", ticketSeleccionado.getFechaFinalización());
                // Abrir la actividad ActualizarTicket
                startActivity(intent);
            }
        });
    }

    private void listaDatos() {
        databaseReference.child("Ticket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTickets.clear();
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Ticket ticket = objSnaptshot.getValue(Ticket.class);
                    listaTickets.add(ticket);
                }
                // Crear el adaptador después de haber agregado todos los tickets
                arrayAdapterTicket = new ArrayAdapter<Ticket>(ListaTicket.this, android.R.layout.simple_list_item_1, listaTickets);
                listaV_Ticket.setAdapter(arrayAdapterTicket);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }



}