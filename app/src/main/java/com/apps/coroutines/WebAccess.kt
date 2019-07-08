package com.apps.coroutines

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebAccess {

    val partsApi: PartsApiClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cdn.rawgit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return@lazy retrofit.create(PartsApiClient::class.java)
    }

}
