package com.example.news.presentation.details

sealed class DetailsEvent {

    object SaveArticleEvent : DetailsEvent()
}