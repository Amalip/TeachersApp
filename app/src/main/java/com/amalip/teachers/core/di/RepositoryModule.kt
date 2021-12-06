package com.amalip.teachers.core.di

import com.amalip.teachers.core.plataform.AuthManager
import com.amalip.teachers.core.plataform.NetworkHandler
import com.amalip.teachers.data.api.UserApi
import com.amalip.teachers.data.source.UserRepositoryImpl
import com.amalip.teachers.domain.repository.UserRepository
import com.amalip.teachers.framework.api.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Amalip on 10/20/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMealRepository(
        apiProvider: ApiProvider,
        authManager: AuthManager,
        networkHandler: NetworkHandler
    ): UserRepository =
        UserRepositoryImpl(
            apiProvider.getEndpoint(UserApi::class.java),
            authManager,
            networkHandler
        )

}