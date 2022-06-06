package com.risingstar.talentaachiva.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityDetailBinding
import com.risingstar.talentaachiva.domain.data.Event

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewmodel : DetailVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra(CURRENT_USER)
        val event = intent.getParcelableExtra<Event>(CURRENT_EVENT)

        if(username!=null && event?.eventId !=null)
            viewmodel = ViewModelProvider(
                this,DetailFactory(username, event.eventId!!)
            )[DetailVM::class.java]

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.getDetail()

        setSupportActionBar(findViewById(R.id.detailToolbar))
        binding.toolbarLayout.title = title
    }

    companion object{
        const val CURRENT_USER = "CURRENT_USER"
        const val CURRENT_EVENT = "CURRENT_EVENT"
    }
}