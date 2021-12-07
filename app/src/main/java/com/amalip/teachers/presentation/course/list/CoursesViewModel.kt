package com.amalip.teachers.presentation.course.list

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.core.presentation.BaseViewModel
import com.amalip.teachers.domain.model.Course
import com.amalip.teachers.domain.usecase.GetCoursesByUser
import com.amalip.teachers.domain.usecase.GetLocalUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser,
    private val getCoursesByUser: GetCoursesByUser
) : BaseViewModel() {

    private var userId = 0
    private val courses = mutableListOf<Course>()

    init {
        getLocalUser(UseCase.None()) {
            it.fold(::handleFailure) {
                userId = it.id
                getCoursesByUser()
            }
        }
    }

    fun getCoursesByUser() {
        if (userId > 0)
            getCoursesByUser(userId) {
                it.fold(::handleFailure) {
                    courses.clear()
                    courses.addAll(it)
                    state.value = CoursesViewState.CoursesReceived(it)

                    true
                }
            }
    }

    fun searchCourse(query: String) {
        val filteredList = courses.filter { it.name.uppercase().contains(query.uppercase()) }

        state.value = CoursesViewState.CoursesReceived(filteredList)

    }

}