package com.example.huc_app.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserInfoDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("mother_name")
    val motherName: String,
    @SerializedName("birth_year")
    val birthYear: Int,
    @SerializedName("endorsement_address")
    val address: String,
    @SerializedName("phone_number")
    val personalPhoneNumber: String,
    @SerializedName("superior_phone_number")
    val parentPhoneNumber: String,
    @SerializedName("sex")
    val gender: String,
    @SerializedName("nationality")
    val nationality: Int,
    @SerializedName("religion")
    val religion: Int,
    @SerializedName("inationality")
    val nationalism: Int,
    @SerializedName("gev")
    val governorate: String
)
