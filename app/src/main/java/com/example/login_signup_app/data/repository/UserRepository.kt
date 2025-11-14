package com.example.login_signup_app.data.repository

import com.example.login_signup_app.data.local.UserDao
import com.example.login_signup_app.data.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun signup(user: User): Long = userDao.insertUser(user)

    suspend fun login(email: String, password: String): User? = userDao.login(email, password)

    suspend fun checkEmailExists(email: String): Boolean =
        userDao.checkEmailExists(email) > 0

    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)

    suspend fun getUserById(userId: Int): User? = userDao.getUserById(userId)

    suspend fun updateUser(user: User): Boolean = userDao.updateUser(user) > 0

    suspend fun updatePassword(email: String, newPassword: String): Boolean =
        userDao.updatePassword(email, newPassword) > 0
}