package io.androidtafs.exampleproject.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import io.androidtafs.exampleproject.ui.feature.lesson.LessonsListRoute
import io.androidtafs.exampleproject.ui.feature.lesson_details.LessonDetailsRoute
import io.androidtafs.exampleproject.ui.feature.main.MainViewModel
import io.androidtafs.exampleproject.ui.feature.practice.PracticeRoute
import io.androidtafs.exampleproject.ui.feature.practice_holder.PracticeDetailsRoute

@Composable
fun rememberEntryProvider(mainViewModel: MainViewModel): (NavKey) -> NavEntry<NavKey> =
    remember(mainViewModel) {
        { key ->
            NavEntry(key) {
                when (key) {
                    is Route.Lessons -> {
                        LessonsListRoute(
                            onNavigateToDetail = { lessonId ->
                                mainViewModel.navigateTo(Route.LessonDetails(lessonId))
                            }
                        )
                    }

                    is Route.LessonDetails -> {
                        LessonDetailsRoute(
                            lessonId = key.id,
                            onNavigateToPractice = { practiceId ->
                                mainViewModel.navigateTo(Route.PracticeDetails(practiceId))
                            },
                            onBack = {
                                mainViewModel.goBack()
                            }
                        )
                    }

                    is Route.Practice -> {
                        PracticeRoute(
                            onNavigateToTask = { id ->
                                mainViewModel.navigateTo(Route.PracticeDetails(id))
                            }
                        )
                    }

                    is Route.PracticeDetails -> {
                        PracticeDetailsRoute(
                            taskId = key.id,
                            onBack = {
                                mainViewModel.goBack()
                            }
                        )
                    }

                    else -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Экран не найден")
                        }
                    }
                }
            }
        }
    }