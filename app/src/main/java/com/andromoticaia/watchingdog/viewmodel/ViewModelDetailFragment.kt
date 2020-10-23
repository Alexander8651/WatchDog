package com.andromoticaia.watchingdog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andromoticaia.watchingdog.data.model.Dog
import com.andromoticaia.watchingdog.domain.Repository
import kotlinx.coroutines.launch

class ViewModelDetailFragment(private val repo:Repository):ViewModel() {

    fun addDogToFavorites(dog:Dog){

        viewModelScope.launch {
            repo.addDogToFavorites(dog)
        }

    }

}