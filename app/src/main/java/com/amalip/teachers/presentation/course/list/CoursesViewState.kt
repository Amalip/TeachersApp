package com.amalip.teachers.presentation.course.list

import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.domain.model.Course

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class CoursesViewState : BaseViewState() {

    data class CoursesReceived(val courses: List<Course>) : BaseViewState()

}