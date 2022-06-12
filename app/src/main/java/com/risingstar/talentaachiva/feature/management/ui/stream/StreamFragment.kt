package com.risingstar.talentaachiva.feature.management.ui.stream

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentStreamBinding
import com.risingstar.talentaachiva.domain.data.Post
import com.risingstar.talentaachiva.feature.management.ManagementVM
import com.risingstar.talentaachiva.feature.util.PostAdapter

class StreamFragment : Fragment() {
    private lateinit var viewmodel:ManagementVM
    private var _binding: FragmentStreamBinding? = null
    private lateinit var rvPosts: RecyclerView
    private lateinit var rvAdapter: PostAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity())[ManagementVM::class.java]
        _binding = FragmentStreamBinding.inflate(inflater, container, false)
        rvPosts = binding.rvPosts
        rvPosts.layoutManager = LinearLayoutManager(requireActivity())

        viewmodel.posts().observe(viewLifecycleOwner){ posts ->
            rvAdapter = PostAdapter(posts as ArrayList<Post>)
            rvPosts.adapter = rvAdapter

            rvAdapter.setOnItemClickCallback(object: PostAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Post) {
                    viewmodel.currentPost = data
                    findNavController().navigate(R.id.navigate_post)
                }

            })
        }

//        viewmodel.currentUser().observe(viewLifecycleOwner){
//            if (it != null) {
//                Glide.with(binding.ivUserCreatePost).load(it.profilePic).into(binding.ivUserCreatePost)
//            }
//        }



        binding.cardShare.setOnClickListener {
            it.findNavController().navigate(R.id.navigation_create_post)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}