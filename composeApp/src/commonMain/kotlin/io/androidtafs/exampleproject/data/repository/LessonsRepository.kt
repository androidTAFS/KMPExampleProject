package io.androidtafs.exampleproject.data.repository

import io.androidtafs.exampleproject.domain.model.Lesson
import io.androidtafs.exampleproject.domain.model.Slide

object LessonsRepository {
    val lessons = listOf(
        Lesson(
            id = 1,
            title = "Основы Android и Compose",
            practiceId = 1,
            slides = listOf(
                Slide(
                    title = "Что такое Android?",
                    description = "Android — это не просто иконка робота. Это целая операционная система на базе Linux. Она состоит из слоев: Ядро (двигатель), Среда выполнения (где живет код) и Фреймворки (инструменты для нас).",
                ),
                Slide(
                    title = "Структура APK",
                    description = "APK (Android Package) — это обычный архив. Внутри него:\n• classes.dex: Скомпилированный код на Kotlin/Java.\n• resources.arsc: Тексты и настройки.\n• res/: Картинки и иконки.\n• AndroidManifest.xml: 'Паспорт' приложения, где указано его имя и права доступа.",
                ),
                Slide(
                    title = "Жизненный цикл приложения",
                    description = "Приложение не просто 'включено'. Оно проходит этапы:\n1. onCreate: Рождение (создание).\n2. onStart: Видимость на экране.\n3. onResume: Активная работа (можно нажимать кнопки).\n4. onPause/onStop: Сон (когда свернули).\n5. onDestroy: Смерть (когда закрыли).",
                ),
                Slide(
                    title = "Декларативный UI",
                    description = "В Jetpack Compose мы не говорим компьютеру 'как' рисовать (подвинь пиксель влево). Мы говорим 'что' мы хотим видеть. Если данные изменились — интерфейс перерисуется сам.",
                    codeExample = "// Просто описываем состояние\n@Composable\nfun Greeting(name: String) {\n    Text(text = \"Привет, \$name!\")\n}"
                ),
                Slide(
                    title = "Компоненты: Text и Icon",
                    description = "Text — выводит буквы на экран. Можно менять шрифт и цвет.\nIcon — выводит векторные картинки. В Android есть сотни готовых иконок.",
                    codeExample = "Text(\n    text = \"Android разработка\", \n    color = Color.Blue\n)\nIcon(\n    imageVector = Icons.Default.Android, \n    contentDescription = null\n)"
                ),
                Slide(
                    title = "Компоненты: Column и Row",
                    description = "Column (Колонка) — ставит элементы друг под друга вертикально.\nRow (Строка) — ставит элементы в ряд горизонтально.",
                    codeExample = "Column {\n    Text(\"Верхний элемент\")\n    Text(\"Нижний элемент\")\n}\n\nRow {\n    Icon(Icons.Default.Phone, null)\n    Text(\"8-800-...\")\n}"
                ),
                Slide(
                    title = "Компонент: Box",
                    description = "Box — это контейнер-коробка. Он позволяет накладывать элементы друг на друга (как слои в Photoshop). Последний добавленный элемент будет сверху.",
                    codeExample = "Box {\n    Image(...) // Картинка на фоне\n    Text(\"Текст поверх картинки\")\n}"
                ),
                Slide(
                    title = "Модификаторы (Modifiers)",
                    description = "Это 'приправы' для компонентов. С их помощью мы меняем размеры, отступы, фон и кликабельность.",
                    codeExample = "Text(\n    text = \"Нажми меня\",\n    modifier = Modifier\n        .padding(16.dp)\n        .background(Color.Yellow)\n        .fillMaxWidth()\n)"
                ),
                Slide(
                    title = "Практическое задание",
                    description = "Сегодня мы создадим твою первую цифровую визитку! На ней должна быть твоя иконка, имя и контактная информация.\n\nИспользуй Column для общей разметки и Row для строки с контактами.",
                )
            )
        )
    )
}