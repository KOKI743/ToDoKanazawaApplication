package com.example.todo_kanazawaapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date


class MainActivity : AppCompatActivity() {
    private val pagesList: Array<Page> = arrayOf(
        Page(1, "あかさたな", "あいうえおかきくけこさしすせそたちつてとなにぬねの", Date()),
        Page(2, "はまやらわ", "はひふえほまみむめもやゆよらりるれろわをん", Date()),
        Page(3, "やゆよ", "よ", Date())
    )

    private lateinit var pagesRecyclerView: RecyclerView
    private lateinit var pagesRecyclerViewComponent: PagesRecyclerViewComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pageeditactuvity)

        pagesRecyclerView = findViewById<RecyclerView>(R.id.recycler).apply { // ここでIDを修正
            pagesRecyclerViewComponent = PagesRecyclerViewComponent(pagesList, context)
            setHasFixedSize(true)
            layoutManager = pagesRecyclerViewComponent.viewManager
            adapter = pagesRecyclerViewComponent.viewAdapter // Adapterを設定
        }
    }
}
