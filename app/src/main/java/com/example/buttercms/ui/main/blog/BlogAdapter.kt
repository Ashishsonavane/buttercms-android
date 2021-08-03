package com.example.buttercms.ui.main.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buttercms.R
import com.example.buttercms.databinding.ItemBlogBinding
import com.example.buttercms.model.Blog

class BlogAdapter(var blog: List<Blog>) :
    RecyclerView.Adapter< BlogAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_blog, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(blog[position])
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(blog: Blog) {
            val binding = ItemBlogBinding.bind(itemView)
            binding.apply {
                binding.author.text = blog.author
                binding.title.text = blog.title
                binding.description.text = blog.description
                binding.time.text = blog.published
            }
        }
    }

    override fun getItemCount(): Int {
        return blog.size
    }
}

// class DiffCallback : DiffUtil.ItemCallback<Blog>() {
//
//    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
//        return oldItem.title == newItem.title
//    }
// }
