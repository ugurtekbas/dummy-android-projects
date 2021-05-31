package com.example.newgithubuser.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface RepoDao {

    @Query("SELECT * FROM ${RepoDatabase.TABLE_NAME}")
    fun getAll(): List<RepoEntity>

    @Query("SELECT * FROM ${RepoDatabase.TABLE_NAME}")
    fun getThat(): Observable<List<RepoEntity>>

    @Query("SELECT * FROM ${RepoDatabase.TABLE_NAME}")
    fun getByPage(): List<RepoEntity>

    @Insert
    fun saveAll(repos: List<RepoEntity>)

    @Query("DELETE FROM ${RepoDatabase.TABLE_NAME}")
    fun clear()
}
