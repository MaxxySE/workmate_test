package com.example.workmate_test.data.model

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
    @SerializedName("results") val results: List<UserDto>
)

data class UserDto(
    @SerializedName("gender") val gender: String?,
    @SerializedName("name") val name: NameDto?,
    @SerializedName("email") val email: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("nat") val nat: String?,
    @SerializedName("picture") val picture: PictureDto?,
    @SerializedName("dob") val dob: DobDto?
)

data class NameDto(
    @SerializedName("first") val first: String?,
    @SerializedName("last") val last: String?
)

data class PictureDto(
    @SerializedName("large") val large: String?
)

data class DobDto(
    @SerializedName("age") val age: Int?,
    @SerializedName("date") val date: String?
)