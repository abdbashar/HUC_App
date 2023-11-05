package com.example.huc_app.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserUndergradInfoDTO(
    @SerializedName("schoo_name")
    val schoolName: String,
    @SerializedName("md_name")
    val directorate: String,
    @SerializedName("record_number")
    val graduationRecordNumber: String,
    @SerializedName("record_number_date")
    val graduationRecordDate: String,
    @SerializedName("way")
    val branch: String,
    @SerializedName("Dorea")
    val attempt: String,
    @SerializedName("prep_document")
    val secondarySchoolCertificate: String
)
