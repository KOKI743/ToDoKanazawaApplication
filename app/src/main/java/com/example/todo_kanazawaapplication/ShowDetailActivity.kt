package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagedetailactivity)

        // ボタンの取得とクリックリスナーの設定
        val detailReturn: Button = findViewById(R.id.datailbtnreturn)

        val detail_title:TextView = findViewById(R.id.page_detail_title)
        val detail_date:TextView = findViewById(R.id.page_detail_updated_at)
        val detail_detail:TextView = findViewById(R.id.page_detail_fragment_host)

        val intent = intent // Intentを取得
        val todoid: String? = intent.getStringExtra("taskid") // "todoid"というキーでデータを取得
        Log.d("ShowDetailActivity", "todoid: $todoid")


        // nullチェックをして、todoidに対応するページをリストから一度だけ取得
        val taskIdInt = todoid?.toIntOrNull() // nullチェックを行い、Intに変換
        val page = pagesList.find { it.id.toString() == todoid }

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

        detailReturn.setOnClickListener {
            finish() // これが画面遷移元へ戻る処理
        }
    }
}



