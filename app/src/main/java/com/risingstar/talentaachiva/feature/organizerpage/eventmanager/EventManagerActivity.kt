package com.risingstar.talentaachiva.feature.organizerpage.eventmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityEventManagerBinding

class EventManagerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEventManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}