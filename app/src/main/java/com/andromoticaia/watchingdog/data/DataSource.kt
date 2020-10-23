package com.andromoticaia.watchingdog.data

import com.andromoticaia.watchingdog.AppDatabase
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.data.responses.ResponseData
import com.andromoticaia.watchingdog.vo.RetrofitClient
import kotlinx.coroutines.flow.Flow

class DataSource(private val appDatabase: AppDatabase){

    suspend fun getData():ResponseData{
        return RetrofitClient.webService.getDogToWatch().await()
    }

    suspend fun addDogToFavorites(dog: Dog){
        appDatabase.dogDao().addDogToFavorite(dog )
    }

    fun getFavoritesDogs(): Flow<List<Dog>>{
        return appDatabase.dogDao().getFavoritesDogs()
    }

    fun deleteDog(dog:Dog){
        appDatabase.dogDao().deleteDog(dog)

    }
}