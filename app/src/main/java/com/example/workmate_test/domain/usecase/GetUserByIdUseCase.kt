package com.example.workmate_test.domain.usecase

import com.example.workmate_test.domain.repository.UserRepository

class GetUserByIdUseCase(private val repository: UserRepository) {
    operator fun invoke(userId: String) = repository.getUserById(userId)
}