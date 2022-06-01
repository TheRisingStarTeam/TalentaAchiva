package com.risingstar.talentaachiva.feature.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentFakeRegEventBinding
import com.risingstar.talentaachiva.domain.data.Event

class EventListPeopleFragment : Fragment() {

    private lateinit var viewmodel : DetailVM
    private lateinit var binding : FragmentFakeRegEventBinding
    private var event: Event? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

}