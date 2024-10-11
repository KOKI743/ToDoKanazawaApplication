package com.example.todo_kanazawaapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class AddTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtodo)

        // EditText の変数は正しく型指定
        val saveButton: Button = findViewById(R.id.addbtnOK)
        val taskTitle: EditText = findViewById(R.id.editTextName)
        val taskdate: EditText = findViewById(R.id.editTextDate)
        val taskContent:EditText= findViewById(R.id.editTextContent)
        val btnreturn:Button = findViewById(R.id.addbtnreturn)

        // 更新ボタンのクリックリスナー
        saveButton.setOnClickListener {
            val taskTitletext = taskTitle.text.toString()
            val taskContenttext = taskContent.text.toString()
            val taskdateString = taskdate.text.toString()

            val taskDate: LocalDate? = try {
                LocalDate.parse(taskdateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            } catch (e: DateTimeParseException) {
                Log.e("formatchenge","Localtimeerror")
                null
            }

            if (taskTitletext.isNotEmpty() && taskContenttext.isNotEmpty() && taskDate != null) {
                val newTask = Page(
                    id = pagesList.size + 1,
                    title = taskTitletext,
                    content = taskContenttext,
                    deadline = taskDate
                )
                pagesList.add(newTask)
                finish()
            }
        }

        btnreturn.setOnClickListener{
            finish()
        }
    }
}

