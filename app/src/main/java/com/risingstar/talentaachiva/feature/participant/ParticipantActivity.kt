package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityParticipantBinding

class ParticipantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantBinding
    private lateinit var viewmodel : ParticipantVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val assignment = intent.getStringExtra(CURRENT_ASSIGNMENT_ID)
        if(assignment!=null)
            viewmodel = ViewModelProvider(
                this,ParticipantFactory(assignment)
            )[ParticipantVM::class.java]

        binding = ActivityParticipantBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object{
        const val CURRENT_ASSIGNMENT_ID = "FISH!"
        const val CURRENT_USER = "User"
    }
}