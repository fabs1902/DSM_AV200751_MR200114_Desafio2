package com.example.dsm_av200751_mr200114_desafio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dsm_av200751_mr200114_desafio2.model.Ticket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActualizarTicket extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText txtNumeroTicket, txtTituloTicket, txtDescripcionTicket, txtDepartamento, txtNombreAutor, txtEmailContacto, txtIdTicket, txtFechaCreacion, txtEstado, txtFechaFinalizacion;
    Button btnActualizar, btnRegresar, btnEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_ticket);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String tid = intent.getStringExtra("tid");
        String titulo = intent.getStringExtra("titulo");
        String numero = intent.getStringExtra("numero");
        String descripcion = intent.getStringExtra("descripcion");
        String departamento = intent.getStringExtra("departamento");
        String autor = intent.getStringExtra("autor");
        String correo = intent.getStringExtra("correo");
        String fechaCreacion = intent.getStringExtra("fechaCreacion");
        String estado = intent.getStringExtra("estado");
        String fechaFinalizacion = intent.getStringExtra("fechaFinalizacion");

        // Mostrar los datos en los EditText correspondientes
        txtNumeroTicket = findViewById(R.id.txtNumeroTicket);
        txtTituloTicket = findViewById(R.id.txtTituloTicket);
        txtDescripcionTicket = findViewById(R.id.txtDescripcionTicket);
        txtDepartamento = findViewById(R.id.txtDepartamento);
        txtNombreAutor = findViewById(R.id.txtNombreAutor);
        txtEmailContacto = findViewById(R.id.txtEmailContacto);
        txtIdTicket = findViewById(R.id.txtIdTicket);
        btnActualizar = findViewById(R.id.btnRegistrarTicket);
        txtFechaCreacion = findViewById(R.id.txtfechaCreacion);
        txtEstado = findViewById(R.id.txtEstado);
        txtFechaFinalizacion = findViewById(R.id.txtFechaFinalizacion);
        btnEliminar = findViewById(R.id.btnEliminarTicket);
        btnRegresar = findViewById(R.id.btnRegresar);

        txtNumeroTicket.setText(numero);
        txtTituloTicket.setText(titulo);
        txtDescripcionTicket.setText(descripcion);
        txtDepartamento.setText(departamento);
        txtNombreAutor.setText(autor);
        txtEmailContacto.setText(correo);
        txtIdTicket.setText(tid);
        txtFechaCreacion.setText(fechaCreacion);
        txtEstado.setText(estado);
        txtFechaFinalizacion.setText(fechaFinalizacion);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarTicket();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarTicket();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActualizarTicket.this, usuario.class));
            }
        });
    }

    public void actualizarTicket(){
        Ticket ticket = new Ticket();
        ticket.setTid(txtIdTicket.getText().toString());
        ticket.setNumero(txtNumeroTicket.getText().toString());
        ticket.setTitulo(txtTituloTicket.getText().toString());
        ticket.setDescripcion(txtDescripcionTicket.getText().toString());
        ticket.setDepartamento(txtDepartamento.getText().toString());
        ticket.setAutor(txtNombreAutor.getText().toString());
        ticket.setCorreo(txtEmailContacto.getText().toString());
        ticket.setFechaFinalización(txtFechaFinalizacion.getText().toString());
        ticket.setEstado(txtEstado.getText().toString());
        ticket.setFechaCreacion(txtFechaCreacion.getText().toString());
        databaseReference.child("Ticket").child(ticket.getTid()).setValue(ticket);
        Toast.makeText(ActualizarTicket.this, "Ticket actualizado", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ActualizarTicket.this, ListaTicket.class));
    }

    private void limpiarCampos() {
        // Limpiar los campos
        txtNumeroTicket.setText("");
        txtTituloTicket.setText("");
        txtDescripcionTicket.setText("");
        txtDepartamento.setText("");
        txtNombreAutor.setText("");
        txtEmailContacto.setText("");
        txtIdTicket.setText("");
    }

    private void eliminarTicket(){
        Ticket ticket = new Ticket();
        ticket.setTid(txtIdTicket.getText().toString());
        databaseReference.child("Ticket").child(ticket.getTid()).removeValue();
        Toast.makeText(ActualizarTicket.this,"Ticket eliminado",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ActualizarTicket.this, ListaTicket.class));
    }
}