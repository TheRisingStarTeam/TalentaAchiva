package com.risingstar.talentaachiva.feature.event

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {

            0 -> fragment = EventDetailFragment()
            1 -> fragment = EventRegisterFragment()
            2 -> fragment = EventListPeopleFragment()
        }

        return fragment as Fragment
    }
}