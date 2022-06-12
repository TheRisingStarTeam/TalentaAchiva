package com.risingstar.talentaachiva.feature.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewmodel: DashboardVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra(CURRENT_USER_ID)

        viewmodel = ViewModelProvider(this,DashboardFactory(username!!))[DashboardVM::class.java]
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        //Toast.makeText(this,"Welcome to $this $username", Toast.LENGTH_SHORT).show()


        val navView: BottomNavigationView = binding.navView


        setContentView(binding.root)
        setSupportActionBar(binding.tbDashboard)
        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_profile, R.id.navigation_stream, R.id.navigation_todolist, R.id.navigation_settings))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    companion object {
        const val CURRENT_USER_ID = "Fish"
    }
}