package com.example.workmate_test.data.mapper

import com.example.workmate_test.data.local.entity.UserEntity
import com.example.workmate_test.data.model.UserDto
import com.example.workmate_test.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        firstName = this.name?.first ?: "",
        lastName = this.name?.last ?: "",
        gender = this.gender ?: "Unknown",
        age = this.dob?.age ?: 0,
        birthDate = this.dob?.date?.take(10) ?: "N/A",
        phone = this.phone ?: "",
        email = this.email ?: "",
        nationality = this.nat ?: "",
        photoUrl = this.picture?.large ?: ""
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        gender = this.gender,
        age = this.age,
        birthDate = this.birthDate,
        phone = this.phone,
        email = this.email,
        nationality = this.nationality,
        photoUrl = this.photoUrl
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        gender = this.gender,
        age = this.age,
        birthDate = this.birthDate,
        phone = this.phone,
        email = this.email,
        nationality = this.nationality,
        photoUrl = this.photoUrl
    )
}