package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.UserUndergradInfoDTO
import com.example.huc_app.domain.models.UserSecondarySchoolInfo
import javax.inject.Inject

class UserSecondarySchoolInfoMapper @Inject constructor() :
    Mapper<UserUndergradInfoDTO, UserSecondarySchoolInfo>() {
    override fun map(input: UserUndergradInfoDTO): UserSecondarySchoolInfo {
        return UserSecondarySchoolInfo(
            schoolName = input.schoolName ?: "",
            directorate = input.directorate ?: "",
            graduationRecordNumber = input.graduationRecordNumber ?: "",
            graduationRecordDate = input.graduationRecordDate ?: "",
            branch = input.branch ?: "",
            attempt = input.attempt ?: "",
            secondarySchoolCertificate = input.secondarySchoolCertificate ?: "",
        )
    }
}