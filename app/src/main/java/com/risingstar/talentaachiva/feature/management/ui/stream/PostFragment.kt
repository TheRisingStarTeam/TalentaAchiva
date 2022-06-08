package com.risingstar.talentaachiva.feature.management.ui.stream

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentPostBinding
import com.risingstar.talentaachiva.feature.management.ManagementVM

class PostFragment : Fragment() {

    private lateinit var binding : FragmentPostBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        binding = FragmentPostBinding.inflate(inflater, container, false)


        binding.tvTitlePost.text = viewmodel.currentPost.title
        binding.tvDescPost.text = viewmodel.currentPost.content


        return binding.root
    }


}