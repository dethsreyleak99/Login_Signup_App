package com.example.login_signup_app.ui.signup

import androidx.lifecycle.*
import com.example.login_signup_app.data.model.User
import com.example.login_signup_app.data.repository.UserRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel


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
                _errorMessage.postValue(e.message)
            }
        }
    }
}
