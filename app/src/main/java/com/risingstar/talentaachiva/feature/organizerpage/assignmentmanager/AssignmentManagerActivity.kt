package com.risingstar.talentaachiva.feature.organizerpage.assignmentmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityAssignmentManagerBinding

class AssignmentManagerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAssignmentManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}