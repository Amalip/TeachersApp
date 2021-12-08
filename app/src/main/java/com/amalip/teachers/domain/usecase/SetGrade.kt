package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.data.dto.SetGradeRequest
import com.amalip.teachers.domain.repository.CourseRepository
import javax.inject.Inject

/**
 * Created by Amalip on 12/7/2021.
 */
class SetGrade @Inject constructor(private val courseRepository: CourseRepository) :
    UseCase<Boolean, SetGradeRequest>() {

    override suspend fun run(params: SetGradeRequest) = courseRepository.setGrade(params)

}