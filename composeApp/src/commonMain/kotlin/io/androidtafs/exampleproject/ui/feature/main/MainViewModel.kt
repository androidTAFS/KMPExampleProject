package io.androidtafs.exampleproject.ui.feature.main

import androidx.lifecycle.ViewModel
import io.androidtafs.exampleproject.ui.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _backStack = MutableStateFlow<List<Route>>(listOf(Route.Lessons))
    val backStack = _backStack.asStateFlow()

    fun navigateTo(route: Route) {
        val current = _backStack.value.toMutableList()
        if (route is Route.Lessons || route is Route.Practice) {
            current.clear()
        }
        current.add(route)
        _backStack.value = current
    }

    fun goBack() {
        val current = _backStack.value.toMutableList()
        if (current.size > 1) {
            current.removeAt(current.lastIndex)
            _backStack.value = current
        }
    }
}