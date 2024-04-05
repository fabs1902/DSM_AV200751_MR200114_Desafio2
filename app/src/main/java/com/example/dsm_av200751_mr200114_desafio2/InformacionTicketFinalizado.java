package com.example.dsm_av200751_mr200114_desafio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformacionTicketFinalizado extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText txtNumeroTicket, txtTituloTicket, txtDescripcionTicket, txtDepartamento, txtNombreAutor, txtEmailContacto, txtIdTicket, txtFechaCreacion, txtEstado, txtFechaFinalizacion;
    Button btnActualizar, btnRegresar, btnEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_ticket_finalizado);

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
        btnActualizar = findViewById(R.id.btnActualizarTicket);
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





        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InformacionTicketFinalizado.this, usuario.class));
            }
        });
    }
}