package com.example.buttercms.ui.main.page

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
import com.example.buttercms.databinding.FragmentPageBinding
import com.example.buttercms.databinding.ItemPageBinding
import com.example.buttercms.model.Page
import com.example.buttercms.ui.main.viewpager.ViewPagerContainerFragmentDirections
import com.example.buttercms.utils.DateFormatter
import com.example.buttercms.utils.createCoroutineErrorHandler
import kotlinx.coroutines.launch

class PageFragment : Fragment() {

    private val pageViewModel: PageViewModel by viewModels()
    private var _binding: FragmentPageBinding? = null
    private lateinit var pageAdapter: PageAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        val view = binding.root

        pageAdapter = PageAdapter()
        pageViewModel.getData().observe(
            viewLifecycleOwner,
            { page ->
                pageAdapter.submitList(page)
            }
        )

        binding.rvPages.apply {
            adapter = pageAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        binding.srlReloadPage.apply {
            setOnRefreshListener {
                lifecycleScope.launch(createCoroutineErrorHandler(requireContext())) {
                    pageViewModel.loadData()
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
            pageViewModel.loadData()
        }
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
            val date = DateFormatter().formatDate(page.fields.study_date)
            binding.apply {
                tvTitlePage.text = page.name
                tvStudyNameDetail.text = date
                Glide.with(itemView)
                    .load(page.fields.featured_image)
                    .into(imgPage)

                itemView.setOnClickListener {
                    itemView.findNavController().navigate(
                        ViewPagerContainerFragmentDirections.actionStartDestToPageDetailFragment(
                            page, date.toString()
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
