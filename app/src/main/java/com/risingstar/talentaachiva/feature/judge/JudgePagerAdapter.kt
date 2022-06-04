package com.risingstar.talentaachiva.feature.judge

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.risingstar.talentaachiva.feature.judge.ui.criteria.CriteriaFragment
import com.risingstar.talentaachiva.feature.judge.ui.submissions.SubmissionsFragment

class JudgePagerAdapter(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CriteriaFragment()
            1 -> fragment = SubmissionsFragment()
        }

        return fragment as Fragment
    }
}