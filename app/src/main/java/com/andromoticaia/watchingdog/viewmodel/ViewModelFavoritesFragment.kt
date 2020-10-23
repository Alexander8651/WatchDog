package com.andromoticaia.watchingdog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andromoticaia.watchingdog.domain.Repository

class ViewModelFavoritesFragment(private val repo:Repository):ViewModel() {

    var favoritesDogs = repo.getFavoritesDogs().asLiveData()
}