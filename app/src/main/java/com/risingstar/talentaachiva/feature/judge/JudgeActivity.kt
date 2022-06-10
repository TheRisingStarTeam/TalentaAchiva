package com.risingstar.talentaachiva.feature.judge

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityJudgeBinding

class JudgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJudgeBinding
    private lateinit var viewmodel : JudgeVM
    private lateinit var username :String
    private lateinit var event : String
    private lateinit var assignment : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = intent.getStringExtra(CURRENT_USER_ID).toString()
        event = intent.getStringExtra(CURRENT_EVENT_ID).toString()
        assignment = intent.getStringExtra(CURRENT_ASSIGNMENT).toString()

        binding = ActivityJudgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProvider(
            this,JudgeFactory(username, event, assignment)
        )[JudgeVM::class.java]

        val detailPagerAdapter = JudgePagerAdapter(this)
        binding.viewPager.adapter = detailPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    companion object{
        const val CURRENT_USER_ID = "Fish"
        const val CURRENT_ASSIGNMENT = "Fwish"
        const val CURRENT_EVENT_ID = "Fishing"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_criteria,
            R.string.tab_submission
        )
    }
}