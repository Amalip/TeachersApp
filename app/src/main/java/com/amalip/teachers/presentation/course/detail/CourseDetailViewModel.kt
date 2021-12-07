package com.amalip.teachers.presentation.course.detail

import com.amalip.teachers.core.presentation.BaseViewModel
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.usecase.GetStudentsByCourse
import com.amalip.teachers.presentation.course.list.CoursesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val getStudentsByCourse: GetStudentsByCourse
) : BaseViewModel() {

    private val students = mutableListOf<User>()

    fun getCoursesByUser(courseId: Int) {
        if (courseId > 0)
            getStudentsByCourse(courseId) {
                it.fold(::handleFailure) {
                    students.clear()
                    students.addAll(it)
                    state.value = CourseViewState.StudentsReceived(it)

                    true
                }
            }
    }

    fun searchCourse(query: String) {
        val filteredList = students.filter { it.fullName.uppercase().contains(query.uppercase()) }

        state.value = CourseViewState.StudentsReceived(filteredList)

    }

}