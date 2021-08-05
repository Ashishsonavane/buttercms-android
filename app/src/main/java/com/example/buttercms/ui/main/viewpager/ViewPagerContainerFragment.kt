package com.example.buttercms.ui.main.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.buttercms.R
import com.example.buttercms.ui.main.blog.BlogFragment
import com.example.buttercms.ui.main.collection.CollectionFragment
import com.example.buttercms.ui.main.home.HomeFragment
import com.example.buttercms.ui.main.page.PageFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_viewpager_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter =
            ChildFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Blogs"
                2 -> "Posts"
                else -> "Collections"
            }
        }.attach()
    }
}

class ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeFragment()
            1 -> BlogFragment()
            2 -> PageFragment()
            else -> CollectionFragment()
        }
    }
}
