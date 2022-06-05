package com.risingstar.talentaachiva.feature.dashboard.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentDashboardBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM
import com.risingstar.talentaachiva.feature.util.EventAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var rvEvents: RecyclerView
    private lateinit var rvAdapter: EventAdapter
    private lateinit var viewmodel: DashboardVM

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(DashboardVM::class.java)
        rvEvents = binding.rvEvent
        rvEvents.layoutManager = GridLayoutManager(this.context,1,RecyclerView.HORIZONTAL,false)

        viewmodel.allEvents().observe(viewLifecycleOwner){
            rvAdapter = EventAdapter(it as ArrayList<Event>)
            rvEvents.adapter = rvAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}