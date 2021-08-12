package com.example.buttercms.ui.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {
            btnBlogPreview.setOnClickListener {
                switchTab(1)
            }
            btnPages.setOnClickListener {
                switchTab(2)
            }

            btnCollections.setOnClickListener {
                switchTab(3)
            }

            val urlBlog = "https://buttercms.com/features/#flexiblecontentmodeling-blog-engine"
            val urlPage = "https://buttercms.com/features/#flexiblecontentmodeling-page-types"
            val urlCollection =
                "https://buttercms.com/features/#flexiblecontentmodeling-collections"
            val urlDocumentation = "https://buttercms.com/docs/api/?javascript#get-a-single-page"

            btnLearnMoreBlog.setOnClickListener {
                redirectUrl(urlBlog)
            }
            btnLearnMorePage.setOnClickListener {
                redirectUrl(urlPage)
            }

            btnLearnMoreCollection.setOnClickListener {
                redirectUrl(urlCollection)
            }

            btnReadDoc.setOnClickListener {
                redirectUrl(urlDocumentation)
            }
        }

        return binding.root
    }

    private fun switchTab(index: Int) {
        val tabHost = activity?.findViewById<View>(R.id.tabLayout) as TabLayout
        tabHost.getTabAt(index)!!.select()
    }

    private fun redirectUrl(url: String) {
        val uri = Uri.parse(url)
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.let { it1 -> customTabsIntent.launchUrl(it1, uri) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
