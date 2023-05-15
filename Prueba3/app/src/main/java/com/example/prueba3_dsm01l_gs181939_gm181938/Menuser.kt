package com.example.prueba3_dsm01l_gs181939_gm181938

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Menuser : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuser)
        var lblUsuario = findViewById<TextView>(R.id.lblUsuario)
        val preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE)
        val user = preferencias.getString("nombre","No existe la informacion")
        lblUsuario.setText("Bienvenido: " + user)

        var btnseis = findViewById<Button>(R.id.button11)
        btnseis.setOnClickListener {
            val intent = Intent(this, FavoritosMenu::class.java)
            startActivity(intent)
        }
        var btnnueve = findViewById<Button>(R.id.button9)
        btnnueve.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}