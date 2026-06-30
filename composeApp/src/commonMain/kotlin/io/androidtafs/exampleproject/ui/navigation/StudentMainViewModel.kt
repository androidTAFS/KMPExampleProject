package io.androidtafs.exampleproject.ui.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudentMainViewModel : ViewModel() {
    private val _backStack = MutableStateFlow<List<StudentRoute>>(listOf(StudentRoute.Home))
    val backStack = _backStack.asStateFlow()

    fun navigateTo(route: StudentRoute) {
        val current = _backStack.value.toMutableList()
        if (route is StudentRoute.Home) {
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
