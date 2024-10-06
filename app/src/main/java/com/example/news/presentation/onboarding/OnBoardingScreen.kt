package com.example.news.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news.presentation.common.NewsButton
import com.example.news.presentation.common.PreviousNewsButton
import com.example.news.presentation.onboarding.Dimens.pageIndicatorWidth
import com.example.news.presentation.onboarding.components.OnBoardingPage
import com.example.news.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    onEvent: (OnBoardingEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "NEXT")
                    1 -> listOf("PREVIOUS", "NEXT")
                    2 -> listOf("PREVIOUS", "GET STARTED")
                    else -> {
                        listOf("", "")
                    }
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val scope = rememberCoroutineScope()
            PageIndicator(
                modifier = Modifier.size(pageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                PreviousNewsButton(text = buttonState.value[0], onClick = {
                    scope.launch { pagerState.animateScrollToPage(page = pagerState.currentPage - 1) }
                })
                NewsButton(text = buttonState.value[1], onClick = {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            onEvent(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }

                })
            }
        }
        Spacer(modifier = Modifier.weight(.5f))
    }
}
