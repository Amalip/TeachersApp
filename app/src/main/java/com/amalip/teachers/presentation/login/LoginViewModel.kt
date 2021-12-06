package com.amalip.teachers.presentation.login

import androidx.lifecycle.MutableLiveData
import com.amalip.teachers.core.presentation.BaseViewModel
import com.amalip.teachers.domain.model.User
import com.amalip.teachers.domain.usecase.FindUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(private val findUser: FindUser) : BaseViewModel() {

    val enrollment = MutableLiveData("")
    val password = MutableLiveData("")

    fun doLogin() {
        findUser(User(enrollment = enrollment.value ?: "", password = password.value ?: "")) {
            it.fold(::handleFailure) {
                state.value = LoginViewState.UserFound(it)

                true
            }
        }

    }

}