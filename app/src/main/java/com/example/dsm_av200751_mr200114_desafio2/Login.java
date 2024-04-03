package com.example.dsm_av200751_mr200114_desafio2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    Button botonRegistrar, botonLogin;
    EditText usuario, password;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        usuario = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtContrasena);
        botonRegistrar = findViewById(R.id.btnRegistrarse);
        botonLogin = findViewById(R.id.btnIniciarSesion);

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegistroUsuarios.class);
                startActivity(intent);
            }
        });

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usuario.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, pass);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        DatabaseReference usuariosRef = FirebaseDatabase.getInstance().getReference().child("Usuario");

        usuariosRef.orderByChild("correo").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot usuarioSnapshot : dataSnapshot.getChildren()) {
                        String contraseñaUsuario = usuarioSnapshot.child("password").getValue(String.class);
                        String tipoUsuario = usuarioSnapshot.child("tipoUsuario").getValue(String.class);

                        if (contraseñaUsuario.equals(password)) {
                            if ("administrador".equals(tipoUsuario)) {
                                startActivity(new Intent(Login.this, Admin.class));
                                Toast.makeText(Login.this, "Bienvenido administrador", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Login.this, usuario.class));
                                Toast.makeText(Login.this, "Bienvenido usuario", Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                    }
                    Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "No se encontró ningún usuario con ese correo electrónico", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Error en la lectura de datos
                Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
