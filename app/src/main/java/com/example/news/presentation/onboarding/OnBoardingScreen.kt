package com.example.news.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.news.presentation.onboarding.components.OnBoardingPage

@Composable
fun OnBoarding(){
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0){
            pages.size
        }
        val buttonstate = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("","NEXT")
                    1-> listOf("PREVIOUS","NEXT")
                    2-> listOf("PREVIOUS","GET STARTED")
                    else -> {
                        listOf("","")
                    }
                }
            }
        }
        HorizontalPager(state = pagerState) {index->
            OnBoardingPage(page = pages[index])

        }
    }
}