package com.example.proyectodivisa.retrofit

import com.example.proyectodivisa.Database.Moneda
import com.example.proyectodivisa.Database.MonedaDao
import kotlinx.coroutines.flow.Flow

class MoneRepo (private val monedaDao: MonedaDao){
    val allMonedas: Flow<List<Moneda>>
    get() = monedaDao.getAll()
}