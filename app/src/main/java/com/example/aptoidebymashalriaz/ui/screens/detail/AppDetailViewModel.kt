package com.example.aptoidebymashalriaz.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidebymashalriaz.domain.interactors.GetAppById
import com.example.aptoidebymashalriaz.domain.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(private val getAppById: GetAppById) : ViewModel() {
    private val _state = MutableStateFlow(AppDetailViewState())
    val uiState = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAppById.invoke(_state.value.appId).collect { result ->
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