package com.example.login_signup_app.ui.login

import androidx.lifecycle.*
import com.example.login_signup_app.data.repository.UserRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel


class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.login(email, password)
            _loginResult.postValue(user != null)
        }
    }
}
