package com.risingstar.talentaachiva.feature.management.ui.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentAnnouncementBinding
import com.risingstar.talentaachiva.feature.management.ManagementVM


class AnnouncementFragment : Fragment() {

    private lateinit var binding : FragmentAnnouncementBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentAnnouncementBinding.inflate(layoutInflater,container,false)
        viewmodel = ViewModelProvider(requireActivity())[ManagementVM::class.java]



        return binding.root
    }
}