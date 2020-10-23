package com.andromoticaia.watchingdog.domain

import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.data.responses.ResponseData
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(var dataSource: DataSource) :Repository{
    override suspend fun getData(): ResponseData {
        return dataSource.getData()
    }

    override suspend fun addDogToFavorites(dog: Dog) {
        dataSource.addDogToFavorites(dog)
    }

    override fun getFavoritesDogs(): Flow<List<Dog>> {
        return dataSource.getFavoritesDogs()
    }

}