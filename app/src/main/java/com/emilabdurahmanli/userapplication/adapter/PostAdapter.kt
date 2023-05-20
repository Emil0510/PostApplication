package com.emilabdurahmanli.userapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilabdurahmanli.userapplication.PostActivity
import com.emilabdurahmanli.userapplication.R
import com.emilabdurahmanli.userapplication.model.Post

class PostAdapter(list : List<Post>, val listener : OnItemClickListener) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var list : List<Post>
    init {
        this.list=list
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.titleText)
        var body = itemView.findViewById<TextView>(R.id.bodyText)
        var context = itemView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.posts_row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(list.get(position).title)
        holder.body.setText(list.get(position).body)
        holder.body.setOnClickListener {
            listener.OnItemClick(list[position])
        }
    }


}
interface OnItemClickListener{
    fun OnItemClick(item : Post)
}