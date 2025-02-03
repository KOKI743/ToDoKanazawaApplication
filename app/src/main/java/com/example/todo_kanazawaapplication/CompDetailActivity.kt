package com.example.todo_kanazawaapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comp_page_detail) //comp_page_detail.xmlを表示

        // comp_page_detail.xmlの要素の取得
        val detailReturn: Button = findViewById(R.id.detailBtnReturn)
        val detailTitle: TextView = findViewById(R.id.pageDetailTitle2)
        val detailDate: TextView = findViewById(R.id.pageDetailUpdatedAt2)
        val detailDetail: TextView = findViewById(R.id.pageDetailFragmentHost2)

        //渡された要素の受け取り
        val intent = intent // Intentを取得
        // 渡される値がない場合のエラー値として-1を設定
        val taskIdInt: Int = intent.getIntExtra("taskId", -1)

        Log.d("MyAdapter2", "Button clicked for taskId: $taskIdInt") // ログで確認

        Log.d("MyAdapter2", "Button clicked for taskId: $taskIdInt") // ログで確認


        //渡された値に対応するendPagesListの要素を表示
        if (taskIdInt != -1) {
            // taskIdIntを使用して、endPagesListの対応するPageを取得
            val page: Page? = endPagesList.find { it.id == taskIdInt }
            if (page != null) {
                // 取得したPageの情報を格納
                detailTitle.text = page.title
                detailDate.text = page.deadline.toString()
                detailDetail.text = page.content
            } else {
                //idが適していないときに表示
                detailTitle.text = "該当するタスクが見つかりません"
            }
        } else {
            //渡された値が適していないときに表示
            Log.e("ShowDetailActivity", "Received taskId is not a valid number.") // ログで確認
            detailTitle.setText(R.string.error_task_name)
        }

        detailReturn.setOnClickListener {
            finish() // 現在のアクティビティを閉じる
        }

    }

}



