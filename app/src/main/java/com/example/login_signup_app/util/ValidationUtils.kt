package com.example.login_signup_app.util

import android.util.Patterns

object ValidationUtils {

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidUsername(username: String): Boolean {
        return username.length >= 3
    }

    fun getEmailError(email: String): String? {
        return when {
            email.isEmpty() -> "Email is required"
            !isValidEmail(email) -> "Valid email is required"
            else -> null
        }
    }

    fun getPasswordError(password: String): String? {
        return when {
            password.isEmpty() -> "Password is required"
            !isValidPassword(password) -> "Password must be at least 6 characters"
            else -> null
        }
    }

    fun getUsernameError(username: String): String? {
        return when {
            username.isEmpty() -> "Username is required"
            !isValidUsername(username) -> "Username must be at least 3 characters"
            else -> null
        }
    }
}