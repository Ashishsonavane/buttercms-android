package com.example.buttercms.ui.main.blog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.buttercms.databinding.FragmentBlogDetailBinding

class BlogDetailFragment : Fragment() {
    private val args: BlogDetailFragmentArgs by navArgs()
    private var _binding: FragmentBlogDetailBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().title = "BlogDetail"

        binding.tvAuthorBlogDetail.text =
            args.blog.author?.firstName + " " + args.blog.author?.lastName
        binding.tvTitleBlogDetail.text = args.blog.title
        binding.tvSubtitleBlogDetail.text = args.blog.subtitle
        binding.tvTimeBlogDetail.text = args.time
        binding.wVBlogDetail.apply {
            loadData(args.blog.body, "text/html; charset=UTF-8", "null")
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = false
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
