package com.example.workmate_test.domain.model

data class User(
    val id: String = java.util.UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val gender: String,
    val age: Int,
    val birthDate: String,
    val phone: String,
    val email: String,
    val nationality: String,
    val photoUrl: String
)