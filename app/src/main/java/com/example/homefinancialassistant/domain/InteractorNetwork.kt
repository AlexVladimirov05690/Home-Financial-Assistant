package com.example.homefinancialassistant.domain

import android.util.Log
import com.example.homefinancialassistant.API
import com.example.homefinancialassistant.data.CurrencyFreaksApi
import com.example.homefinancialassistant.data.Enity.CurrencyFreaksApiDTO
import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.utils.ConverterDTO
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InteractorNetwork(val retrofitService: CurrencyFreaksApi) {
    private var scope: CoroutineScope = CoroutineScope(Job())
    private lateinit var job: Job
    private val defaultDTO = CurrencyFreaksApiDTO("00 00 0000", "---", mapOf("---" to 8888.0))

    //private var result = RateCurrency("---", "00 00 0000", 9999.9999)
    //private lateinit var result: RateCurrency
//    private suspend fun rateEuroToDollar():Double {
//        job = scope.launch {
//            try {
//                val deferred = async {
//                    ConverterDTO.converterDTOToRate(retrofitService.getRate(API.KEY, "EUR"))
//                }
//                val result = deferred.await()
//
//            } catch (e: Exception) {
//                Log.e("TAG", "Exception get -> ${e.localizedMessage}")
//            }
//        }
//
//    }


    fun getRateEuroToDollar(): Double {
        return 0.0
    }


}