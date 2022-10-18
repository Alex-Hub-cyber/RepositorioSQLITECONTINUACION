package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLLite(Context: Context?,
                   name:String,
                   factory:SQLiteDatabase.CursorFactory?,
                   version:Int):
                   SQLiteOpenHelper(Context,name,factory,version) {
    override fun onCreate(baseDeDatos: SQLiteDatabase?) {
        baseDeDatos?.execSQL("create table articulos(codigo int primary key, descripcion text , precio real)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}