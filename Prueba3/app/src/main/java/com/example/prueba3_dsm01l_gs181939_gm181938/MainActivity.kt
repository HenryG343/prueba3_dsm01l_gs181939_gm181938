package com.example.prueba3_dsm01l_gs181939_gm181938

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Usuario

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var managerUsuario: Usuario? = null
    private var cursor: Cursor? = null
    private var txtNombre: EditText? = null
    private var txtContra: EditText? = null
    private var btnRegistro: Button? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        setSpinnerUsuario()
        txtNombre = findViewById(R.id.txtUsuario)
        txtContra = findViewById(R.id.txtPass)
        btnRegistro = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistro!!.setOnClickListener(this)
        btnLogin!!.setOnClickListener(this)
    }
    fun setSpinnerUsuario() {
        // Cargando valores por defecto
        managerUsuario = Usuario(this)
        managerUsuario!!.insertValuesDefault()
    }
    override fun onClick(view:View){
        managerUsuario= Usuario(this)
        val nombre: String = txtNombre!!.text.toString().trim()
        val contra: String = txtContra!!.text.toString().trim()
        if (db != null) {
            if (view==btnRegistro){
                val i = Intent(this@MainActivity,Registro::class.java)
                startActivity(i)
            }
            if (view==btnLogin){
                cursor = managerUsuario!!.loginUser(nombre,contra)
                if (cursor != null && cursor!!.count>0) {
                    cursor!!.moveToFirst()
                    val id = cursor!!.getString(0)
                    val nombre = cursor!!.getString(1)
                    val tipo = cursor!!.getString(5)
                    val preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = preferences.edit()
                    editor.putString("id",id)
                    editor.putString("nombre",nombre)
                    editor.putString("tipo",tipo)
                    editor.commit()
                    if(tipo == "admin"){
                        val i = Intent(this@MainActivity,Menu::class.java)
                        startActivity(i)
                    }else{
                        val i = Intent(this@MainActivity,Menuser::class.java)
                        startActivity(i)
                    }
                    Toast.makeText(this, "Bienvenido",
                        Toast.LENGTH_LONG)
                        .show()

                }else{
                    Toast.makeText(this, "No se encontro al usuario",
                        Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}