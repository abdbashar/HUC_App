package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.UserUniversityInfoDTO
import com.example.huc_app.domain.models.UserUniversityInfo
import javax.inject.Inject

class UserUniversityInfoMapper @Inject constructor() :
    Mapper<UserUniversityInfoDTO, UserUniversityInfo>() {
    override fun map(input: UserUniversityInfoDTO): UserUniversityInfo {
        return UserUniversityInfo(
            department = input.department ?: "",
            classType = input.classType ?: "",
            email = input.email ?: "",
            startDate = input.startYear ?: "",
            universityNumber = input.universityNumber ?: "",
            window = input.window ?: "",
        )
    }
}