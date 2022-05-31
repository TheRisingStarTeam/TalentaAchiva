package com.risingstar.talentaachiva.feature.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityDebugDetailBinding

class DebugDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDebugDetailBinding
    private lateinit var viewmodel : DetailVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugDetailBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this,DetailFactory(EVENT_ID)).get(DetailVM::class.java)

        val detailPagerAdapter = DetailPagerAdapter(this)
        binding.viewPager.adapter = detailPagerAdapter

        setContentView(binding.root)
    }

    companion object{
        const val EVENT_ID : String = "Event ID"
    }
}

