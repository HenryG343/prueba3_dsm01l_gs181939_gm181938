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
import com.example.prueba3_dsm01l_gs181939_gm181938.model.tipoAutomovil

class TipoMenu : AppCompatActivity() , View.OnClickListener{
    private var managerTipo: tipoAutomovil? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var txtIdDB: TextView? = null
    private var txtId: EditText? = null
    private var txtDes: EditText? = null
    private var btnAgregar: Button? = null
    private var btnActualizar: Button? = null
    private var btnEliminar: Button? = null
    private var btnBuscar: Button? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_menu)
        txtIdDB = findViewById(R.id.txtIdDB)
        txtId = findViewById(R.id.txtId)
        txtDes= findViewById(R.id.txtDes)
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
        managerTipo = tipoAutomovil(this)
        val descripcion: String = txtDes!!.text.toString().trim()
        val idcolores = txtId!!.text.toString().trim()
        if (db != null) {
            if (view === btnAgregar) {
                if (vericarFormulario("insertar")) {
                    managerTipo!!.addNewUser(
                        descripcion
                    )
                    Toast.makeText(this, "Tipo agregado",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnActualizar) {
                if (vericarFormulario("actualizar")) {
                    managerTipo!!.updateRegistro(
                        idcolores.toInt(),
                        descripcion,
                    )
                    Toast.makeText(this, "Tipo actualizado",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnEliminar) {
                if (vericarFormulario("eliminar")) {
                    // manager.eliminar(1);
                    managerTipo!!.deleteUser(idcolores.toInt())
                    Toast.makeText(this, "Tipo eliminado",
                        Toast.LENGTH_LONG).show()
                }
            } else if (view === btnBuscar) {
                /*IMPLEMENTE LA BUSQUEDA*/
                if (vericarFormulario("buscar")){
                    cursor = managerTipo!!.searchProducto(idcolores.toInt())
                    if (cursor != null && cursor!!.count>0){
                        cursor!!.moveToFirst()
                        txtDes!!.setText(cursor!!.getString(1))
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
        var idcolores_v = true
        var descripcion_v = true
        val descipcion: String = txtDes!!.text.toString().trim()
        val idcolores: String = txtId!!.text.toString().trim()
        if (opc === "insertar" || opc == "actualizar") {
            if (descipcion.isEmpty()) {
                txtDes!!.error = "Ingrese la descripcion del producto"
                txtDes!!.requestFocus()
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