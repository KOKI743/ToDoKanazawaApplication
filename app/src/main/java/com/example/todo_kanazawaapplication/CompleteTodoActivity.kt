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

    private lateinit var pagesRecyclerView: RecyclerView
    private lateinit var pagesRecyclerViewComponent: EndPagesRecyclerViewComponent
    //lateinit var adapter: PagesRecyclerViewComponent.MyAdapter  // Adapterを設定

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.endtodo)

        // EditText の変数は正しく型指定
        val btnreturn: Button = findViewById(R.id.btnreturn)
        val intent = intent
        val checkedIds = intent.getIntArrayExtra("checkedIds")

        if (checkedIds != null) {
            val iterator = pagesList.iterator()  // イテレータを使用して安全にリストから削除
            while (iterator.hasNext()) {
                val page = iterator.next()
                if (checkedIds.contains(page.id)) {

                    val taskTitletext = page.title
                    val taskContenttext = page.content
                    val taskdateString = page.deadline.toString()

                    val taskDate: LocalDate? = try {
                        LocalDate.parse(taskdateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    } catch (e: DateTimeParseException) {
                        Log.e("formatchenge", "Localtimeerror")
                        null
                    }

                    if (taskTitletext.isNotEmpty() && taskContenttext.isNotEmpty() && taskDate != null) {
                        val newTask = Page(
                            id = pagesList.size + 1,
                            title = taskTitletext,
                            content = taskContenttext,
                            deadline = taskDate
                        )
                        endpagesList.add(newTask)

                        iterator.remove()  // チェックされたIDに一致するアイテムを削除
                    }
                }
            }
        }

        pagesRecyclerView = findViewById<RecyclerView>(R.id.recycler2).apply { // ここでIDを修正
            pagesRecyclerViewComponent = EndPagesRecyclerViewComponent(endpagesList, context)
            setHasFixedSize(true)
            layoutManager = pagesRecyclerViewComponent.viewManager
            adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        }

        btnreturn.setOnClickListener {
            finish()
        }

    }
}

