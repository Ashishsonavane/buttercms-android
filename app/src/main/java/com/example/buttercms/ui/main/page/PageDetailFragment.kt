package com.example.buttercms.ui.main.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.buttercms.databinding.FragmentPageDetailBinding

class PageDetailFragment : Fragment() {
    private val args: PageDetailFragmentArgs by navArgs()
    private var _binding: FragmentPageDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            tvTitlePageDetail.text = args.page.fields.title
            tvStudyDateName.text = args.page.fields.study_date
            tvIndustryName.text = args.page.fields.industry
            tvContentPageDetail.text = args.page.fields.content
            Glide.with(view)
                .load(args.page.fields.featured_image)
                .into(ivPageDetail)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
