package com.example.huc_app.data.remote.response

data class AccountDetailsDto(
    var email: String?,
    var full_name: String?,
    var en_full_name: String?,
    var personal_picture: String?,
    var token: Token?,
    var timestamp: String?,
    var dep_name: String?,
    var en_dep_name: String?,
    var edu: String?,
    var stage: String?,
)