package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityParticipantBinding
import com.risingstar.talentaachiva.feature.event.DetailVM

class ParticipantActivity : AppCompatActivity() {
    private lateinit var binding : ActivityParticipantBinding
    private lateinit var viewmodel : DetailVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParticipantBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[DetailVM::class.java]

        setContentView(binding.root)
    }
}