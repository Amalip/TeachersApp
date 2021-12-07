package com.amalip.teachers.presentation.course.detail

import com.amalip.teachers.R
import com.amalip.teachers.core.exception.Failure

/**
 * Created by Amalip on 9/27/2021.
 */

class CourseFailure {

    object NotFound : Failure.FeatureFailure(R.string.text_no_info)

}