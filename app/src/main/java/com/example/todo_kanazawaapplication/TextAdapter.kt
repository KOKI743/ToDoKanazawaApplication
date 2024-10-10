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

class PagesRecyclerViewComponent(data: Array<Page>, context: Context) {
    val viewManager: LinearLayoutManager = LinearLayoutManager(context)
    val viewAdapter: MyAdapter = MyAdapter(data)

    class MyAdapter(private val data: Array<Page>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.checkBox)
            val deadline: TextView = itemView.findViewById(R.id.deadlineText)
            val button: Button = itemView.findViewById(R.id.btntododetail) // ボタンをここで取得
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val page = data[position]
            holder.title.text = page.title
            holder.deadline.text = page.deadline.toString()
            val taskid = page.id


            // ボタンのクリックリスナーを設定
            holder.button.setOnClickListener {
                // ボタンがクリックされたときの処理
                val intent = Intent(holder.itemView.context, ShowDetailActivity::class.java).apply {
                    Log.d("MyAdapter", "Button clicked for taskid: $taskid")
                    putExtra("taskid",taskid.toString())       // タスクのタイトルを渡す
                }
                (holder.itemView.context as MainActivity).startActivityForResult(intent, MainActivity.REQUEST_CODE) // RESULTを受け取るためのリクエストコードを指定
            }
        }
    }
}

