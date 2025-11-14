package com.example.login_signup_app.ui.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.login_signup_app.LoginApp
import com.example.login_signup_app.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val repository: UserRepository) : ViewModel() {

    private val _resetResult = MutableLiveData<Boolean>()
    val resetResult: LiveData<Boolean> get() = _resetResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun sendPasswordReset(email: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Simulate API call delay
                delay(2000)

                // Check if email exists
                val userExists = repository.checkEmailExists(email)
                if (userExists) {
                    // In a real app, send actual email here
                    // For demo, we'll just simulate success
                    _resetResult.postValue(true)
                } else {
                    _errorMessage.postValue("No account found with this email")
                    _resetResult.postValue(false)
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Failed to send reset email: ${e.message}")
                _resetResult.postValue(false)
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
                    return ForgotPasswordViewModel(application.userRepository) as T
                }
            }
        }
    }
}