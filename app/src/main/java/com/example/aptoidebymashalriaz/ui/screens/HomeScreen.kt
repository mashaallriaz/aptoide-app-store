package com.example.aptoidebymashalriaz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aptoidebymashalriaz.R
import com.example.aptoidebymashalriaz.domain.models.App
import com.example.aptoidebymashalriaz.ui.components.TopAppBarWithLogo
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor
import com.example.aptoidebymashalriaz.ui.theme.AptoideSpacing
import com.example.aptoidebymashalriaz.ui.theme.BodyMediumText
import com.example.aptoidebymashalriaz.ui.theme.BodySmallText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineLargeText

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier) {
    val state by viewModel.uiState.collectAsState()
    HomeScreenImpl(state)
}

@Composable
private fun HomeScreenImpl(state: HomeViewState) {
    val bannerApps = state.apps.filter { it.graphic.isNullOrEmpty().not() }.take(5)
    val apps = state.apps.filter { it.graphic.isNullOrEmpty().not() }.drop(5)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { bannerApps.size })

    Column {
        TopAppBarWithLogo()

        if (state.loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = AptoideColor.AptoidePrimary
            )
        }

        HeadlineLargeText(
            modifier = Modifier.padding(AptoideSpacing.spacing16),
            text = stringResource(R.string.home_header_label)
        )

        HomeBannerCarousel(bannerApps = bannerApps, pagerState = pagerState)
        CustomPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun HomeBannerCarousel(bannerApps: List<App>, pagerState: PagerState) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
    ) { page ->
        val item = bannerApps[page]
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AptoideSpacing.spacing16)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.graphic)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_placeholder)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(AptoideColor.LightGrey),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                BodyMediumText(
                    text = item.name ?: "",
                    color = AptoideColor.White
                )

                item.rating?.takeIf { it > 0 }?.let { rating ->
                    Spacer(modifier = Modifier.padding(AptoideSpacing.spacing2))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(AptoideSpacing.spacing4))
                        BodySmallText(text = rating.toString(), color = AptoideColor.White)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(AptoideSpacing.spacing12),
        horizontalArrangement = Arrangement.spacedBy(AptoideSpacing.spacing8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { page ->
            val color = if (page == pagerState.currentPage) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}