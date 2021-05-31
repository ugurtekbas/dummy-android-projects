package com.example.newgithubuser.di

import android.content.Context
import androidx.fragment.app.DialogFragment
import com.example.newgithubuser.navigation.Navigator
import com.example.newgithubuser.navigation.RepoNavigator
import com.example.newgithubuser.presentation.ui.MainActivity
import dagger.*


@Component(
    modules = [DialogModule::class]
)
interface DialogComponent {

    @Component.Factory
    interface Factory {
        fun create(): DialogComponent
    }

    fun inject(fragment: DialogFragment)

    companion object {

        fun create(
        ): DialogComponent {
            return DaggerDialogComponent
                .factory()
                .create()
        }
    }
}

@Module
class DialogModule {

    @Provides
    fun provideNavigator(context: Context) = RepoNavigator(context)
}
