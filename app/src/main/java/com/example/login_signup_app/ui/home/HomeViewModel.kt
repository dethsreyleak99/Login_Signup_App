package com.example.login_signup_app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.login_signup_app.LoginApp
import com.example.login_signup_app.data.model.User
import com.example.login_signup_app.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository,
    private val app: LoginApp
) : ViewModel() {

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loadUserData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Get actual logged-in user from session
                val currentUser = app.sessionManager.getCurrentUser()
                if (currentUser != null) {
                    // Fetch fresh data from database
                    val freshUser = repository.getUserByEmail(currentUser.email)
                    _userData.value = freshUser
                } else {
                    _userData.value = null
                }
            } catch (e: Exception) {
                // Fallback to session data
                _userData.value = app.sessionManager.getCurrentUser()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        app.sessionManager.clearSession()
        _userData.value = null
    }

    fun updateUsername(newUsername: String) {
        viewModelScope.launch {
            try {
                val currentUser = _userData.value
                currentUser?.let { user ->
                    // In a real app, you would update in database
                    app.sessionManager.updateUsername(newUsername)
                    _userData.value = user.copy(username = newUsername)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    companion object {
        fun createFactory(application: LoginApp): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(application.userRepository, application) as T
                }
            }
        }
    }
}