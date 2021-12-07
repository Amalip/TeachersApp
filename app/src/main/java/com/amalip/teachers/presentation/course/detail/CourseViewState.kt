package com.amalip.teachers.presentation.course.detail

import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.domain.model.User

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class CourseViewState : BaseViewState() {

    data class StudentsReceived(val students: List<User>) : BaseViewState()

}