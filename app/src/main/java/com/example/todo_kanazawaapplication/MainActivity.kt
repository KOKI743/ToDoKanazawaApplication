package com.example.todo_kanazawaapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pageeditactuvity)  // 正しいレイアウトを設定

        // RecyclerView を取得
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)

        // レイアウトマネージャーを設定
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // アダプターを設定
        recyclerView.adapter = TextAdapter()  // アダプターを設定
    }
}



