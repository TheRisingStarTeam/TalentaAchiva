package com.risingstar.talentaachiva.feature.dashboard

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        val bottomnavView: BottomNavigationView = binding.appBarDashboardActivity.dashboardBottomNav

        val navController =
            findNavController(R.id.nav_host_fragment_content_dashboard_activity)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_profile, R.id.navigation_organized, R.id.navigation_participated), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottomnavView.setupWithNavController(navController)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this,DebugFactory())[DebugVM::class.java]

        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.navigation_home -> {
                // TODO navigate to home page
                true
            }
            R.id.navigation_search -> {
                //TODO navigate to search page
                true
            }
            R.id.navigation_allassignment ->{
                //TODO navigate to allassignment page
                true
            }
            else -> false
        }
    }
}