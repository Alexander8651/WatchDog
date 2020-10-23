package com.andromoticaia.watchingdog

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.domain.DogDao

@Database(entities = [Dog::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun dogDao():DogDao

    companion object{
    private const val DATABASE_NAME =   "favoritedog"

        @Volatile
        private var INSTANCE:AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase?{
            INSTANCE ?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,AppDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .build()
            }
            return  INSTANCE
        }
    }
}