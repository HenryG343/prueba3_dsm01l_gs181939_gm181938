package com.example.prueba3_dsm01l_gs181939_gm181938

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Colores
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Marcas

class MarcasMenu : AppCompatActivity() , View.OnClickListener{
    private var managerMarcas: Marcas? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var txtIdDB: TextView? = null
    private var txtId: EditText? = null
    private var txtNombre: EditText? = null
    private var btnAgregar: Button? = null
    private var btnActualizar: Button? = null
    private var btnEliminar: Button? = null
    private var btnBuscar: Button? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcas_menu)
        txtIdDB = findViewById(R.id.txtIdDB)
        txtId = findViewById(R.id.txtId)
        txtNombre= findViewById(R.id.txtNombre)
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
    }
    override fun onClick(view: View) {
        managerMarcas = Marcas(this)
        val nombre: String = txtNombre!!.text.toString().trim()
        val idmarca = txtId!!.text.toString().trim()
        if (db != null) {
            if (view === btnAgregar) {
                if (vericarFormulario("insertar")) {
                    managerMarcas!!.addNewUser(
                        nombre
                    )
                    Toast.makeText(this, "Marca agregada",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnActualizar) {
                if (vericarFormulario("actualizar")) {
                    managerMarcas!!.updateRegistro(
                        idmarca.toInt(),
                        nombre,
                    )
                    Toast.makeText(this, "Marca actualizada",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnEliminar) {
                if (vericarFormulario("eliminar")) {
                    // manager.eliminar(1);
                    managerMarcas!!.deleteUser(idmarca.toInt())
                    Toast.makeText(this, "Marca eliminada",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnBuscar) {
                /*IMPLEMENTE LA BUSQUEDA*/
                if (vericarFormulario("buscar")){
                    cursor = managerMarcas!!.searchProducto(idmarca.toInt())
                    if (cursor != null && cursor!!.count>0){
                        cursor!!.moveToFirst()
                        txtNombre!!.setText(cursor!!.getString(1))
                    }
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
        var idmarcas_v = true
        var nombre_v = true
        val nombre: String = txtNombre!!.text.toString().trim()
        val idmarcas: String = txtId!!.text.toString().trim()
        if (opc === "insertar" || opc == "actualizar") {
            if (nombre.isEmpty()) {
                txtNombre!!.error = "Ingrese la descripcion del producto"
                txtNombre!!.requestFocus()
                nombre_v = false
            }
            if (opc == "actualizar") {
                if (idmarcas.isEmpty()) {
                    idmarcas_v = false
                    notificacion = "No se ha seleccionado un producto"
                }
                response =
                    !(nombre_v == false ||
                            idmarcas_v == false)
            } else {
                response = !(nombre_v == false )
            }
        } else if (opc === "eliminar"||opc=="buscar") {
            if (idmarcas.isEmpty()) {
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