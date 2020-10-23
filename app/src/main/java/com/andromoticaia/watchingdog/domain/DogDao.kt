package com.andromoticaia.watchingdog.domain

import androidx.room.*
import com.andromoticaia.watchingdog.data.model.Dog
import kotlinx.coroutines.flow.Flow


@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDogToFavorite(dog:Dog)

    @Query("SELECT * FROM dogentity WHERE isFavorite == 1")
    fun getFavoritesDogs():Flow<List<Dog>>

    @Delete
    fun deleteDog(dog: Dog)
}