package com.risingstar.talentaachiva.feature.organizerpage.createevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityAssignmentManagerBinding
import com.risingstar.talentaachiva.databinding.ActivityEventCreatorBinding

class EventCreatorActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEventCreatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventCreatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}