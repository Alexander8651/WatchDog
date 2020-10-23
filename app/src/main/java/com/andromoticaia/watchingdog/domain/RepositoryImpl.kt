package com.andromoticaia.watchingdog.domain

import com.andromoticaia.watchingdog.data.DataSource
import com.andromoticaia.watchingdog.data.responses.ResponseData

class RepositoryImpl(var dataSource: DataSource) :Repository{
    override suspend fun getData(): ResponseData {
        return dataSource.getData()
    }

}