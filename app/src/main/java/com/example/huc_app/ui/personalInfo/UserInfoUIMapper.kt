package com.example.huc_app.ui.personalInfo

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.UserInfo
import com.example.huc_app.ui.personalInfo.userInfoUIState.UserInfoUIState
import javax.inject.Inject

class UserInfoUIMapper @Inject constructor() : Mapper<UserInfo, UserInfoUIState>() {
    override fun map(input: UserInfo): UserInfoUIState {
        return UserInfoUIState(
            id = input.id,
            username = input.username,
            fullName = input.fullName,
            motherName = input.motherName,
            birthYear = input.birthYear,
            address = input.address,
            personalPhoneNumber = input.personalPhoneNumber,
            parentPhoneNumber = input.parentPhoneNumber,
            gender = input.gender,
            nationality = input.nationality,
            religion = input.religion,
            nationalism = input.nationalism,
            governorate = input.governorate,
        )
    }
}