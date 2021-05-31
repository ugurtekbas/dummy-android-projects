package com.example.newgithubuser.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newgithubuser.domain.RepoItem

@Entity(tableName = RepoDatabase.TABLE_NAME)
data class RepoEntity(
    @PrimaryKey(autoGenerate = true) val fuckId: Int,
    val id: Int,
    val name: String,
    val description: String,
    @Embedded val owner: OwnerEntity,
    val fork: Boolean,
    val url: String
)

@Entity
data class OwnerEntity(
    val login: String,
    val avatarUrl: String,
    @ColumnInfo(name = "owner_url") val url: String
)
