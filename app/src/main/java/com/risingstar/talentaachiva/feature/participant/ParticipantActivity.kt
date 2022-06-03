package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityParticipantBinding

class ParticipantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}