package com.amalip.teachers.domain.usecase

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.domain.model.Course
import com.amalip.teachers.domain.repository.CourseRepository
import javax.inject.Inject

/**
 * Created by Amalip on 12/6/2021.
 */
class GetCoursesByUser @Inject constructor(private val courseRepository: CourseRepository) :
    UseCase<List<Course>, Int>() {

    override suspend fun run(params: Int) = courseRepository.getCoursesByUser(params)

}