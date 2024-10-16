package com.example.todo_kanazawaapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagesRecyclerViewComponent(data: ArrayList<Page>, context: Context) {
    val viewManager: LinearLayoutManager = LinearLayoutManager(context)
    val viewAdapter: MyAdapter = MyAdapter(data)


    class MyAdapter(private val data: ArrayList<Page>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        // チェックボックスの状態を保存するマップ
        private val checkedItems: MutableMap<Int, Boolean> = mutableMapOf()


        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val title: TextView = itemView.findViewById(R.id.checkBox)
            val deadline: TextView = itemView.findViewById(R.id.deadlineText)
            val button: Button = itemView.findViewById(R.id.btntododetail) // ボタンをここで取得

            val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
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
                    putExtra("taskid", taskid.toString())       // タスクのタイトルを渡す
                }
                holder.itemView.context.startActivity(intent)
            }


            // チェックボックスの状態を設定
            holder.checkBox.setOnCheckedChangeListener(null) // リスナーを解除
            holder.checkBox.isChecked = checkedItems[taskid] ?: false // 状態を適用

            // チェックボックスの状態変更を監視して保存
            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                checkedItems[taskid] = isChecked
            }

        }
        // チェックが入っているアイテムのIDを取得する
        fun getCheckedItemIds(): List<Int> {
            return checkedItems.filter { it.value }.keys.toList()
        }

        // 削除後にチェックボックスの状態をリセット
        fun resetCheckedItems() {
            checkedItems.clear()
            notifyDataSetChanged() // UIを更新
        }

    }

}

