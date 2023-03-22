package com.example.proyectodivisa.retrofit

import android.app.Application
import com.example.proyectodivisa.Database.MonedaBD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Myapplication : Application(){
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MonedaBD.getDatabase(this, applicationScope) }
    val repositoryMoneda by lazy {  MoneRepo (database.MonedaDao()) }


    override fun onCreate() {
        super.onCreate()

    }

}