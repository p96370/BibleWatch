package com.example.biblewatch.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblewatch.data.model.DailyVerse
import com.example.biblewatch.data.repository.DailyVerseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DailyVerseUiState {
    object Loading : DailyVerseUiState()
    data class Success(val verse: DailyVerse) : DailyVerseUiState()
    data class Error(val message: String) : DailyVerseUiState()
}

@HiltViewModel
class DailyVerseViewModel @Inject constructor(
    private val dailyVerseRepository: DailyVerseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DailyVerseUiState>(DailyVerseUiState.Loading)
    val uiState: StateFlow<DailyVerseUiState> = _uiState.asStateFlow()

    init {
        fetchDailyVerse()
    }

    fun fetchDailyVerse() {
        viewModelScope.launch {
            _uiState.value = DailyVerseUiState.Loading
            dailyVerseRepository.getDailyVerse().collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { verse -> DailyVerseUiState.Success(verse) },
                    onFailure = { error -> DailyVerseUiState.Error(error.message ?: "Unknown error occurred") }
                )
            }
        }
    }

    fun fetchRandomVerse() {
        viewModelScope.launch {
            _uiState.value = DailyVerseUiState.Loading
            dailyVerseRepository.getRandomVerse().collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { verse -> DailyVerseUiState.Success(verse) },
                    onFailure = { error -> DailyVerseUiState.Error(error.message ?: "Unknown error occurred") }
                )
            }
        }
    }

    fun retry() {
        fetchDailyVerse()
    }
}

