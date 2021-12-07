package com.amalip.teachers.presentation.course.list

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.core.presentation.BaseViewModel
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

    init {
        getLocalUser(UseCase.None()) {
            it.fold(::handleFailure) {
                userId = it.id
                getCoursesByUser()
            }
        }
    }

    fun getCoursesByUser() {
        getCoursesByUser(userId) {
            it.fold(::handleFailure) {
                state.value = CoursesViewState.CoursesReceived(it)

                true
            }
        }
    }

}