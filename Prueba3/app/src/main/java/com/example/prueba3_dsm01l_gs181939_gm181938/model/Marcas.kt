package com.example.prueba3_dsm01l_gs181939_gm181938.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB
class Marcas (context: Context){
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }
    companion object {
        val TABLE_NAME_MARCAS = "marcas"
        val COL_ID = "idmarcas"
        val COL_NOMBRE = "nombre"
        //sentencia SQL para crear la tabla
        val CREATE_TABLE_MARCAS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MARCAS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_NOMBRE + " varchar(45) NOT NULL);"
                )
    }
    fun generarContentValues(nombre: String?
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_NOMBRE, nombre)
        return valores
    }
    //Agregar un nuevo registro
    fun addNewUser(nombre: String?) {
        db!!.insert(
            Marcas.TABLE_NAME_MARCAS,
            null,
            generarContentValues(nombre)
        )
    }
    // Eliminar un registro
    fun deleteUser(id: Int) {
        db!!.delete(TABLE_NAME_MARCAS, "$COL_ID=?", arrayOf(id.toString()))
    }
    //Modificar un registro
    fun updateRegistro(
        id: Int,
        nombre: String?
    ) {
        db!!.update(
            TABLE_NAME_MARCAS, generarContentValues(nombre),
            "$COL_ID=?", arrayOf(id.toString())
        )
    }
    // Mostrar un registro particular
    fun searchProducto(id: Int): Cursor? {
        val columns = arrayOf(COL_ID,
            COL_NOMBRE)
        return db!!.query(
            TABLE_NAME_MARCAS, columns,
            "$COL_ID=?", arrayOf(id.toString()), null, null, null
        )
    }
}