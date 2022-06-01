package com.risingstar.talentaachiva.feature.management.participant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.ActivityManagementBinding

class ManagementActivity : AppCompatActivity() {
    private lateinit var binding : ActivityManagementBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManagementBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[ManagementVM::class.java]

        setContentView(binding.root)
    }
}