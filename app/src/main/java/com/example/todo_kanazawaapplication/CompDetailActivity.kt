package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompDetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.copmpagedetail)

        // ボタンの取得とクリックリスナーの設定
        val detailReturn: Button = findViewById(R.id.datailbtnreturn)

        val detail_title: TextView = findViewById(R.id.page_detail_title2)
        val detail_date: TextView = findViewById(R.id.page_detail_updated_at2)
        val detail_detail: TextView = findViewById(R.id.page_detail_fragment_host2)

        val intent = intent // Intentを取得
        //val todoid: String? = intent.getStringExtra("taskid") // "todoid"というキーでデータを取得
        // CompDetailActivityでの取得方法
        val taskIdInt: Int = intent.getIntExtra("taskid", -1) // デフォルト値として-1を設定

        Log.d("MyAdapter2", "Button clicked for taskid: $taskIdInt")

        // nullチェックをして、todoidに対応するページをリストから一度だけ取得
        // taskIdInt = todoid?.toIntOrNull() // nullチェックを行い、Intに変換

        if (taskIdInt != null) {
            // taskIdIntを使用して、対応するPageを取得
            val page = endpagesList.find { it.id == taskIdInt }
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
            finish() // 現在のアクティビティを閉じる
        }

    }

}



