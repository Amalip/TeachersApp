package com.amalip.teachers.presentation.profile

import com.amalip.teachers.R
import com.amalip.teachers.core.exception.Failure

/**
 * Created by Amalip on 10/29/2021.
 */

sealed class ProfileFailure {

    object NotFound : Failure.FeatureFailure(R.string.text_no_info)
    object CompleteForm : Failure.FeatureFailure(R.string.failure_complete_form)
    object CouldNotUpdate : Failure.FeatureFailure(R.string.failure_could_not_update)

}