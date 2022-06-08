package com.risingstar.talentaachiva.feature.judge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityJudgeBinding

class JudgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJudgeBinding
    private lateinit var viewmodel : JudgeVM
    val username = intent.getStringExtra(CURRENT_USER_ID)
    val event = intent.getStringExtra(CURRENT_EVENT_ID)
    val assignment = intent.getStringExtra(CURRENT_ASSIGNMENT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJudgeBinding.inflate(layoutInflater)
        if(username!=null && event!=null && assignment!=null)
            viewmodel = ViewModelProvider(
                this,JudgeFactory(username, event, assignment)
            )[JudgeVM::class.java]
        Toast.makeText(this,"Welcome to $this $username, $event, $assignment", Toast.LENGTH_SHORT).show()

        setContentView(binding.root)
    }

    companion object{
        const val CURRENT_USER_ID = "Fish"
        const val CURRENT_ASSIGNMENT_ID = "Fwish"
        const val CURRENT_EVENT_ID = "Fishing"
    }
}