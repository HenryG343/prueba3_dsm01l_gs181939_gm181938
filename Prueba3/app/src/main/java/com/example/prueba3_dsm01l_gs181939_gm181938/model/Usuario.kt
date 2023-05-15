package com.example.prueba3_dsm01l_gs181939_gm181938.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB

class Usuario(context: Context) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }
    companion object {
        val TABLE_NAME_USERS = "usuario"
        val COL_ID = "idusuario"
        val COL_NOMBRE = "nombre"
        val COL_APELLIDOS = "apellido"
        val COL_EMAIL = "email"
        val COL_USER = "user"
        val COL_CONTRA = "contra"
        val COL_TIPO = "tipo"
        //sentencia SQL para crear la tabla
        val CREATE_TABLE_USERS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_NOMBRE + " varchar(45) NOT NULL,"
                        + COL_APELLIDOS + " varchar(45) NOT NULL,"
                        + COL_EMAIL + " varchar(45) NOT NULL,"
                        + COL_USER + " varchar(45) NOT NULL,"
                        + COL_TIPO + " varchar(45) NOT NULL,"
                        + COL_CONTRA + " varchar(45) NOT NULL);"
                )
    }
    fun generarContentValues(nombre: String?,
                             apellido: String?,
                             email: String?,
                             user: String?,
                             tipo: String?,
                             contra: String?
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_NOMBRE, nombre)
        valores.put(COL_APELLIDOS, apellido)
        valores.put(COL_EMAIL, email)
        valores.put(COL_USER, user)
        valores.put(COL_TIPO, tipo)
        valores.put(COL_CONTRA, contra)
        return valores
    }
    fun insertValuesDefault(){
        val columns = arrayOf(COL_ID, COL_NOMBRE)
        var cursor: Cursor? =
            db!!.query(TABLE_NAME_USERS, columns, null, null, null, null, null)
        if (cursor == null || cursor!!.count <= 0) {
            db!!.insert(Usuario.TABLE_NAME_USERS,null,generarContentValues("admin", "admin","admin","admin","admin","admin"))
            db!!.insert(Usuario.TABLE_NAME_USERS,null,generarContentValues("client", "client","client","client","client","client"))
        }
    }
    //Agregar un nuevo registro
    fun addNewUser(nombre: String?, contra: String?,apellido: String?,email: String?,user: String?,tipo: String?,) {
        db!!.insert(
            Usuario.TABLE_NAME_USERS,
            null,
            generarContentValues(nombre,apellido,email,user,tipo,contra)
        )
    }
    fun loginUser(nombre: String?,contra: String?): Cursor? {
        val columns = arrayOf(
            COL_ID,
            COL_NOMBRE,
            COL_APELLIDOS,
            COL_EMAIL,
            COL_USER,
            COL_TIPO,
            COL_CONTRA,
        )
        return db!!.query(
            Usuario.TABLE_NAME_USERS, columns,
            "${Usuario.COL_USER}=? AND ${Usuario.COL_CONTRA}=?", arrayOf(nombre,contra), null, null, null
        )
    }
    // Eliminar un registro
    fun deleteUser(id: Int) {
        db!!.delete(TABLE_NAME_USERS, "$COL_ID=?", arrayOf(id.toString()))
    }
    //Modificar un registro
    fun updateRegistro(
        id: Int,
        nombre: String?,
        apellido: String?,
        email: String?,
        user: String?,
        tipo: String?,
        contra: String?
    ) {
        db!!.update(
            TABLE_NAME_USERS, generarContentValues(nombre, apellido,
                email, user,tipo,contra),
            "$COL_ID=?", arrayOf(id.toString())
        )
    }
    // Mostrar un registro particular
    fun searchProducto(id: Int): Cursor? {
        val columns = arrayOf(COL_ID,
            COL_NOMBRE,
            COL_APELLIDOS,
            COL_EMAIL,
            COL_USER,
            COL_TIPO,
            COL_CONTRA)
        return db!!.query(
            TABLE_NAME_USERS, columns,
            "$COL_ID=?", arrayOf(id.toString()), null, null, null
        )
    }
}