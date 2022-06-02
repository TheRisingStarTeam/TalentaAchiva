package com.risingstar.talentaachiva.feature.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentFakeEventStreamBinding
import com.risingstar.talentaachiva.domain.data.Post

class EventStreamFragment : Fragment() {
    private lateinit var binding : FragmentFakeEventStreamBinding
    private lateinit var viewmodel : ManagementVM

    private lateinit var rvPosts: RecyclerView
    private lateinit var rvAdapter : PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        binding = FragmentFakeEventStreamBinding.inflate(layoutInflater,container,false)
        viewmodel.getPosts()

        rvPosts = binding.rvPosts
        rvPosts.layoutManager = LinearLayoutManager(this.context)

        viewmodel.allPosts().observe(viewLifecycleOwner){ list->
            rvAdapter = PostAdapter(list as ArrayList<Post>)
            rvPosts.adapter = rvAdapter

            rvAdapter.setOnItemClickCallback(object : PostAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Post) {
                    TODO("Go To Post Fragment")
                }
            })
        }

        binding.tvPost.setOnClickListener {
            TODO("Go To Post Creator")
        }


        return binding.root
    }
}