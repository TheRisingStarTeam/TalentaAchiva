package com.risingstar.talentaachiva.feature.judge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityJudgeBinding

class JudgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJudgeBinding
    private lateinit var viewmodel : JudgeVM
    private lateinit var username :String
    private lateinit var event : String
    private lateinit var assignment : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = intent.getStringExtra(CURRENT_USER_ID).toString()
        event = intent.getStringExtra(CURRENT_EVENT_ID).toString()
        assignment = intent.getStringExtra(CURRENT_ASSIGNMENT).toString()

        binding = ActivityJudgeBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(
            this,JudgeFactory(username, event, assignment)
        )[JudgeVM::class.java]
        Toast.makeText(this,"Welcome to $this $username, $event, $assignment", Toast.LENGTH_SHORT).show()

        setContentView(binding.root)
    }

    companion object{
        const val CURRENT_USER_ID = "Fish"
        const val CURRENT_ASSIGNMENT = "Fwish"
        const val CURRENT_EVENT_ID = "Fishing"
    }
}