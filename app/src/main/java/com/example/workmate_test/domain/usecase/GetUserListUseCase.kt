package com.example.workmate_test.domain.usecase

import com.example.workmate_test.domain.model.User
import com.example.workmate_test.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserListUseCase(private val repository: UserRepository) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}