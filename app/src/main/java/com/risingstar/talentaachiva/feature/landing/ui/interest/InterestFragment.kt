package com.risingstar.talentaachiva.feature.landing.ui.interest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentInterestBinding
import com.risingstar.talentaachiva.domain.data.Interest
import com.risingstar.talentaachiva.feature.landing.LandingVM
import com.risingstar.talentaachiva.feature.util.InterestAdapter


class InterestFragment : Fragment() {

    val interests = mutableListOf<Interest>(
        Interest("E-Sports","https://cdn.discordapp.com/attachments/985110137750585415/985117664559132712/esports.jpg"),
        Interest("Gaming","https://media.discordapp.net/attachments/985110137750585415/985117664890454057/gaming.png"),
        Interest("Music","https://cdn.discordapp.com/attachments/985110137750585415/985117665150513192/music.png"),
        Interest("Sports","https://cdn.discordapp.com/attachments/985110137750585415/985117665372827658/sports.png"),
        Interest("Video","https://cdn.discordapp.com/attachments/985110137750585415/985117665632878603/video.png"),
        Interest("Streaming","https://cdn.discordapp.com/attachments/985110137750585415/985117665930665994/vtuber.png")
    )

    private lateinit var binding : FragmentInterestBinding
    private lateinit var viewmodel : LandingVM
    private lateinit var rvInterest: RecyclerView
    private lateinit var rvAdapter : InterestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInterestBinding.inflate(layoutInflater,container,false)
        viewmodel = ViewModelProvider(requireActivity())[LandingVM::class.java]

        rvInterest = binding.rvInterest
        rvInterest.layoutManager = GridLayoutManager(this.context,2,RecyclerView.VERTICAL,false)
        rvAdapter = InterestAdapter(interests as ArrayList<Interest>)
        rvInterest.adapter = rvAdapter

        return binding.root
    }
}