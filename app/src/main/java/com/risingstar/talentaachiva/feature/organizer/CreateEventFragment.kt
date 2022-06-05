package com.risingstar.talentaachiva.feature.organizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentCreateEventBinding
import com.risingstar.talentaachiva.domain.data.Event

class CreateEventFragment : Fragment() {
    private lateinit var binding : FragmentCreateEventBinding
    private lateinit var viewmodel : OrganizerVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(OrganizerVM::class.java)
        binding = FragmentCreateEventBinding.inflate(layoutInflater,container,false)

        var event = Event(TODO("Handle The Event Creation Stuff..."))

        return binding.root
    }
}