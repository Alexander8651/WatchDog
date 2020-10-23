package com.andromoticaia.watchingdog.data

import com.andromoticaia.watchingdog.data.responses.ResponseData
import com.andromoticaia.watchingdog.vo.RetrofitClient

class DataSource(){

    suspend fun getData():ResponseData{
        return RetrofitClient.webService.getDogToWatch().await()
    }
}