package com.example.buttercms.ui.main.blog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentBlogBinding
import com.example.buttercms.databinding.ItemBlogBinding
import com.example.buttercms.model.Blog
import com.example.buttercms.ui.main.viewpager.ViewPagerContainerFragmentDirections
import com.example.buttercms.utils.DateFormatter
import com.example.buttercms.utils.createCoroutineErrorHandler
import kotlinx.coroutines.launch

class BlogFragment : Fragment() {

    private val blogViewModel: BlogViewModel by viewModels()
    private var _binding: FragmentBlogBinding? = null
    private lateinit var blogAdapter: BlogAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogBinding.inflate(inflater, container, false)
        val view = binding.root

        blogAdapter = BlogAdapter()
        blogViewModel.getData().observe(
            viewLifecycleOwner,
            { blogs ->
                blogAdapter.submitList(blogs)
            }
        )

        binding.rvBlog.apply {
            adapter = blogAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        binding.srlReloadBlog.apply {
            setOnRefreshListener {
                lifecycleScope.launch(createCoroutineErrorHandler(requireContext())) {
                    blogViewModel.loadData()
                }
                isRefreshing = false
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        lifecycleScope.launch(createCoroutineErrorHandler(requireContext())) {
            blogViewModel.loadData()
        }
        super.onResume()
    }
}

class BlogAdapter : ListAdapter<Blog, BlogAdapter.NewsItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(blog: Blog) {
            val binding = ItemBlogBinding.bind(itemView)
            val date = DateFormatter().formatDate(blog.published)
            val authorFirstName = blog.author.firstName
            val authorLastName = blog.author.lastName
            binding.apply {
                tvAuthorBlogItem.text = "$authorFirstName $authorLastName"
                tvTitleBlogItem.text = blog.title
                tvSubtitleBlogItem.text = blog.subtitle
                tvTimeBlogItem.text = date

                Glide.with(itemView)
                    .load(blog.image)
                    .into(binding.ivBlogItem)
            }

            itemView.setOnClickListener {
                itemView.findNavController().navigate(
                    ViewPagerContainerFragmentDirections.actionStartDestToBlogDetailFragment(
                        blog,
                        date.toString()
                    )
                )
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Blog>() {

    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.title == newItem.title
    }
}
