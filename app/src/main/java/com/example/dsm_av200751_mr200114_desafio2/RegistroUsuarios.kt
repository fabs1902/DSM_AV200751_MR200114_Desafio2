package com.example.dsm_av200751_mr200114_desafio2

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import android.widget.EditText
import android.widget.Toast

class RegistroUsuarios : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var txtCorreo: EditText // Campo de texto para correo
    private lateinit var txtContra: EditText // Campo de texto para password
    private lateinit var txtContra2: EditText // Campo de texto para confirmar password
    private lateinit var btnRegistrar: Button // Boton para registrar usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuarios)
        auth = FirebaseAuth.getInstance()

        // Inicializar vistas

    }
}
