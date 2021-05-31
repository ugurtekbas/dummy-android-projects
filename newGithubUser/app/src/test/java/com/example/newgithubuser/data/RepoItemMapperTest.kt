package com.example.newgithubuser.data

import android.R
import com.example.newgithubuser.data.local.OwnerEntity
import com.example.newgithubuser.data.local.RepoEntity
import com.example.newgithubuser.data.remote.OwnerResponse
import com.example.newgithubuser.data.remote.RepoResponse
import com.example.newgithubuser.domain.RepoItem
import org.junit.Assert.assertEquals
import org.junit.Test

class RepoItemMapperTest {

    private val mapper = RepoItemMapper()
    private val response = RepoResponse(
        id = 1,
        name = "Response",
        description = "Response repo desc",
        owner = OwnerResponse(login = "login", avatarUrl = "avatar-url", url = "owner-url"),
        fork = false,
        url = "url"
    )

    private val entity = RepoEntity(
        fuckId = 0,
        id = 1,
        name = "Response",
        description = "Response repo desc",
        owner = OwnerEntity(login = "login", avatarUrl = "avatar-url", url = "owner-url"),
        fork = false,
        url = "url"
    )

    private val domain = RepoItem(
        id = 1,
        name = "Response",
        description = "Response repo desc",
        ownerLogin = "login",
        profilePicture = "avatar-url",
        background = R.color.white,
        url = "url"
    )

    @Test
    fun `should map response model to entity model correctly`() {
        val expected = mapper.mapToCacheModel(response)

        assertEquals(expected, entity)
    }

    @Test
    fun `should map entity model to domain model correctly`() {
        val expected = mapper.mapToDomainModel(entity)

        assertEquals(expected, domain)
    }
}
