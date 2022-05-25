package com.risingstar.talentaachiva.feature.participantpage.submissionpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivitySubmissionManagerBinding

class SubmissionManagerActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySubmissionManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmissionManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}