package com.example.buttercms.ui.main.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.*
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentHomeBinding
import com.example.buttercms.databinding.ItemHomeBinding
import com.example.buttercms.model.HomePage
import com.example.buttercms.model.Section
import com.example.buttercms.utils.createCoroutineErrorHandler
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeAdapter = HomeAdapter(requireContext())

        homeViewModel.getData().observe(
            viewLifecycleOwner,
            { homePage ->
                homeAdapter.submitList(homePage[0].fields.section)
                val headlineName = formatHeadline(homePage)
                binding.tvTitleHome.text = headlineName.first + "\n" + headlineName.second

                val subHeadline = homePage[0].fields.subheadline
                binding.tvSubtitleHome.text = subHeadline

                binding.btnReadDoc.setOnClickListener {
                    redirectUrl(homePage[0].fields.docUrl)
                }
            }
        )

        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        binding.apply {
            srlReloadHome.apply {
                setOnRefreshListener {
                    lifecycleScope.launch(createCoroutineErrorHandler(requireContext())) {
                        homeViewModel.loadData()
                    }
                    isRefreshing = false
                }
            }
        }

        return binding.root
    }

    private fun formatHeadline(homePage: List<HomePage>): Pair<String, String> {
        val headline = homePage[0].fields.headline
        val headlineFirst = headline.substring(0, headline.indexOf(' '))
        val headlineSecond = headline.substring(headline.indexOf(' '))
            .replace("""^\s*|\s*$""".toRegex(), "")
        return Pair(headlineFirst, headlineSecond)
    }

    private fun redirectUrl(url: String) {
        val uri = Uri.parse(url)
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.let { customTabsIntent.launchUrl(it, uri) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        lifecycleScope.launch(createCoroutineErrorHandler(requireContext())) {
            homeViewModel.loadData()
        }
        super.onResume()
    }
}

class HomeAdapter(val context: Context) :
    ListAdapter<Section, HomeAdapter.NewsItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(section: Section) {
            val binding = ItemHomeBinding.bind(itemView)
            binding.apply {
                tvTitleHomePage.text = section.title
                tvSubtitleHomePage.text = section.subtitle
                btnPreviewHomePage.setOnClickListener {

                    if (layoutPosition == 0) {
                        switchTab(1)
                    }

                    if (layoutPosition == 1) {
                        switchTab(2)
                    }
                    if (layoutPosition == 2) {
                        switchTab(3)
                    }
                }

                btnLearnHomePage.setOnClickListener {
                    redirectUrl(section.buttonurl)
                }
            }
        }

        private fun switchTab(index: Int) {
            val activity = itemView.context as Activity
            val tabHost = activity.findViewById(R.id.tabLayout) as TabLayout
            tabHost.getTabAt(index)!!.select()
        }

        private fun redirectUrl(url: String?) {
            val uri = Uri.parse(url)
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.let { customTabsIntent.launchUrl(it, uri) }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Section>() {

    override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
        return oldItem.title == newItem.title
    }
}
