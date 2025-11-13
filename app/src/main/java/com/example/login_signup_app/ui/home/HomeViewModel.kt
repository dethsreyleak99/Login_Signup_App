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

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> get() = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loadUserData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // For demo - in real app, get actual logged-in user
                _userData.value = User(
                    id = 1,
                    username = "John Doe",
                    email = "john@example.com",
                    password = ""
                )
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        _userData.value = null
    }

    companion object {
        fun createFactory(application: LoginApp): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(application.userRepository) as T
                }
            }
        }
    }
}
