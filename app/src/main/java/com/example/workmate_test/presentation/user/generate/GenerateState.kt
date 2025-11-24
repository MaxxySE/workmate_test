package com.example.workmate_test.presentation.user.generate

import com.example.workmate_test.domain.model.User

sealed interface GenerateState {
    data object Initial : GenerateState
    data object Loading : GenerateState
    data class Success(val user: User) : GenerateState
    data class Error(val message: String) : GenerateState
}