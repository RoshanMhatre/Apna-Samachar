package com.example.apnasamachar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apnasamachar.R.id.title

class SamacharAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewViewHolder>() {
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        val viewHolder = NewViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }
    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}
class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView =  itemView.findViewById(title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView =  itemView.findViewById(R.id.author)
}
interface NewsItemClicked{
    fun onItemClicked(item: News)
}