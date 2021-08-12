package com.example.buttercms.ui.main.blog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
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

        val authorFirstName = args.blog.author.firstName
        val authorLastName = args.blog.author.lastName

        binding.apply {
            tvAuthorBlogDetail.text =
                "$authorFirstName $authorLastName"
            tvTitleBlogDetail.text = args.blog.title
            tvSubtitleBlogDetail.text = args.blog.subtitle
            tvTimeBlogDetail.text = args.time
            Glide.with(view)
                .load(args.blog.image)
                .into(ivBlogDetail)

            wvBlogDetail.apply {
                loadData(args.blog.body, "text/html; charset=UTF-8", "null")
                settings.javaScriptEnabled = true
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
