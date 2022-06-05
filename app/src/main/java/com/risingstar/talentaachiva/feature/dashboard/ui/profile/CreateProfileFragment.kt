package com.risingstar.talentaachiva.feature.dashboard.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentCreateProfileBinding
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM

class CreateProfileFragment : Fragment() {

    private var _binding: FragmentCreateProfileBinding? = null
    private lateinit var viewmodel : DashboardVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_profile, container, false)
    }
}