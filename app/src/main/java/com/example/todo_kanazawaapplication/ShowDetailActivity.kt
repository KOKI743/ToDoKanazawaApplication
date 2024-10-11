package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class ShowDetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagedetailactivity)

        // ボタンの取得とクリックリスナーの設定
        val detailReturn: Button = findViewById(R.id.datailbtnreturn)

        val detail_title: TextView = findViewById(R.id.page_detail_title)
        val detail_date: TextView = findViewById(R.id.page_detail_updated_at)
        val detail_detail: TextView = findViewById(R.id.page_detail_fragment_host)
        val updateButton: Button = findViewById(R.id.btnupdate)

        val intent = intent // Intentを取得
        val todoid: String? = intent.getStringExtra("taskid") // "todoid"というキーでデータを取得

        // nullチェックをして、todoidに対応するページをリストから一度だけ取得
        val taskIdInt = todoid?.toIntOrNull() // nullチェックを行い、Intに変換

        if (taskIdInt != null) {
            // taskIdIntを使用して、対応するPageを取得
            val page = pagesList.find { it.id == taskIdInt }
            if (page != null) {
                // 取得したPageの情報を表示
                detail_title.text = page.title
                detail_date.text = page.deadline.toString()
                detail_detail.text = page.content
            } else {
                detail_title.text = "該当するタスクが見つかりません"
            }
        } else {
            Log.e("ShowDetailActivity", "Received taskid is not a valid number.")
            detail_title.text = "無効なタスクID"
        }

        // 更新ボタンのクリックリスナー
        updateButton.setOnClickListener {
            if (taskIdInt != null) {
                val updatedTitle = detail_title.text.toString() // 更新したタイトルを取得
                val updatedContent = detail_detail.text.toString() // 更新した内容を取得
                val updatedDeadline = detail_date.text.toString() // 更新した締切日を取得

                // IDを元に配列を更新する
                val index = pagesList.indexOfFirst { it.id == taskIdInt }
                if (index != -1) {
                    // ページの内容を更新
                    pagesList[index] = pagesList[index].copy(
                        title = updatedTitle,
                        content = updatedContent,
                        deadline = LocalDate.parse(updatedDeadline) // 文字列を LocalDate に変換
                    )

                    finish() // 画面を閉じる
                }
            }
        }

        detailReturn.setOnClickListener {
            finish() // 現在のアクティビティを閉じる
        }
    }
}



