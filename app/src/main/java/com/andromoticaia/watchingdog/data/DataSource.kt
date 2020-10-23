package com.andromoticaia.watchingdog.data

import com.andromoticaia.watchingdog.AppDatabase
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.data.responses.ResponseData
import com.andromoticaia.watchingdog.vo.RetrofitClient
import kotlinx.coroutines.flow.Flow

class DataSource(private val appDatabase: AppDatabase){

    //get data from api
    suspend fun getData():ResponseData{
        return RetrofitClient.webService.getDogToWatch().await()
    }

    //add dog to favorites in room
    suspend fun addDogToFavorites(dog: Dog){
        appDatabase.dogDao().addDogToFavorite(dog )
    }

    //get all favorites from room
    fun getFavoritesDogs(): Flow<List<Dog>>{
        return appDatabase.dogDao().getFavoritesDogs()
    }

    //delete the dog selected from room
    fun deleteDog(dog:Dog){
        appDatabase.dogDao().deleteDog(dog)

    }
}