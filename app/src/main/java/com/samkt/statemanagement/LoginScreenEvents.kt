package com.samkt.statemanagement

sealed class LoginScreenEvents {
    data class OnEmailChange(val value: String) : LoginScreenEvents()
    data class OnPasswordChange(val value: String) : LoginScreenEvents()
    data object OnLoginClicked : LoginScreenEvents()
    data object OnEyeClicked : LoginScreenEvents()
}