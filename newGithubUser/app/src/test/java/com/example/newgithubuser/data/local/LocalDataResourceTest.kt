package com.example.newgithubuser.data.local

import io.reactivex.Observable
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LocalDataResourceTest {

    private val firstEntity = RepoEntity(
        fuckId = 1,
        id = 1,
        name = "John",
        description = "Tester name",
        owner = OwnerEntity(login = "owner-login", avatarUrl = "avatar", url = "url"),
        fork = true,
        url = "url"
    )
    private val secondEntity = RepoEntity(
        fuckId = 2,
        id = 2,
        name = "Joe",
        description = "Second Tester name",
        owner = OwnerEntity(login = "owner-login", avatarUrl = "avatar", url = "url"),
        fork = true,
        url = "url"
    )
    private val entityList = listOf(firstEntity, secondEntity)
    private val repoDoa: RepoDao = mock {
        on { getAll() } doReturn entityList
        on { getThat() } doReturn Observable.just(entityList)
    }
    private val localResource = LocalDataResource(repoDoa)

    @Test
    fun `when subscribing to repo table should invoke correct method`() {
        localResource.getThat()

        verify(repoDoa).getThat()
    }

    @Test
    fun `when getting all saved repos should invoke correct method`() {
        localResource.getAllRepos()

        verify(repoDoa).getAll()
    }

    @Test
    fun `when removing all items in the table should invoke correct method`() {
        localResource.clearTable().test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `should save all items in the table successfully`() {
        localResource.saveList(entityList).test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `when subscribing the repo list should emit list successfully`() {
        localResource.getThat().test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(entityList)
    }
}
