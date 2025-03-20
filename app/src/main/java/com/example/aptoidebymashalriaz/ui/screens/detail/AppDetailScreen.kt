package com.example.aptoidebymashalriaz.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.aptoidebymashalriaz.R
import com.example.aptoidebymashalriaz.domain.models.App
import com.example.aptoidebymashalriaz.ui.components.TopBarWithLogoAndNavigationIcon
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor
import com.example.aptoidebymashalriaz.ui.theme.AptoideSpacing
import com.example.aptoidebymashalriaz.ui.theme.BodyMediumText
import com.example.aptoidebymashalriaz.ui.theme.BodySmallText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineLargeText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineMediumText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineSmallText

@Composable
fun AppDetailScreen(viewModel: AppDetailViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    AppDetailScreenImpl(uiState = state)
}

@Composable
private fun AppDetailScreenImpl(uiState: AppDetailViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.bg_aptoide_gradient),
                contentScale = ContentScale.Crop
            )
    ) {
        TopBarWithLogoAndNavigationIcon(onClick = {})
        AppDetailsHeader(app = uiState.app)
        AppDetailsBody(app = uiState.app, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun AppDetailsHeader(app: App?) {
    Column(
        modifier = Modifier
            .padding(AptoideSpacing.spacing16)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing24))
        AsyncImage(
            model = app?.icon,
            contentDescription = "${app?.name} icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White.copy(alpha = 0.3f))
                .padding(AptoideSpacing.spacing4)
        )

        Spacer(modifier = Modifier.height(AptoideSpacing.spacing12))
        HeadlineLargeText(text = app?.name.orEmpty(), color = AptoideColor.White)
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing4))
        HeadlineMediumText(text = app?.storeName.orEmpty(), color = AptoideColor.White)
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing12))
    }
}

@Composable
fun AppDetailsBody(app: App?, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(AptoideColor.White)
            .padding(AptoideSpacing.spacing16),
    ) {
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing16))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppStatsRow(iconRes = R.drawable.ic_download, text = app?.downloads.toString())
            Spacer(modifier = Modifier.width(AptoideSpacing.spacing24))
            AppStatsRow(iconRes = R.drawable.ic_app_size, text = app?.size.toString())
            Spacer(modifier = Modifier.width(AptoideSpacing.spacing24))
            AppStatsRow(iconRes = R.drawable.ic_star, text = app?.rating.toString())
        }
        LatestVersionSection(version = app?.verCode.toString())
        DescriptionSection()
    }
}

@Composable
private fun AppStatsRow(iconRes: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = AptoideColor.SecondaryTextGrey,
            modifier = Modifier.size(10.dp)
        )
        Spacer(modifier = Modifier.width(AptoideSpacing.spacing4))
        BodySmallText(text = text, color = AptoideColor.SecondaryTextGrey)
    }
}

@Composable
private fun LatestVersionSection(version: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AptoideSpacing.spacing16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(color = AptoideColor.AccentGrey, thickness = 1.dp)
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing12))
        BodyMediumText(
            text = stringResource(R.string.app_detail_latest_version_label),
            color = AptoideColor.SecondaryTextGrey
        )
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing4))
        HeadlineSmallText(text = version, color = AptoideColor.SecondaryTextGrey)
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing12))
        HorizontalDivider(color = AptoideColor.AccentGrey, thickness = 1.dp)
    }
}

@Composable
private fun DescriptionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AptoideSpacing.spacing16),
    ) {
        HeadlineMediumText(text = stringResource(R.string.app_detail_description_heading))
        Spacer(modifier = Modifier.height(AptoideSpacing.spacing8))
        BodyMediumText(text = stringResource(R.string.app_detail_description_body))
    }
}