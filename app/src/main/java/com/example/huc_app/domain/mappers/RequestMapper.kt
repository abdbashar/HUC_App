package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.RequestDTO
import com.example.huc_app.domain.models.Request
import javax.inject.Inject

class RequestMapper @Inject constructor() : Mapper<List<RequestDTO>, List<Request>>() {
    override fun map(input: List<RequestDTO>): List<Request> {
        return input.map {
            Request(
                id = it.id ?: 0,
                status = it.status ?: "",
                addressedTo = it.title ?: "",
                requestType = it.type ?: "",
                date = it.created_date ?: ""
            )
        }
    }
}