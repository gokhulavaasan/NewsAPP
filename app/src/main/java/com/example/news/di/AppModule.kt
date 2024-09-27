package com.example.news.di

import android.app.Application
import com.example.news.data.manager.LocalUserManagerImpl
import com.example.news.data.remote.NewsApi
import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.manager.LocalUserManager
import com.example.news.domain.repository.NewsRepository
import com.example.news.domain.usecases.app_entry.AppEntryUseCases
import com.example.news.domain.usecases.app_entry.ReadAppEntry
import com.example.news.domain.usecases.app_entry.SaveAppEntry
import com.example.news.domain.usecases.news.GetNews
import com.example.news.domain.usecases.news.NewsUseCases
import com.example.news.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ):LocalUserManager=LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager:LocalUserManager
    )= AppEntryUseCases(
        readAppEntry=ReadAppEntry(localUserManager),
        saveAppEntry=SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi:NewsApi
    ):NewsRepository=NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }
}