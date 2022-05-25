package com.risingstar.talentaachiva.feature.organizerpage.participantmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityParticipantManagerBinding

class ParticipantManagerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityParticipantManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}