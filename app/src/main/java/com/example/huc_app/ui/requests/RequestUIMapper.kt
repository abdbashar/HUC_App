package com.example.huc_app.ui.requests

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.Request
import com.example.huc_app.ui.requests.requestsUIState.RequestUIState
import javax.inject.Inject

class RequestUIMapper @Inject constructor() : Mapper<List<Request>, List<RequestUIState>>() {
    override fun map(input: List<Request>): List<RequestUIState> {
        return input.map {
            RequestUIState(
                id = it.id,
                requestType = it.requestType,
                status = it.status,
                addressedTo = it.addressedTo,
                date = it.date,
            )
        }
    }
}