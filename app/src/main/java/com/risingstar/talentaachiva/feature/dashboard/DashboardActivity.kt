package com.risingstar.talentaachiva.feature.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding : ActivityDashboardBinding
    private lateinit var viewmodel : DebugVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.appBarDashboardActivity.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController =
            findNavController(R.id.nav_host_fragment_content_dashboard_activity)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        binding = ActivityDashboardBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this,DebugFactory())[DebugVM::class.java]

        setContentView(binding.root)
    }
}