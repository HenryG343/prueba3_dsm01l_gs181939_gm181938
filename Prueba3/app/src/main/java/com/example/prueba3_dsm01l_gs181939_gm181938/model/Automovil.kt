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
                        + COL_ANIO + " year NOT NULL,"
                        + COL_CAPACIDADASIENTOS + " integer NOT NULL,"
                        + COL_PRECIO + " decimal(10,2) NOT NULL,"
                        + COL_URLIMG + " varchar(45) NOT NULL,"
                        + COL_DES + " varchar(45) NOT NULL,"
                        + COL_IDMARCAS + " integer NOT NULL,"
                        + COL_IDTIPOAUTO + " integer NOT NULL,"
                        + COL_IDCOLORES + " integer NOT NULL,"
                        + "FOREIGN KEY(idcolores) REFERENCES colores(idcolores),"
                        + "FOREIGN KEY(idtipoautomovil) REFERENCES tipo_automovil(idtipoautomovil),"
                        + "FOREIGN KEY(idmarcas) REFERENCES marcas(idmarcas));"
                )
    }
}