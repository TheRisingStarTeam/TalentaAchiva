package com.risingstar.talentaachiva.feature.dashboard.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentSearchBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM
import com.risingstar.talentaachiva.feature.detail.DetailActivity
import com.risingstar.talentaachiva.feature.management.ManagementActivity
import com.risingstar.talentaachiva.feature.util.SearchAdapter


class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private lateinit var viewmodel : DashboardVM
    private lateinit var rvAdapter : SearchAdapter
    private lateinit var rvSearch : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(DashboardVM::class.java)
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)

        rvSearch = binding.rvSearch

        viewmodel.searchEvent().observe(viewLifecycleOwner){
            rvAdapter = SearchAdapter(it as ArrayList<Event>)
            rvSearch.adapter = rvAdapter
            rvAdapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Event) {
                    showSelected(data)
                }
            })
        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            //
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    viewmodel.getEventSearch(query)
                    true
                } else false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return binding.root
    }

    private fun showSelected(event: Event) {
        if(event.participants?.contains(viewmodel.userID) != true){
            val intent = Intent(this.context,DetailActivity::class.java)
            intent.putExtra(DetailActivity.CURRENT_EVENT,event)
            startActivity(intent)
        }else{
            val intent = Intent(this.context, ManagementActivity::class.java)
            intent.putExtra(ManagementActivity.CURRENT_EVENT,event)
            intent.putExtra(ManagementActivity.CURRENT_USER,viewmodel.userID)
            startActivity(intent)
        }
    }
}