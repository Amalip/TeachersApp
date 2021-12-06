package com.amalip.teachers.presentation.login

import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.domain.model.User

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class LoginViewState : BaseViewState() {

    data class UserFound(val user: User) : BaseViewState()

}