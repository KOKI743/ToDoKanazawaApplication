package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShowDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagedetailactivity)

        // ボタンの取得とクリックリスナーの設定
        val detailReturn: Button = findViewById(R.id.datailbtnreturn)

        val detail_title: TextView = findViewById(R.id.page_detail_title)
        val detail_date:TextView = findViewById(R.id.page_detail_updated_at)
        val detail_detail:TextView = findViewById(R.id.page_detail_fragment_host)
        val updateButton:Button = findViewById(R.id.btnupdate)

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
            // 新しい内容を取得
            val newtitle = detail_title.text.toString()
            val newdate = detail_date.text.toString()
            val newdetail = detail_detail.text.toString()
            if (taskIdInt != null) {
                // pagesListを更新
                val index = pagesList.indexOfFirst { it.id == taskIdInt }
                if (index != -1) {
                    pagesList[index] = pagesList[index].copy(title = newtitle) // 内容を更新
                    pagesList[index] = pagesList[index].copy(content = newdate) // 内容を更新
                    pagesList[index] = pagesList[index].copy(content = newdetail) // 内容を更新
                    Toast.makeText(this, "内容が更新されました", Toast.LENGTH_SHORT).show()
                }
            }
            Log.d("title", "title: ${detail_title.text}")
        }

        // 戻るボタンのクリックリスナー
        detailReturn.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("taskid", todoid) // 更新したタスクのIDを渡す
                putExtra("title", detail_title.text.toString()) // 更新したタイトルを渡す
                putExtra("content", detail_detail.text.toString()) // 更新した内容を渡す
                putExtra("deadline", detail_date.text.toString()) // 更新した締切日を渡す
            }
            setResult(RESULT_OK, resultIntent) // 結果を設定
            finish() // 画面遷移元へ戻る処理
        }
    }
}



