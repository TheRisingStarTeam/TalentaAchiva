package com.risingstar.talentaachiva.feature.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeCreatePostBinding

class CreatePostFragment : Fragment() {

    private lateinit var binding : FragmentFakeCreatePostBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        binding = FragmentFakeCreatePostBinding.inflate(layoutInflater,container,false)

//        binding.buttonSubmitPostMan.setOnClickListener {
//            val post = viewmodel.currentUser?.let { user ->
//                Post(
//                    null,
//                    binding.tvUsernamePostMan.text.toString(),
//                    binding.editDescriptionPostMan.text.toString(),
//                    user.uid
//                )
//            }
//            if (post != null) {
//                viewmodel.createPost(post)
//            }
//        }

        return binding.root
    }


}