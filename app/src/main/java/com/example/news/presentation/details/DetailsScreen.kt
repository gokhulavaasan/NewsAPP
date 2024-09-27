package com.example.news.presentation.details

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.news.R
import com.example.news.domain.model.Article
import com.example.news.domain.model.Source
import com.example.news.presentation.details.components.DetailsTopBar
import com.example.news.presentation.onboarding.Dimens.ArticleImageHeight
import com.example.news.presentation.onboarding.Dimens.MediumPadding1
import com.example.styleage.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent)-> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data= Uri.parse(article.url)
                    if(it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT,article.url)
                    it.type= "text/plain"
                    if(it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = { event(DetailsEvent.SaveArticleEvent)},
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                top =  MediumPadding1,
                end = MediumPadding1
            )
        ) {
            item{
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = colorResource(R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    NewsAppTheme(dynamicColor = false) {
        DetailsScreen(
            article = Article(
                author = "",
                title = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                description = "Coinbase says Apple blocked its last app release on NFTs in Wallet ... - CryptoSaurus",
                content = "We use cookies and data to Deliver and maintain Google services Track outages and protect against spam, fraud, and abuse Measure audience engagement and site statistics to unde… [+1131 chars]",
                publishedAt = "2023-06-16T22:24:33Z",
                source = Source(
                    id = "", name = "bbc"
                ),
                url = "https://edition.cnn.com/2024/09/25/china/china-hongkong-panda-anniversary-intl-hnk/index.html",
                urlToImage = "https://media.wired.com/photos/6495d5e893ba5cd8bbdc95af/191:100/w_1280,c_limit/The-EU-Rules-Phone-Batteries-Must-Be-Replaceable-Gear-2BE6PRN.jpg"
            ),
            event = {}

        ) { }
    }
}