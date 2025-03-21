package com.example.aptoidebymashalriaz.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidebymashalriaz.domain.interactors.GetAppById
import com.example.aptoidebymashalriaz.domain.models.Result
import com.example.aptoidebymashalriaz.ui.navigation.RouteArgs.APP_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAppById: GetAppById
) : ViewModel() {
    private val _state =
        MutableStateFlow(AppDetailViewState(appId = savedStateHandle.get<Long>(APP_ID)))
    val uiState = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value.appId?.let { id ->
                getAppById.invoke(id).collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.update { it.copy(app = result.data) }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}