package com.foodviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodviewer.domain.model.FoodItem
import com.foodviewer.domain.model.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val defaultSearch = "chicken"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: FoodRepository
) : ViewModel() {
    private val _results = MutableStateFlow<List<FoodItem>>(emptyList())
    val results: Flow<List<FoodItem>> get() = _results

    private val _searchText = MutableStateFlow(defaultSearch)
    val searchText = _searchText.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: Flow<Boolean> get() = _loading

    init {
        searchFood()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _results.value = emptyList()
        if (text.length > 2) {
            searchFood()
        }
    }

    private fun searchFood() {
        _loading.value = true
        viewModelScope.launch {
            try {
                repo.getFood(_searchText.value).collect {
                    _results.value = it
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _results.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}