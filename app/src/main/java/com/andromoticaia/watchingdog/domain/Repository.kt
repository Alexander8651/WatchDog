package com.andromoticaia.watchingdog.domain

import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.data.responses.ResponseData
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getData():ResponseData
    suspend fun addDogToFavorites(dog:Dog)
    fun getFavoritesDogs(): Flow<List<Dog>>
}