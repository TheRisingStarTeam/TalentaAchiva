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

class ManagementActivity : AppCompatActivity() {
    private lateinit var viewmodel:ManagementVM
    private lateinit var binding: ActivityManagementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManagementBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this,ManagementFactory(CURRENT_USER_ID, CURRENT_EVENT_ID)).get(ManagementVM::class.java)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_management)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_people, R.id.navigation_stream, R.id.navigation_assignments))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    companion object {
        const val CURRENT_EVENT_ID = "Fishing"
        const val CURRENT_USER_ID = "Fish"
    }
}