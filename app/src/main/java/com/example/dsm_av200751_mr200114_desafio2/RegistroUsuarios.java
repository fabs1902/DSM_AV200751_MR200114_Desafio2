package com.example.dsm_av200751_mr200114_desafio2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_av200751_mr200114_desafio2.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegistroUsuarios extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText nombre, password, correo, departamento, tipoUsuario;
    Button btnRegistrar, btnRegresar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        nombre = findViewById(R.id.txtNumeroTicket);
        correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtContra);
        departamento = findViewById(R.id.txtDepartamento);
        tipoUsuario = findViewById(R.id.txtTipoUsuario);

        // Inicializar el botón btnRegistrar
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegresar = findViewById(R.id.btnRegresar);
        inicializarFirebase();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroUsuarios.this, Login.class);
                startActivity(intent);
            }
        });

    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void registrarUsuario(){
        String nombreU = nombre.getText().toString();
        String correoU = correo.getText().toString();
        String passwordU = password.getText().toString();
        String departamentoU = departamento.getText().toString();
        String tipoUsuarioU = tipoUsuario.getText().toString();

        Usuario usuario = new Usuario();
        usuario.setUid(UUID.randomUUID().toString());
        usuario.setNombre(nombreU);
        usuario.setCorreo(correoU);
        usuario.setPassword(passwordU);
        usuario.setDepartamento(departamentoU);
        usuario.setTipoUsuario(tipoUsuarioU);
        databaseReference.child("Usuario").child(usuario.getUid()).setValue(usuario)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Limpiar los campos después de agregar el usuario con éxito
                nombre.setText("");
                correo.setText("");
                password.setText("");
                departamento.setText("");
                tipoUsuario.setText("");

                // Mostrar un Toast para indicar que el usuario fue agregado exitosamente
                Toast.makeText(RegistroUsuarios.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // Mostrar un Toast si ocurre un error al agregar el usuario
                        Toast.makeText(RegistroUsuarios.this, "Error al agregar usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //Toast.makeText(this,"Usuario agregado", Toast.LENGTH_SHORT).show();

    }

}
