package com.risingstar.talentaachiva.feature.dashboard.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentSettingsBinding
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM
import com.risingstar.talentaachiva.feature.organizer.OrganizerActivity

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private lateinit var viewmodel: DashboardVM

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(DashboardVM::class.java)

        binding.buttonSwitchOrg.setOnClickListener{
            val intent = Intent(requireActivity(), OrganizerActivity::class.java)
            intent.putExtra(OrganizerActivity.CURRENT_USER_ID,viewmodel.userID)
            startActivity(intent)
        }

        binding.buttonLogout.setOnClickListener{

        }


        return binding.root


    }


}