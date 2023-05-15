package com.example.prueba3_dsm01l_gs181939_gm181938

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Usuario

class Registro : AppCompatActivity(), View.OnClickListener {
    private var managerUsuarios: Usuario? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var txtId: EditText? = null
    private var txtNombre: EditText? = null
    private var txtApellido: EditText? = null
    private var txtEmail: EditText? = null
    private var txtUser: EditText? = null
    private var txtPassword: EditText? = null
    private var btnAgregar: Button? = null
    private var cmbTipo: Spinner? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        txtNombre= findViewById(R.id.txtNombres)
        txtApellido= findViewById(R.id.txtApellidos)
        txtEmail= findViewById(R.id.txtEmail)
        txtUser= findViewById(R.id.txtUser)
        txtPassword= findViewById(R.id.txtPass)
        btnAgregar = findViewById(R.id.btnAgregar)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        btnAgregar!!.setOnClickListener(this)
        cmbTipo = findViewById<Spinner>(R.id.cmbTipo)
        var cat = ArrayList<String>()
        cat.add("admin")
        cat.add("client")
        var adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, cat)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbTipo!!.adapter = adaptador
    }
    override fun onClick(view: View) {
        managerUsuarios = Usuario(this)
        val nombres: String = txtNombre!!.text.toString().trim()
        val apellidos: String = txtApellido!!.text.toString().trim()
        val email: String = txtEmail!!.text.toString().trim()
        val user: String = txtUser!!.text.toString().trim()
        val password: String = txtPassword!!.text.toString().trim()
        val tipo: String = cmbTipo!!.selectedItem.toString().trim()
        if (db != null) {
            if (view === btnAgregar) {
                    managerUsuarios!!.addNewUser(
                        nombres,password,apellidos,email,user,tipo
                    )
                val i = Intent(this@Registro,MainActivity::class.java)
                startActivity(i)
                    Toast.makeText(this, "Bienvenido",
                        Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "No se puede conectar a la Base de Datos",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}