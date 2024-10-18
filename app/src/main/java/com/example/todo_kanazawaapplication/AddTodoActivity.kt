package com.example.todo_kanazawaapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import android.app.DatePickerDialog
import android.icu.util.Calendar

class AddTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo) //addTodo.xmlを表示

        //addTodo.xml内の要素の取得
        val saveButton: Button = findViewById(R.id.addBtnOK)
        val taskTitle: EditText = findViewById(R.id.editTextName)
        val taskDate: EditText = findViewById(R.id.editTextDate)
        val taskContent:EditText= findViewById(R.id.editTextContent)
        val btnReturn:Button = findViewById(R.id.addBtnReturn)
        val btnCalender:Button = findViewById(R.id.addBtnCalender)

        // 更新ボタンのクリックリスナー
        saveButton.setOnClickListener {
            val taskTitleText = taskTitle.text.toString()
            val taskContentText = taskContent.text.toString()
            val taskDateString = taskDate.text.toString()

            //taskDateがLocalDate型に変換可能かの判定
            val parsedTaskDate: LocalDate? = try {
                LocalDate.parse(taskDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            } catch (e: DateTimeParseException) {
                Log.e("formatChange","LocalTimeError")
                null
            }

            //空でないなら追加、元画面に遷移
            if (taskTitleText.isNotEmpty() && taskContentText.isNotEmpty() && parsedTaskDate != null) {
                val newTask = Page(
                    id = pagesList.size + 1,
                    title = taskTitleText,
                    content = taskContentText,
                    deadline = parsedTaskDate
                )
                pagesList.add(newTask)
                finish()
            }else{
                //要素が空かdateが適していないときに表示
                Toast.makeText(this, "追加に失敗しました。\nもう一度確かめてみてください。", Toast.LENGTH_SHORT).show()
            }


        }

        btnCalender.setOnClickListener{

            Log.d("calender", "Calendar button clicked")

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(

                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // 選択された日付をtaskDate (EditText) にセット
                    val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    taskDate.setText(selectedDate)
                },
                year, month, day
            )
            // DatePickerDialogを表示
            datePickerDialog.show()
        }

        btnReturn.setOnClickListener{
            finish()
        }
    }
}

