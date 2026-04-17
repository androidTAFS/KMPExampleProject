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
        ),
        Lesson(
            id = 2,
            title = "LazyColumn и LazyRow: Списки",
            practiceId = 2,
            slides = listOf(
                Slide(
                    title = "Проблема обычных списков",
                    description = "Представь, что у тебя в приложении 1000 картинок. Если использовать обычный Column, телефон попытается отрисовать все 1000 сразу, даже те, которые не видны. Телефон перегреется и приложение «зависнет».",
                ),
                Slide(
                    title = "Что значит «Lazy»?",
                    description = "Lazy (Ленивый) в программировании — это суперсила! Ленивые списки создают только те элементы, которые ты видишь на экране прямо сейчас. Как только ты прокручиваешь список, старые элементы исчезают, а новые появляются.",
                ),
                Slide(
                    title = "LazyColumn — Вертикальный список",
                    description = "Используется для длинных списков, которые листаются вверх и вниз. Вместо того чтобы перечислять элементы вручную, мы используем функцию items().",
                    codeExample = "LazyColumn {\n    items(listOf(\"Кот\", \"Собака\", \"Хомяк\")) { animal ->\n        Text(\"Это: \$animal\")\n    }\n}"
                ),
                Slide(
                    title = "LazyRow — Горизонтальный список",
                    description = "Работает точно так же, как LazyColumn, но листается влево и вправо. Идеально подходит для выбора картинок или категорий.",
                    codeExample = "LazyRow {\n    items(10) { index ->\n        Box(Modifier.size(50.dp).background(Color.Red))\n    }\n}"
                ),
                Slide(
                    title = "Отступы и промежутки",
                    description = "В ленивых списках нельзя просто добавить Modifier.padding всем элементам. Мы используем Arrangement.spacedBy для создания красивых дырок между карточками и contentPadding для отступов по краям.",
                    codeExample = "LazyColumn(\n    verticalArrangement = Arrangement.spacedBy(10.dp),\n    contentPadding = PaddingValues(16.dp)\n) {\n    // элементы\n}"
                ),
                Slide(
                    title = "Сетка: LazyVerticalGrid",
                    description = "Если нужно сделать список в несколько колонок (как в галерее фото), мы используем Grid. Он автоматически расставляет элементы по сетке.",
                    codeExample = "LazyVerticalGrid(\n    columns = GridCells.Fixed(2)\n) {\n    // элементы в 2 колонки\n}"
                ),
                Slide(
                    title = "Интерактивность",
                    description = "Каждый элемент списка может быть кнопкой! Мы можем добавить Modifier.clickable к любому элементу внутри нашего 'ленивого' цикла.",
                ),
                Slide(
                    title = "Практическое задание: Динамический лист",
                    description = ""
                )
            )
        ),
        Lesson(
            id = 3,
            title = "Состояние и логика: Мемогенератор",
            practiceId = 3,
            slides = listOf(
                Slide(
                    title = "Интерактивность: Оживляем экран",
                    description = "Раньше наши экраны были как картины в музее — на них можно смотреть, но нельзя ничего изменить. Сегодня мы сделаем так, чтобы приложение реагировало на нажатия и менялось прямо на глазах!",
                ),
                Slide(
                    title = "Что такое State (Состояние)?",
                    description = "State — это «память» твоего экрана. Приложение должно помнить, какую картинку ты выбрал или какой текст ввел. Если у экрана нет состояния, он все мгновенно забывает.",
                    codeExample = "// Так мы просим Compose запомнить значение\nval count = remember { mutableStateOf(0) }"
                ),
                Slide(
                    title = "Рекомпозиция: Магия обновления",
                    description = "В обычных программах нужно вручную перерисовывать экран. В Compose всё иначе: как только State (память) меняется, Compose сам замечает это и мгновенно перерисовывает нужную часть экрана. Это называется Рекомпозиция.",
                ),
                Slide(
                    title = "ViewModel: Мозг приложения",
                    description = "Представь, что экран — это тело, а ViewModel — это мозг. Экран только показывает кнопки и картинки, а ViewModel решает, ЧТО именно показывать и ХРАНИТ все данные. Если телефон повернуть, экран «переродится», но «мозг» (ViewModel) останется прежним и всё вспомнит.",
                ),
                Slide(
                    title = "State Hoisting (Поднятие состояния)",
                    description = "Это правило «Главного». Мы не храним данные внутри маленьких кнопок. Мы поднимаем их наверх — в Route или ViewModel. \n\nЭто как пульт от телевизора: пульт (ViewModel) знает, какой канал включен, а телевизор (Screen) просто его показывает.",
                ),
                Slide(
                    title = "События и Действия (Actions)",
                    description = "Общение в Compose идет по кругу:\n1. Пользователь нажимает на кнопку (Action).\n2. ViewModel меняет данные (State).\n3. Экран видит изменения и перерисовывается (Recomposition).",
                    codeExample = "// Кнопка просто сообщает о клике вверх\nButton(onClick = { onAction(MemeAction.Randomize) }) {\n    Text(\"🎲\")\n}"
                ),
                Slide(
                    title = "Почему UI должен быть «глупым»?",
                    description = "Хороший экран не должен знать, откуда берутся данные. Он просто говорит: «Эй, Мозг, мне дали текст, я его рисую». Это делает твой код чистым, как в профессиональных студиях разработки!",
                ),
                Slide(
                    title = "Практическое задание: Мемогенератор",
                    description = "Настало время собрать всё вместе! \n\n1. Создай ViewModel для хранения списков фраз и картинок.\n2. Реализуй State Hoisting: передавай данные из ViewModel в Screen.\n3. Сделай так, чтобы при клике на элемент списка, мем наверху мгновенно обновлялся!",
                )
            )
        )

    )
}