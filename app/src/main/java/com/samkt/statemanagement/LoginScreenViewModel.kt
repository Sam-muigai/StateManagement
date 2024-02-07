package com.samkt.statemanagement

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel : ViewModel() {

    private var _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    fun onEvent(
        loginScreenEvents: LoginScreenEvents
    ) {
        when (loginScreenEvents) {
            is LoginScreenEvents.OnEmailChange -> {
                _loginScreenState.update {
                    it.copy(
                        email = loginScreenEvents.value
                    )
                }
            }

            is LoginScreenEvents.OnEyeClicked -> {
                _loginScreenState.update {
                    it.copy(
                        isPasswordVisible = !loginScreenState.value.isPasswordVisible
                    )
                }
            }
            is LoginScreenEvents.OnLoginClicked -> {
                loginUser()
            }
            is LoginScreenEvents.OnPasswordChange -> {
                _loginScreenState.update {
                    it.copy(
                        password = loginScreenEvents.value
                    )
                }
            }
        }
    }

    private fun loginUser() {
        loginScreenState.value.apply {
            if (email.isEmpty()){
                // TODO: Show certain error
            }else if (password.isEmpty()){
                // TODO: Show certain error
            }else{
                // TODO: Login user

            }
        }
    }


}