package com.example.workmate_test.presentation.user.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workmate_test.domain.model.User
import com.example.workmate_test.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    fun loadUser(userId: String) {
        viewModelScope.launch {
            getUserByIdUseCase(userId).collect { userDomain ->
                _user.value = userDomain
            }
        }
    }
}