package com.example.buttercms.ui.main.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import com.example.buttercms.R
import com.example.buttercms.databinding.FragmentCollectionBinding
import com.example.buttercms.databinding.ItemCollectionBinding
import com.example.buttercms.model.Collection

class CollectionFragment : Fragment() {

    private val collectionViewModel: CollectionViewModel by viewModels()
    private lateinit var collectionAdapter: CollectionAdapter
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val view = binding.root

        collectionAdapter = CollectionAdapter()
        collectionViewModel.getData().observe(
            viewLifecycleOwner,
            { collections ->
                collectionAdapter.submitList(collections)
            }
        )

        collectionViewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        binding.rvCollections.apply {
            adapter = collectionAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        binding.srlReloadCollection.apply {
            setOnRefreshListener {
                collectionViewModel.loadData()
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
        collectionViewModel.loadData()
        super.onResume()
    }

    class CollectionAdapter :
        ListAdapter<Collection, CollectionAdapter.NewsItemViewHolder>(DiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_collection, parent, false)
            return NewsItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(collection: Collection) {
                val binding = ItemCollectionBinding.bind(itemView)
                binding.apply {
                    tvTitleCollection.text = collection.question
                    tvSubtitleCollection.text = collection.answer
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Collection>() {

        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem.question == newItem.question
        }
    }
}
