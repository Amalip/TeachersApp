package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/29/2021.
 */
class FindUser @Inject constructor(private val userRepository: UserRepository) :
    UseCase<User, User>() {

    override suspend fun run(params: User) = userRepository.findUser(params.name, params.password)

}