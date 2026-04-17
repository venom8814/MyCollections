# My Collections — Android App

## Описание
Приложение для ведения личных коллекций. Хранит элементы в локальной базе данных SQLite.

## Стек
- **Язык:** Java  
- **IDE:** Android Studio  
- **База данных:** SQLite (SQLiteOpenHelper)  
- **UI:** RecyclerView, LinearLayout  

## Структура проекта
```
app/src/main/
├── java/com/mycollections/app/
│   ├── CollectionItem.java   — модель данных
│   ├── DatabaseHelper.java   — работа с SQLite
│   ├── ItemAdapter.java      — адаптер RecyclerView
│   ├── MainActivity.java     — главный экран (список)
│   └── AddItemActivity.java  — экран добавления элемента
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_add_item.xml
    │   └── item_row.xml
    └── values/
        ├── strings.xml  (Russian)
        ├── colors.xml
        └── themes.xml
```

## Запуск
1. Открыть папку `MyCollections` в **Android Studio**.  
2. Дождаться синхронизации Gradle.  
3. Запустить на эмуляторе или устройстве (minSdk 21).

## Функционал
| Действие | Описание |
|---|---|
| Просмотр | RecyclerView на главном экране |
| Добавление | Кнопка «Добавить» → форма с полями Название / Описание |
| Удаление | Кнопка «Удалить» рядом с каждым элементом |

## Цветовая схема
- Фон: `#000000`  
- Текст: `#FFFFFF`  
- Второстепенный текст: `#CCCCCC`  
- Разделители / вторичные кнопки: `#2A2A2A`  
