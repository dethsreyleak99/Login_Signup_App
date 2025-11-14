package com.example.login_signup_app

import android.app.Application
import com.example.login_signup_app.data.local.AppDatabase
import com.example.login_signup_app.data.local.SessionManager
import com.example.login_signup_app.data.repository.UserRepository

class LoginApp : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val userRepository by lazy { UserRepository(database.userDao()) }
    lateinit var sessionManager: SessionManager

    override fun onCreate() {
        super.onCreate()
        sessionManager = SessionManager(this)
    }
}