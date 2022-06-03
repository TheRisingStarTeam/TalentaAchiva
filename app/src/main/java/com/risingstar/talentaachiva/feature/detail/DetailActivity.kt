package com.risingstar.talentaachiva.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.detailToolbar))
        binding.toolbarLayout.title = title
    }
}