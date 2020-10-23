package com.andromoticaia.watchingdog.domain

import com.andromoticaia.watchingdog.data.responses.ResponseData

interface Repository {

    suspend fun getData():ResponseData
}