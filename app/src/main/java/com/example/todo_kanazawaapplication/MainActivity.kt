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

    // previousListSizeを初期化（RecyclerViewの項目数を追跡するための変数）
    private var previousListSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagesRecyclerViewComponent = PagesRecyclerViewComponent(pagesList, this)
        adapter = pagesRecyclerViewComponent.viewAdapter
        pagesRecyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = pagesRecyclerViewComponent.viewManager
            adapter = this@MainActivity.adapter
        }

        // previousListSizeの初期値を設定
        previousListSize = pagesList.size


        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            Log.d("MainActivity", "Add button clicked")
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        val btnDelete: Button = findViewById(R.id.btnDelete)
        btnDelete.setOnClickListener {
            val checkedIds = adapter.getCheckedItemIds()
            Log.d("MainActivity", "Checked IDs: $checkedIds")

            if (checkedIds.isNotEmpty()) {
                val iterator = pagesList.iterator()
                while (iterator.hasNext()) {
                    val page = iterator.next()
                    if (checkedIds.contains(page.id)) {
                        iterator.remove()
                    }
                }
                adapter.notifyItemRangeRemoved(0, checkedIds.size)
                adapter.resetCheckedItems()
            } else {
                Toast.makeText(this, "削除するタスクをチェックしてください", Toast.LENGTH_SHORT).show()
            }
        }

        val btnComp: Button = findViewById(R.id.btnComp)
        btnComp.setOnClickListener {
            val checkedIds = adapter.getCheckedItemIds()
            Log.d("MainActivity", "Checked IDs: $checkedIds")
            val intent = Intent(this, CompleteTodoActivity::class.java)
            intent.putExtra("checkedIds", checkedIds.toIntArray())
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()

        // RecyclerViewの更新処理
        val currentListSize = pagesList.size
        if (currentListSize > previousListSize) {
            adapter.notifyItemRangeInserted(previousListSize, currentListSize - previousListSize)
        } else if (currentListSize < previousListSize) {
            adapter.notifyItemRangeRemoved(currentListSize, previousListSize - currentListSize)
        }
        previousListSize = currentListSize // 前回のリストサイズを更新
    }
}
