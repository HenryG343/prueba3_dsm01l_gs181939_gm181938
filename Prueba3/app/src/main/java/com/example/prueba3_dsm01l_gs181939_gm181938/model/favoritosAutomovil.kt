package com.example.prueba3_dsm01l_gs181939_gm181938.model
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.prueba3_dsm01l_gs181939_gm181938.db.HelperDB

class favoritosAutomovil (context: Context){
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null
    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }
    companion object {
        val TABLE_NAME_FA = "favoritos_automovil"
        val COL_ID = "idfavoritosautomovil"
        val COL_IDUSUARIO = "idusuario"
        val COL_IDAUTOMOVIL = "idautomovil"
        val COL_FECHA = "fecha_agregado"
        //sentencia SQL para crear la tabla.
        val CREATE_TABLE_FAV = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_FA + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_IDUSUARIO + " integer NOT NULL,"
                        + COL_IDAUTOMOVIL + " integer NOT NULL,"
                        + COL_FECHA + " timestamp NOT NULL,"
                        + "FOREIGN KEY(idusuario) REFERENCES usuario(idusuario),"
                        + "FOREIGN KEY(idautomovil) REFERENCES automovil(idautomovil));"
                )
    }

}