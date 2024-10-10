package com.example.todo_kanazawaapplication


import java.time.LocalDate

data class Page(
    val id: Int,
    val title: String,
    val content: String,
    val deadline: LocalDate
)

public val pagesList: Array<Page> = arrayOf(
    Page(1, "TODO1", "あいうえおかきくけこさしすせそたちつてとなにぬねの", LocalDate.of(2024,10, 15)),
    Page(2, "TODO2", "はひふえほまみむめもやゆよらりるれろわをん", LocalDate.of(2024,10, 30)),
    Page(3, "DOTO3", "よ", LocalDate.of(2024,11, 15))

)