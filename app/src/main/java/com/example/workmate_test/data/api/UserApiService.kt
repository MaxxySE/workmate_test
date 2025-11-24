package com.example.workmate_test.data.api

import com.example.workmate_test.data.model.UserResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("api/")
    suspend fun getUser(
        @Query("gender") gender: String? = null,
        @Query("nat") nat: String? = null
    ): Response<UserResponseDto>
}