package com.example.dsm_av200751_mr200114_desafio2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var botonRegistrar: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonRegistrar = findViewById(R.id.btnRegistrarse)

        botonRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroUsuarios::class.java)
            startActivity(intent)
        }
    }
}