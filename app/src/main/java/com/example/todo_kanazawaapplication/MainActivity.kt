package com.example.todo_kanazawaapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var pagesRecyclerView: RecyclerView
    private lateinit var pagesRecyclerViewComponent: PagesRecyclerViewComponent
    private lateinit var adapter: PagesRecyclerViewComponent.MyAdapter  // Adapterを設定

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //画面の呼び出し

        pagesRecyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            pagesRecyclerViewComponent = PagesRecyclerViewComponent(pagesList, context)
            setHasFixedSize(true)
            layoutManager = pagesRecyclerViewComponent.viewManager
            adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        }

        // ボタンがクリックされたときに別画面に遷移
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            Log.d("MainActivity", "Add button clicked")  // ログで確認
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }


        // ボタンがクリックされたときにチェックされたアイテムのIDを取得し削除
        val btnDelete: Button = findViewById(R.id.btnDelete)
        btnDelete.setOnClickListener {
            val checkedIds = adapter.getCheckedItemIds() //adapterクラスの関数
            Log.d("MainActivity", "Checked IDs: $checkedIds") // ログで確認

            if (checkedIds.isNotEmpty()) {
                val iterator = pagesList.iterator()  // イテレータを使用
                while (iterator.hasNext()) {
                    val page = iterator.next()
                    if (checkedIds.contains(page.id)) {
                        iterator.remove()  // チェックされたIDに一致するアイテムを削除
                    }
                }
                ////警告があるが代替案が不明
                adapter.notifyDataSetChanged() // UIを更新
                adapter.resetCheckedItems() // チェックボックスの状態をリセット
            }else{
                //チェックされていないときに表示
                Toast.makeText(this, "削除するタスクをチェックしてください", Toast.LENGTH_SHORT).show()
            }

        }

        // ボタンがクリックされたときにチェックされたアイテムのIDを取得し完了
        val btnComp: Button = findViewById(R.id.btnComp)
        btnComp.setOnClickListener {
            val checkedIds = adapter.getCheckedItemIds()
            Log.d("MainActivity", "Checked IDs: $checkedIds") // ログで確認

            val intent = Intent(this, CompleteTodoActivity::class.java)
            intent.putExtra("checkedIds", checkedIds.toIntArray())       // タスクのIDを渡す
            startActivity(intent) //別画面にデータを渡し遷移

        }
    }

    //画面更新用
    override fun onResume() {
        super.onResume()
        // データを再取得してRecyclerViewを更新
        adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        adapter.notifyDataSetChanged()
    }

}