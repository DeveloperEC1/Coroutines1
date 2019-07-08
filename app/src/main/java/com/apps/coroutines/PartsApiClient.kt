package com.apps.coroutines

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface PartsApiClient {

    @GET("arpitmandliya/AndroidRestJSONExample/master/countries.json")
    fun getPartsAsync(): Deferred<Response<List<PartData>>>

    @POST("arpitmandliya/AndroidRestJSONExample/master/countries.json")
    fun addPartAsync(@Body newPart: PartData): Deferred<Response<Void>>

    @DELETE("arpitmandliya/AndroidRestJSONExample/master/countries.json/{id}")
    fun deletePartAsync(@Path("id") id: Int): Deferred<Response<Void>>

    @PUT("arpitmandliya/AndroidRestJSONExample/master/countries.json/{id}")
    fun updatePartAsync(@Path("id") id: Int, @Body newPart: PartData): Deferred<Response<Void>>
}
