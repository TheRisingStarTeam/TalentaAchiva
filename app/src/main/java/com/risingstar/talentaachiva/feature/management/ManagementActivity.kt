package com.risingstar.talentaachiva.feature.management

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityManagementBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity

class ManagementActivity : AppCompatActivity() {
    private lateinit var viewmodel:ManagementVM
    private lateinit var binding: ActivityManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getParcelableExtra<Identity>(CURRENT_USER)
        val event = intent.getParcelableExtra<Event>(CURRENT_EVENT)

        binding = ActivityManagementBinding.inflate(layoutInflater)

        if(username?.userId!=null && event?.eventId!=null)
        viewmodel = ViewModelProvider(
            this,ManagementFactory(username.userId!!, event.eventId!!)
        )[ManagementVM::class.java]
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_management)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_people, R.id.navigation_stream, R.id.navigation_assignments))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    companion object {
        const val CURRENT_EVENT = "Fishing"
        const val CURRENT_USER = "Fish"
    }
}