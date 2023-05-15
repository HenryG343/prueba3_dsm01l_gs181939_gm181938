package com.example.prueba3_dsm01l_gs181939_gm181938

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Usuario

class UsuarioMenu : AppCompatActivity() ,View.OnClickListener{
    private var managerUsuarios: Usuario? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var txtIdDB: TextView? = null
    private var txtId: EditText? = null
    private var txtNombre: EditText? = null
    private var txtApellido: EditText? = null
    private var txtEmail: EditText? = null
    private var txtUser: EditText? = null
    private var txtPassword: EditText? = null
    private var btnAgregar: Button? = null
    private var btnActualizar: Button? = null
    private var btnEliminar: Button? = null
    private var btnBuscar: Button? = null
    private var cmbTipo: Spinner? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_menu)
        txtIdDB = findViewById(R.id.txtIdDB)
        txtId = findViewById(R.id.txtId)
        txtNombre= findViewById(R.id.txtNombres)
        txtApellido= findViewById(R.id.txtApellidos)
        txtEmail= findViewById(R.id.txtEmail)
        txtUser= findViewById(R.id.txtUser)
        txtPassword= findViewById(R.id.txtPass)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnBuscar = findViewById(R.id.btnBuscar)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        btnAgregar!!.setOnClickListener(this)
        btnActualizar!!.setOnClickListener(this)
        btnEliminar!!.setOnClickListener(this)
        btnBuscar!!.setOnClickListener(this)
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
        val iduser = txtId!!.text.toString().trim()
        if (db != null) {
            if (view === btnAgregar) {
                if (vericarFormulario("insertar")) {
                    managerUsuarios!!.addNewUser(
                        nombres,password,apellidos,email,user,tipo
                    )
                    Toast.makeText(this, "Usuario agregado",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnActualizar) {
                Log.d(tipo,tipo)
                    managerUsuarios!!.updateRegistro(
                        iduser.toInt(),
                        nombres,apellidos,email,user,tipo, password
                    )
                    Toast.makeText(this, "Usuario actualizado",
                        Toast.LENGTH_LONG).show()
            } else if (view === btnEliminar) {
                    // manager.eliminar(1);
                    managerUsuarios!!.deleteUser(iduser.toInt())
                    Toast.makeText(this, "Usuario eliminado",
                        Toast.LENGTH_LONG).show()

            } else if (view === btnBuscar) {
                /*IMPLEMENTE LA BUSQUEDA*/
                    cursor = managerUsuarios!!.searchProducto(iduser.toInt())
                    var cat = ArrayList<String>()
                    cat.add("admin")
                    cat.add("client")
                    if (cursor != null && cursor!!.count>0){
                        cursor!!.moveToFirst()
                        txtNombre!!.setText(cursor!!.getString(1))
                        txtApellido!!.setText(cursor!!.getString(2))
                        txtEmail!!.setText(cursor!!.getString(3))
                        txtUser!!.setText(cursor!!.getString(4))
                        cmbTipo!!.setSelection(cursor!!.getInt(5))
                        cmbTipo!!.setSelection(cat.indexOf(cursor!!.getString(5)))
                        txtPassword!!.setText(cursor!!.getString(6))
                    }

            } else {
                Toast.makeText(this, "No se puede conectar a la Base de Datos",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    private fun vericarFormulario(opc: String): Boolean {
        var notificacion: String = "Se han generado algunos errores, favor verifiquelos"
        var response = true
        var idcolores_v = true
        var descripcion_v = true
        val descipcion: String = txtNombre!!.text.toString().trim()
        val idcolores: String = txtId!!.text.toString().trim()
        if (opc === "insertar" || opc == "actualizar") {
            if (descipcion.isEmpty()) {
                txtNombre!!.error = "Ingrese la descripcion del producto"
                txtNombre!!.requestFocus()
                descripcion_v = false
            }
            if (opc == "actualizar") {
                if (idcolores.isEmpty()) {
                    idcolores_v = false
                    notificacion = "No se ha seleccionado un producto"
                }
                response =
                    !(descripcion_v == false ||
                            idcolores_v == false)
            } else {
                response = !(descripcion_v == false )
            }
        } else if (opc === "eliminar"||opc=="buscar") {
            if (idcolores.isEmpty()) {
                response = false
                notificacion = "No se ha seleccionado un producto"
            }
        }
        //Mostrar errores
        if (response == false) {
            Toast.makeText(
                this,
                notificacion,
                Toast.LENGTH_LONG
            ).show()
        }
        return response
    }
}
