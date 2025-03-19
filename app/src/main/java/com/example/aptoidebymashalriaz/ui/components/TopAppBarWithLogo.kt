package com.example.aptoidebymashalriaz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.aptoidebymashalriaz.R
import com.example.aptoidebymashalriaz.ui.theme.AptoideSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(TopAppBarDefaults.TopAppBarExpandedHeight)
    ) {
        Image(
            painter = painterResource(R.drawable.bg_aptoide_gradient),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        CenterAlignedTopAppBar(
            title = {
                Image(
                    painter = painterResource(R.drawable.ic_aptoide_logo_colored),
                    contentDescription = null,
                    modifier = Modifier.padding(bottom = AptoideSpacing.spacing12)
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent
            )
        )
    }
}