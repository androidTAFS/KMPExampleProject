package io.androidtafs.exampleproject.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import io.androidtafs.exampleproject.ui.feature.main.StudentNav
import io.androidtafs.exampleproject.ui.screens.StudentScreensRegistry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberStudentEntryProvider(viewModel: StudentMainViewModel): (NavKey) -> NavEntry<NavKey> =
    remember(viewModel) {
        { key ->
            NavEntry(key) {
                when (key) {
                    is StudentRoute.Home -> {
                        StudentNav(onTaskSelected = { taskId ->
                            viewModel.navigateTo(StudentRoute.Task(taskId))
                        })
                    }

                    is StudentRoute.Task -> {
                        val taskScreen = StudentScreensRegistry.getScreen(key.id)
                        Scaffold(
                            topBar = {
                                Surface(shadowElevation = 4.dp) {
                                    CenterAlignedTopAppBar(
                                        title = {
                                            Text("Задание №${key.id}")
                                        },
                                        navigationIcon = {
                                            IconButton(onClick = { viewModel.goBack() }) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                                    contentDescription = "Назад"
                                                )
                                            }
                                        },
                                        colors = TopAppBarDefaults.topAppBarColors(
                                            containerColor = MaterialTheme.colorScheme.surface
                                        )
                                    )
                                }
                            },
                            containerColor = MaterialTheme.colorScheme.background
                        ) { paddingValues ->
                            Surface(modifier = Modifier.padding(paddingValues)) {
                                taskScreen()
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }
