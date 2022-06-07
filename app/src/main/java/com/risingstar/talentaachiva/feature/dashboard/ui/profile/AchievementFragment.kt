package com.risingstar.talentaachiva.feature.dashboard.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentAchievementBinding
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM

class AchievementFragment : Fragment() {

    private lateinit var binding : FragmentAchievementBinding
    private lateinit var viewmodel: DashboardVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

}