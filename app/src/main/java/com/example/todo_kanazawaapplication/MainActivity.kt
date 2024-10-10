package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    private lateinit var pagesRecyclerView: RecyclerView
    private lateinit var pagesRecyclerViewComponent: PagesRecyclerViewComponent

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pageeditactuvity)

        pagesRecyclerView = findViewById<RecyclerView>(R.id.recycler).apply { // ここでIDを修正
            pagesRecyclerViewComponent = PagesRecyclerViewComponent(pagesList, context)
            setHasFixedSize(true)
            layoutManager = pagesRecyclerViewComponent.viewManager
            adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        }
//        holder.button.setOnClickListener {
//            val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java).apply {
//                putExtra("taskid", taskid) // タスクのIDを渡す
//            }
//            (holder.itemView.context as MainActivity).startActivityForResult(intent, REQUEST_CODE) // RESULTを受け取るためのリクエストコードを指定
//        }

    }
//        val detail:Button = findViewById(R.id.btntododetail)
//        detail.setOnClickListener() {
//            val intent = Intent(this, ShowDetailActivity::class.java)
//            startActivity(intent)
//        }

    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val taskid = data?.getStringExtra("taskid") // タスクのIDを取得
            val updatedTitle = data?.getStringExtra("title") // 更新したタイトルを取得
            val updatedContent = data?.getStringExtra("content") // 更新した内容を取得
            val updatedDeadline = data?.getStringExtra("deadline") // 更新した締切日を取得

            // IDを元に配列を更新する
            val taskIdInt = taskid?.toIntOrNull()
            if (taskIdInt != null) {
                val index = pagesList.indexOfFirst { it.id == taskIdInt }
                if (index != -1) {
                    // ページの内容を更新
                    pagesList[index] = pagesList[index].copy(
                        title = updatedTitle ?: pagesList[index].title,
                        content = updatedContent ?: pagesList[index].content,
                        deadline = LocalDate.parse(updatedDeadline) // 文字列を LocalDate に変換
                    )

                    // RecyclerViewのAdapterを再描画
                    pagesRecyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

}
