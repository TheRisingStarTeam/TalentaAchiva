package com.risingstar.talentaachiva.feature.browseevent.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {
    private lateinit var binding : FragmentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}