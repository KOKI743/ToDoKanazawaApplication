package com.example.todo_kanazawaapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextAdapter : RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.item_text, parent, false)  // アイテム用のレイアウト
        return ViewHolder(root)
    }

    override fun getItemCount() = 5  // アイテム数を返す

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> holder.text.text = "青森"
            1 -> holder.text.text = "岩手"
            2 -> holder.text.text = "秋田"
            3 -> holder.text.text = "宮城"
            4 -> holder.text.text = "山形"
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.textView)  // TextView を取得
    }
}
