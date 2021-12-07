package com.amalip.teachers.presentation.profile

import com.amalip.teachers.core.interactor.UseCase
import com.amalip.teachers.core.presentation.BaseViewModel
import com.amalip.teachers.domain.usecase.GetLocalUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser
) : BaseViewModel() {

    init {
        getLocalUser(UseCase.None()) {
            it.fold({}) {
                state.value = ProfileViewState.UserFound(it)

                true
            }
        }
    }

}