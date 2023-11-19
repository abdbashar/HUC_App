package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.UserInfoDTO
import com.example.huc_app.domain.models.UserInfo
import javax.inject.Inject

class UserInfoMapper @Inject constructor() : Mapper<UserInfoDTO, UserInfo>() {
    override fun map(input: UserInfoDTO): UserInfo {
        return UserInfo(
            id = input.id ?: 0,
            username = input.username ?: "",
            fullName = input.fullName ?: "",
            motherName = input.motherName ?: "",
            birthYear = input.birthYear ?: 0,
            address = input.address ?: "",
            personalPhoneNumber = input.personalPhoneNumber ?: "",
            parentPhoneNumber = input.parentPhoneNumber ?: "",
            gender = input.gender ?: "",
            nationality = input.nationality ?: 0,
            religion = input.religion ?: 0,
            nationalism = input.nationalism ?: 0,
            governorate = input.governorate ?: "",
        )
    }
}

