package com.example.buttercms.ui.main.page

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
import com.example.buttercms.databinding.FragmentPageBinding
import com.example.buttercms.databinding.ItemPageBinding
import com.example.buttercms.model.Page

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

        // load data to the list
        pageAdapter = PageAdapter(pageViewModel.fetchData())
        val recyclerView = binding.rvPages
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = pageAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class PageAdapter(var pages: List<Page>) :
    RecyclerView.Adapter<PageAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_page, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(page: Page) {
            val binding = ItemPageBinding.bind(itemView)
            binding.apply {
                binding.tvAuthorPage.text = page.author
                binding.tvTitlePage.text = page.title
                binding.tvDescriptionPage.text = page.description
                binding.tvTimePage.text = page.published
            }

            itemView.setOnClickListener {
                itemView.findNavController().navigate(R.id.action_start_dest_to_pageDetailFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return pages.size
    }
}
