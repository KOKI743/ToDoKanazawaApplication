package com.example.todo_kanazawaapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagesRecyclerViewComponent(data: Array<Page>, context: Context) {
    val viewManager: LinearLayoutManager = LinearLayoutManager(context)
    val viewAdapter: MyAdapter = MyAdapter(data)

    class MyAdapter(private val data: Array<Page>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.textView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val page = data[position]
            holder.title.text = page.title
        }
    }
}

