package com.apps.coroutines.RetrofitPackage

import com.apps.coroutines.DataPackage.JSONResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface PartsApiClient {

    @GET("/3/search/movie?/&query=q&api_key=4e0be2c22f7268edffde97481d49064a")
    fun getPartsAsync(): Deferred<Response<JSONResponse>>
}
