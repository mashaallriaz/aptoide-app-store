package com.example.aptoidebymashalriaz.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidebymashalriaz.domain.GetAllApps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllApps: GetAllApps) : ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val uiState = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllApps.getAllApps().collect { result ->
                _state.update {
                    it.copy(
                        text = result.responses.appsList?.datasets?.all?.data?.list?.first()
                            .toString()
                    )
                }
            }
        }
    }
}