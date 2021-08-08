package com.example.buttercms.ui.main.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
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
            Observer<List<Page>> { page ->
                pageAdapter.submitList(page)
            })

        val recyclerView = binding.rvPages
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = pageAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

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


class PageAdapter() :
    ListAdapter<Page, PageAdapter.NewsItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_page, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(page: Page) {
            val binding = ItemPageBinding.bind(itemView)
            val date = DateFormatter().formatDate(page.published)
            binding.apply {
//                binding.tvAuthorPage.text = page.author
                binding.tvTitlePage.text = page.name
                binding.tvDescriptionPage.text = page.subtitle
                binding.tvTimePage.text = date

            }

            itemView.setOnClickListener {
                itemView.findNavController().navigate(ViewPagerContainerFragmentDirections.actionStartDestToPageDetailFragment(page, date.toString()))
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
