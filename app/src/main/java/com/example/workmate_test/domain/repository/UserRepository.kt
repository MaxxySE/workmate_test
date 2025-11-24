package com.example.workmate_test.domain.repository

import com.example.workmate_test.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun generateNewUser(gender: String?, nat: String?): User?
    fun getUsers(): Flow<List<User>>
    fun getUserById(userId: String): Flow<User>
}