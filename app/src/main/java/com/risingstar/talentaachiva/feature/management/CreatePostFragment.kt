package com.risingstar.talentaachiva.feature.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeCreatePostBinding
import com.risingstar.talentaachiva.domain.data.Post

class CreatePostFragment : Fragment() {

    private lateinit var binding : FragmentFakeCreatePostBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        binding = FragmentFakeCreatePostBinding.inflate(layoutInflater,container,false)

        binding.button5.setOnClickListener {
            val post = viewmodel.currentUser?.let { user ->
                Post(
                    null,
                    binding.textView.text.toString(),
                    binding.etContent.text.toString(),
                    user.uid
                )
            }
            if (post != null) {
                viewmodel.createPost(post)
            }
        }

        return binding.root
    }


}