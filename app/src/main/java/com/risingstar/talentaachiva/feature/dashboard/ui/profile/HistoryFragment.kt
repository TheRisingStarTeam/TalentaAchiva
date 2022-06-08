package com.risingstar.talentaachiva.feature.dashboard.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentHistoryBinding
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM

class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding
    private lateinit var viewmodel : DashboardVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(DashboardVM::class.java)

        return inflater.inflate(R.layout.fragment_history, container, false)
    }

}