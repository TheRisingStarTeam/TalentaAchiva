package com.risingstar.talentaachiva.feature.organizer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentOrganizerListBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.management.ManagementActivity
import com.risingstar.talentaachiva.feature.util.SearchAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OrganizerListFragment : Fragment() {

    private var _binding: FragmentOrganizerListBinding? = null
    private lateinit var viewmodel:OrganizerVM
    private lateinit var rvEvents: RecyclerView
    private lateinit var rvAdapter: SearchAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentOrganizerListBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[OrganizerVM::class.java]


        rvEvents = binding.rvEvents
        rvEvents.layoutManager = LinearLayoutManager(requireActivity())
        viewmodel.organizedEvents().observe(viewLifecycleOwner){
            rvAdapter = SearchAdapter(it as ArrayList<Event>)
            rvEvents.adapter = rvAdapter
            rvAdapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Event) {
                    if(data.eventId!=null)
                    goToManagement(data.eventId!!)
                }

            })
        }

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.org_to_cev)
        }


        return binding.root
    }

    private fun goToManagement(data:String){
        val intent = Intent(this.context, ManagementActivity::class.java)
        intent.putExtra(ManagementActivity.CURRENT_EVENT,data)
        intent.putExtra(ManagementActivity.CURRENT_USER,viewmodel.userID)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}