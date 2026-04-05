package io.androidtafs.exampleproject.domain.model


data class Lesson(
    val id: Int,
    val title: String,
    val slides: List<Slide>,
    val practiceId: Int
)