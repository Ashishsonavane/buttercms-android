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
import com.buttercms.model.Data
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentBlogBinding
import com.example.buttercms.databinding.ItemBlogBinding
import com.example.buttercms.ui.main.viewpager.ViewPagerContainerFragmentDirections
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

class BlogAdapter : ListAdapter<Data, BlogAdapter.NewsItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(blog: Data) {
            val binding = ItemBlogBinding.bind(itemView)
            val authorFirstName = blog.author?.first_name
            val authorLastName = blog.author?.last_name
            val date = blog.published.toString()
            val blogDate = date.substring(0, 10) + date.substring(29, 34)
            binding.apply {
                tvAuthorBlogItem.text = "$authorFirstName $authorLastName"
                tvTitleBlogItem.text = blog.title
                tvSubtitleBlogItem.text = blog.slug
                tvTimeBlogItem.text = blogDate

                Glide.with(itemView)
                    .load(blog.featured_image)
                    .into(binding.ivBlogItem)
            }

            itemView.setOnClickListener {
                itemView.findNavController().navigate(
                    ViewPagerContainerFragmentDirections.actionStartDestToBlogDetailFragment(
                        blog,
                        blogDate
                    )
                )
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Data>() {

    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.title == newItem.title
    }
}
