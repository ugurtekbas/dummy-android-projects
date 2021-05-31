package com.example.newgithubuser.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import javax.inject.Inject

class RepoNavigator @Inject constructor(
    private val context: Context
) : Navigator {

    override fun navigateToSite(url: String) {
        context.startActivity(
            Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
        )
    }
}
