package com.example.login_signup_app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.login_signup_app.LoginApp
import com.example.login_signup_app.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val sessionManager: com.example.login_signup_app.data.local.SessionManager
) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val user = repository.login(email, password)
                if (user != null) {
                    sessionManager.saveUserSession(user)
                    _loginResult.postValue(true)
                } else {
                    _errorMessage.postValue("Invalid email or password")
                    _loginResult.postValue(false)
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Login failed: ${e.message}")
                _loginResult.postValue(false)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    companion object {
        fun createFactory(application: LoginApp): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LoginViewModel(application.userRepository, application.sessionManager) as T
                }
            }
        }
    }
}