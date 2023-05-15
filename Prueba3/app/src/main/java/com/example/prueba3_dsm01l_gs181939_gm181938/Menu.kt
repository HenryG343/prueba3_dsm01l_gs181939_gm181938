package com.example.prueba3_dsm01l_gs181939_gm181938

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Usuario


class Menu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var lblUsuario = findViewById<TextView>(R.id.lblUsuario)
        val preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE)
        val user = preferencias.getString("nombre","No existe la informacion")
        lblUsuario.setText("Bienvenido: " + user)

        var btnuno = findViewById<Button>(R.id.button5)
        btnuno.setOnClickListener {
            val intent = Intent(this, AutomovilMenu::class.java)
            startActivity(intent)
        }

        var btndos = findViewById<Button>(R.id.button6)
        btndos.setOnClickListener {
            val intent = Intent(this, ColoresMenu::class.java)
            startActivity(intent)
        }
        var btntres = findViewById<Button>(R.id.button7)
        btntres.setOnClickListener {
            val intent = Intent(this, TipoMenu::class.java)
            startActivity(intent)
        }
        var btncuatro = findViewById<Button>(R.id.button10)
        btncuatro.setOnClickListener {
            val intent = Intent(this, UsuarioMenu::class.java)
            startActivity(intent)
        }
        var btncinco = findViewById<Button>(R.id.button8)
        btncinco.setOnClickListener {
            val intent = Intent(this, MarcasMenu::class.java)
            startActivity(intent)
        }
        var btnnueve = findViewById<Button>(R.id.button9)
        btnnueve.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}