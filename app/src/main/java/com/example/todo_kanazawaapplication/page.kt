package com.example.todo_kanazawaapplication

import java.time.LocalDate

data class Page(
    val id: Int,
    val title: String,
    val content: String,
    val deadline: LocalDate
)


// 未完了TODO初期データ
val pagesList: ArrayList<Page> = arrayListOf(
    Page(1, "TODO1", "あいうえおかきくけこさしすせそたちつてとなにぬねの", LocalDate.of(2024, 10, 15)),
    Page(2, "TODO2", "はひふえほまみむめもやゆよらりるれろわをん", LocalDate.of(2024, 10, 30)),
    Page(3, "ToDo3", "よ", LocalDate.of(2024, 11, 15))
)

// 完了済みTODO初期データ
val endPagesList: ArrayList<Page> = arrayListOf(
    Page(1, "END TODO1", "あ", LocalDate.of(2024, 10, 15)),
    Page(2, "END-TODO2", "は", LocalDate.of(2024, 10, 30)),
    Page(3, "ENDTODO3", "よよよ", LocalDate.of(2024, 11, 15))
)
