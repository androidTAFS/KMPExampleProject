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
import exampleproject.composeapp.generated.resources.Res
import exampleproject.composeapp.generated.resources.lucy_promo
import exampleproject.composeapp.generated.resources.me
import io.androidtafs.exampleproject.ui.component.AppNavRail
import io.androidtafs.exampleproject.ui.feature.practice_holder.task_4.MeteoriteDefensePreview
import io.androidtafs.exampleproject.ui.feature.practice_holder.task_4.MeteoriteDefenseScreen
import io.androidtafs.exampleproject.ui.feature.practice_holder.task_4.SolarHarvesterScreen
import io.androidtafs.exampleproject.ui.feature.practice_holder.task_5.FlappyBirdScreen
import io.androidtafs.exampleproject.ui.feature.practice_holder.task_5.FlappyState
import io.androidtafs.exampleproject.ui.navigation.Route
import io.androidtafs.exampleproject.ui.navigation.rememberEntryProvider
import io.androidtafs.exampleproject.ui.theme.AndroidSchoolTheme
import org.jetbrains.compose.resources.painterResource

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
            FlappyBirdScreen(painterResource( Res.drawable.me), painterResource(Res.drawable.lucy_promo))
        }

    }
}