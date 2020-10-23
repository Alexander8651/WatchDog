package com.andromoticaia.watchingdog.domain

import com.andromoticaia.watchingdog.data.responses.ResponseData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface WebService {

    @GET("random/10")
    fun getDogToWatch():Deferred<ResponseData>
}