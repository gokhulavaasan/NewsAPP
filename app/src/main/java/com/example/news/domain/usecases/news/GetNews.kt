package com.example.news.domain.usecases.news

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.news.domain.model.Article
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources:List<String>):Flow<PagingData<Article>>{
        return newsRepository.getNews(sources=sources)
    }
}