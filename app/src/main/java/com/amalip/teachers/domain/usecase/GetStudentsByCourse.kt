package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 12/6/2021.
 */
class GetStudentsByCourse @Inject constructor(private val userRepository: UserRepository) :
    UseCase<List<User>, Int>() {

    override suspend fun run(params: Int) = userRepository.getStudentsByCourse(params)

}