package com.example.newgithubuser.di

import android.content.Context
import androidx.room.Room
import com.example.newgithubuser.data.DataRepository
import com.example.newgithubuser.data.RepoDataRepository
import com.example.newgithubuser.data.RepoItemMapper
import com.example.newgithubuser.data.local.LocalDataResource
import com.example.newgithubuser.data.local.RepoDatabase
import com.example.newgithubuser.data.remote.RemoteDataResource
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.UseCaseInterace
import com.example.newgithubuser.presentation.ui.utils.ImageService
import com.example.newgithubuser.util.AppSchedulerProvider
import com.example.newgithubuser.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @ActivityScope
    @Provides
    fun provideRemoteUseCase(repository: RepoDataRepository):UseCaseInterace =
        GetReposUseCase(repository)

    @ActivityScope
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataResource, localDatasource: LocalDataResource, mapper: RepoItemMapper
    ): DataRepository =
        RepoDataRepository(remoteDataSource, localDatasource, mapper)

    @ActivityScope
    @Provides
    fun provideLocalDao(database: RepoDatabase) = database.repoDoa()

    @ActivityScope
    @Provides
    fun provideLocalDB(context: Context) =
        Room.databaseBuilder(
            context,
            RepoDatabase::class.java,
            RepoDatabase.DATABASE_NAME
        )
        .allowMainThreadQueries()
        .build()

    @ActivityScope
    @Provides
    fun provideImageService(context: Context) = ImageService(context)

    @ActivityScope
    @Provides
    fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()
}
