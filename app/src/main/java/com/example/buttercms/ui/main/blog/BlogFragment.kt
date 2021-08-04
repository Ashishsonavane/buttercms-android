package com.example.buttercms.ui.main.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentBlogBinding
import com.example.buttercms.databinding.ItemBlogBinding
import com.example.buttercms.model.Blog

class BlogFragment : Fragment() {

    private val blogViewModel: BlogViewModel by viewModels()
    private var _binding: FragmentBlogBinding? = null
    private lateinit var blogAdapter: BlogAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlogBinding.inflate(inflater, container, false)
        val view = binding.root
        blogAdapter = BlogAdapter(blogViewModel.fetchData())
        val recyclerView = binding.rvBlog
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = blogAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class BlogAdapter(var blog: List<Blog>) :
    RecyclerView.Adapter<BlogAdapter.NewsItemViewHolder>() {

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
                binding.tvAuthorBlog.text = blog.author
                binding.tvTitleBlog.text = blog.title
                binding.tvDescriptionBlog.text = blog.description
                binding.tvTimeBlog.text = blog.published
            }

            itemView.setOnClickListener {
                itemView.findNavController().navigate(R.id.action_start_dest_to_blogDetailFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return blog.size
    }
}
