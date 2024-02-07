package com.samkt.statemanagement

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val isPasswordVisible: Boolean = false
)
