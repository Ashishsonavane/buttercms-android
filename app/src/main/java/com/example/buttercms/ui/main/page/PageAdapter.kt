package com.example.buttercms.ui.main.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buttercms.R
import com.example.buttercms.databinding.ItemPageBinding
import com.example.buttercms.model.Pages

class PageAdapter (var pages: List<Pages>) :
    RecyclerView.Adapter< PageAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_page, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pages: Pages) {
            val binding = ItemPageBinding.bind(itemView)
            binding.apply {
                binding.author.text = pages.author
                binding.title.text = pages.title
                binding.description.text = pages.description
                binding.time.text = pages.published
            }
        }
    }

    override fun getItemCount(): Int {
        return pages.size
    }
}