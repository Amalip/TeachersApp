package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/6/2021.
 */


class GetLocalUser @Inject constructor(private val userRepository: UserRepository) :
    UseCase<User, UseCase.None>() {

    override suspend fun run(params: None) = userRepository.getLocalUser()

}