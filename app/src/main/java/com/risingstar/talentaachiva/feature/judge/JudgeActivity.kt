package com.risingstar.talentaachiva.feature.judge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.databinding.ActivityJudgeBinding

class JudgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJudgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJudgeBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}