package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 12/5/2021.
 */
class DoLogout @Inject constructor(private val userRepository: UserRepository) :
    UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None) = userRepository.doLogout()

}