package com.risingstar.talentaachiva.feature.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityMainDebugBinding

class MainDebugActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainDebugBinding
    private lateinit var viewmodel : DebugVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDebugBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this,DebugFactory())[DebugVM::class.java]


    }
}