package com.foodviewer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.foodviewer.R
import com.foodviewer.domain.model.FoodItem

@Composable
fun HomeComposable(viewModel: HomeViewModel, onFoodClick: (String) -> Unit) {

    val results by viewModel.results.collectAsState(listOf())
    val loading by viewModel.loading.collectAsState(false)

    Column(modifier = Modifier.fillMaxSize()) {
        SearchView(viewModel)
        if (loading) {
            Loading()
        } else if (results.isEmpty()) {
            EmptyResults()
        } else {
            SearchResults(results, onFoodClick)
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun EmptyResults() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(id = R.string.no_results),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun SearchResults(results: List<FoodItem>, onFoodClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(results.size) { index ->
            val name = results[index].name
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onFoodClick.invoke(name) }
                    .padding(all = 16.dp)
            ) {
                Text(
                    name, modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(viewModel: HomeViewModel) {
    val searchText by viewModel.searchText.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 8.dp)
    ) {
        SearchBar(
            query = searchText,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = viewModel::onSearchTextChange,
            active = false,
            onActiveChange = { },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.cd_clear),
                        modifier = Modifier.clickable { viewModel.onSearchTextChange("") }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {}
    }
}
