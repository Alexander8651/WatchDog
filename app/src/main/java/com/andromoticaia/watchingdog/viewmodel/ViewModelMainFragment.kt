package com.andromoticaia.watchingdog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.domain.Repository

class ViewModelMainFragment(private var repo:Repository) :ViewModel(){

    fun getData () = liveData {

        val items = repo.getData()
        val dogs = items.message.map {
            Dog(imageURL = it, isFavorite = false, nickName = "")
        }
        emit(dogs)
    }

}