package com.example.newgithubuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.newgithubuser.data.local.OwnerEntity
import com.example.newgithubuser.data.local.RepoDao
import com.example.newgithubuser.data.local.RepoDatabase
import com.example.newgithubuser.data.local.RepoEntity
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private val firstEntity = RepoEntity(
        fuckId = 1,
        id = 1,
        name = "John",
        description = "Tester name",
        owner = OwnerEntity("login", "avatarUrl", "url"),
        fork = true,
        url = "url"
    )
    private val secondEntity = RepoEntity(
        fuckId = 2,
        id = 1,
        name = "Joe",
        description = "Second Tester name",
        owner = OwnerEntity("login-init", "avatarUrl", "url-url"),
        fork = false,
        url = "url"
    )
    private val firstDiffEntity = RepoEntity(
        fuckId = 22,
        id = 22,
        name = "John",
        description = "Tester name-surname",
        owner = OwnerEntity("login", "avatarUrl", "url"),
        fork = true,
        url = "url-klr"
    )
    private val secondDiffEntity = RepoEntity(
        fuckId = 33,
        id = 33,
        name = "Joe EE",
        description = "Third Tester name",
        owner = OwnerEntity("login", "avatarUrurl", "url-url"),
        fork = false,
        url = "url-url"
    )
    private val repoEntityList = listOf(firstEntity, secondEntity)
    private val repoDiffEntityList = listOf(firstDiffEntity, secondDiffEntity)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private var db: RepoDatabase = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().context,
        RepoDatabase::class.java
    ).build()
    private var repoDAO: RepoDao = db.repoDoa()

    var testSubscriber = TestObserver<List<RepoEntity>>()

    @After
    fun closeDB() {
        db.close()
    }

    @Before
    fun clearDB() {
        db.clearAllTables()
    }

    @Test
    fun shouldSaveItemsSuccessfully() {
        repoDAO.saveAll(repoEntityList)

        assertEquals(repoDAO.getAll(), repoEntityList)
    }

    @Test
    fun shouldRetrieveItemsSuccessfully() {
        repoDAO.saveAll(repoEntityList)
        repoDAO.saveAll(repoDiffEntityList)

        assertEquals(repoDAO.getAll(), repoEntityList + repoDiffEntityList)
    }

    @Test
    fun shouldObserveRepoTable() {
        repoDAO.saveAll(repoEntityList)

        repoDAO.getThat().test()
            .assertNoErrors()
            .assertValue {
             it == repoEntityList
                && it[0].name == "John"
            }
    }

    @Test
    fun shouldClearAllRowsFromTable() {
        repoDAO.saveAll(repoEntityList)

        repoDAO.clear()

        assertEquals(repoDAO.getAll().size, 0)
    }
}
