package com.risingstar.talentaachiva.feature.event

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityDebugDetailBinding

class EventDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDebugDetailBinding
    private lateinit var viewmodel : DetailVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugDetailBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this,DetailFactory(EVENT_ID)).get(DetailVM::class.java)

        val detailPagerAdapter = DetailPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = detailPagerAdapter

        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager){ tab ,position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.viewPager.adapter = detailPagerAdapter

        setContentView(binding.root)
    }

    companion object{
        const val EVENT_ID : String = "Event ID"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )


    }
}

