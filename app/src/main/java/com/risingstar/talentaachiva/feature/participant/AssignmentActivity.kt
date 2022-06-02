package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityAssignmentBinding

class AssignmentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAssignmentBinding
    private lateinit var viewmodel : AssignmentVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAssignmentBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this, AssignmentFactory(EVENT_ID,ASSIGNMENT_ID))
            .get(AssignmentVM::class.java)


        setContentView(binding.root)
    }

    companion object{
        const val EVENT_ID : String = "Event ID"
        const val ASSIGNMENT_ID : String = "Event ID"
    }
}