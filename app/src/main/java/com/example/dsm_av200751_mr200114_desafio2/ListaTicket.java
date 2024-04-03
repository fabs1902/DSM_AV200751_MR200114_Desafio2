package com.example.dsm_av200751_mr200114_desafio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    ListView listaTicket;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ticket);

        listaTicket = findViewById(R.id.lstTickets);
        inicializarFirebase();
        listaDatos();
    }

    private void listaDatos() {
        databaseReference.child("Ticket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTickets.clear();
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Ticket ticket = objSnaptshot.getValue(Ticket.class);
                    listaTickets.add(ticket);

                    arrayAdapterTicket = new ArrayAdapter<Ticket>(ListaTicket.this, android.R.layout.simple_list_item_1,listaTickets);
                    listaTicket.setAdapter(arrayAdapterTicket);
                }
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