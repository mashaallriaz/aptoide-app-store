package com.example.aptoidebymashalriaz.ui.theme

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
internal fun BaseText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Unspecified,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE
) {
    BasicText(
        text = text,
        style = style.copy(
            textAlign = textAlign,
            color = color.takeOrElse { style.color }
        ),
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines
    )
}

@Composable
fun HeadlineLargeText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseText(
        text = text,
        style = Typography.headlineLarge,
        modifier = modifier,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun HeadlineMediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    BaseText(
        text = text,
        style = Typography.headlineMedium,
        modifier = modifier,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun HeadlineSmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
) {
    BaseText(
        text = text,
        style = Typography.headlineSmall,
        modifier = modifier,
        color = color
    )
}

@Composable
fun BodyMediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
) {
    BaseText(
        text = text,
        style = Typography.bodyMedium,
        modifier = modifier,
        color = color,
        maxLines = maxLines
    )
}

@Composable
fun BodySmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
) {
    BaseText(
        text = text,
        style = Typography.bodySmall,
        modifier = modifier,
        color = color
    )
}