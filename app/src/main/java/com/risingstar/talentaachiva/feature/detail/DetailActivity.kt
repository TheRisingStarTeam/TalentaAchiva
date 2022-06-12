package com.risingstar.talentaachiva.feature.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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


        viewmodel.events().observe(this){ data ->
            if (data != null) {
                Glide.with(binding.ivBannerDetail).load(data.banner).into(binding.ivBannerDetail)
            }
        }

        Toast.makeText(this,"Welcome to $this $event $username", Toast.LENGTH_SHORT).show()
        viewmodel.getDetail()

        setSupportActionBar(findViewById(R.id.detailToolbar))
        binding.toolbarLayout.title = title
    }

    companion object{
        const val CURRENT_USER = "CURRENT_USER"
        const val CURRENT_EVENT = "CURRENT_EVENT"
    }
}