package com.risingstar.talentaachiva.feature.dashboard.ui.dashboard

import android.content.Intent
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
import com.risingstar.talentaachiva.feature.detail.DetailActivity
import com.risingstar.talentaachiva.feature.management.ManagementActivity
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
            rvAdapter.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Event) {
                    showSelected(data)
                }
            })
        }

        return binding.root
    }

    private fun showSelected(event: Event) {
        if(event.participants?.contains(viewmodel.userID) != true){
            val intent = Intent(this.context,DetailActivity::class.java)
            intent.putExtra(DetailActivity.CURRENT_EVENT,event)
            startActivity(intent)
        }else{
            val intent = Intent(this.context,ManagementActivity::class.java)
            intent.putExtra(ManagementActivity.CURRENT_EVENT,event)
            intent.putExtra(ManagementActivity.CURRENT_USER,viewmodel.userID)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}