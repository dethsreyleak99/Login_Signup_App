package com.example.login_signup_app.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.login_signup_app.LoginApp
import com.example.login_signup_app.data.model.User
import com.example.login_signup_app.data.repository.UserRepository
import kotlinx.coroutines.launch

class SignupViewModel(private val repository: UserRepository) : ViewModel() {

    private val _signupResult = MutableLiveData<Boolean>()
    val signupResult: LiveData<Boolean> get() = _signupResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun signup(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                if (repository.checkEmailExists(email)) {
                    _errorMessage.postValue("Email already exists")
                } else {
                    repository.signup(User(username = username, email = email, password = password))
                    _signupResult.postValue(true)
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Signup failed: ${e.message}")
            }
        }
    }

    companion object {
        fun createFactory(application: LoginApp): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SignupViewModel(application.userRepository) as T
                }
            }
        }
    }
}
