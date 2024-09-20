package com.example.news.presentation.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news.presentation.common.NewsButton
import com.example.news.presentation.common.PreviousNewsButton
import com.example.news.presentation.onboarding.Dimens.pageIndicatorwidth
import com.example.news.presentation.onboarding.Dimens.titlePadding
import com.example.news.presentation.onboarding.components.OnBoardingPage
import com.example.news.presentation.onboarding.components.PageIndicator
import com.example.styleage.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@Composable
fun OnBoarding(
    event: (OnBoardingEvent)->Unit
){
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
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal =30.dp)
            .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            val scope= rememberCoroutineScope()
            PageIndicator(modifier = Modifier.size(pageIndicatorwidth), pageSize = pages.size, selectedPage = pagerState.currentPage)

            Row (verticalAlignment = Alignment.CenterVertically){
                PreviousNewsButton(text=buttonstate.value[0], onClick = {
                    scope.launch { pagerState.animateScrollToPage(page = pagerState.currentPage-1) }
                })
                NewsButton(text = buttonstate.value[1], onClick = {
                    scope.launch {
                        if(pagerState.currentPage==2){
                            event(OnBoardingEvent.SaveAppEntry)
                        }else{
                            pagerState.animateScrollToPage(page = pagerState.currentPage+1) }
                        }

                })
            }
        }
        Spacer(modifier = Modifier.weight(.5f))
    }
}
