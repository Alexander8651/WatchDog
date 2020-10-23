package com.andromoticaia.watchingdog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andromoticaia.watchingdog.domain.Repository

class VMFactory ( private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        //return a instance of viewmodel factory
        return modelClass.getConstructor(Repository::class.java).newInstance(repository)
    }

}