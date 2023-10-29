package com.example.huc_app.ui.home

import com.example.huc_app.domain.mappers.Mapper
import com.example.huc_app.domain.models.UniversityNews
import com.example.huc_app.ui.home.newsUIState.NewsUIState
import javax.inject.Inject

class NewsUIMapper @Inject constructor(): Mapper<UniversityNews, NewsUIState>() {
    override fun map(input: UniversityNews): NewsUIState {
        return NewsUIState(
            date = input.date,
            imagePath = input.imagePath,
            title = input.title,
            details = input.details,
        )
    }
}