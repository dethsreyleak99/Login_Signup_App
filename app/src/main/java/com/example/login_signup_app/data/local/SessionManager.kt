package com.example.login_signup_app.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.login_signup_app.data.model.User

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_REMEMBER_ME = "remember_me"
    }

    fun saveUserSession(user: User, rememberMe: Boolean = false) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putInt(KEY_USER_ID, user.id)
            putString(KEY_USERNAME, user.username)
            putString(KEY_EMAIL, user.email)
            putBoolean(KEY_REMEMBER_ME, rememberMe)
            apply()
        }
    }

    fun getCurrentUser(): User? {
        return if (isLoggedIn()) {
            User(
                id = sharedPreferences.getInt(KEY_USER_ID, -1),
                username = sharedPreferences.getString(KEY_USERNAME, "") ?: "",
                email = sharedPreferences.getString(KEY_EMAIL, "") ?: "",
                password = "" // Password not stored in session
            )
        } else {
            null
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun shouldRememberMe(): Boolean {
        return sharedPreferences.getBoolean(KEY_REMEMBER_ME, false)
    }

    fun clearSession() {
        with(sharedPreferences.edit()) {
            remove(KEY_IS_LOGGED_IN)
            remove(KEY_USER_ID)
            remove(KEY_USERNAME)
            remove(KEY_EMAIL)
            // Keep remember_me preference if set
            if (!shouldRememberMe()) {
                remove(KEY_REMEMBER_ME)
            }
            apply()
        }
    }

    fun updateUsername(newUsername: String) {
        sharedPreferences.edit().putString(KEY_USERNAME, newUsername).apply()
    }
}