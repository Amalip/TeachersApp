package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 12/7/2021.
 */
class UpdateProfile @Inject constructor(private val userRepository: UserRepository) :
    UseCase<Boolean, User>() {

    override suspend fun run(params: User) = userRepository.updateUser(params)

}