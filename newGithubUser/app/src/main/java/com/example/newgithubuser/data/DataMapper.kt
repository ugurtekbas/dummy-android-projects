package com.example.newgithubuser.data

import com.example.newgithubuser.data.local.RepoEntity
import com.example.newgithubuser.data.remote.RepoResponse
import com.example.newgithubuser.domain.RepoItem

interface DataMapper {
    fun mapToCacheModel(response : RepoResponse): RepoEntity
    fun mapToDomainModel(entity: RepoEntity): RepoItem
}
