package io.androidtafs.exampleproject.ui.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.SaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.androidtafs.exampleproject.teacher.ui.theme.AndroidSchoolTheme
import io.androidtafs.exampleproject.ui.navigation.StudentMainViewModel
import io.androidtafs.exampleproject.ui.navigation.rememberStudentEntryProvider

@Composable
fun App() {
    // На Desktop нет Activity — создаём ViewModelStoreOwner вручную
    val viewModelStoreOwner = remember {
        object : ViewModelStoreOwner {
            override val viewModelStore = ViewModelStore()
        }
    }

    CompositionLocalProvider(LocalViewModelStoreOwner provides viewModelStoreOwner) {
        val viewModel: StudentMainViewModel = viewModel()
        val backStack = viewModel.backStack.collectAsStateWithLifecycle().value

        val entryProvider = rememberStudentEntryProvider(viewModel)
        val saveableStateHolder = rememberSaveableStateHolder()

        AndroidSchoolTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                if (backStack.isNotEmpty()) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = { viewModel.goBack() },
                        entryDecorators = listOf(
                            SaveableStateHolderNavEntryDecorator(saveableStateHolder)
                        ),
                        entryProvider = entryProvider
                    )
                }
            }
        }
    }
}
