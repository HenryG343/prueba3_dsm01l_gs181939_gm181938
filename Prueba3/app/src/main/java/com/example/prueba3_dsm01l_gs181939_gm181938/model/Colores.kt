package com.example.prueba3_dsm01l_gs181939_gm181938.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB

class Colores (context: Context){
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }
    companion object {
        val TABLE_NAME_COLOR = "colores"
        val COL_ID = "idcolores"
        val COL_DES = "descripcion"
        //sentencia SQL para crear la tabla
        val CREATE_TABLE_COLORES = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COLOR + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_DES + " varchar(45) NOT NULL);"
                )
    }
    fun generarContentValues(descripcion: String?
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_DES, descripcion)
        return valores
    }
    //Agregar un nuevo registro
    fun addNewUser(descripcion: String?) {
        db!!.insert(
            Colores.TABLE_NAME_COLOR,
            null,
            generarContentValues(descripcion)
        )
    }
    // Eliminar un registro
    fun deleteUser(id: Int) {
        db!!.delete(TABLE_NAME_COLOR, "$COL_ID=?", arrayOf(id.toString()))
    }
    //Modificar un registro
    fun updateRegistro(
        id: Int,
        descripcion: String?
    ) {
        db!!.update(
            TABLE_NAME_COLOR, generarContentValues(descripcion),
            "$COL_ID=?", arrayOf(id.toString())
        )
    }
    // Mostrar un registro particular
    fun searchProducto(id: Int): Cursor? {
        val columns = arrayOf(COL_ID,
            COL_DES)
        return db!!.query(
            TABLE_NAME_COLOR, columns,
            "$COL_ID=?", arrayOf(id.toString()), null, null, null
        )
    }
    // Mostrar un registro particular
    fun searchAll(id: Int): Cursor? {
        val columns = arrayOf(COL_ID,
            COL_DES)
        return db!!.query(
            TABLE_NAME_COLOR, columns,
            null, null, null, null, "${Colores.COL_ID} ASC"
        )
    }
}