package com.example.prueba3_dsm01l_gs181939_gm181938

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB
import com.example.prueba3_dsm01l_gs181939_gm181938.model.Automovil

class AutomovilMenu : AppCompatActivity(), View.OnClickListener{
    private var managerautos: Automovil? = null
    private var dbHelper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var txtidDB: TextView? = null
    private var txtid: EditText?= null
    private var txtmodelo: EditText? = null
    private var txtnumerovin: EditText? = null
    private var txtnumerochasis: EditText? = null
    private var txtnumeromotor: EditText? = null
    private var txtasientos: EditText? = null
    private var txtaño: EditText? = null
    private var txtprecio: EditText? = null
    private var txtdescripcion: EditText? = null
    private var agregar: Button? = null
    private var eliminar: Button? = null
    private var actualizar: Button? = null
    private var buscar: Button? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automovil_menu)
        txtid = findViewById(R.id.txtId)
        txtmodelo = findViewById(R.id.txtmodelo)
        txtnumerovin = findViewById(R.id.txtnumerovin)
        txtnumerochasis = findViewById(R.id.txtnumerochasis)
        txtnumeromotor = findViewById(R.id.txtnumeromotor)
        txtasientos = findViewById(R.id.txtasientos)
        txtaño = findViewById(R.id.txtaño)
        txtprecio = findViewById(R.id.txtprecio)
        txtdescripcion = findViewById(R.id.txtdescripcion)
        agregar = findViewById(R.id.button)
        eliminar= findViewById(R.id.button2)
        actualizar = findViewById(R.id.button3)
        buscar = findViewById(R.id.button4)
        dbHelper = HelperDB(this)
        db = dbHelper!!.writableDatabase
        agregar!!.setOnClickListener(this)
        eliminar!!.setOnClickListener(this)
        actualizar!!.setOnClickListener(this)
        buscar!!.setOnClickListener(this)
    }

    override fun onClick(view: View){
        managerautos = Automovil(this)
        val idautos = txtid!!.text.toString().trim()
        val modelo: String = txtmodelo!!.text.toString().trim()
        val numerovin: String = txtnumerovin!!.text.toString().trim()
        val numerochasis: String = txtnumerochasis!!.text.toString().trim()
        val numeromotor: String = txtnumeromotor!!.text.toString().trim()
        val descripcion: String = txtdescripcion!!.text.toString().trim()
        val asientos: String = txtasientos!!.text.toString().trim()
        val año: String = txtaño!!.text.toString().trim()
        val precio: String = txtprecio!!.text.toString().trim()
        if (db != null){
            if(view === agregar) {
                if (verificarformulario("insertar")) {
                    managerautos!!.addNewUser(
                        modelo,
                        numerovin,
                        numerochasis,
                        numeromotor,
                        asientos.toInt(),
                        año.toInt(),
                        precio.toDouble(),
                        descripcion
                    )
                    Toast.makeText(this, "Automovil agregado", Toast.LENGTH_LONG).show()
                    }
                }else if(view === actualizar){
                    if(verificarformulario("actualizar")){
                        managerautos!!.updateRegistro(
                            idautos.toInt(),modelo,numerovin,numerochasis,numeromotor,asientos.toInt(),año.toInt(),precio.toDouble(),descripcion
                        )
                        Toast.makeText(this, "Auto Actualizado", Toast.LENGTH_LONG).show()
                    }
                }else if(view === eliminar){
                    if(verificarformulario("eliminar")){
                        managerautos!!.deleteuser(idautos.toInt())
                        Toast.makeText(this, "Auto Eliminado", Toast.LENGTH_LONG).show()
                    }
            }else if (view === buscar){
                if(verificarformulario("buscar")){
                    cursor = managerautos!!.searchProducto(idautos.toInt())
                    if (cursor!= null && cursor!!.count>0){
                        cursor!!.moveToFirst()
                        txtmodelo!!.setText(cursor!!.getString(1))
                        txtnumerovin!!.setText(cursor!!.getString(2))
                        txtnumerochasis!!.setText(cursor!!.getString(3))
                        txtnumeromotor!!.setText(cursor!!.getString(4))
                        txtasientos!!.setText(cursor!!.getString(5))
                        txtaño!!.setText(cursor!!.getString(6))
                        txtprecio!!.setText(cursor!!.getString(7))
                        txtdescripcion!!.setText(cursor!!.getString(8))
                    }
                }
            }else {
                Toast.makeText(this, "No se puede conectar a la BD", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun verificarformulario(opc: String): Boolean{
        var notification: String = "Se han generado algunos errores, por favor verifiquelos"
        var response = true
        var idautos_v = true
        var modelo_v = true
        var vin_v = true
        var chasis_v = true
        var motor_v = true
        var asientos_v = true
        var año_v = true
        var precio_v = true
        var descripcion_v = true
        val idautos = txtid!!.text.toString().trim()
        val modelo: String = txtmodelo!!.text.toString().trim()
        val numerovin: String = txtnumerovin!!.text.toString().trim()
        val numerochasis: String = txtnumerochasis!!.text.toString().trim()
        val numeromotor: String = txtnumeromotor!!.text.toString().trim()
        val descripcion: String = txtdescripcion!!.text.toString().trim()
        val asientos = txtasientos!!.text.toString().trim()
        val año = txtaño!!.text.toString().trim()
        val precio = txtprecio!!.text.toString().trim()
        if (opc === "insertar" || opc === "actualizar"){
            if(descripcion.isEmpty() || modelo.isEmpty() || numerovin.isEmpty() || numerochasis.isEmpty() || numeromotor.isEmpty() || asientos.isEmpty() || año.isEmpty() || precio.isEmpty()){
                txtid!!.error = "No deje campos vacíos en el formulario"
                idautos_v = false
                modelo_v = false
                vin_v = false
                chasis_v = false
                motor_v = false
                asientos_v = false
                año_v = false
                precio_v = false
                descripcion_v = false
            }
            if(opc == "actualizar"){
                if(idautos.isEmpty()){
                    idautos_v = false
                    notification = "Seleccione un automovil"
                }
                response = !(idautos_v == false || modelo_v == false || vin_v == false || chasis_v == false || motor_v == false || año_v == false || asientos_v == false || precio_v == false || descripcion_v == false)
            }else {
                response = !(modelo_v == false || vin_v == false || chasis_v == false || motor_v == false || año_v == false || asientos_v == false || precio_v == false || descripcion_v == false)
            }
        }else if (opc === "eliminar" || opc == "buscar"){
            if(idautos.isEmpty()){
                response = false
                notification = "Seleccione un automovil"
            }
        }
        if (response == false){
            Toast.makeText(
                this,notification,Toast.LENGTH_LONG
            ).show()
        }
        return response
    }
}