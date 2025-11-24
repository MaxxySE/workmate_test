package com.example.workmate_test.domain.usecase

import com.example.workmate_test.domain.model.User
import com.example.workmate_test.domain.repository.UserRepository

class GenerateUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(gender: String?, nat: String?): User? {
        return repository.generateNewUser(gender = gender, nat = nat)
    }
}