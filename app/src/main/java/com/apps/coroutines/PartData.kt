package com.apps.coroutines

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PartData(var id: Int, var countryName: String)
