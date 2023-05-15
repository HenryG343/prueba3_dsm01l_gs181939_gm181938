package com.example.prueba3_dsm01l_gs181939_gm181938

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Menuser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuser)

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