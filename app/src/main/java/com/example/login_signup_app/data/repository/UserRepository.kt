package com.example.login_signup_app.data.repository

import com.example.login_signup_app.data.local.UserDao
import com.example.login_signup_app.data.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun signup(user: User) = userDao.insertUser(user)

    suspend fun login(email: String, password: String): User? = userDao.login(email, password)

    suspend fun checkEmailExists(email: String): Boolean =
        userDao.getUserByEmail(email) != null
}