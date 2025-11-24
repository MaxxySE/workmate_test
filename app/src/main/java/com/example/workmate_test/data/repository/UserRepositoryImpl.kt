package com.example.workmate_test.data.repository

import com.example.workmate_test.data.api.UserApiService
import com.example.workmate_test.data.local.dao.UserDao
import com.example.workmate_test.data.mapper.toDomain
import com.example.workmate_test.data.mapper.toEntity
import com.example.workmate_test.domain.model.User
import com.example.workmate_test.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val api: UserApiService,
    private val dao: UserDao
) : UserRepository {

    override suspend fun generateNewUser(gender: String?, nat: String?): User? {
        return try {
            val response = api.getUser(gender = gender, nat = nat)

            if (response.isSuccessful) {
                val user = response.body()?.results?.firstOrNull()?.toDomain()

                if (user != null) {
                    dao.insertUser(user.toEntity())
                }

                user
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun getUsers(): Flow<List<User>> {
        return dao.getAllUsers().map { entities ->
            entities.map { it.toDomain() }
                .reversed()
        }
    }

    override fun getUserById(userId: String): Flow<User> {
        return dao.getUserById(userId).map { it.toDomain() }
    }
}