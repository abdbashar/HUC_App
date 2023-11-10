package com.example.huc_app.domain.mappers

import com.example.huc_app.data.remote.response.UniversityNewsDetailsDTO
import com.example.huc_app.domain.models.UniversityNews
import javax.inject.Inject

class UniversityNewsMapper @Inject constructor(): Mapper<List<UniversityNewsDetailsDTO>, List<UniversityNews>>() {
    override fun map(input: List<UniversityNewsDetailsDTO>): List<UniversityNews> {
        return input.map {
            UniversityNews(
                date = it.date ?: "",
                imagePath = it.default_img ?: "",
                details = it.details ?: "",
                title = it.title ?: "",
            )
        }
    }
}