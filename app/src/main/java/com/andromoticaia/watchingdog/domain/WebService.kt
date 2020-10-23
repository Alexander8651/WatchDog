package com.andromoticaia.watchingdog.domain

import com.andromoticaia.watchingdog.data.responses.ResponseData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

//interface to do request in retrofiervices
interface WebService {

    @GET("random/10")
    fun getDogToWatch():Deferred<ResponseData>
}