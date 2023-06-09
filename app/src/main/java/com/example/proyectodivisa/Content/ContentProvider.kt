package com.example.proyectodivisa.Content

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.proyectodivisa.Database.MonedaBD
import com.example.proyectodivisa.retrofit.MoneRepo
import com.example.proyectodivisa.retrofit.Myapplication

private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI("com.example.proyectodivisa", "monedas", 1)
    addURI("com.example.proyectodivisa", "monedas/#", 2)
    addURI("com.example.proyectodivisa", "monedas/*", 3)
}

class ContentProvider : ContentProvider() {

    lateinit var repository: MoneRepo
    lateinit var db: MonedaBD
    override fun onCreate(): Boolean {
        repository =  (context as Myapplication).repositoryMoneda
        db =  (context as Myapplication).database
        return true
    }


    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        var cursor: Cursor? = null

        when( sUriMatcher.match(p0)){
            1 -> {
                cursor = db.MonedaDao().getAllCursor()

            }
            2 -> { }
            3 -> { }
            else -> { }
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }
}