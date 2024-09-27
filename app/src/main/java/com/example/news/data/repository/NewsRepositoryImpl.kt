package com.example.news.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news.data.remote.NewsApi
import com.example.news.data.remote.NewsPagingSource
import com.example.news.domain.model.Article
import com.example.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory ={
                NewsPagingSource(
                    newsApi= newsApi,
                    source= sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}