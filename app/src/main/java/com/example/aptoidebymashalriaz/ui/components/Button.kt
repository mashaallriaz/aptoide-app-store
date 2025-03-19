package com.example.aptoidebymashalriaz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aptoidebymashalriaz.ui.theme.AptoideColor
import com.example.aptoidebymashalriaz.ui.theme.HeadlineMediumText
import com.example.aptoidebymashalriaz.ui.theme.HeadlineSmallText

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AptoideColor.AptoidePrimary,
            contentColor = AptoideColor.White
        )
    ) {
        HeadlineMediumText(text = text)
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