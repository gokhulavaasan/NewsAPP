package com.example.news.presentation.onboarding.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.R
import com.example.news.presentation.onboarding.Dimens.imageSpace
import com.example.news.presentation.onboarding.Dimens.titlePadding
import com.example.news.presentation.onboarding.Page
import com.example.news.presentation.onboarding.pages
import com.example.styleage.ui.theme.NewsAppTheme
import kotlinx.coroutines.FlowPreview

@Composable
fun OnBoardingPage(
    modifier: Modifier= Modifier,
    page: Page
){
    Column (modifier=Modifier) {
       Image(modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight(fraction = 0.6f),
           painter = painterResource(id=page.image),
           contentDescription = null,
           contentScale = ContentScale.Crop
           )
        Spacer(modifier=Modifier.height(imageSpace))
        Text(
            text = page.title,
            modifier=Modifier.padding(horizontal = titlePadding),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.display_small)
        )
        Text(
            text = page.description,
            modifier=Modifier.padding(horizontal = titlePadding),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_medium)
        )
    }

}
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPreview(){
    NewsAppTheme {
        OnBoardingPage(page = pages[0])
    }
}