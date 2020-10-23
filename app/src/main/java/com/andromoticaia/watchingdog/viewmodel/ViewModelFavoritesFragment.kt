package com.andromoticaia.watchingdog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andromoticaia.watchingdog.domain.Repository

class ViewModelFavoritesFragment(private val repo:Repository):ViewModel() {

    // cast the coroutine flow as live data
    var favoritesDogs = repo.getFavoritesDogs().asLiveData()
}