package com.example.newgithubuser.domain

data class RepoItem(
    val id: Int,
    val name: String,
    val description: String,
    val ownerLogin: String,
    val profilePicture: String,
    val background: Int,
    val url: String
)
