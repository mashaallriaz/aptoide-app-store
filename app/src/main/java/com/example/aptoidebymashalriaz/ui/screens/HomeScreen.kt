package com.example.aptoidebymashalriaz.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier) {
    val state by viewModel.uiState.collectAsState()
    Text(text = state.text, modifier = modifier)
}