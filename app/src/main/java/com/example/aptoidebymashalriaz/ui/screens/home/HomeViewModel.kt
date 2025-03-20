package com.example.aptoidebymashalriaz.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoidebymashalriaz.domain.models.Result
import com.example.aptoidebymashalriaz.domain.interactors.GetAllApps
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
            getAllApps.invoke(Unit).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _state.update {
                            it.copy(loading = true, apps = result.data ?: emptyList())
                        }
                    }

                    is Result.Success -> {
                        _state.update {
                            it.copy(loading = false, apps = result.data)
                        }
                    }

                    is Result.Error -> {
                        _state.update {
                            it.copy(loading = false)
                        }
                    }
                }
            }
        }
    }
}