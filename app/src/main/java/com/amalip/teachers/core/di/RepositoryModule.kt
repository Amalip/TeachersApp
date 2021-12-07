package com.amalip.teachers.core.di

import com.amalip.teachers.core.plataform.AuthManager
import com.amalip.teachers.core.plataform.NetworkHandler
import com.amalip.teachers.data.api.CourseApi
import com.amalip.teachers.data.api.UserApi
import com.amalip.teachers.data.source.CourseRepositoryImpl
import com.amalip.teachers.data.source.UserRepositoryImpl
import com.amalip.teachers.domain.repository.CourseRepository
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
    fun provideUserRepository(
        apiProvider: ApiProvider,
        authManager: AuthManager,
        networkHandler: NetworkHandler
    ): UserRepository =
        UserRepositoryImpl(
            apiProvider.getEndpoint(UserApi::class.java),
            authManager,
            networkHandler
        )

    @Provides
    @Singleton
    fun provideCourseRepository(
        apiProvider: ApiProvider,
        networkHandler: NetworkHandler
    ): CourseRepository =
        CourseRepositoryImpl(
            apiProvider.getEndpoint(CourseApi::class.java),
            networkHandler
        )

}