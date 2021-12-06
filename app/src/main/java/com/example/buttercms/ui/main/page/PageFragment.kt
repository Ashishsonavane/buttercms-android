package com.example.buttercms.ui.main.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentPageBinding
import com.example.buttercms.databinding.ItemPageBinding
import com.example.buttercms.model.Page
import com.example.buttercms.ui.main.viewpager.ViewPagerContainerFragmentDirections

class PageFragment : Fragment() {

    private val pageViewModel: PageViewModel by viewModels()
    private var _binding: FragmentPageBinding? = null
    private lateinit var pageAdapter: PageAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        val view = binding.root

        pageAdapter = PageAdapter()
        pageViewModel.getData().observe(
            viewLifecycleOwner,
            { page ->
                pageAdapter.submitList(page.sortedBy { it.fields.study_date })
            }
        )

        pageViewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        binding.rvPages.apply {
            adapter = pageAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        binding.srlReloadPage.apply {
            setOnRefreshListener {
                pageViewModel.loadData()
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
        pageViewModel.loadData()
        super.onResume()
    }
}

class PageAdapter :
    ListAdapter<Page, PageAdapter.NewsItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(page: Page) {
            val binding = ItemPageBinding.bind(itemView)
            val date = page.published.toString()
            val pageDate = date.substring(0, 10) + date.substring(29, 34)
            binding.apply {
                tvTitlePage.text = page.name
                tvStudyNameDetail.text = pageDate
                tvReviewNamePage.text = page.fields.reviewer
                Glide.with(itemView)
                    .load(page.fields.featured_image)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken)
                    .into(imgPage)

                itemView.setOnClickListener {
                    itemView.findNavController().navigate(
                        ViewPagerContainerFragmentDirections.actionStartDestToPageDetailFragment(
                            page, pageDate
                        )
                    )
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Page>() {

    override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
        return oldItem.name == newItem.name
    }
}
