package com.example.news.domain.usecases.news

import com.example.news.data.local.NewsDao
import com.example.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}