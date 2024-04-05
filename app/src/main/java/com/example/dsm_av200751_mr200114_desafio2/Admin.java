package com.example.dsm_av200751_mr200114_desafio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


public class Admin extends AppCompatActivity {

    private List<Ticket> listaTickets = new ArrayList<Ticket>();
    ArrayAdapter<Ticket> arrayAdapterTicket;
    ListView listaV_Ticket;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btnCerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listaV_Ticket = findViewById(R.id.lstTickets);
        inicializarFirebase();
        listaDatos();
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion7);
        listaV_Ticket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ticket ticketSeleccionado = listaTickets.get(position);
                // Crear un Intent para abrir la actividad ActualizarTicket
                Intent intent = new Intent(Admin.this, FinalizarTicket.class);
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

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, Login.class));
            }
        });
    }


    private void listaDatos() {
        databaseReference.child("Ticket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTickets.clear();
                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Ticket ticket = objSnapshot.getValue(Ticket.class);
                    // Agregar solo los tickets con estado "Activo" a la lista
                    if (ticket.getEstado().equals("Activo")) {
                        listaTickets.add(ticket);
                    }
                }
                // Crear el adaptador después de haber agregado todos los tickets con estado "Activo"
                arrayAdapterTicket = new ArrayAdapter<>(Admin.this, android.R.layout.simple_list_item_1, listaTickets);
                listaV_Ticket.setAdapter(arrayAdapterTicket);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar el error si ocurriera
            }
        });
    }


    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}