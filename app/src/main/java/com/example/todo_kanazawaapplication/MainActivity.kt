package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var pagesRecyclerView: RecyclerView
    private lateinit var pagesRecyclerViewComponent: PagesRecyclerViewComponent
    lateinit var adapter: PagesRecyclerViewComponent.MyAdapter  // Adapterを設定

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



        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            Log.d("MainActivity", "Add button clicked")  // ここでクリックイベントがトリガーされたことを確認
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)

//            adapter.notifyDataSetChanged()  // データ全体の変更を通知
        }


        // ボタンがクリックされたときにチェックされたアイテムのIDを取得
        val btndelete: Button = findViewById(R.id.btnDelete)
        btndelete.setOnClickListener {
            val checkedIds = adapter.getCheckedItemIds()
            Log.d("MainActivity", "Checked IDs: $checkedIds")

            if (checkedIds.isNotEmpty()) {
                val iterator = pagesList.iterator()  // イテレータを使用して安全にリストから削除
                while (iterator.hasNext()) {
                    val page = iterator.next()
                    if (checkedIds.contains(page.id)) {
                        iterator.remove()  // チェックされたIDに一致するアイテムを削除
                    }
                }
                adapter.notifyDataSetChanged() // UIを更新
                adapter.resetCheckedItems() // チェックボックスの状態をリセット
            }

        }

        // ボタンがクリックされたときにチェックされたアイテムのIDを取得
        val btncomp: Button = findViewById(R.id.btncomp)
        btndelete.setOnClickListener {
            val checkedIds = adapter.getCheckedItemIds()
            Log.d("MainActivity", "Checked IDs: $checkedIds")

            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        // データを再取得してRecyclerViewを更新
        adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        adapter.notifyDataSetChanged()
    }

}