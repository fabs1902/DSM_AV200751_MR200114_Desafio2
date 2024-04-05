package com.example.dsm_av200751_mr200114_desafio2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_av200751_mr200114_desafio2.model.Ticket;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.UUID;

public class RegistrarTicket extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText numeroTicket, tituloTicket, descripcionTicket, departamentoUsuario, autorTicket, correoContacto;
    Button btnRegistrar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_ticket);


        // Inicializar EditText y Button
        numeroTicket = findViewById(R.id.txtNumeroTicket);
        tituloTicket = findViewById(R.id.txtTituloTicket);
        descripcionTicket = findViewById(R.id.txtDescripcionTicket);
        departamentoUsuario = findViewById(R.id.txtDepartamento);
        autorTicket = findViewById(R.id.txtNombreAutor);
        correoContacto = findViewById(R.id.txtEmailContacto);
        btnRegistrar = findViewById(R.id.btnActualizarTicket);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarTicket();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrarTicket.this, usuario.class));
            }
        });
    }

    private void registrarTicket() {
        // Obtener valores de los EditText
        String numeroTicketN = numeroTicket.getText().toString();
        String tituloTicketN = tituloTicket.getText().toString();
        String descripcionTicketN = descripcionTicket.getText().toString();
        String departamentoUsuarioN = departamentoUsuario.getText().toString();
        String autorTicketN = autorTicket.getText().toString();
        String correoContactoN = correoContacto.getText().toString();

        // Crear un nuevo objeto Ticket
        Ticket ticket = new Ticket();
        ticket.setTid(UUID.randomUUID().toString());
        ticket.setNumero(numeroTicketN);
        ticket.setTitulo(tituloTicketN);
        ticket.setDescripcion(descripcionTicketN);
        ticket.setDepartamento(departamentoUsuarioN);
        ticket.setAutor(autorTicketN);
        ticket.setCorreo(correoContactoN);
        ticket.setFechaCreacion(new Date().toString());
        ticket.setEstado("Activo");
        ticket.setFechaFinalización("Sin finalizar");

        // Guardar el ticket en la base de datos
        databaseReference.child("Ticket").child(ticket.getTid()).setValue(ticket)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Limpiar los campos después de agregar el ticket con éxito
                        limpiarCampos();

                        // Mostrar un Toast para indicar que el ticket fue agregado exitosamente
                        Toast.makeText(RegistrarTicket.this, "Ticket agregado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Mostrar un Toast si ocurre un error al agregar el ticket
                        Toast.makeText(RegistrarTicket.this, "Error al agregar ticket: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void limpiarCampos() {
        // Limpiar los campos
        numeroTicket.setText("");
        tituloTicket.setText("");
        descripcionTicket.setText("");
        departamentoUsuario.setText("");
        autorTicket.setText("");
        correoContacto.setText("");
    }
}
