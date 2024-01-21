package com.example.huc_app.domain.useCases

import com.example.huc_app.data.local.datastore.DataStorePreferences
import com.example.huc_app.domain.models.StudentDetails
import com.example.huc_app.util.Constants
import javax.inject.Inject

class GetStudentDetailsUseCase @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) {
    private fun readUserDetails(): StudentDetails {
        return StudentDetails(
            email = dataStorePreferences.readString(Constants.EMAIL_KEY) ?: "",
            fullNameInArabic = dataStorePreferences.readString(Constants.USER_NAME_AR_KEY) ?: "",
            fullNameInEnglish = dataStorePreferences.readString(Constants.USER_NAME_EN_KEY) ?: "",
            personalPicture = dataStorePreferences.readString(Constants.USER_IMAGE_KEY) ?: "",
            departmentNameInArabic = dataStorePreferences.readString(Constants.DEPARTMENT_NAME_AR_KEY) ?: "",
            departmentNameInEnglish = dataStorePreferences.readString(Constants.DEPARTMENT_NAME_EN_KEY) ?: "",
            studySystemType = dataStorePreferences.readString(Constants.STUDY_SYSTEM_TYPE_KEY) ?: "",
            stage = dataStorePreferences.readString(Constants.STAGE_KEY) ?: "",
        )
    }

    operator fun invoke(): StudentDetails {
        return readUserDetails()
    }
}

