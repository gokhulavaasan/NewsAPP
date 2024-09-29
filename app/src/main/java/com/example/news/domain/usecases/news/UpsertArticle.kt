package com.example.news.domain.usecases.news

import com.example.news.data.local.NewsDao
import com.example.news.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }
}