package com.example.news.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.news.domain.model.Article
import com.example.news.presentation.onboarding.Dimens.ExtraSmallPadding
import com.example.news.presentation.onboarding.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.EmptyScreen

@Composable
fun ArticlesList(
    modifier: Modifier= Modifier,
    articles: List<Article>,
    onClick:(Article)-> Unit
){
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all= ExtraSmallPadding)
    ) {
        items(count = articles.size){ it ->
            val article= articles[it]
            ArticleCard(article = article, onClick = {onClick(article)})
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier= Modifier,
    articles: LazyPagingItems<Article>,
    onClick:(Article)-> Unit
){
    val handlingPagingResult = handlePagingResult(articles=articles)
    if (handlingPagingResult){
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all= ExtraSmallPadding)
        ) {
            items(count = articles.itemCount){ it ->
                articles[it]?.let{
                    ArticleCard(article = it, onClick = {onClick(it)})
                }
            }
        }
    }
}
@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
):Boolean{
    val loadState=articles.loadState
    val error =when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.prepend as LoadState.Error
        else -> null
    }
    return when{
        loadState.refresh is LoadState.Loading ->{
            ShimmerEffect()
            false
        }
        error != null ->{
            EmptyScreen()
            false
        }
        else ->{
            true
        }
    }
}
@Composable
fun ShimmerEffect(){
    Column (
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ){
      repeat(10) {
          ArticleCardShimmerEffect(
              modifier = Modifier.padding(horizontal = MediumPadding1)
          )
      }
    }
}