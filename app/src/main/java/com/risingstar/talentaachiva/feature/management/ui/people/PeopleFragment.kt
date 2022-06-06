package com.risingstar.talentaachiva.feature.management.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentPeopleBinding
import com.risingstar.talentaachiva.feature.management.ManagementVM
import com.risingstar.talentaachiva.feature.util.PeopleAdapter

class PeopleFragment : Fragment() {

    private lateinit var viewmodel : ManagementVM
    private var _binding: FragmentPeopleBinding? = null
    private lateinit var rvParticipant: RecyclerView
    private lateinit var rvOrganizer : RecyclerView
    private lateinit var rvParticipantAdapter: PeopleAdapter
    private lateinit var rvOrganizerAdapter:PeopleAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        rvParticipant = binding.rvParticipant
        rvOrganizer = binding.rvOrganizer



        viewmodel.people().observe(viewLifecycleOwner){ people ->

        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}