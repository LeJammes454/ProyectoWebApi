package com.example.proyectodivisa

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.proyectodivisa.Database.MonedaBD
import com.example.proyectodivisa.WorkManager.Worker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var listMon : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listMon = findViewById(R.id.jsonText)

        val workManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<Worker>(
            24,TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)

        val applicationScope = CoroutineScope(SupervisorJob())
        val lista = MonedaBD.getDatabase(applicationContext, applicationScope).MonedaDao().getAllLista()

        for(list in lista){
            listMon.append(list.code + " -> " + list.value + "\n")
        }
    }
}