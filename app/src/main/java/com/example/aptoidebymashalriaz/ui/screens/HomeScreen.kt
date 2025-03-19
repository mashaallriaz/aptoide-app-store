package com.example.aptoidebymashalriaz.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aptoidebymashalriaz.ui.components.TopAppBarWithLogo

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), modifier: Modifier) {
    val state by viewModel.uiState.collectAsState()
    HomeScreenImpl(state)
}

@Composable
private fun HomeScreenImpl(state: HomeViewState) {
    Column {
        TopAppBarWithLogo()
    }
}