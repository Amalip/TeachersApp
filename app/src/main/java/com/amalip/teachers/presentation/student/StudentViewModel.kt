package com.amalip.teachers.presentation.student

import androidx.lifecycle.MutableLiveData
import com.amalip.teachers.R
import com.amalip.teachers.core.exception.Failure
import com.amalip.teachers.core.presentation.BaseViewModel
import com.amalip.teachers.data.dto.SetGradeRequest
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.usecase.GetCourseByIdAndUserId
import com.amalip.teachers.domain.usecase.SetGrade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class StudentViewModel @Inject constructor(
    private val getCourseByIdAndUserId: GetCourseByIdAndUserId,
    private val setGrade: SetGrade
) :
    BaseViewModel() {

    val user = MutableLiveData(User())
    val grade1 = MutableLiveData("")
    val grade2 = MutableLiveData("")
    val grade3 = MutableLiveData("")
    private var grades = mutableListOf<Double>()

    fun getCoursesByIdAndUser(courseId: Int) {
        getCourseByIdAndUserId(Pair(courseId, user.value?.id ?: 0)) {
            it.fold(::handleFailure) {
                grades = it.grades
                grade1.value = it.grades[0].toString()
                grade2.value = it.grades[1].toString()
                grade3.value = it.grades[2].toString()

                true
            }
        }
    }

    fun setGrade(courseId: Int) {
        grades.forEachIndexed { index, grade ->
            val gradeValue = when (index) {
                0 -> grade1.value?.toDouble() ?: 0.0
                1 -> grade2.value?.toDouble() ?: 0.0
                else -> grade3.value?.toDouble() ?: 0.0
            }

            val partial = index + 1

            if (grade != gradeValue) {
                if (gradeValue > 10 || gradeValue < 0)
                    failure.value =
                        object : Failure.FeatureFailure(R.string.failure_bad_grade_for, partial) {}
                else
                    setGrade(
                        SetGradeRequest(
                            user.value?.id ?: 0,
                            courseId,
                            gradeValue,
                            partial
                        )
                    ) {
                        it.fold(::handleFailure) {
                            state.value = StudentViewState.UpdatedGradeForPartial(partial)

                            true
                        }
                    }
            }
        }
    }


}