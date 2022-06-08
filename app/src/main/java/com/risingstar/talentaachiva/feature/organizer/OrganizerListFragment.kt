package com.risingstar.talentaachiva.feature.organizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentOrganizerListBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.util.SearchAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OrganizerListFragment : Fragment() {

    private var _binding: FragmentOrganizerListBinding? = null
    private lateinit var viewmodel:OrganizerVM
    private lateinit var rvEvents: RecyclerView
    private lateinit var rvAdapter: SearchAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity())[OrganizerVM::class.java]
        rvEvents = binding.rvEvents
        rvEvents.layoutManager = LinearLayoutManager(requireActivity())
        viewmodel.organizedEvents().observe(viewLifecycleOwner){
            rvAdapter = SearchAdapter(it as ArrayList<Event>)
            rvEvents.adapter = rvAdapter
        }

        binding.floatingActionButton.setOnClickListener {
            TODO("GO TO CREATE EVENT FRAGMENT")
        }

        _binding = FragmentOrganizerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}