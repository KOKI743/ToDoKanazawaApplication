package com.example.todo_kanazawaapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CompleteTodoActivity : AppCompatActivity() {

    //完了済みリスト表示RecyclerViewの設定
    private lateinit var pagesRecyclerView: RecyclerView
    private lateinit var pagesRecyclerViewComponent: EndPagesRecyclerViewComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_todo) //endtodo.xmlを表示

        // endtodo.xml内の要素の取得
        val btnReturn: Button = findViewById(R.id.btnReturn)

        //渡された要素の受け取り
        val intent = intent
        val checkedIds = intent.getIntArrayExtra("checkedIds")

        //要素があるならendPagesListに指定のIDのpagesListの要素を格納
        if (checkedIds != null) {
            val iterator = pagesList.iterator()  // イテレータを使用
            while (iterator.hasNext()) {
                val page = iterator.next()
                if (checkedIds.contains(page.id)) {

                    //指定のIDのpagesListの要素の取得
                    val taskTitleText = page.title
                    val taskContentText = page.content
                    val taskDateString = page.deadline.toString()

                    //taskDateがLocalDate型に変換可能かの判定
                    val taskDate: LocalDate? = try {
                        LocalDate.parse(taskDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    } catch (e: DateTimeParseException) {
                        Log.e("formatChange", "LocalTimeError")
                        null
                    }

                    //endPagesListに格納
                    if (taskTitleText.isNotEmpty() && taskContentText.isNotEmpty() && taskDate != null) {
                        val newTask = Page(
                            id = pagesList.size + 1,
                            title = taskTitleText,
                            content = taskContentText,
                            deadline = taskDate
                        )
                        endPagesList.add(newTask)

                        // チェックされたIDに一致するpagesListの要素を削除
                        iterator.remove()
                    }
                }
            }
        }

        //EndPagesRecyclerViewの設定
        pagesRecyclerView = findViewById<RecyclerView>(R.id.recycler2).apply { // ここでIDを修正
            pagesRecyclerViewComponent = EndPagesRecyclerViewComponent(endPagesList, context)
            setHasFixedSize(true)
            layoutManager = pagesRecyclerViewComponent.viewManager
            adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        }

        // 現在のアクティビティを閉じる
        btnReturn.setOnClickListener {
            finish()
        }

    }
}

