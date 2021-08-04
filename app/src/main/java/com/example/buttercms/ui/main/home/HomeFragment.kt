package com.example.buttercms.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
