package com.amalip.teachers.presentation.profile

import com.amalip.teachers.core.presentation.BaseViewState

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class ProfileViewState : BaseViewState() {

    object Updated : ProfileViewState()
    object LoggedOut: ProfileViewState()

}