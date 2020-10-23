package com.andromoticaia.watchingdog.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "dogentity")
@Parcelize
data class Dog(

    @PrimaryKey
    val imageURL:String = "",

    @ColumnInfo(name = "isFavorite")
    val isFavorite:Boolean =false,

    @ColumnInfo(name = "nickName")
    val nickName:String ="",

) : Parcelable