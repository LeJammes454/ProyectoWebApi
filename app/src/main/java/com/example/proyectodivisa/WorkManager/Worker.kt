package com.example.proyectodivisa.WorkManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.proyectodivisa.Database.Moneda
import com.example.proyectodivisa.Database.MonedaBD
import com.example.proyectodivisa.retrofit.ExchangerateAPI
import com.example.proyectodivisa.retrofit.Posts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Worker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val applicationScope = CoroutineScope(SupervisorJob())
    override fun doWork(): Result {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api : ExchangerateAPI = retrofit.create(ExchangerateAPI::class.java)

        var call : Call<Posts> = api.getPosts("v6/e635fe44bc6d624af6a33ace/latest/USD")

        call.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if(!response.isSuccessful) {
                    return
                }

                var post = response.body()

                val applicationScope = CoroutineScope(SupervisorJob())
                var moneda = Moneda(
                    _id = 0,
                    code = "",
                    update = "",
                    base_code = "",
                    value = 0.0
                )
                for(codes in post!!.conversion_ratesonversions){
                    moneda.code = codes.key
                    moneda.value = codes.value
                    moneda.base_code = post.base_code
                    moneda.update = post.time_last_update_utc

                    MonedaBD.getDatabase(applicationContext, applicationScope).MonedaDao().insert(moneda)
                }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
            }
        })
        return Result.success()
    }
}