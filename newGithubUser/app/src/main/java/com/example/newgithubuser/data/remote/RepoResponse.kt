package com.example.newgithubuser.data.remote

import com.google.gson.annotations.SerializedName

data class RepoResponse (
    val id: Int,
    val name: String,
    val description: String?,
    val owner: OwnerResponse,
    val fork: Boolean,
    @SerializedName("html_url") val url: String
)

data class OwnerResponse (
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("html_url") val url: String
)
