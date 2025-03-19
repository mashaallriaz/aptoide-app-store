package com.example.aptoidebymashalriaz.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.aptoidebymashalriaz.R
import com.example.aptoidebymashalriaz.domain.models.App
import com.example.aptoidebymashalriaz.ui.components.OutlinedSmallButton
import com.example.aptoidebymashalriaz.ui.components.TopAppBarWithLogo
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor
import com.example.aptoidebymashalriaz.ui.theme.AptoideSpacing
import com.example.aptoidebymashalriaz.ui.theme.BodyMediumText
import com.example.aptoidebymashalriaz.ui.theme.BodySmallText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineLargeText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineMediumText

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier) {
    val state by viewModel.uiState.collectAsState()
    HomeScreenImpl(state, onAppClick = {})
}

@Composable
private fun HomeScreenImpl(
    state: HomeViewState,
    onAppClick: (app: App) -> Unit
) {
    val allApps = state.apps
    val bannerApps = allApps.filter { it.graphic.isNullOrEmpty().not() }.take(5)
    val localTopCarouselApps = allApps.take(5)

    LazyColumn {
        item { TopAppBarWithLogo() }

        item {
            if (state.loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = AptoideColor.AptoidePrimary
                )
            }
        }

        item {
            HeadlineLargeText(
                modifier = Modifier.padding(AptoideSpacing.spacing16),
                text = stringResource(R.string.home_header_label)
            )
        }

        item { HomeBannerCarousel(bannerApps = bannerApps, onAppClick = onAppClick) }

        item {
            HeadlineMediumText(
                text = stringResource(R.string.top_local_app_carousel_header_label),
                modifier = Modifier.padding(horizontal = AptoideSpacing.spacing16)
            )
        }
        item { LocalTopAppsCarousel(localTopCarouselApps, onAppClick = onAppClick) }

        item { AppListHeader() }

        items(allApps) { app ->
            AppListItem(app = app, onAppClick = onAppClick)
        }

        item { Spacer(modifier = Modifier.padding(vertical = AptoideSpacing.spacing16)) }
    }
}

@Composable
private fun HomeBannerCarousel(bannerApps: List<App>, onAppClick: (app: App) -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { bannerApps.size })
    Column {
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
                    .clickable { onAppClick(item) }
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
                        .padding(AptoideSpacing.spacing16)
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

        CustomPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun CustomPagerIndicator(
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

@Composable
private fun LocalTopAppsCarousel(apps: List<App>, onAppClick: (app: App) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AptoideSpacing.spacing12),
        contentPadding = PaddingValues(horizontal = AptoideSpacing.spacing16),
        horizontalArrangement = Arrangement.spacedBy(AptoideSpacing.spacing8),
    ) {
        items(apps) { app ->
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(AptoideColor.LightGrey)
                    .clickable { onAppClick(app) }
            ) {
                Column(modifier = Modifier.padding(AptoideSpacing.spacing8)) {
                    AsyncImage(
                        model = app.icon,
                        contentDescription = app.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    BodyMediumText(text = app.name.orEmpty(), maxLines = 2)

                    Spacer(modifier = Modifier.height(4.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = AptoideColor.BackgroundGrey,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        BodySmallText(text = app.rating?.toString() ?: "--")
                    }
                }
            }
        }
    }
}

@Composable
private fun AppListHeader() {
    Row(
        modifier = Modifier.padding(
            horizontal = AptoideSpacing.spacing16,
            vertical = AptoideSpacing.spacing8
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_app_list),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = AptoideColor.MainGrey
        )
        Spacer(modifier = Modifier.width(AptoideSpacing.spacing8))
        HeadlineMediumText(text = stringResource(R.string.app_list_header_label))
    }
}

@Composable
private fun AppListItem(
    app: App,
    onAppClick: (app: App) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AptoideSpacing.spacing8, horizontal = AptoideSpacing.spacing16),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AptoideSpacing.spacing12)
    ) {
        AsyncImage(
            model = app.icon,
            contentDescription = "${app.name} icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(AptoideColor.LightGrey)
        )

        Column(modifier = Modifier.weight(1f)) {
            BodyMediumText(text = app.name.orEmpty(), maxLines = 1)
            Spacer(modifier = Modifier.width(AptoideSpacing.spacing2))
            BodySmallText(
                text = app.storeName.orEmpty(),
                color = AptoideColor.SecondaryTextGrey
            )

            app.rating?.takeIf { it > 0 }?.let { rating ->
                Spacer(modifier = Modifier.height(AptoideSpacing.spacing2))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = AptoideColor.BackgroundGrey,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(AptoideSpacing.spacing4))
                    BodySmallText(text = rating.toString())
                }
            }
        }

        OutlinedSmallButton(
            text = stringResource(R.string.download_button_label),
            onClick = { onAppClick(app) }
        )
    }
}