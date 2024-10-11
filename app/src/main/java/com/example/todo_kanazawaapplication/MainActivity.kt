package com.example.todo_kanazawaapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate


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



        val btnadd = findViewById<Button>(R.id.btnadd)
        btnadd.setOnClickListener {
            Log.d("MainActivity", "Add button clicked")  // ここでクリックイベントがトリガーされたことを確認
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)

//            adapter.notifyDataSetChanged()  // データ全体の変更を通知
        }
    }

    override fun onResume() {
        super.onResume()
        // データを再取得してRecyclerViewを更新
        adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        adapter.notifyDataSetChanged()
    }

}