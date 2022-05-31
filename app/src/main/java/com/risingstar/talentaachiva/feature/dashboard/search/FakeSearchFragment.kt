package com.risingstar.talentaachiva.feature.dashboard.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentFakeSearchBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.dashboard.DebugVM
import com.risingstar.talentaachiva.feature.dashboard.EventAdapter

class FakeSearchFragment : Fragment() {

    private lateinit var binding : FragmentFakeSearchBinding
    private lateinit var viewmodel : DebugVM

    private lateinit var rvEvent: RecyclerView
    private lateinit var rvAdapter : EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeSearchBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DebugVM::class.java]

        rvEvent = binding.rvSearch
        rvEvent.layoutManager = LinearLayoutManager(this.context)

        binding.idTako.setOnClickListener {
            viewmodel.getEventSearch(binding.editTextTextPersonName.text.toString())
        }
        viewmodel.searchEvent().observe(viewLifecycleOwner){list ->
            rvAdapter = EventAdapter(list as ArrayList<Event>)
            rvEvent.adapter = rvAdapter

//            rvAdapter.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
//                override fun onItemClicked(data: Event) {
//                    TODO("Intent to Detail Event ")
//                }
//            })
        }

        return binding.root
    }
}