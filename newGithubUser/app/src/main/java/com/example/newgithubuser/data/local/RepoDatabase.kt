package com.example.newgithubuser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RepoEntity::class), version = RepoDatabase.DATABASE_VERSION)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDoa(): RepoDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "github-repo-database"
        const val TABLE_NAME = "repo_collection"
    }
}
