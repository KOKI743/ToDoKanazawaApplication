package com.example.todo_kanazawaapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndPagesRecyclerViewComponent(data: ArrayList<Page>, context: Context) {
    val viewManager: LinearLayoutManager = LinearLayoutManager(context)
    val viewAdapter: MyAdapter = MyAdapter(data)


    class MyAdapter(private val data: ArrayList<Page>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val title: TextView = itemView.findViewById(R.id.textView)
            val deadline: TextView = itemView.findViewById(R.id.deadlineText)
            val detail: Button = itemView.findViewById(R.id.btnToDoDetail)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.end_item, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val page = data[position]
            holder.title.text = page.title
            holder.deadline.text = page.deadline.toString()
            val taskId = page.id


            // ボタンのクリックリスナーを設定
            holder.detail.setOnClickListener {
                // ボタンがクリックされたときの処理
                val intent = Intent(holder.itemView.context, CompDetailActivity::class.java).apply {
                    Log.d("MyAdapter", "Button clicked for taskId: $taskId") // ログで確認
                    putExtra("taskId", taskId)       // タスクのIDを渡す
                }
                holder.itemView.context.startActivity(intent)
            }

        }

    }

}

