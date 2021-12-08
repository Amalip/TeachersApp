package com.amalip.teachers.presentation.course.list

import com.amalip.teachers.R
import com.amalip.teachers.core.exception.Failure

/**
 * Created by Amalip on 9/27/2021.
 */

class CoursesFailure {

    object NotFound : Failure.FeatureFailure(R.string.failure_courses_not_found)
    object CouldNotUpdate : Failure.FeatureFailure(R.string.failure_could_not_update)

}