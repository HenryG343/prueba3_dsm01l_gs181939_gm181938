package com.example.prueba3_dsm01l_gs181939_gm181938.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB

class Automovil  (context: Context){
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }
    companion object {
        val TABLE_NAME_AUTO = "automovil"
        val COL_ID = "idautomovil"
        val COL_MODELO = "modelo"
        val COL_NUMEROVIN = "numero_vin"
        val COL_NUMEROCHASIS = "numero_chasis"
        val COL_NUMEROMOTOR = "numero_motor"
        val COL_NUMEROASIENTOS = "numero_asientos"
        val COL_ANIO = "anio"
        val COL_CAPACIDADASIENTOS = "capacidad_asientos"
        val COL_PRECIO = "precio"
        val COL_URLIMG = "url_img"
        val COL_DES = "descripcion"
        val COL_IDMARCAS = "idmarcas"
        val COL_IDTIPOAUTO = "idtipoautomovil"
        val COL_IDCOLORES = "idcolores"
        //sentencia SQL para crear la tabla.
        val CREATE_TABLE_AUTO = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_AUTO + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_MODELO + " varchar(45) NOT NULL,"
                        + COL_NUMEROVIN + " varchar(45) NOT NULL,"
                        + COL_NUMEROCHASIS + " varchar(45) NOT NULL,"
                        + COL_NUMEROMOTOR + " varchar(45) NOT NULL,"
                        + COL_NUMEROASIENTOS + " integer NOT NULL,"
                        + COL_ANIO + " integer NOT NULL,"
                        + COL_PRECIO + " decimal(10,2) NOT NULL,"
                        + COL_DES + " varchar(45) NOT NULL"
                )
    }
    fun generarContentValues(modelo: String?, numerovin: String?, numerochasis: String?, numeromotor: String?, asientos: Int?, anio: Int?, precio: Double?, descripcion: String? ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_MODELO, modelo)
        valores.put(COL_NUMEROVIN, numerovin)
        valores.put(COL_NUMEROCHASIS, numerochasis)
        valores.put(COL_NUMEROMOTOR, numeromotor)
        valores.put(COL_NUMEROASIENTOS, asientos)
        valores.put(COL_ANIO, anio)
        valores.put(COL_PRECIO, precio)
        valores.put(COL_DES, descripcion)
        return valores
    }

    //Agregando un nuevo automovil
    fun addNewUser(modelo: String?, numerovin: String?, numerochasis: String?, numeromotor: String?, asientos: Int?, anio: Int?, precio: Double?, descripcion: String?){
        db!!.insert(
            Automovil.TABLE_NAME_AUTO,
            null,
            generarContentValues(modelo,numerovin,numerochasis,numeromotor,asientos,anio,precio,descripcion)
        )
    }

    //Eliminar un automovil
    fun deleteuser(id: Int){
        db!!.delete(TABLE_NAME_AUTO, "$COL_ID=?", arrayOf(id.toString()))
    }

    //Modificar registro
    fun updateRegistro(
        id: Int, modelo: String?, numerovin: String?,numerochasis: String?,numeromotor: String?, asientos: Int,anio: Int,precio: Double,descripcion: String?
    ) {
        db!!.update(
            TABLE_NAME_AUTO, generarContentValues(modelo,numerovin,numerochasis,numeromotor,asientos,anio,precio,descripcion),
            "$COL_ID=?", arrayOf(id.toString())
        )
    }

    //Mostrar un registro particular
    fun searchProducto(id: Int): Cursor?{
        val columns = arrayOf(COL_ID, COL_MODELO, COL_NUMEROVIN, COL_NUMEROCHASIS, COL_NUMEROCHASIS,
            COL_NUMEROASIENTOS, COL_ANIO, COL_PRECIO, COL_DES)
        return db!!.query(
            TABLE_NAME_AUTO, columns,"$COL_ID=?", arrayOf(id.toString()), null,null,null
        )
    }

}