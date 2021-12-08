package com.amalip.teachers.core.exception

import androidx.annotation.StringRes
import com.amalip.teachers.R

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure : Throwable() {
    data class ServerError(val code: Int, val serverMessage: String?, val payload: String? = null) :
        Failure()

    object NetworkConnection : FeatureFailure(R.string.failure_no_internet)
    object DatabaseError : FeatureFailure(R.string.failure_db)
    object Unauthorized : FeatureFailure(R.string.failure_unauthorized)

    object SilenceFail: Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure(@StringRes val defaultMessage: Int? = null, val extra: Any? = null) : Failure()
}