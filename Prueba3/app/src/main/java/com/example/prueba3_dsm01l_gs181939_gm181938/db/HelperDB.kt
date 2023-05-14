package com.example.prueba3_dsm01l_gs181939_gm181938.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.prueba3_dsm01l_gs181939_gm181938.model.*

class HelperDB(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "cars.sqlite"
        private const val DB_VERSION = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Usuario.CREATE_TABLE_USERS)
        db.execSQL(Colores.CREATE_TABLE_COLORES)
        db.execSQL(tipoAutomovil.CREATE_TABLE_TIPO)
        db.execSQL(Marcas.CREATE_TABLE_MARCAS)
        db.execSQL(favoritosAutomovil.CREATE_TABLE_FAV)
        db.execSQL(Automovil.CREATE_TABLE_AUTO)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
