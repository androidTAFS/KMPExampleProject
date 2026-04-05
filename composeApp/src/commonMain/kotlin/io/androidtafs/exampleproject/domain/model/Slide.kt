package io.androidtafs.exampleproject.domain.model

data class Slide(
    val title: String,
    val description: String,
    val imageRes: String? = null,
    val codeExample: String? = null
)