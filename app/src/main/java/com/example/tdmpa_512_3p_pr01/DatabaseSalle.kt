package com.example.tdmpa_512_3p_pr01

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseSalle (context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "LoginDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "LoginTable"
        private const val KEY_ID = "_id"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_APELLIDOS = "apellidos"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_NOMBRE TEXT, $KEY_APELLIDOS TEXT, $KEY_PASSWORD TEXT);")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addLogin(loginModel: LoginModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NOMBRE, loginModel.nombre)
        values.put(KEY_APELLIDOS, loginModel.apellidos)
        values.put(KEY_PASSWORD, loginModel.password)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getLoginByName(nombre: String): LoginModel?{
        val db = this.readableDatabase//consultar base de datos
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_NOMBRE, KEY_APELLIDOS, KEY_PASSWORD),
            "$KEY_NOMBRE=?",
            arrayOf(nombre),
            null,
            null,
            null
        )
        return if(cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val apellidos = cursor.getString(cursor.getColumnIndex(KEY_APELLIDOS))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            LoginModel(id, nombre, apellidos, password)
        }
        else{
            null
        }
    }
}

data class LoginModel(val id: Int, val nombre: String, val apellidos: String,  val password: String)