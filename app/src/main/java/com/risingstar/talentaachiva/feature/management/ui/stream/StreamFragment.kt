package com.risingstar.talentaachiva.feature.management.ui.stream

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentStreamBinding
import com.risingstar.talentaachiva.domain.data.Post
import com.risingstar.talentaachiva.feature.management.ManagementVM
import com.risingstar.talentaachiva.feature.util.PostAdapter

class StreamFragment : Fragment() {
    private lateinit var viewmodel:ManagementVM
    private var _binding: FragmentStreamBinding? = null
    private lateinit var rvPosts: RecyclerView
    private lateinit var rvAdapter: PostAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        _binding = FragmentStreamBinding.inflate(inflater, container, false)
        rvPosts = binding.rvPosts
        rvPosts.layoutManager = LinearLayoutManager(requireActivity())
        viewmodel.posts().observe(viewLifecycleOwner){ posts ->
            rvAdapter = PostAdapter(posts as ArrayList<Post>)
            rvPosts.adapter = rvAdapter
        }
        binding.cardShare.setOnClickListener {
            TODO("Go To Post Creator Page")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}