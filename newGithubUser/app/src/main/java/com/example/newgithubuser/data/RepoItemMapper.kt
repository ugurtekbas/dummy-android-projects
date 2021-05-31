package com.example.newgithubuser.data

import com.example.newgithubuser.data.local.OwnerEntity
import com.example.newgithubuser.data.local.RepoEntity
import com.example.newgithubuser.data.remote.OwnerResponse
import com.example.newgithubuser.data.remote.RepoResponse
import com.example.newgithubuser.domain.RepoItem
import javax.inject.Inject

class RepoItemMapper @Inject constructor(): DataMapper {

    override fun mapToCacheModel(response: RepoResponse): RepoEntity =
        with(response) {
            RepoEntity(
                fuckId = 0,
                id = id,
                name = name,
                description = description.orEmpty(),
                owner = owner.toEntityModel(),
                fork = fork,
                url = url
            )
        }

    private fun OwnerResponse.toEntityModel() =
        with(this) {
            OwnerEntity(login, avatarUrl, url)
        }

    override fun mapToDomainModel(entity: RepoEntity): RepoItem =
        with(entity) {
            RepoItem(
                id = id,
                name = name,
                description = description,
                ownerLogin = owner.login,
                profilePicture = owner.avatarUrl,
                background = if(fork) android.R.color.holo_green_dark else android.R.color.white,
                url = url
            )
        }
}
