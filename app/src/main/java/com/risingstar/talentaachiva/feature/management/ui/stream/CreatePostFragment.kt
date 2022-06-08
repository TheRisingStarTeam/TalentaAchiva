package com.risingstar.talentaachiva.feature.management.ui.stream

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentCreatePostBinding
import com.risingstar.talentaachiva.domain.data.Post
import com.risingstar.talentaachiva.feature.management.ManagementVM


class CreatePostFragment : Fragment() {

    private lateinit var binding : FragmentCreatePostBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)

        viewmodel.currentUser().observe(viewLifecycleOwner){
            if (it != null) {
                binding.tvUsernamePostMan.text = viewmodel.currentUser.name
            }
        }

        binding.buttonSubmitPostMan.setOnClickListener {
            val post = Post(
                null,
                binding.tvUsernamePostMan.text.toString(),
                binding.editDescriptionPostMan.text.toString(),
                null
            )
            viewmodel.createPost(post)
        }

        return binding.root
    }


}