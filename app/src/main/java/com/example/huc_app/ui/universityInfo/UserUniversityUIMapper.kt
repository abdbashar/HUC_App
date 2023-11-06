package com.example.huc_app.ui.universityInfo

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.UserUniversityInfo
import com.example.huc_app.ui.universityInfo.userUniversityInfoUIState.UserUniversityInfoUIState
import javax.inject.Inject

class UserUniversityUIMapper @Inject constructor() : Mapper<UserUniversityInfo, UserUniversityInfoUIState>() {
    override fun map(input: UserUniversityInfo): UserUniversityInfoUIState {
        return UserUniversityInfoUIState(
            department = input.department,
            classType = input.classType,
            startDate = input.startDate,
            window = input.window,
            email = input.email,
            universityNumber = input.universityNumber,
        )
    }
}