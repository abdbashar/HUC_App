package com.example.huc_app.ui.secondarySchoolInfo

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.UserSecondarySchoolInfo
import com.example.huc_app.ui.secondarySchoolInfo.secondarySchoolUIState.UserSecondaryInfoUIState
import javax.inject.Inject

class UserSecondarySchoolUIMapper @Inject constructor() : Mapper<UserSecondarySchoolInfo, UserSecondaryInfoUIState>() {
    override fun map(input: UserSecondarySchoolInfo): UserSecondaryInfoUIState {
        return UserSecondaryInfoUIState(
            schoolName = input.schoolName,
            directorate = input.directorate,
            graduationRecordNumber = input.graduationRecordNumber,
            graduationRecordDate = input.graduationRecordDate,
            branch = input.branch,
            attempt = input.attempt,
            secondarySchoolCertificate = input.secondarySchoolCertificate,
        )
    }
}