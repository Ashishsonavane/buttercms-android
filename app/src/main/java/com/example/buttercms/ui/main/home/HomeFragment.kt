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

/**
 * A placeholder fragment containing a simple view.
 */
class HomeFragment : Fragment() {

    private lateinit var pageViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnBlogPreview.setOnClickListener {
            val tabhost = activity?.findViewById<View>(R.id.tabLayout) as TabLayout
            tabhost.getTabAt(1)!!.select()
        }

        binding.btnPages.setOnClickListener {
            val tabhost = activity?.findViewById<View>(R.id.tabLayout) as TabLayout
            tabhost.getTabAt(2)!!.select()
        }

        binding.btnCollections.setOnClickListener {
            val tabhost = activity?.findViewById<View>(R.id.tabLayout) as TabLayout
            tabhost.getTabAt(3)!!.select()
        }

        binding.btnReadDoc.setOnClickListener {
            val url = "https://buttercms.com/docs/api/?javascript#get-a-single-page"
            val uri = Uri.parse(url)
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.let { it1 -> customTabsIntent.launchUrl(it1, uri) }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
