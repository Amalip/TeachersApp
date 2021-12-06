package com.amalip.teachers.presentation.login

import com.amalip.teachers.R
import com.amalip.teachers.core.exception.Failure

/**
 * Created by Amalip on 10/29/2021.
 */

sealed class LoginFailure {

    object NotFound : Failure.FeatureFailure(R.string.failure_user_not_found)

}