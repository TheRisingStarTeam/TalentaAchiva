package com.risingstar.talentaachiva.feature.participantpage.mainpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityActiveEventBinding

class ActiveEventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityActiveEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActiveEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}