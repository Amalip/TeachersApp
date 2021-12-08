package com.amalip.teachers.presentation.student

import com.amalip.teachers.core.presentation.BaseViewState

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class StudentViewState : BaseViewState() {

    data class UpdatedGradeForPartial(val partial: Int) : StudentViewState()

}