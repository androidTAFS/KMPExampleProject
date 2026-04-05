package io.androidtafs.exampleproject.ui.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.SaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.androidtafs.exampleproject.ui.component.AppNavRail
import io.androidtafs.exampleproject.ui.navigation.Route
import io.androidtafs.exampleproject.ui.navigation.rememberEntryProvider
import io.androidtafs.exampleproject.ui.theme.AndroidSchoolTheme

@Composable
fun App(viewModel: MainViewModel = viewModel { MainViewModel() }) {
    AndroidSchoolTheme {
        val backStack by viewModel.backStack.collectAsState()
        val currentRoute = backStack.lastOrNull()

        val saveableStateHolder = rememberSaveableStateHolder()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                val showNavRail = currentRoute is Route.Lessons || currentRoute is Route.Practice

                if (showNavRail) {
                    AppNavRail(
                        currentRoute = currentRoute,
                        onNavigate = { viewModel.navigateTo(it) }
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    if (backStack.isNotEmpty()) {
                        NavDisplay(
                            backStack = backStack,
                            onBack = { viewModel.goBack() },
                            entryProvider = rememberEntryProvider(viewModel),
                            entryDecorators = listOf(
                                remember(saveableStateHolder) {
                                    SaveableStateHolderNavEntryDecorator(saveableStateHolder)
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}