package com.amalip.teachers.core.presentation

import com.amalip.teachers.core.exception.Failure

/**
 * Created by Amalip on 9/27/2021.
 */

interface OnFailure {

    fun handleFailure(failure: Failure?)

}