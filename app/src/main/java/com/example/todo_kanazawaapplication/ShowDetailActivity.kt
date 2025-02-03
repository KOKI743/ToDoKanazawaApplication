package com.example.todo_kanazawaapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class ShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page_detail) //page_detail.xmlを表示

        // page_detail.xml内の要素の取得
        val detailReturn: Button = findViewById(R.id.detailBtnReturn)
        val detailTitle: TextView = findViewById(R.id.pageDetailTitle)
        val detailDate: TextView = findViewById(R.id.pageDetailUpdatedAt)
        val detailDetail: TextView = findViewById(R.id.pageDetailFragmentHost)
        val updateButton: Button = findViewById(R.id.btnUpdate)

        val intent = intent // Intentを取得
        val todoId: String? = intent.getStringExtra("taskId") // "todoId"というキーで、渡されたデータを取得

        // nullチェックをして、todoIdに対応するページをリストから一度だけ取得
        val taskIdInt = todoId?.toIntOrNull() // nullチェックを行い、Intに変換

        if (taskIdInt != null) {
            // taskIdIntを使用して、対応するPageを取得
            val page = pagesList.find { it.id == taskIdInt }
            if (page != null) {
                // 取得したPageの情報を格納、表示
                detailTitle.text = page.title
                detailDate.text = page.deadline.toString()
                detailDetail.text = page.content
            } else {
                detailTitle.text = "該当するタスクが見つかりません"
            }
        } else {
            //渡された要素が空のときに表示
            Log.e("ShowDetailActivity", "Received taskId is not a valid number.")
            detailTitle.setText(R.string.error_task_name)
        }

        // 更新ボタンのクリックリスナー
        updateButton.setOnClickListener {
            if (taskIdInt != null) {
                val updatedTitle = detailTitle.text.toString() // 入力したタイトルを取得
                val updatedContent:String = detailDetail.text.toString() // 入力した内容を取得
                val updatedDeadline = detailDate.text.toString() // 入力した締切日を取得

                //taskDateがLocalDate型に変換可能かの判定
                val taskDate: LocalDate? = try {
                    LocalDate.parse(updatedDeadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                } catch (e: DateTimeParseException) {
                    Log.e("formatChange","LocalTimError")
                    null
                }

                // IDを元に配列を更新する
                val index: Int = pagesList.indexOfFirst { it.id == taskIdInt }
                if (index != -1 && updatedTitle.isNotEmpty() && updatedContent.isNotEmpty() && taskDate != null) {
                    // ページの内容を更新
                    pagesList[index] = pagesList[index].copy(
                        title   = updatedTitle,
                        content = updatedContent,
                        deadline = taskDate // 文字列を LocalDate に変換
                    )
                    finish() // 画面を閉じる
                }else{
                    //要素が空かdateが適していないときに表示
                    Toast.makeText(this, "追加に失敗しました。\nもう一度確かめてみてください。", Toast.LENGTH_SHORT).show()
                }
            }
        }

        detailReturn.setOnClickListener {
            finish() // 現在のアクティビティを閉じる
        }
    }
}



