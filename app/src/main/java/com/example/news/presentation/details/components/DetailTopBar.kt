package com.example.news.presentation.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.R
import com.example.styleage.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_arrow_back_24),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onBookMarkClick) {
                Icon(
                    painter = painterResource(R.drawable.rounded_bookmark_24),
                    contentDescription = null
                )
            }

            IconButton(onShareClick) {
                Icon(
                    painter = painterResource(R.drawable.rounded_share_24),
                    contentDescription = null
                )
            }

            IconButton(onBrowsingClick) {
                Icon(
                    painter = painterResource(R.drawable.rounded_globe_asia_24),
                    contentDescription = null
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailScreenPreview() {
    NewsAppTheme {
        Box(
            Modifier.background(color = MaterialTheme.colorScheme.background)
        ) {
            DetailsTopBar(
                onBrowsingClick = {},
                onShareClick = {},
                onBookMarkClick = {},
                onBackClick = {},
            )
        }
    }
}

