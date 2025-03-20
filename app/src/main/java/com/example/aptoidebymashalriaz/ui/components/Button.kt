package com.example.aptoidebymashalriaz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aptoidebymashalriaz.R
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor
import com.example.aptoidebymashalriaz.ui.theme.HeadlineMediumText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineSmallText

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(R.drawable.bg_aptoide_gradient),
                contentScale = ContentScale.Crop
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = AptoideColor.Transparent,
            contentColor = AptoideColor.White
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        HeadlineMediumText(text = text, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun OutlinedSmallButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp)
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .widthIn(min = 0.dp)
            .wrapContentWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, AptoideColor.AptoidePrimary),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = AptoideColor.AptoidePrimary),
        contentPadding = contentPadding
    ) {
        HeadlineSmallText(text = text, color = AptoideColor.AptoidePrimary)
    }
}